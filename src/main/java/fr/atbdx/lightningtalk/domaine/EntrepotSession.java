package fr.atbdx.lightningtalk.domaine;

import java.util.List;

public interface EntrepotSession {

    List<Session> recupererLesSessions();

    void creerUneSession(Session session) throws ImpossibleDeCreerUneSession;

    Session recupererDepuisSonTitre(String titreDeLaSession);

    void sauvegargerUneSession(Session sessionAMettreAJour);

}
