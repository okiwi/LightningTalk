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
import fr.atbdx.lightningtalk.doublures.domaine.ImpossibleDeMettreAJourLaSession;

public class SessionTest {

    private Session session;

    @Before
    public void avantLesTests() throws ImpossibleDeCreerUneSession {
        session = new Session(AidePourLesSessions.TITRE, AidePourLesSessions.DESCRIPTION, AidePourLesUtilisateurs.UTILISATEUR);
    }

    @Test
    public void peutCreerUneSession() {

        assertThat(session.getTitre(), is(AidePourLesSessions.TITRE));
        assertThat(session.getDescription(), is(AidePourLesSessions.DESCRIPTION));
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
            assertThat(exception.getMessage(), is("Veuillez entrer le titre de la session."));
        }
    }

    @Test
    public void creerUnSessionSansOrateurRetourneUneExcpetion() {
        try {
            new Session(AidePourLesSessions.TITRE, null, null);
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
            assertThat(exception.getMessage(), is("Veuillez entrer le titre de la session."));
        }
    }

    @Test
    public void supprimeLesEspacesAvantEtApresLeTitre() throws ImpossibleDeCreerUneSession {
        session = new Session(" " + AidePourLesSessions.TITRE + " ", AidePourLesSessions.DESCRIPTION, AidePourLesUtilisateurs.UTILISATEUR);

        AidePourLesSessions.verifier(session);
    }

    @Test
    public void ajoutDUnVote() {
        session.ajouterUnVote(AidePourLesUtilisateurs.UTILISATEUR);

        assertThat(session.getNombreDeVotes(), is(1));
        assertThat(session.getVotants().iterator().next(), is(AidePourLesUtilisateurs.ID));
    }

    @Test
    public void supressionDUnVote() {
        session.ajouterUnVote(AidePourLesUtilisateurs.UTILISATEUR);

        session.supprimerUnVote(AidePourLesUtilisateurs.UTILISATEUR);

        assertThat(session.getNombreDeVotes(), is(0));
        assertThat(session.getVotants().iterator().hasNext(), is(false));
    }

    @Test
    public void peutAjouterUnVoteSiNAPasDejaVote() {
        boolean peutVoter = session.peutAjouterUnVote(AidePourLesUtilisateurs.UTILISATEUR);

        assertThat(peutVoter, is(true));

    }

    @Test
    public void nePeutPasAjouterUnVoteSiADejaVote() {
        session.ajouterUnVote(AidePourLesUtilisateurs.UTILISATEUR);

        boolean peutVoter = session.peutAjouterUnVote(AidePourLesUtilisateurs.UTILISATEUR);

        assertThat(peutVoter, is(false));
    }

    @Test
    public void nePeutPasAjouterUnVoteSiVotantNull() {

        boolean peutVoter = session.peutAjouterUnVote(null);

        assertThat(peutVoter, is(false));
    }

    @Test
    public void peutSupprimerUnVoteSiADejaVote() {
        session.ajouterUnVote(AidePourLesUtilisateurs.UTILISATEUR);

        boolean peutVoter = session.peutSupprimerUnVote(AidePourLesUtilisateurs.UTILISATEUR);

        assertThat(peutVoter, is(true));

    }

    @Test
    public void nePeutPasSupprimerUnVoteSiLeVotantNaPasDejaVote() {

        boolean peutVoter = session.peutSupprimerUnVote(AidePourLesUtilisateurs.UTILISATEUR);

        assertThat(peutVoter, is(false));
    }

    @Test
    public void nePeutPasSupprimerUnVoteSiVotantNull() {

        boolean peutVoter = session.peutSupprimerUnVote(null);

        assertThat(peutVoter, is(false));
    }

    @Test
    public void neFaitRienSiUnUtilisateurVotePourUneSessionDejaVote() {
        session.ajouterUnVote(AidePourLesUtilisateurs.UTILISATEUR);

        session.ajouterUnVote(AidePourLesUtilisateurs.UTILISATEUR);

        assertThat(session.getNombreDeVotes(), is(1));
    }

    @Test
    public void neRienFaireSiUnUtilisateurSupprimeUnVoteOuIlNAPasVote() {
        session.supprimerUnVote(AidePourLesUtilisateurs.UTILISATEUR);

        assertThat(session.getNombreDeVotes(), is(0));
    }

    @Test
    public void neRienFaireSiOnAjouteUnVoteAvecUnVotantNull() {
        session.ajouterUnVote(null);

        assertThat(session.getNombreDeVotes(), is(0));
    }

    @Test
    public void neRienFaireSiOnSupprimeUnVoteAvecUnVotantNull() {
        session.ajouterUnVote(AidePourLesUtilisateurs.UTILISATEUR);

        session.supprimerUnVote(null);

        assertThat(session.getNombreDeVotes(), is(1));
    }

    @Test
    public void estLOrateurSiLUtilisateurLOrateurDeLaSession() {
        boolean peutSupprimerOuEditer = session.estOrateur(AidePourLesUtilisateurs.UTILISATEUR);

        assertThat(peutSupprimerOuEditer, is(true));

    }

    @Test
    public void nEstPasOrateurSiLUtilisateurCourantNEstPasLOrateurDeLaSession() {
        boolean peutSupprimerOuEditer = session.estOrateur(AidePourLesUtilisateurs.UN_AUTRE_UTILISATEUR);

        assertThat(peutSupprimerOuEditer, is(false));
    }

    @Test
    public void nEstPasOrateurSiLUtilisateurCourantEstNull() {
        boolean peutSupprimerOuEditer = session.estOrateur(null);

        assertThat(peutSupprimerOuEditer, is(false));
    }

    @Test
    public void peutMettreAJourLaDescriptionSiLUtilisateurCourantEstLOrateur() throws ImpossibleDeMettreAJourLaSession {
        session.mettreAJourDescription(AidePourLesSessions.NOUVELLE_DESCRIPTION, AidePourLesUtilisateurs.UTILISATEUR);

        assertThat(session.getDescription(), is(AidePourLesSessions.NOUVELLE_DESCRIPTION));
    }

    @Test
    public void mettreAJourLaDescriptionAvecUnUtilisateurCourantQuiNestPasUnOrateurRenvoitUneException() {
        try {

            session.mettreAJourDescription(AidePourLesSessions.NOUVELLE_DESCRIPTION, AidePourLesUtilisateurs.UN_AUTRE_UTILISATEUR);
            fail("mettre a jour la description avec un utilisateur courant qui n'est pas un orateur retourne Une exception");
        } catch (ImpossibleDeMettreAJourLaSession exception) {
            assertThat(exception.getMessage(), is("Veuillez vous connecter avec le compte qui vous a permis de créer la session pour la mettre à jour."));
        }
    }

    @Test
    public void mettreAJourLaDescriptionAvecUnUtilisateurCourantNullRenvoitUneException() {
        try {

            session.mettreAJourDescription(AidePourLesSessions.NOUVELLE_DESCRIPTION, null);
            fail("mettre a jour la description avec un utilisateur courant qui n'est pas un orateur retourne Une exception");
        } catch (ImpossibleDeMettreAJourLaSession exception) {
            assertThat(exception.getMessage(), is("Veuillez vous connecter avec le compte qui vous a permis de créer la session pour la mettre à jour."));
        }
    }

    @Test
    public void peutClonerAvecUnNouveauTitreRecopieLaDescription() throws ImpossibleDeCreerUneSession, ImpossibleDeMettreAJourLaSession {
        Session sessionClonee = session.clonerAvecUnNouveauTitre(AidePourLesSessions.NOUVEAU_TITRE, AidePourLesUtilisateurs.UTILISATEUR);

        assertThat(sessionClonee.getTitre(), is(AidePourLesSessions.NOUVEAU_TITRE));
        assertThat(sessionClonee.getDescription(), is(AidePourLesSessions.DESCRIPTION));
        assertThat(sessionClonee.getOrateur(), is(AidePourLesUtilisateurs.UTILISATEUR.getId()));
    }

    @Test
    public void peutClonerAvecUnNouveauTitreRecopieLesVotes() throws ImpossibleDeCreerUneSession, ImpossibleDeMettreAJourLaSession {
        session.ajouterUnVote(AidePourLesUtilisateurs.UN_AUTRE_UTILISATEUR);

        Session sessionClonee = session.clonerAvecUnNouveauTitre(AidePourLesSessions.NOUVEAU_TITRE, AidePourLesUtilisateurs.UTILISATEUR);

        assertThat(sessionClonee.getVotants().iterator().next(), is(AidePourLesUtilisateurs.UN_AUTRE_UTILISATEUR.getId()));
    }

    @Test
    public void renvoitUneExceptionSiOnCloneAvecUnNouveauTitreMaisQueLUtilisateurCourantNestPasLOrateur() throws ImpossibleDeCreerUneSession {
        try {

            session.clonerAvecUnNouveauTitre(AidePourLesSessions.NOUVEAU_TITRE, AidePourLesUtilisateurs.UN_AUTRE_UTILISATEUR);
            fail("mettre a jour la description avec un utilisateur courant qui n'est pas un orateur retourne Une exception");
        } catch (ImpossibleDeMettreAJourLaSession exception) {
            assertThat(exception.getMessage(), is("Veuillez vous connecter avec le compte qui vous a permis de créer la session pour la mettre à jour."));
        }
    }

    @Test
    public void renvoitUneExceptionSiOnCloneAvecUnNouveauTitreMaisQueLUtilisateurCourantEstNull() throws ImpossibleDeCreerUneSession {
        try {
            session.clonerAvecUnNouveauTitre(AidePourLesSessions.NOUVEAU_TITRE, null);
            fail("mettre a jour la description avec un utilisateur courant qui n'est pas un orateur retourne Une exception");
        } catch (ImpossibleDeMettreAJourLaSession exception) {
            assertThat(exception.getMessage(), is("Veuillez vous connecter avec le compte qui vous a permis de créer la session pour la mettre à jour."));
        }
    }
}
