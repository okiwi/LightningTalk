package fr.atbdx.lightningtalk.web;

import fr.atbdx.lightningtalk.domaine.Session;
import fr.atbdx.lightningtalk.domaine.Utilisateur;

public class SessionPourLaPresentation {

    private Utilisateur utilisateurCourant;
    private Session session;

    public SessionPourLaPresentation(Session session, Utilisateur utilisateurCourant) {
        this.session = session;
        this.utilisateurCourant = utilisateurCourant;
    }

    public String getTitre() {
        return session.getTitre();
    }

    public String getDescription() {
        return session.getDescription();
    }

    public String getOrateur() {
        return session.getOrateur().getNomAffiche();
    }

    public int getNombreDeVotes() {
        return session.getNombreDeVotes();
    }

    public boolean isPeutVoter() {
        return session.peutVoter(utilisateurCourant);
    }

}
