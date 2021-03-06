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
					<button type="button" class="btn btn-warning bouton">Autres</button>
				</div>
				<div class="chart Bénéficiaires">
					<div class="col-sm-6 text-center" style="margin-bottom:10px">
				
				    	<h3><label class="label label-success">Répartition par âge des bénéficiaires</label></h3>
				    	<div id="bar_chart" ></div>
	   				</div>
	   				<div class="col-sm-6 text-center" style="margin-bottom:10px">
				
				    	<h3><label class="label label-success">Nombre moyen de soins partiqué par sexe</label></h3>
				    	<div id="graphSexe" ></div>
	   				</div>
	   			</div>
			    <div class="chart Autres">
					<div class="col-sm-10 text-center" style="margin-bottom:10px">
				    	<h3><label class="label label-success">Les régions avec le plus de remboursements</label></h3>
				    	<div id="graphRegionTTT" ></div>
	   				</div>
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
		   				<h3><label class="label label-success">Tous les remboursements sur: </label></h3>
				    	<div class="row" style="margin-bottom:10px">
							<button type="button" class="btn btn-info btn-sm" onclick="triTab(12)">1 an</button>
							<button type="button" class="btn btn-info btn-sm" onclick="triTab(6)">6 mois</button>
							<button type="button" class="btn btn-info btn-sm" onclick="triTab(3)">3 mois</button>
							<button type="button" class="btn btn-info btn-sm" onclick="triTab(1)">1 mois</button>
						</div>
					</div>	
   				</div>
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
						<li>Cliquer sur les <b>boutons orange</b> pour changer le <b>domaine</b> représenté.<br><br></li>
						<li>Les <b>boutons bleu ciel</b> permettent de changer <b>l'interval de temps</b> représenté.<br><br></li>
						<li><b>Survoler un graphique</b> avec la souris affiche des <b>informations complémentaires</b></li>
						</ul>
					</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">var dataTab = [];var dataTabMonth = []; var tabBenefAge = []; var tabFormules = []; var annee; var formule = [];</script>
	<c:forEach var="f" items="${remb}">
		<script type="text/javascript">
		var objet = {y: '${f.date}', a:${f.remboursements_somme}, b:${f.remboursements_moyenne}, c: ${f.benef_somme}}; 
		dataTabMonth.push(objet);
		dataTab = dataTabMonth;
		</script>
	</c:forEach>
	<c:forEach var="b" items="${benefs}">
		<script type="text/javascript">
		var objet = {y: '${b.range}', a: "${b.frequence}"}; 
		tabBenefAge.push(objet);
		</script>
	</c:forEach>
	<c:forEach var="f" items="${formules}">
		<script type="text/javascript"> // permet de trier les formule par année sous forme de tableau d'objet (Pour les morris chart)
 		if(annee == undefined){
			annee = "${f.annee}";
		}
		if (annee == "${f.annee}"){
			formule.push("${f.nb}");
		}else{
			tabFormules.push({y:annee, a:formule[0], b:formule[1], c:formule[2], d:formule[3], e:formule[4], f:formule[5]});
			annee = "${f.annee}";
			formule = [];
			formule.push("${f.nb}");
		}
		</script>
	</c:forEach>
	<script type="text/javascript"> //push dans le tab le dernier element du foreach.
		tabFormules.push({y:annee, a:formule[0], b:formule[1], c:formule[2], d:formule[3], e:formule[4], f:formule[5]});
	</script>
	<script type="text/javascript">
		var dataTabAll = [];
		dataTabAll[1] = dataTabMonth;
		var tabTrois = []; var tabSix = []; var tabDouze = [];	
		
		var ObjetsommeTrois = $.extend({}, dataTabMonth[0]);
 		var ObjetsommeSix = $.extend({}, dataTabMonth[0]);
		var ObjetsommeDouze = $.extend({}, dataTabMonth[0]);

		for	(index = 1; index < dataTabMonth.length; index++) {
			if((index+1) % 3 == 0){
				tabTrois.push(ObjetsommeTrois);
				
				var ObjetsommeTrois = $.extend({}, dataTabMonth[0]);
				ObjetsommeTrois.y = dataTabMonth[index].y;
				ObjetsommeTrois.a = 0;
				ObjetsommeTrois.b = 0;
				ObjetsommeTrois.c = 0;
				
			}
			if((index+1) % 6 == 0){
				tabSix.push(ObjetsommeSix);
				var ObjetsommeSix = $.extend({}, dataTabMonth[0]);
				ObjetsommeSix.y = dataTabMonth[index].y;
				ObjetsommeSix.a = 0;
				ObjetsommeSix.b = 0;
				ObjetsommeSix.c = 0;
			}
			if((index+1) % 12 == 0){
				tabDouze.push(ObjetsommeDouze);
				var ObjetsommeDouze = $.extend({}, dataTabMonth[0]);
				ObjetsommeDouze.y = dataTabMonth[index].y;
				ObjetsommeDouze.a = 0;
				ObjetsommeDouze.b = 0;
				ObjetsommeDouze.c = 0;
			}

			ObjetsommeTrois.a += parseFloat(dataTabMonth[index].a);
			ObjetsommeTrois.b += parseFloat(dataTabMonth[index].b);
			ObjetsommeTrois.c += parseFloat(dataTabMonth[index].c);
			 
			ObjetsommeSix.a += parseFloat(dataTabMonth[index].a);
			ObjetsommeSix.b += parseFloat(dataTabMonth[index].b);
			ObjetsommeSix.c += parseFloat(dataTabMonth[index].c);
			
			ObjetsommeDouze.a += parseFloat(dataTabMonth[index].a);
			ObjetsommeDouze.b += parseFloat(dataTabMonth[index].b);
			ObjetsommeDouze.c += parseFloat(dataTabMonth[index].c);

		} 
		dataTabAll[3] = tabTrois;
	 	dataTabAll[6] = tabSix;
		dataTabAll[12] = tabDouze;
	</script>
</body>


<script type="text/javascript">
/* $('.chart').hide();
 */

$('.bouton').on('click', function() {
	$('.chart').hide();
	$('.' + $(this).text()).toggle();
});
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
    		    lineColors: [ 
    		  		           '#0b62a4'
    		  			],
    		    hideHover: 'auto',
    		    resize: true,
    		    yLabelFormat: function (x) { return x.toString();}
    		};
      
      config.element = 'remb_chart';
      var remb_chart = Morris.Area(config);
      
      
      config = {
  		    data: dataTab,
  		    xkey: 'y',
  		    ykeys: ['c'],
  		    labels: ['Bénéficiaires'],
  		  	lineColors: [ 
  	  		           '#7a92a3'
  	  			],
  		  	yLabelFormat: function (x) { return x.toString();},
  		  	resize: true,
  		    hideHover: 'auto'
  		};
	  config.element = 'somme_benef_chart';
      var somme_benef_chart = Morris.Area(config);
      
      
      config = {
    		data: dataTab,
  		    xkey: 'y',
  		    ykeys: ['b'],
  		    labels: ['Moyenne remboursement'],
  		 	lineColors: [ 
  	  		           '#4da74d'
  	  			],
  		    hideHover: 'auto',
  		  	resize: true,
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
  			xLabelAngle: 45,
  			lineColors: [ 
  		           '#0b62a4'
  			],
  		    behaveLikeLine: true
  		};
		config.element = 'bar_chart';
		Morris.Area(config);
		
		function triTab(inter){
			remb_chart.setData(dataTabAll[inter]);
			somme_benef_chart.setData(dataTabAll[inter]);
			moy_remb_chart.setData(dataTabAll[inter]);
		}
		
		
		Morris.Donut({
		  element: 'graphSexe',
		  colors: [ 
		           '#0b62a4',
		           '#ff228a'
			],
		  data: [
		    {value: ${chartSexe.moyH}, label: 'Homme'},
		    {value: ${chartSexe.moyF}, label: 'Femme'}
		  ],
		  formatter: function (x) { return x }
		});

		Morris.Bar({
			  element: 'graphRegionTTT',
			  data: [
			    {x: '${chartRegions.label1}', y: ${chartRegions.nb1}},
			    {x: '${chartRegions.label2}', y: ${chartRegions.nb2}},
			    {x: '${chartRegions.label3}', y: ${chartRegions.nb3}}
			  ],
			  barColors: [ 
			           '#0b62a4'
				],
			  xkey: 'x',
			  ykeys: ['y'],
			  labels: ['Y'],
	      	  hideHover: 'auto'
			});
     
      
      $('.chart').hide();
      $('.Remboursements').toggle();
      
</script>
</html>