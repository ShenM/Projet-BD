<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="col-lg-8 remboursements">
	<p class="row titre-remboursement">Remboursements :</p>
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
			    	<td><c:out value="${presta.montantRembourse}"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>