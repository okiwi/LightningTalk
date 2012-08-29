package fr.atbdx.lightningtalk.doublures.domaine;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import fr.atbdx.lightningtalk.domaine.ServiceDAuthentification;

public class AidePourLAuthentification {
    public static final String CODE_AUTHENTIFICATION = "codeAuthentification";

    public final FakeSystemeDAuthentificationExterne systemeDAuthentificationExterne;
    public final FakeEntrepotUtilisateur entrepotUtilisateur;
    public final ServiceDAuthentification serviceDAuthentification;

    public AidePourLAuthentification() {
        systemeDAuthentificationExterne = new FakeSystemeDAuthentificationExterne();
        entrepotUtilisateur = new FakeEntrepotUtilisateur();
        serviceDAuthentification = new ServiceDAuthentification(systemeDAuthentificationExterne, entrepotUtilisateur);
    }

    public AidePourLAuthentification simulerAuthentification() throws IOException {
        serviceDAuthentification.authentifier(CODE_AUTHENTIFICATION);
        return this;

    }

    public void verifierLaConnexionAuSystemeDAuthentificationExterneEtLaCreationDeLUtilisateur() {
        assertThat(systemeDAuthentificationExterne.codeDAuthentificationUtilise, is(AidePourLAuthentification.CODE_AUTHENTIFICATION));
        assertThat(entrepotUtilisateur.utilisateurCreer, is(AidePourLesUtilisateurs.UTILISATEUR));
        assertThat(serviceDAuthentification.recupererUtilisateurCourant(), is(AidePourLesUtilisateurs.UTILISATEUR));
    }

    public static AidePourLAuthentification getInstance() {
        return new AidePourLAuthentification();
    }

}
