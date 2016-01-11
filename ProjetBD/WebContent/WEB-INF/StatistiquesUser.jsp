<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Demander remboursement</title>
	<link rel="stylesheet" href="styles/Header.css">
	<link rel="stylesheet" href="styles/Menu.css">
	<link rel="stylesheet" href="styles/Demande.css">
	
	<link rel="stylesheet" href="styles/bootstrap-datetimepicker.css">
    <script type="text/javascript" src="js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="js/bootstrap-datetimepicker.fr.js"></script>
    <script type="text/javascript" src="js/chosen.jquery.js"></script>
    <link rel="stylesheet" href="styles/chosen.css">
    
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
					<div class="col-sm-6 text-center">
				    	<label class="label label-success">Bar stacked</label>
				    	<div id="pie_chart" ></div>
				    </div>
					<div class="col-sm-6 text-center">
				    	<label class="label label-success">Bar stacked</label>
				    	<div id="stacked" ></div>
    				</div>
    				
    				<div class="col-sm-6 text-center">
				    	<label class="label label-success">Area Chart</label>
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
Morris.Donut({
    element: 'pie_chart',
    data: [
      { label: "Download Sales", value: 120 },
      { label: "In-Store Sales", value: 30 },
      { label: "Mail-Order Sales", value: 20 }
    ]
});









var data = [
            { y: '2014', a: 50, b: 90},
            { y: '2015', a: 65,  b: 75},
            { y: '2016', a: 50,  b: 50},
            { y: '2017', a: 75,  b: 60},
            { y: '2018', a: 80,  b: 65},
            { y: '2019', a: 90,  b: 70},
            { y: '2020', a: 100, b: 75},
            { y: '2021', a: 115, b: 75},
            { y: '2022', a: 120, b: 85},
            { y: '2023', a: 145, b: 85},
            { y: '2024', a: 160, b: 95}
          ],
          config = {
            data: data,
            xkey: 'y',
            ykeys: ['a', 'b'],
            labels: ['Total Income', 'Total Outcome'],
            fillOpacity: 0.6,
            hideHover: 'auto',
            behaveLikeLine: true,
            resize: true,
            pointFillColors:['#ffffff'],
            pointStrokeColors: ['black'],
            lineColors:['gray','red']
        };
      config.element = 'stacked';
      config.stacked = true;
      Morris.Bar(config);
      
      config.element = 'area_chart';
      Morris.Area(config);

</script>
</html>