package fr.atbdx.lightningtalk.domaine;

import fr.atbdx.lightningtalk.domaine.doublures.AidePourLesUtilisateurs;
import org.junit.Test;

public class UtilisateurTest {

    @Test
    public void peutCreerunUtilisateur() {

        Utilisateur utilisateur = new Utilisateur(AidePourLesUtilisateurs.ID, AidePourLesUtilisateurs.NOM_AFFICHE);

        AidePourLesUtilisateurs.verifier(utilisateur);
    }
}
