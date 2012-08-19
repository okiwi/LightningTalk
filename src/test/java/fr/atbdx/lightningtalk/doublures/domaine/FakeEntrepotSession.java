package fr.atbdx.lightningtalk.doublures.domaine;

import java.util.List;

import fr.atbdx.lightningtalk.domaine.EntrepotSession;
import fr.atbdx.lightningtalk.domaine.Session;

public class FakeEntrepotSession implements EntrepotSession {

    public Session session;
    public List<Session> sessions;

    @Override
    public List<Session> recupererLesSessions() {
        return sessions;
    }

    @Override
    public void creerUneSession(Session session) {
        this.session = session;
    }

    @Override
    public Session recupererDepuisSonTitre(String titreDeLaSession) {
        return session;
    }

    @Override
    public void sauvegargerUneSession(Session sessionAMettreAJour) {
        this.session = sessionAMettreAJour;
    }

}
