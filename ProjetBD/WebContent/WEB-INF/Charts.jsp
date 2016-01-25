<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<link rel="stylesheet"	href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>
	
<div class="panel panel-default"> 
	<div class="panel-heading"> Graphique des 5 derniers remboursements </div>
	<div class="panel-body"> <div id="pie_chart"></div></div>
</div>

<script type="text/javascript">

var donut = Morris.Donut({
	element : 'pie_chart',
	data : [ {
		label : "Remboursement mutuelle",
		value : "${chart.remboursementMutuelle}"
	}, {
		label : "Remboursement sécu",
		value : "${chart.remboursementSecu}"
	}, {
		label : "Reste à charge",
		value : "${chart.fraisPayer}"
	} ],
	formatter : function(x) {
		return parseFloat(x).toFixed(2) + "€"
	},
	colors : [ '#0B62A4', '#4DA74D', '#FF4040' ]
});
</script>

