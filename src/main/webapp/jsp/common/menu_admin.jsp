<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
    <head>
        <fmt:setLocale value="${locale}" scope="session"/>
        <fmt:bundle basename="title" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="header.title"/></title>
        <link rel="stylesheet" href="css/style.css" type="text/css">
    </head>
    <body>
        <div id="login">
            <form name ="menu" class="menu" method="POST" action="carrent" >
                <input type="hidden" name="command" value="mainAdmRedirect" />
                <input type="submit" value="<fmt:message key='menu.main'/>"/> 
            </form>
               <form name ="menu" class="menu" method="POST" action="carrent" >
                    <input type="hidden" name="command" value="users" />
                    <input type="submit" value="<fmt:message key='menu.users'/>"/>
                </form>
                <form name ="menu" class="menu" method="POST" action="carrent" >
                    <input type="hidden" name="command" value="carsRedirectAdmin" />
                    <input type="submit" value="<fmt:message key='menu.allcars'/>"/>
                </form>
                <form name ="menu" class="menu" method="POST" action="carrent" > 
                    <input type="hidden" name="command" value="newOrders" />
                    <input type="submit" value="<fmt:message key='menu.atc'/>"/>
                </form>
                <form name ="menu" class="menu" method="POST" action="carrent" > 
                    <input type="hidden" name="command" value="unpaidOrders" />
                    <input type="submit" value="<fmt:message key='menu.up'/>"/>
                </form>
                <form name ="menu" class="menu" method="POST" action="carrent" > 
                    <input type="hidden" name="command" value="paidOrders" />
                    <input type="submit" value="<fmt:message key='menu.pa'/>"/>
                </form>
                <form name ="menu" class="menu" method="POST" action="carrent" > 
                    <input type="hidden" name="command" value="archiveOrders" />
                    <input type="submit" value="<fmt:message key='menu.arch'/>"/>
                </form>
                <form name ="menu" class="menu" method="POST" action="carrent" > 
                    <input type="hidden" name="command" value="repairBills" />
                    <input type="submit" value="<fmt:message key='menu.repair'/>"/>
                </form>
                <form name ="menu" class="menu" method="POST" action="carrent" >
                    <input type="hidden" name="command" value="logoutARedirect" />
                    <input type="submit" value="<fmt:message key='menu.logout'/>"/>
                </form>
        </div>
    </body>
</html>
