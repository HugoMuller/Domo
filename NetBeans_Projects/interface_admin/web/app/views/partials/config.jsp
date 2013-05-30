<%-- 
    Document   : config
    Created on : 28 mai 2013, 10:42:28
    Author     : Hugo
--%>

<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.Iterator"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.HashMap"%>
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
        <script type="text/javascript" src="/interface_admin/static/javascript/showHideObject.js"></script>
        <link rel="stylesheet" type="text/css" href="/interface_admin/static/css/bootstrap.css"/>
        <link rel="shortcut icon" href="/interface_admin/static/images/logo.png"/>
        
        <title>Configurations</title>
    </head>
    <body>
        <header>
            <jsp:include page="header.jsp"/>
        </header>
        
        <h3>Configurations des modes de fonctionnement</h3>
        
        <section>
            <article id="config-standard">
                <form class="form-horizontal well" action="config" method="POST">
                    <fieldset>
                        <legend>Mode Standard:</legend>
                        <h4>Précisez les heures de début et de fin de journée:</h4>
                        <br/>
                        <label for="std-heure-debut">Début de journée: 
                            <select name="std-heure-debut" id="std-heure-debut" style="width:64px">
                                <% String strStd;
                                    HashMap<String, String> configStd = (HashMap<String, String>) request.getAttribute("config-standard");
                                    //String hour = configStd.get("heureDebut").toString();
                                   //String strHourStd[] = hour.split("h");
                                   //hour = strHourStd[0]+", "+strHourStd[1];
                                   /* Iterator iterator = configStd.keySet().iterator();  
        while (iterator.hasNext()) {  
           String key = iterator.next().toString();  
           String value = configStd.get(key).toString();  
            String msg = key+", "+value;*/
                                    request.toString();
                                    %>

                                    <%
                                    for(int i = 0; i<=12; i++){
                                        strStd = i<10? "0"+i : ""+i;
                                %>
                                
                                <option value="<%= strStd %>" <% if(String.valueOf(i).equals("9")){%>selected<% } %>><%= strStd %>h</option>
                                <% } %>
                            </select>
                            <select name="std-minute-debut" id="std-minute-debut" style="width:64px">
                                <% for(int i = 0; i<60; i+=5){
                                        strStd = i<10? "0"+i : ""+i;
                                %>
                                <option value="<%= strStd %>" <% if(strStd.equals("45")){%>selected<% } %>><%= strStd %></option>
                                <% } %>
                            </select>
                        </label>
                        
                        <label for="std-heure-fin">Fin de journée: 
                        <select name="std-heure-fin" id="std-heure-fin" style="width:64px">
                            <% String strHourEndStd[] = "15h45".split("h");  //(((HashMap<String, String>) request.getAttribute("config-standard")).get("heureFin")).split("h");
                                for(int i = 13; i<=24; i++){ %>
                            <option value="<%= i %>" <% if(strHourEndStd[0].equals(String.valueOf(i))){%>selected<% } %>><%= i %>h</option>
                            <% } %>
                        </select>
                        <select name="std-minute-fin" id="std-minute-fin" style="width:64px">
                            <% for(int i = 0; i<60; i+=5){
                                strStd = i<10? "0"+i : ""+i;
                            %>
                            <option value="<%= strStd %>" <% if(strHourEndStd[1].equals(String.valueOf(i))){%>selected<% } %>><%= strStd %></option>
                            <% } %>
                        </select>
                        </label>
                        <br/>
                        
                        <h4>Options de notifications:</h4>
                        <br/>
                        Les notifications seront envoyées par:
                        <label class="checkbox" for="std-notif-sms">
                            <input type="checkbox" name="std-notif" value="SMS" id="std-notif-sms"
                                   onClick="disableObject('std-notif-sms', 'sms-field', 0);"/>SMS
                        </label>
                        <div>au numéro: 
                            <input type="tel" id="sms-field" placeHolder="Votre numéro" size="15" maxlength="15"/>
                        </div>
                        <label class="checkbox" for="std-notif-mail">
                            <input type="checkbox" name="std-notif" value="e-mail" id="std-notif-mail"
                                   onClick="disableObject('std-notif-mail', 'email-field', 0);"/>e-mail
                        </label>
                        <div>à l'adresse: 
                            <input type="email" id="email-field" placeHolder="Votre e-mail" size="100" maxlength="100"/>
                        </div>

                        <!--<button type="submit">Valider</button>-->
                        <input type="hidden" name="action" value="validate-std"/>
                        <input type="submit" value="Valider"/>
                    </fieldset>
                </form>
            </article>
            <aside>
                <!-- Placez ici des informations complémentaires -->
            </aside>
        </section>
        
        <footer>
            <jsp:include page="footer.jsp"/>
        </footer>
    </body>
</html>
