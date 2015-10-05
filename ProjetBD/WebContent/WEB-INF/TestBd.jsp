<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de test BDD</title>
</head>
<body>
	<c:out value="${ beneficiaire.num }"/></br>
	<c:out value="${ beneficiaire.sexe }"/></br>
	<c:out value="${ beneficiaire.regimeSocial }"/></br>
	<c:out value="${ beneficiaire.dateNaissanceBenficiaire }"/></br>
</body>
</html>