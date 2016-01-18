<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Statistiques</title>
	<link rel="stylesheet" href="styles/Header.css">
	<link rel="stylesheet" href="styles/Menu.css">
	<link rel="stylesheet" href="styles/Demande.css">
	
    
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>



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
					<div class="row col-lg-8" style="margin-bottom:10px">
						<c:set var="mut" value="0"/>
						<c:set var="secu" value="0"/>
						<c:set var="ch" value="0"/>
						<c:forEach var="frais" items="${frais}">	
							<%-- <div class="alert alert-warning col-lg-2" ><b>${frais.key}</b></div> --%>
							<button type="button" class="btn btn-warning bouton" onclick='fonction("${frais.value.rembMut}", "${frais.value.rembSecu}", "${frais.value.aCharge}")'>
								<span class="glyphicon glyphicon-user" style="vertical-align:middle">${frais.key}</span>
							</button>
							<c:set var="mut" value="${mut + frais.value.rembMut}"/>
							<c:set var="secu" value="${secu + frais.value.rembSecu}"/>
							<c:set var="ch" value="${ch + frais.value.aCharge}"/>
						</c:forEach>
							<button type="button" class="btn btn-info bouton" onclick='fonction("${mut}", "${secu}", "${ch}")'>
								<span class="glyphicon glyphicon-user" style="vertical-align:middle">Tous</span>
							</button>
					</div>
					<div class="col-sm-6 text-center" style="margin-bottom:10px">
				    	<h3><label class="label label-success" id="label_frais">Frais et remboursements par utilisateurs</label></h3>
				    	<div id="pie_chart" ></div>
				    </div>
					<div class="col-sm-6 text-center" style="margin-bottom:10px">
				    	<h3><label class="label label-success">Frais et remboursement par mois</label></h3>
				    	<div id="stacked" ></div>
				    	<script type="text/javascript">var dataTab = [];</script>
						<c:forEach var="f" items="${frais_date}">
							<script type="text/javascript">
							
							var objet = {y: '${f.date}', a: "${f.rembMut}", b: "${f.rembSecu}", c: "${f.aCharge}"}; 
							dataTab.push(objet);
								/*  dataTab = dataTab + "{ y: '${f.date}', a: ${f.rembMut}, b: ${f.rembSecu}, c: ${f.aCharge} },";*/
							</script>
						</c:forEach>
    				</div>
    				
    				<div class="col-sm-6 text-center" style="margin-bottom:10px">
				    	<h3><label class="label label-success">Comparaison des frais et remboursements</label></h3>
				    	<div id="area_chart" ></div>
    				</div>
				</div>
				    
			</div>
			<div class="col-lg-2">
				<jsp:include page="Info1.jsp" />
				<jsp:include page="Info2.jsp" />
			</div>
		</div>
		
	</div>

</body>
<script type="text/javascript">



var donut = Morris.Donut({
    element: 'pie_chart',
    data: [
    	{label: "Remboursement mutuelle", value:"${mut}"},
	    {label: "Remboursement sécu", value:"${secu}"},
	    {label: "Reste à charge", value:"${ch}"}
    ],
    formatter: function (x) { return x + "€"},
    colors: [
             '#0B62A4',
             '#4DA74D',
             '#FF4040'
           ]
});
function fonction(mut, sec, ch){
	donut.setData([
	       	    {label: "Remboursement mutuelle", value: mut},
	       	    {label: "Remboursement sécu", value: sec},
	       	    {label: "Reste à charge", value: ch}
	       	  ]);
}

$('.bouton').on('click', function() {
	var prenom = $(this).children().text();
    $('#label_frais').text("Frais pour " + prenom);
});

config = {
    data: dataTab,
    xkey: 'y',
    ykeys: ['a', 'b', 'c'],
    labels: ['Remboursement sécu', 'Remboursement mutuelle', 'Reste à chagre'],
    hideHover: 'auto',
    behaveLikeLine: true
};
      config.element = 'stacked';
      config.stacked = true;
      Morris.Bar(config);
      
      config.element = 'area_chart';
      Morris.Area(config);

</script>
</html>