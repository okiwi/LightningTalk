package fr.atbdx.lightningtalk.domaine.google;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;

@Component
public class ConnecteurGoogleConcret implements ConnecteurGoogle {

    private final String googleClientId;
    private final String googleRedirectURI;
    private final String clientSecret;

    @Autowired
    public ConnecteurGoogleConcret(@Value("${google.clientId}") String googleClientId, @Value("${google.redirectURI}") String googleRedirectURI,
            @Value("${google.clienSecret}") String clientSecret) {
        Validate.notBlank(googleClientId, "googleClientId");
        Validate.notBlank(googleRedirectURI, "googleRedirectURI");
        Validate.notBlank(clientSecret, "clientSecret");
        this.googleClientId = googleClientId;
        this.googleRedirectURI = googleRedirectURI;
        this.clientSecret = clientSecret;
    }

    @Override
    public String recupererLURLDuServiceDAuthentificationExterne() {
        return new GoogleAuthorizationCodeRequestUrl(googleClientId, googleRedirectURI, Arrays.asList("https://www.googleapis.com/auth/plus.me")).build();
    }

    @Override
    public GoogleTokenResponse recupererLeGoogleTokenResponse(String code) throws IOException {
        GoogleAuthorizationCodeTokenRequest requeteDAuthorisationGoogle = new GoogleAuthorizationCodeTokenRequest(new NetHttpTransport(), new JacksonFactory(), googleClientId,
                clientSecret, code, googleRedirectURI);
        return requeteDAuthorisationGoogle.execute();
    }

}
