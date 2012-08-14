package fr.atbdx.lightningtalk.tests.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import fr.atbdx.lightningtalk.doublures.domaine.FakeEntrepotUtilisateur;
import fr.atbdx.lightningtalk.doublures.domaine.google.FakeConnecteurGoogle;
import fr.atbdx.lightningtalk.web.ControlleurAuthentification;

public class ControlleurAuthentificationTest {

    private static final String CODE_ERREUR = "codeErreur";
    private static final String CODE = "code";
    private FakeEntrepotUtilisateur entrepotUtilisateur;
    private ControlleurAuthentification controlleurAuthentification;

    @Before
    public void avantLesTests() {
        entrepotUtilisateur = new FakeEntrepotUtilisateur();
        controlleurAuthentification = new ControlleurAuthentification(entrepotUtilisateur);
    }

    @Test
    public void demanderAuthentificationExterne() {

        String urlDAuthentificationExterne = controlleurAuthentification.demanderAuthentificationExterne();

        assertThat(urlDAuthentificationExterne, is("redirect:" + FakeConnecteurGoogle.URL_DU_SERVICE_D_AUTHENTIFICATION_EXTERNE));
    }

    @Test
    public void authentification() throws IOException {

        String urlDAuthentificationExterne = controlleurAuthentification.authentification(CODE, CODE_ERREUR);

        assertThat(entrepotUtilisateur.codePasserPourAuthentifier, is(CODE));
        assertThat(entrepotUtilisateur.codeErreurPasserPourAuthentifier, is(CODE_ERREUR));
        assertThat(urlDAuthentificationExterne, is("redirect:accueil"));
    }

}
