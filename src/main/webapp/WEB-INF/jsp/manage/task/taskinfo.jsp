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
        <title>任务信息-3</title>
        <link href="<c:url value="/resources/admin/plugins/bootstrap-3.3.0/css/bootstrap.min.css" />" rel="stylesheet"/>
        <link href="<c:url value="/resources/admin/plugins/font-awesome-4.7.0/css/font-awesome.min.css" />" rel="stylesheet"/>
        <link href="<c:url value="/resources/admin/plugins/bTabs/b.tabs.css" />" rel="stylesheet"/>
        <link href="<c:url value="/resources/admin/plugins/personselect/css/common.css" />" rel="stylesheet"/>
        <link href="<c:url value="/resources/admin/plugins/personselect/css/select.css" />" rel="stylesheet"/>
        <style type="text/css">
            div.menuSideBar { }
            div.menuSideBar li.nav-header { font-size: 14px; padding: 3px 15px; }
            div.menuSideBar .nav-list > li > a, div.menuSideBar .dropdown-menu li a { -webkit-border-radius: 0px; -moz-border-radius: 0px; -ms-border-radius: 0px; border-radius: 0px; }
        </style>
    </head>
    <body>
        <div class="content">
            <div class="container">
                <h3 class="page-header">任务标题：xxxxxx</h3>
                <div class="">
                    <div class="row-fluid">
                        <div class="col-md-2" style="padding-left: 0px;">
                            <div class="well menuSideBar" style="padding: 8px 0px;">
                                <ul class="nav nav-list" id="menuSideBar">
                                    <li class="nav-header">导航菜单</li>
                                    <li class="nav-divider"></li>
                                    <li mid="tab1" funurl="html.html"><a tabindex="-1" href="javascript:void(0);">页面1</a></li>
                                    <li mid="tab2" funurl="html2.html"><a tabindex="-1" href="javascript:void(0);">页面2</a></li>
                                    <li mid="tab3" funurl="html3.html"><a tabindex="-1" href="javascript:void(0);">页面3</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-md-10" id="mainFrameTabs" style="padding : 0px;">
                            <!-- Nav tabs -->
                            <ul class="nav nav-tabs" role="tablist">
                                <li role="presentation" class="active noclose"><a href="#bTabs_navTabsMainPage" data-toggle="tab">首页</a></li>
                            </ul>

                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div class="tab-pane active" id="bTabs_navTabsMainPage">
                                    <div class="page-header">
                                        <h2 style="font-size: 31.5px;text-align: center;font-weight: normal;">欢迎使用</h2>
                                    </div>
                                    <div style="text-align: center;font-size: 20px;line-height: 20px;"> Welcome to use bTabs plugin! </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:import url="/resources/inc/personselect.jsp" />
        <c:import url="/resources/inc/footer.jsp" />
        <script src="<c:url value="/resources/admin/plugins/personselect/js/select.js" />"></script>
        <script type="text/javascript" src="/resources/admin/plugins/bTabs/b.tabs.js" ></script> 
        <script type="text/javascript" src="/resources/admin/plugins/bTabs/demo.js" ></script>
    </body>
</html>