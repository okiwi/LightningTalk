package fr.atbdx.lightningtalk.domaine.doublures;

import fr.atbdx.lightningtalk.domaine.Session;
import org.apache.commons.lang3.StringUtils;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class AidePourLesSessions {

    public static final String TITRE_DE_LA_SESSION = "Titre de la session";
    public static final String DESCRIPTION_DE_LA_SESSION = "Description de la session";

    public static Session creerAvecSuffixe(String suffixe) {
        return new Session(TITRE_DE_LA_SESSION + suffixe, DESCRIPTION_DE_LA_SESSION + suffixe, AidePourLesUtilisateurs.creerAvecSuffixe(suffixe));
    }

    public static Session creer() {
        return creerAvecSuffixe(StringUtils.EMPTY);
    }

    public static void verifierAvecSuffixe(Session session, String suffixe) {
        assertThat(session, notNullValue());
        assertThat(session.getTitre(), is(TITRE_DE_LA_SESSION + suffixe));
        assertThat(session.getDescription(), is(DESCRIPTION_DE_LA_SESSION + suffixe));
        AidePourLesUtilisateurs.verifierAvecSuffixe(session.getOrateur(), suffixe);
    }

    public static void verifier(Session session) {
        verifierAvecSuffixe(session, StringUtils.EMPTY);
    }

}