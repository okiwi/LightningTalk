package fr.atbdx.lightningtalk.web;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.Person;

import fr.atbdx.lightningtalk.domaine.EntrepotUtilisateur;

@Controller
@RequestMapping("/authentification")
public class ControlleurAuthentification {
    
    private static final String CLIENT_SECRET = "hVorhxkqTrUt8Qj4Gkctl4yi";
    private static final String URL_DE_REDIRECTION = "http://lightningtalk.herokuapp.com/authentification/google/retour";
    private static final String CLIENT_ID = "1073813811256.apps.googleusercontent.com";
    private final EntrepotUtilisateur entrepotUtilisateur;
    
    @Autowired
    public ControlleurAuthentification(EntrepotUtilisateur entrepotUtilisateur) {
        this.entrepotUtilisateur = entrepotUtilisateur;
    }
    
    @RequestMapping(value = "/externe", method = RequestMethod.GET)
    public String demanderAuthentificationExterne() {
        return "redirect:" + entrepotUtilisateur.recupererLURLDuServiceDAuthentificationExterne();
    }
    
    @RequestMapping(value = "/externe/retour", method = RequestMethod.GET)
    public @ResponseBody
    String resultatAuthentificationGoogle(@RequestParam(required = false, value = "error") String codeErreur, @RequestParam(required = false, value = "code") String code)
        throws IOException {
        if (StringUtils.isNotEmpty(codeErreur)) {
            return codeErreur;
        } else {
            GoogleAuthorizationCodeTokenRequest requeteDAuthorisationGoogle = new GoogleAuthorizationCodeTokenRequest(new NetHttpTransport(), new JacksonFactory(), CLIENT_ID,
                            CLIENT_SECRET, code, URL_DE_REDIRECTION);
            String displayName = "non trouvé";
            try {
                GoogleTokenResponse reponse = requeteDAuthorisationGoogle.execute();
                GoogleCredential googleCredential = new GoogleCredential().setFromTokenResponse(reponse);
                Plus googlePlus = new Plus.Builder(new NetHttpTransport(), new JacksonFactory(), googleCredential).build();
                Person profile = googlePlus.people().get("me").execute();
                displayName = profile.getDisplayName();
                
            } catch (TokenResponseException e) {
                e.printStackTrace();
            }
            
            return displayName;
        }
    }
}
