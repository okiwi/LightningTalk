package fr.atbdx.lightningtalk.doublures.domaine;

import fr.atbdx.lightningtalk.domaine.EntrepotUtilisateur;
import fr.atbdx.lightningtalk.domaine.Utilisateur;

public class FakeEntrepotUtilisateur implements EntrepotUtilisateur {

    public Utilisateur utilisateurCreer;
    public Utilisateur utilisateurMisAJour;
    public String idUtilisateurRecuperer;

    @Override
    public Utilisateur recuperer(String id) {
        idUtilisateurRecuperer = id;
        return utilisateurCreer;
    }

    @Override
    public void creer(Utilisateur utilisateur) {
        this.utilisateurCreer = utilisateur;
    }

    public void mettreAJour(Utilisateur utilisateur) {
        this.utilisateurMisAJour = utilisateur ; 
    }

}
