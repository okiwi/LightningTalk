        function recupererUtilisateurCourant() {
            $.getJSON('utilisateurs/courant', function(utilisateur) {
                $('#divPourAfficherUtilisateur').html(ich.templateUtilisateur(utilisateur));
            });
        }

        function deconnexion() {
            $.getJSON('authentification/deconnexion', function() {
            	recupererUtilisateurCourant();
            	recupererLesSessions();
            });
        }