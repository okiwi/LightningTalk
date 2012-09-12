package fr.atbdx.lightningtalk.domaine.google;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;

import fr.atbdx.lightningtalk.domaine.SystemeDAuthentificationExterne;
import fr.atbdx.lightningtalk.domaine.Utilisateur;

@Component
public class SystemeDAuthentificationExterneGoogle implements SystemeDAuthentificationExterne {
    
    private final String googleClientId;
    private final String googleRedirectURI;
    private final String clientSecret;
    
    @Autowired
    public SystemeDAuthentificationExterneGoogle(@Value("${google.clientId}") String googleClientId, @Value("${google.redirectURI}") String googleRedirectURI,
        @Value("${google.clienSecret}") String googleClientSecret) {
        Validate.notBlank(googleClientId, "googleClientId");
        Validate.notBlank(googleRedirectURI, "googleRedirectURI");
        Validate.notBlank(googleClientSecret, "clientSecret");
        this.googleClientId = googleClientId;
        this.googleRedirectURI = googleRedirectURI;
        clientSecret = googleClientSecret;
    }
    
    @Override
    public String recupererLURLDuServiceDAuthentificationExterne() {
        return new GoogleAuthorizationCodeRequestUrl(googleClientId, googleRedirectURI, Arrays.asList("https://www.googleapis.com/auth/plus.me")).build();
    }
    
    @Override
    public Utilisateur recupererUtilisateurDepuisUnCodeDAuthentification(String codeDAuthentification) throws IOException {
        // NetHttpTransport netHttpTransport = new NetHttpTransport();
        // JacksonFactory jaksonFactory = new JacksonFactory();
        // GoogleAuthorizationCodeTokenRequest requeteDAuthorisationGoogle = new GoogleAuthorizationCodeTokenRequest(netHttpTransport,
        // jaksonFactory, googleClientId, clientSecret,
        // codeDAuthentification, googleRedirectURI);
        // GoogleTokenResponse googleTokenResponse = requeteDAuthorisationGoogle.execute();
        // GoogleCredential googleCredential = new
        // GoogleCredential.Builder().setJsonFactory(jaksonFactory).setTransport(netHttpTransport).build();
        // googleCredential.setFromTokenResponse(googleTokenResponse);
        // Plus googlePlus = new Plus.Builder(netHttpTransport, jaksonFactory, googleCredential).build();
        
        // return MappingPersonGoogleEnUtilisateur.mapper(googlePlus.people().get("me").execute());
        return new Utilisateur("id", "Roux Jérôme", "https://lh5.googleusercontent.com/-0Lro86zQRTo/AAAAAAAAAAI/AAAAAAAAAAA/KpZLjmp_NAA/photo.jpg",
                        "http://agiletourbordeaux.okiwi.org");
    }
}
