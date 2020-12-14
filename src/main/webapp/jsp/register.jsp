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
        <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />

    </head>
    <body>
        <div class="header"> 
            <c:import url="\common\header.jsp" charEncoding="utf-8"/> 
        </div>
    <center>
        <div id="login">
            <h1><fmt:message key="register.title"/></h1>
            <form name ="RegisterForm" method="POST" action="carrent">
                <input type="hidden" name="command" value="Register" />
                <input type="hidden" name="userType" value="GUEST" /> 
                <div class="titling"><fmt:message key="register.name"/>: <h7>*</h7></div>
                <div class="warning"> <fmt:message key="register.warn.login"/> </div>
                <input type="text" name="login" pattern="^[a-z0-9_-]{3,16}$"  value="" autofocus required>
                <c:if test="${not empty errorLogin}"><div class="alert"><fmt:message key="error.double.login"/></div></c:if>
                <div class="titling"> <fmt:message key="register.passoword" />: <h7>*</h7></div>
                <div class="warning"> <fmt:message key="register.warn.pass"/> </div>
                <input type="password" name="password" pattern="^[a-z0-9_-]{6,16}$" value=""  required id='pas1'>
                <div class="titling"> <fmt:message key="register.repeat_password" />: <h7>*</h7></div>
                <input type="password" name="passwordRepeat" value=""  required id='pas2'>
                <c:if test="${not empty errorPassword}"><div class="alert"><fmt:message key="error.double.pass"/></div></c:if>
                <div class="titling"> <fmt:message key="register.realname" />: <h7>*</h7></div>
                <input type="text" name="realname" maxlength="25" value=""  required>
                <div class="titling"> <fmt:message key="register.surname" />: <h7>*</h7></div>
                <input type="text" name="surname"  maxlength="25" value=""  required>
                <div class="titling"> <fmt:message key="register.email" />: <h7>*</h7></div>
                <input type="email" name="email" value=""required>
                <div class="titling"> <fmt:message key="register.passport.number" />:<h7>*</h7></div>
                <div class="warning"> <fmt:message key="passnum.warn" /></div>
                <input type="text" pattern="^[A-Z0-9]{7,14}$" name="pass_num" value=""  required>
                <br/>
                <input type="submit" value="<fmt:message key="register.button"/>">
                <br/>
            </form>
            <br/>
            <form name="back" action="" method="POST">
                <input type="hidden" name="command" value="returnIndex"/> 
                <input type="submit" value="<fmt:message key="error.back.button"/>"/> </form>
        </div>
    </center>
    <div class="footer">  <c:import url="\common\footer.jsp" charEncoding="utf-8"/> </div>
</body>
</html>
