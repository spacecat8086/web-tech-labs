<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="custom" uri="customtags" %>

<!DOCTYPE html>
<html>
    <head>
        <fmt:setLocale value="${locale}" scope="session"/>
        <fmt:bundle basename="title" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="header.title"/></title>
        <link rel="stylesheet" href="..\css\style.css" type="text/css">
                        <link rel="shortcut icon" href="..\img\favicon.ico" type="image/x-icon" />

    </head>
    <body>
        <div class="page-wrapper"> 
            <div class="page-buffer">    
                <div class="header"> 
                    <c:import url='..\common\header.jsp' charEncoding="utf-8"/> 
                </div>
                <div id="menu">     
                    <c:import url="..\common\menu_admin.jsp" charEncoding="utf-8"/> 
                </div>
                 <custom:info-tag type="${userType}" username="${userName}">
                    <fmt:message key='infotag.access'/>
                </custom:info-tag>
                <div id="page-content">  
                    <h2><fmt:message key="menu.arch"/></h2>
                    <table id="usertable">
                        <tr> 
                                <td>    <b><fmt:message key="order.car.id"/></b></td> 
                                <td>    <b><fmt:message key="order.name"/></b></td> 
                                <td>    <b><fmt:message key="order.sum"/></b></td> 
                                <td>    <b><fmt:message key="column.surname"/></b> 
                                <td>    <b><fmt:message key="column.passNum"/></b> 
                            </tr>
                        <c:forEach var="elem" items="${lst}" varStatus="status">
                            
                            <tr>
                                <td>${elem.id}</td>
                                <td>${elem.carName}</td>
                                <td>${elem.sumToPay}</td>
                                <td>${elem.clientSurname}</td>
                                <td>${elem.passNum}</td>
                            </tr>
                        </c:forEach>
                    </table>    
                            <br/>
                            <br/>
                            <br/>
                            <br/>
                            <br/>
                            <br/>
                            <br/>
                            <br/>
                            
                </div>

            </div> 
            
        </div>
        <div class="footer">  <c:import url="..\common\footer.jsp" charEncoding="utf-8"/> </div>
    </body>
</html>
