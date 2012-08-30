package fr.atbdx.lightningtalk.tests.web;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.EntrepotUtilisateur;
import fr.atbdx.lightningtalk.domaine.ImpossibleDeCreerUneSession;
import fr.atbdx.lightningtalk.domaine.OperationPermiseUniquementALOrateur;
import fr.atbdx.lightningtalk.domaine.Session;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLAuthentification;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesSessions;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesUtilisateurs;
import fr.atbdx.lightningtalk.doublures.domaine.FakeEntrepotSession;
import fr.atbdx.lightningtalk.doublures.domaine.FakeEntrepotUtilisateur;
import fr.atbdx.lightningtalk.web.ControlleurSessions;
import fr.atbdx.lightningtalk.web.SessionPourLaPresentation;

public class ControlleurSessionsTest {

    private static final String MESSAGE = "message";
    private FakeEntrepotSession fakeEntrepotSession;
    private ControlleurSessions controlleurSessions;
    private EntrepotUtilisateur fakeEntrepotUtilisateur;

    @Before
    public void avantLesTests() throws IOException {
        fakeEntrepotUtilisateur = new FakeEntrepotUtilisateur();
        fakeEntrepotUtilisateur.creer(AidePourLesUtilisateurs.UN_AUTRE_UTILISATEUR);
        fakeEntrepotSession = new FakeEntrepotSession();
        controlleurSessions = new ControlleurSessions(AidePourLAuthentification.getInstance().simulerAuthentification().serviceDAuthentification, fakeEntrepotSession,
                fakeEntrepotUtilisateur);
    }

    @Test
    public void peutCreerUneSession() throws IOException, ImpossibleDeCreerUneSession {

        controlleurSessions.creerUneSession(AidePourLesSessions.TITRE, AidePourLesSessions.DESCRIPTION);

        AidePourLesSessions.verifier(fakeEntrepotSession.session);
    }

    @Test
    public void peutRecupererLesSessionsDePresentation() throws IOException {
        fakeEntrepotSession.creer(AidePourLesSessions.creer());

        List<SessionPourLaPresentation> sessionsPourLaPresentation = controlleurSessions.recupererLesSessions();

        AidePourLesSessions.verifierSessionPourLaPresentationAvecOrateurQuiEstUnAutreUtilisateur(sessionsPourLaPresentation.get(0));
    }

    @Test
    public void peutAjouterUnVote() throws IOException {
        fakeEntrepotSession.creer(AidePourLesSessions.creer());

        controlleurSessions.ajouterUnVote(AidePourLesSessions.TITRE);

        assertThat(fakeEntrepotSession.titreDeLaSessionRecupere, is(AidePourLesSessions.TITRE));
        assertThat(fakeEntrepotSession.sessionSauvegardee, is(true));
        assertThat(fakeEntrepotSession.recuperer(null).getNombreDeVotes(), is(1));
    }

    @Test
    public void peutSupprimerUnVote() throws IOException {
        Session session = AidePourLesSessions.creer();
        session.ajouterUnVote(AidePourLesUtilisateurs.UTILISATEUR);
        fakeEntrepotSession.creer(session);

        controlleurSessions.supprimerUnVote(AidePourLesSessions.TITRE);

        assertThat(fakeEntrepotSession.titreDeLaSessionRecupere, is(AidePourLesSessions.TITRE));
        assertThat(fakeEntrepotSession.sessionSauvegardee, is(true));
        assertThat(fakeEntrepotSession.recuperer(null).getNombreDeVotes(), is(0));
    }

    @Test
    public void peutGererLesExceptions() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        String messageRetourne = controlleurSessions.gererLesExceptions(new Exception(MESSAGE), response);

        assertThat(messageRetourne, is(MESSAGE));
        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Test
    public void peutSupprimerUneSession() throws OperationPermiseUniquementALOrateur {
        Session session = AidePourLesSessions.creer();
        fakeEntrepotSession.creer(session);

        controlleurSessions.supprimerUneSession(AidePourLesSessions.TITRE);

        assertThat(fakeEntrepotSession.titreDeLaSessionRecupere, is(AidePourLesSessions.TITRE));
        assertThat(fakeEntrepotSession.utilisateurCourantRecupererDurantLaSupression, is(AidePourLesUtilisateurs.UTILISATEUR));
        assertThat(fakeEntrepotSession.session, nullValue());

    }
}
