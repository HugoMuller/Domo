google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawVisualization);
     
     function drawChauff() {
        var dataChauff = google.visualization.arrayToDataTable(chauff);

        var optionsChauff = {
            title: 'Graph Chauff',
            chartArea:{width:"100%"}
         };

        var chartChauff = new google.visualization.LineChart(document.getElementById('chart_chauf_div'));
        chartChauff.draw(dataChauff, optionsChauff);         
     }


     function drawElec() {
        var dataElec = google.visualization.arrayToDataTable(elec);

        var optionsElec = {
          title: 'Graph Elec',
          //chartArea:{left:250,top:50,width:"10%",height:"75%"}
            chartArea:{width:"100%",height:"75%"}
        };

        var chartElec = new google.visualization.LineChart(document.getElementById('chart_elec_div'));
        chartElec.draw(dataElec, optionsElec);
     }
     
     function drawVentil() {
        var dataVentil = google.visualization.arrayToDataTable(ventil);

        var optionsVentil = {
          title: 'Graph Ventil',
          chartArea:{width:"100%"}
        };

        var chartVentil = new google.visualization.LineChart(document.getElementById('chart_ventil_div'));
        chartVentil.draw(dataVentil, optionsVentil);
     }
      
      function drawVisualization() {
          drawChauff();
          drawElec();
          drawVentil();
      }



