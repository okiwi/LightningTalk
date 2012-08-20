package fr.atbdx.lightningtalk.tests.domaine.mongodb;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.bson.types.ObjectId;
import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.ImpossibleDeCreerUneSession;
import fr.atbdx.lightningtalk.domaine.mongodb.SessionMongo;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesParticipants;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesSessions;

public class SessionMongoTest {

    @Test
    public void peutCreerUneSessionMongoDB() throws ImpossibleDeCreerUneSession {
        ObjectId id = new ObjectId();

        SessionMongo sessionMongo = new SessionMongo(id, AidePourLesSessions.TITRE_DE_LA_SESSION, AidePourLesSessions.DESCRIPTION_DE_LA_SESSION,
                AidePourLesParticipants.PARTICIPANT);

        assertThat(sessionMongo.id, is(id));

    }
}
