package fr.atbdx.lightningtalk.tests.domaine.google;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.Utilisateur;
import fr.atbdx.lightningtalk.domaine.google.EntrepotUtilisateurGoogle;
import fr.atbdx.lightningtalk.domaine.google.PoigneePourStockerEnSessionLesInformationsDAuthentification;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesParticipants;
import fr.atbdx.lightningtalk.doublures.domaine.google.AidePourLAuthentification;
import fr.atbdx.lightningtalk.doublures.domaine.google.FakeConnecteurGoogle;

public class EntrepotUtilisateurGoogleTest {

    private PoigneePourStockerEnSessionLesInformationsDAuthentification poigneePourStockerEnSessionLeCredentialGoogle;
    private EntrepotUtilisateurGoogle entrepotUtilisateurGoogle;
    private FakeConnecteurGoogle fakeConnecteurGoogle;

    @Before
    public void avantLesTests() {
        poigneePourStockerEnSessionLeCredentialGoogle = new PoigneePourStockerEnSessionLesInformationsDAuthentification();
        fakeConnecteurGoogle = new FakeConnecteurGoogle();
        entrepotUtilisateurGoogle = new EntrepotUtilisateurGoogle(poigneePourStockerEnSessionLeCredentialGoogle, fakeConnecteurGoogle);
    }

    @Test
    public void creerAvecUnClientIdNullRetourneUneException() {

        String urlDuServiceDAuthentificationExterne = entrepotUtilisateurGoogle.recupererLURLDuServiceDAuthentificationExterne();

        assertThat(urlDuServiceDAuthentificationExterne, is(FakeConnecteurGoogle.URL_DU_SERVICE_D_AUTHENTIFICATION_EXTERNE));
    }

    @Test
    public void authentifier() throws IOException {

        entrepotUtilisateurGoogle.authentifier("code", "codeErreur");

        assertThat(fakeConnecteurGoogle.codePassePourRecupereLeGoogleTokenResponse, is("code"));
        AidePourLAuthentification.verifier(poigneePourStockerEnSessionLeCredentialGoogle.recuperer());
    }

    @Test
    public void recupererUtilisateurCourantNullSiNonAuthentifie() throws IOException {
        Utilisateur utilisateur = entrepotUtilisateurGoogle.recupererUtilisateurCourant();

        assertThat(utilisateur, nullValue());
    }

    @Test
    public void recupererUtilisateurCourantDepuisGoogleSiAuthentifie() throws IOException {
        entrepotUtilisateurGoogle.authentifier(null, null);

        Utilisateur utilisateur = entrepotUtilisateurGoogle.recupererUtilisateurCourant();

        AidePourLAuthentification.verifier(fakeConnecteurGoogle.informationsDAuthentification);
        assertThat(utilisateur, notNullValue());
    }

    @Test
    public void recupererUtilisateurAvecChampsRemplisSiAuthentifie() throws IOException {
        entrepotUtilisateurGoogle.authentifier(null, null);

        Utilisateur utilisateur = entrepotUtilisateurGoogle.recupererUtilisateurCourant();

        AidePourLesParticipants.verifier(utilisateur);
    }
}
