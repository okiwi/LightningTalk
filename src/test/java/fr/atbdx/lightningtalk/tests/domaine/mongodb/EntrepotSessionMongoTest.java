package fr.atbdx.lightningtalk.tests.domaine.mongodb;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

import fr.atbdx.lightningtalk.domaine.EntrepotSession;
import fr.atbdx.lightningtalk.domaine.ImpossibleDeCreerUneSession;
import fr.atbdx.lightningtalk.domaine.OperationPermiseUniquementALOrateur;
import fr.atbdx.lightningtalk.domaine.Session;
import fr.atbdx.lightningtalk.domaine.mongodb.EntrepotSessionMongo;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesSessions;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesUtilisateurs;
import fr.atbdx.lightningtalk.doublures.mongodb.AidePourMongoDb;

public class EntrepotSessionMongoTest extends BasePourLesTestsDesEntrepotsMongo {
    
    private EntrepotSession entrepotSessionMongo;
    
    @Before
    public void avantLesTests() {
        
        entrepotSessionMongo = new EntrepotSessionMongo(session);
    }
    
    @Test
    public void peutCreerUneSession() throws MongoException, IOException, ImpossibleDeCreerUneSession {
        Session session = AidePourLesSessions.creer();
        session.ajouterUnVote(AidePourLesUtilisateurs.UTILISATEUR);
        
        entrepotSessionMongo.creer(session);
        
        DBCollection sessions = baseLightningTalk.getCollection("session");
        assertThat(sessions.count(), is(1l));
        DBObject sessionMango = sessions.findOne();
        assertThat((String) sessionMango.get(AidePourMongoDb.ID), is(AidePourLesSessions.TITRE));
        assertThat((String) sessionMango.get("description"), is(AidePourLesSessions.DESCRIPTION));
        assertThat((String) sessionMango.get("orateur"), is(AidePourLesUtilisateurs.ID));
    }
    
    @Test
    public void creerUneSessionDejaExistanteEnvoitUneErreur() throws ImpossibleDeCreerUneSession {
        Session session = AidePourLesSessions.creer();
        entrepotSessionMongo.creer(session);
        try {
            entrepotSessionMongo.creer(session);
            fail("Créer une session existante devrai lancer une erreur");
        } catch (ImpossibleDeCreerUneSession impossibleDeCreerUneSession) {
            assertThat(impossibleDeCreerUneSession.getMessage(), is("Une session avec le même titre existe déjà."));
        }
        
    }
    
    @Test
    public void listerUneSession() throws ImpossibleDeCreerUneSession {
        entrepotSessionMongo.creer(AidePourLesSessions.creer());
        
        List<Session> sessions = entrepotSessionMongo.recupererLesSessions();
        
        assertThat(sessions.size(), is(1));
        AidePourLesSessions.verifier(sessions.get(0));
    }
    
    @Test
    public void listerDeuxSessions() throws ImpossibleDeCreerUneSession {
        entrepotSessionMongo.creer(AidePourLesSessions.creer());
        entrepotSessionMongo.creer(AidePourLesSessions.creerAvecSuffixe("2"));
        
        List<Session> sessions = entrepotSessionMongo.recupererLesSessions();
        
        assertThat(sessions.size(), is(2));
        AidePourLesSessions.verifier(sessions.get(0));
        AidePourLesSessions.verifierAvecSuffixe(sessions.get(1), "2");
    }
    
    @Test
    public void listerDeuxSessionsRetourneCelleAvecPlusDeVoteEnPremier() throws ImpossibleDeCreerUneSession {
        entrepotSessionMongo.creer(AidePourLesSessions.creer());
        Session sessionAvecPlusDeVote = AidePourLesSessions.creerAvecSuffixe("2");
        sessionAvecPlusDeVote.ajouterUnVote(AidePourLesUtilisateurs.UN_AUTRE_UTILISATEUR);
        entrepotSessionMongo.creer(sessionAvecPlusDeVote);
        
        List<Session> sessions = entrepotSessionMongo.recupererLesSessions();
        
        assertThat(sessions.size(), is(2));
        AidePourLesSessions.verifierAvecSuffixe(sessions.get(0), "2");
        AidePourLesSessions.verifier(sessions.get(1));
    }
    
    @Test
    public void peutRecupererUneSessionParSonTitre() throws ImpossibleDeCreerUneSession {
        entrepotSessionMongo.creer(AidePourLesSessions.creer());
        
        Session session = entrepotSessionMongo.recuperer(AidePourLesSessions.TITRE);
        
        AidePourLesSessions.verifier(session);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void peutSauvegarderUneSession() throws ImpossibleDeCreerUneSession {
        entrepotSessionMongo.creer(AidePourLesSessions.creer());
        Session sessionAMettreAJour = entrepotSessionMongo.recuperer(AidePourLesSessions.TITRE);
        sessionAMettreAJour.ajouterUnVote(AidePourLesUtilisateurs.UTILISATEUR);
        
        entrepotSessionMongo.mettreAJour(sessionAMettreAJour);
        
        DBCollection sessions = baseLightningTalk.getCollection("session");
        assertThat(sessions.count(), is(1l));
        DBObject sessionMango = sessions.findOne();
        assertThat((String) sessionMango.get(AidePourMongoDb.ID), is(AidePourLesSessions.TITRE));
        assertThat((String) sessionMango.get("description"), is(AidePourLesSessions.DESCRIPTION));
        assertThat((String) sessionMango.get("orateur"), is(AidePourLesUtilisateurs.ID));
        assertThat(((List<String>) sessionMango.get("votants")).get(0), is(AidePourLesUtilisateurs.ID));
    }
    
    @Test
    public void peutSupprimerUneSession() throws ImpossibleDeCreerUneSession, OperationPermiseUniquementALOrateur {
        Session session = AidePourLesSessions.creer();
        entrepotSessionMongo.creer(session);
        
        entrepotSessionMongo.supprimer(session, AidePourLesUtilisateurs.UTILISATEUR);
        
        assertThat(entrepotSessionMongo.recupererLesSessions().size(), is(0));
    }
    
    @Test(expected = OperationPermiseUniquementALOrateur.class)
    public void nePeutPasSupprimerUneSessionSiNEstPasOrateur() throws ImpossibleDeCreerUneSession, OperationPermiseUniquementALOrateur {
        Session session = AidePourLesSessions.creer();
        entrepotSessionMongo.creer(session);
        
        entrepotSessionMongo.supprimer(session, AidePourLesUtilisateurs.UN_AUTRE_UTILISATEUR);
    }
    
    @Test
    public void supprimerUneSessionNullNeFaitRien() throws ImpossibleDeCreerUneSession, OperationPermiseUniquementALOrateur {
        entrepotSessionMongo.supprimer(null, AidePourLesUtilisateurs.UTILISATEUR);
    }
}
