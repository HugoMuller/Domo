/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $('#container').highcharts({
        title: {
            text: 'Température, variation journalière'
        },
        xAxis: {
            title: {
                text: 'x label'
            },
            min: 6,
            max : 22
        },
        yAxis: {
            title: {
                text: 'y laber'
            },
            min: 0
        },

        series: [{
            name: 'Today',
            data: today
        }, {
            name: 'Yesterday',
            data: yesterday
        }]
    });
});
