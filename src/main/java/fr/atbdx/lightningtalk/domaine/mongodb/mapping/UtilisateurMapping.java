package fr.atbdx.lightningtalk.domaine.mongodb.mapping;

import fr.atbdx.lightningtalk.domaine.Utilisateur;
import org.mongolink.domain.mapper.ComponentMap;

@SuppressWarnings("UnusedDeclaration")
public class UtilisateurMapping extends ComponentMap<Utilisateur> {

    public UtilisateurMapping() {
        super(Utilisateur.class);
    }

    @Override
    protected void map() {
        property(element().getNomAffiche());
        property(element().getId());
    }
}
