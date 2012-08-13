package fr.atbdx.lightningtalk.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.EntrepotUtilisateur;
import fr.atbdx.lightningtalk.domaine.google.EntrepotUtilisateurGoogle;

public class ControlleurAuthentificationTest {

    @Test
    public void demanderAuthentificationExterne() {
        EntrepotUtilisateur entrepotUtilisateur = mock(EntrepotUtilisateur.class);
        when(entrepotUtilisateur.recupererLURLDuServiceDAuthentificationExterne()).thenReturn("urlExterne");
        ControlleurAuthentification controlleurAuthentification = new ControlleurAuthentification(entrepotUtilisateur);

        String urlDAuthentificationExterne = controlleurAuthentification.demanderAuthentificationExterne();

        verify(entrepotUtilisateur);
        assertThat(urlDAuthentificationExterne, is("redirect:urlExterne"));
    }

}
