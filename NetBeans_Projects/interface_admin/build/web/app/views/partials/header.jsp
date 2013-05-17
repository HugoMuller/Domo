<%-- 
    Document   : header
    Created on : 22 avr. 2013, 12:32:15
    Author     : Hugo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a href="#" class="brand">
                <img src="/interface_admin/static/images/logo.png" alt="EcologU" width="64px" height="64px"/>
                Interface Administrateur
            </a>
            <div class="nav-collapse">
                <ul class="nav nav-pills">
                    <li class="active"><a href="#"><i class="icon-home"></i> Accueil</a></li>
                    <li><a href="#">Link1</a></li>
                    <li><a href="#">Link2</a></li>
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#"><i class="icon-play-circle"></i>  Mode <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Standard</a></li>
                            <li><a href="#">Congés</a></li>
                            <li><a href="#">Alerting</a></li>
                            <li class="divider"></li>
                            <!--<li class="nav-header">Nav header</li>-->
                            <li><a href="#"><i class="icon-wrench"></i> Configurations</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#"><i class="icon-exclamation-sign"></i> Notifications <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Chauffage</a></li>
                            <li class="disabled"><a href="#">Eau</a></li>
                            <li><a href="#">Eclairage</a></li>
                            <li class="disabled"><a href="#">Ventillation</a></li>
                            <li class="divider"></li>
                            <li><a href="#"><i class="icon-list-alt"></i> Tout</a></li>
                        </ul>
                    </li>
                    <li class="divider-vertical"></li>
                    <li><a href="mailto:contact@ecologu.com"><i class="icon-envelope"></i> Contact</a></li>
                </ul>
            </div><!-- /.nav-collapse -->
            <ul class="nav pull-right">
                <li><a href="#">Se connecter</a></li>
            </ul>
        </div>
    </div><!-- /navbar-inner -->
</div>
