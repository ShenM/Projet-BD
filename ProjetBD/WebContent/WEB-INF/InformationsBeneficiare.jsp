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
         	Modifiez vos informations
        </div>
        <h3>Informations Personelles</h3>
        
        <form class="form-horizontal" role="form">
          <div class="form-group">
            <label class="col-lg-3 control-label">Prénom:</label>
            <div class="col-lg-8">
              <input class="form-control" value="${benef.prenom}" type="text">
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Nom:</label>
            <div class="col-lg-8">
              <input class="form-control" value="${benef.nom}" type="text">
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Numéro  Bénéficiaire:</label>
            <div class="col-lg-8">
              <input class="form-control" value="${benef.num}" type="text">
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Email:</label>
            <div class="col-lg-8">
              <input class="form-control" value="${benef.email}" type="text">
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label">Numéro Téléphone:</label>
            <div class="col-md-8">
              <input class="form-control" value="${benef.numTelephone}" type="text">
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label">Mot de passe:</label>
            <div class="col-md-8">
              <input class="form-control" type="password">
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label">Confirmation mot de passe:</label>
            <div class="col-md-8">
              <input class="form-control" type="password">
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label"></label>
            <div class="col-md-8">
              <input class="btn btn-primary" value="Sauvegarder" type="button">
              <span></span>
              <input class="btn btn-default" value="Annuler" type="reset">
            </div>
          </div>
        </form>
      </div>
  </div>
</div>
<hr>