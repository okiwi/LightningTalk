package fr.atbdx.lightningtalk.tests.domaine;

import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.Utilisateur;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesUtilisateurs;

public class UtilisateurTest {

    @Test
    public void peutCreerUnUtilisateur() {
        Utilisateur utilisateur = new Utilisateur(AidePourLesUtilisateurs.ID, AidePourLesUtilisateurs.NOM_AFFICHE, AidePourLesUtilisateurs.URL_IMAGE,
                AidePourLesUtilisateurs.URL_PROFILE);

        AidePourLesUtilisateurs.verifier(utilisateur);
    }

}
