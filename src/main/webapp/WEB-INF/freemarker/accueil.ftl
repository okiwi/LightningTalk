<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="fr">
    <head>
        <title>Application permettant de proposer et de voter pour les lightnings talks de l'agile tour 2012 - #atbdx</title>

        <meta charset="utf-8" />
        <meta name="description" content="Le site de l'agile tour bordeaux propulsé par okiwi">
        <meta name="author" content="okiwi">
        <link rel="icon" type="image/png" href="http://okiwi.github.com/atbdx/img/okiwi_thumb.png" />

        <link rel="stylesheet" href="http://okiwi.github.com/atbdx/static/lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="http://okiwi.github.com/atbdx/static/lib/bootstrap/css/bootstrap-responsive.min.css">

        <link rel="stylesheet" href="http://okiwi.github.com/atbdx/static/css/style.css" />
        <link rel="stylesheet" href="http://okiwi.github.com/atbdx/static/lib/countdown/jquery.countdown.css">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>
    <body>
        <script src="http://okiwi.github.com/atbdx/static/lib/jquery/jquery.min.js"></script>
        <script src="http://okiwi.github.com/atbdx/static/lib/jquery/jquery.custom.min.js"></script>
		
	        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <i class="icon-bar"></i>
                        <i class="icon-bar"></i>
                        <i class="icon-bar"></i>
                    </a>

                    <div class="span4">
                        <h1><a href="http://okiwi.github.com/atbdx/index.html" title="Agile Tour Bordeaux 2012"><span>Agile Tour Bordeaux 2012</span></a></h1>
                    </div>

                    <div class="nav-collapse span8 noLeftMargin">
                        <ul class="nav">
                            <li><a href="http://okiwi.github.com/atbdx/presentation.html" class="presentation-link">Présentation</a></li>
                            <li><a href="http://okiwi.github.com/atbdx/pourquoivenir.html" class="viens-link">Viens !</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle programmation-link" data-toggle="dropdown">Programme<b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="http://lightningtalk.herokuapp.com/">Lightning Talks</a></li>
                                    <li><a href="http://okiwi.github.com/atbdx/programme.html">Programmation</a></li>
                                    <li><a href="http://okiwi.github.com/atbdx/orateurs.html">Orateurs</a></li>
                                </ul>
                            </li>    
                            <li>
                                <a href="http://okiwi.github.com/atbdx/informations_pratiques.html" class="infospratiques-link">Info</a>
                            </li>
                            <li>
                                <a href="http://okiwi.github.com/atbdx/equipe.html" class="equipe-link">Equipe</a>
                            </li>
                            <li>
                                <a href="http://okiwi.github.com/atbdx/sponsors.html" class="sponsors-link">Sponsors</a>
                            </li>
                            <li id="divPourAfficherUtilisateur" class="dropdown">
                            </li>
                        </ul>
                        <div >
						</div>
                        <div class="btn-group social">
                            <a href="http://www.facebook.com/Agiletourbordeaux" class="facebook"><span>Facebook</span></a>
                            <a href="http://twitter.com/okiwi_fr" class="twitter"><span>Twitter</span></a>
                            <a href="https://plus.google.com/u/0/events/cqotp15cf2eso0rr8gc0uit8mak/101137288862468153673" class="googleplus"><span>Google +</span></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="content" class="container">
			<#if erreur??>
			<div class="row">
				<div class="span12 alert alert-block alert-error fade in">
					<button class="close" data-dismiss="alert" type="button">×</button>
					<h4 class="alert-heading">Une erreur est survenue</h4>
					<p>${erreur}</p>
				</div>
			</div>
			</#if>
			<div class="row">
				<div class="span12">
					<div class="pull-right">
						<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordeonParent" href="#information">
							<small>Qu'est ce qu'un lightning talk ?</small>
						</a>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="span12">
					<div class="accordion" id="accordeonParent">
						<div id="information" class="accordion-body collapse">
							<div class="well text-info">
						     	<p>Tout participant peut <strong>proposer</strong> un Lightning Talk et <strong>voter</strong> pour ceux qui l'intéressent si il est 
						     		connecté ( menu en haut à droite : <i class="icon-user"></i>).
						     	</p> 
						     	<p>Il s'agit de sessions courtes de 5 minutes qui auront lieu entre 12h45 et 13H45 le jour de la conférence.
							     	Pourquoi ne pas tenter votre chance? C'est l'occasion de synthétiser votre connaissance sur un sujet et de donner aux autres 
							     	participants envie de le connaître plus! En fonction des votes des participants, votre sujet peut être choisi. 
							     	Les sujets gagnants seront sélectionnés avant la conférence.
						     	</p>
						    </div>
						</div>
					</div>
				</div>
			</div>
			<div id="divPourAfficherLesSessions">
				<!-- Div pour afficher les sessions -->
			</div>

			<div id="conteneurModalDeCreationEtDeMiseAJourDUneSession"></div>

	        </div>
        
        <div id="footer"class="container">
            <div class="span4">
                <a class="reservez" href="http://www.yuticket.com/association-okiwi/ca5f3eb4-6f52-4d8e-818b-ef8847dafb3a-agile-tour-bordeaux-2012.html"><b>Réservez votre place >></b></a>
            </div>
            <div class="span8 noLeftMargin">
                <p>L'Agile Tour Bordeaux est un évènement propulsé par <a href="http://okiwi.org">Okiwi</a><img src="http://okiwi.github.com/atbdx/static/img/favicon.png" class="okiwi" /></p>
            </div>
        </div>

        <script src="http://okiwi.github.com/atbdx/static/lib/bootstrap/js/bootstrap.min.js"></script>
       	<script src="<@spring.url '/ressources/js/ICanHaz.min.js'/>"></script>
		<script src="<@spring.url '/ressources/js/ICanHaz-utils.js'/>"></script>
		<script src="<@spring.url '/ressources/js/utilisateur.js'/>"></script>
		<script src="<@spring.url '/ressources/js/session.js'/>"></script>
		
		<script type="text/javascript">
	        $(document).ready(function() {
	            chargerTemplatesExternes('<@spring.url '/ressources/templates/'/>',[ "utilisateur", "session", "erreur" ]);
	            recupererUtilisateurCourant();
	            recupererLesSessions();
	        });
    	</script>

        <!--google analytics-->
        <script type="text/javascript">
            var _gaq = _gaq || [];
            _gaq.push(['_setAccount', 'UA-33933906-1']);
            _gaq.push(['_trackPageview']);

            (function() {
                var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
                ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
                var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
            })();
        </script>
    </body>
</html>
