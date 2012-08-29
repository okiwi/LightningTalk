package fr.atbdx.lightningtalk.domaine;

public interface EntrepotUtilisateur {

    Utilisateur recuperer(String id);

    void creer(Utilisateur utilisateur);

    public abstract void mettreAJour(Utilisateur utilisateur);

}
