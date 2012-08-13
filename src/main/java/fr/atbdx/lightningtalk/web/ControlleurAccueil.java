package fr.atbdx.lightningtalk.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.atbdx.lightningtalk.domaine.google.PoigneePourStockerEnSessionLeCredentialGoogle;

@Controller
public class ControlleurAccueil {

    @Autowired
    private PoigneePourStockerEnSessionLeCredentialGoogle poigneePourStockerEnSessionLeCredentialGoogle;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView accueil() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("accueil");
        if (poigneePourStockerEnSessionLeCredentialGoogle.recuperer() != null) {
            mav.addObject("accessToken", poigneePourStockerEnSessionLeCredentialGoogle.recuperer().accessToken);
        } else {
            mav.addObject("accessToken", "vide");
        }
        return mav;
    }

}
