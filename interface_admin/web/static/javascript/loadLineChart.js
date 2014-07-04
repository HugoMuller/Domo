google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawVisualization);
     
     function drawChauff() {
        var dataChauff = google.visualization.arrayToDataTable(chauff);

        var optionsChauff = {
            title: 'Courbe des températures - Salle de réunion',
            pointSize: 3,
            colors:['#AEEE00'],
            //hAxis: {title : "Heure"},
            vAxis: {title : "Température ambiante"},
            height : 0,
            width : 0,
            legend: {position: 'none'}
            //interpolateNulls : true 
        };

        var chartChauff = new google.visualization.LineChart(document.getElementById('chart_chauf_div'));
       //chartChauff.draw(dataChauff, optionsChauff);         
     }
     
    function drawChauffYear() {
        var dataChauff = google.visualization.arrayToDataTable(chauffYear);

        var optionsChauff = {
            title: 'Courbe des températures - Salle de réunion, Bilan sur l\'année '+year,
            colors:['#96CA2D'],
            pointSize: 3,
            vAxis: {title : "Température ambiante"},
            height : 400,
            width : 800,
            legend: {position: 'none'}
          };

        var chartChauff = new google.visualization.LineChart(document.getElementById('chart_chaufYear_div'));
        chartChauff.draw(dataChauff, optionsChauff);         
     }


     function drawElec() {
        var dataElec = google.visualization.arrayToDataTable(elec);

        var optionsElec = {
          title: 'Courbe Journalière Electricité - Données récupereées via Linkee',
          colors:['#BD8D46'],
          pointSize: 3,
          vAxis: {title : "Consommation Electrique"},
          height : 400,
          width : 800,
          legend: {position: 'none'}
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
          vAxis: {title : "Vitesse Ventilateur (rpm)"},
          height : 400,
          width : 800,
          legend: {position: 'none'}
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
          vAxis: {title : "Consommation"},
          height : 400,
          width : 800,
          legend: {position: 'none'}
        };

        var chartEau = new google.visualization.LineChart(document.getElementById('chart_eau_div'));
        chartEau.draw(dataEau, optionsEau);
     }
      
      function drawVisualization() {
          drawChauff();
          drawChauffYear();
          drawElec();
          drawVentil();
          drawEau();
      }



