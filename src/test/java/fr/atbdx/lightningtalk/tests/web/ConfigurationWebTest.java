package fr.atbdx.lightningtalk.tests.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import fr.atbdx.lightningtalk.doublures.spring.FakeResourceHandlerRegistry;
import fr.atbdx.lightningtalk.web.ConfigurationWeb;

public class ConfigurationWebTest {

    @Test
    public void addResourceHandlersUtiliseLesRessources() {
        ConfigurationWeb configurationWeb = new ConfigurationWeb();
        FakeResourceHandlerRegistry registry = new FakeResourceHandlerRegistry();

        configurationWeb.addResourceHandlers(registry);

        assertThat(registry.ressourceAjouter, is(new String[] { "/ressources/**" }));
        assertThat(registry.resourceHandlerRegistration.resourceLocations, is(new String[] { "/ressources/" }));
    }

}
