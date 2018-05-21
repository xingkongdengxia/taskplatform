<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div class="fade_layer"></div>
<div class="detail_layer select_peo workAdd">
    <div class="title fl">选择人员</div>
    <div class="fl serbox">
        <input type="text" class="searchUser" name="">
        <input type="button" class="searchBtn" value="搜索" name="">
    </div>
    <div class="clear"></div>
    <div class="select_peo_con">
        <div class="left">
            <div class="areas_list">
                <ul class="yiji">                    
                    <li class="areas_list_one"><a>信息系统组</a></li>
                    <ul class="areas_list_two">
                        <c:forEach var="user" items="${userList}">
                            <li><span id="${user.username}">${user.realname}</span></li>
                            </c:forEach>
                    </ul>                    
                </ul>
            </div>
        </div>
        <div class="center">
            <a id="list_add"><img src="<c:url value="/resources/admin/plugins/personselect/images/addicon.jpg" />"></a>           
        </div>
        <div class="right">
            <ul class="send_to">
            </ul>
        </div>
        <div class="clear"></div>
        <div class="bot_btn">
            <a onclick="do_add(this)" class="a_con do_add">确定</a><a class="a_con close_btn">取消</a> 
        </div>
    </div>
</div>
