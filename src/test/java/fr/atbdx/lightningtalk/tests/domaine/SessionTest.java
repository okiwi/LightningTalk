package fr.atbdx.lightningtalk.tests.domaine;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.Session;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesSessions;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesUtilisateurs;

public class SessionTest {

    @Test
    public void creation() {
        Session session = new Session(AidePourLesSessions.TITRE_DE_LA_SESSION, AidePourLesSessions.DESCRIPTION_DE_LA_SESSION, AidePourLesUtilisateurs.UTILISATEUR);

        assertThat(session.getTitre(), is(AidePourLesSessions.TITRE_DE_LA_SESSION));
        assertThat(session.getDescription(), is(AidePourLesSessions.DESCRIPTION_DE_LA_SESSION));
        assertThat(session.getOrateur(), is(AidePourLesUtilisateurs.UTILISATEUR));
    }

}
