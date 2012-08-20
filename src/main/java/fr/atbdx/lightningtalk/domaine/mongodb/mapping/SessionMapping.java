package fr.atbdx.lightningtalk.domaine.mongodb.mapping;

import fr.atbdx.lightningtalk.domaine.Session;
import org.mongolink.domain.mapper.EntityMap;

@SuppressWarnings("UnusedDeclaration")
public class SessionMapping extends EntityMap<Session> {

    public SessionMapping() {
        super(Session.class);
    }

    @Override
    public void map() {
        id(element().getId()).auto();
        property(element().getTitre());
        property(element().getDescription());
        property(element().getOrateur());
    }
}
