package fr.atbdx.lightningtalk.domaine.google;

import fr.atbdx.lightningtalk.domaine.doublures.google.AidePourLAuthentification;
import fr.atbdx.lightningtalk.domaine.doublures.google.FakeConnecteurGoogle;
import org.junit.Test;

public class PoigneePourStockerEnSessionLesInformationsDAuthentificationTest {

    @Test
    public void creerEtRecuperer() {
        PoigneePourStockerEnSessionLesInformationsDAuthentification poigneePourStockerEnSessionLesInformationsDAuthentification = new PoigneePourStockerEnSessionLesInformationsDAuthentification();

        poigneePourStockerEnSessionLesInformationsDAuthentification.creer(FakeConnecteurGoogle.GOOGLE_TOKEN_RESPONSE);

        AidePourLAuthentification.verifier(poigneePourStockerEnSessionLesInformationsDAuthentification.recuperer());
    }
}
