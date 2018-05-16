<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html lang="zh-cn">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>人员选择框</title>
        <c:import url="/resources/inc/head.jsp" />
    </head>
    <body>
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="tel" name="phone" id="area_btn_y2" onclick="show_lay(2)"  lay-verify="phone" autocomplete="off" class="layui-input">
                    <em>查询人员2</em>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="tel" id="area_btn_y1" onclick="show_lay(1)" name="phone" lay-verify="phone" autocomplete="off" class="layui-input">
                    <em>查询人员1</em>
                </div>
            </div>
        </form>
        <c:import url="/resources/inc/personselect.jsp" />
        <c:import url="/resources/inc/footer.jsp" />
    </body>
</html>