<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mes contrats</title>
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

				<!-- <div class="panel panel-info">
				<div class = "panel-heading">
					<table >
		            	<tbody class="info-contrat">
		            		<tr>
		            			<td><b>Contrat </b></td>
		            			<td>123626</td>
		            		</tr>
		            		<tr>
		            			<td><b>fromule</b> </td>
		            			<td>privilège</td>
		            		</tr>
		            		<tr>
		            			<td><b>Garantie:</b> </td>
		            			<td>oui</td>
		            		</tr>
		            	</tbody>
		            </table>
				
				</div>
				</div> -->
				
				
				
				<c:set var="val" value="1"/>
				<c:forEach var="cont" items="${contrat}">	
					<c:choose> 
  						<c:when test="${val == '1'}">
					
							<div class="row info-contrat">
								<div class="alert alert-warning col-lg-2" ><b>Contrat :  </b>  ${ cont.value.numAdhesionNormalise }</div>
								<div class="alert alert-warning col-lg-2" ><b>Formule :  </b> ${ cont.value.formule }</div>
								<div class="alert alert-warning col-lg-2" ><b>Garantie :  </b> ${ cont.value.primeGarantie }</div>
								<div class="alert alert-warning col-lg-2" ><b>Année :  </b> ${ cont.value.exercicePaiement }</div>
								<div class="col-lg-3"></div>
							</div>
						</c:when>	
					</c:choose>
					<c:set var="val" value="2"/>
					
				    <div class="col-lg-6">
						<div class="panel panel-info contrat-pannel ">				
							<div class="panel-heading">
			                    <h3 class="panel-title">
			                        <span class="glyphicon glyphicon-user"></span>${cont.key.nom}   ${cont.key.prenom}
			                    </h3>
			                </div>
			                
			                
			                <div class="panel-body">
			                	<table class="table table-contrat-information">
			                		<tbody>
			                			<tr><td>Date de naissance :</td><td> ${cont.key.dateNaissanceBenficiaire}</td></tr>
					                	<tr><td>Sexe :</td><td> ${cont.key.sexe}</td></tr>
										<tr><td>Email :</td><td> ${cont.key.email}</td></tr>
										<tr><td>Téléphone :</td><td> ${cont.key.numTelephone}</td></tr>
										<tr><td>Numéro bénéficiaire :</td><td> ${cont.key.num}</td></tr>
										<tr><td>Type :</td><td> ${cont.value.typeBeneficiaire}</td></tr>
										<tr><td>Primes acquises :</td><td> ${cont.value.primesAcquises}€</td></tr>
			                		</tbody>
			                	</table>	
			                </div>
		                	<div class="panel-footer"></div>
		 				</div>
		 				
					</div>		   
				</c:forEach>
				
				<!-- <div class="col-lg-5">
					<div class="panel panel-info contrat-pannel ">						
						<div class="panel-heading">
		                    <h3 class="panel-title">
		                        <span class="glyphicon glyphicon-user"></span>Robert DUPONT
		                    </h3>
		                </div>
		                <div class="panel-body">
		                	<table class="table table-contrat-information">
		                		<tbody>
		                			<tr><td>Date de naissance:</td><td> 12/02/1990</td></tr>
				                	<tr><td>Sexe:</td><td> Homme</td></tr>
									<tr><td>Email:</td><td> dupon@gmail.com</td></tr>
									<tr><td>Telephone:</td><td> 1232347667</td></tr>
									<tr><td>Numéro bénéficiaire:</td><td> 3</td></tr>
									<tr><td>Type:</td><td> Assuré</td></tr>
									<tr><td>Primes acquises:</td><td> 153€</td></tr>
		                		</tbody>
		                	</table>
			                	
		                </div>
	                	<div class="panel-footer "></div>
	 				</div>
				</div> -->

				<div>								
					<a  href="Accueil"><button type="button" class="btn btn-default pull-right"  style="margin-bottom: 20px;">Retour</button></a>
				</div>
				
			</div>
				
				

<!-- 
CONTRAT: ( num, formule, annee paiement, garantie)
BENEF(Nom, prenom, sexe, date naissance, email, tel, num benef sur contrat, type benef, primes acquises) -->


			<div class="col-lg-2">
				<jsp:include page="Info1.jsp" />
				<jsp:include page="Info2.jsp" />
			</div>
		</div>
		
	</div>

</body>
</html>