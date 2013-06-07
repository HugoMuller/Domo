<%-- 
    Document   : system
    Created on : 6 juin 2013, 17:41:45
    Author     : Hugo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entities.SystemEntity" %>

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
        <link rel="shortcut icon" href="/interface_admin/static/images/logo.png"/>
        <title>Interface Administrateur</title>
    </head>
    <body>
        <header>
            <jsp:include page="header.jsp"/>
        </header>
        
        <h3 style="margin-left:200px;">Interaction avec le système</h3>
        
        <section style="margin-left:200px;margin-right:200px;">
            <article id="config-standard">
                <form class="form-horizontal well" action="system" method="POST">
                    <fieldset>
                        <% if(SystemEntity.isOff()) { %>
                        Activer le système:
                        <input type="hidden" name="action" value="on-system"/>
                        <input type="submit" value="On"/>
                        <% } else { %>
                        Désactiver le système:
                        <input type="hidden" name="action" value="off-system"/>
                        <input type="submit" value="Off"/>
                        <% } %>
                    </fieldset>
                </form>
                Changer les seuils de températures:
                <form class="form-horizontal well" action="system" method="POST">
                    <fieldset>
                        <br/>
                        <div>minimale: 
                            <input type="tel" id="tmin-field" name="tmin-field"
                                   placeHolder="Tmin"
                                   size="5" maxlength="2"
                                   onKeyPress="validatePhone();"/>
                            <input type="hidden" name="action" value="send-tmin"/>
                            <input type="submit" value="Changer"/>
                        </div>
                    </fieldset>
                </form>
                <form class="form-horizontal well" action="system" method="POST">
                    <fieldset>
                        <div>maximale: 
                            <input type="tel" id="tmax-field" name="tmax-field"
                                   placeHolder="Tmax"
                                   size="5" maxlength="2"
                                   onKeyPress="validatePhone();"/>
                            <input type="hidden" name="action" value="send-tmax"/>
                            <input type="submit" value="Changer"/>
                        </div>
                        
                    </fieldset>
                </form>
                Agir sur une source de lumière:
                <form class="form-horizontal well" action="system" method="POST">
                    <fieldset>
                        <div>LED n°: 
                            <select name="led" id="led" style="width:64px">
                                <option value="2">2</option>
                                <option value="3">3</option>
                            </select>
                            <input type="hidden" name="action" value="send-led"/>
                            <input type="submit" value="Changer"/>
                        </div>
                        
                    </fieldset>
                </form>
                Demander au capteur de remonter une mesure:
                <form class="form-horizontal well" action="system" method="POST">
                    <fieldset>
                        <div>
                            <input type="hidden" name="action" value="send-push"/>
                            <input type="submit" value="Demande"/>
                        </div>
                        
                    </fieldset>
                </form>
            </article>
        </section>
        
        <footer>
            <jsp:include page="footer.jsp"/>
        </footer>
    </body>
</html>


