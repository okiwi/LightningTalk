package fr.atbdx.lightningtalk.tests.domaine;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.UnknownHostException;

import org.junit.Test;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import com.mongodb.DB;
import com.mongodb.MongoException;

import fr.atbdx.lightningtalk.domaine.ConfigurationDuDomaine;

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
    public void peutInitialiserMongoDB() throws UnknownHostException, MongoException {
        DB intialiserMongoDB = ConfigurationDuDomaine.intialiserMongoDB("localhost", 1234);

        assertThat(intialiserMongoDB.getMongo().getAddress().getHost(), is("localhost"));
        assertThat(intialiserMongoDB.getMongo().getAddress().getPort(), is(1234));

    }

}
