package fr.atbdx.lightningtalk.tests.domaine.google;

import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.Participant;
import fr.atbdx.lightningtalk.domaine.google.UtilisateurGoogle;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesParticipants;
import fr.atbdx.lightningtalk.doublures.domaine.google.AidePourLAuthentification;

public class UtilisateurGoogleTest {

    @Test
    public void peutCreerunUtilisateur() {

        Participant utilisateur = new UtilisateurGoogle(AidePourLAuthentification.PERSON);

        AidePourLesParticipants.verifier(utilisateur);
    }

}
