package fr.atbdx.lightningtalk.tests.domaine;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.ImpossibleDeCreerUneSession;
import fr.atbdx.lightningtalk.domaine.Session;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesSessions;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesUtilisateurs;

public class SessionTest {

    private Session session;

    @Before
    public void avantLesTests() throws ImpossibleDeCreerUneSession {
        session = new Session(AidePourLesSessions.TITRE_DE_LA_SESSION, AidePourLesSessions.DESCRIPTION_DE_LA_SESSION, AidePourLesUtilisateurs.UTILISATEUR);
    }

    @Test
    public void peutCreerUneSession() {

        assertThat(session.getTitre(), is(AidePourLesSessions.TITRE_DE_LA_SESSION));
        assertThat(session.getDescription(), is(AidePourLesSessions.DESCRIPTION_DE_LA_SESSION));
        assertThat(session.getOrateur(), is(AidePourLesUtilisateurs.ID));
        assertThat(session.getNombreDeVotes(), is(0));
        assertThat(session.getVotants().iterator().hasNext(), is(false));
    }

    @Test
    public void creerUneSessionAvecUnTitreNulleRetourneUneException() {
        try {
            new Session(null, null, AidePourLesUtilisateurs.UTILISATEUR);
            fail("creer une session sans un titre retourne Une exception");
        } catch (ImpossibleDeCreerUneSession exception) {
            assertThat(exception.getMessage(), is("Veuillez entrer un titre pour créer une session."));
        }
    }

    @Test
    public void creerUnSessionSansOrateurRetourneUneExcpetion() {
        try {
            new Session(AidePourLesSessions.TITRE_DE_LA_SESSION, null, null);
            fail("creer une session sans orateur retourne Une exception");
        } catch (ImpossibleDeCreerUneSession exception) {
            assertThat(exception.getMessage(), is("Veuillez vous connecter pour créer une session."));
        }
    }

    @Test
    public void creerUneSessionAvecUnTitreVideRetourneUneException() {
        try {
            new Session(StringUtils.EMPTY, null, AidePourLesUtilisateurs.UTILISATEUR);
            fail("creer une session sans un titre retourne Une exception");
        } catch (ImpossibleDeCreerUneSession exception) {
            assertThat(exception.getMessage(), is("Veuillez entrer un titre pour créer une session."));
        }
    }

    @Test
    public void supprimeLesEspacesAvantEtApresLeTitre() throws ImpossibleDeCreerUneSession {
        session = new Session(" " + AidePourLesSessions.TITRE_DE_LA_SESSION + " ", AidePourLesSessions.DESCRIPTION_DE_LA_SESSION, AidePourLesUtilisateurs.UTILISATEUR);

        AidePourLesSessions.verifier(session);
    }

    @Test
    public void peutAjouterUnVote() {
        session.ajouterUnVote(AidePourLesUtilisateurs.UTILISATEUR);

        assertThat(session.getNombreDeVotes(), is(1));
        assertThat(session.getVotants().iterator().next(), is(AidePourLesUtilisateurs.ID));
    }

    @Test
    public void peutSupprimerUnVote() {
        session.ajouterUnVote(AidePourLesUtilisateurs.UTILISATEUR);

        session.supprimerUnVote(AidePourLesUtilisateurs.UTILISATEUR);

        assertThat(session.getNombreDeVotes(), is(0));
        assertThat(session.getVotants().iterator().hasNext(), is(false));
    }

    @Test
    public void peutVoterSiNaPasDejaVote() {
        boolean peutVoter = session.peutVoter(AidePourLesUtilisateurs.UTILISATEUR);

        assertThat(peutVoter, is(true));

    }

    @Test
    public void nePeutPasVoterSiADejaVote() {
        session.ajouterUnVote(AidePourLesUtilisateurs.UTILISATEUR);

        boolean peutVoter = session.peutVoter(AidePourLesUtilisateurs.UTILISATEUR);

        assertThat(peutVoter, is(false));
    }

    @Test
    public void neFaitRienSiUnUtilisateurVotePourUneSessionDejaVote() {
        session.ajouterUnVote(AidePourLesUtilisateurs.UTILISATEUR);

        session.ajouterUnVote(AidePourLesUtilisateurs.UTILISATEUR);

        assertThat(session.getNombreDeVotes(), is(1));
    }

    @Test
    public void neFaisRienSiUnUtilisateurSupprimeUnVoteOuIlNAPasVote() {
        session.supprimerUnVote(AidePourLesUtilisateurs.UTILISATEUR);

        assertThat(session.getNombreDeVotes(), is(0));
    }

    @Test
    public void nePeutPasVoterSiUtilisateurNull() {

        boolean peutVoter = session.peutVoter(null);

        assertThat(peutVoter, is(false));
    }

    @Test
    public void nePeutPasAjouterUnVoteSiUtilisateurNull() {
        session.ajouterUnVote(null);

        assertThat(session.getNombreDeVotes(), is(0));
    }

    @Test
    public void nePeutPasSupprimerUnVoteSiUtilisateurNull() {
        session.ajouterUnVote(AidePourLesUtilisateurs.UTILISATEUR);

        session.supprimerUnVote(null);

        assertThat(session.getNombreDeVotes(), is(1));
    }
}
