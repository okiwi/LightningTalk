package fr.atbdx.lightningtalk.tests.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.EntrepotUtilisateur;
import fr.atbdx.lightningtalk.domaine.google.EntrepotUtilisateurGoogle;
import fr.atbdx.lightningtalk.web.ControlleurAuthentification;

public class ControlleurAuthentificationTest {

    @Test
    public void demanderAuthentificationExterne() {
        EntrepotUtilisateur entrepotUtilisateur = mock(EntrepotUtilisateur.class);
        when(entrepotUtilisateur.recupererLURLDuServiceDAuthentificationExterne()).thenReturn("urlExterne");
        ControlleurAuthentification controlleurAuthentification = new ControlleurAuthentification(entrepotUtilisateur);

        String urlDAuthentificationExterne = controlleurAuthentification.demanderAuthentificationExterne();

        verify(entrepotUtilisateur).recupererLURLDuServiceDAuthentificationExterne();
        assertThat(urlDAuthentificationExterne, is("redirect:urlExterne"));
    }

    @Test
    public void authentification() throws IOException {
        EntrepotUtilisateur entrepotUtilisateur = mock(EntrepotUtilisateur.class);
        ControlleurAuthentification controlleurAuthentification = new ControlleurAuthentification(entrepotUtilisateur);

        String urlDAuthentificationExterne = controlleurAuthentification.authentification("code", "codeErreur");

        verify(entrepotUtilisateur).authentifier("code", "codeErreur");
        assertThat(urlDAuthentificationExterne, is("redirect:accueil"));
    }

}
