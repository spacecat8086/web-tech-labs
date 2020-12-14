<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="custom" uri="customtags" %>

<!DOCTYPE html>

<html>
    <head>
        <fmt:setLocale value="${locale}" scope="session"/>
        <fmt:bundle basename="title" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="header.title"/></title>
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
    </head>
    <body>

        <div class="page-wrapper">   
            <div class="header"> 
                <c:import url="common\header.jsp" charEncoding="utf-8"/> 
            </div>
            <div class="page-buffer">
                <center>
                    <div id="login">
                        <form name ="LanguageForm" method="POST" action="carrent" class="lang">
                            <input type="hidden" name="command" value="RU" />
                            <input type="hidden" name="userType" value="GUEST" /> 
                            <input name= "language" type="submit" value="RU"/> 
                            <input type="hidden" name="command" value="EN" />
                            <input type="hidden" name="userType" value="GUEST" /> 
                            <input name = "language" type="submit" value="EN"/>
                        </form>

                        <form name='form-login' method="POST" action="carrent" >                  
                            <h1><fmt:message key="login.title"/></h1> <br/>
                            <input type="hidden" name="command" value="Login" />
                            <input type="hidden" name="userType" value="GUEST" /> 
                            <span class="fontawesome-user"></span>
                            <input type="text" id="user" maxlength="16" name= "login" placeholder="<fmt:message key="login.name" />" required>
                            <c:if test="${not empty errorLogin}"><div class="alert"><fmt:message key="active.restricted"/></div></c:if>
                                <span class="fontawesome-lock"></span>
                                <input type="password" maxlength="16" name="password" id="pass" placeholder="<fmt:message key="login.password" />" required>
                            <c:if test="${not empty errorPassword}"><div class="alert"><fmt:message key="error.pass.message"/></div></c:if>
                            <input type="submit" value="<fmt:message key="login.enter"/>">
                            <div class='notice'><a href='mailto:hamzees@gmail.com?Subject=<fmt:message key="login.forgot"/>' target="_top"><fmt:message key="login.forgot"/></a></div>

                        </form>
                        <form name ="RegisterForm"  method="POST" action="carrent">
                            <input type="hidden" name="command" value="REGISTERREDIRECT" /> 
                            <input type="hidden" name="userType" value="GUEST" /> 
                            <input type="submit" value="<fmt:message key="login.register" /> " />
                        </form> 
                    </div></center> </div>
        </div>
        <div class="footer"> 
            <c:import url="common\footer.jsp" charEncoding="utf-8"/> 
        </div>
    </body>
</html>
