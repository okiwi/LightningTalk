package fr.atbdx.lightningtalk.tests.domaine.google;

import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.google.PoigneePourStockerEnSessionLesInformationsDAuthentification;
import fr.atbdx.lightningtalk.doublures.domaine.google.AidePourLAuthentification;
import fr.atbdx.lightningtalk.doublures.domaine.google.FakeConnecteurGoogle;

public class PoigneePourStockerEnSessionLesInformationsDAuthentificationTest {

    @Test
    public void creerEtRecuperer() {
        PoigneePourStockerEnSessionLesInformationsDAuthentification poigneePourStockerEnSessionLesInformationsDAuthentification = new PoigneePourStockerEnSessionLesInformationsDAuthentification();

        poigneePourStockerEnSessionLesInformationsDAuthentification.creer(FakeConnecteurGoogle.GOOGLE_TOKEN_RESPONSE);

        AidePourLAuthentification.verifier(poigneePourStockerEnSessionLesInformationsDAuthentification.recuperer());
    }
}
