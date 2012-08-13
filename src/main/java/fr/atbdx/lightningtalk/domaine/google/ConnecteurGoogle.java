package fr.atbdx.lightningtalk.domaine.google;

import java.io.IOException;

import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;

public interface ConnecteurGoogle {

    String recupererLURLDuServiceDAuthentificationExterne();

    GoogleTokenResponse recupererLeGoogleTokenResponse(String code) throws IOException;

}
