package fr.atbdx.lightningtalk.tests.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesSessions;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesUtilisateurs;
import fr.atbdx.lightningtalk.doublures.domaine.FakeEntrepotSession;
import fr.atbdx.lightningtalk.doublures.domaine.FakeEntrepotUtilisateur;
import fr.atbdx.lightningtalk.web.ControlleurSessions;
import fr.atbdx.lightningtalk.web.SessionPourLaPresentation;

public class ControlleurSessionsTest {

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
    public void peutCreerUneSession() throws IOException {
        fakeEntrepotUtilisateur.utilisateurCourantARetourner = AidePourLesUtilisateurs.UTILISATEUR;

        controlleurSessions.creerUneSession(AidePourLesSessions.TITRE_DE_LA_SESSION, AidePourLesSessions.DESCRIPTION_DE_LA_SESSION);

        AidePourLesSessions.verifier(fakeEntrepotSession.session);
    }

    @Test
    public void peutRecupererLesSessions() throws IOException {
        fakeEntrepotSession.sessions = Arrays.asList(AidePourLesSessions.creer());
        fakeEntrepotUtilisateur.utilisateurCourantARetourner = AidePourLesUtilisateurs.UTILISATEUR;

        List<SessionPourLaPresentation> sessionsPourLaPresentation = controlleurSessions.recupererLesSessions();

        AidePourLesSessions.verifierSessionPourLaPresentation(sessionsPourLaPresentation.get(0));
    }
}
