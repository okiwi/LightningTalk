package fr.atbdx.lightningtalk.doublures.domaine;

import java.io.IOException;

import fr.atbdx.lightningtalk.domaine.SystemeDAuthentificationExterne;
import fr.atbdx.lightningtalk.domaine.Utilisateur;

public class FakeSystemeDAuthentificationExterne implements SystemeDAuthentificationExterne {

    public static final String URL_DU_SYSTEME_D_AUTHENTIFICATION_EXTERNE = "urlDuSystemeDAuthentificationExterne";
    public String codeDAuthentificationUtilise;
    public Utilisateur utilisateurARetourner = AidePourLesUtilisateurs.UTILISATEUR;

    @Override
    public String recupererLURLDuServiceDAuthentificationExterne() {
        return URL_DU_SYSTEME_D_AUTHENTIFICATION_EXTERNE;
    }

    @Override
    public Utilisateur recupererUtilisateurDepuisUnCodeDAuthentification(String codeDAuthentification) throws IOException {
        this.codeDAuthentificationUtilise = codeDAuthentification;
        return utilisateurARetourner;
    }

}
