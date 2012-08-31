package fr.atbdx.lightningtalk.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.atbdx.lightningtalk.domaine.EntrepotSession;
import fr.atbdx.lightningtalk.domaine.EntrepotUtilisateur;
import fr.atbdx.lightningtalk.domaine.ImpossibleDeCreerUneSession;
import fr.atbdx.lightningtalk.domaine.OperationPermiseUniquementALOrateur;
import fr.atbdx.lightningtalk.domaine.ServiceDAuthentification;
import fr.atbdx.lightningtalk.domaine.Session;
import fr.atbdx.lightningtalk.domaine.Utilisateur;

@Controller
@RequestMapping("/sessions")
public class ControlleurSessions {

    private final EntrepotSession entrepotSession;
    private ServiceDAuthentification serviceDAuthentification;
    private EntrepotUtilisateur entrepotUtilisateur;

    @Autowired
    public ControlleurSessions(ServiceDAuthentification serviceDAuthentification, EntrepotSession entrepotSession, EntrepotUtilisateur entrepotUtilisateur) {
        this.serviceDAuthentification = serviceDAuthentification;
        this.entrepotSession = entrepotSession;
        this.entrepotUtilisateur = entrepotUtilisateur;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    void creerUneSession(@RequestParam("titre") String titre, @RequestParam("description") String description) throws ImpossibleDeCreerUneSession {
        Session session = new Session(titre, description, serviceDAuthentification.recupererUtilisateurCourant());
        entrepotSession.creer(session);
    }

    @ExceptionHandler(ImpossibleDeCreerUneSession.class)
    public @ResponseBody
    String gererLesExceptions(Exception exception, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return exception.getMessage();
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<SessionPourLaPresentation> recupererLesSessions() {
        List<Session> sessions = entrepotSession.recupererLesSessions();
        Utilisateur utilisateurCourant = serviceDAuthentification.recupererUtilisateurCourant();
        List<SessionPourLaPresentation> sessionsPourLaPresentations = new ArrayList<SessionPourLaPresentation>();
        for (Session session : sessions) {
            sessionsPourLaPresentations.add(new SessionPourLaPresentation(session, utilisateurCourant, entrepotUtilisateur));
        }
        return sessionsPourLaPresentations;
    }

    @RequestMapping(value = "/{titreDeLaSession}/votants", method = RequestMethod.POST)
    public @ResponseBody
    void ajouterUnVote(@PathVariable String titreDeLaSession) {
        Session session = entrepotSession.recuperer(titreDeLaSession);
        session.ajouterUnVote(serviceDAuthentification.recupererUtilisateurCourant());
        entrepotSession.mettreAJour(session);
    }

    @RequestMapping(value = "/{titreDeLaSession}/votants", method = RequestMethod.GET, headers = "X-HTTP-Method-Override=DELETE")
    public @ResponseBody
    void supprimerUnVote(@PathVariable String titreDeLaSession) {
        Session session = entrepotSession.recuperer(titreDeLaSession);
        session.supprimerUnVote(serviceDAuthentification.recupererUtilisateurCourant());
        entrepotSession.mettreAJour(session);
    }

    @RequestMapping(value = "/{titreDeLaSession}", method = RequestMethod.GET, headers = "X-HTTP-Method-Override=DELETE")
    public @ResponseBody
    void supprimerUneSession(@PathVariable String titreDeLaSession) throws OperationPermiseUniquementALOrateur {
        Session session = entrepotSession.recuperer(titreDeLaSession);
        entrepotSession.supprimer(session, serviceDAuthentification.recupererUtilisateurCourant());

    }

    @RequestMapping(value = "/{titreDeLaSession}", method = RequestMethod.POST)
    public @ResponseBody
    void mettreAJour(@PathVariable String titreDeLaSession, @RequestParam("titre") String nouveauTitre, @RequestParam("description") String nouvelleDescription)
            throws OperationPermiseUniquementALOrateur, ImpossibleDeCreerUneSession {
        Session sessionAMettreAJour = entrepotSession.recuperer(titreDeLaSession);
        Utilisateur utilisateurCourant = serviceDAuthentification.recupererUtilisateurCourant();
        if (titreDeLaSession.equals(nouveauTitre)) {
            sessionAMettreAJour.mettreAJourDescription(nouvelleDescription, utilisateurCourant);
            entrepotSession.mettreAJour(sessionAMettreAJour);
        } else {
            Session nouvelleSession = sessionAMettreAJour.clonerAvecUnNouveauTitre(nouveauTitre, utilisateurCourant);
            nouvelleSession.mettreAJourDescription(nouvelleDescription, utilisateurCourant);
            entrepotSession.creer(nouvelleSession);
            entrepotSession.supprimer(sessionAMettreAJour, utilisateurCourant);
        }

    }

}
