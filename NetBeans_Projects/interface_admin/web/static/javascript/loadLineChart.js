google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawVisualization);
     
     function drawChauff() {
        var dataChauff = google.visualization.arrayToDataTable(chauff);

        var optionsChauff = {
            title: 'Courbe des températures',
            pointSize: 3,
            hAxis: {title : "Temps"},
            vAxis: {title : "Température ambiante"}
        };

        var chartChauff = new google.visualization.LineChart(document.getElementById('chart_chauf_div'));
        chartChauff.draw(dataChauff, optionsChauff);         
     }


     function drawElec() {
        var dataElec = google.visualization.arrayToDataTable(elec);

        var optionsElec = {
          title: 'Courbe Electricité - Données à récuperer via Linkee',
          pointSize: 3,
          hAxis: {title : "Temps"},
          vAxis: {title : "Consommation Electrique"}
        };

        var chartElec = new google.visualization.LineChart(document.getElementById('chart_elec_div'));
        chartElec.draw(dataElec, optionsElec);
     }
     
     function drawVentil() {
        var dataVentil = google.visualization.arrayToDataTable(ventil);

        var optionsVentil = {
          title: 'Courbe ventilation',
          pointSize: 3,
          hAxis: {title : "Temps"},
          vAxis: {title : "Vitesse Ventilateur"}
        };

        var chartVentil = new google.visualization.LineChart(document.getElementById('chart_ventil_div'));
        chartVentil.draw(dataVentil, optionsVentil);
     }
     
     function drawEau() {
        var dataEau = google.visualization.arrayToDataTable(eau);

        var optionsEau = {
          title: 'Consommation Eau',
          pointSize: 3,
          hAxis: {title : "Temps"},
          vAxis: {title : "Consommation"}
        };

        var chartEau = new google.visualization.LineChart(document.getElementById('chart_eau_div'));
        chartEau.draw(dataEau, optionsEau);
     }
      
      function drawVisualization() {
          drawChauff();
          drawElec();
          drawVentil();
          drawEau();
      }



