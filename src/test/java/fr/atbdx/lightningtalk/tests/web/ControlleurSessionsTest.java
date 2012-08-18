package fr.atbdx.lightningtalk.tests.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.Session;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesSessions;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesUtilisateurs;
import fr.atbdx.lightningtalk.doublures.domaine.FakeEntrepotSession;
import fr.atbdx.lightningtalk.doublures.domaine.FakeEntrepotUtilisateur;
import fr.atbdx.lightningtalk.web.ControlleurSessions;

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

        AidePourLesSessions.verifier(fakeEntrepotSession.sessionCreer);
    }

    @Test
    public void peutRecupererLesSessions() {
        List<Session> sessionsAttendues = new ArrayList<Session>();
        fakeEntrepotSession.sessions = sessionsAttendues;

        List<Session> sessions = controlleurSessions.recupererLesSessions();

        assertThat(sessions, is(sessionsAttendues));

    }
}
