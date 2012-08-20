package fr.atbdx.lightningtalk.domaine.mongodb;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.FakeDB;
import com.mongodb.MongoException;
import fr.atbdx.lightningtalk.domaine.Session;
import fr.atbdx.lightningtalk.domaine.doublures.AidePourLesSessions;
import fr.atbdx.lightningtalk.domaine.doublures.AidePourLesUtilisateurs;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mongolink.MongoSession;
import org.mongolink.test.FakePersistentContext;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class EntrepotSessionMongoTest {

    @Rule
    public FakePersistentContext context = new FakePersistentContext("fr.atbdx.lightningtalk.domaine.mongodb.mapping");

    private FakeDB baseLightningTalk;
    private EntrepotSessionMongo entrepotDeSessionMongo;

    @Before
    public void avantLesTests() throws UnknownHostException {
        final MongoSession session = context.getSession();
        entrepotDeSessionMongo = new EntrepotSessionMongo(session);
        baseLightningTalk = (FakeDB) session.getDb();
    }

    @Test
    public void creerUneSession() throws MongoException, IOException {
        Session session = AidePourLesSessions.creer();

        entrepotDeSessionMongo.creerUneSession(session);

        DBCollection sessions = baseLightningTalk.getCollection("session");
        assertThat(sessions.count(), is(1l));
        DBObject sessionMango = sessions.findOne();
        assertThat((String) sessionMango.get("titre"), is(AidePourLesSessions.TITRE_DE_LA_SESSION));
        assertThat((String) sessionMango.get("description"), is(AidePourLesSessions.DESCRIPTION_DE_LA_SESSION));
        assertThat((String) ((DBObject) sessionMango.get("orateur")).get("id"), is(AidePourLesUtilisateurs.ID));
        assertThat((String) ((DBObject) sessionMango.get("orateur")).get("nomAffiche"), is(AidePourLesUtilisateurs.NOM_AFFICHE));
    }

    @Test
    public void listerUneSession() {
        entrepotDeSessionMongo.creerUneSession(AidePourLesSessions.creer());

        List<Session> sessions = entrepotDeSessionMongo.recupererLesSessions();

        assertThat(sessions.size(), is(1));
        AidePourLesSessions.verifier(sessions.get(0));
    }

    @Test
    public void listerDeuxSessions() {
        entrepotDeSessionMongo.creerUneSession(AidePourLesSessions.creer());
        entrepotDeSessionMongo.creerUneSession(AidePourLesSessions.creerAvecSuffixe("2"));

        List<Session> sessions = entrepotDeSessionMongo.recupererLesSessions();

        assertThat(sessions.size(), is(2));
        AidePourLesSessions.verifierAvecSuffixe(sessions.get(1), "2");
        AidePourLesSessions.verifier(sessions.get(0));
    }

}
