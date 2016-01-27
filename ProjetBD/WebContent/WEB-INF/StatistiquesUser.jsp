<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Statistiques</title>
<link rel="stylesheet" href="styles/Header.css">
<link rel="stylesheet" href="styles/Menu.css">
<link rel="stylesheet" href="styles/Demande.css">
<link rel="stylesheet" href="styles/Contrat.css">

<link rel="stylesheet"	href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
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
					<div class="col-lg-6">
						<div class="panel panel-info contrat-pannel ">
							<div class="panel-heading">
								<h3 class="panel-title">Frais et remboursements par
									utilisateurs</h3>
							</div>
							<div class="panel-body">
				            <label class="col-lg-4 control-label">Bénéficiaire :</label>
								<div class="row info-contrat" style="margin-bottom: 10px; margin-right: 20px;">
									<c:set var="mut" value="0" />
									<c:set var="secu" value="0" />
									<c:set var="ch" value="0" />
									<select class="select_benef form-control"
										data-placeholder="Choisir un bénéficiaire" name="benef">
										<c:forEach items="${frais}" var="frais">
											<option
												onclick='fonction("${frais.value.rembMut}", "${frais.value.rembSecu}", "${frais.value.aCharge}")'>
												${frais.key}</option>
											<c:set var="mut" value="${mut + frais.value.rembMut}" />
											<c:set var="secu" value="${secu + frais.value.rembSecu}" />
											<c:set var="ch" value="${ch + frais.value.aCharge}" />
										</c:forEach>
										<option selected=true onclick='fonction("${mut}", "${secu}", "${ch}")'>Tous</option>
									</select>
								</div>
								<div id="pie_chart"></div>
							</div>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="panel panel-info contrat-pannel ">
							<div class="panel-heading">
								<h3 class="panel-title">Frais et remboursement par mois</h3>
							</div>
							<div class="panel-body">
								<div id="pie_chart"></div>
								<div id="stacked"></div>
								<script type="text/javascript">
									var dataTab = [];
								</script>
								<c:forEach var="f" items="${frais_date}">
									<script type="text/javascript">
										var objet = {
											y : '${f.date}',
											a : "${f.rembMut}",
											b : "${f.rembSecu}",
											c : "${f.aCharge}"
										};
										dataTab.push(objet);
									</script>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="panel panel-info contrat-pannel">
							<div class="panel-heading">
								<h3 class="panel-title">Comparaison des frais et
									remboursements</h3>
							</div>
							<div class="panel-body">
								<div id="area_chart"></div>
							</div>
						</div>
					</div>
					<div class="col-lg-12">								
						<a  href="Accueil"><button type="button" class="btn btn-default pull-right"  style="margin-bottom: 20px;">Retour</button></a>
					</div>
				</div>
			</div>
			<div class="col-lg-2">
				<jsp:include page="Info1.jsp" />
			</div>
			<div class="col-lg-2">
				<div class="panel panel-info">
					<div class="panel-heading text-center">
						<h3 class="panel-title">
			            	<span class="glyphicon glyphicon-info-sign" style="font-size:1.5em;padding-right: 10px;"></span><b>Astuces</b>
			        	</h3>
			        </div>
					<div class="panel-content" style="padding:10px;">
					<p>
						<ul>
						<li>Cliquer sur la <b>liste bénéficiaire</b> pour choisir les informations représentées.<br><br></li>
						<li><b>Survoler un graphique</b> avec la souris affiche des <b>informations complémentaires</b></li>
						</ul>
					</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var donut = Morris.Donut({
		element : 'pie_chart',
		data : [ {
			label : "Remboursement mutuelle",
			value : "${mut}"
		}, {
			label : "Remboursement sécu",
			value : "${secu}"
		}, {
			label : "Reste à charge",
			value : "${ch}"
		} ],
		formatter : function(x) {
			return parseFloat(x).toFixed(2) + "€"
		},
		colors : [ '#0B62A4', '#4DA74D', '#FF4040' ]
	});
	function fonction(mut, sec, ch) {
		donut.setData([ {
			label : "Remboursement mutuelle",
			value : mut
		}, {
			label : "Remboursement sécu",
			value : sec
		}, {
			label : "Reste à charge",
			value : ch
		} ]);
	}

	$('.bouton').on('click', function() {
		var prenom = $(this).children().text();
		$('#label_frais').text("Frais pour " + prenom);
	});

	config = {
		data : dataTab,
		xkey : 'y',
		ykeys : [ 'a', 'b', 'c' ],
		labels : [ 'Remboursement sécu', 'Remboursement mutuelle',
				'Reste à chagre' ],
		hideHover : 'auto',
		behaveLikeLine : true
	};
	config.element = 'stacked';
	config.stacked = true;
	Morris.Bar(config);

	config.element = 'area_chart';
	Morris.Area(config); 
</script>
</html>