<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Demander remboursement</title>
	<link rel="stylesheet" href="styles/Header.css">
	<link rel="stylesheet" href="styles/Menu.css">
	<link rel="stylesheet" href="styles/Demande.css">
	
	<link rel="stylesheet" href="styles/bootstrap-datetimepicker.css">
    <script type="text/javascript" src="js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="js/bootstrap-datetimepicker.fr.js"></script>
    <script type="text/javascript" src="js/chosen.jquery.js"></script>
    <link rel="stylesheet" href="styles/chosen.css">
</head>


<body>

	<jsp:include page="Header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-lg-2">
				<jsp:include page="Menu.jsp" />
			</div>
			<div class="col-lg-8">
			
			
				
				<div class="container">
				    <h1>Demande de remboursement</h1>
				  	<hr>
					<div class="row">
				      
				      <!-- edit form column -->
				      <div class="col-md-9">
				        <h3>Bénéficiaire</h3>
				        
				        <form class="form-horizontal" method="post" action="DemandeRemboursement" enctype="multipart/form-data">
				          <div class="form-group">
				            <label class="col-lg-4 control-label">Bénéficiare :</label>
				            <div class="col-lg-8">
				            	<select class="select_benef" data-placeholder="Choisir un bénéficiaire" name="benef">
				            		<c:forEach items="${lBenef}" var="entry">
				            			<option value="${entry.num}"> ${entry.prenom} ${entry.nom}</option> 
				            		</c:forEach>

				            	</select>
							</div>
							
							
						</div>
							<hr><h3>Acte</h3>
							
				          <div class="form-group">
				            <label class="col-lg-4 control-label">Acte :</label>
				            <div class="col-lg-8">
				              <input class="form-control" maxlength="7" placeholder="Identifiant de l'acte  EX : PH7" type="text" name="acteId">
				            </div>
				          </div>
							<div class="form-group">
				            <label class="col-lg-4 control-label">Désignation acte :</label>
				            <div class="col-lg-8">
				              <input class="form-control" maxlength="25" placeholder="EX : PHARMACIE 65%" type="text" name="acteDesign">
				            </div>
				          </div>
				          <div class="form-group">
				            <label class="col-lg-4 control-label">Libellé barème :</label>
				            <div class="col-lg-8">
				              <input class="form-control" maxlength="45" placeholder="EX : FORMULE/CONFORT/REGIME GENERAL" type="text" name="acteLib">
				            </div>
				          </div>
				          <div class="form-group">
						    <label class="col-lg-4 control-label">Date début soins :</label>
						    <div class="input-group col-lg-4" style="padding-left: 15px;">
						      <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
						      <input class="form-control date" id="dateSoins" type="text" value="" readonly name = "acteDateDebutSoins">
						    </div>
						  </div>
						  
				          <hr><h3>Frais</h3>
				          <div class="form-group">
						    <label class="col-lg-4 control-label">Date de paiement :</label>
						    <div class="input-group col-lg-4" style="padding-left: 15px;">
						      <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
						      <input class="form-control date" id="datePaie" type="text" value="" readonly name="fraisDatePaiement">
						    </div>
						  </div>
						  <div class="form-group">
				            <label class="col-lg-4 control-label">Frais réels assuré :</label>
				            <div class="col-lg-4 ">
				              <input class="form-control" value=0  type="number" step="any" name="fraisReels">
				            </div>
				            <span class="form_info">Frais réel dépensé par l'assuré</span>
				          </div>
      				      <div class="form-group">
				            <label class="col-lg-4 control-label">Facture :</label>
				            <div class="col-lg-4">
								<input type="file" size="50" name="fraisFile">
				            </div>
				          </div>         
				          <div class="form-group" >
				         		<label class="col-lg-7 control-label" style="color: ${errorColor}; font-weight: bold;"><c:out value="${error}"/></label>
				          </div>
				          <hr>
				          <div class="form-group">
				            <label class="col-md-4 control-label"></label>
				            <div class="col-md-8">
				              <button class="btn btn-primary" type="submit">Valider </button>
				              <a href="Accueil"><button type="button" class="btn btn-default" value="Annuler"> Annuler</button></a>
				            </div>
				          </div>
				        </form>
				      </div>
				  </div>
				</div>



				<!-- 
				
	N			num_sinistre				Numéro du sinistre
	N			num_adhesion				Numéro de contrat lié au sinistre
	N			num_beneficiaire_sinistre	Numéro de bénéficiaire unique sinistré
	N			num_beneficiaire			Numéro de bénéficiaire sinistré sur le contrat (1, 2, 3, etc.)
	O			acte						Acte médical pratiqué
	O			designation_acte			Désignation de l'acte médical pratiqué
	O			libelle_bareme				Libellé bareme
	O			jour_debut_soins			**
	O			mois_debut_soins			**
	O			annee_debut_soins			**
	O			jour_paiement				**
	O			mois_paiement				**
	O			annee_paiement				**
	O			frais_reel_assure			Frais réel dépensé par l'assuré
	O			montant_secu				Montant remboursé par la sécu
	O			montant_rembourse			Montant remboursé par l'organisme		
				
				Faire en premier lieu une liste box avec les benef sous le meme contrat que la personne connecté afin de savoir a qui est destiné la demande
				
				-->
			</div>
			<div class="col-lg-2">
				<jsp:include page="Info1.jsp" />
				<jsp:include page="Info2.jsp" />
			</div>
		</div>
		
	</div>

</body>

<script type="text/javascript">
  $('#dateSoins').datetimepicker({
  todayBtn:"true",
  format:"dd-mm-yyyy", 
  autoclose:"true",
  pickerPosition:"bottom-left",
  minView:"month",
  language:"fr"
  });
  
  $('#datePaie').datetimepicker({
	  todayBtn:"true",
	  format:"dd-mm-yyyy", 
	  autoclose:"true",
	  pickerPosition:"bottom-left",
	  minView:"month",
	  language:"fr"
	  });
  
  
 $(".select_benef").chosen({
	/* disable_search_threshold: 10, */
	no_results_text: "Oops, pas de bénéficiaire trouvé",
	width: "100%"
});
</script>
</html>