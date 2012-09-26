function recupererUtilisateurCourant() {
    $.getJSON('utilisateurs/courant', function(utilisateur) {
        $('#divPourAfficherUtilisateur').html(ich.templateUtilisateur(utilisateur));
        $('#explicationsConnectionEtProposer').html(ich.templateExplicationsConnectionEtProposer(utilisateur));
    });
}

function deconnexion() {
    $.getJSON('authentification/deconnexion', function() {
    	recupererUtilisateurCourant();
    	recupererLesSessions();
    });
}