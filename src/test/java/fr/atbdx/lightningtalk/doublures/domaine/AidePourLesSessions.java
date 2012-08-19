package fr.atbdx.lightningtalk.doublures.domaine;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.StringUtils;

import fr.atbdx.lightningtalk.domaine.Session;
import fr.atbdx.lightningtalk.web.SessionPourLaPresentation;

public class AidePourLesSessions {

    public static final String TITRE_DE_LA_SESSION = "Titre de la session";
    public static final String DESCRIPTION_DE_LA_SESSION = "Description de la session";

    public static Session creerAvecSuffixe(String suffixe) {
        return new Session(TITRE_DE_LA_SESSION + suffixe, DESCRIPTION_DE_LA_SESSION + suffixe, AidePourLesParticipants.creerAvecSuffixe(suffixe));
    }

    public static Session creer() {
        return creerAvecSuffixe(StringUtils.EMPTY);
    }

    public static void verifierAvecSuffixe(Session session, String suffixe) {
        assertThat(session, notNullValue());
        assertThat(session.getTitre(), is(TITRE_DE_LA_SESSION + suffixe));
        assertThat(session.getDescription(), is(DESCRIPTION_DE_LA_SESSION + suffixe));
        AidePourLesParticipants.verifierAvecSuffixe(session.getOrateur(), suffixe);
    }

    public static void verifier(Session session) {
        verifierAvecSuffixe(session, StringUtils.EMPTY);
    }

    public static Session ajouterUnVote(Session session) {
        session.ajouterUnVote(AidePourLesParticipants.PARTICIPANT);
        return session;
    }

    public static void verifierSessionPourLaPresentation(SessionPourLaPresentation sessionPourLaPresentation) {
        assertThat(sessionPourLaPresentation.getTitre(), is(TITRE_DE_LA_SESSION));
        assertThat(sessionPourLaPresentation.getDescription(), is(DESCRIPTION_DE_LA_SESSION));
        assertThat(sessionPourLaPresentation.getOrateur(), is(AidePourLesParticipants.NOM_AFFICHE));
        assertThat(sessionPourLaPresentation.getNombreDeVotes(), is(0));
        assertThat(sessionPourLaPresentation.isPeutVoter(), is(true));
    }

}
