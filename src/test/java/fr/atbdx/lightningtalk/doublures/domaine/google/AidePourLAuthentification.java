package fr.atbdx.lightningtalk.doublures.domaine.google;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.google.api.services.plus.model.Person;

import fr.atbdx.lightningtalk.domaine.Utilisateur;
import fr.atbdx.lightningtalk.domaine.google.InformationsDAuthentification;

public class AidePourLAuthentification {

    public static final String REFRESH_TOKEN = "refreshToken";
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String NOM_AFFICHE = "nomAffiche";
    public static final String ID = "id";
    public static final Person PERSON = creerPerson();

    public static void verifier(InformationsDAuthentification credentialGoogle) {
        assertThat(credentialGoogle, notNullValue());
        assertThat(credentialGoogle.refreshToken, is(AidePourLAuthentification.REFRESH_TOKEN));
        assertThat(credentialGoogle.accessToken, is(AidePourLAuthentification.ACCESS_TOKEN));
    }

    public static void verifier(Utilisateur utilisateur) {
        assertThat(utilisateur, notNullValue());
        assertThat(utilisateur.getId(), is(AidePourLAuthentification.ID));
        assertThat(utilisateur.getNomAffiche(), is(AidePourLAuthentification.NOM_AFFICHE));
    }

    private static Person creerPerson() {
        Person person = new Person();
        person.setId(AidePourLAuthentification.ID);
        person.setDisplayName(AidePourLAuthentification.NOM_AFFICHE);
        return person;
    }

    public static final Utilisateur UTILISATEUR = new Utilisateur(PERSON);

}
