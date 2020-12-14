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
                        <h2><fmt:message key="car.change.title"/></h2>

                        <br/>
                        <br/>
                        <div id="back"> 
                            <form name ="BackForm"  method="POST" action="carrent" >
                                <input type="hidden" name="command" value="allcars"/> 
                                <input type="submit" value="<fmt:message key="menu.users.back" /> "/>
                            </form> 
                        </div><br/><br/>
                        <b> <fmt:message key="order.car.id"/> </b> ${carid}<br/>

                        <hr/>
                        <b> <fmt:message key="order.name"/> </b> ${carname}<br/>
                        <form name ="ChangeCarInfoForm"  method="POST" action="carrent" class="menu">
                            <input type="hidden" name="carid" value="${carid}" /> 
                            <input type="hidden" name="carname" value="${carname}" /> 
                            <input type="hidden" name="command" value="changecarname" /> 
                            <input type="text" name="newcarname" value="" required> 
                            <input type="submit" value="<fmt:message key="car.change"/> " />
                        </form>
                        <c:if test="${not empty success}"><div class="warnings"><fmt:message key="carnamechange.success"/></div></c:if>
                        <c:if test="${not empty fail}"><div class="warnings"><fmt:message key="carnamechange.fail"/></div></c:if>

                            <hr/>
                            <b> <fmt:message key="order.price"/></b> ${carprice} <fmt:message key="cars.currency"/>
                        <form name ="ChangeCarInfoForm"  method="POST" action="carrent" class="menu">
                            <input type="hidden" name="carid" value="${carid}" /> 
                            <input type="hidden" name="carprice" value="${carprice}" /> 
                            <input type="hidden" name="command" value="changecarprice" /> 
                            <input type="number" name="newprice" min="1" max="10000" value="" required> 
                            <input type="submit" value="<fmt:message key="car.change"/> " />
                        </form>
                        <c:if test="${not empty psuccess}"><div class="warnings"><fmt:message key="carpricechange.success"/></div></c:if>
                        <c:if test="${not empty pfail}"><div class="warnings"><fmt:message key="carpricechange.fail"/></div></c:if>

                            <hr/>
                            <img src="${carimage}"/><br/>
                        <form name ="ChangeCarInfoForm"  method="POST" action="carrent" class="menu">
                            <input type="hidden" name="carimage" value="${carimage}" /> 
                            <input type="hidden" name="carid" value="${carid}" /> 
                            <input type="hidden" name="command" value="changecarimage" /> 
                            <input type="text" maxlength="100" name="newimage" value="" required> 
                            <input type="submit" value="<fmt:message key="car.change"/> " />
                        </form>
                        <div class="notice"><fmt:message key="carimagechange.notice"/></div>
                        <c:if test="${not empty isuccess}"><div class="warnings"><fmt:message key="carimagechange.success"/></div></c:if>
                        <c:if test="${not empty ifail}"><div class="warnings"><fmt:message key="carimagechange.fail"/></div></c:if>
                            <hr/>
                        <c:if test="${active == 1}"><div class="warnings"><fmt:message key="caractivechange.success"/></div></c:if>
                        <c:if test="${active == 0}"><div class="warnings"><fmt:message key="caractivechange.fail"/></div></c:if>                            
                        <form name ="ChangeCarInfoForm"  method="POST" action="carrent" class="menu">
                            <input type="hidden" name="active" value="${active}" /> 
                            <input type="hidden" name="carid" value="${carid}" /> 
                            <input type="hidden" name="command" value="changeactive" /> 
                            <input type="submit" value="<fmt:message key="car.change"/> " />
                        </form>
                        <hr/>
                        <form name ="ChangeCarInfoForm"  method="POST" action="carrent" class="menu">
                            <input type="hidden" name="carid" value="${carid}" /> 
                            <input type="hidden" name="command" value="deletecar" /> 
                            <input type="submit" value="<fmt:message key="car.delete"/> " />
                        </form>
                        <div class="notice"><fmt:message key="cardelete.notice"/></div>
                        <c:if test="${not empty dsuccess}"><div class="warnings"><fmt:message key="cardelete.success"/></div></c:if>
                        <c:if test="${not empty dfail}"><div class="warnings"><fmt:message key="cardelete.fail"/></div></c:if>
                            <br/>

                        </div>               
                    </div>
                </div> 

            </div>
            <div class="footer">  <c:import url="..\common\footer.jsp" charEncoding="utf-8"/> </div>
    </body>
</html>
