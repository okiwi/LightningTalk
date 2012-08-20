package fr.atbdx.lightningtalk.tests.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.Session;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesSessions;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesUtilisateurs;
import fr.atbdx.lightningtalk.web.SessionPourLaPresentation;

public class SessionPourLaPresentationTest {

    private Session session;

    @Before
    public void avantLesTests() {
        session = AidePourLesSessions.creer();
    }

    @Test
    public void peutCreerUneSessionPourLaPresentation() throws UnsupportedEncodingException {

        SessionPourLaPresentation sessionPourLaPresentation = new SessionPourLaPresentation(session, AidePourLesUtilisateurs.UTILISATEUR);

        AidePourLesSessions.verifierSessionPourLaPresentation(sessionPourLaPresentation);
    }

    @Test
    public void nombreDeVotesAugmenteSiOnAjouteUnVote() {
        AidePourLesSessions.ajouterUnVote(session);

        SessionPourLaPresentation sessionPourLaPresentation = new SessionPourLaPresentation(session, AidePourLesUtilisateurs.UTILISATEUR);

        assertThat(sessionPourLaPresentation.getNombreDeVotes(), is(1));
    }

    @Test
    public void nePeutPasVoterSiUtilisateurCourantADejaVoter() {
        AidePourLesSessions.ajouterUnVote(session);

        SessionPourLaPresentation sessionPourLaPresentation = new SessionPourLaPresentation(session, AidePourLesUtilisateurs.UTILISATEUR);

        assertThat(sessionPourLaPresentation.isPeutVoter(), is(false));
    }

    @Test
    public void nePeutPasVoterSiUtilisateurCourantNull() {

        SessionPourLaPresentation sessionPourLaPresentation = new SessionPourLaPresentation(session, null);

        assertThat(sessionPourLaPresentation.isPeutVoter(), is(false));
    }
}
