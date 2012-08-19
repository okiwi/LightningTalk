package fr.atbdx.lightningtalk.domaine;

public class Session {

    private String titre;
    private Utilisateur orateur;
    @SuppressWarnings("UnusedDeclaration")
    private String id;
    private String description;

    public String getId() {
        return id;
    }

    @SuppressWarnings("UnusedDeclaration")
    protected Session() {
        // pour MongoLink
    }

    public Session(String titre, String description, Utilisateur orateur) {
        this.titre = titre;
        this.description = description;
        this.orateur = orateur;
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
