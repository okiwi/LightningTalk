package fr.atbdx.lightningtalk.domaine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigurationDuDomaine.class)
public class TestDIntegrationSurLaValorisation {

    @Value("${cle}")
    private String valeur;

    @Test
    public void queLaValorisationEstBienRecupererDepuisLeBonFichier() {

        assertThat(valeur, is("valeur"));
    }

}