<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <fmt:setLocale value="${locale}" scope="session"/>
        <fmt:bundle basename="title" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="err.title"/></title>
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />


    </head>
    <body>
        <div class="page-wrapper"> <div class="page-buffer"> <div class="header"> <c:import url="\common\header.jsp"/> </div>
                <div class="errorMes">
                    <br/>
                    <br/>
                    <h2><fmt:message key="err.title"/> </h2><br/>
                    <b> <fmt:message key="error.clarification"/></b> <br/>
                    <div id="back"> 
                        <form name ="BackForm"  method="POST" action="carrent" >
                            <input type="hidden" name="command" value="logout"/> 
                            <input type="submit" value="<fmt:message key="error.back.button"/>"/>
                        </form> 
                    </div>
                </div> 
            </div> 
        </div>
        <div class="footer">  <c:import url="\common\footer.jsp" charEncoding="utf-8"/> </div>
    </body>
</html>
