package fr.atbdx.lightningtalk.web;

import fr.atbdx.lightningtalk.domaine.Session;
import fr.atbdx.lightningtalk.domaine.doublures.AidePourLesSessions;
import fr.atbdx.lightningtalk.domaine.doublures.AidePourLesUtilisateurs;
import fr.atbdx.lightningtalk.domaine.doublures.FakeEntrepotSession;
import fr.atbdx.lightningtalk.domaine.doublures.FakeEntrepotUtilisateur;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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
