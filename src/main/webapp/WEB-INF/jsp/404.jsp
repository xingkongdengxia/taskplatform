<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8" isErrorPage="true"%> 
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title><spring:message code="404"/></title>
    </head>
    <body>
        <c:if test="${requestHeader == 'ajax'}">
            <h5 style="padding-bottom: 10px;">页面没有找到！</h5>
        </c:if>
        <c:if test="${requestHeader != 'ajax'}">
            <% Exception e = (Exception) request.getAttribute("ex");%>
            <h2>错误: <%= e.getClass().getSimpleName()%></h2>
            <hr />
            <h5>错误描述：</h5>
            <p><%= e.getMessage()%></p>
        </c:if>
    </body>
</html>