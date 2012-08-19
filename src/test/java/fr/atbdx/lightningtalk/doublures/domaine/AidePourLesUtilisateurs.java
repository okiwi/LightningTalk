package fr.atbdx.lightningtalk.doublures.domaine;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.StringUtils;

import fr.atbdx.lightningtalk.domaine.Utilisateur;

public class AidePourLesUtilisateurs {

    public static final String ID = AidePourLesParticipants.ID;
    public static final String NOM_AFFICHE = AidePourLesParticipants.NOM_AFFICHE;
    public static final String URL_IMAGE = "urlImage";
    public static final String URL_PROFILE = "urlProfile";
    public static final Utilisateur UTILISATEUR = creerAvecSuffixe(StringUtils.EMPTY);

    public static void verifier(Utilisateur utilisateur) {
        verifierAvecSuffixe(utilisateur, StringUtils.EMPTY);
    }

    public static void verifierAvecSuffixe(Utilisateur utilisateur, String suffixe) {
        AidePourLesParticipants.verifierAvecSuffixe(utilisateur, suffixe);
        assertThat(utilisateur.getUrlImage(), is(URL_IMAGE));
        assertThat(utilisateur.getUrlProfil(), is(URL_PROFILE));
    }

    public static Utilisateur creerAvecSuffixe(String suffixe) {
        return new Utilisateur(ID + suffixe, NOM_AFFICHE + suffixe, URL_IMAGE + suffixe, URL_PROFILE + suffixe);
    }
}
