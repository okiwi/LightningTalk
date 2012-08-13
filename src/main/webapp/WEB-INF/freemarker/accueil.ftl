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
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#">Project name</a>
				<div class="btn-group pull-right">
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#"> <i class="icon-user"></i> Username <span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li class="divider"></li>
						<li><a href="authentification/externe"> Connexion</a></li>
					</ul>
				</div>
				<div class="nav-collapse">
					<ul class="nav">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#about">About</a></li>
						<li><a href="#contact">Contact</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3">
				<div class="well sidebar-nav">
					<ul class="nav nav-list">
						<li class="nav-header">Sidebar</li>
						<li class="active"><a href="#">Link</a></li>
						<li><a href="#">Link</a></li>

					</ul>
				</div>
				<!--/.well -->
			</div>
			<!--/span-->
			<div class="span9">
				<div class="row-fluid">
					<div class="span4">
						<h2>Heading</h2>
						<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.
							Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.</p>
						<p>
							<a class="btn" href="#">View details &raquo;</a>
						</p>
					</div>
					<!--/span-->
					<div class="span4">
						<h2>Heading</h2>
						<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.
							Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.</p>
						<p>
							<a class="btn" href="#">View details &raquo;</a>
						</p>
					</div>
					<!--/span-->
					<div class="span4">
						<h2>Heading</h2>
						<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.
							Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.</p>
						<p>
							<a class="btn" href="#">View details &raquo;</a>
						</p>
					</div>
					<!--/span-->
				</div>
				<!--/row-->
				<div class="row-fluid">
					<div class="span4">
						<h2>Heading</h2>
						<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.
							Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.</p>
						<p>
							<a class="btn" href="#">View details &raquo;</a>
						</p>
					</div>
					<!--/span-->
					<div class="span4">
						<h2>Heading</h2>
						<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.
							Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.</p>
						<p>
							<a class="btn" href="#">View details &raquo;</a>
						</p>
					</div>
					<!--/span-->
					<div class="span4">
						<h2>Heading</h2>
						<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.
							Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.</p>
						<p>
							<a class="btn" href="#">View details &raquo;</a>
						</p>
					</div>
					<!--/span-->
				</div>
				<!--/row-->
			</div>
			<!--/span-->
		</div>
		<!--/row-->

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
