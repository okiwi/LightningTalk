package fr.atbdx.lightningtalk.web;

import java.io.IOException;
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
    void creerUneSession(@RequestParam("titre") String titre, @RequestParam("description") String description) throws IOException, ImpossibleDeCreerUneSession {
        Session session = new Session(titre, description, serviceDAuthentification.recupererUtilisateurCourant());
        entrepotSession.creer(session);
    }

    @ExceptionHandler(ImpossibleDeCreerUneSession.class)
    public @ResponseBody
    String gererLExceptionImpossibleDeCreerUneSession(ImpossibleDeCreerUneSession exception, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return exception.getMessage();
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<SessionPourLaPresentation> recupererLesSessions() throws IOException {
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
    void ajouterUnVote(@PathVariable String titreDeLaSession) throws IOException {
        Session session = entrepotSession.recuperer(titreDeLaSession);
        session.ajouterUnVote(serviceDAuthentification.recupererUtilisateurCourant());
        entrepotSession.mettreAJour(session);
    }

    @RequestMapping(value = "/{titreDeLaSession}/votants", method = RequestMethod.GET, headers = "X-HTTP-Method-Override=DELETE")
    public @ResponseBody
    void supprimerUnVote(@PathVariable String titreDeLaSession) throws IOException {
        Session session = entrepotSession.recuperer(titreDeLaSession);
        session.supprimerUnVote(serviceDAuthentification.recupererUtilisateurCourant());
        entrepotSession.mettreAJour(session);
    }

}
