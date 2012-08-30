package fr.atbdx.lightningtalk.domaine;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;


public class Session {

    private String titre;
    private String orateur;
    private String description;
    protected List<String> votants = Lists.newArrayList();

    private String id;

    public String getId() {
        return id;
    }

    public Session(String titre, String description, Utilisateur orateur) throws ImpossibleDeCreerUneSession {
        if (StringUtils.isBlank(titre)) {
            throw new ImpossibleDeCreerUneSession("Veuillez entrer le titre de la session.");
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

    public boolean estOrateur(Utilisateur utilisateur) {
        return utilisateur != null && utilisateur.getId().equals(orateur);
    }

    private void verifierSiEstOrateur(Utilisateur utilisateurCourant) throws ImpossibleDeMettreAJourLaSession {
        if (!estOrateur(utilisateurCourant)) {
            throw new ImpossibleDeMettreAJourLaSession("Veuillez vous connecter avec le compte qui vous a permis de créer la session pour la mettre à jour.");
        }
    }

    public void mettreAJourDescription(String nouvelleDescription, Utilisateur utilisateurCourant) throws ImpossibleDeMettreAJourLaSession {
        verifierSiEstOrateur(utilisateurCourant);
        description = nouvelleDescription;
    }

    public Session clonerAvecUnNouveauTitre(String nouveauTitre, Utilisateur utilisateurCourant) throws ImpossibleDeCreerUneSession, ImpossibleDeMettreAJourLaSession {
        verifierSiEstOrateur(utilisateurCourant);
        Session session = new Session(nouveauTitre, description, utilisateurCourant);
        session.votants.addAll(this.votants);
        return session;

    }
}
