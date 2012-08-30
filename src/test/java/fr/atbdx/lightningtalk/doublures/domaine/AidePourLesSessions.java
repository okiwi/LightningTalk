package fr.atbdx.lightningtalk.doublures.domaine;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

import fr.atbdx.lightningtalk.domaine.ImpossibleDeCreerUneSession;
import fr.atbdx.lightningtalk.domaine.Session;
import fr.atbdx.lightningtalk.web.SessionPourLaPresentation;

public class AidePourLesSessions {

    public static final String TITRE = "Titre de la session";
    public static final String DESCRIPTION = "Description de la session";

    public static Session creerAvecSuffixe(String suffixe) {
        try {
            return new Session(TITRE + suffixe, DESCRIPTION + suffixe, AidePourLesUtilisateurs.creerAvecSuffixe(suffixe));
        } catch (ImpossibleDeCreerUneSession e) {
            throw new RuntimeException(e);
        }
    }

    public static Session creer() {
        return creerAvecSuffixe(StringUtils.EMPTY);
    }

    public static void verifierAvecSuffixe(Session session, String suffixe) {
        assertThat(session, notNullValue());
        assertThat(session.getTitre(), is(TITRE + suffixe));
        assertThat(session.getDescription(), is(DESCRIPTION + suffixe));
        assertThat(session.getOrateur(), is(AidePourLesUtilisateurs.recupererUnIdAvecSuffix(suffixe)));
    }

    public static void verifier(Session session) {
        verifierAvecSuffixe(session, StringUtils.EMPTY);
    }

    public static Session ajouterUnVote(Session session) {
        session.ajouterUnVote(AidePourLesUtilisateurs.UTILISATEUR);
        return session;
    }

    public static void verifierSessionPourLaPresentationAvecOrateurQuiEstUnAutreUtilisateur(SessionPourLaPresentation sessionPourLaPresentation) throws UnsupportedEncodingException {
        assertThat(sessionPourLaPresentation.getTitre(), is(TITRE));
        assertThat(sessionPourLaPresentation.getTitreEncodePourJavascript(), is(TITRE));
        assertThat(sessionPourLaPresentation.getDescription(), is(DESCRIPTION));
        assertThat(sessionPourLaPresentation.getOrateur(), is(AidePourLesUtilisateurs.UN_AUTRE_UTILISATEUR.getNomAffiche()));
        assertThat(sessionPourLaPresentation.getNombreDeVotes(), is(0));
        assertThat(sessionPourLaPresentation.isPeutAjouterUnVote(), is(true));
    }

    public static final String NOUVEAU_TITRE = "nouveau titre";
    public static final String NOUVELLE_DESCRIPTION = "nouvelle description";

}
