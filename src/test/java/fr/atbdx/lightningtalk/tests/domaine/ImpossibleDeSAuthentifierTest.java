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

        assertThat(
                exception.getMessage(),
                is("Un problème est survenue durant l'authentification. Pour Utiliser cette application, vous devez avoir configurer un compte google + et authoriser l'application savoir qui vous êtes sur Google."));
    }

    @Test
    public void peutCreerLExceptionAvecUneCause() {
        Throwable cause = new IOException();
        ImpossibleDeSAuthentifier exception = new ImpossibleDeSAuthentifier(cause);

        assertThat(
                exception.getMessage(),
                is("Un problème est survenue durant l'authentification. Pour Utiliser cette application, vous devez avoir configurer un compte google + et authoriser l'application savoir qui vous êtes sur Google."));
        assertThat(exception.getCause(), is(cause));
    }

}
