package fr.atbdx.lightningtalk.domaine;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Participant {

    private final String id;
    private final String nomAffiche;

    public Participant(String id, String nomAffiche) {
        this.id = id;
        this.nomAffiche = nomAffiche;
    }

    public String getId() {
        return id;
    }

    public String getNomAffiche() {
        return nomAffiche;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Participant) {
            return ((Participant) obj).id.equals(id);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }
}
