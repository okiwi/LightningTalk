package fr.atbdx.lightningtalk.domaine;

import com.google.api.services.plus.model.Person;

public class Utilisateur {

    private final String id;
    private final String nomAffiche;

    public Utilisateur(Person person) {
        this.id = person.getId();
        this.nomAffiche = person.getDisplayName();
    }

    public String getId() {
        return id;
    }

    public String getNomAffiche() {
        return nomAffiche;
    }

}
