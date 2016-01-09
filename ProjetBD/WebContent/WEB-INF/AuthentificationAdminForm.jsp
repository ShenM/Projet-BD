<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="login-form">
	<div class="container">
	    <div class="row">
	        <div class="col-sm-6 col-md-4 col-md-offset-4">
	            <h1 class="text-center login-title">Connectez-vous à votre plateforme d'administration</h1>
	            <div class="account-wall">
	                <img class="profile-img" src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120"
	                    alt="">
		            <c:forEach items="${erreur}" var="entry">
				    	Erreur: <c:out value="${entry.value}"/> <br />
				    </c:forEach> 	
	                <form class="form-signin" method="post" action="AuthentificationAdmin">
	                <input type="text" class="form-control" id="id" name="id" placeholder="Id" required autofocus>
	                <input type="password" class="form-control" id="motdepasse" name="motdepasse" placeholder="Mot de passe" required>
	                <button class="btn btn-lg btn-primary btn-block" type="submit" value="Connexion">
	                    Connexion</button>
	                <label class="checkbox pull-left">
	                    <input type="checkbox" value="remember-me">
	                    Se souvenir de moi
	                </label>
	                <a href="#" class="pull-right need-help">Besoin d'aide? </a><span class="clearfix"></span>
	                </form>
	            </div>
	        </div>
	    </div>
	</div>
</div>