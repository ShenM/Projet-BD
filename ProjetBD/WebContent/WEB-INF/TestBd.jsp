<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
>>>>>>> d1222dab3aa598c2f6fd9a500a82ff0a2df84c23
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de test BDD</title>
</head>
<body>
	<c:out value="${ beneficiaire.num }" />
	</br>
	<c:out value="${ beneficiaire.sexe }" />
	</br>
	<c:out value="${ beneficiaire.regimeSocial }" />
	</br>
	<c:out value="${ beneficiaire.dateNaissanceBenficiaire }" />
	</br>

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
					<td>Remboursement</td>
				</tr>
				<tr>
					<td><c:out value="${presta.jourPaiement}" />/<c:out
							value="${presta.moisPaiement}" />/<c:out
							value="${presta.anneePaiement}" /></td>
				</tr>
				<tr>
					<td><c:out value="${presta.montantRembourse}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>