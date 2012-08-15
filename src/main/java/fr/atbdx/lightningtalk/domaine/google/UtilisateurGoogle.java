package fr.atbdx.lightningtalk.domaine.google;

import com.google.api.services.plus.model.Person;

import fr.atbdx.lightningtalk.domaine.Utilisateur;

public class UtilisateurGoogle extends Utilisateur {

    public UtilisateurGoogle(Person person) {
        super(person.getId(), person.getDisplayName());
    }

}
