package fr.atbdx.lightningtalk.tests.domaine;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.Participant;
import fr.atbdx.lightningtalk.doublures.domaine.AidePourLesParticipants;

public class ParticipantTest {

    @Test
    public void peutCreerUnParticipant() {

        Participant participant = new Participant(AidePourLesParticipants.ID, AidePourLesParticipants.NOM_AFFICHE);

        AidePourLesParticipants.verifier(participant);
    }

    @Test
    public void deuxParticipantsAvecLeMemeIdSontEgaux() {
        Participant participant = new Participant(AidePourLesParticipants.ID, null);
        Participant participant2 = new Participant(AidePourLesParticipants.ID, null);

        assertThat(participant, is(participant2));
    }

    @Test
    public void deuxParticipantsAvecDesIdDifferentsNeSontPasEgaux() {
        Participant participant = new Participant(AidePourLesParticipants.ID, null);
        Participant participant2 = new Participant("un autre id", null);

        assertThat(participant, not(participant2));
    }

    @Test
    public void deuxParticipantsAvecLeMemeIdOntLeMemeHashCode() {
        Participant participant = new Participant(AidePourLesParticipants.ID, null);
        Participant participant2 = new Participant(AidePourLesParticipants.ID, null);

        assertThat(participant.hashCode(), is(participant2.hashCode()));
    }

    @Test
    public void deuxParticipantsAvecDesIdDifferentsNOntPasLeMemeHashCode() {
        Participant participant = new Participant(AidePourLesParticipants.ID, null);
        Participant participant2 = new Participant("un autre id", null);

        assertThat(participant.hashCode(), not(participant2.hashCode()));
    }
}
