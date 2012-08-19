package fr.atbdx.lightningtalk.tests.domaine.mongodb;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

import fr.atbdx.lightningtalk.domaine.Session;
import fr.atbdx.lightningtalk.domaine.mongodb.EntrepotSessionMongo;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesParticipants;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesSessions;

public class EntrepotSessionMongoTest {

    private DB baseLightningTalk;
    private EntrepotSessionMongo entrepotDeSessionMongo;

    @Before
    public void avantLesTests() throws UnknownHostException {
        Mongo mongo = new Mongo("localhost", 27017);
        baseLightningTalk = mongo.getDB("LightningTalkTest");
        entrepotDeSessionMongo = new EntrepotSessionMongo(baseLightningTalk);
    }

    @Test
    public void creerUneSession() throws MongoException, IOException {
        Session session = AidePourLesSessions.creer();

        entrepotDeSessionMongo.creerUneSession(session);

        DBCollection sessions = baseLightningTalk.getCollection("sessions");
        assertThat(sessions.count(), is(1l));
        DBObject sessionMango = sessions.findOne();
        assertThat((String) sessionMango.get("titre"), is(AidePourLesSessions.TITRE_DE_LA_SESSION));
        assertThat((String) sessionMango.get("description"), is(AidePourLesSessions.DESCRIPTION_DE_LA_SESSION));
        assertThat((String) ((DBObject) sessionMango.get("orateur")).get("id"), is(AidePourLesParticipants.ID));
        assertThat((String) ((DBObject) sessionMango.get("orateur")).get("nomAffiche"), is(AidePourLesParticipants.NOM_AFFICHE));
    }

    @Test
    public void listerUneSession() {
        entrepotDeSessionMongo.creerUneSession(AidePourLesSessions.creer());

        List<Session> sessions = entrepotDeSessionMongo.recupererLesSessions();

        assertThat(sessions.size(), is(1));
        AidePourLesSessions.verifier(sessions.get(0));
    }

    @Test
    public void listerDeuxSessions() {
        entrepotDeSessionMongo.creerUneSession(AidePourLesSessions.creer());
        entrepotDeSessionMongo.creerUneSession(AidePourLesSessions.creerAvecSuffixe("2"));

        List<Session> sessions = entrepotDeSessionMongo.recupererLesSessions();

        assertThat(sessions.size(), is(2));
        AidePourLesSessions.verifierAvecSuffixe(sessions.get(0), "2");
        AidePourLesSessions.verifier(sessions.get(1));
    }

    @Test
    public void peutRecupererUneSessionParSonTitre() {
        entrepotDeSessionMongo.creerUneSession(AidePourLesSessions.creer());

        Session session = entrepotDeSessionMongo.recupererDepuisSonTitre(AidePourLesSessions.TITRE_DE_LA_SESSION);

        AidePourLesSessions.verifier(session);
    }

    @Test
    public void peutSauvegarderUneSession() {
        entrepotDeSessionMongo.creerUneSession(AidePourLesSessions.creer());
        Session sessionAMettreAJour = entrepotDeSessionMongo.recupererDepuisSonTitre(AidePourLesSessions.TITRE_DE_LA_SESSION);
        sessionAMettreAJour.ajouterUnVote(AidePourLesParticipants.PARTICIPANT);

        entrepotDeSessionMongo.sauvegargerUneSession(sessionAMettreAJour);

        Session sessionMiseAJour = entrepotDeSessionMongo.recupererDepuisSonTitre(AidePourLesSessions.TITRE_DE_LA_SESSION);
        AidePourLesSessions.verifier(sessionMiseAJour);
        AidePourLesParticipants.verifier(sessionMiseAJour.getVotants().iterator().next());
    }

    @After
    public void apresLesTests() {
        baseLightningTalk.dropDatabase();
    }

}
