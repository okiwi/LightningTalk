package fr.atbdx.lightningtalk.domaine;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServiceDAuthentification {

    private EntrepotUtilisateur entrepotUtilisateur;
    private SystemeDAuthentificationExterne systemeDAuthentificationExterne;
    private Utilisateur utilisateurCourant;

    @Autowired
    public ServiceDAuthentification(SystemeDAuthentificationExterne systemeDAuthentificationExterne, EntrepotUtilisateur entrepotUtilisateur) {
        this.entrepotUtilisateur = entrepotUtilisateur;
        this.systemeDAuthentificationExterne = systemeDAuthentificationExterne;
    }

    protected ServiceDAuthentification() {
    }

    public void authentifier(String codeDAuthentification) throws IOException {
        utilisateurCourant = systemeDAuthentificationExterne.recupererUtilisateurDepuisUnCodeDAuthentification(codeDAuthentification);
        if (entrepotUtilisateur.recuperer(utilisateurCourant.getId()) == null) {
            entrepotUtilisateur.creer(utilisateurCourant);
        } else {
            entrepotUtilisateur.mettreAJour(utilisateurCourant);
        }

    }

    public Utilisateur recupererUtilisateurCourant() {
        return utilisateurCourant;
    }

}