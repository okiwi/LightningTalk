package fr.atbdx.lightningtalk.domaine.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import fr.atbdx.lightningtalk.domaine.EntrepotSession;
import fr.atbdx.lightningtalk.domaine.Session;

@Repository
public class EntrepotSessionMongo implements EntrepotSession {

    private DBCollection sessionsMongo;

    @Autowired
    public EntrepotSessionMongo(DB baseLightningTalk) {
        sessionsMongo = baseLightningTalk.getCollection("sessions");
    }

    @Override
    public void creerUneSession(Session session) {
        sessionsMongo.save(AssistantMongoPourLesSessions.fabriquerPourUneCreation(session));
    }

    @Override
    public List<Session> recupererLesSessions() {
        DBCursor curseursDeSessionsMongo = sessionsMongo.find();
        List<Session> sessions = new ArrayList<Session>();
        for (DBObject sessionDBObject : curseursDeSessionsMongo) {
            sessions.add(0, AssistantMongoPourLesSessions.fabriquer(sessionDBObject));
        }
        return sessions;
    }

    @Override
    public Session recupererDepuisSonTitre(String titreDeLaSession) {
        DBObject sessionDBObject = sessionsMongo.findOne(AssistantMongoPourLesSessions.fabriquerPourLaRechercheParTitre(titreDeLaSession));
        return AssistantMongoPourLesSessions.fabriquer(sessionDBObject);
    }

    @Override
    public void sauvegargerUneSession(Session sessionAMettreAJour) {
        sessionsMongo.save(AssistantMongoPourLesSessions.fabriquerPourUneMiseAJour((SessionMongo) sessionAMettreAJour));
    }
}
