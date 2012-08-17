package fr.atbdx.lightningtalk.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.atbdx.lightningtalk.domaine.EntrepotSession;
import fr.atbdx.lightningtalk.domaine.EntrepotUtilisateur;
import fr.atbdx.lightningtalk.domaine.Session;

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

}
