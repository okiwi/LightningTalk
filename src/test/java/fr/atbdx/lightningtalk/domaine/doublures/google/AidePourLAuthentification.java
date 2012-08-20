package fr.atbdx.lightningtalk.domaine.doublures.google;

import com.google.api.services.plus.model.Person;
import fr.atbdx.lightningtalk.domaine.doublures.AidePourLesUtilisateurs;
import fr.atbdx.lightningtalk.domaine.google.InformationsDAuthentification;
import fr.atbdx.lightningtalk.domaine.google.UtilisateurGoogle;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class AidePourLAuthentification {

    public static final String REFRESH_TOKEN = "refreshToken";
    public static final String ACCESS_TOKEN = "accessToken";
    public static final Person PERSON = creerPerson();
    public static final UtilisateurGoogle UTILISATEUR_GOOGLE = new UtilisateurGoogle(PERSON);

    public static void verifier(InformationsDAuthentification credentialGoogle) {
        assertThat(credentialGoogle, notNullValue());
        assertThat(credentialGoogle.refreshToken, is(AidePourLAuthentification.REFRESH_TOKEN));
        assertThat(credentialGoogle.accessToken, is(AidePourLAuthentification.ACCESS_TOKEN));
    }

    private static Person creerPerson() {
        Person person = new Person();
        person.setId(AidePourLesUtilisateurs.ID);
        person.setDisplayName(AidePourLesUtilisateurs.NOM_AFFICHE);
        return person;
    }

}
