<%-- 
    Document   : reloadCurves
    Created on : 15 juin 2013, 20:16:02
    Author     : Hugo
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entities.DBLinker"%>

<% DBLinker myLinker = new DBLinker();%>
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
                    <% String chaufYearStringAndYear = myLinker.getJSonStringYearChauffage();
                        if (!chaufYearStringAndYear.equals("exception")) {
                            String[] temp = chaufYearStringAndYear.split("%");
                         String year = temp[1];
                        String chaufYearString = temp[0]; %>
                            <script>
                                var chauffYear=<%=chaufYearString%>;
                                var year=<%=year%>
                            </script>
                             <span id="chart_chaufYear_div"></span>
                    <% }else{%>
                    No Data Found 
                    <%}%>                                 
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
