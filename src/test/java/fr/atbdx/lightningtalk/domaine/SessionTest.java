package fr.atbdx.lightningtalk.domaine;

import fr.atbdx.lightningtalk.domaine.doublures.AidePourLesSessions;
import fr.atbdx.lightningtalk.domaine.doublures.AidePourLesUtilisateurs;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class SessionTest {

    @Test
    public void creation() {
        Session session = new Session(AidePourLesSessions.TITRE_DE_LA_SESSION, AidePourLesSessions.DESCRIPTION_DE_LA_SESSION, AidePourLesUtilisateurs.UTILISATEUR);

        assertThat(session.getTitre(), is(AidePourLesSessions.TITRE_DE_LA_SESSION));
        assertThat(session.getDescription(), is(AidePourLesSessions.DESCRIPTION_DE_LA_SESSION));
        assertThat(session.getOrateur(), is(AidePourLesUtilisateurs.UTILISATEUR));
    }

}
