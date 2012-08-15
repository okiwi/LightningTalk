package fr.atbdx.lightningtalk.domaine;


public class Utilisateur {

    private final String id;
    private final String nomAffiche;

    public Utilisateur(String id, String nomAffiche) {
        this.id = id;
        this.nomAffiche = nomAffiche;
    }

    public String getId() {
        return id;
    }

    public String getNomAffiche() {
        return nomAffiche;
    }

}
