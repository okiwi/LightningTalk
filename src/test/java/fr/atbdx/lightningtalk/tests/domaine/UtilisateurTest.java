package fr.atbdx.lightningtalk.tests.domaine;

import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.Utilisateur;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesUtilisateurs;

public class UtilisateurTest {

    @Test
    public void peutCreerunUtilisateur() {

        Utilisateur utilisateur = new Utilisateur(AidePourLesUtilisateurs.ID,AidePourLesUtilisateurs.NOM_AFFICHE);

        AidePourLesUtilisateurs.verifier(utilisateur);
    }
}
