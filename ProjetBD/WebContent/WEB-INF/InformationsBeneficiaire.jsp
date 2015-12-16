<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
	
	
	

<div class="container">
    <h1>Editer le Profile</h1>
  	<hr>
	<div class="row">
      
      <!-- edit form column -->
      <div class="col-md-9 personal-info">
        <div class="alert alert-info alert-dismissable">
          <a class="panel-close close" data-dismiss="alert">×</a> 
          <i class="fa fa-coffee"></i>
          ${success}
        </div>
        <h3>Informations Personelles</h3>
        
        <form class="form-horizontal" method="post" action="EditerProfile">
          <div class="form-group">
            <label class="col-lg-3 control-label">Prénom:</label>
            <div class="col-lg-8">
              <input class="form-control" value="${benef.prenom}" type="text" disabled>
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Nom:</label>
            <div class="col-lg-8">
              <input class="form-control" value="${benef.nom}" type="text" disabled>
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Numéro  Bénéficiaire:</label>
            <div class="col-lg-8">
              <input class="form-control" value="${benef.num}" type="text" disabled>
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Email:</label>
            <div class="col-lg-8">
              <input class="form-control" value="${benef.email}" type="text" name="email" id="email">
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label">Numéro Téléphone:</label>
            <div class="col-md-8">
              <input class="form-control" value="${benef.numTelephone}" type="text" name="num" id="num">
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label">Mot de passe:</label>
            <div class="col-md-8">
              <input class="form-control" type="password" name="password1" id="password1">
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label">Confirmation mot de passe:</label>
            <div class="col-md-8">
              <input class="form-control" type="password" name="password2" id="password2">
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label"></label>
            <div class="col-md-8">
              <button class="btn btn-primary" type="submit">Sauvegarder </button>
              <span></span>
              <input class="btn btn-default" value="Annuler" type="reset">
            </div>
          </div>
        </form>
      </div>
  </div>
</div>
<hr>
<script>
var password = document.getElementById("password1")
, confirm_password = document.getElementById("password2");

function validatePassword(){
if(password.value != confirm_password.value) {
  confirm_password.setCustomValidity("Les mots de passe ne correspondent pas");
} else {
  confirm_password.setCustomValidity('');
}
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;
</script>