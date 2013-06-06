/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $('#container').highcharts({
        title: {
            text: 'Variation de la température journalière'
        },
        chart: {
            height : 400,
            width : 650
        },
        xAxis: {
            title: {
                text:'Temps (en h)'
            },
            lineColor : '#000000',
            lineWidth : 1,
            min: 6,
            max : 22
        },
        yAxis: {
            title: {
                text:'Température en *C'
            },
            min: 0
        },
        
        colors: [
            '#787746',
            '#B5E655'
        ],

        series: [{
            name: 'Today',
            data: today
        }, {
            name: 'Yesterday',
            data: yesterday
        }]
    });
});
