package fr.atbdx.lightningtalk.domaine;

import java.io.IOException;

public interface EntrepotUtilisateur {

    String recupererLURLDuServiceDAuthentificationExterne();

    void authentifier(String code, String codeErreur) throws IOException;

    Utilisateur recupererUtilisateurCourant() throws IOException;

}
