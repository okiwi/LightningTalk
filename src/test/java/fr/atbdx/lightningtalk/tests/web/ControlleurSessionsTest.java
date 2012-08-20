package fr.atbdx.lightningtalk.tests.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.ImpossibleDeCreerUneSession;
import fr.atbdx.lightningtalk.domaine.Session;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesParticipants;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesSessions;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesUtilisateurs;
import fr.atbdx.lightningtalk.doublures.domaine.FakeEntrepotSession;
import fr.atbdx.lightningtalk.doublures.domaine.FakeEntrepotUtilisateur;
import fr.atbdx.lightningtalk.web.ControlleurSessions;
import fr.atbdx.lightningtalk.web.SessionPourLaPresentation;

public class ControlleurSessionsTest {

    private static final String MESSAGE = "message";
    private FakeEntrepotUtilisateur fakeEntrepotUtilisateur;
    private FakeEntrepotSession fakeEntrepotSession;
    private ControlleurSessions controlleurSessions;

    @Before
    public void avantLesTests() {
        fakeEntrepotUtilisateur = new FakeEntrepotUtilisateur();
        fakeEntrepotSession = new FakeEntrepotSession();
        controlleurSessions = new ControlleurSessions(fakeEntrepotUtilisateur, fakeEntrepotSession);
    }

    @Test
    public void peutCreerUneSession() throws IOException, ImpossibleDeCreerUneSession {
        fakeEntrepotUtilisateur.utilisateurCourantARetourner = AidePourLesUtilisateurs.UTILISATEUR;

        controlleurSessions.creerUneSession(AidePourLesSessions.TITRE_DE_LA_SESSION, AidePourLesSessions.DESCRIPTION_DE_LA_SESSION);

        AidePourLesSessions.verifier(fakeEntrepotSession.session);
    }

    @Test
    public void peutRecupererLesSessionsDePresentation() throws IOException {
        fakeEntrepotSession.creerUneSession(AidePourLesSessions.creer());
        fakeEntrepotUtilisateur.utilisateurCourantARetourner = AidePourLesUtilisateurs.UTILISATEUR;

        List<SessionPourLaPresentation> sessionsPourLaPresentation = controlleurSessions.recupererLesSessions();

        AidePourLesSessions.verifierSessionPourLaPresentation(sessionsPourLaPresentation.get(0));
    }

    @Test
    public void peutAjouterUnVote() throws IOException {
        fakeEntrepotSession.creerUneSession(AidePourLesSessions.creer());
        fakeEntrepotUtilisateur.utilisateurCourantARetourner = AidePourLesUtilisateurs.UTILISATEUR;

        controlleurSessions.ajouterUnVote(AidePourLesSessions.TITRE_DE_LA_SESSION);

        assertThat(fakeEntrepotSession.titreDeLaSessionRecupere, is(AidePourLesSessions.TITRE_DE_LA_SESSION));
        assertThat(fakeEntrepotSession.sessionSauvegardee, is(true));
        assertThat(fakeEntrepotSession.recupererDepuisSonTitre(null).getNombreDeVotes(), is(1));
    }

    @Test
    public void peutSupprimerUnVote() throws IOException {
        Session session = AidePourLesSessions.creer();
        session.ajouterUnVote(AidePourLesParticipants.PARTICIPANT);
        fakeEntrepotSession.creerUneSession(session);
        fakeEntrepotUtilisateur.utilisateurCourantARetourner = AidePourLesUtilisateurs.UTILISATEUR;

        controlleurSessions.supprimerUnVote(AidePourLesSessions.TITRE_DE_LA_SESSION);

        assertThat(fakeEntrepotSession.titreDeLaSessionRecupere, is(AidePourLesSessions.TITRE_DE_LA_SESSION));
        assertThat(fakeEntrepotSession.sessionSauvegardee, is(true));
        assertThat(fakeEntrepotSession.recupererDepuisSonTitre(null).getNombreDeVotes(), is(0));
    }

    @Test
    public void peutGererLExceptionImpossibleDeCreerUneSession() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        String messageRetourne = controlleurSessions.gererLExceptionImpossibleDeCreerUneSession(new ImpossibleDeCreerUneSession(MESSAGE), response);

        assertThat(messageRetourne, is(MESSAGE));
        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
