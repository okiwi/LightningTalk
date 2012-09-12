package fr.atbdx.lightningtalk.tests.domaine;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.ImpossibleDeSAuthentifier;

public class ImpossibleDeSAuthentifierTest {

    @Test
    public void peutCreerLException() {
        ImpossibleDeSAuthentifier exception = new ImpossibleDeSAuthentifier();

        assertThat(exception.getMessage(), is(ImpossibleDeSAuthentifier.MESSAGE_D_ERREUR));
    }

    @Test
    public void peutCreerLExceptionAvecUneCause() {
        Throwable cause = new IOException();
        ImpossibleDeSAuthentifier exception = new ImpossibleDeSAuthentifier(cause);

        assertThat(exception.getMessage(), is(ImpossibleDeSAuthentifier.MESSAGE_D_ERREUR));
        assertThat(exception.getCause(), is(cause));
    }

}
