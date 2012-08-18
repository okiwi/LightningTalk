package fr.atbdx.lightningtalk.doublures.domaine;

import java.util.List;

import fr.atbdx.lightningtalk.domaine.EntrepotSession;
import fr.atbdx.lightningtalk.domaine.Session;

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
