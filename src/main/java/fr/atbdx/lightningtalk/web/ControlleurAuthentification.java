package fr.atbdx.lightningtalk.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.atbdx.lightningtalk.domaine.EntrepotUtilisateur;

@Controller
@RequestMapping("/authentification")
public class ControlleurAuthentification {

    private final EntrepotUtilisateur entrepotUtilisateur;

    @Autowired
    public ControlleurAuthentification(EntrepotUtilisateur entrepotUtilisateur) {
        this.entrepotUtilisateur = entrepotUtilisateur;
    }

    @RequestMapping(value = "/externe", method = RequestMethod.GET)
    public String demanderAuthentificationExterne() {
        return "redirect:" + entrepotUtilisateur.recupererLURLDuServiceDAuthentificationExterne();
    }

    @RequestMapping(value = "/externe/retour", method = RequestMethod.GET)
    public String authentification(@RequestParam(required = false, value = "code") String code, @RequestParam(required = false, value = "error") String codeErreur)
            throws IOException {
        entrepotUtilisateur.authentifier(code, codeErreur);
        return "redirect:/";
    }

}
