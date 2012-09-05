package fr.atbdx.lightningtalk.tests.web;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.atbdx.lightningtalk.domaine.ImpossibleDeSAuthentifier;
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
    public void peutDemanderUrlPourAuthentificationExterne() {

        String urlDAuthentificationExterne = controlleurAuthentification.demanderAuthentificationExterne();

        assertThat(urlDAuthentificationExterne, is(ControlleurAuthentification.REDIRECTION + FakeSystemeDAuthentificationExterne.URL_DU_SYSTEME_D_AUTHENTIFICATION_EXTERNE));
    }

    @Test
    public void peutSAuthentifier() throws ImpossibleDeSAuthentifier {

        String redirection = controlleurAuthentification.authentification(AidePourLAuthentification.CODE_AUTHENTIFICATION, null, null);

        aidePourLAuthentification.verifierLaConnexionAuSystemeDAuthentificationExterneEtLaCreationDeLUtilisateur();
        assertThat(redirection, is(ControlleurAuthentification.REDIRECTION_VERS_PAGE_D_ACCUEIL));
    }

    @Test
    public void ajouterUneErreurFlashSiLAuthentificationAEchouee() throws ImpossibleDeSAuthentifier {
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        controlleurAuthentification.authentification(AidePourLAuthentification.CODE_AUTHENTIFICATION, "codeErreur", redirectAttributes);

        verify(redirectAttributes).addFlashAttribute("erreur", new ImpossibleDeSAuthentifier().getMessage());

    }

    @Test
    public void deconnexion() throws ImpossibleDeSAuthentifier {
        aidePourLAuthentification.simulerAuthentification();

        controlleurAuthentification.deconnexion();

        assertThat(aidePourLAuthentification.serviceDAuthentification.recupererUtilisateurCourant(), nullValue());
    }

}
