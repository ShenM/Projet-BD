<script type="text/javascript">
$(function () {

    // Radialize the colors
    Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function (color) {
        return {
            radialGradient: {
                cx: 0.4,
                cy: 0.3,
                r: 0.9
            },
            stops: [
                [0, color],
                [1, Highcharts.Color(color).brighten(0.2).get('rgb')] // brigther
            ]
        };
    });

    // Build the chart
    $('#container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: true,
            height : 600,
            width : 600,
            type: 'pie'
        },
        title: {
            text: 'Browser market shares. January, 2015 to May, 2015'
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
                {name: "Solde", y: 3500},
                {name: "Remboursement Sécu", y: 800},
                {name: "Remboursement Mutuelle", y: 1200},
            ]
        }]
    });
});
</script>
<script src="js/highcharts.js"></script>
<script src="js/modules/exporting.js"></script>
<div id="container"></div>
