package fr.atbdx.lightningtalk.domaine.mongodb;

import java.util.List;

import org.mongolink.MongoSession;
import org.mongolink.MongoSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

import fr.atbdx.lightningtalk.domaine.EntrepotSession;
import fr.atbdx.lightningtalk.domaine.ImpossibleDeCreerUneSession;
import fr.atbdx.lightningtalk.domaine.OperationPermiseUniquementALOrateur;
import fr.atbdx.lightningtalk.domaine.Session;
import fr.atbdx.lightningtalk.domaine.Utilisateur;

@Repository
public class EntrepotSessionMongo extends EntrepotMongo implements EntrepotSession {

    private static final Function<Session, Integer> ORDONNER_PAR_NOMBRE_DE_VOTE = new Function<Session, Integer>() {
        public Integer apply(Session session) {
            return session.getNombreDeVotes();
        }
    };

    @Autowired
    public EntrepotSessionMongo(MongoSessionManager sessionManager) {
        super(sessionManager);
    }

    public EntrepotSessionMongo(final MongoSession sessionMongo) {
        super(sessionMongo);
    }

    public void creer(Session session) throws ImpossibleDeCreerUneSession {
        if (sessionMongo.get(session.getTitre(), Session.class) != null) {
            throw new ImpossibleDeCreerUneSession("Une session avec le même titre existe déjà.");
        }
        sessionMongo.save(session);
    }

    public List<Session> recupererLesSessions() {
        return Ordering.natural().reverse().onResultOf(ORDONNER_PAR_NOMBRE_DE_VOTE).sortedCopy(sessionMongo.getAll(Session.class));
    }

    public Session recuperer(String titreDeLaSession) {
        return sessionMongo.get(titreDeLaSession, Session.class);
    }

    public void mettreAJour(Session sessionAMettreAJour) {
        sessionMongo.update(sessionAMettreAJour);
    }

    @Override
    public void supprimer(Session session, Utilisateur utilisateurCourant) throws OperationPermiseUniquementALOrateur {
        if (session != null) {
            session.verifierSiEstOrateur(utilisateurCourant);
            sessionMongo.delete(session);
        }
    }

}
