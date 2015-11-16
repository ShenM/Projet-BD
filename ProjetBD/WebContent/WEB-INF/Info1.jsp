<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="panel panel-primary">
	<div class="panel-heading">Mes informations : </div>
	<div class="panel-content">
		<ul class="list-group">
		<li class="list-group-item">${benef.num}</li>
		<li class="list-group-item">${benef.nom}</li>
		<li class="list-group-item">${benef.prenom}</li>
		<li class="list-group-item">${benef.numTelephone}</li>
		<li class="list-group-item">${benef.email}</li>
		</ul>
	</div>
	<div class="panel-footer">
	<button type="button" class="btn btn-primary">Modifier</button>
	<button type="button" class="btn btn-default">Voir plus...</button>
	</div>
</div>