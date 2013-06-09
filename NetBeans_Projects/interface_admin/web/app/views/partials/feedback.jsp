<%-- 
    Document   : feedback
    Created on : 17 mai 2013, 10:17:55
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
        
        <form class="form-horizontal well" action="control_feedback.php" method="POST" onSubmit="">
            <fieldset>
                <legend>Que pensez-vous de notre application?</legend>
                <h4>Trouvez-vous l'application intuitive?</h4>
                <br/>
                <label for="ami" class="radio">
                    <input type="radio" name="origine" value="ami" id="ami" />oui
                </label>
                <label for="web" class="radio">
                    <input type="radio" name="origine" value="web" id="web" />non
                </label>
                <label class="control-label" for="textarea">Votre avis :</label>
                <div class="controls">
                    <textarea id="textarea" rows="3"></textarea>
                    <p class="help-block">Vous pouvez agrandir la fenêtre</p>
                </div>
                <button type="submit">Envoyer</button>
            </fieldset>
        </form>
        
        <footer>
            <jsp:include page="footer.jsp"/>
        </footer>
    </body>
</html>
