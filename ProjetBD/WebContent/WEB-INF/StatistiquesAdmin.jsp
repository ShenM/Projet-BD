<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
					<button type="button" class="btn btn-warning bouton">test1</button>
					<button type="button" class="btn btn-warning bouton">test2</button>
					<button type="button" class="btn btn-warning bouton">Remboursements</button>
					<button type="button" class="btn btn-warning bouton">Remboursements</button>
				</div>
				<div class="col-sm-6 text-center chart test1" style="margin-bottom:10px">
			    	<h3><label class="label label-success" id="label_frais">Frais et remboursements par utilisateurs</label></h3>
			    	<div id="pie_chart" ></div>
			    </div>
				<div class="col-sm-6 text-center chart test2" style="margin-bottom:10px">
			
			    	<h3><label class="label label-success">Frais et remboursement par mois</label></h3>
			    	<div id="stacked" ></div>
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
							<button type="button" class="btn btn-warning btn-sm ">Tous</button>
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
</body>
<script type="text/javascript">
/* $('.chart').hide();
 */
 

$('.bouton').on('click', function() {
	$('.chart').hide();
	$('.' + $(this).text()).toggle();
});

var donut = Morris.Donut({
    element: 'pie_chart',
    data: [
    	{label: "Remboursement mutuelle", value:13},
	    {label: "Remboursement sécu", value:34},
	    {label: "Reste à charge", value:42}
    ],
    formatter: function (x) { return x + "€"},
    colors: [
             '#0B62A4',
             '#4DA74D',
             '#FF4040'
           ]
});

config = {
    data: [
       { y: '2010-01', a: 162625.38},
       { y: '2010-02', a: 418498.87},
       { y: '2010-03', a: 611611.8},
       { y: '2010-04', a: 634144.04},
       { y: '2010-05', a: 518340.49},
       { y: '2010-06', a: 712343.43},
       { y: '2010-07', a: 665881.32}
     ],
    xkey: 'y',
    ykeys: ['a'],
    labels: ['Total Remboursement ', 'Moyenne'],
    hideHover: 'auto',
    behaveLikeLine: true
};
      config.element = 'stacked';
      Morris.Bar(config);
      
      config.element = 'remb_chart';
      var remb_chart = Morris.Area(config);
      
	  config.element = 'somme_benef_chart';
      var somme_benef_chart = Morris.Area(config);
      
      
      config.element = 'moy_remb_chart';
      var moy_remb_chart = Morris.Area(config);
      moy_remb_chart.setData([
                                 { y: '2010-01', a:13.24526633002117608731063691154911223326},
                                 { y: '2010-02', a:16.20392883416579548534479420761218879467},
                                 { y: '2010-03', a:18.14548745030558357562451788998991277517},
                                 { y: '2010-04', a:18.93532517169304269931322782920274708868},
                                 { y: '2010-05', a:17.93131386861313868613138686131386861314},
                                 { y: '2010-06', a:19.15159107406909530850920822691221938433},
                                 { y: '2010-07', a:19.36996596561654594641766297233615498735}
                               ]);

      
      
      $('.chart').hide();
      $('.Remboursements').toggle();
</script>
</html>