package fr.atbdx.lightningtalk.tests.domaine.mongodb;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

import fr.atbdx.lightningtalk.domaine.EntrepotUtilisateur;
import fr.atbdx.lightningtalk.domaine.ImpossibleDeCreerUneSession;
import fr.atbdx.lightningtalk.domaine.Utilisateur;
import fr.atbdx.lightningtalk.domaine.mongodb.EntrepotUtilisateurMongo;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesUtilisateurs;
import fr.atbdx.lightningtalk.doublures.mongodb.AidePourMongoDb;

public class EntrepotUtilisateurMongoTest extends BasePourLesTestsDesEntrepotsMongo {

    private EntrepotUtilisateur entrepotUtilisateurMongo;

    @Before
    public void avantLesTests() {
        entrepotUtilisateurMongo = new EntrepotUtilisateurMongo(session);
    }

    @Test
    public void peutCreerUnUtilisateur() throws MongoException, IOException, ImpossibleDeCreerUneSession {
        Utilisateur utilisateur = AidePourLesUtilisateurs.UTILISATEUR;

        entrepotUtilisateurMongo.creer(utilisateur);

        DBCollection utilisateurs = baseLightningTalk.getCollection("utilisateur");
        assertThat(utilisateurs.count(), is(1l));
        DBObject utilisateurMongo = utilisateurs.findOne();
        assertThat(AidePourMongoDb.dans(utilisateurMongo).recupererChaine(AidePourMongoDb.ID), is(AidePourLesUtilisateurs.ID));
        assertThat(AidePourMongoDb.dans(utilisateurMongo).recupererChaine("nomAffiche"), is(AidePourLesUtilisateurs.NOM_AFFICHE));
        assertThat(AidePourMongoDb.dans(utilisateurMongo).recupererChaine("urlImage"), is(AidePourLesUtilisateurs.URL_IMAGE));
        assertThat(AidePourMongoDb.dans(utilisateurMongo).recupererChaine("urlProfil"), is(AidePourLesUtilisateurs.URL_PROFIL));
    }

    @Test
    public void peutRecupererUnUtilisateurParSonId() throws ImpossibleDeCreerUneSession {
        entrepotUtilisateurMongo.creer(AidePourLesUtilisateurs.UTILISATEUR);

        Utilisateur utilisateur = entrepotUtilisateurMongo.recuperer(AidePourLesUtilisateurs.ID);

        AidePourLesUtilisateurs.verifier(utilisateur);
    }

    @Test
    public void peutMettreAJourUnUtilisateur() throws MongoException, IOException, ImpossibleDeCreerUneSession {
        Utilisateur utilisateur = AidePourLesUtilisateurs.UTILISATEUR;
        entrepotUtilisateurMongo.creer(utilisateur);
        Utilisateur utilisateurAvecMemeId = new Utilisateur(AidePourLesUtilisateurs.ID, "nouveauNom", AidePourLesUtilisateurs.URL_IMAGE, AidePourLesUtilisateurs.URL_PROFIL);

        entrepotUtilisateurMongo.mettreAJour(utilisateurAvecMemeId);

        DBCollection utilisateurs = baseLightningTalk.getCollection("utilisateur");
        assertThat(utilisateurs.count(), is(1l));
        assertThat(AidePourMongoDb.dans(utilisateurs.findOne()).recupererChaine("nomAffiche"), is("nouveauNom"));
    }
}
