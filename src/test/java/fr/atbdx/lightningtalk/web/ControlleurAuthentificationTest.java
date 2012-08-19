package fr.atbdx.lightningtalk.web;

import fr.atbdx.lightningtalk.domaine.doublures.FakeEntrepotUtilisateur;
import fr.atbdx.lightningtalk.domaine.doublures.google.FakeConnecteurGoogle;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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
        assertThat(urlDAuthentificationExterne, is("redirect:/"));
    }

}
