package fr.atbdx.lightningtalk.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.atbdx.lightningtalk.domaine.ServiceDAuthentification;
import fr.atbdx.lightningtalk.domaine.Utilisateur;

@Controller
public class ControlleurAccueil {

    private ServiceDAuthentification serviceDAuthentification;

    @Autowired
    public ControlleurAccueil(ServiceDAuthentification serviceDAuthentification) {
        this.serviceDAuthentification = serviceDAuthentification;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView valoriserAccueil() throws IOException {
        ModelAndView valorisation = new ModelAndView();
        valorisation.setViewName("accueil");
        Utilisateur utilisateurCourant = serviceDAuthentification.recupererUtilisateurCourant();
        if (utilisateurCourant != null) {
            valorisation.addObject("utilisateur", utilisateurCourant);
        }
        return valorisation;
    }

}
