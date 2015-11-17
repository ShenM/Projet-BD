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
			    <tr>
			    	<td><c:out value="${presta.designationActe}"/></td>
			    	<td><c:out value="${presta.jourPaiement}"/>/<c:out value="${presta.moisPaiement}"/>/<c:out value="${presta.anneePaiement}"/></td>
			    	<td><c:out value="${presta.montantRembourse} €"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
<div class = "panel-footer"><a href="TousRemboursements"><button type="button" class="btn btn-default pull-right">Voir plus...</button></a>
<div class="clearfix"></div></div>

</div>