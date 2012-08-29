package fr.atbdx.lightningtalk.tests.domaine.mongodb;

import org.junit.After;
import org.junit.Before;
import org.mongolink.MongoSession;
import org.mongolink.MongoSessionManager;
import org.mongolink.Settings;
import org.mongolink.domain.criteria.RestrictionFactory;
import org.mongolink.domain.criteria.Restrictions;
import org.mongolink.domain.mapper.ContextBuilder;
import org.mongolink.test.FakeCriteriaFactory;
import org.mongolink.test.FakeDBFactory;
import org.mongolink.test.criteria.FakeRestrictonFactory;

import com.mongodb.FakeDB;

public abstract class BasePourLesTestsDesEntrepotsMongo {
    protected FakeDB baseLightningTalk;
    protected MongoSession session;

    @Before
    public void avantLesTestsDesEntrepotsMongo() {
        ContextBuilder context = new ContextBuilder("fr.atbdx.lightningtalk.domaine.mongodb.mapping");
        final MongoSessionManager manager = MongoSessionManager.create(context,
                Settings.defaultInstance().withDbFactory(FakeDBFactory.class).withCriteriaFactory(FakeCriteriaFactory.class));
        session = manager.createSession();
        Restrictions.setFactory(new FakeRestrictonFactory());
        baseLightningTalk = (FakeDB) session.getDb();
    }

    @After
    public void apresLesTests() {
        Restrictions.setFactory(new RestrictionFactory());
    }

}
