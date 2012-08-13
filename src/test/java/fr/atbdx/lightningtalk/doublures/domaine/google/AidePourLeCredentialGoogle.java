package fr.atbdx.lightningtalk.doublures.domaine.google;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import fr.atbdx.lightningtalk.domaine.google.CredentialGoogle;

public class AidePourLeCredentialGoogle {

    public static void verifier(CredentialGoogle credentialGoogle) {
        assertThat(credentialGoogle, notNullValue());
        assertThat(credentialGoogle.refreshToken, is(FakeConnecteurGoogle.REFRESH_TOKEN));
        assertThat(credentialGoogle.accessToken, is(FakeConnecteurGoogle.ACCESS_TOKEN));

    }

}
