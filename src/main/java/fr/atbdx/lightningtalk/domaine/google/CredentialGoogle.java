package fr.atbdx.lightningtalk.domaine.google;

import java.io.Serializable;

import org.apache.commons.lang3.Validate;

import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;

public final class CredentialGoogle implements Serializable {

    private static final long serialVersionUID = 3165915070445362770L;

    public final String accessToken;

    public final String refreshToken;

    public CredentialGoogle(GoogleTokenResponse googleTokenResponse) {
        Validate.notNull(googleTokenResponse, "googleTokenResponse");
        this.accessToken = googleTokenResponse.getAccessToken();
        this.refreshToken = googleTokenResponse.getRefreshToken();
    }

}
