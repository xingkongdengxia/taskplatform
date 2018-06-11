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
        <title>任务信息-${tpTaskChild.taskId}</title>
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
                <h3 class="page-header">任务标题：${tpTaskChild.title}</h3>
                <nav class="navbar navbar-default" role="navigation">
                    <div class="container-fluid">                        
                        <div>                            
                            <form class="navbar-form navbar-right" role="search">
                                <button type="submit" class="btn btn-default">
                                    指派人员
                                </button>
                                <button type="submit" class="btn btn-default">
                                    完成任务
                                </button>
                                <button type="submit" class="btn btn-default">
                                    关闭任务
                                </button>
                                <button type="submit" class="btn btn-default">
                                    暂停任务
                                </button>
                                <button type="submit" class="btn btn-default">
                                    激活任务
                                </button>
                                <button type="submit" class="btn btn-default">
                                    作废任务
                                </button>
                                <button type="submit" class="btn btn-default">
                                    修改任务
                                </button>
                                <button type="submit" class="btn btn-default">
                                    删除任务
                                </button>
                            </form>
                        </div>
                    </div>
                </nav>
                <div class="">
                    <div class="row-fluid">
                        <div class="col-md-2" style="padding-left: 0px;">
                            <div class="well menuSideBar" style="padding: 8px 0px;">
                                <ul class="nav nav-list" id="menuSideBar">
                                    <li class="nav-header">相关信息</li>
                                    <li class="nav-divider"></li>
                                    <li mid="tab1" funurl="<c:url value="/manage/task/result?message=页面完善中" />"><a tabindex="-1" href="javascript:void(0);">相关任务</a></li>
                                    <li mid="tab2" funurl="<c:url value="/manage/task/result?message=页面完善中" />"><a tabindex="-1" href="javascript:void(0);">相关服务器</a></li>
                                    <li mid="tab3" funurl="<c:url value="/manage/task/result?message=页面完善中" />"><a tabindex="-1" href="javascript:void(0);">相关系统</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-md-10" id="mainFrameTabs" style="padding : 0px;">
                            <!-- Nav tabs -->
                            <ul class="nav nav-tabs" role="tablist">
                                <li role="presentation" class="active noclose"><a href="#bTabs_navTabsMainPage" data-toggle="tab">基本信息</a></li>
                            </ul>

                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div class="tab-pane active" id="bTabs_navTabsMainPage">
                                    <div id='wrap'>
                                        <div class='content_wrap'>
                                            <div class='panel panel-default'>                   
                                                <form class='form-condensed' method='post' id='addtaskform'>                    
                                                    <table class='table table-form'>  
                                                        <tr>
                                                            <th>任务编号</th>
                                                            <td><div class="row">
                                                                    <div class="col-xs-3">
                                                                        ${tpTaskChild.taskId}
                                                                    </div>
                                                                    <div class="col-xs-3"></div>
                                                                    <div class="col-xs-3"></div>
                                                                    <div class="col-xs-3"></div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th>任务来源</th>
                                                            <td><div class="row">
                                                                    <div class="col-xs-3">
                                                                        ${tpTaskChild.taskSourceName}
                                                                    </div>
                                                                    <div class="col-xs-3"></div>
                                                                    <div class="col-xs-3"></div>
                                                                    <div class="col-xs-3"></div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th>任务类型</th>
                                                            <td><div class="row">
                                                                    <div class="col-xs-3">
                                                                        ${tpTaskChild.taskTypeName}
                                                                    </div>
                                                                    <div class="col-xs-3"></div>
                                                                    <div class="col-xs-3"></div>
                                                                    <div class="col-xs-3"></div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th>任务状态</th>
                                                            <td><div class="row">
                                                                    <div class="col-xs-3">
                                                                        ${tpTaskChild.taskStatusName}
                                                                    </div>
                                                                    <div class="col-xs-3"></div>
                                                                    <div class="col-xs-3"></div>
                                                                    <div class="col-xs-3"></div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th>优先级</th>
                                                            <td><div class="row">
                                                                    <div class="col-xs-2">
                                                                        ${tpTaskChild.priorityName}
                                                                    </div>
                                                                    <div class="col-xs-2"></div>
                                                                    <div class="col-xs-2"></div>
                                                                    <div class="col-xs-2"></div>
                                                                    <div class="col-xs-2"></div>
                                                                    <div class="col-xs-2"></div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th>发起人</th>
                                                            <td><div class="row">
                                                                    <div class="col-xs-3">                                           
                                                                        ${tpTaskChild.initiatorRealname}
                                                                    </div>
                                                                    <div class="col-xs-3"></div>
                                                                    <div class="col-xs-3"></div>
                                                                    <div class="col-xs-3"></div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th>责任人</th>
                                                            <td><div class="row">
                                                                    <div class="col-xs-3">                                           
                                                                        ${tpTaskChild.responsiblemanRealname}
                                                                    </div>
                                                                    <div class="col-xs-3"></div>
                                                                    <div class="col-xs-3"></div>
                                                                    <div class="col-xs-3"></div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th>执行人</th>
                                                            <td><div class="row">
                                                                    <div class="col-xs-6">                                           
                                                                        ${tpTaskChild.executorRealname}
                                                                    </div>
                                                                    <div class="col-xs-6"></div>                                      
                                                                </div>
                                                            </td>
                                                        </tr>  
                                                        <tr>
                                                            <th>抄送人</th>
                                                            <td><div class="row">
                                                                    <div class="col-xs-6">                                           
                                                                        ${tpTaskChild.ccRealname}
                                                                    </div>
                                                                    <div class="col-xs-6"></div>                                      
                                                                </div>
                                                            </td>
                                                        </tr> 
                                                        <tr>
                                                            <th>任务名称</th>
                                                            <td><div class="row">
                                                                    <div class="col-xs-12">                                           
                                                                        ${tpTaskChild.title}
                                                                    </div>                                                                            
                                                                </div>
                                                            </td>
                                                        </tr>      
                                                        <tr>
                                                            <th>任务描述</th>
                                                            <td><div class="row">
                                                                    <div class="col-xs-12">                                           
                                                                        <textarea name='description' id='description' rows='10' class='form-control' readonly="yes">${tpTaskChild.description}</textarea>
                                                                    </div>                                                                            
                                                                </div>
                                                            </td>
                                                        </tr>   
                                                        <tr>
                                                            <th>开始日期</th>
                                                            <td><div class="row">
                                                                    <div class="col-xs-3 input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_starttime" data-link-format="yyyy-mm-dd"">                                           
                                                                        ${tpTaskChild.showStarttime}
                                                                    </div>
                                                                    <div class="col-xs-3"></div>
                                                                    <div class="col-xs-3"></div>
                                                                    <div class="col-xs-3"></div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th>截止日期</th>
                                                            <td><div class="row">
                                                                    <div class="col-xs-3 input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_endtime" data-link-format="yyyy-mm-dd"">                                           
                                                                        ${tpTaskChild.showEndtime}
                                                                    </div>
                                                                    <div class="col-xs-3"></div>
                                                                    <div class="col-xs-3"></div>
                                                                    <div class="col-xs-3"></div>
                                                                </div>
                                                            </td>
                                                        </tr>                                             
                                                    </table>                                                     
                                                </form>
                                            </div>
                                        </div>                
                                    </div>
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
        <script type="text/javascript" src="<c:url value="/resources/admin/plugins/bTabs/b.tabs.js" />" ></script> 
        <script type="text/javascript" src="<c:url value="/resources/admin/plugins/bTabs/demo.js" />" ></script>
    </body>
</html>