package fr.atbdx.lightningtalk.domaine.mongodb;

import fr.atbdx.lightningtalk.domaine.EntrepotSession;
import fr.atbdx.lightningtalk.domaine.Session;
import org.mongolink.MongoSession;
import org.mongolink.MongoSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntrepotSessionMongo implements EntrepotSession {

    private MongoSession session;

    @Autowired
    public EntrepotSessionMongo(MongoSessionManager sessionManager) {
        this(sessionManager.createSession());
    }

    public EntrepotSessionMongo(final MongoSession session) {
        this.session = session;
    }

    @Override
    public void creerUneSession(Session session) {
        this.session.save(session);
    }

    @Override
    public List<Session> recupererLesSessions() {
        return session.getAll(Session.class);
    }

}
