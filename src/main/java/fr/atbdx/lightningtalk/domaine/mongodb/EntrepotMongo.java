package fr.atbdx.lightningtalk.domaine.mongodb;

import org.mongolink.MongoSession;
import org.mongolink.MongoSessionManager;
import org.springframework.beans.factory.annotation.Autowired;

public class EntrepotMongo {

    protected MongoSession sessionMongo;

    @Autowired
    public EntrepotMongo(MongoSessionManager sessionManager) {
        this(sessionManager.createSession());
    }

    public EntrepotMongo(final MongoSession sessionMongo) {
        this.sessionMongo = sessionMongo;
    }

}
