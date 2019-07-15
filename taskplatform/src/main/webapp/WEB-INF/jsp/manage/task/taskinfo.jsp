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
                        <div class="navbar-right">  
                        		<button  class="btn btn-default" onclick="taskinfo_back()">
                  	返回
                                </button>  
                                <button  class="btn btn-default" onclick="taskinfo_lay()">
                                    指派人员
                                </button>
                                <button class="btn btn-default" onclick="taskinfo_status(1)">
                                    完成任务
                                </button>
                                <button class="btn btn-default" onclick="taskinfo_status(4)">
                                    关闭任务
                                </button>
                                <button class="btn btn-default" onclick="taskinfo_status(2)">
                                    暂停任务
                                </button>
                                <button class="btn btn-default" onclick="taskinfo_status(0)">
                                    激活任务
                                </button>
                                <button class="btn btn-default" onclick="taskinfo_status(3)">
                                    作废任务
                                </button>
                                <button class="btn btn-default" onclick="taskinfo_update()">
                                    修改任务
                                </button>
                   <c:if test="${upmsUser.username == tpTaskChild.initiator || upmsUser.username == 'admin'}">
                   				<button class="btn btn-default" onclick="taskinfo_del()">
                                    删除任务
                                </button>
                   </c:if>
                                
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
        <div class="taskinfo panel-body" style="display: none;border:1px solid #F00;
        			border:1;border-color: blue;opacity:1; width: 500px;height:250px;position:absolute; 
        			left:500px; top:300px; z-index:999;background-color: white;text-align: center;">
        		<span style="font-size: 20px;">指派人</span>
        		<input type='text' name='showresponsibleman' id='area_btn_ytaskinfo-responsibleman' value='' onclick="show_lay('taskinfo-responsibleman')" class='form-control' autocomplete='off' />                                           
                 <input id="nruid_ytaskinfo-responsibleman" type="hidden" class="form-control" name="responsibleman" value="">
                <div class="bot_btn">
                <a id="taskinfo_sumbit" class="a_con do_add">确定</a><a id="close_btn" class="a_con close_btn">取消</a> 
		        </div>
        </div>
		        
     
        <c:import url="/resources/inc/personselect.jsp" />
        <c:import url="/resources/inc/footer.jsp" />
        <script src="<c:url value="/resources/admin/plugins/personselect/js/select.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/plugins/bTabs/b.tabs.js" />" ></script> 
        <script type="text/javascript" src="<c:url value="/resources/admin/plugins/bTabs/demo.js" />" ></script>
    	
    	<script>
    	//取消按钮关闭事件
        $('#close_btn').click(function () {
            $(this).parents('.taskinfo').fadeOut();
            $('.taskinfo').fadeOut();
        });	
    	
    	//显示弹窗效果
    	function taskinfo_lay() {
    
    	    $('.taskinfo').show();
    	    $('.taskinfo').fadeIn();
    	    
    	    
    	}
    	
    	//确定
    	$('#taskinfo_sumbit').click(function () {
    		 $(this).parents('.taskinfo').fadeOut();
             $('.taskinfo').fadeOut();
    		
    		var userid = $('#nruid_ytaskinfo-responsibleman').val();//指派人id
    		var taskId = ${tpTaskChild.taskId};//任务编号
    		$.ajax({
        	    type:'POST',
        	    url:'${basePath}/taskplatController/updatetask',
        	    dataType:'json',
        	    data:{'userid':userid,'taskId':taskId},
        	    success:function(data){
        	        //请求成功函数内容
        	        var message = data.message;
        	        if(message=='success'){
        	        	alert("指派成功");
        	        }
        	        location.reload();
        	    },

        	    error:function(jqXHR){
        	        //请求失败函数内容

        	    }

        	});
    		
    		
    	})
    	
    	//任务进度
    	function taskinfo_status(data) {
    		 $(this).parents('.taskinfo').fadeOut();
             $('.taskinfo').fadeOut();
    		
    		var taskStatus = data;//任务类型
    		var taskId = ${tpTaskChild.taskId};//任务编号
    		$.ajax({
        	    type:'POST',
        	    url:'${basePath}/taskplatController/oktask',
        	    dataType:'json',
        	    data:{'taskId':taskId,'taskStatus':taskStatus,},
        	    success:function(data){
        	        //请求成功函数内容
        	        var message = data.message;
        	        if(message=='success'){
        	        	alert("执行成功");
        	        }
        	        location.reload();
        	    },

        	    error:function(jqXHR){
        	        //请求失败函数内容

        	    }

        	});
    		
    		
    	}
    	
    	//任务删除
    	function taskinfo_del() {
    		 $(this).parents('.taskinfo').fadeOut();
             $('.taskinfo').fadeOut();
    		
    		var taskId = ${tpTaskChild.taskId};//任务编号
    		$.ajax({
        	    type:'POST',
        	    url:'${basePath}/taskplatController/deltask',
        	    dataType:'json',
        	    data:{'taskId':taskId},
        	    success:function(data){
        	        //请求成功函数内容
        	        var message = data.message;
        	        if(message=='success'){
        	        	alert("删除成功");
        	        	window.history.go(-1);
        	        }
        	        location.reload();
        	    },

        	    error:function(jqXHR){
        	        //请求失败函数内容

        	    }

        	});
    		
    		
    	}
    	
    	//任务修改
    	function taskinfo_update() {
    		var taskId = ${tpTaskChild.taskId};//任务编号
    		window.location = "${basePath}/taskplatController/taskinfoupdatesel?taskId="+taskId;
    	}
    	//任务返回
    	function taskinfo_back() {
    		var taskId = ${tpTaskChild.taskId};//任务编号
    		window.location = "${basePath}/manage/task/mytodoindex";
    	}
    	
    	</script>
    
    </body>
</html>