package fr.atbdx.lightningtalk.domaine.google;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PoigneePourStockerEnSessionLeCredentialGoogle {

    private CredentialGoogle credentialGoogle;

    public void creer(GoogleTokenResponse googleTokenResponse) {
        this.credentialGoogle = new CredentialGoogle(googleTokenResponse);

    }

    public CredentialGoogle recuperer() {
        return credentialGoogle;
    }

}
