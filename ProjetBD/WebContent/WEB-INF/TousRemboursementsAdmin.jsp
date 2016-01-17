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

	<jsp:include page="HeaderAdmin.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-lg-2">
				<jsp:include page="MenuAdmin.jsp" />
			</div>
			<div class="col-lg-8">
				<jsp:include page="DataTableRemboursementsAdmin.jsp"/>
				<a href="Accueil"><button type="button" class="btn btn-default pull-right">Retour</button></a>
			</div>
		</div>
		
	</div>

</body>
</html>
