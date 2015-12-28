<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Authentification Administrateur</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="styles/Header.css">
<link rel="stylesheet" href="styles/LoginForm.css">
</head>
<body>
	<jsp:include page="HeaderOffline.jsp" />
	
		<div class="container">
		<div class="row">
			<div class="col-lg-2">			
			</div>
			<div class="col-lg-8">			
				<jsp:include page="AuthentificationAdminForm.jsp" />
			</div>
			<div class="col-lg-2">			
			</div>

		</div>
	</div>
</body>
</html>