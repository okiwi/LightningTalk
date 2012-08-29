package fr.atbdx.lightningtalk.doublures.domaine;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.StringUtils;

import fr.atbdx.lightningtalk.domaine.Utilisateur;

public class AidePourLesUtilisateurs {

    private static final String SUFFIXE_UN_AUTRE_UTILISATEUR = "unAutreUtilisateur";
    public static final String ID = "id";
    public static final String NOM_AFFICHE = "nomAffiche";
    public static final String URL_IMAGE = "urlImage";
    public static final String URL_PROFIL = "urlProfil";
    public static final Utilisateur UTILISATEUR = creerAvecSuffixe(StringUtils.EMPTY);
    public static final Utilisateur UN_AUTRE_UTILISATEUR = creerAvecSuffixe(SUFFIXE_UN_AUTRE_UTILISATEUR);

    public static void verifier(Utilisateur utilisateur) {
        verifierAvecSuffixe(utilisateur, StringUtils.EMPTY);
    }

    public static void verifierAvecSuffixe(Utilisateur utilisateur, String suffixe) {
        assertThat(utilisateur, notNullValue());
        assertThat(utilisateur.getId(), is(recupererUnIdAvecSuffix(suffixe)));
        assertThat(utilisateur.getNomAffiche(), is(AidePourLesUtilisateurs.NOM_AFFICHE + suffixe));
        assertThat(utilisateur.getUrlImage(), is(URL_IMAGE));
        assertThat(utilisateur.getUrlProfil(), is(URL_PROFIL));
    }

    public static Utilisateur creerAvecSuffixe(String suffixe) {
        return new Utilisateur(recupererUnIdAvecSuffix(suffixe), NOM_AFFICHE + suffixe, URL_IMAGE + suffixe, URL_PROFIL + suffixe);
    }

    public static String recupererUnIdAvecSuffix(String suffixe) {
        return ID + suffixe;
    }

}
