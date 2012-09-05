package fr.atbdx.lightningtalk.domaine;

public class ImpossibleDeSAuthentifier extends Exception {

    private static final long serialVersionUID = 3636689716241605495L;

    public ImpossibleDeSAuthentifier() {
        super("Un problème est survenue durant l'authentification. "
                + "Pour Utiliser cette application, vous devez avoir configurer un compte google + et authoriser l'application savoir qui vous êtes sur Google.");
    }
}
