package fr.atbdx.lightningtalk.tests.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.ImpossibleDeCreerUneSession;
import fr.atbdx.lightningtalk.domaine.Session;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesSessions;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesUtilisateurs;
import fr.atbdx.lightningtalk.doublures.domaine.FakeEntrepotUtilisateur;
import fr.atbdx.lightningtalk.web.SessionPourLaPresentation;

public class SessionPourLaPresentationTest {

    private Session session;
    private SessionPourLaPresentation sessionPourLaPresentation;
    private FakeEntrepotUtilisateur entrepotUtilisateur;

    @Before
    public void avantLesTests() {
        session = mock(Session.class);
        entrepotUtilisateur = new FakeEntrepotUtilisateur();
        sessionPourLaPresentation = new SessionPourLaPresentation(session, AidePourLesUtilisateurs.UTILISATEUR, entrepotUtilisateur);

    }

    @Test
    public void peutRecupererLeTitre() {
        when(session.getTitre()).thenReturn(AidePourLesSessions.TITRE);

        String titre = sessionPourLaPresentation.getTitre();

        verify(session).getTitre();
        assertThat(titre, is(AidePourLesSessions.TITRE));
    }

    @Test
    public void peutRecupererLaDescription() {
        when(session.getDescription()).thenReturn(AidePourLesSessions.DESCRIPTION);

        String description = sessionPourLaPresentation.getDescription();

        verify(session).getDescription();
        assertThat(description, is(AidePourLesSessions.DESCRIPTION));
    }

    @Test
    public void peutRecupererLOrateur() {
        when(session.getOrateur()).thenReturn(AidePourLesUtilisateurs.ID);
        entrepotUtilisateur.creer(AidePourLesUtilisateurs.UTILISATEUR);

        String orateur = sessionPourLaPresentation.getOrateur();

        assertThat(orateur, is(AidePourLesUtilisateurs.NOM_AFFICHE));
        assertThat(entrepotUtilisateur.idUtilisateurRecuperer, is(AidePourLesUtilisateurs.ID));
        verify(session).getOrateur();

    }

    @Test
    public void peutRecupererLeTitreEncodePourJavascript() throws UnsupportedEncodingException, ImpossibleDeCreerUneSession {
        when(session.getTitre()).thenReturn(AidePourLesSessions.TITRE);

        String titreEncodePourJavascript = sessionPourLaPresentation.getTitreEncodePourJavascript();

        assertThat(titreEncodePourJavascript, is(AidePourLesSessions.TITRE));
        verify(session).getTitre();
    }

    @Test
    public void encodeCaractereSpeciauxDansGetTitreEncodePourJavascript() throws UnsupportedEncodingException, ImpossibleDeCreerUneSession {
        when(session.getTitre()).thenReturn("un chtit' session");

        String titreEncodePourJavascript = sessionPourLaPresentation.getTitreEncodePourJavascript();

        assertThat(titreEncodePourJavascript, is("un chtit\\' session"));
        verify(session).getTitre();
    }

    @Test
    public void peutRecupererLeNombreDeVote() {
        when(session.getNombreDeVotes()).thenReturn(1);

        int nombreDeVotes = sessionPourLaPresentation.getNombreDeVotes();

        verify(session).getNombreDeVotes();
        assertThat(nombreDeVotes, is(1));
    }

    @Test
    public void peutRecuperIsPeutAjouterUnVote() {
        when(session.peutAjouterUnVote(AidePourLesUtilisateurs.UTILISATEUR)).thenReturn(true);

        boolean peutAjouterunVote = sessionPourLaPresentation.isPeutAjouterUnVote();

        assertThat(peutAjouterunVote, is(true));
        verify(session).peutAjouterUnVote(AidePourLesUtilisateurs.UTILISATEUR);
    }
    
    @Test
    public void peutRecuperIsPeutEnleverUnVote() {
        when(session.peutSupprimerUnVote(AidePourLesUtilisateurs.UTILISATEUR)).thenReturn(true);

        boolean peutAjouterunVote = sessionPourLaPresentation.isPeutSupprimerUnVote();

        assertThat(peutAjouterunVote, is(true));
        verify(session).peutSupprimerUnVote(AidePourLesUtilisateurs.UTILISATEUR);
    }
    
    @Test
    public void peutRecupererIsEstOrateur() {
        when(session.estOrateur(AidePourLesUtilisateurs.UTILISATEUR)).thenReturn(true);

        boolean estOrateur = sessionPourLaPresentation.isEstOrateur();

        assertThat(estOrateur, is(true));
        verify(session).estOrateur(AidePourLesUtilisateurs.UTILISATEUR);
    }

}
