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
            <th>N° Bénéficaire</th>
            <th>Date Création</th>
            <th>Désignation</th>
            <th>Libellé</th>
            <th>Date début de soin</th>
            <th>Frais date paiement</th>
            <th>Frais rééls</th>
        </tr>
    </thead>
    <tbody>	
    	<c:forEach items="${lremb}" var="remb">
		    <tr>
		    	<td><c:out value="${remb.idBenef}"/> </td>
		    	<td><c:out value="${remb.date_creation}"/> </td>
		    	<td><c:out value="${remb.acteDesignation}"/> </td>
		    	<td><c:out value="${remb.acteLibelle}"/> </td>
		    	<td><c:out value="${remb.acteDateDebutSoins}"/> </td>
		    	<td><c:out value="${remb.fraisDatePaiement}"/> </td>
		    	<td><c:out value="${remb.fraisReels}"/> </td>
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
    
    $('#table_id tbody').on('click', 'tr', function () {
        var data = table.row( this ).data();
        window.location.replace("/ProjetBD/TraitementRemboursementAdmin?id="+data[0]+"&dateC="+data[1]);
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