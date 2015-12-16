<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<link rel="stylesheet" href="styles/Contrat.css">
</head>


<body>

	<jsp:include page="Header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-lg-2">
				<jsp:include page="Menu.jsp" />
			</div>
			<div class="col-lg-8">
				<div class="panel panel-info">
				<div class = "panel-heading"><b> Contrat 123626  fromule privilège avec garantie</b></div>
				</div>
				
				
				<div class="panel panel-default">
				<div class = "panel-heading"> Robert DUPONT</div>
				<div>Date de naissance: 12/02/1990</div><br>
				<div>Sexe: Homme</div><br>
				<div>Email: dupon@gmail.com</div><br>
				<div>Telephone: 1232347667</div><br>
				<hr>
				<div>Numéro bénéficiaire: 1</div><br>
				<div>Type: Assuré</div><br>
				<div>Primes acquises: 153€</div><br>
				<div class = "panel-footer">
				<div class="clearfix"></div></div>
				</div>
<!-- 
CONTRAT: ( num, formule, annee paiement, garantie)
BENEF(Nom, prenom, sexe, date naissance, email, tel, num benef sur contrat, type benef, primes acquises) -->


			</div>
			<div class="col-lg-2">
				<jsp:include page="Info1.jsp" />
				<jsp:include page="Info2.jsp" />
			</div>
		</div>
		
	</div>

</body>
</html>