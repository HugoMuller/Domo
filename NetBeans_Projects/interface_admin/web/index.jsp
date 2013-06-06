<%-- 
    Document   : index
    Created on : 15 avr. 2013, 10:02:58
    Author     : Hugo
    Modified on : 06 juin 2013, 11:09:46 by Yaxi/Elo
--%>

<%@page import="entities.DBLinker"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="language" content="fr"/>
        <meta name="author" content="EcologU"/>
        <meta name="publisher" content="EcologU"/>
        <meta name="copyright" content="EcologU"/>
        <script type="text/javascript" src="/interface_admin/static/javascript/jquery-2.0.0.js"></script>
        <script type="text/javascript" src="/interface_admin/static/javascript/bootstrap.js"></script>
        <link rel="stylesheet" type="text/css" href="/interface_admin/static/css/bootstrap.css"/>
        <!--<link rel="stylesheet" type="text/css" href="static/css/font-awesome.css"/>-->
        <link rel="shortcut icon" href="/interface_admin/static/images/logo.png"/>
        
        
        <%--  Pour dessiner les graphes  --%>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript" src="/interface_admin/static/javascript/loadLineChart.js"></script>
       
       <script src="http://code.highcharts.com/highcharts.js"></script>
        <script src="http://code.highcharts.com/modules/exporting.js"></script>
        <script type="text/javascript" src="/interface_admin/static/javascript/LoadMultipleLineChart.js"></script> 
        
        <% DBLinker myLinker = new DBLinker();%>
        
        <title>Interface Administrateur</title>
    </head>

    <body>
        <header>
            <jsp:include page="app/views/partials/header.jsp"/>
        </header>
        
        <table border="0" style="margin-top : 1%" > 
            <tbody>
                <tr>
                    <td>
                        
                 <% String chaufString = myLinker.getJSonStringChauffage(); %>
                    <script>
                        var chauff=<%=chaufString%>;
                    </script>
                      <span id="chart_chauf_div"></span>

           <% String todayString = myLinker.getJSonStringTodayChauffage(); 
              String yesterdayString = myLinker.getJSonStringYesterdayChauffage(); %>
           <script>
              var today=<%=todayString%>;
              var yesterday=<%=yesterdayString%>;
           </script>
            <span id="container" style="width :50%"></span>  
                    </td>
                    
                    <td>
                    <%  String chaufYearStringAndYear = myLinker.getJSonStringYearChauffage();
                        String[] temp = chaufYearStringAndYear.split("%");
                        String year = temp[1];
                        String chaufYearString = temp[0]; %>
                        <script>
                            var chauffYear=<%=chaufYearString%>;
                            var year=<%=year%>
                        </script>
                         <span id="chart_chaufYear_div"></span>
                    </td>
                </tr>
            </tbody>
        </table>        
        
        
        <% String elecString = myLinker.getJSonStringElec(); %>
        <script>
            var elec=<%=elecString%>;
        </script>
          <div id="chart_elec_div" style="margin-left: auto; margin-right:auto"></div>
                
   <%--        <% String todayString = myLinker.getJSonStringTodayChauffage(); 
              String yesterdayString = myLinker.getJSonStringYesterdayChauffage(); %>
           <script>
              var today=<%=todayString%>;
              var yesterday=<%=yesterdayString%>;
           </script>
            <div id="container" style="width :50%"></div>  
   --%>
            
            
            
            <table>
                <tbody>
                    <tr> 
                        <td>
                            <% String ventilString = myLinker.getJSonStringVentil(); %>
                            <script>
                                var ventil=<%=ventilString%>;
                            </script>
                            <span id="chart_ventil_div" style="width: 50%; height: 500px;"></span>
                        </td>
                        <td>   
                            <% String eauString = myLinker.getJSonStringEau(); %>
                            <script>
                                var eau=<%=eauString%>;
                            </script>
                            <span id="chart_eau_div" style="width: 100%; height: 500px;"></span>
                        </td>
                    </tr>
                </tbody>
           </table>
                            
                            
     
                        
                            
        <footer>
            <jsp:include page="app/views/partials/footer.jsp"/>
        </footer>
    </body>
</html>
