package fr.atbdx.lightningtalk.domaine;

public class OperationPermiseUniquementALOrateur extends Exception {

    private static final long serialVersionUID = 8272512401317155845L;

    public OperationPermiseUniquementALOrateur() {
        super("Seul l'orateur peut effecuter cette opération.");
    }

}
