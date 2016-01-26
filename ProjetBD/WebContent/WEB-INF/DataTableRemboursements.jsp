<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/buttons/1.1.0/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/fixedcolumns/3.2.0/js/dataTables.fixedColumns.min.js"></script>
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/buttons/1.1.0/js/buttons.colVis.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> 

<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.7/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/colreorder/1.3.0/css/colReorder.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.1.0/css/buttons.dataTables.min.css">

  

 <div class="dropdown" style="margin-bottom:5px">
  <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Nombre de lignes
  <span class="caret"></span></button>
  <ul class="dropdown-menu">
    <li><a id="all">All</a></li>
    <li><a id="_10">10</a></li>
    <li><a id="_20">20</a></li>
    <li><a id="_50">50</a></li>
  </ul>
</div>
  
  
<table id="table_id" class="display">  
    <thead>
        <tr>
            <th>Nom/Prénom bénéficiare</th>
            <th>Désignation acte</th>
            <th>Libellé barème</th>
            <th>Date début soin</th>
            <th>Date de paiement</th>
            <th>Frais réels</th>
            <th>Montant sécurité sociale</th>
            <th>Montant remboursé</th>
        </tr>
    </thead>
    <tbody>	
    	<c:forEach items="${lpresta}" var="presta">
		    <tr>
		    	<td><c:out value="${presta.nomBenef}"/>  <c:out value="${presta.prenomBenef}"/></td>
		    	<td><c:out value="${presta.designationActe}"/></td>
		    	<td><c:out value="${presta.libelleBareme}"/></td>
		    	<td><c:out value="${presta.jourDebutSoins}"/>/<c:out value="${presta.moisDebutSoins}"/>/<c:out value="${presta.anneeDebutSoins}"/></td>
		    	<td><c:out value="${presta.jourPaiement}"/>/<c:out value="${presta.moisPaiement}"/>/<c:out value="${presta.anneePaiement}"/></td>
		    	<td><c:out value="${presta.fraisReelAssure}"/></td>
		    	<td><c:out value="${presta.montantSecu}"/></td>
		    	<td><c:out value="${presta.montantRembourse} €"/></td>
			</tr>
		</c:forEach>
		   
    </tbody>
</table>
    
<script>
$(document).ready(function() {
    var table = $('#table_id').DataTable( {
        dom: 'Bfrtp',
        "pagingType": "full_numbers",
        buttons: [
	        {
		        text : 'Choisir les champs visibles',
		        extend : 'colvis'
	        }
        ]
    } );
    $('#all').on( 'click', function () {
        table.page.len( -1 ).draw();
        $(".dropdown-toggle").text("Tout afficher");
    } );
     
    $('#_10').on( 'click', function () {
        table.page.len( 10 ).draw();
        $(".dropdown-toggle").text("10 lignes affichées");
    } );
    $('#_20').on( 'click', function () {
        table.page.len( 20 ).draw();
        $(".dropdown-toggle").text("20 lignes affichées");
    } ); 
    $('#_50').on( 'click', function () {
        table.page.len( 50 ).draw();
        $(".dropdown-toggle").text("50 lignes affichées");
    } ); 
    
} );



</script>