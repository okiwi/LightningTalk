package fr.atbdx.lightningtalk.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.atbdx.lightningtalk.domaine.EntrepotUtilisateur;
import fr.atbdx.lightningtalk.domaine.Utilisateur;

@Controller
public class ControlleurAccueil {

    private final EntrepotUtilisateur entrepotUtilisateur;

    @Autowired
    public ControlleurAccueil(EntrepotUtilisateur entrepotUtilisateur) {
        this.entrepotUtilisateur = entrepotUtilisateur;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView valoriserAccueil() throws IOException {
        ModelAndView valorisation = new ModelAndView();
        valorisation.setViewName("accueil");
        Utilisateur utilisateurCourant = entrepotUtilisateur.recupererUtilisateurCourant();
        if (utilisateurCourant != null) {
            valorisation.addObject(utilisateurCourant);
        }
        return valorisation;
    }

}
