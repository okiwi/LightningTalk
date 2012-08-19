package fr.atbdx.lightningtalk.domaine.doublures;

import fr.atbdx.lightningtalk.domaine.Utilisateur;
import org.apache.commons.lang3.StringUtils;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class AidePourLesUtilisateurs {

    public static final String ID = "id";
    public static final String NOM_AFFICHE = "nomAffiche";

    public static final Utilisateur UTILISATEUR = creerAvecSuffixe(StringUtils.EMPTY);

    public static void verifier(Utilisateur utilisateur) {
        verifierAvecSuffixe(utilisateur, StringUtils.EMPTY);
    }

    public static void verifierAvecSuffixe(Utilisateur utilisateur, String suffixe) {
        assertThat(utilisateur, notNullValue());
        assertThat(utilisateur.getId(), is(AidePourLesUtilisateurs.ID + suffixe));
        assertThat(utilisateur.getNomAffiche(), is(AidePourLesUtilisateurs.NOM_AFFICHE + suffixe));
    }

    public static Utilisateur creerAvecSuffixe(String suffixe) {
        return new Utilisateur(ID + suffixe, NOM_AFFICHE + suffixe);
    }

}
