package fr.atbdx.lightningtalk.domaine.google;

import java.io.IOException;

import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.services.plus.model.Person;

public interface ConnecteurGoogle {

    String recupererLURLDuServiceDAuthentificationExterne();

    GoogleTokenResponse recupererLeGoogleTokenResponse(String code) throws IOException;

    Person recupererPersonDepuisGoogle(InformationsDAuthentification informationsDAuthentification) throws IOException;

}
