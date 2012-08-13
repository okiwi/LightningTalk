package fr.atbdx.lightningtalk.tests.domaine.google;

import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.google.PoigneePourStockerEnSessionLeCredentialGoogle;
import fr.atbdx.lightningtalk.doublures.domaine.google.AidePourLeCredentialGoogle;
import fr.atbdx.lightningtalk.doublures.domaine.google.FakeConnecteurGoogle;

public class PoigneePourStockerEnSessionLeCredentialGoogleTest {

    @Test
    public void creer() {
        PoigneePourStockerEnSessionLeCredentialGoogle poigneePourStockerEnSessionLeCredentialGoogle = new PoigneePourStockerEnSessionLeCredentialGoogle();

        poigneePourStockerEnSessionLeCredentialGoogle.creer(FakeConnecteurGoogle.GOOGLE_TOKEN_RESPONSE);

        AidePourLeCredentialGoogle.verifier(poigneePourStockerEnSessionLeCredentialGoogle.recuperer());
    }
}
