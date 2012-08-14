package fr.atbdx.lightningtalk.tests.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import fr.atbdx.lightningtalk.domaine.Utilisateur;
import fr.atbdx.lightningtalk.doublures.domaine.FakeEntrepotUtilisateur;
import fr.atbdx.lightningtalk.doublures.domaine.google.AidePourLAuthentification;
import fr.atbdx.lightningtalk.web.ControlleurAccueil;

public class ControlleurAccueilTest {

    private FakeEntrepotUtilisateur entrepotUtilisateur;
    private ControlleurAccueil controlleurAccueil;

    @Before
    public void avantLesTest() {
        entrepotUtilisateur = new FakeEntrepotUtilisateur();
        controlleurAccueil = new ControlleurAccueil(entrepotUtilisateur);
    }

    @Test
    public void valoriserAccueilNonAuthentifie() throws IOException {

        ModelAndView valorisation = controlleurAccueil.valoriserAccueil();

        assertThat(valorisation.getViewName(), is("accueil"));
        assertThat(valorisation.getModel().containsKey(Utilisateur.class), is(false));
    }

    @Test
    public void valoriserAccueilAuthentifie() throws IOException {
        entrepotUtilisateur.utilisateurCourantARetourner = AidePourLAuthentification.UTILISATEUR;

        ModelAndView valorisation = controlleurAccueil.valoriserAccueil();

        assertThat(valorisation.getViewName(), is("accueil"));
        assertThat((Utilisateur) valorisation.getModel().get("utilisateur"), is(AidePourLAuthentification.UTILISATEUR));
    }
}
