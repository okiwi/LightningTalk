package fr.atbdx.lightningtalk.domaine.mongodb.mapping;

import org.mongolink.domain.mapper.EntityMap;

import fr.atbdx.lightningtalk.domaine.Session;

public class SessionMapping extends EntityMap<Session> {
    
    public SessionMapping() {
        super(Session.class);
    }
    
    @Override
    public void map() {
        id(element().getTitre()).natural();
        property(element().getDescription());
        property(element().getOrateur());
        property(element().getVotants());
    }
}
