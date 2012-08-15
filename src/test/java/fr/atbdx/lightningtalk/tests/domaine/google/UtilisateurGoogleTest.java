package fr.atbdx.lightningtalk.tests.domaine.google;

import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.Utilisateur;
import fr.atbdx.lightningtalk.domaine.google.UtilisateurGoogle;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesUtilisateurs;
import fr.atbdx.lightningtalk.doublures.domaine.google.AidePourLAuthentification;

public class UtilisateurGoogleTest {

    @Test
    public void peutCreerunUtilisateur() {

        Utilisateur utilisateur = new UtilisateurGoogle(AidePourLAuthentification.PERSON);

        AidePourLesUtilisateurs.verifier(utilisateur);
    }

}
