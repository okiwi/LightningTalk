package fr.atbdx.lightningtalk.domaine.mongodb;

import java.util.List;

import org.mongolink.MongoSession;
import org.mongolink.MongoSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.atbdx.lightningtalk.domaine.EntrepotSession;
import fr.atbdx.lightningtalk.domaine.ImpossibleDeCreerUneSession;
import fr.atbdx.lightningtalk.domaine.Session;

@Repository
public class EntrepotSessionMongo extends EntrepotMongo implements EntrepotSession {

    @Autowired
    public EntrepotSessionMongo(MongoSessionManager sessionManager) {
        super(sessionManager);
    }

    public EntrepotSessionMongo(final MongoSession sessionMongo) {
        super(sessionMongo);
    }

    public void creer(Session session) throws ImpossibleDeCreerUneSession {
        if (sessionMongo.get(session.getTitre(), Session.class) != null) {
            throw new ImpossibleDeCreerUneSession("Une session avec le même titre existe déjà.");
        }
        sessionMongo.save(session);
    }

    public List<Session> recupererLesSessions() {
        return sessionMongo.getAll(Session.class);
    }

    public Session recuperer(String titreDeLaSession) {
        return sessionMongo.get(titreDeLaSession, Session.class);
    }

    public void mettreAJour(Session sessionAMettreAJour) {
        sessionMongo.update(sessionAMettreAJour);
    }

}
