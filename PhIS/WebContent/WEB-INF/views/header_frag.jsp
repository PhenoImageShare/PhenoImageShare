<%--
  Created by IntelliJ IDEA.
  User: ilinca
  Date: 14/11/2016
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
    String baseUrl = (request.getAttribute("baseUrl") != null &&  ! ((String) request.getAttribute("baseUrl")).isEmpty()) ? (String) request.getAttribute("baseUrl") : (String) application.getInitParameter("baseUrl");
%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>${title}</title>
    <!-- jQuery -->
    <script type="text/javascript" charset="utf8" src="${djangoBaseUrl}/phis/static/queries/assets/jquery/v1.10.2/jquery.min.js"></script>
    <!-- jQuery UI -->
    <script type="text/javascript" charset="utf8" src="${djangoBaseUrl}/phis/static/queries/assets/jquery-ui/v1.11.0/jquery-ui.js"></script>
    <!-- Bootstrap -->
    <script type="text/javascript" charset="utf8" src="${djangoBaseUrl}/phis/static/queries/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${djangoBaseUrl}/phis/static/queries/css/bootstrap.css"/>
    <link rel="stylesheet" href="${mappedHostname}${baseUrl}/css/documentation.css">
    <!-- FlatUI CSS -->
    <link rel="stylesheet" href="${djangoBaseUrl}/phis/static/queries/css/flat-ui.detailed_view.css">
</head>

<body>


<div class="container">
    <div class="navbar navbar-default navbar-fixed-top">
        <div class="navbar-header">
            <a id="phISMenuItem" href="/" class="navbar-brand">PhenoImageShare</a>
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="navbar-collapse collapse" id="navbar-main">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="/documentation/">Documentation</a>
                    <span class="dropdown-arrow"></span>

                </li>

                <li>
                    <a id="aboutpage" href="/documentation/about/">About</a>
                </li>

                <li>
                    <a id="news" href="/documentation/news/" >News</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <!--  <li data-toggle="tooltip" data-placement="bottom" title="Register for an account"><a id="registerButton" href="#" target="_blank">Register</a></li>-->
            </ul>
        </div>
    </div>


    <div class="clear"></div>
    <br/> <br/> <br/> <br/>