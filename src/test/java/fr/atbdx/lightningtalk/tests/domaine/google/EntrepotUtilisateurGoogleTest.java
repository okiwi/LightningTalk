package fr.atbdx.lightningtalk.tests.domaine.google;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.google.EntrepotUtilisateurGoogle;
import fr.atbdx.lightningtalk.domaine.google.PoigneePourStockerEnSessionLeCredentialGoogle;
import fr.atbdx.lightningtalk.doublures.domaine.google.AidePourLeCredentialGoogle;
import fr.atbdx.lightningtalk.doublures.domaine.google.FakeConnecteurGoogle;

public class EntrepotUtilisateurGoogleTest {

    @Test
    public void creerAvecUnClientIdNullRetourneUneException() {
        PoigneePourStockerEnSessionLeCredentialGoogle poigneePourStockerEnSessionLeCredentialGoogle = new PoigneePourStockerEnSessionLeCredentialGoogle();
        EntrepotUtilisateurGoogle entrepotUtilisateurGoogle = new EntrepotUtilisateurGoogle(poigneePourStockerEnSessionLeCredentialGoogle, new FakeConnecteurGoogle());

        String urlDuServiceDAuthentificationExterne = entrepotUtilisateurGoogle.recupererLURLDuServiceDAuthentificationExterne();

        assertThat(urlDuServiceDAuthentificationExterne, is(FakeConnecteurGoogle.URL_DU_SERVICE_D_AUTHENTIFICATION_EXTERNE));
    }

    @Test
    public void authentifier() throws IOException {
        PoigneePourStockerEnSessionLeCredentialGoogle poigneePourStockerEnSessionLeCredentialGoogle = new PoigneePourStockerEnSessionLeCredentialGoogle();
        FakeConnecteurGoogle connecteurGoogle = new FakeConnecteurGoogle();
        EntrepotUtilisateurGoogle entrepotUtilisateurGoogle = new EntrepotUtilisateurGoogle(poigneePourStockerEnSessionLeCredentialGoogle, connecteurGoogle);

        entrepotUtilisateurGoogle.authentifier("code", "codeErreur");

        assertThat(connecteurGoogle.codePassePourRecupereLeGoogleTokenResponse, is("code"));
        AidePourLeCredentialGoogle.verifier(poigneePourStockerEnSessionLeCredentialGoogle.recuperer());
    }
}
