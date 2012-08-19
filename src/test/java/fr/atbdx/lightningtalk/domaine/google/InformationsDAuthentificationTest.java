package fr.atbdx.lightningtalk.domaine.google;

import fr.atbdx.lightningtalk.domaine.doublures.google.AidePourLAuthentification;
import fr.atbdx.lightningtalk.domaine.doublures.google.FakeConnecteurGoogle;
import org.junit.Test;

public class InformationsDAuthentificationTest {

    @Test
    public void creation() {

        InformationsDAuthentification informationDAuthentification = new InformationsDAuthentification(FakeConnecteurGoogle.GOOGLE_TOKEN_RESPONSE);

        AidePourLAuthentification.verifier(informationDAuthentification);
    }

    @Test(expected = NullPointerException.class)
    public void creationAvecCredentialNull() {

        InformationsDAuthentification informationDAuthentification = new InformationsDAuthentification(null);

        AidePourLAuthentification.verifier(informationDAuthentification);
    }

}
