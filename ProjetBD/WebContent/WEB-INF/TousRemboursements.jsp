<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.7/css/jquery.dataTables.css">

<table id="table_id" class="display">
    <thead>
        <tr>
            <th>Date</th>
            <th>Adresse IP</th>
            <th>Navigateur</th>
            <th>Pays</th>
            <th>Ville</th>
            <th>Email</th>
            <th>Evenements</th>
            <th>Professeur</th>
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
$(document).ready( function () {
    $('#table_id').DataTable({
  "order": [[ 0, "desc" ]],
  "aLengthMenu": [[10,25,50,100,-1],[ 10, 25, 50, 100, "All" ]],
  "columnDefs": [
    { "width": "20%", "targets": 2 },
    { "width": "1%", "targets": 7 },
    { "width": "10%", "targets": 0 }
  ]});
} );
</script>