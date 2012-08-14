package fr.atbdx.lightningtalk.tests.domaine.google;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.google.ConnecteurGoogleConcret;

public class ConnecteurGoogleConcretTest {
    
    private static final String HTTP_GOOGLE_REDIRECT_URI = "http://googleRedirectURI/";
    private static final String GOOGLE_CLIENT_ID = "googleClientId";
    private static final String CLIENT_SECRET = "clientSecret";
    
    @Test(expected = NullPointerException.class)
    public void creerAvecUnClientIdNullRetourneUneException() {
        new ConnecteurGoogleConcret(null, HTTP_GOOGLE_REDIRECT_URI, CLIENT_SECRET);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void creerAvecUnClientIdVideRetourneUneException() {
        new ConnecteurGoogleConcret(StringUtils.EMPTY, HTTP_GOOGLE_REDIRECT_URI, CLIENT_SECRET);
    }
    
    @Test(expected = NullPointerException.class)
    public void creerAvecUneRedirectURINullRetourneUneException() {
        new ConnecteurGoogleConcret(GOOGLE_CLIENT_ID, null, CLIENT_SECRET);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void creerAvecUneRedirectURIVideRetourneUneException() {
        new ConnecteurGoogleConcret(GOOGLE_CLIENT_ID, StringUtils.EMPTY, CLIENT_SECRET);
    }
    
    @Test(expected = NullPointerException.class)
    public void creerAvecUnClientSecretNullRetourneUneException() {
        new ConnecteurGoogleConcret(GOOGLE_CLIENT_ID, HTTP_GOOGLE_REDIRECT_URI, null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void creerAvecUnClientSecretVideRetourneUneException() {
        new ConnecteurGoogleConcret(GOOGLE_CLIENT_ID, HTTP_GOOGLE_REDIRECT_URI, StringUtils.EMPTY);
    }
    
    @Test
    public void recupererLURLDuServiceDAuthentificationExterne() {
        ConnecteurGoogleConcret connecteurGoogleConcret = new ConnecteurGoogleConcret(GOOGLE_CLIENT_ID, HTTP_GOOGLE_REDIRECT_URI, HTTP_GOOGLE_REDIRECT_URI);
        
        String pageDAutenthification = connecteurGoogleConcret.recupererLURLDuServiceDAuthentificationExterne();
        
        assertThat(pageDAutenthification,
                        is("https://accounts.google.com/o/oauth2/auth?client_id=googleClientId&redirect_uri=http://googleRedirectURI/&response_type=code&scope=https://www.googleapis.com/auth/plus.me"));
    }
    
}
