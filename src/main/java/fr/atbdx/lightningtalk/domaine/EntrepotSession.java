package fr.atbdx.lightningtalk.domaine;

import java.util.List;

public interface EntrepotSession {

    List<Session> recupererLesSessions();

    void creer(Session session) throws ImpossibleDeCreerUneSession;

    Session recuperer(String titreDeLaSession);

    void mettreAJour(Session sessionAMettreAJour);

}
