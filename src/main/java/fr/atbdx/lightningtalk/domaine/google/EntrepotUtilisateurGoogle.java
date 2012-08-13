package fr.atbdx.lightningtalk.domaine.google;

import java.util.Arrays;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;

import fr.atbdx.lightningtalk.domaine.EntrepotUtilisateur;

@Repository
public class EntrepotUtilisateurGoogle implements EntrepotUtilisateur {
    
    private final String googleClientId;
    private final String googleRedirectURI;
    
    @Autowired
    public EntrepotUtilisateurGoogle(@Value("${google.clientId}") String googleClientId, @Value("${google.redirectURI}") String googleRedirectURI) {
        Validate.notBlank(googleClientId, "googleClientId");
        Validate.notBlank(googleRedirectURI, "googleRedirectURI");
        this.googleClientId = googleClientId;
        this.googleRedirectURI = googleRedirectURI;
    }
    
    @Override
    public String recupererLURLDuServiceDAuthentificationExterne() {
        return new GoogleAuthorizationCodeRequestUrl(googleClientId, googleRedirectURI, Arrays.asList("https://www.googleapis.com/auth/plus.me")).build();
    }
    
}
