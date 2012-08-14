package fr.atbdx.lightningtalk.tests.domaine.google;

import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.Utilisateur;
import fr.atbdx.lightningtalk.doublures.domaine.google.AidePourLAuthentification;

public class UtilisateurTest {

    @Test
    public void creation() {

        Utilisateur utilisateur = new Utilisateur(AidePourLAuthentification.PERSON);

        AidePourLAuthentification.verifier(utilisateur);
    }
}
