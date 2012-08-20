package fr.atbdx.lightningtalk.domaine.doublures;

import fr.atbdx.lightningtalk.domaine.EntrepotSession;
import fr.atbdx.lightningtalk.domaine.Session;

import java.util.List;

public class FakeEntrepotSession implements EntrepotSession {

    public Session sessionCreer;
    public List<Session> sessions;

    @Override
    public List<Session> recupererLesSessions() {
        return sessions;
    }

    @Override
    public void creerUneSession(Session session) {
        sessionCreer = session;
    }

}
