package fr.atbdx.lightningtalk.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.atbdx.lightningtalk.domaine.ImpossibleDeSAuthentifier;
import fr.atbdx.lightningtalk.domaine.ServiceDAuthentification;
import fr.atbdx.lightningtalk.domaine.SystemeDAuthentificationExterne;

@Controller
@RequestMapping("/authentification")
public class ControlleurAuthentification {

    public static final String REDIRECTION = "redirect:";
    public static final String REDIRECTION_VERS_PAGE_D_ACCUEIL = REDIRECTION + "/";

    private ServiceDAuthentification serviceDAuthentification;
    private SystemeDAuthentificationExterne systemeDAuthentificationExterne;

    @Autowired
    public ControlleurAuthentification(ServiceDAuthentification serviceDAuthentification, SystemeDAuthentificationExterne systemeDAuthentificationExterne) {
        this.serviceDAuthentification = serviceDAuthentification;
        this.systemeDAuthentificationExterne = systemeDAuthentificationExterne;
    }

    @RequestMapping(value = "/externe", method = RequestMethod.GET)
    public String demanderAuthentificationExterne() {
        return REDIRECTION + systemeDAuthentificationExterne.recupererLURLDuServiceDAuthentificationExterne();
    }

    @RequestMapping(value = "/externe/retour", method = RequestMethod.GET)
    public String authentification(@RequestParam(required = false, value = "code") String codeDAuthentification,
            @RequestParam(required = false, value = "error") String codeErreur, RedirectAttributes redirectAttributes) {
        try {
            serviceDAuthentification.authentifier(codeDAuthentification, codeErreur);
        } catch (ImpossibleDeSAuthentifier e) {
            redirectAttributes.addFlashAttribute("erreur", e.getMessage());
        }
        return REDIRECTION_VERS_PAGE_D_ACCUEIL;
    }

    @RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
    public @ResponseBody
    void deconnexion() {
        serviceDAuthentification.deconnexion();
    }
}
