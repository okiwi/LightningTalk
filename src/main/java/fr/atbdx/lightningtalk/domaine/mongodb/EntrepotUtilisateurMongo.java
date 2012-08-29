package fr.atbdx.lightningtalk.domaine.mongodb;

import org.mongolink.MongoSession;
import org.mongolink.MongoSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.atbdx.lightningtalk.domaine.EntrepotUtilisateur;
import fr.atbdx.lightningtalk.domaine.Utilisateur;

@Repository
public class EntrepotUtilisateurMongo extends EntrepotMongo implements EntrepotUtilisateur {

    @Autowired
    public EntrepotUtilisateurMongo(MongoSessionManager sessionManager) {
        super(sessionManager);
    }

    public EntrepotUtilisateurMongo(final MongoSession sessionMongo) {
        super(sessionMongo);
    }

    public void creer(Utilisateur utilisateur) {
        sessionMongo.save(utilisateur);
    }

    public Utilisateur recuperer(String id) {
        return sessionMongo.get(id, Utilisateur.class);
    }

    public void mettreAJour(Utilisateur utilisateur) {
        sessionMongo.update(utilisateur);
    }
}
