package fr.atbdx.lightningtalk.domaine.google;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class EntrepotUtilisateurGoogleTest {

    @Test
    public void recupererLaPageDAutenthification() {
        EntrepotUtilisateurGoogle entrepotUtilisateurGoogle = new EntrepotUtilisateurGoogle("googleClientId", "http://googleRedirectURI/");

        String pageDAutenthification = entrepotUtilisateurGoogle.recupererLaPageDAutenthification();

        assertThat(
                pageDAutenthification,
                is("https://accounts.google.com/o/oauth2/auth?client_id=googleClientId&redirect_uri=http://googleRedirectURI/&response_type=code&scope=https://www.googleapis.com/auth/plus.me"));

    }

}
