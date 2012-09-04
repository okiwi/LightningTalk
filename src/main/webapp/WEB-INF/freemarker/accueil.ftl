<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Lightning talks Agile tour 2012</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Application permettant de proposer et de voter pour les lightnings talks de l'agile tour 2012">
<meta name="author" content="Jérôme Roux">

<link href="<@spring.url '/ressources/css/bootstrap.css'/>" rel="stylesheet" />
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}
</style>
<link type="text/css" rel="stylesheet" href="http://agiletourbordeaux.okiwi.org/css/style.css">
<link type="text/css" rel="stylesheet" href="http://fonts.googleapis.com/css?family=Ubuntu">
<link href="<@spring.url '/ressources/css/bootstrap-responsive.css'/>" rel="stylesheet">

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Le fav and touch icons 
    TODO-->
</head>

<body>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="http://agiletourbordeaux.okiwi.org/index.html">Agile Tour Bordeaux</a> 
				<div id="divPourAfficherUtilisateurSousMobile">
					<!-- Div pour afficher l'utilisateur sous mobile -->
				</div>
				<div class="nav-collapse">
					<ul class="nav">
						<li class="active"><a href="index.html">Accueil</a></li>
						<li><a href="http://agiletourbordeaux.okiwi.org/inscription.html">Inscription</a></li>
						<li><a href="http://agiletourbordeaux.okiwi.org/orateurs.html">Orateurs</a></li>
						<li><a href="http://agiletourbordeaux.okiwi.org/programmation.html">Programmation</a></li>
						<li><a href="http://agiletourbordeaux.okiwi.org/organisateurs.html">L'équipe</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Sponsors <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="http://agiletourbordeaux.okiwi.org/sponsors.html">Sponsors</a></li>
								<li><a href="http://agiletourbordeaux.okiwi.org/offre_de_sponsoring.html">Devenez sponsor</a></li>
							</ul></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<div class="container-fluid">
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

		<hr>

		<footer>
			<p>Agile tour bordeaux 2012</p>
		</footer>

	</div>
	<div class="modal fade hide" id="modalDeCreationDUneSession">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>Création d'une session</h3>
		</div>
		<div class="modal-body">
			<div id="conteneurDErreurPourLaCreationDUneSession"></div>
			<form id="formulaireDeCreationDUneSession" class="well">
				<label class="control-label" for="titre">Titre</label> <input type="text" class="span5" placeholder="Entrer le titre de votre session ..." name="titre" id="titreCreation" /> <label
					class="control-label" for="description">description</label>
				<textarea class="span5" placeholder="Entrer la description de votre session ..." name="description" id="descriptionCreation" rows="10"></textarea>
			</form>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal">Fermer</a> <a href="#" class="btn btn-primary" id="creerUneSession">Créer</a>
		</div>
	</div>
	<div id="conteneurModalDeCreationEtDeMiseAJourDUneSession"></div>

	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<@spring.url '/ressources/js/jquery.js'/>"></script>
	<script src="<@spring.url '/ressources/js/bootstrap.js'/>"></script>
	<script src="<@spring.url '/ressources/js/ICanHaz.js'/>"></script>
	
	<script id="templateDeSession" type="text/html">
		<div class="row-fluid">
			<div class="well">
				<h2>
					{{titre}} <small>proposé par <a>{{orateur}}</a></small>
				</h2>
				<p>{{description}}</p>
					<span class="badge badge-inverse">{{nombreDeVotes}}</span>
					{{#peutAjouterUnVote}}
						<a href="#" onClick="voter('{{titreEncodePourJavascript}}');return false;" class="btn btn-info btn-mini">
							<i class="icon-ok icon-white"></i>Voter
						</a>
					{{/peutAjouterUnVote}}
					{{#peutSupprimerUnVote}}
						<a href="#" onClick="enleverMonVote('{{titreEncodePourJavascript}}');return false;" class="btn btn-mini">
							<i class="icon-remove"></i>Enlever mon vote
						</a>
					{{/peutSupprimerUnVote}}
					{{#estOrateur}}
						<a href="#" onClick="supprimerSession('{{titre}}','{{titreEncodePourJavascript}}');return false;">
							<i class="icon-remove"></i><small>Supprimer</small>
						</a>
						<a href="#" onClick="afficherModalDeMiseAJourDUneSession('{{titreEncodePourJavascript}}','{{titre}}','{{description}}');return false;">
							<i class="icon-edit"></i><small>Mettre à jour</small>
						</a>
					{{/estOrateur}}
			</div>
		</div>
	</script>
	<script id="templateUtilisateurAuthentifieSousMobile" type="text/html">
		<div class="btn-group pull-right visible-phone" id="divPourAfficherUtilisateurSousMobile">
			<a class="btn dropdown-toggle" data-toggle="dropdown" href="#"> <i class="icon-user"></i> {{nomAffiche}}<span class="caret"></span>
			</a>
			<ul class="dropdown-menu">
				<li><a href="#" onClick="deconnexion();return false;"> Déconnexion</a></li>
			</ul>
		</div>
		<div class="btn-group pull-right visible-phone">
			<a class="btn" rel="tooltip" title="Créer une session" data-toggle="modal" href="#" onClick="afficherModalDeCreationDUneSession();return false;"> <i class="icon-pencil"></i>
			</a>
		</div>
	</script>
	<script id="templateUtilisateurNonAuthentifieSousMobile" type="text/html">
		<div class="pull-right visible-phone">
			<a class="btn" rel="tooltip" title="Connexion avec votre compte google" href="authentification/externe"> <i class="icon-user"></i>
			</a>
		</div>
	</script>
	<script id="templateUtilisateurAuthentifie" type="text/html">
		<h3>
			<img alt="" src="{{urlImage}}"> <a href="{{urlProfil}}">{{nomAffiche}}}</a>
		</h3>
		<p>
			<a class="btn" href="#" onClick="deconnexion();return false;"> Déconnexion</a>
		</p>
		<p>
			<a class="btn" rel="tooltip" title="Créer une session" data-toggle="modal" onClick="afficherModalDeCreationDUneSession();return false;"> <i class="icon-pencil"></i> Créer une session
			</a>
		</p>
	</script>
	<script id="templateUtilisateurNonAuthentifie" type="text/html">
		<a rel="tooltip" title="Connexion avec votre compte google" href="authentification/externe"> <i class="icon-user"> </i>Connexion
		</a>
	</script>
	
	<script id="templateErreur" type="text/html">
		<div class="alert alert-error">
			<a class="close" data-dismiss="alert" href="#">×</a>
			<h4 class="alert-heading">{{titre}}</h4>
			{{message}}
		</div>
	</script>
	<script id="templateModalDeCreationEtDeMiseAJourDUneSession" type="text/html">
		<div class="modal fade hide" id="modalDeCreationEtDeMiseAJourDUneSession">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3>{{modal.titre}} d'une session</h3>
			</div>
			<div class="modal-body">
				<div id="conteneurDErreurPourLaCreationEtLaMiseAJourDUneSession"></div>
				<form id="formulaireDeCreationEtDeMiseAJourDUneSession" class="well">
					<label class="control-label" for="titre">Titre</label> <input type="text" class="span5" placeholder="Entrer le titre de votre session ..." name="titre" id="titre" value="{{session.titre}}"/> <label
						class="control-label" for="description">description</label>
					<textarea class="span5" placeholder="Entrer la description de votre session ..." name="description" id="description" rows="10">{{session.description}}</textarea>
				</form>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">Close</a> <a href="#" class="btn btn-primary" id="sauvegarderSession">{{modal.action}}</a>
			</div>
		</div>
	</script>
	<script type="text/javascript">
	function recupererLesSessions(){
		$('#divPourAfficherLesSessions').html('');
		$.getJSON('<@spring.url 'sessions'/>', function (sessions) {
		    $.each(sessions, function (index,session) {
				$('#divPourAfficherLesSessions').append(ich.templateDeSession(session));
		    });
		});
	}
	
	function recupererUtilisateurCourant(){
		$.getJSON('<@spring.url 'utilisateurs/courant'/>', function (utilisateur) {
			if(utilisateur == null){
				afficherUtilisateurNonAuthentifie();
			}else {
			    $('#divPourAfficherUtilisateur').html(ich.templateUtilisateurAuthentifie(utilisateur));
			    $('#divPourAfficherUtilisateurSousMobile').html(ich.templateUtilisateurAuthentifieSousMobile(utilisateur));
			}
		});
	}
	
	function deconnexion(){
		$.getJSON('authentification/deconnexion', function () {
			afficherUtilisateurNonAuthentifie()
		});
		recupererLesSessions();
	}
	
	function afficherUtilisateurNonAuthentifie(){
		$('#divPourAfficherUtilisateur').html(ich.templateUtilisateurNonAuthentifie());
		$('#divPourAfficherUtilisateurSousMobile').html(ich.templateUtilisateurNonAuthentifieSousMobile());
	}
	
	function voter(titreEncodePourLURL){
		$.ajax({  
       		  type: 'POST',
       		  url: '<@spring.url 'sessions'/>/' + titreEncodePourLURL + '/votants',
       		  success: function() {
       			recupererLesSessions();
       		  }});
	}
	function enleverMonVote(titreEncodePourLURL){
		$.ajax({
			headers: {
		    	'X-HTTP-Method-Override': 'DELETE',
		  	},
  		  		type: 'GET',
   		  	url: '<@spring.url 'sessions'/>/' + titreEncodePourLURL + '/votants',
   		  	success: function() {
   		  	recupererLesSessions();
   		  	}
  		  	});
	}
	
	function supprimerSession(titre,titreEncodePourLURL){
		if(confirm("Voulez vraiment supprimer la session " + titre))
		$.ajax({
			headers: {
		    	'X-HTTP-Method-Override': 'DELETE',
		  	},
  		  		type: 'GET',
   		  	url: '<@spring.url 'sessions'/>/' + titreEncodePourLURL ,
   		  	success: function() {
   		  	recupererLesSessions();
   		  	}
		 });
	}
	
	function afficherModalDeMiseAJourDUneSession(titreEncodePourLURL,titre,description){
		var vue = {
		  modal:{
		  	titre: "Mise à jour",
	  		action: "Mettre à jour"
		  },
		  session:{
			  titre: titre,
			  description: description
		  }
		}
		$('#conteneurModalDeCreationEtDeMiseAJourDUneSession').html(ich.templateModalDeCreationEtDeMiseAJourDUneSession(vue));
		$('#sauvegarderSession').bind('click',function(){
			$.ajax({  
	       		  type: 'POST',
	       		  url: '<@spring.url 'sessions'/>/' + titreEncodePourLURL,
	       		  data: $('#formulaireDeCreationEtDeMiseAJourDUneSession').serializeArray(),
	       		  success: function() {
	       			$('#modalDeCreationEtDeMiseAJourDUneSession').modal('hide');
	       			recupererLesSessions();
	       		  },
	       		  error: function(data) {
	       			  var erreur = {titre:"Sauvegarde impossible", message:data.responseText};
	       			  $('#conteneurDErreurPourLaCreationEtLaMiseAJourDUneSession').html(ich.templateErreur(erreur));
	       		  }
	    		});
	      });
		$('#modalDeCreationEtDeMiseAJourDUneSession').modal();
	}
	
	function afficherModalDeCreationDUneSession(){
		var vue = {
		  modal:{
		  	titre: "Création",
	  		action: "Créer"
		  },
		  session:{
			  titre: "",
			  description: ""
		  }
		}
		$('#conteneurModalDeCreationEtDeMiseAJourDUneSession').html(ich.templateModalDeCreationEtDeMiseAJourDUneSession(vue));
		$('#sauvegarderSession').bind('click',function(){
       		$.ajax({  
         		  type: 'POST',
         		  url: '<@spring.url 'sessions'/>',
         		  data: $('#formulaireDeCreationEtDeMiseAJourDUneSession').serializeArray(),
         		  success: function() {
         			$('#modalDeCreationEtDeMiseAJourDUneSession').modal('hide');
         			recupererLesSessions();
         		  },
         		  error: function(data) {
         			  var erreur = {titre:"Création impossible", message:data.responseText};
         			  $('#conteneurDErreurPourLaCreationEtLaMiseAJourDUneSession').html(ich.templateErreur(erreur));
         		  }
      		});
        });
		$('#modalDeCreationEtDeMiseAJourDUneSession').modal();
	}
	
		
	
     $(document).ready(function(){
        recupererLesSessions();
        recupererUtilisateurCourant();
      });
   </script>
</body>
</html>
