package fr.atbdx.lightningtalk.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.atbdx.lightningtalk.domaine.EntrepotSession;
import fr.atbdx.lightningtalk.domaine.EntrepotUtilisateur;
import fr.atbdx.lightningtalk.domaine.Session;
import fr.atbdx.lightningtalk.domaine.Utilisateur;

@Controller
@RequestMapping("/sessions")
public class ControlleurSessions {

    private final EntrepotUtilisateur entrepotUtilisateur;
    private final EntrepotSession entrepotSession;

    @Autowired
    public ControlleurSessions(EntrepotUtilisateur entrepotUtilisateur, EntrepotSession entrepotSession) {
        this.entrepotUtilisateur = entrepotUtilisateur;
        this.entrepotSession = entrepotSession;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    void creerUneSession(@RequestParam("titre") String titre, @RequestParam("description") String description) throws IOException {
        Session session = new Session(titre, description, entrepotUtilisateur.recupererUtilisateurCourant());
        entrepotSession.creerUneSession(session);

    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<SessionPourLaPresentation> recupererLesSessions() throws IOException {
        List<Session> sessions = entrepotSession.recupererLesSessions();
        Utilisateur utilisateurCourant = entrepotUtilisateur.recupererUtilisateurCourant();
        List<SessionPourLaPresentation> sessionsPourLaPresentations = new ArrayList<SessionPourLaPresentation>();
        for (Session session : sessions) {
            sessionsPourLaPresentations.add(new SessionPourLaPresentation(session, utilisateurCourant));
        }
        return sessionsPourLaPresentations;
    }

    @RequestMapping(value = "/{titreDeLaSession}/votants", method = RequestMethod.POST)
    public @ResponseBody
    void ajouterUnVote(@PathVariable String titreDeLaSession) throws IOException {
        Session session = entrepotSession.recupererDepuisSonTitre(titreDeLaSession);
        session.ajouterUnVote(entrepotUtilisateur.recupererUtilisateurCourant());
        entrepotSession.sauvegargerUneSession(session);
    }

    @RequestMapping(value = "/{titreDeLaSession}/votants", method = RequestMethod.GET,headers="X-HTTP-Method-Override=DELETE")
    public @ResponseBody
    void supprimerUnVote(@PathVariable String titreDeLaSession) throws IOException {
        Session session = entrepotSession.recupererDepuisSonTitre(titreDeLaSession);
        session.supprimerUnVote(entrepotUtilisateur.recupererUtilisateurCourant());
        entrepotSession.sauvegargerUneSession(session);
    }

}
