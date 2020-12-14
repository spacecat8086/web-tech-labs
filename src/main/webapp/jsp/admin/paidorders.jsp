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
                    <h2><fmt:message key="menu.pa"/></h2>
                   <table class="cartable">
                            <c:forEach var="elem" items="${lst}" varStatus="status">
                                <tr>
                                    <td>
                                        <span class="selection">  <b><fmt:message key="order.car.id"/></b> ${elem.id} </span><br/>
                                        <b><fmt:message key="order.name"/></b> ${elem.carName} <br/>
                                        <b><fmt:message key="order.sum"/></b> ${elem.sumToPay} <br/>
                                        <b><fmt:message key="column.surname"/>:</b> ${elem.clientSurname} <br/>
                                        <b><fmt:message key="column.passNum"/>:</b> ${elem.passNum} <br/>
                                        <b><fmt:message key="user.order.period"/>:</b> ${elem.period} <br/>
                                      <b><fmt:message key="start.date"/>:</b> ${elem.date} <br/><br/>
                                        <form name ="ReturnedWithoutDamage"  method="POST" action="carrent" class="menu">
                                            <input type="hidden" name="applId" value="${elem.id}" /> 
                                            <input type="hidden" name="command" value="return" /> 
                                            <input type="submit" value="<fmt:message key="order.return" /> " />
                                        </form>
                                        <form name ="ReturnedWithDamage"  method="POST" action="carrent" class="menu">
                                            <input type="hidden" name="command" value="returnDamage" /> 
                                            <input type="hidden" name="applId" value="${elem.id}" />  <br/>
                                            <fmt:message key="order.damage.info"/> <br/>
                                            <input type="text" maxlength="100" name="damage" value="" required/><br/>
                                            <fmt:message key="order.damage.price" /><br/>
                                            <input type="number" min="1" max="10000" name="damagecost" value="" required/><br/>
                                            <input type="submit" value="<fmt:message key="order.returnd" /> " />
                                        </form>
                                        <hr/>
                                    </td>

                                </tr>
                                <tr>
                                    <td><br/></td>

                                </tr>

                            </c:forEach>
                        </table>
                </div>
            </div> 
        </div>
        <div class="footer">  <c:import url="..\common\footer.jsp" charEncoding="utf-8"/> </div>
    </body>
</html>
