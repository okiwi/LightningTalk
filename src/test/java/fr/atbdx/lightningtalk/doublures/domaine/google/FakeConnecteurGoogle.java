package fr.atbdx.lightningtalk.doublures.domaine.google;

import java.io.IOException;

import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;

import fr.atbdx.lightningtalk.domaine.google.ConnecteurGoogle;

public class FakeConnecteurGoogle implements ConnecteurGoogle {

    public static final String REFRESH_TOKEN = "refreshToken";

    public static final String ACCESS_TOKEN = "accessToken";

    public static final String URL_DU_SERVICE_D_AUTHENTIFICATION_EXTERNE = "urlDuServiceDAuthentificationExterne";

    public static final GoogleTokenResponse GOOGLE_TOKEN_RESPONSE;

    static {
        GOOGLE_TOKEN_RESPONSE = new GoogleTokenResponse();
        GOOGLE_TOKEN_RESPONSE.setAccessToken(ACCESS_TOKEN);
        GOOGLE_TOKEN_RESPONSE.setRefreshToken(REFRESH_TOKEN);
    }

    public String codePassePourRecupereLeGoogleTokenResponse;

    @Override
    public String recupererLURLDuServiceDAuthentificationExterne() {
        return URL_DU_SERVICE_D_AUTHENTIFICATION_EXTERNE;
    }

    @Override
    public GoogleTokenResponse recupererLeGoogleTokenResponse(String code) throws IOException {
        this.codePassePourRecupereLeGoogleTokenResponse = code;
        return GOOGLE_TOKEN_RESPONSE;
    }

}
