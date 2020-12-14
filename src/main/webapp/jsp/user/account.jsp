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
                    <h2><fmt:message key="menu.account"/></h2>
                    <fmt:message key="user.account.username"/>: <b> ${userName}</b> <br/>
                    <fmt:message key="user.account.name"/>: <b> ${userRealName}</b> <br/>
                    <fmt:message key="user.account.surname"/>: <b> ${userSurname}</b> <br/>
                    <fmt:message key="user.account.passnum"/>: <b> ${userPassNum}</b> <br/>
                    <fmt:message key="user.account.email"/>: <b> ${userEmail}</b>

                    <form name='form-change-email' method="POST" action="carrent" > 
                        <input type="hidden" name="command" value="ChangeEmail" /> 
                        <input type="email" name="newemail" required value="" />
                        <input type="submit" value="<fmt:message key="user.account.emailchange"/>"/>
                    </form> 
                    <c:if test="${not empty cpError}"><div class="warnings"><fmt:message key="change.email.fail"/></div></c:if>
                    <c:if test="${not empty cpSuccess}"><div class="warnings"><fmt:message key="change.email.success"/></div></c:if>
                    <c:if test="${not empty cpSame}"><div class="warnings"><fmt:message key="error.same.emails"/></div></c:if>
  
                    <form name='form-change-password' method="POST" action="carrent" > 
                        <input type="hidden" name="command" value="ChangePassword" /> 
                        <input type="submit" value="<fmt:message key="user.account.passchange"/>"/>
                    </form> 


                </div>

            </div> 

        </div>
        <div class="footer">  <c:import url="..\common\footer.jsp" charEncoding="utf-8"/> </div>
    </body>
</html>
