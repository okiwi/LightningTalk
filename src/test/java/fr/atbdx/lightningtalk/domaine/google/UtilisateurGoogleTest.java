package fr.atbdx.lightningtalk.domaine.google;

import fr.atbdx.lightningtalk.domaine.Utilisateur;
import fr.atbdx.lightningtalk.domaine.doublures.AidePourLesUtilisateurs;
import fr.atbdx.lightningtalk.domaine.doublures.google.AidePourLAuthentification;
import org.junit.Test;

public class UtilisateurGoogleTest {

    @Test
    public void peutCreerunUtilisateur() {

        Utilisateur utilisateur = new UtilisateurGoogle(AidePourLAuthentification.PERSON);

        AidePourLesUtilisateurs.verifier(utilisateur);
    }

}
