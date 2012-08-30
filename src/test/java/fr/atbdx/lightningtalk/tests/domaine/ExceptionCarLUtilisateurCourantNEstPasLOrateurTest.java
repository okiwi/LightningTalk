package fr.atbdx.lightningtalk.tests.domaine;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import fr.atbdx.lightningtalk.domaine.OperationPermiseUniquementALOrateur;

public class ExceptionCarLUtilisateurCourantNEstPasLOrateurTest {

    @Test
    public void peutCreerLException() {
        OperationPermiseUniquementALOrateur exception = new OperationPermiseUniquementALOrateur();

        assertThat(exception.getMessage(), is("Seul l'orateur peut effecuter cette opération."));
    }

}
