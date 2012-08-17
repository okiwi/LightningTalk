package fr.atbdx.lightningtalk.tests.web;

import java.io.IOException;

import org.junit.Test;

import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesSessions;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesUtilisateurs;
import fr.atbdx.lightningtalk.doublures.domaine.FakeEntrepotSession;
import fr.atbdx.lightningtalk.doublures.domaine.FakeEntrepotUtilisateur;
import fr.atbdx.lightningtalk.web.ControlleurSessions;

public class ControlleurSessionsTest {

    @Test
    public void peutCreerUneSession() throws IOException {
        FakeEntrepotUtilisateur fakeEntrepotUtilisateur = new FakeEntrepotUtilisateur();
        FakeEntrepotSession fakeEntrepotSession = new FakeEntrepotSession();
        ControlleurSessions controlleurSessions = new ControlleurSessions(fakeEntrepotUtilisateur, fakeEntrepotSession);
        fakeEntrepotUtilisateur.utilisateurCourantARetourner = AidePourLesUtilisateurs.UTILISATEUR;

        controlleurSessions.creerUneSession(AidePourLesSessions.TITRE_DE_LA_SESSION, AidePourLesSessions.DESCRIPTION_DE_LA_SESSION);

        AidePourLesSessions.verifier(fakeEntrepotSession.sessionCreer);
    }
}
