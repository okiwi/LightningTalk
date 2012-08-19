package fr.atbdx.lightningtalk.domaine.doublures;

import fr.atbdx.lightningtalk.domaine.EntrepotUtilisateur;
import fr.atbdx.lightningtalk.domaine.Utilisateur;
import fr.atbdx.lightningtalk.domaine.doublures.google.FakeConnecteurGoogle;

import java.io.IOException;

public class FakeEntrepotUtilisateur implements EntrepotUtilisateur {

    public String codePasserPourAuthentifier;
    public String codeErreurPasserPourAuthentifier;
    public Utilisateur utilisateurCourantARetourner;

    @Override
    public String recupererLURLDuServiceDAuthentificationExterne() {
        return FakeConnecteurGoogle.URL_DU_SERVICE_D_AUTHENTIFICATION_EXTERNE;
    }

    @Override
    public void authentifier(String code, String codeErreur) throws IOException {
        this.codePasserPourAuthentifier = code;
        this.codeErreurPasserPourAuthentifier = codeErreur;

    }

    @Override
    public Utilisateur recupererUtilisateurCourant() {
        return utilisateurCourantARetourner;
    }

}
