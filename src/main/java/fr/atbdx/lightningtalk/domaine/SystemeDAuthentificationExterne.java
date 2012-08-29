package fr.atbdx.lightningtalk.domaine;

import java.io.IOException;

public interface SystemeDAuthentificationExterne {

    String recupererLURLDuServiceDAuthentificationExterne();

    Utilisateur recupererUtilisateurDepuisUnCodeDAuthentification(String codeDAuthentification) throws IOException;

}
