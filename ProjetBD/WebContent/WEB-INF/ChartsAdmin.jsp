<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>

<div id="graph" style="height: 300px;"></div>

<script>
	Morris.Donut({
	  element: 'graph',
	  colors: [ 
	           '#0b62a4',
	           '#ff228a'
		],
	  data: [
	    {value: ${chartSexe.moyH}, label: 'Homme'},
	    {value: ${chartSexe.moyF}, label: 'Femme'}
	  ],
	  formatter: function (x) { return x }
	}).on('click', function(i, row){
	  console.log(i, row);
	});
</script>

