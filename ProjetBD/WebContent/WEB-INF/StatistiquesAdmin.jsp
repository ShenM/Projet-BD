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
</head>

<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>

<body>
	<jsp:include page="HeaderAdmin.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-lg-2">
				<jsp:include page="MenuAdmin.jsp" />
			</div>
			<div class="col-lg-8">
				<div class="row" style="margin-bottom:10px">
					<button type="button" class="btn btn-warning bouton">Remboursements</button>
					<button type="button" class="btn btn-warning bouton">Bénéficiaires</button>
					<button type="button" class="btn btn-warning bouton">Contrats</button>
				</div>
				<div class="chart Bénéficiaires">
					<div class="col-sm-6 text-center" style="margin-bottom:10px">
				
				    	<h3><label class="label label-success">Répartition par age des bénéficiaires</label></h3>
				    	<div id="bar_chart" ></div>
	   				</div>
	   			</div>
			    <div class="chart Contrats">
					<div class="col-sm-6 text-center" style="margin-bottom:10px">
				
				    	<h3><label class="label label-success">Répartition des formules par an</label></h3>
				    	<div id="formules" ></div>
	   				</div>
	   			</div>
    			<div class="chart Remboursements"> 
	   				<div class="col-sm-6 text-center chart Remboursements" style="margin-bottom:10px">
				    	<h3><label class="label label-success">Somme des remboursements</label></h3>
				    	<div id="remb_chart" ></div>
	   				</div>
	   				<div class="col-sm-6 text-center" style="margin-bottom:10px">
				    	<h3><label class="label label-success">Somme des bénéficiaires</label></h3>
				    	<div id="somme_benef_chart" ></div>
	   				</div>
	   				<div class="col-sm-6 text-center" style="margin-bottom:10px">
				    	<h3><label class="label label-success">Moyenne des remboursements</label></h3>
				    	<div id="moy_remb_chart" ></div>
	   				</div>
	   				<div class="col-sm-6 text-center" style="margin-bottom:10px">
		   				<h4><label class="label label-info">Tous les remboursements sur: </label></h4>
				    	<div class="row" style="margin-bottom:10px">
							<button type="button" class="btn btn-warning btn-sm">1 an</button>
							<button type="button" class="btn btn-warning btn-sm">6 mois</button>
							<button type="button" class="btn btn-warning btn-sm">3 mois</button>
							<button type="button" class="btn btn-warning btn-sm">1 mois</button>
						</div>
					</div>
						
   				</div>
			</div>
			<div class="col-lg-2">
				<div class="panel panel-primary">
					<div class="panel-heading">Informations :</div>
					<div class="panel-content" style="padding:10px;">
					<p>Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500, quand un peintre anonyme assembla ensemble des morceaux de texte pour réaliser un livre spécimen de polices de texte. Il n'a pas fait que survivre cinq siècles, mais s'est aussi adapté à la bureautique informatique, sans que son contenu n'en soit modifié. Il a été popularisé dans les années 1960 grâce à la vente de feuilles Letraset contenant des passages du Lorem Ipsum, et, plus récemment, par son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker
					</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">var dataTab = []; var tabBenefAge = []; var tabFormules = []; var annee; var formule = [];</script>
	<c:forEach var="f" items="${remb}">
		<script type="text/javascript">
		var objet = {y: '${f.date}', a: "${f.remboursements_somme}", b: "${f.remboursements_moyenne}", c: "${f.benef_somme}"}; 
		dataTab.push(objet);
		</script>
	</c:forEach>
	<c:forEach var="b" items="${benefs}">
		<script type="text/javascript">
		var objet = {y: '${b.range}', a: "${b.frequence}"}; 
		tabBenefAge.push(objet);
		</script>
	</c:forEach>
	<c:forEach var="f" items="${formules}">
		<script type="text/javascript">
		if (annee == ${f.annee}){
			formule.push(${f.nb});
		}else{
			var objet = {y:annee, a:formule[0], b:formule[1], c:formule[2], d:formule[3], e:formule[4], f:formule[5]}; 
			tabFormules.push(objet);
			var formule = [];
			formule.push(${f.nb});
			annee = ${f.annee};
		}
		</script>
	</c:forEach>
</body>


<script type="text/javascript">
/* $('.chart').hide();
 */

$('.bouton').on('click', function() {
	$('.chart').hide();
	$('.' + $(this).text()).toggle();
});

 	tabFormules.shift();
 	config = {
	    data: tabFormules,
	    xkey: 'y',
	    ykeys: ['a', 'b', 'c', 'd', 'e', 'f'],

	    labels: ['Confort', 'Confort specifique', 'Privilege', 'Privilege specifique', 'TM+', 'TM+   specifique'],
	    hideHover: 'auto',
	    behaveLikeLine: true
	};

      config.element = 'formules';
      Morris.Bar(config);
      
      config = {
    			data: dataTab,
    		    xkey: 'y',
    		    ykeys: ['a'],
    		    labels: ['Total Remboursement'],
    		    hideHover: 'auto',
    		    yLabelFormat: function (x) { return x.toString();}
    		};
      
      config.element = 'remb_chart';
      var remb_chart = Morris.Area(config);
      
      
      config = {
  		    data: dataTab,
  		    xkey: 'y',
  		    ykeys: ['c'],
  		    labels: ['Bénéficiaires'],
  		  	yLabelFormat: function (x) { return x.toString();},
  		    hideHover: 'auto'
  		};
	  config.element = 'somme_benef_chart';
      var somme_benef_chart = Morris.Area(config);
      
      
      config = {
    		data: dataTab,
  		    xkey: 'y',
  		    ykeys: ['b'],
  		    labels: ['Moyenne remboursement'],
  		    hideHover: 'auto',
  		  	yLabelFormat: function (x) { return x.toString();},
  		    postUnits: '€'
  		};
      config.element = 'moy_remb_chart';
      var moy_remb_chart = Morris.Area(config);
      
      config = {
  		data: tabBenefAge,
  		    xkey: 'y',
  		    ykeys: ['a'],
  		    labels: ['Nombre de bénéficiaires'],
  		  	yLabelFormat: function (x) { return x.toString();},
  		    hideHover: 'auto',
  		  parseTime: false,
  		    behaveLikeLine: true
  		};
		config.element = 'bar_chart';
		Morris.Area(config);
     
      
      $('.chart').hide();
      $('.Remboursements').toggle();
      
      


</script>
</html>