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
                    <div id="welcome_msg">
                        
                        <h2><fmt:message key="menu.allcars"/></h2>
                        <div id="login"> 
                            <form name ="AllCarsForm"  method="POST" action="carrent">
                                <input type="hidden" name="command" value="allcars" /> 
                                <input type="submit" value="<fmt:message key="menu.cars.getall" /> " />
                            </form> 
                            <form name ="AddNewCarCommand"  method="POST" action="carrent">
                                <input type="hidden" name="command" value="addnewcar" /> 
                                <input type="submit" value="<fmt:message key="menu.cars.addnew" /> " />
                            </form> 
                        </div>
                    </div>               
                </div>

            </div> 
               
        </div>
        <div class="footer">  <c:import url="..\common\footer.jsp" charEncoding="utf-8"/> </div>
    </body>
</html>
