package fr.atbdx.lightningtalk.domaine.google;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.api.services.plus.model.Person;

import fr.atbdx.lightningtalk.domaine.EntrepotUtilisateur;
import fr.atbdx.lightningtalk.domaine.Utilisateur;

@Repository
public class EntrepotUtilisateurGoogle implements EntrepotUtilisateur {

    private final ConnecteurGoogle connecteurGoogle;
    private final PoigneePourStockerEnSessionLesInformationsDAuthentification poigneePourStockerEnSessionLesInformationsDAuthentification;

    @Autowired
    public EntrepotUtilisateurGoogle(PoigneePourStockerEnSessionLesInformationsDAuthentification poigneePourStockerEnSessionLesInformationsDAuthentification,
            ConnecteurGoogle connecteurGoogle) {
        this.connecteurGoogle = connecteurGoogle;
        this.poigneePourStockerEnSessionLesInformationsDAuthentification = poigneePourStockerEnSessionLesInformationsDAuthentification;
    }

    @Override
    public String recupererLURLDuServiceDAuthentificationExterne() {
        return connecteurGoogle.recupererLURLDuServiceDAuthentificationExterne();
    }

    @Override
    public void authentifier(String code, String codeErreur) throws IOException {
        poigneePourStockerEnSessionLesInformationsDAuthentification.creer(connecteurGoogle.recupererLeGoogleTokenResponse(code));
    }

    @Override
    public Utilisateur recupererUtilisateurCourant() throws IOException {
        Utilisateur utilisateur;
        InformationsDAuthentification informationsDAuthentification = poigneePourStockerEnSessionLesInformationsDAuthentification.recuperer();
        if (informationsDAuthentification == null) {
            utilisateur = null;
        } else {
            Person person = connecteurGoogle.recupererPersonDepuisGoogle(informationsDAuthentification);
            utilisateur = new UtilisateurGoogle(person);
        }
        return utilisateur;
    }

}
