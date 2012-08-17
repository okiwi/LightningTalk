package fr.atbdx.lightningtalk.doublures.domaine;

import java.util.List;

import fr.atbdx.lightningtalk.domaine.EntrepotSession;
import fr.atbdx.lightningtalk.domaine.Session;

public class FakeEntrepotSession implements EntrepotSession {

    public Session sessionCreer;

    @Override
    public List<Session> recupererLesSessions() {
        return null;
    }

    @Override
    public void creerUneSession(Session session) {
        sessionCreer = session;
    }

}
