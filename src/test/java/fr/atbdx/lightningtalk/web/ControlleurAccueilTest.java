package fr.atbdx.lightningtalk.web;

import fr.atbdx.lightningtalk.domaine.Utilisateur;
import fr.atbdx.lightningtalk.domaine.doublures.AidePourLesUtilisateurs;
import fr.atbdx.lightningtalk.domaine.doublures.FakeEntrepotUtilisateur;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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
        entrepotUtilisateur.utilisateurCourantARetourner = AidePourLesUtilisateurs.UTILISATEUR;

        ModelAndView valorisation = controlleurAccueil.valoriserAccueil();

        assertThat(valorisation.getViewName(), is("accueil"));
        assertThat((Utilisateur) valorisation.getModel().get("utilisateur"), is(AidePourLesUtilisateurs.UTILISATEUR));
    }
}
