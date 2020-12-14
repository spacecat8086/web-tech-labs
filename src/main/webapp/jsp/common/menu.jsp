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
                <input type="hidden" name="command" value="MAINREDIRECT" />
                <input type="submit" value="<fmt:message key='menu.main'/>"/> 
            </form>
               <form name ="menu" class="menu" method="POST" action="carrent" >
                    <input type="hidden" name="command" value="ACCOUNT" />
                    <input type="submit" value="<fmt:message key='menu.account'/>"/>
                </form>
                <form name ="menu" class="menu" method="POST" action="carrent" >
                    <input type="hidden" name="command" value="CARSREDIRECT" />
                    <input type="submit" value="<fmt:message key='menu.choose'/>"/>
                </form>
                <form name ="menu" class="menu" method="POST" action="carrent" > 
                    <input type="hidden" name="command" value="basket" />
                    <input type="submit" value="<fmt:message key='menu.basket'/>"/>
                </form>
                 <form name ="menu" class="menu" method="POST" action="carrent" > 
                    <input type="hidden" name="command" value="paid" />
                    <input type="submit" value="<fmt:message key='menu.paid.user'/>"/>
                </form>
                 <form name ="menu" class="menu" method="POST" action="carrent" > 
                    <input type="hidden" name="command" value="denied" />
                    <input type="submit" value="<fmt:message key='menu.annul'/>"/>
                </form>
                <form name ="menu" class="menu" method="POST" action="carrent" > 
                    <input type="hidden" name="command" value="CONTACTSREDIRECT" />
                    <input type="submit" value="<fmt:message key='menu.contacts'/>"/>
                </form>
                <form name ="menu" class="menu" method="POST" action="carrent" >
                    <input type="hidden" name="command" value="LOGOUTREDIRECT" />
                    <input type="submit" value="<fmt:message key='menu.logout'/>"/>
                </form>
        </div>
    </body>
</html>
