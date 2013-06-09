<%-- 
    Document   : config
    Created on : 28 mai 2013, 10:42:28
    Author     : Hugo
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.logging.Level"%>
<%@ page import="java.util.logging.Level"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.HashMap"%> 
<%@ page import="java.util.Map"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="language"  content="fr"/>
        <meta name="author"    content="EcologU"/>
        <meta name="publisher" content="EcologU"/>
        <meta name="copyright" content="EcologU"/>
        <script type="text/javascript" src="/interface_admin/static/javascript/jquery-2.0.0.js"></script>
        <script type="text/javascript" src="/interface_admin/static/javascript/bootstrap.js"></script>
        <script type="text/javascript" src="/interface_admin/static/javascript/showHideObject.js"></script>
        <script type="text/javascript" src="/interface_admin/static/javascript/validatePhone.js"></script>
        <link rel="stylesheet" type="text/css" href="/interface_admin/static/css/bootstrap.css"/>
        <link rel="shortcut icon" href="/interface_admin/static/images/logo.png"/>
        
        <title>Configurations</title>
    </head>
    
    <body onLoad="disableObject('std-notif-sms', 'std-sms-field', 0);
                  disableObject('std-notif-mail', 'std-email-field', 0);
                  disableObject('holiday-notif-sms', 'holiday-sms-field', 0);
                  disableObject('holiday-notif-mail', 'holiday-email-field', 0);
                  disableObject('alerting-notif-sms', 'alerting-sms-field', 0);
                  disableObject('alerting-notif-mail', 'alerting-email-field', 0);">
        <header>
            <jsp:include page="header.jsp"/>
        </header>
        
        <h3 style="margin-left:200px;">Configurations des modes de fonctionnement</h3>
        
        <section style="margin-left:200px;margin-right:200px;">
            <article id="config-standard">
                <form class="form-horizontal well" action="config" method="POST">
                    <fieldset>
                        <legend><img src="/interface_admin/static/images/arrow_up.png"
                                     onClick="toggleForm(this, event, 'collapsed-std');"
                                     alt="Afficher/Masquer" width="16px" height="16px"/>Mode Standard:</legend>
                        <div id="collapsed-std">
                            <h4>Précisez les heures de début et de fin de journée:</h4>
                            <br/>

                            <label for="std-heure-debut">Début de journée: 
                                <select name="std-heure-debut" id="std-heure-debut" style="width:64px;">
                                    <% String strStd;
                                       HashMap<String, String> configStd = (HashMap<String, String>) request.getAttribute("config-standard");
                                       String hour[] = (configStd.get("heureDebut").toString()).split("h");
                                       for(int i = 0; i<=12; i++){
                                            strStd = i<10? "0"+i : ""+i;
                                    %>
                                        <option value="<%= strStd %>" <% if(strStd.equals(hour[0])){%>selected<% } %>><%= strStd %></option>
                                    <% } %>
                                </select> h
                                <select name="std-heure-debut" id="std-minute-debut" style="width:64px;">
                                    <% for(int i = 0; i<60; i+=5){
                                            strStd = i<10? "0"+i : ""+i;
                                    %>
                                    <option value="<%= strStd %>" <% if(strStd.equals(hour[1])){%>selected<% } %>><%= strStd %></option>
                                    <% } %>
                                </select>
                            </label>

                            <label for="std-heure-fin">Fin de journée: 
                            <select name="std-heure-fin" id="std-heure-fin" style="width:64px">
                                <% hour = (configStd.get("heureFin").toString()).split("h");
                                    for(int i = 13; i<=24; i++){ %>
                                <option value="<%= i %>" <% if(hour[0].equals(String.valueOf(i))){%>selected<% } %>><%= i %></option>
                                <% } %>
                            </select> h
                            <select name="std-heure-fin" id="std-minute-fin" style="width:64px">
                                <% for(int i = 0; i<60; i+=5){
                                    strStd = i<10? "0"+i : ""+i;
                                %>
                                <option value="<%= strStd %>" <% if(hour[1].equals(strStd)){%>selected<% } %>><%= strStd %></option>
                                <% } %>
                            </select>
                            </label>
                            <br/>

                            <h4>Options de notifications:</h4>
                            <br/>
                            Les notifications seront envoyées par:
                            <% String notif = configStd.get("notification").toString();%>
                            <label class="checkbox" for="std-notif-sms">
                                <input type="checkbox" name="std-notif" value="sms" id="std-notif-sms"
                                       onClick="disableObject('std-notif-sms', 'std-sms-field', 0);"
                                       <% if(notif.equals("sms") || notif.equals("both")){ %>
                                       checked<% } %>
                                />SMS
                            </label>
                            <div>au numéro: 
                                <input type="tel" id="std-sms-field" name="std-sms-field"
                                       placeHolder="Votre numéro"
                                       size="15" maxlength="15"
                                       value="<%= configStd.get("sms").toString() %>"
                                       onKeyPress="validatePhone();"/>
                            </div>
                            <label class="checkbox" for="std-notif-mail">
                                <input type="checkbox" name="std-notif" value="email" id="std-notif-mail"
                                       onClick="disableObject('std-notif-mail', 'std-email-field', 0);"
                                       <% if(notif.equals("email") || notif.equals("both")){ %>
                                       checked<% } %>
                                />e-mail
                            </label>
                            <div>à l'adresse: 
                                <input type="email" id="std-email-field" name="std-email-field"
                                       placeHolder="Votre e-mail"
                                       size="100" maxlength="100"
                                       value="<%= configStd.get("email").toString() %>"/>
                            </div>

                            <input type="hidden" name="action" value="validate-std"/>
                            <input type="submit" value="Valider"/>
                        </div>
                    </fieldset>
                </form>
            </article>
                        
            <article id="config-holiday">
                <form class="form-horizontal well" action="config" method="POST">
                    <fieldset>
                        <legend><img src="/interface_admin/static/images/arrow_up.png"
                                     onClick="toggleForm(this, event, 'collapsed-holiday');"
                                     alt="Afficher/Masquer" width="16px" height="16px"/>Mode Congés:</legend>
                        <div id="collapsed-holiday">
                            <h4>Précisez les heures de début et de fin de journée:</h4>
                            <br/>
                            <label for="holiday-heure-debut">Début de journée: 
                                <select name="holiday-heure-debut" id="holiday-heure-debut" style="width:64px">
                                    <% String strHoliday;
                                       HashMap<String, String> configHoliday = (HashMap<String, String>) request.getAttribute("config-holiday");
                                       String hourHoliday[] = (configHoliday.get("heureDebut").toString()).split("h");
                                       for(int i = 0; i<=12; i++){
                                            strHoliday = i<10? "0"+i : ""+i;
                                    %>
                                        <option value="<%= strHoliday %>" <% if(strHoliday.equals(hourHoliday[0])){%>selected<% } %>><%= strHoliday %></option>
                                    <% } %>
                                </select> h
                                <select name="holiday-heure-debut" id="holiday-minute-debut" style="width:64px">
                                    <% for(int i = 0; i<60; i+=5){
                                            strHoliday = i<10? "0"+i : ""+i;
                                    %>
                                    <option value="<%= strHoliday %>" <% if(strHoliday.equals(hourHoliday[1])){%>selected<% } %>><%= strHoliday %></option>
                                    <% } %>
                                </select>
                            </label>

                            <label for="holiday-heure-fin">Fin de journée: 
                            <select name="holiday-heure-fin" id="holiday-heure-fin" style="width:64px">
                                <% hourHoliday = (configHoliday.get("heureFin").toString()).split("h");
                                    for(int i = 13; i<=24; i++){ %>
                                <option value="<%= i %>" <% if(hourHoliday[0].equals(String.valueOf(i))){%>selected<% } %>><%= i %></option>
                                <% } %>
                            </select> h
                            <select name="holiday-heure-fin" id="holiday-minute-fin" style="width:64px">
                                <% for(int i = 0; i<60; i+=5){
                                    strHoliday = i<10? "0"+i : ""+i;
                                %>
                                <option value="<%= strHoliday %>" <% if(hourHoliday[1].equals(strHoliday)){%>selected<% } %>><%= strHoliday %></option>
                                <% } %>
                            </select>
                            </label>
                            <br/>

                            <h4>Options de notifications:</h4>
                            <br/>
                            Les notifications seront envoyées par:
                            <% String notifHoliday = configHoliday.get("notification").toString();%>
                            <label class="checkbox" for="holiday-notif-sms">
                                <input type="checkbox" name="holiday-notif" value="sms" id="holiday-notif-sms"
                                       onClick="disableObject('holiday-notif-sms', 'holiday-sms-field', 0);"
                                       <% if(notifHoliday.equals("sms") || notifHoliday.equals("both")){ %>
                                       checked<% } %>
                                />SMS
                            </label>
                            <div>au numéro: 
                                <input type="tel" id="holiday-sms-field" name="holiday-sms-field"
                                       placeHolder="Votre numéro"
                                       size="15" maxlength="15"
                                       value="<%= configHoliday.get("sms").toString() %>"
                                       onKeyPress="validatePhone();"/>
                            </div>
                            <label class="checkbox" for="holiday-notif-mail">
                                <input type="checkbox" name="holiday-notif" value="email" id="holiday-notif-mail"
                                       onClick="disableObject('holiday-notif-mail', 'holiday-email-field', 0);"
                                       <% if(notifHoliday.equals("email") || notifHoliday.equals("both")){ %>
                                       checked<% } %>
                                />e-mail
                            </label>
                            <div>à l'adresse: 
                                <input type="email" id="holiday-email-field" name="holiday-email-field"
                                       placeHolder="Votre e-mail"
                                       size="100" maxlength="100"
                                       value="<%= configHoliday.get("email").toString() %>"/>
                            </div>

                            <input type="hidden" name="action" value="validate-holiday"/>
                            <input type="submit" value="Valider"/>
                        </div>
                    </fieldset>
                </form>
            </article>
                        
            <article id="config-alerting">
                <form class="form-horizontal well" action="config" method="POST">
                    <fieldset>
                        <legend><img src="/interface_admin/static/images/arrow_up.png"
                                     onClick="toggleForm(this, event, 'collapsed-alerting');"
                                     alt="Afficher/Masquer" width="16px" height="16px"/>Mode Alerting:</legend>
                        <div id="collapsed-alerting">
                            <h4>Précisez les heures de début et de fin de journée:</h4>
                            <br/>
                            <label for="alerting-heure-debut">Début de journée: 
                                <select name="alerting-heure-debut" id="alerting-heure-debut" style="width:64px">
                                    <% String strAlerting;
                                       HashMap<String, String> configAlerting = (HashMap<String, String>) request.getAttribute("config-alerting");
                                       String hourAlerting[] = (configAlerting.get("heureDebut").toString()).split("h");
                                       for(int i = 0; i<=12; i++){
                                            strAlerting = i<10? "0"+i : ""+i;
                                    %>
                                        <option value="<%= strAlerting %>" <% if(strAlerting.equals(hourAlerting[0])){%>selected<% } %>><%= strAlerting %></option>
                                    <% } %>
                                </select> h
                                <select name="alerting-heure-debut" id="alerting-minute-debut" style="width:64px">
                                    <% for(int i = 0; i<60; i+=5){
                                            strAlerting = i<10? "0"+i : ""+i;
                                    %>
                                    <option value="<%= strAlerting %>" <% if(strAlerting.equals(hourAlerting[1])){%>selected<% } %>><%= strAlerting %></option>
                                    <% } %>
                                </select>
                            </label>

                            <label for="alerting-heure-fin">Fin de journée: 
                            <select name="alerting-heure-fin" id="alerting-heure-fin" style="width:64px">
                                <% hourAlerting = (configAlerting.get("heureFin").toString()).split("h");
                                    for(int i = 13; i<=24; i++){ %>
                                <option value="<%= i %>" <% if(hourAlerting[0].equals(String.valueOf(i))){%>selected<% } %>><%= i %></option>
                                <% } %>
                            </select> h
                            <select name="alerting-heure-fin" id="alerting-minute-fin" style="width:64px">
                                <% for(int i = 0; i<60; i+=5){
                                    strAlerting = i<10? "0"+i : ""+i;
                                %>
                                <option value="<%= strAlerting %>" <% if(hourAlerting[1].equals(strAlerting)){%>selected<% } %>><%= strAlerting %></option>
                                <% } %>
                            </select>
                            </label>
                            <br/>

                            <h4>Options de notifications:</h4>
                            <br/>
                            Les notifications seront envoyées par:
                            <% String notifAlerting = configAlerting.get("notification").toString();%>
                            <label class="checkbox" for="alerting-notif-sms">
                                <input type="checkbox" name="alerting-notif" value="sms" id="alerting-notif-sms"
                                       onClick="disableObject('alerting-notif-sms', 'alerting-sms-field', 0);"
                                       <% if(notifAlerting.equals("sms") || notifAlerting.equals("both")){ %>
                                       checked<% } %>
                                />SMS
                            </label>
                            <div>au numéro: 
                                <input type="tel" id="alerting-sms-field" name="alerting-sms-field"
                                       placeHolder="Votre numéro"
                                       size="15" maxlength="15"
                                       value="<%= configAlerting.get("sms").toString() %>"
                                       onKeyPress="validatePhone();"/>
                            </div>
                            <label class="checkbox" for="alerting-notif-mail">
                                <input type="checkbox" name="alerting-notif" value="email" id="alerting-notif-mail"
                                       onClick="disableObject('alerting-notif-mail', 'alerting-email-field', 0);"
                                       <% if(notifAlerting.equals("email") || notifAlerting.equals("both")){ %>
                                       checked<% } %>
                                />e-mail
                            </label>
                            <div>à l'adresse: 
                                <input type="email" id="alerting-email-field" name="alerting-email-field"
                                       placeHolder="Votre e-mail"
                                       size="100" maxlength="100"
                                       value="<%= configAlerting.get("email").toString() %>"/>
                            </div>

                            <input type="hidden" name="action" value="validate-alerting"/>
                            <input type="submit" value="Valider"/>
                        </div>
                    </fieldset>
                </form>
            </article>
            
            <!--<article id="config-all">
                <form class="form-horizontal well" action="config" method="POST">
                    <p style="text-align: center;">
                        <input type="hidden" name="action" value="validate-all"/>
                        <input type="submit" value="Tout valider"/>
                    </p>
                </form>
            </article>-->
        </section>
        
        <footer>
            <jsp:include page="footer.jsp"/>
        </footer>
    </body>
</html>
