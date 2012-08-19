package fr.atbdx.lightningtalk.tests.domaine.mongodb;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Test;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import fr.atbdx.lightningtalk.domaine.mongodb.AssistantMongo;

public class AssistantMongoTest {

    private static final String SOUS_DB_OBJECT = "sousDBObject";
    private static final String NOM_DE_L_ATTRIBUT = "nomDeLAttribut";
    private static final String VALEUR = "valeur";

    @Test
    public void peutRecupererUneChaineDUnDBObject() {
        DBObject dbOject = BasicDBObjectBuilder.start().add(NOM_DE_L_ATTRIBUT, VALEUR).get();

        String valeur = AssistantMongo.dans(dbOject).recupererChaine(NOM_DE_L_ATTRIBUT);

        assertThat(valeur, is(VALEUR));
    }

    @Test
    public void peutRecupererUnSousObjetDansUnDBObject() {
        DBObject sousDBObjectAttendu = BasicDBObjectBuilder.start().get();
        DBObject dbOject = BasicDBObjectBuilder.start().add(SOUS_DB_OBJECT, sousDBObjectAttendu).get();

        DBObject sousDBObject = AssistantMongo.dans(dbOject).recupererSousDBObject(SOUS_DB_OBJECT);

        assertThat(sousDBObject, is(sousDBObjectAttendu));
    }

    @Test
    public void peutRecupererObjectIdDansUnDBObject() {
        ObjectId objectIdAttendu = new ObjectId();
        DBObject dbOject = BasicDBObjectBuilder.start().add(AssistantMongo.ID, objectIdAttendu).get();

        ObjectId objectId = AssistantMongo.dans(dbOject).recupererObjectId();

        assertThat(objectId, is(objectIdAttendu));
    }

    @Test
    public void peutRecupererListeDansUnDBObject() {
        List<DBObject> listDeDBObject = Arrays.asList(BasicDBObjectBuilder.start().get());
        DBObject dbObject = BasicDBObjectBuilder.start().add("liste", listDeDBObject).get();

        List<DBObject> liste = AssistantMongo.dans(dbObject).recupererListe("liste");

        assertThat(liste, is(listDeDBObject));
    }

}
