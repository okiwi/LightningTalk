package fr.atbdx.lightningtalk.domaine;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

public class Session {

    private String titre;
    private String orateur;
    private String description;
    protected List<String> votants = Lists.newArrayList();

    public Session(String titre, String description, Utilisateur orateur) throws ImpossibleDeCreerUneSession {
        if (StringUtils.isBlank(titre)) {
            throw new ImpossibleDeCreerUneSession("Veuillez entrer un titre pour créer une session.");
        }
        if (orateur == null) {
            throw new ImpossibleDeCreerUneSession("Veuillez vous connecter pour créer une session.");
        }
        this.titre = StringUtils.trim(titre);
        this.orateur = orateur.getId();
        this.description = description;
    }

    protected Session() {

    }

    public String getTitre() {
        return titre;
    }

    public String getOrateur() {
        return orateur;
    }

    public String getDescription() {
        return description;
    }

    public boolean peutAjouterUnVote(Utilisateur votant) {
        return votantNonNull(votant) && !votants.contains(votant.getId());
    }

    public boolean peutSupprimerUnVote(Utilisateur votant) {
        return votantNonNull(votant) && votants.contains(votant.getId());
    }

    private boolean votantNonNull(Utilisateur votant) {
        return votant != null;
    }

    public Iterable<String> getVotants() {
        return votants;
    }

    public int getNombreDeVotes() {
        return votants.size();
    }

    public void ajouterUnVote(Utilisateur votant) {
        if (peutAjouterUnVote(votant)) {
            votants.add(votant.getId());
        }
    }

    public void supprimerUnVote(Utilisateur votant) {
        if (peutSupprimerUnVote(votant)) {
            votants.remove(votant.getId());
        }
    }

    public boolean peutSupprimerOuEditer(Utilisateur utilisateur) {
        return utilisateur != null && utilisateur.getId().equals(orateur);
    }

}
