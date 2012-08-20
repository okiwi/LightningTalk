package fr.atbdx.lightningtalk.tests.domaine;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.ImpossibleDeCreerUneSession;
import fr.atbdx.lightningtalk.domaine.Session;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesParticipants;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesSessions;

public class SessionTest {

    private Session session;

    @Before
    public void avantLesTests() throws ImpossibleDeCreerUneSession {
        session = new Session(AidePourLesSessions.TITRE_DE_LA_SESSION, AidePourLesSessions.DESCRIPTION_DE_LA_SESSION, AidePourLesParticipants.PARTICIPANT);
    }

    @Test
    public void peutCreerUneSession() {

        assertThat(session.getTitre(), is(AidePourLesSessions.TITRE_DE_LA_SESSION));
        assertThat(session.getDescription(), is(AidePourLesSessions.DESCRIPTION_DE_LA_SESSION));
        assertThat(session.getOrateur(), is(AidePourLesParticipants.PARTICIPANT));
        assertThat(session.getNombreDeVotes(), is(0));
        assertThat(session.getVotants().iterator().hasNext(), is(false));
    }

    @Test
    public void creerUneSessionAvecUnTitreNulleRetourneUneException() {
        try {
            new Session(null, null, AidePourLesParticipants.PARTICIPANT);
            fail("creer une session sans un titre retourne Une exception");
        } catch (ImpossibleDeCreerUneSession exception) {
            assertThat(exception.getMessage(), is("Veuillez entrer un titre pour créer une session."));
        }
    }

    @Test
    public void creerUneSessionAvecUnTitreVideRetourneUneException() {
        try {
            new Session(StringUtils.EMPTY, null, AidePourLesParticipants.PARTICIPANT);
            fail("creer une session sans un titre retourne Une exception");
        } catch (ImpossibleDeCreerUneSession exception) {
            assertThat(exception.getMessage(), is("Veuillez entrer un titre pour créer une session."));
        }
    }

    @Test
    public void supprimeLesEspacesAvantEtApresLeTitre() throws ImpossibleDeCreerUneSession {
        session = new Session(" " + AidePourLesSessions.TITRE_DE_LA_SESSION + " ", AidePourLesSessions.DESCRIPTION_DE_LA_SESSION, AidePourLesParticipants.PARTICIPANT);

        AidePourLesSessions.verifier(session);
    }

    @Test
    public void peutAjouterUnVote() {
        session.ajouterUnVote(AidePourLesParticipants.PARTICIPANT);

        assertThat(session.getNombreDeVotes(), is(1));
        assertThat(session.getVotants().iterator().next(), is(AidePourLesParticipants.PARTICIPANT));
    }

    @Test
    public void peutSupprimerUnVote() {
        session.ajouterUnVote(AidePourLesParticipants.PARTICIPANT);

        session.supprimerUnVote(AidePourLesParticipants.PARTICIPANT);

        assertThat(session.getNombreDeVotes(), is(0));
        assertThat(session.getVotants().iterator().hasNext(), is(false));
    }

    @Test
    public void peutVoterSiNaPasDejaVote() {
        boolean peutVoter = session.peutVoter(AidePourLesParticipants.PARTICIPANT);

        assertThat(peutVoter, is(true));

    }

    @Test
    public void nePeutPasVoterSiADejaVote() {
        session.ajouterUnVote(AidePourLesParticipants.PARTICIPANT);

        boolean peutVoter = session.peutVoter(AidePourLesParticipants.PARTICIPANT);

        assertThat(peutVoter, is(false));
    }

    @Test
    public void neFaitRienSiUnUtilisateurVotePourUneSessionDejaVote() {
        session.ajouterUnVote(AidePourLesParticipants.PARTICIPANT);

        session.ajouterUnVote(AidePourLesParticipants.PARTICIPANT);

        assertThat(session.getNombreDeVotes(), is(1));
    }

    @Test
    public void neFaisRienSiUnUtilisateurSupprimeUnVoteOuIlNAPasVote() {
        session.supprimerUnVote(AidePourLesParticipants.PARTICIPANT);

        assertThat(session.getNombreDeVotes(), is(0));
    }

    @Test
    public void nePeutPasVoterSiUtilisateurNull() {
        session.ajouterUnVote(AidePourLesParticipants.PARTICIPANT);

        boolean peutVoter = session.peutVoter(null);

        assertThat(peutVoter, is(false));
    }
}
