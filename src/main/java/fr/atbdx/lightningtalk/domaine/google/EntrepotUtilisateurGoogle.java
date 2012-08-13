package fr.atbdx.lightningtalk.domaine.google;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.atbdx.lightningtalk.domaine.EntrepotUtilisateur;

@Repository
public class EntrepotUtilisateurGoogle implements EntrepotUtilisateur {

    private final ConnecteurGoogle connecteurGoogle;
    private final PoigneePourStockerEnSessionLeCredentialGoogle poigneePourStockerEnSessionLeCredentialGoogle;

    @Autowired
    public EntrepotUtilisateurGoogle(PoigneePourStockerEnSessionLeCredentialGoogle poigneePourStockerEnSessionLeCredentialGoogle, ConnecteurGoogle connecteurGoogle) {
        this.connecteurGoogle = connecteurGoogle;
        this.poigneePourStockerEnSessionLeCredentialGoogle = poigneePourStockerEnSessionLeCredentialGoogle;
    }

    @Override
    public String recupererLURLDuServiceDAuthentificationExterne() {
        return connecteurGoogle.recupererLURLDuServiceDAuthentificationExterne();
    }

    @Override
    public void authentifier(String code, String codeErreur) throws IOException {
        poigneePourStockerEnSessionLeCredentialGoogle.creer(connecteurGoogle.recupererLeGoogleTokenResponse(code));
    }

}
