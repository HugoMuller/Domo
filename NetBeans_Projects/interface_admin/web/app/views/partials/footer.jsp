<%-- 
    Document   : footer
    Created on : 22 avr. 2013, 12:32:15
    Author     : Hugo
--%>

<%@page import="entities.NavBarEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="navbar navbar-fixed-bottom">
    <div class="navbar-inner">
        <div class="container">
            <a data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <div class="nav-collapse">
                <ul class="nav nav-pills">
                    <li <% if(NavBarEntity.getCurrentURL().contains("/interface_admin/cgu")) { %> class="active" <% } %>><a href="cgu"><i class="icon-th-list"></i>Conditions Générales d'Utilisation</a></li>
                    <li class="divider-vertical"></li>
                    <li <% if(NavBarEntity.getCurrentURL().contains("/interface_admin/feedback")) { %> class="active" <% } %>><a href="feedback"><i class="icon-comment"></i> Feedback</a></li>
                    <li><a href="http://ecologu.insa-lyon.fr/" target="_target"><i class="icon-info-sign"></i> A propos...</a></li>
                </ul>
            </div>
            <div class="copyright">Copyright © 2013 EcologU - All Rights Reserved. Designed with <a href="http://twitter.github.io/bootstrap/" target="_target">Bootstrap</a> and <a href="http://glyphicons.com" target="_target">Glyphicons Free</a></div>
        </div>
    </div>
</div>
