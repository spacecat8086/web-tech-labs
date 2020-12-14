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
                    <c:import url="..\common\menu.jsp" charEncoding="utf-8"/> 
                </div>
                 <custom:info-tag type="${userType}" username="${userName}">
                    <fmt:message key='infotag.access'/>
                </custom:info-tag>
                <div id="page-content">  
                    <h2><fmt:message key="cars.title"/></h2>
                    <div id="cars"> 
                        <ul>        <c:forEach var="elem" items="${lst}" varStatus="status">
                                <li>
                                    <form name ="order"  method="POST" action="carrent" class="order">
                                            <input type="hidden" name="carId" value="${elem.id}" /> 
                                            <input type="hidden" name="carName" value="${elem.carname}" /> 
                                            <input type="hidden" name="carPrice" value="${elem.price}" /> 
                                            <input type="hidden" name="carImage" value="${elem.image}" /> 
                                            <c:out value="${elem.carname}"/>
                                            <c:out value="${elem.price}"/> 
                                            <fmt:message key="cars.currency"/>
                                            <img src="${elem.image}" width="200" height="135"/>
                                            <input type="hidden" name="command" value="DEFINEORDER" /> 
                                            <input type="submit" value="<fmt:message key="cars.order"/>" />
                                        </form> 
                                </li>  
                            </c:forEach>
                        </ul>
                    </div>               
                </div>

            </div> 
            
        </div>
        <div class="footer">  <c:import url="..\common\footer.jsp" charEncoding="utf-8"/> </div>
    </body>
</html>
