<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/buttons/1.1.0/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/fixedcolumns/3.2.0/js/dataTables.fixedColumns.min.js"></script>
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/buttons/1.1.0/js/buttons.colVis.min.js"></script>

<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.7/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/colreorder/1.3.0/css/colReorder.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.1.0/css/buttons.dataTables.min.css">

   


<table id="table_id" class="display">
    <thead>
        <tr>
            <th>Nom/Prénom Bénéficiare</th>
            <th>Désignation acte</th>
            <th>Libellé Barème</th>
            <th>Date début soin</th>
            <th>Date Paiement</th>
            <th>Frais réel</th>
            <th>Montant sécu</th>
            <th>Montant remboursé</th>
        </tr>
    </thead>
    <tbody>	
    <tr>
		   <td>(timestamp)</td>
		   <td>ip_address </td>
		   <td>user_agent </td>
		   <td>Country </td>
		   <td>city </td>
		   <td>email </td>
		   <td>ererere  </td>
		   <td>Non</td>
	</tr>
    <tr>
		   <td>(timestamp)</td>
		   <td>ip_address </td>
		   <td>user_agent </td>
		   <td>Country </td>
		   <td>city </td>
		   <td>email </td>
		   <td>ererere  </td>
		   <td>Non</td>
	</tr>
    <tr>
		   <td>(timestamp)</td>
		   <td>ip_address </td>
		   <td>user_agent </td>
		   <td>Country </td>
		   <td>city </td>
		   <td>email </td>
		   <td>ererere  </td>
		   <td>Non</td>
	</tr>
		   
    </tbody>
</table>
    
<script>
$(document).ready(function() {
    var table = $('#table_id').DataTable( {
        dom: 'Bfrtip',
        buttons: [
	        {
		        text : 'Choisir les champs visibles',
		        extend : 'colvis'
	        }
        ]
    } );
} );
</script>