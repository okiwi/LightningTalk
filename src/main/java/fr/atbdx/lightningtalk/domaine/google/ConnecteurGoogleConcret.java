package fr.atbdx.lightningtalk.domaine.google;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.Person;

@Component
public class ConnecteurGoogleConcret implements ConnecteurGoogle {

    private final String googleClientId;
    private final String googleRedirectURI;
    private final String clientSecret;

    @Autowired
    public ConnecteurGoogleConcret(@Value("${google.clientId}") String googleClientId, @Value("${google.redirectURI}") String googleRedirectURI,
            @Value("${google.clienSecret}") String googleClientSecret) {
        Validate.notBlank(googleClientId, "googleClientId");
        Validate.notBlank(googleRedirectURI, "googleRedirectURI");
        Validate.notBlank(googleClientSecret, "clientSecret");
        this.googleClientId = googleClientId;
        this.googleRedirectURI = googleRedirectURI;
        this.clientSecret = googleClientSecret;
    }

    @Override
    public String recupererLURLDuServiceDAuthentificationExterne() {
        return new GoogleAuthorizationCodeRequestUrl(googleClientId, googleRedirectURI, Arrays.asList("https://www.googleapis.com/auth/plus.me")).build();
    }

    @Override
    public GoogleTokenResponse recupererLeGoogleTokenResponse(String code) throws IOException {
        GoogleAuthorizationCodeTokenRequest requeteDAuthorisationGoogle = new GoogleAuthorizationCodeTokenRequest(getNetHttpTransport(), getJaksonFactory(), googleClientId,
                clientSecret, code, googleRedirectURI);
        return requeteDAuthorisationGoogle.execute();
    }

    @Override
    public Person recupererPersonDepuisGoogle(InformationsDAuthentification informationsDAuthentification) throws IOException {
        JacksonFactory jaksonFactory = getJaksonFactory();
        NetHttpTransport netHttpTransport = getNetHttpTransport();
        GoogleCredential googleCredential = new GoogleCredential.Builder().setJsonFactory(jaksonFactory).setTransport(netHttpTransport)
                .setClientSecrets(googleClientId, clientSecret).build();
        googleCredential.setAccessToken(informationsDAuthentification.accessToken);
        googleCredential.setRefreshToken(informationsDAuthentification.refreshToken);
        Plus googlePlus = new Plus.Builder(netHttpTransport, jaksonFactory, googleCredential).build();
        return googlePlus.people().get("me").execute();
//        Person person = new Person();
//        person.setId("id");
//        person.setDisplayName("displayName");
//        return person;
    }

    private JacksonFactory getJaksonFactory() {
        return new JacksonFactory();
    }

    private NetHttpTransport getNetHttpTransport() {
        return new NetHttpTransport();
    }

}
