<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<head>
	<link rel="stylesheet" href="styles/Remboursements.css">
	</head>


<div class="panel panel-default">
<div class = "panel-heading"> Vos dernières transactions</div>

	<table class="table">
		<thead class="headTable">
			<tr>
				<th>Transaction</th>
				<th>Date</th>
				<th>Remboursement</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${lpresta}" var="presta">
			    <tr class="rowTable">
			    	<td><c:out value="${presta.designationActe}"/></td>
			    	<td><c:out value="${presta.jourPaiement}"/>/<c:out value="${presta.moisPaiement}"/>/<c:out value="${presta.anneePaiement}"/></td>
			    	<td><c:out value="${presta.montantRembourse} €"/></td>
				</tr>
				<tr class='showMore'>
			    	<td><u>Nom</u>: <c:out value="${presta.nomBenef}"/><br><u>Prenom: </u><c:out value="${presta.prenomBenef}"/></td>
			    	<td><u>Date début soins: </u><c:out value="${presta.jourDebutSoins}"/>/<c:out value="${presta.moisDebutSoins}"/>/<c:out value="${presta.anneeDebutSoins}"/></td>
			    	<td><u>Frais réels: </u><c:out value="${presta.fraisReelAssure}"/><br><u>Remboursement secu: </u><c:out value="${presta.montantSecu}"/></td>
				</tr>					
			</c:forEach>
		</tbody>
	</table>
	
<div class = "panel-footer"><a href="TousRemboursements"><button type="button" class="btn btn-default pull-right">Voir plus...</button></a>
<div class="clearfix"></div></div>

</div>
<script>
$(document).ready(function() {
			$('.showMore').each(
				function(){
					$(this).slideUp('fast');  	
			});		
	
			$(".rowTable").click(function()
			{
				$(this).next('.showMore').slideToggle('fast');
			});

		});
</script>