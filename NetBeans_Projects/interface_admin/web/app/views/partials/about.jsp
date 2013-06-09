<%-- 
    Document   : about
    Created on : 17 mai 2013, 09:41:41
    Author     : Hugo
--%>

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
        <link rel="shortcut icon" href="/interface_admin/static/images/logo.png"/>
        <title>Interface Administrateur</title>
    </head>

    <body>
        <header>
            <jsp:include page="header.jsp"/>
        </header>
        <p>Notre société, EcologU, fondée en 2013, est composée d’une équipe de six jeunes et enthousiastes
personnes aux compétences variées et complémentaires.</p>
        <img src="/interface_admin/static/images/photo_group.png" title="team EcologU" alt="team EcologU" width="600px" height="450px"/>
        
        <footer>
            <jsp:include page="footer.jsp"/>
        </footer>
    </body>
</html>
