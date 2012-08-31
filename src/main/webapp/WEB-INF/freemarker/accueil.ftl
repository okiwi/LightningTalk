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
				</a> <a class="brand" href="http://agiletourbordeaux.okiwi.org/index.html">Agile Tour Bordeaux</a> <#if utilisateur??>
				<div class="btn-group pull-right visible-phone">
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#"> <i class="icon-user"></i> ${utilisateur.nomAffiche}<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="authentification/deconnexion"> Déconnexion</a></li>
					</ul>
				</div>
				<div class="btn-group pull-right visible-phone">
					<a class="btn" rel="tooltip" title="Créer une session" data-toggle="modal" href="#modalDeCreationDUneSession"> <i class="icon-pencil"></i>
					</a>
				</div>
				<#else>
				<div class="pull-right visible-phone">
					<a class="btn" rel="tooltip" title="Connexion avec votre compte google" href="authentification/externe"> <i class="icon-user"></i>
					</a>
				</div>
				</#if>
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
				<div class="well">
					<#if utilisateur??>
					<h3>
						<img alt="" src="${utilisateur.urlImage}"> <a href="${utilisateur.urlProfil}">${utilisateur.nomAffiche}</a>
					</h3>
					<p>
						<a class="btn" href="authentification/deconnexion"> Déconnexion</a>
					</p>
					<p>
						<a class="btn" rel="tooltip" title="Créer une session" data-toggle="modal" href="#modalDeCreationDUneSession"> <i class="icon-pencil"></i> Créer une session
						</a>
					</p>
					<#else> <a rel="tooltip" title="Connexion avec votre compte google" href="authentification/externe"> <i class="icon-user"> </i>Connexion
					</a> </#if>
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
	<#if utilisateur??>
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
	</#if>
	<div id="conteneurModalDeMiseAJourDUneSession"></div>

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
	<script id="templateErreur" type="text/html">
		<div class="alert alert-error">
			<a class="close" data-dismiss="alert" href="#">×</a>
			<h4 class="alert-heading">{{titre}}</h4>
			{{message}}
		</div>
	</script>
	<script id="templateModalDeMiseAJourDUneSession" type="text/html">
		<div class="modal fade hide" id="modalDeMiseAJourDUneSession">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3>Mise à jour d'une session</h3>
			</div>
			<div class="modal-body">
				<div id="conteneurDErreurPourLaMiseAJourDUneSession"></div>
				<form id="formulaireDeMiseAJourDUneSession" class="well">
					<label class="control-label" for="titre">Titre</label> <input type="text" class="span5" placeholder="Entrer le titre de votre session ..." name="titre" id="titreMiseAJour" /> <label
						class="control-label" for="description">description</label>
					<textarea class="span5" placeholder="Entrer la description de votre session ..." name="description" id="descriptionMiseAJour" rows="10"></textarea>
				</form>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">Close</a> <a href="#" class="btn btn-primary" id="mettreAJourSession">Mettre à jour</a>
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
		$('#conteneurModalDeMiseAJourDUneSession').html(ich.templateModalDeMiseAJourDUneSession());
		$('#modalDeMiseAJourDUneSession').modal();
		$('#titreMiseAJour').val(titre);
		$('#descriptionMiseAJour').val(description);
		$('#mettreAJourSession').bind('click',function(){
			$.ajax({  
	       		  type: 'POST',
	       		  url: '<@spring.url 'sessions'/>/' + titreEncodePourLURL,
	       		  data: $('#formulaireDeMiseAJourDUneSession').serializeArray(),
	       		  success: function() {
	       			$('#modalDeMiseAJourDUneSession').modal('hide');
	       			recupererLesSessions();
	       		  },
	       		  error: function(data) {
	       			  var erreur = {titre:"Création impossible", message:data.responseText};
	       			  $('#conteneurDErreurPourLaMiseAJourDUneSession').html(ich.templateErreur(erreur));
	       		  }
	    		});
	      });
	}
		
	
     $(document).ready(function(){
		<#if utilisateur??>
       	$('#creerUneSession').bind('click',function(){
       		$.ajax({  
       		  type: 'POST',
       		  url: '<@spring.url 'sessions'/>',
       		  data: $('#formulaireDeCreationDUneSession').serializeArray(),
       		  success: function() {
       			$('#modalDeCreationDUneSession').modal('hide');
       			recupererLesSessions();
       		  },
       		  error: function(data) {
       			  var erreur = {titre:"Création impossible", message:data.responseText};
       			  $('#conteneurDErreurPourLaCreationDUneSession').html(ich.templateErreur(erreur));
       		  }
    		});
       	});
       	
        $('#modalDeCreationDUneSession').on('hidden', function () {
        	$('#titreCreation').val('');
   			$('#descriptionCreation').val('');
   			$('#conteneurDErreurPourLaCreationDUneSession').html('');
         });
        
        </#if>
        recupererLesSessions();
      });
   </script>
</body>
</html>
