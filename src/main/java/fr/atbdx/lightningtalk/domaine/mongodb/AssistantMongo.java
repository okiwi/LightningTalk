package fr.atbdx.lightningtalk.domaine.mongodb;

import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.DBObject;

public class AssistantMongo {
    private final DBObject dbOject;
    public static final String ID = "_id";

    private AssistantMongo(DBObject dbOject) {
        this.dbOject = dbOject;
    }

    public String recupererChaine(String nomDeLAttribut) {
        return (String) dbOject.get(nomDeLAttribut);
    }

    public DBObject recupererSousDBObject(String nomDeLAttribut) {
        return (DBObject) dbOject.get(nomDeLAttribut);
    }

    public ObjectId recupererObjectId() {
        return (ObjectId) dbOject.get(ID);
    }

    @SuppressWarnings("unchecked")
    public  List<DBObject> recupererListe(String nomDeLAttribut) {
        return (List<DBObject>) dbOject.get(nomDeLAttribut);
    }

    public static AssistantMongo dans(DBObject dbOject) {
        return new AssistantMongo(dbOject);
    }

}
