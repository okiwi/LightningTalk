package fr.atbdx.lightningtalk.tests.domaine;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.Utilisateur;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesUtilisateurs;

public class UtilisateurTest {

    @Test
    public void peutCreerUnUtilisateur() {
        Utilisateur utilisateur = new Utilisateur(AidePourLesUtilisateurs.ID, AidePourLesUtilisateurs.NOM_AFFICHE, AidePourLesUtilisateurs.URL_IMAGE,
                AidePourLesUtilisateurs.URL_PROFIL);

        AidePourLesUtilisateurs.verifier(utilisateur);
    }

    @Test
    public void deuxUtilisateursAvecLeMemeIdSontEgaux() {
        Utilisateur utilisateur = new Utilisateur(AidePourLesUtilisateurs.ID, null, null, null);
        Utilisateur utilisateur2 = new Utilisateur(AidePourLesUtilisateurs.ID, null, null, null);

        assertThat(utilisateur, is(utilisateur2));
    }

    @Test
    public void deuxUtilisateursAvecDesIdDifferentsNeSontPasEgaux() {
        Utilisateur utilisateur = new Utilisateur(AidePourLesUtilisateurs.ID, null, null, null);
        Utilisateur utilisateur2 = new Utilisateur("un autre id", null, null, null);

        assertThat(utilisateur, not(utilisateur2));
    }

    @Test
    public void deuxUtilisateursAvecLeMemeIdOntLeMemeHashCode() {
        Utilisateur utilisateur = new Utilisateur(AidePourLesUtilisateurs.ID, null, null, null);
        Utilisateur utilisateur2 = new Utilisateur(AidePourLesUtilisateurs.ID, null, null, null);

        assertThat(utilisateur.hashCode(), is(utilisateur2.hashCode()));
    }

    @Test
    public void deuxUtilisateursAvecDesIdDifferentsNOntPasLeMemeHashCode() {
        Utilisateur utilisateur = new Utilisateur(AidePourLesUtilisateurs.ID, null, null, null);
        Utilisateur utilisateur2 = new Utilisateur("un autre id", null, null, null);

        assertThat(utilisateur.hashCode(), not(utilisateur2.hashCode()));
    }

}
