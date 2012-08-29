package fr.atbdx.lightningtalk.tests.domaine.google;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.google.SystemeDAuthentificationExterneGoogle;

public class SystemeDauthentificationExterneGoogleTest {

    private static final String GOOGLE_CLIENT_SECRET = "googleClientSecret";
    private static final String GOOGLE_REDIRECT_URI = "googleRedirectURI";
    private static final String GOOGLE_CLIENT_ID = "googleClientId";

    @Test(expected = NullPointerException.class)
    public void creerAvecUnClientIdNullRenvoitUneException() {
        new SystemeDAuthentificationExterneGoogle(null, GOOGLE_REDIRECT_URI, GOOGLE_CLIENT_SECRET);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creerAvecUnClientIdVideRenvoitUneException() {
        new SystemeDAuthentificationExterneGoogle(StringUtils.EMPTY, GOOGLE_REDIRECT_URI, GOOGLE_CLIENT_SECRET);
    }

    @Test(expected = NullPointerException.class)
    public void creerAvecUneredirectUriNullRenvoitUneException() {
        new SystemeDAuthentificationExterneGoogle(GOOGLE_CLIENT_ID, null, GOOGLE_CLIENT_SECRET);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creerAvecUneredirectUriRenvoitUneException() {
        new SystemeDAuthentificationExterneGoogle(GOOGLE_CLIENT_ID, StringUtils.EMPTY, GOOGLE_CLIENT_SECRET);
    }

    @Test(expected = NullPointerException.class)
    public void creerAvecUnGoogleClientSecretNullRenvoitUneException() {
        new SystemeDAuthentificationExterneGoogle(GOOGLE_CLIENT_ID, GOOGLE_REDIRECT_URI, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creerAvecUnGoogleClientSecretRenvoitUneException() {
        new SystemeDAuthentificationExterneGoogle(GOOGLE_CLIENT_ID, GOOGLE_REDIRECT_URI, StringUtils.EMPTY);
    }

    @Test
    public void peutRecupererLURLDuServiceDAuthentificationExterne() {
        SystemeDAuthentificationExterneGoogle systemeDAuthentificationExterneGoogle = new SystemeDAuthentificationExterneGoogle(GOOGLE_CLIENT_ID, GOOGLE_REDIRECT_URI,
                GOOGLE_CLIENT_SECRET);

        String urlDuServiceDAuthentificationExterne = systemeDAuthentificationExterneGoogle.recupererLURLDuServiceDAuthentificationExterne();

        assertThat(
                urlDuServiceDAuthentificationExterne,
                is("https://accounts.google.com/o/oauth2/auth?client_id=googleClientId&redirect_uri=googleRedirectURI&response_type=code&scope=https://www.googleapis.com/auth/plus.me"));

    }
}
