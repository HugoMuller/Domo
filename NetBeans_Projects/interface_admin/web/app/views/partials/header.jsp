<%-- 
    Document   : header
    Created on : 22 avr. 2013, 12:32:15
    Author     : Hugo
--%>
<%@ page import="entities.ModeEntity" %>
<%@ page import="entities.NavBarEntity" %>
<%@ page import="mode.ModeType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a href="/interface_admin/home" class="brand">
                <img src="/interface_admin/static/images/logo.png" alt="EcologU" width="64px" height="64px"/>
                Interface Administrateur
            </a>
            <div class="nav-collapse">
                <ul class="nav nav-pills">
                    <li <% if(NavBarEntity.getCurrentURL().equals("http://localhost:8080/interface_admin/home")) { %> class="active" <% } %>><a href="/interface_admin/home"><i class="icon-home"></i> Accueil</a></li>
                    <li <% if(NavBarEntity.getCurrentURL().equals("http://localhost:8080/interface_admin/system")) { %> class="active" <% } %>><a href="system"><i class="icon-cog"></i> Système</a></li>
                    <li <% if(NavBarEntity.getCurrentURL().equals("http://localhost:8080/interface_admin/config")) { %> class="dropdown active" <% }else{ %>class="dropdown" <% } %>>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#"><i class="icon-play-circle"></i>  Mode <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="mode?mode=STANDARD">
                                    <% if(ModeEntity.getMode() == ModeType.STANDARD){%>
                                    <i class="icon-check"></i>
                                    <% } %>
                                    Standard
                                </a>
                            </li>
                            <li>
                                <a href="mode?mode=HOLIDAY">
                                    <% if(ModeEntity.getMode() == ModeType.HOLIDAY){%>
                                    <i class="icon-check"></i>
                                    <% } %>
                                    Congés
                                </a>
                            </li>
                            <li>
                                <a href="mode?mode=ALERTING">
                                    <% if(ModeEntity.getMode() == ModeType.ALERTING){%>
                                    <i class="icon-check"></i>
                                    <% } %>
                                    Alerting
                                </a>
                            </li>
                            <li class="divider"></li>
                            <!--<li class="nav-header">Nav header</li>-->
                            <li><a href="config"><i class="icon-wrench"></i> Configurations</a></li>
                        </ul>
                    </li>
                    <li <% if(NavBarEntity.getCurrentURL().equals("http://localhost:8080/interface_admin/Notif")) { %> class="dropdown active" <% }else{ %>class="dropdown" <% } %>>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#"><i class="icon-exclamation-sign"></i> Notifications <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="Notif?type=Chauffage">Chauffage</a></li>
                            <li class="disabled"><a href="Notif?type=Eau">Eau</a></li>
                            <li><a href="Notif?type=Eclairage">Eclairage</a></li>
                            <li class="disabled"><a href="Notif?type=Ventilation">Ventilation</a></li>
                            <li class="divider"></li>
                            <li><a href="Notif?type=All"><i class="icon-list-alt"></i> Tout</a></li>
                        </ul>
                    </li>
                    <li class="divider-vertical"></li>
                    <li><a href="mailto:ecologupi2013@gmail.com"><i class="icon-envelope"></i> Contact</a></li>
                </ul>
            </div><!-- /.nav-collapse -->
            <ul class="nav pull-right">
                <li><i class="icon-user"></i> <%=request.getRemoteUser()%><a href="logout"><i class="icon-off"></i> Déconnexion</a></li>
            </ul>
        </div>
    </div><!-- /navbar-inner -->
</div>
