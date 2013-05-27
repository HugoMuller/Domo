<%-- 
    Document   : index
    Created on : 15 avr. 2013, 10:02:58
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
        <!--<link rel="stylesheet" type="text/css" href="static/css/font-awesome.css"/>-->
        <link rel="shortcut icon" href="/interface_admin/static/images/logo.png"/>
        
        
        <%--  Pour dessiner les graphes  --%>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript" src="/interface_admin/static/javascript/loadLineChart.js"></script>
        
        <title>Interface Administrateur</title>
    </head>

    <body>
        <header>
            <jsp:include page="app/views/partials/header.jsp"/>
        </header>

        <section>
            <article>
                <div id="chart_div" style="width: 900px; height: 500px;"></div>
            </article>
            <aside>
                <!-- Placez ici des informations complémentaires -->
            </aside>
        </section>
        
        <footer>
            <jsp:include page="app/views/partials/footer.jsp"/>
        </footer>
    </body>
</html>
