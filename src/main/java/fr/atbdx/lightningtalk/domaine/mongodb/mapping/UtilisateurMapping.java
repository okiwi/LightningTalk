package fr.atbdx.lightningtalk.domaine.mongodb.mapping;

import org.mongolink.domain.mapper.EntityMap;

import fr.atbdx.lightningtalk.domaine.Utilisateur;

public class UtilisateurMapping extends EntityMap<Utilisateur> {

    public UtilisateurMapping() {
        super(Utilisateur.class);
    }

    @Override
    protected void map() {
        id(element().getId()).natural();
        property(element().getNomAffiche());
        property(element().getUrlImage());
        property(element().getUrlProfil());
    }
}
