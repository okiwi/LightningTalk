package fr.atbdx.lightningtalk.tests.domaine.google;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.google.CredentialGoogle;
import fr.atbdx.lightningtalk.doublures.domaine.google.AidePourLeCredentialGoogle;
import fr.atbdx.lightningtalk.doublures.domaine.google.FakeConnecteurGoogle;

public class CredentialGoogleTest {

    @Test
    public void creation() {

        CredentialGoogle credential = new CredentialGoogle(FakeConnecteurGoogle.GOOGLE_TOKEN_RESPONSE);

        AidePourLeCredentialGoogle.verifier(credential);
    }

    @Test(expected = NullPointerException.class)
    public void creationAvecCredentialNull() {

        CredentialGoogle credential = new CredentialGoogle(null);

        AidePourLeCredentialGoogle.verifier(credential);
    }

}
