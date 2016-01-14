<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Traitement de remboursement</title>
	<link rel="stylesheet" href="styles/Header.css">
	<link rel="stylesheet" href="styles/Menu.css">
	<link rel="stylesheet" href="styles/Demande.css">
</head>


<body>

	<jsp:include page="HeaderAdmin.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-lg-2">
				<jsp:include page="MenuAdmin.jsp" />
			</div>
			<div class="col-lg-10">
				
				<div class="container">
				    <h1>Traitement de remboursement</h1>
				  	<hr>
				  	
				  	<c:choose>
				  		<c:when test="${error != ''}">
				        	<div class="form-group" >
				         		<label class="col-lg-7 control-label" style="color: ${errorColor}; font-weight: bold;"><c:out value="${error}"/></label>
				          	</div>
			  			</c:when>
			  			<c:otherwise>

				  	
				  	<form method="post" action="TraitementRemboursementAdmin">
						<div class="col-lg-8">
							<div class="row">
		 						<div class="row info-contrat">
									<div class="alert alert-warning col-lg-2" ><b>Contrat :  </b>  ${ adhesion.numAdhesionNormalise }</div>
									<div class="alert alert-warning col-lg-2" ><b>Fromule :  </b> ${ adhesion.formule }</div>
									<div class="alert alert-warning col-lg-2" ><b>Garantie :  </b> ${ adhesion.primeGarantie }</div>
									<div class="alert alert-warning col-lg-2" ><b>Année :  </b> ${ adhesion.exercicePaiement }</div>
									<div class="col-lg-3"></div>
								</div>
							</div>
							<div class="row">
								<div class="panel panel-info contrat-pannel ">				
									<div class="panel-heading">
					                    <h3 class="panel-title">
					                        Informations de la demande
					                    </h3>
					                </div>
					                
					                
					                <div class="panel-body">
					                	<table class="table table-contrat-information">
					                		<tbody>
					                			<tr style="font-weight:bold;">
					                				<td>Acte :</td>
					                				<td>Designation de l'acte :</td>
					                				<td>Libelle de l'acte :</td>
					                				<td>Debut des soins :</td>
					                			</tr>
					                			<tr>
					                				<td> ${demande.acteId}</td>	               
						                			<td> ${demande.acteDesignation}</td>
								                	<td> ${demande.acteLibelle}</td>
													<td> ${demande.acteDateDebutSoins}</td>
												</tr>
												<tr>
					                			<tr style="font-weight:bold;">
					                				<td>Date de paiement :</td>
					                				<td>Frais reels :</td>
					                				<td>Date de création :</td>
					                				<td>Facture :</td>
					                			</tr>
					                			<tr>
					                				<td> ${demande.fraisDatePaiement}</td>	               
						                			<td> ${demande.fraisReels}</td>
								                	<td> ${demande.date_creation}</td>
													<td> [Je suis un bouton de DL]</td>
												</tr>
					                		</tbody>
					                	</table>	
					                </div>
		       		 				<div class="panel-footer"></div>
					                
				 				</div>
							</div>
						</div>
				
				        <div class="col-lg-4">    
							<div class="panel panel-info contrat-pannel ">				
								<div class="panel-heading">
				                    <h3 class="panel-title">
				                        <span class="glyphicon glyphicon-user"></span>${benef.nom}   ${benef.prenom}
				                    </h3>
				                </div>
				                
				                
				                <div class="panel-body">
				                	<table class="table table-contrat-information">
				                		<tbody>
				                			<tr><td>Numéro bénéficiaire :</td><td> ${benef.num}</td></tr>				               
				                			<tr><td>Date de naissance :</td><td> ${benef.dateNaissanceBenficiaire}</td></tr>
						                	<tr><td>Sexe :</td><td> ${benef.sexe}</td></tr>
											<tr><td>Email :</td><td> ${benef.email}</td></tr>
											<tr><td>Telephone :</td><td> ${benef.numTelephone}</td></tr>
				                		</tbody>
				                	</table>	
				                </div>
	       		 				<div class="panel-footer"></div>
				                
			 				</div>
			 				
						</div>
						<div class="form-group">
						 	<input type="hidden" name="id" value="${ benef.num }">
						 	<input type="hidden" name="dateC" value="${ demande.date_creation }">
						  	<label class="col-lg-8 control-label"></label>
						  	<div class="col-md-8">
						  		<input type="submit" name="action" value="TraitementRemboursementValide">
						    	<input type="submit" name="action" value="TraitementRemboursementRejete">
						    	
						    	<a href="AccueilAdmin"><button type="button" class="btn btn-default" value="Annuler"> Annuler</button></a>
						  </div>
						</div>
		 			</form>
		 			
	 					</c:otherwise>
	 				</c:choose>
			  	</div>
			</div>
	 	</div>				                			

</body>

</html>