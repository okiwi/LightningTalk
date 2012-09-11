        
        function recupererLesSessions() {
            $('#divPourAfficherLesSessions').html('');
            $.getJSON('sessions', function(sessions) {
                $.each(sessions, function(index, session) {
                    $('#divPourAfficherLesSessions').append(ich.templateSession(session));
                });
            });
        }

        function voter(titreEncodePourJavascript) {
            $.ajax({
                type : 'POST',
                url : 'sessions/' + titreEncodePourJavascript + '/votants',
                success : function() {
                    recupererLesSessions();
                }
            });
        }
        function enleverMonVote(titreEncodePourJavascript) {
            $.ajax({
                headers : {
                    'X-HTTP-Method-Override' : 'DELETE',
                },
                type : 'GET',
                url : 'sessions/' + titreEncodePourJavascript + '/votants',
                success : function() {
                    recupererLesSessions();
                }
            });
        }

        function supprimerSession(titreEncodePourJavascript) {
            if (confirm("Voulez vraiment supprimer la session " + titreEncodePourJavascript))
                $.ajax({
                    headers : {
                        'X-HTTP-Method-Override' : 'DELETE',
                    },
                    type : 'GET',
                    url : 'sessions/' + titreEncodePourJavascript,
                    success : function() {
                        recupererLesSessions();
                    }
                });
        }

        function afficherModalDeMiseAJourDUneSession(titreEncodePourJavascript, descriptionEncodePourJavascript) {
            var vue = {
                modal : {
                    titre : "Mise à jour",
                    action : "Mettre à jour"
                },
                session : {
                    titre : titreEncodePourJavascript,
                    description : descriptionEncodePourJavascript
                }
            }
            $('#conteneurModalDeCreationEtDeMiseAJourDUneSession').html(ich.templateModalDeCreationEtDeMiseAJourDUneSession(vue));
            $('#sauvegarderSession').bind('click', function() {
                $.ajax({
                    type : 'POST',
                    url : 'sessions/' + titreEncodePourJavascript,
                    data : $('#formulaireDeCreationEtDeMiseAJourDUneSession').serializeArray(),
                    success : function() {
                        $('#modalDeCreationEtDeMiseAJourDUneSession').modal('hide');
                        recupererLesSessions();
                    },
                    error : function(data) {
                        var erreur = {
                            titre : "Sauvegarde impossible",
                            message : data.responseText
                        };
                        $('#conteneurDErreurPourLaCreationEtLaMiseAJourDUneSession').html(ich.templateErreur(erreur));
                    }
                });
            });
            $('#modalDeCreationEtDeMiseAJourDUneSession').modal();
        }

        function afficherModalDeCreationDUneSession() {
            var vue = {
                modal : {
                    titre : "Création",
                    action : "Créer"
                },
                session : {
                    titre : "",
                    description : ""
                }
            }
            $('#conteneurModalDeCreationEtDeMiseAJourDUneSession').html(ich.templateModalDeCreationEtDeMiseAJourDUneSession(vue));
            $('#sauvegarderSession').bind('click', function() {
                $.ajax({
                    type : 'POST',
                    url : 'sessions/',
                    data : $('#formulaireDeCreationEtDeMiseAJourDUneSession').serializeArray(),
                    success : function() {
                        $('#modalDeCreationEtDeMiseAJourDUneSession').modal('hide');
                        recupererLesSessions();
                    },
                    error : function(data) {
                        var erreur = {
                            titre : "Création impossible",
                            message : data.responseText
                        };
                        $('#conteneurDErreurPourLaCreationEtLaMiseAJourDUneSession').html(ich.templateErreur(erreur));
                    }
                });
            });
            $('#modalDeCreationEtDeMiseAJourDUneSession').modal();
        }