package fr.atbdx.lightningtalk.doublures.mongodb;

import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.DBObject;

public class AidePourMongoDb {
    private final DBObject dbOject;
    public static final String ID = "_id";

    private AidePourMongoDb(DBObject dbOject) {
        this.dbOject = dbOject;
    }

    public String recupererChaine(String nomDeLAttribut) {
        return (String) dbOject.get(nomDeLAttribut);
    }

    public ObjectId recupererObjectId() {
        return (ObjectId) dbOject.get(ID);
    }

    @SuppressWarnings("unchecked")
    public List<DBObject> recupererListe(String nomDeLAttribut) {
        return (List<DBObject>) dbOject.get(nomDeLAttribut);
    }

    public static AidePourMongoDb dans(DBObject dbOject) {
        return new AidePourMongoDb(dbOject);
    }

}
