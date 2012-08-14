package fr.atbdx.lightningtalk.tests.domaine.google;

import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.google.InformationsDAuthentification;
import fr.atbdx.lightningtalk.doublures.domaine.google.AidePourLAuthentification;
import fr.atbdx.lightningtalk.doublures.domaine.google.FakeConnecteurGoogle;

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
