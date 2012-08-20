package fr.atbdx.lightningtalk.tests.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import fr.atbdx.lightningtalk.doublures.spring.FakeResourceHandlerRegistry;
import fr.atbdx.lightningtalk.web.ConfigurationWeb;

public class ConfigurationWebTest {

    @Test
    public void addResourceHandlersUtiliseLesRessourcesCompressees() {
        ConfigurationWeb configurationWeb = new ConfigurationWeb();
        configurationWeb.utiliserLesResourcesCompressees = Boolean.toString(true);
        FakeResourceHandlerRegistry registry = new FakeResourceHandlerRegistry();

        configurationWeb.addResourceHandlers(registry);

        assertThat(registry.ressourceAjouter, is(new String[] { "/ressources/**" }));
        assertThat(registry.resourceHandlerRegistration.resourceLocations, is(new String[] { "/ressources/", "/ressourcesCompressees/" }));
    }

    @Test
    public void addResourceHandlersUtiliseLesRessourcesNonCompressees() {
        ConfigurationWeb configurationWeb = new ConfigurationWeb();
        configurationWeb.utiliserLesResourcesCompressees = Boolean.toString(false);
        FakeResourceHandlerRegistry registry = new FakeResourceHandlerRegistry();

        configurationWeb.addResourceHandlers(registry);

        assertThat(registry.ressourceAjouter, is(new String[] { "/ressources/**" }));
        assertThat(registry.resourceHandlerRegistration.resourceLocations, is(new String[] { "/ressources/", "/ressourcesNonCompressees/" }));
    }
}
