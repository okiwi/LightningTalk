package fr.atbdx.lightningtalk.tests.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import fr.atbdx.lightningtalk.doublures.domaine.AidePourLAuthentification;
import fr.atbdx.lightningtalk.doublures.domaine.FakeSystemeDAuthentificationExterne;
import fr.atbdx.lightningtalk.web.ControlleurAuthentification;

public class ControlleurAuthentificationTest {

    private AidePourLAuthentification aidePourLAuthentification;
    private ControlleurAuthentification controlleurAuthentification;

    @Before
    public void avantLesTests() {
        aidePourLAuthentification = AidePourLAuthentification.getInstance();
        controlleurAuthentification = new ControlleurAuthentification(aidePourLAuthentification.serviceDAuthentification, aidePourLAuthentification.systemeDAuthentificationExterne);
    }

    @Test
    public void demanderAuthentificationExterne() {

        String urlDAuthentificationExterne = controlleurAuthentification.demanderAuthentificationExterne();

        assertThat(urlDAuthentificationExterne, is("redirect:" + FakeSystemeDAuthentificationExterne.URL_DU_SYSTEME_D_AUTHENTIFICATION_EXTERNE));
    }

    @Test
    public void authentification() throws IOException {

        String urlDAuthentificationExterne = controlleurAuthentification.authentification(AidePourLAuthentification.CODE_AUTHENTIFICATION);

        aidePourLAuthentification.verifierLaConnexionAuSystemeDAuthentificationExterneEtLaCreationDeLUtilisateur();
        assertThat(urlDAuthentificationExterne, is("redirect:/"));
    }

}
