package fr.atbdx.lightningtalk.tests.web;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.ImpossibleDeSAuthentifier;
import fr.atbdx.lightningtalk.domaine.Utilisateur;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLAuthentification;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesUtilisateurs;
import fr.atbdx.lightningtalk.web.ControleurUtilisateurs;

public class ControlleurUtilisateursTest {

    private AidePourLAuthentification aidePourLAuthentification;
    private ControleurUtilisateurs controleurUtilisateur;

    @Before
    public void avantLesTests() {
        aidePourLAuthentification = AidePourLAuthentification.getInstance();
        controleurUtilisateur = new ControleurUtilisateurs(aidePourLAuthentification.serviceDAuthentification);
    }

    @Test
    public void peutRecupererUtilisateurCourant() throws ImpossibleDeSAuthentifier {
        aidePourLAuthentification.simulerAuthentification();

        Utilisateur utilisateur = controleurUtilisateur.recuperUtilisateurCourant();

        assertThat(utilisateur, is(AidePourLesUtilisateurs.UTILISATEUR));
    }

    @Test
    public void recupereUnUtilisateurCourantNullSiIlNestPasAuthentifier() throws IOException {

        Utilisateur utilisateur = controleurUtilisateur.recuperUtilisateurCourant();

        assertThat(utilisateur, nullValue());
    }

}
