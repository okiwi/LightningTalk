package fr.atbdx.lightningtalk.web;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringEscapeUtils;

import fr.atbdx.lightningtalk.domaine.EntrepotUtilisateur;
import fr.atbdx.lightningtalk.domaine.Session;
import fr.atbdx.lightningtalk.domaine.Utilisateur;

public class SessionPourLaPresentation {

    private Utilisateur utilisateurCourant;
    private Session session;
    private EntrepotUtilisateur entrepotUtilisateur;

    public SessionPourLaPresentation(Session session, Utilisateur utilisateurCourant, EntrepotUtilisateur entrepotUtilisateur) {
        this.session = session;
        this.utilisateurCourant = utilisateurCourant;
        this.entrepotUtilisateur = entrepotUtilisateur;
    }

    public String getTitre() {
        return session.getTitre();
    }

    public String getDescription() {
        return session.getDescription();
    }

    public String getOrateur() {
        return entrepotUtilisateur.recuperer(session.getOrateur()).getNomAffiche();
    }

    public int getNombreDeVotes() {
        return session.getNombreDeVotes();
    }

    public boolean isPeutAjouterUnVote() {
        return session.peutAjouterUnVote(utilisateurCourant);
    }

    public String getTitreEncodePourJavascript() throws UnsupportedEncodingException {
        return StringEscapeUtils.escapeEcmaScript(session.getTitre());
    }

    public boolean isPeutSupprimerUnVote() {
        return session.peutSupprimerUnVote(utilisateurCourant);
    }

    public boolean isEstOrateur() {
        return session.estOrateur(utilisateurCourant);
    }

}
