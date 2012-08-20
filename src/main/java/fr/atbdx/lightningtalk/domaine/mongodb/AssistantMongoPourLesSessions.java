package fr.atbdx.lightningtalk.domaine.mongodb;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import static fr.atbdx.lightningtalk.domaine.mongodb.AssistantMongo.dans;

import fr.atbdx.lightningtalk.domaine.ImpossibleDeCreerUneSession;
import fr.atbdx.lightningtalk.domaine.Participant;
import fr.atbdx.lightningtalk.domaine.Session;

public class AssistantMongoPourLesSessions {
    public static final String TITRE = "titre";
    public static final String DESCRIPTION = "description";
    public static final String ORATEUR = "orateur";
    public static final String PARTICIPANT_NOM_AFFICHE = "nomAffiche";
    public static final String PARTICIPANT_ID = "id";
    public static final String VOTANTS = "votants";

    public static DBObject fabriquerPourUneCreation(Session session) {
        return ajouterLeTitreLaDescriptionEtLOrateur(session, BasicDBObjectBuilder.start()).get();
    }

    public static DBObject fabriquerPourUneMiseAJour(SessionMongo sessionMongo) {
        BasicDBObjectBuilder sessionDBObject = BasicDBObjectBuilder.start().add(AssistantMongo.ID, sessionMongo.id);
        List<DBObject> votantsDBObjects = new ArrayList<DBObject>();
        for (Participant votant : sessionMongo.getVotants()) {
            votantsDBObjects.add(fabriquerParticipantDBObject(votant));
        }
        sessionDBObject.add(VOTANTS, votantsDBObjects);
        return ajouterLeTitreLaDescriptionEtLOrateur(sessionMongo, sessionDBObject).get();
    }

    public static Session fabriquer(DBObject sessionDBObject) {
        Participant orateur = contruireParticipant(dans(sessionDBObject).recupererSousDBObject(ORATEUR));
        SessionMongo sessionMongo;
        try {
            sessionMongo = new SessionMongo(dans(sessionDBObject).recupererObjectId(), dans(sessionDBObject).recupererChaine(TITRE), dans(sessionDBObject).recupererChaine(
                    DESCRIPTION), orateur);
        } catch (ImpossibleDeCreerUneSession e) {
            throw new RuntimeException(e);
        }
        List<DBObject> votantsDBOject = dans(sessionDBObject).recupererListe(VOTANTS);
        if (votantsDBOject != null) {
            for (DBObject votantObject : votantsDBOject) {
                sessionMongo.ajouterUnVote(contruireParticipant(votantObject));
            }
        }
        return sessionMongo;
    }

    private static BasicDBObjectBuilder ajouterLeTitreLaDescriptionEtLOrateur(Session session, BasicDBObjectBuilder builderSession) {
        return builderSession.add(TITRE, session.getTitre()).add(DESCRIPTION, session.getDescription()).add(ORATEUR, fabriquerParticipantDBObject(session.getOrateur()));
    }

    private static DBObject fabriquerParticipantDBObject(Participant participant) {
        return BasicDBObjectBuilder.start().add(PARTICIPANT_ID, participant.getId()).add(PARTICIPANT_NOM_AFFICHE, participant.getNomAffiche()).get();
    }

    public static Participant contruireParticipant(DBObject participantDBObject) {
        return new Participant(dans(participantDBObject).recupererChaine(PARTICIPANT_ID), dans(participantDBObject).recupererChaine(PARTICIPANT_NOM_AFFICHE));
    }

    public static DBObject fabriquerPourLaRechercheParTitre(String titrePourLaRecherche) {
        return BasicDBObjectBuilder.start().add(TITRE, titrePourLaRecherche).get();
    }
}
