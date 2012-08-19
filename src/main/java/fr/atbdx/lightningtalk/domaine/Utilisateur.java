package fr.atbdx.lightningtalk.domaine;

public class Utilisateur extends Participant {

    private final String urlImage;
    private final String urlProfil;

    public Utilisateur(String id, String nomAffiche, String urlImage, String urlProfil) {
        super(id, nomAffiche);
        this.urlImage = urlImage;
        this.urlProfil = urlProfil;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getUrlProfil() {
        return urlProfil;
    }

}
