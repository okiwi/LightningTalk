package fr.atbdx.lightningtalk.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.atbdx.lightningtalk.domaine.ServiceDAuthentification;
import fr.atbdx.lightningtalk.domaine.Utilisateur;

@Controller
@RequestMapping("/utilisateurs")
public class ControleurUtilisateurs {

    private ServiceDAuthentification serviceDAuthentification;

    @Autowired
    public ControleurUtilisateurs(ServiceDAuthentification serviceDAuthentification) {
        this.serviceDAuthentification = serviceDAuthentification;
    }

    @RequestMapping(value = "/courant", method = RequestMethod.GET)
    public @ResponseBody
    Utilisateur recuperUtilisateurCourant() {
        return serviceDAuthentification.recupererUtilisateurCourant();
    }

}
