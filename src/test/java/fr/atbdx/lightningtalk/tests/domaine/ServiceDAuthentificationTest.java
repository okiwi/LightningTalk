package fr.atbdx.lightningtalk.tests.domaine;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.ServiceDAuthentification;
import fr.atbdx.lightningtalk.domaine.Utilisateur;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLAuthentification;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesUtilisateurs;

public class ServiceDAuthentificationTest {

    private AidePourLAuthentification aidePourLAuthentification;
    private ServiceDAuthentification serviceDAuthentification;

    @Before
    public void avantLesTests() {
        aidePourLAuthentification = AidePourLAuthentification.getInstance();
        serviceDAuthentification = aidePourLAuthentification.serviceDAuthentification;
    }

    @Test
    public void peutAuthentifierUnUtilisateur() throws IOException {

        serviceDAuthentification.authentifier(AidePourLAuthentification.CODE_AUTHENTIFICATION);

        aidePourLAuthentification.verifierLaConnexionAuSystemeDAuthentificationExterneEtLaCreationDeLUtilisateur();
    }

    @Test
    public void peutMettreAJourUnUtilisateurConnecterPrecedement() throws IOException {
        aidePourLAuthentification.simulerAuthentification();
        Utilisateur unAutreUtilisateur = AidePourLesUtilisateurs.creerAvecSuffixe("unAutreUtilisateur");
        aidePourLAuthentification.systemeDAuthentificationExterne.utilisateurARetourner = unAutreUtilisateur;

        serviceDAuthentification.authentifier(AidePourLAuthentification.CODE_AUTHENTIFICATION);

        assertThat(aidePourLAuthentification.entrepotUtilisateur.utilisateurMisAJour, is(unAutreUtilisateur));
        assertThat(aidePourLAuthentification.entrepotUtilisateur.idUtilisateurRecuperer, is(unAutreUtilisateur.getId()));
    }

    @Test
    public void peuxSeDeconnecter() throws IOException {
        aidePourLAuthentification.simulerAuthentification();

        serviceDAuthentification.deconnexion();

        assertThat(serviceDAuthentification.recupererUtilisateurCourant(), nullValue());

    }
}
