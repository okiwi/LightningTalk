package fr.atbdx.lightningtalk.tests.domaine.google;

import org.junit.Test;

import com.google.api.services.plus.model.Person;
import com.google.api.services.plus.model.Person.Image;

import fr.atbdx.lightningtalk.domaine.Utilisateur;
import fr.atbdx.lightningtalk.domaine.google.MappingPersonGoogleEnUtilisateur;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesUtilisateurs;

public class MappingPersonGoogleEnUtilisateurTest {

    @Test
    public void peutMapperUnePersonGoogleEnUtilisateur() {
        Person person = new Person();
        person.setId(AidePourLesUtilisateurs.ID);
        person.setDisplayName(AidePourLesUtilisateurs.NOM_AFFICHE);
        Image image = new Image();
        image.setUrl(AidePourLesUtilisateurs.URL_IMAGE);
        person.setImage(image);
        person.setUrl(AidePourLesUtilisateurs.URL_PROFIL);

        Utilisateur utilisateur = MappingPersonGoogleEnUtilisateur.mapper(person);

        AidePourLesUtilisateurs.verifier(utilisateur);
    }

}
