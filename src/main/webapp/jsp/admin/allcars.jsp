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
                    <div id="adminT"><fmt:message key="cars.title"/></div>
                    <div id="back">
                        <form name ="BackForm"  method="POST" action="carrent" >
                            <input type="hidden" name="command" value="CARSREDIRECTADMIN"/> 
                            <input type="submit" value="<fmt:message key="menu.users.back" /> "/>
                        </form> 
                    </div>
                    <table id="usertable">
                        <c:forEach var="elem" items="${lst}" varStatus="status">
                            <tr>
                                <td>
                                    <b> <fmt:message key="order.car.id"/> </b> ${elem.id}<br/>
                                    <b> <fmt:message key="order.name"/> </b> ${elem.carname}<br/>
                                    <b> <fmt:message key="order.price"/></b> ${elem.price} <fmt:message key="cars.currency"/><br/>
                                    <b> <fmt:message key="order.active"/> </b> ${elem.active}<br/>
                                    <form name ="ChangeCarInfoForm"  method="POST" action="carrent" class="carchange">
                                        <input type="hidden" name="command" value="carinfo" /> 
                                        <input type="hidden" name="carid" value="${elem.id}"/>
                                        <input type="hidden" name="carname" value="${elem.carname}"/>
                                        <input type="hidden" name="carprice" value="${elem.price}"/>
                                        <input type="hidden" name="carimage" value="${elem.image}"/>
                                        <input type="hidden" name="active" value="${elem.active}" />
                                        <input type="submit" value="<fmt:message key="car.changed" /> " />
                                    </form>
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
