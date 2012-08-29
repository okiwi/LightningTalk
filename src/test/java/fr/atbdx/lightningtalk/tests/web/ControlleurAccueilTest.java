package fr.atbdx.lightningtalk.tests.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import fr.atbdx.lightningtalk.domaine.Utilisateur;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLAuthentification;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesUtilisateurs;
import fr.atbdx.lightningtalk.web.ControlleurAccueil;

public class ControlleurAccueilTest {

    private ControlleurAccueil controlleurAccueil;
    private AidePourLAuthentification aidePourLAuthentification;

    @Before
    public void avantLesTest() {
        aidePourLAuthentification = AidePourLAuthentification.getInstance();
        controlleurAccueil = new ControlleurAccueil(aidePourLAuthentification.serviceDAuthentification);
    }

    @Test
    public void valoriserAccueilNonAuthentifie() throws IOException {

        ModelAndView valorisation = controlleurAccueil.valoriserAccueil();

        assertThat(valorisation.getViewName(), is("accueil"));
        assertThat(valorisation.getModel().containsKey("utilisateur"), is(false));
    }

    @Test
    public void valoriserAccueilAuthentifie() throws IOException {
        aidePourLAuthentification.simulerAuthentification();

        ModelAndView valorisation = controlleurAccueil.valoriserAccueil();

        assertThat(valorisation.getViewName(), is("accueil"));
        assertThat((Utilisateur) valorisation.getModel().get("utilisateur"), is(AidePourLesUtilisateurs.UTILISATEUR));
    }
}
