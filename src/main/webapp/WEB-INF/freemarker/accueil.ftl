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
				<div class="btn-group pull-right">
					<#if utilisateur??> <a class="btn dropdown-toggle" data-toggle="dropdown" href="#"> <i class="icon-user"></i> ${utilisateur.nomAffiche} <span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li class="divider"></li>
						<li><a href=""> Deconnexion</a></li>
					</ul>
					<#else> <a class="btn dropdown-toggle" data-toggle="dropdown" href="#"> <i class="icon-user"></i> <span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li class="divider"></li>
						<li><a href="authentification/externe"> Connexion</a></li>
					</ul>
					</#if>
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
				<div class="well">
					<h3>Utilisateur courant</h3>
					<ul class="thumbnails">
						<li class="span10"><a class="thumbnail" href="#profil"> <img alt="" src="http://agiletourbordeaux.okiwi.org/img/profils/jr.jpg">
						</a></li>
					</ul>
					<p>Des informations sur les éléments lu et non lu</p>
				</div>
			</div>
			<div class="span9">
				<div class="row-fluid">
					<div class="well">
						<h2>
							Titre de la session <small>proposé par <a>Utilisateur</a></small>
						</h2>
						<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.
							Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.</p>
						<a href="#" class="btn btn-info btn-mini"><i class="icon-ok icon-white"></i> Voter</a><span class="badge badge-inverse">10</span>

					</div>
				</div>
				<div class="row-fluid">
					<div class="well">
						<h2>
							Titre de la session <small>proposé par <a>Utilisateur</a></small>
						</h2>
						<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.
							Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.</p>
						<a href="#" class="btn btn-mini disabled"><i class="icon-ok"></i> Voter</a><span class="badge badge-inverse">10</span>
					</div>
				</div>
			</div>
		</div>

		<hr>

		<footer>
			<p>Agile tour bordeaux 2012</p>
		</footer>

	</div>
	<!--/.fluid-container-->

	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<@spring.url '/ressources/js/jquery.js'/>"></script>
	<script src="<@spring.url '/ressources/js/bootstrap.js'/>"></script>
</body>
</html>
