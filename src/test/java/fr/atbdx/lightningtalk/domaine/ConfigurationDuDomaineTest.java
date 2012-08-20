package fr.atbdx.lightningtalk.domaine;

import com.mongodb.DB;
import org.junit.Test;
import org.mongolink.MongoSession;
import org.mongolink.MongoSessionManager;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import java.net.UnknownHostException;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ConfigurationDuDomaineTest {

    @Test
    public void valoriserAPatirDeLOptionDeDemarrageEnvironnement() {
        Environment environment = mock(Environment.class);
        when(environment.getProperty("environnement")).thenReturn("test");

        PropertyPlaceholderConfigurer valorisation = ConfigurationDuDomaine.valorisation(environment);

        assertThat(valorisation, notNullValue());
        verify(environment).getProperty("environnement");
    }

    @Test
    public void peutConfigurerMongo() throws UnknownHostException {
        final MongoSessionManager mongoSessionManager = ConfigurationDuDomaine.initialiserLaBaseLightningTalkMongoDB("localhost", 27017, "toto", "tata");

        assertThat(mongoSessionManager, notNullValue());
        final MongoSession session = mongoSessionManager.createSession();
        final DB db = session.getDb();
        assertThat(db.getMongo().getAddress().getHost(), is("localhost"));
        assertThat(db.getMongo().getAddress().getPort(), is(27017));
    }
}
