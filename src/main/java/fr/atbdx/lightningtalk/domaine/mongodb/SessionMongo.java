package fr.atbdx.lightningtalk.domaine.mongodb;

import org.bson.types.ObjectId;

import fr.atbdx.lightningtalk.domaine.ImpossibleDeCreerUneSession;
import fr.atbdx.lightningtalk.domaine.Participant;
import fr.atbdx.lightningtalk.domaine.Session;

public class SessionMongo extends Session {

    public final ObjectId id;

    public SessionMongo(ObjectId id, String titre, String description, Participant orateur) throws ImpossibleDeCreerUneSession {
        super(titre, description, orateur);
        this.id = id;
    }

}
