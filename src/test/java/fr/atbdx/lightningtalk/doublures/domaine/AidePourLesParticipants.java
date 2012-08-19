package fr.atbdx.lightningtalk.doublures.domaine;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.StringUtils;

import fr.atbdx.lightningtalk.domaine.Participant;

public class AidePourLesParticipants {

    public static final String ID = "id";
    public static final String NOM_AFFICHE = "nomAffiche";

    public static final Participant PARTICIPANT = creerAvecSuffixe(StringUtils.EMPTY);

    public static void verifier(Participant participant) {
        verifierAvecSuffixe(participant, StringUtils.EMPTY);
    }

    public static void verifierAvecSuffixe(Participant participant, String suffixe) {
        assertThat(participant, notNullValue());
        assertThat(participant.getId(), is(AidePourLesParticipants.ID + suffixe));
        assertThat(participant.getNomAffiche(), is(AidePourLesParticipants.NOM_AFFICHE + suffixe));
    }

    public static Participant creerAvecSuffixe(String suffixe) {
        return new Participant(ID + suffixe, NOM_AFFICHE + suffixe);
    }

}
