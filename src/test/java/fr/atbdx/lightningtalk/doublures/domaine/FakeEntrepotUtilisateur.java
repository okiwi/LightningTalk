package fr.atbdx.lightningtalk.doublures.domaine;

import java.io.IOException;

import fr.atbdx.lightningtalk.domaine.EntrepotUtilisateur;
import fr.atbdx.lightningtalk.domaine.Utilisateur;
import fr.atbdx.lightningtalk.doublures.domaine.google.FakeConnecteurGoogle;

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
