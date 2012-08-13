package fr.atbdx.lightningtalk.domaine.google;

import java.util.Arrays;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;

import fr.atbdx.lightningtalk.domaine.EntrepotUtilisateur;

public class EntrepotUtilisateurGoogle implements EntrepotUtilisateur {

    private final String googleClientId;
    private final String googleRedirectURI;

    public EntrepotUtilisateurGoogle(String googleClientId, String googleRedirectURI) {
        this.googleClientId = googleClientId;
        this.googleRedirectURI = googleRedirectURI;
    }

    public String recupererLaPageDAutenthification() {
        return new GoogleAuthorizationCodeRequestUrl(googleClientId, googleRedirectURI, Arrays.asList("https://www.googleapis.com/auth/plus.me")).build();
    }

}
