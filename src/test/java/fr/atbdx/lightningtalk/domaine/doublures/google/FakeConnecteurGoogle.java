package fr.atbdx.lightningtalk.domaine.doublures.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.services.plus.model.Person;
import fr.atbdx.lightningtalk.domaine.google.ConnecteurGoogle;
import fr.atbdx.lightningtalk.domaine.google.InformationsDAuthentification;

import java.io.IOException;

public class FakeConnecteurGoogle implements ConnecteurGoogle {

    public static final String URL_DU_SERVICE_D_AUTHENTIFICATION_EXTERNE = "urlDuServiceDAuthentificationExterne";

    public static final GoogleTokenResponse GOOGLE_TOKEN_RESPONSE;

    static {
        GOOGLE_TOKEN_RESPONSE = new GoogleTokenResponse();
        GOOGLE_TOKEN_RESPONSE.setAccessToken(AidePourLAuthentification.ACCESS_TOKEN);
        GOOGLE_TOKEN_RESPONSE.setRefreshToken(AidePourLAuthentification.REFRESH_TOKEN);
    }

    public String codePassePourRecupereLeGoogleTokenResponse;

    public InformationsDAuthentification informationsDAuthentification;

    @Override
    public String recupererLURLDuServiceDAuthentificationExterne() {
        return URL_DU_SERVICE_D_AUTHENTIFICATION_EXTERNE;
    }

    @Override
    public GoogleTokenResponse recupererLeGoogleTokenResponse(String code) throws IOException {
        this.codePassePourRecupereLeGoogleTokenResponse = code;
        return GOOGLE_TOKEN_RESPONSE;
    }

    @Override
    public Person recupererPersonDepuisGoogle(InformationsDAuthentification informationsDAuthentification) throws IOException {
        this.informationsDAuthentification = informationsDAuthentification;
        return AidePourLAuthentification.PERSON;
    }

}
