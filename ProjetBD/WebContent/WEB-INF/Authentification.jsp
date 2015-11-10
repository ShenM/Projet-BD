<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Authentification</title>
</head>
<body>
	<form method="post" action="Authentification">
		<fieldset>
			<legend>Authentification</legend>
			<p>Vous pouvez vous authentifier via ce formulaire.</p>

			<label for="nom">Numero bénéficiaire <span class="requis">*</span></label>
			<input type="number" id="id" name="id"
				value="<c:out value="${id}"/>" size="20"
				maxlength="60" /> <p class="erreur">${form.erreurs['id']}</p>


			<label for="motdepasse">Mot de passe <span class="requis">*</span></label>
			<input type="password" id="motdepasse" name="motdepasse" value=""
				size="20" maxlength="20" /> <p class="erreur">${form.erreurs['motdepasse']}</p>


			<input type="submit" value="Connexion" class="sansLabel" />


			<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>

		</fieldset>
	</form>
</body>
</html>