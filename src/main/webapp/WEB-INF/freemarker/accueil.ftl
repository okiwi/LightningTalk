<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="description" content="Le site de l agile tour bordeaux propulsé par okiwi">
<meta name="author" content="okiwi">
<link rel="icon" type="image/png" href="http://agiletourbordeaux.okiwi.org/img/okiwi_thumb.png" />
<title>Application permettant de proposer et de voter pour les lightnings talks de l'agile tour 2012 - #atbdx</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="http://agiletourbordeaux.okiwi.org/css/style.css" rel="stylesheet" type="text/css">
<link href="<@spring.url '/ressources/css/bootstrap.css'/>" rel="stylesheet" type="text/css">
<link href="<@spring.url '/ressources/css/bootstrap-responsive.css'/>" rel="stylesheet">
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
			  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
			<![endif]-->

</head>

<body>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="brand" href="index.html">Agile Tour Bordeaux</a>
				<div id="divPourAfficherUtilisateurSousMobile">
					<!-- Div pour afficher l'utilisateur sous mobile -->
				</div>
				<div class="btn-group pull-right">
					<a href="http://www.okiwi.org/"><img src="http://agiletourbordeaux.okiwi.org/img/okiwi_thumb.png" width="28px" height: 28px; alt="Logo Okiwi"></a>
				</div>
				<div class="nav-collapse">
					<ul class="nav">
						<li><a href="inscription.html">Inscription</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Jour J <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="http://agiletourbordeaux.okiwi.org/comment-convaincre.html">Pourquoi venir?</a></li>
								<li><a href="http://agiletourbordeaux.okiwi.org/programmation.html">Programmation</a></li>
								<li><a href="http://agiletourbordeaux.okiwi.org/accomodations.html">Accomodations</a></li>
							</ul></li>
						<li><a href="http://agiletourbordeaux.okiwi.org/organisateurs.html">L'équipe</a></li>
						<li><a href="http://agiletourbordeaux.okiwi.org/sponsors.html">Sponsors</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<#if erreur??>
		<div class="row-fluid">
			<div class="span12 alert alert-block alert-error fade in">
				<button class="close" data-dismiss="alert" type="button">×</button>
				<h4 class="alert-heading">Une erreur est survenue</h4>
				<p>${erreur}</p>
			</div>
		</div>
		</#if>
		<div class="row-fluid">
			<div class="span3 hidden-phone">
				<div class="well" id="divPourAfficherUtilisateur">
					<!-- Div pour afficher l'utilisateur -->
				</div>
			</div>
			<div class="span9" id="divPourAfficherLesSessions">
				<!-- Div pour afficher les sessions -->
			</div>
		</div>


		<div class="row footer" style="text-decoration: none;">
			<small> L'Agile Tour Bordeaux est un évènement propulsé par <a href="http://okiwi.org">Okiwi</a> <br> <a href="mailto:contact@okiwi.org"><span
					class="label label-info">feedback</span></a>
			</small>
		</div>

	</div>
	<div id="conteneurModalDeCreationEtDeMiseAJourDUneSession"></div>

	<script src="<@spring.url '/ressources/js/jquery.js'/>"></script>
	<script src="<@spring.url '/ressources/js/bootstrap.js'/>"></script>
	<script src="<@spring.url '/ressources/js/ICanHaz.js'/>"></script>
	<script src="<@spring.url '/ressources/js/ICanHaz-utils.js'/>"></script>


	<script type="text/javascript">
        function recupererLesSessions() {
            $('#divPourAfficherLesSessions').html('');
            $.getJSON('<@spring.url 'sessions'/>', function(sessions) {
                $.each(sessions, function(index, session) {
                    $('#divPourAfficherLesSessions').append(ich.templateDeSession(session));
                });
            });
        }

        function recupererUtilisateurCourant() {
            $.getJSON('<@spring.url 'utilisateurs/courant'/>', function(utilisateur) {
                $('#divPourAfficherUtilisateur').html(ich.templateUtilisateur(utilisateur));
                $('#divPourAfficherUtilisateurSousMobile').html(ich.templateUtilisateurSousMobile(utilisateur));
            });
        }

        function deconnexion() {
            $.getJSON('authentification/deconnexion', function() {
                afficherUtilisateur(null);
            });
            recupererLesSessions();
        }

        function voter(titreEncodePourLURL) {
            $.ajax({
                type : 'POST',
                url : '<@spring.url 'sessions'/>/' + titreEncodePourLURL + '/votants',
                success : function() {
                    recupererLesSessions();
                }
            });
        }
        function enleverMonVote(titreEncodePourLURL) {
            $.ajax({
                headers : {
                    'X-HTTP-Method-Override' : 'DELETE',
                },
                type : 'GET',
                url : '<@spring.url 'sessions'/>/' + titreEncodePourLURL + '/votants',
                success : function() {
                    recupererLesSessions();
                }
            });
        }

        function supprimerSession(titre, titreEncodePourLURL) {
            if (confirm("Voulez vraiment supprimer la session " + titre))
                $.ajax({
                    headers : {
                        'X-HTTP-Method-Override' : 'DELETE',
                    },
                    type : 'GET',
                    url : '<@spring.url 'sessions'/>/' + titreEncodePourLURL,
                    success : function() {
                        recupererLesSessions();
                    }
                });
        }

        function afficherModalDeMiseAJourDUneSession(titreEncodePourLURL, titre, description) {
            var vue = {
                modal : {
                    titre : "Mise à jour",
                    action : "Mettre à jour"
                },
                session : {
                    titre : titre,
                    description : description
                }
            }
            $('#conteneurModalDeCreationEtDeMiseAJourDUneSession').html(ich.templateModalDeCreationEtDeMiseAJourDUneSession(vue));
            $('#sauvegarderSession').bind('click', function() {
                $.ajax({
                    type : 'POST',
                    url : '<@spring.url 'sessions'/>/' + titreEncodePourLURL,
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
                    url : '<@spring.url 'sessions'/>',
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

        $(document).ready(function() {
            chargerTemplatesExternes('<@spring.url '/ressources/templates/'/>',[ "utilisateur", "session", "erreur" ]);
            recupererUtilisateurCourant();
            recupererLesSessions();

        });

    </script>

	<script type="text/javascript">
        var _gaq = _gaq || [];
        _gaq.push([ '_setAccount', 'UA-33933906-1' ]);
        _gaq.push([ '_trackPageview' ]);

        (function() {
            var ga = document.createElement('script');
            ga.type = 'text/javascript';
            ga.async = true;
            ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
            var s = document.getElementsByTagName('script')[0];
            s.parentNode.insertBefore(ga, s);
        })();
    </script>
</body>
</html>
