package fr.atbdx.lightningtalk.tests.domaine.mongodb;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Test;

import com.mongodb.DBObject;

import static fr.atbdx.lightningtalk.domaine.mongodb.AssistantMongo.dans;
import fr.atbdx.lightningtalk.domaine.Session;
import fr.atbdx.lightningtalk.domaine.mongodb.AssistantMongoPourLesSessions;
import fr.atbdx.lightningtalk.domaine.mongodb.SessionMongo;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesParticipants;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesSessions;

public class AssistantMongoPourLesSessionsTest {

    private static final String VALEUR = "valeur";
    private static final ObjectId OBJECT_ID = new ObjectId();

    @Test
    public void peutFabriquerUnDBObjectPourUneCreationDeSession() {
        DBObject sessionDBObject = AssistantMongoPourLesSessions.fabriquerPourUneCreation(AidePourLesSessions.creer());

        verifierSessionDBObjectAvecTitreDescriptionEtOrateur(sessionDBObject);
    }

    @Test
    public void peutFabriquerUnDBObjectPourUneMiseAJourDeSession() {
        SessionMongo sessionMongo = creerUneSessionMongo();

        DBObject sessionDBObject = AssistantMongoPourLesSessions.fabriquerPourUneMiseAJour(sessionMongo);

        assertThat(dans(sessionDBObject).recupererObjectId(), is(OBJECT_ID));
        verifierSessionDBObjectAvecTitreDescriptionEtOrateur(sessionDBObject);
    }

    @Test
    public void peutFabriquerUnDBObjectAvecUnVotePourUneMiseAJourDeSession() {
        SessionMongo sessionMongo = creerUneSessionMongoAvecUnVote();

        DBObject sessionDBObject = AssistantMongoPourLesSessions.fabriquerPourUneMiseAJour(sessionMongo);

        List<DBObject> votants = dans(sessionDBObject).recupererListe(AssistantMongoPourLesSessions.VOTANTS);
        assertThat(votants, notNullValue());
        verifierParticipantDBObject(votants.get(0));
    }

    @Test
    public void peutFabriquerUnDBObjectPourLaRechercheParTitre() {

        DBObject sessionDBObject = AssistantMongoPourLesSessions.fabriquerPourLaRechercheParTitre(VALEUR);

        assertThat(dans(sessionDBObject).recupererChaine(AssistantMongoPourLesSessions.TITRE), is(VALEUR));
        assertThat(sessionDBObject.keySet().size(), is(1));

    }

    @Test
    public void peutFabriquerUneSessionMongoAvecUnVote() {
        DBObject sessionDBObjectAvecUnVote = AssistantMongoPourLesSessions.fabriquerPourUneMiseAJour(creerUneSessionMongoAvecUnVote());

        Session session = AssistantMongoPourLesSessions.fabriquer(sessionDBObjectAvecUnVote);

        AidePourLesSessions.verifier(session);
        assertThat(session.getNombreDeVotes(), is(1));
        AidePourLesParticipants.verifier(session.getVotants().iterator().next());

    }

    private SessionMongo creerUneSessionMongoAvecUnVote() {
        return (SessionMongo) AidePourLesSessions.ajouterUnVote(creerUneSessionMongo());
    }

    private SessionMongo creerUneSessionMongo() {
        return new SessionMongo(OBJECT_ID, AidePourLesSessions.TITRE_DE_LA_SESSION, AidePourLesSessions.DESCRIPTION_DE_LA_SESSION, AidePourLesParticipants.PARTICIPANT);
    }

    private void verifierSessionDBObjectAvecTitreDescriptionEtOrateur(DBObject sessionDBObject) {
        assertThat(dans(sessionDBObject).recupererChaine(AssistantMongoPourLesSessions.TITRE), is(AidePourLesSessions.TITRE_DE_LA_SESSION));
        assertThat(dans(sessionDBObject).recupererChaine(AssistantMongoPourLesSessions.DESCRIPTION), is(AidePourLesSessions.DESCRIPTION_DE_LA_SESSION));
        DBObject orateurMongo = dans(sessionDBObject).recupererSousDBObject(AssistantMongoPourLesSessions.ORATEUR);
        verifierParticipantDBObject(orateurMongo);
    }

    private void verifierParticipantDBObject(DBObject orateurMongo) {
        assertThat(dans(orateurMongo).recupererChaine(AssistantMongoPourLesSessions.PARTICIPANT_ID), is(AidePourLesParticipants.ID));
        assertThat(dans(orateurMongo).recupererChaine(AssistantMongoPourLesSessions.PARTICIPANT_NOM_AFFICHE), is(AidePourLesParticipants.NOM_AFFICHE));
    }

}
