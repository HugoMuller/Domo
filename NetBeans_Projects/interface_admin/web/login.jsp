<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="language"  content="fr"/>
        <meta name="author"    content="EcologU"/>
        <meta name="publisher" content="EcologU"/>
        <meta name="copyright" content="EcologU"/>
        <script type="text/javascript" src="/interface_admin/static/javascript/jquery-2.0.0.js"></script>
        <script type="text/javascript" src="/interface_admin/static/javascript/bootstrap.js"></script>
        <link rel="stylesheet" type="text/css" href="/interface_admin/static/css/bootstrap.css"/>
        <link rel="shortcut icon" href="/interface_admin/static/images/logo.png"/>
        <title>Interface Administrateur</title>
    </head>
    
    <body>
        <div class="container login">
            <form method="POST" action="j_security_check" class="form-signin">
                <h2 class="form-signin-heading">Authentification</h2>
                <input type="text" name="j_username" class="input-block-level" placeholder="Login"/>
                <input type="password" name="j_password" class="input-block-level" placeholder="Mot de passe"/>
                <button class="btn btn-large btn-primary" type="submit">S'authentifier</button>
            </form>
        </div>
    </body>
</html>
