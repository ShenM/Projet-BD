<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="navbar navbar-default navbar-static-top">
		<div class="col-lg-4 title">
			<div class="col-lg-3 title-logo">
				<img src="res/logoPolyMut.png">
			</div>
			<div class="col-lg-9">
				<div class="title-text"><a href="StatistiquesAdmin"><p>Mutuelle Polytech</p></a></div>
			</div>
		</div>
		<div class="col-lg-4 user pull-right">
			<div class="user-avatar col-lg-5">
				<img class="user-icon" src="res/avatar.jpg">
			</div>
			<div class="user-name col-lg-3"><p><c:out value="${admin.id}"/></p></div>
			<div class="logout col-lg-2">
				<a class="glyphicon glyphicon-off" href="Deconnexion"></a>
			</div>
		</div>
	</div>
