<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script type="text/javascript">
$(function () {

    // Radialize the colors
    Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function (color) {
        return {
            radialGradient: {
                cx: 0,
                cy: 0,
                r: 0
            },
            stops: [
                [0, color],
                [1, Highcharts.Color(color).brighten(0.2).get('rgb')] // darken
            ]
        };
    });

    // Build the chart
    $('#container').highcharts({
        chart: {
            margin: [0, 0, 0, 0],
            spacingTop: 0,
            spacingBottom: 0,
            spacingLeft: 0,
            spacingRight: 0,
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: true,
            type: 'pie'
        },
        title: {
            text: ""
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                size:'100%',
                dataLabels: {
                    enabled: true,
                    format: '<div style="font-size: 200%">{y}</div>',
                    inside : true,
                    distance : -120,
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'blue'
                    }
                }
            },
            showInLegend: true
        },
        series: [{
            name: "Montant",
            colorByPoint: true,
            data: [
                {name: "Mes frais", y: ${chart.fraisPayer}},
                {name: "Remboursement Sécu", y: ${chart.remboursementSecu}},
                {name: "Remboursement Mutuelle", y: ${chart.remboursementMutuelle}},
            ]
        }]
    });
});
</script>
<script src="js/highcharts.js"></script>
<!--script src="js/modules/exporting.js"></script-->
<div class="panel panel-default"> 
	<div class="panel-heading"> Votre solde et vos remboursements</div>
	<div class="panel-body"> <div id="container"></div></div>
</div>
