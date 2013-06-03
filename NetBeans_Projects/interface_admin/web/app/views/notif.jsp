<%-- 
    Document   : notif
    Created on : 30 mai 2013, 10:14:04
    Author     : Elo/Yaxi
--%>

<%@page import="entities.DBLinker"%>
<%@page import="java.util.List"%>

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
        
        <title>Interface Administrateur</title>
    </head>

    <body>
        <header>
            <jsp:include page="partials/header.jsp"/>
        </header>

        <% String type = (String) request.getAttribute("type"); 
            DBLinker myLinker = new DBLinker();
            List<String> listNotif = null;
            if (type.equals("Chauffage")) {
                listNotif = myLinker.getChauffageNotif();
            } else if (type.equals("Eclairage")) {
                listNotif = myLinker.getEclairageNotif();
            } else if (type.equals("All")) {
                listNotif = myLinker.getAllNotif();
            }
            for (String notif : listNotif) {%> 
                <%= notif %>
        <% } %>
        
        <footer>
            <jsp:include page="partials/footer.jsp"/>
        </footer>
    </body>
</html>
