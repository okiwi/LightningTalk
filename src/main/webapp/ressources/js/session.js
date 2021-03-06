        
        function recupererLesSessions() {
            $('#divPourAfficherLesSessions').html('');
            $.getJSON('sessions', function(sessions) {
                $.each(sessions, function(index, session) {
                	var divDeLaSession = $(ich.templateSession(session));
                    $('#divPourAfficherLesSessions').append(divDeLaSession);
                    var ouvriActions = divDeLaSession.find(".ouvrirActions");
                    var actions = divDeLaSession.find(".actions");
                    var markdown = divDeLaSession.find(".markdown");
                    markdown.html(new Markdown.getSanitizingConverter().makeHtml(markdown.html()));
                        
                    divDeLaSession.hover(
                            function () {
                            	divDeLaSession.addClass("sessionAvecCadre");
                            	ouvriActions.addClass("hide");
                            	actions.removeClass("hide");
                              },
                              function () {
                            	  divDeLaSession.removeClass("sessionAvecCadre");
                            	  actions.addClass("hide");
                            	  ouvriActions.removeClass("hide");
                              }
                     );
                });
            });
        }

        function voter(titreEncodePourJavascript) {
            
            $.ajax({
                type : 'POST',
                url : 'sessions/' + encodeURIComponent(titreEncodePourJavascript) + '/votants',
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
                url : 'sessions/' + encodeURIComponent(titreEncodePourJavascript) + '/votants',
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
                    url : 'sessions/' + encodeURIComponent(titreEncodePourJavascript),
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
                    url : 'sessions/' + encodeURIComponent(titreEncodePourJavascript),
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
                    titre : "Proposition d'une session",
                    action : "Proposer"
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