package fr.atbdx.lightningtalk.domaine.google;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class EntrepotUtilisateurGoogleTest {
    
    private static final String HTTP_GOOGLE_REDIRECT_URI = "http://googleRedirectURI/";
    private static final String GOOGLE_CLIENT_ID = "googleClientId";
    
    @Test(expected = NullPointerException.class)
    public void creerAvecUnClientIdNullRetourneUneException() {
        new EntrepotUtilisateurGoogle(null, HTTP_GOOGLE_REDIRECT_URI);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void creerAvecUnClientIdVideRetourneUneException() {
        new EntrepotUtilisateurGoogle(StringUtils.EMPTY, HTTP_GOOGLE_REDIRECT_URI);
    }
    
    @Test(expected = NullPointerException.class)
    public void creerAvecUneRedirectURINullRetourneUneException() {
        new EntrepotUtilisateurGoogle(GOOGLE_CLIENT_ID, null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void creerAvecUneRedirectURIVideRetourneUneException() {
        new EntrepotUtilisateurGoogle(GOOGLE_CLIENT_ID, StringUtils.EMPTY);
    }
    
    @Test
    public void recupererLURLDuServiceDAuthentificationExterne() {
        EntrepotUtilisateurGoogle entrepotUtilisateurGoogle = new EntrepotUtilisateurGoogle(GOOGLE_CLIENT_ID, HTTP_GOOGLE_REDIRECT_URI);
        
        String pageDAutenthification = entrepotUtilisateurGoogle.recupererLURLDuServiceDAuthentificationExterne();
        
        assertThat(pageDAutenthification,
                        is("https://accounts.google.com/o/oauth2/auth?client_id=googleClientId&redirect_uri=http://googleRedirectURI/&response_type=code&scope=https://www.googleapis.com/auth/plus.me"));
        
    }
    
}
