package fr.atbdx.lightningtalk.domaine.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import fr.atbdx.lightningtalk.domaine.EntrepotSession;
import fr.atbdx.lightningtalk.domaine.Session;
import fr.atbdx.lightningtalk.domaine.Utilisateur;

@Repository
public class EntrepotSessionMongo implements EntrepotSession {

    private DBCollection sessionsMongo;

    @Autowired
    public EntrepotSessionMongo(DB baseLightningTalk) {
        sessionsMongo = baseLightningTalk.getCollection("sessions");
    }

    @Override
    public void creerUneSession(Session session) {
        DBObject orateurMongo = BasicDBObjectBuilder.start().add("id", session.getOrateur().getId()).add("nomAffiche", session.getOrateur().getNomAffiche()).get();
        sessionsMongo.insert(BasicDBObjectBuilder.start().add("titre", session.getTitre()).add("description", session.getDescription()).add("orateur", orateurMongo).get());
    }

    @Override
    public List<Session> recupererLesSessions() {
        DBCursor curseursDeSessionsMongo = sessionsMongo.find();
        List<Session> sessions = new ArrayList<Session>();
        for (DBObject sessionMongo : curseursDeSessionsMongo) {
            DBObject orateurMongo = (DBObject) sessionMongo.get("orateur");
            Utilisateur orateur = new Utilisateur((String) orateurMongo.get("id"), (String) orateurMongo.get("nomAffiche"));
            sessions.add(new Session((String) sessionMongo.get("titre"), (String) sessionMongo.get("description"), orateur));
        }
        return sessions;
    }
}
