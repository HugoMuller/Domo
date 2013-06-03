<%-- 
    Document   : notif
    Created on : 30 mai 2013, 10:14:04
    Author     : Elo/Yaxi
--%>

<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        We're on the notif page :
        <% String type = (String) request.getAttribute("type"); %>
        <%= type %>
    </body>
</html>
