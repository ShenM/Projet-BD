<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="panel panel-default">
<div class = "panel-heading"> Vos dernières transactions</div>

	<table class="table">
		<thead>
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
			    	<td>Nom: <c:out value="${presta.nomBenef}"/><br>Prenom: <c:out value="${presta.prenomBenef}"/></td>
			    	<td>Date début soins: <c:out value="${presta.jourDebutSoins}"/>/<c:out value="${presta.moisDebutSoins}"/>/<c:out value="${presta.anneeDebutSoins}"/></td>
			    	<td>Frais réels: <c:out value="${presta.fraisReelAssure}"/><br>Remboursement secu: <c:out value="${presta.montantSecu}"/></td>
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
					$(this).slideUp('slow');  	
			});		
	
			$(".rowTable").click(function()
			{
				$(this).next('.showMore').slideToggle('slow');
			});

		});
</script>