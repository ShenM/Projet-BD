<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Remboursement</title>
<link rel="stylesheet" href="styles/Header.css">
<link rel="stylesheet" href="styles/Menu.css">
</head>
<body>
	<jsp:include page="Header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-lg-2">
				<jsp:include page="Menu.jsp" />
			</div>
			<div class="col-lg-8">
				<jsp:include page="DataTableRemboursements.jsp"/>
				<a href="Accueil"><button type="button" class="btn btn-default pull-right">Retour</button></a>
			</div>
			<div class="col-lg-2">
				<jsp:include page="Info1.jsp" />
				<div>
					<div class="panel panel-info">
						<div class="panel-heading text-center">
							<h3 class="panel-title">
				            	<span class="glyphicon glyphicon-info-sign" style="font-size:1.5em;padding-right: 10px;"></span><b>Astuces</b>
				        	</h3>
				        </div>
						<div class="panel-content" style="padding:10px;">
						<p>
							<ul>
							<li>Cliquer sur les <b>entêtes du tableau</b> pour le trier.<br><br></li>
							<li>Appuyer sur la touche <b>ctrl</b> puis sur les <b>entêtes de colonnes</b> pour appliquer plusieurs tris.<br><br></li>
							<li>Le bouton <b>choisir les champs visibles</b> permet de choisir les <b>colonnes affichées</b> dans le tableau.<br><br></li>
							<li>Cliquer sur le <b>bouton bleu</b> pour choisir le <b>nombre de lignes</b> par page du tableau</li>
							</ul>
						</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
