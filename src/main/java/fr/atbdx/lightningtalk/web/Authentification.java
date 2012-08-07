package fr.atbdx.lightningtalk.web;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.client.googleapis.auth.oauth2.GoogleBrowserClientRequestUrl;

@Controller
@RequestMapping("/authentification")
public class Authentification {
    
    @RequestMapping(value = "/google", method = RequestMethod.GET)
    public String demanderAuthentificationGoogle(@RequestParam(required = false, value = "error") String codeErreur, @RequestParam(required = false, value = "code") String code) {
        GoogleBrowserClientRequestUrl urlDeRedirectionGoogle = new GoogleBrowserClientRequestUrl("1073813811256.apps.googleusercontent.com",
                        "http://localhost:8080/LightningTalk/authentification/google/retour", Arrays.asList("https://www.googleapis.com/auth/userinfo.email",
                                        "https://www.googleapis.com/auth/userinfo.profile")).setResponseTypes("code").setApprovalPrompt("auto");
        urlDeRedirectionGoogle.set("access_type", "offline");
        return "redirect:" + urlDeRedirectionGoogle.build();
    }
    
    @RequestMapping(value = "/google/retour", method = RequestMethod.GET)
    public @ResponseBody
    String resultatAuthentificationGoogle(@RequestParam(required = false, value = "error") String codeErreur, @RequestParam(required = false, value = "code") String code) {
        if (StringUtils.isNotEmpty(codeErreur)) {
            return codeErreur;
        } else {
            return code;
        }
    }
}
