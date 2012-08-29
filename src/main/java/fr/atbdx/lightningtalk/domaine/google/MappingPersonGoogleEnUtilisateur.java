package fr.atbdx.lightningtalk.domaine.google;

import com.google.api.services.plus.model.Person;

import fr.atbdx.lightningtalk.domaine.Utilisateur;

public class MappingPersonGoogleEnUtilisateur {

    public static Utilisateur mapper(Person person) {
        return new Utilisateur(person.getId(), person.getDisplayName(), person.getImage().getUrl(), person.getUrl());
    }

}
