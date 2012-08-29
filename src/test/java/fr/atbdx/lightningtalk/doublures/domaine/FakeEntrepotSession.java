package fr.atbdx.lightningtalk.doublures.domaine;

import java.util.Arrays;
import java.util.List;

import fr.atbdx.lightningtalk.domaine.EntrepotSession;
import fr.atbdx.lightningtalk.domaine.Session;

public class FakeEntrepotSession implements EntrepotSession {

    public Session session;
    public String titreDeLaSessionRecupere;
    public boolean sessionSauvegardee = false;

    @Override
    public List<Session> recupererLesSessions() {
        return Arrays.asList(session);
    }

    @Override
    public void creer(Session session) {
        this.session = session;
    }

    @Override
    public Session recuperer(String titreDeLaSession) {
        this.titreDeLaSessionRecupere = titreDeLaSession;
        return session;
    }

    @Override
    public void mettreAJour(Session sessionAMettreAJour) {
        this.session = sessionAMettreAJour;
        sessionSauvegardee = true;

    }

}
