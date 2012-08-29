package fr.atbdx.lightningtalk.tests.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.ImpossibleDeCreerUneSession;
import fr.atbdx.lightningtalk.domaine.Session;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesSessions;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesUtilisateurs;
import fr.atbdx.lightningtalk.doublures.domaine.FakeEntrepotUtilisateur;
import fr.atbdx.lightningtalk.web.SessionPourLaPresentation;

public class SessionPourLaPresentationTest {

    private Session session;

    @Before
    public void avantLesTests() {
        session = AidePourLesSessions.creer();
    }

    @Test
    public void peutCreerUneSessionPourLaPresentation() throws UnsupportedEncodingException {
        FakeEntrepotUtilisateur fakeEntrepotUtilisateur = new FakeEntrepotUtilisateur();
        fakeEntrepotUtilisateur.creer(AidePourLesUtilisateurs.UN_AUTRE_UTILISATEUR);
        
        SessionPourLaPresentation sessionPourLaPresentation = new SessionPourLaPresentation(session, AidePourLesUtilisateurs.UTILISATEUR, fakeEntrepotUtilisateur);

        AidePourLesSessions.verifierSessionPourLaPresentationAvecOrateurQuiEstUnAutreUtilisateur(sessionPourLaPresentation);
        assertThat(fakeEntrepotUtilisateur.idUtilisateurRecuperer, is(AidePourLesUtilisateurs.UTILISATEUR.getId()));
    }

    @Test
    public void encodeLesCaracteresSpeciauxJavascript() throws UnsupportedEncodingException, ImpossibleDeCreerUneSession {
        Session sessionAvecCharactereSpecial = new Session("un chtit' session", "description", AidePourLesUtilisateurs.UTILISATEUR);

        SessionPourLaPresentation sessionPourLaPresentation = new SessionPourLaPresentation(sessionAvecCharactereSpecial, AidePourLesUtilisateurs.UTILISATEUR,null);

        assertThat(sessionPourLaPresentation.getTitreEncodePourJavascript(), is("un chtit\\' session"));
    }

    @Test
    public void nombreDeVotesAugmenteSiOnAjouteUnVote() {
        AidePourLesSessions.ajouterUnVote(session);

        SessionPourLaPresentation sessionPourLaPresentation = new SessionPourLaPresentation(session, AidePourLesUtilisateurs.UTILISATEUR,null);

        assertThat(sessionPourLaPresentation.getNombreDeVotes(), is(1));
    }

    @Test
    public void nePeutPasVoterSiUtilisateurCourantADejaVoter() {
        AidePourLesSessions.ajouterUnVote(session);

        SessionPourLaPresentation sessionPourLaPresentation = new SessionPourLaPresentation(session, AidePourLesUtilisateurs.UTILISATEUR,null);

        assertThat(sessionPourLaPresentation.isPeutVoter(), is(false));
    }

    @Test
    public void nePeutPasVoterSiUtilisateurCourantNull() {

        SessionPourLaPresentation sessionPourLaPresentation = new SessionPourLaPresentation(session, null,null);

        assertThat(sessionPourLaPresentation.isPeutVoter(), is(false));
    }
}
