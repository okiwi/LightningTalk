package fr.atbdx.lightningtalk.domaine;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Utilisateur {

    private String urlImage;
    private String urlProfil;
    private String id;
    private String nomAffiche;

    public Utilisateur(String id, String nomAffiche, String urlImage, String urlProfil) {
        this.id = id;
        this.nomAffiche = nomAffiche;
        this.urlImage = urlImage;
        this.urlProfil = urlProfil;
    }
    
    protected Utilisateur() {
    }

    public String getId() {
        return id;
    }

    public String getNomAffiche() {
        return nomAffiche;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getUrlProfil() {
        return urlProfil;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Utilisateur) {
            return ((Utilisateur) obj).id.equals(id);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }

}
