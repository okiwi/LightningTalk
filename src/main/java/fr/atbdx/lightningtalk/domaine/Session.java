package fr.atbdx.lightningtalk.domaine;

public class Session {

    private String titre;
    private Utilisateur orateur;
    private String description;

    public Session(String titre, String description, Utilisateur orateur) {
        this.titre = titre;
        this.orateur = orateur;
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public Utilisateur getOrateur() {
        return orateur;
    }

    public String getDescription() {
        return description;
    }

}
