<%-- 
    Document   : list
    Created on : 2017-7-10, 15:53:54
    Author     : justincai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <c:forEach var="actionsetting" items="${page.list}">

                <tr>
                    <td>${actionsetting.id}</td>
                    <td>${actionsetting.actionshowname}</td>
                </tr> 
            </c:forEach>
        </table>
        页次：${page.currentPage}/${page.totalPage}&nbsp;每页${page.pageSize}&nbsp;总数${page.totalRecord}&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="<c:url value='/pagination/list?p=1'/>">首页</a>
        <c:choose>  
            <c:when test="${page.currentPage>1}">
                <a href="<c:url value='/pagination/list?p=${page.currentPage-1}'/>">上一页</a>
            </c:when>  
            <c:otherwise>
                <a href="#">上一页</a>
            </c:otherwise>  
        </c:choose>
        
        <c:choose>  
            <c:when test="${page.currentPage<page.totalPage}">
                <a href="<c:url value='/pagination/list?p=${page.currentPage+1}'/>">下一页</a>
            </c:when>  
            <c:otherwise>
                <a href="#">下一页</a>
            </c:otherwise>  
        </c:choose>
    </body>
</html>
