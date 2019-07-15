
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
        <title>我的待办</title>
        <c:import url="/resources/inc/head.jsp" /> 
    </head>
    <body>
        <div id="main">
            <c:import url="/resources/inc/search.jsp" />
            <div id="toolbar">
               <%--  <shiro:hasPermission name="tp:task:mytodo"><a class="waves-effect waves-button" href="javascript:;" onclick="viewAction()"><i class="glyphicon glyphicon-eye-open"></i> 查看任务详情</a></shiro:hasPermission> --%>
                </div>
                <table id="table"></table>
            </div>
            
            
     
        <c:import url="/resources/inc/footer.jsp" />
        <script>
            var $table = $('#table');
            $('#myModal').modal('hide')

            $(function () {
                // bootstrap table初始化
                $table.bootstrapTable({
                    url: '${basePath}/manage/task/allHistorytaskList',
                    height: getHeight(),
                    striped: true,
                    search: false,
                    showRefresh: true,
                    showColumns: true,
                    minimumCountColumns: 2,
                    clickToSelect: true,
                    detailView: true,
                    detailFormatter: 'detailFormatter',
                    pagination: true,
                    paginationLoop: false,
                    sidePagination: 'server',
                    silentSort: false,
                    smartDisplay: false,
                    escape: true,
                    searchOnEnterKey: true,
                    idField: 'id',
                    maintainSelected: true,
                    toolbar: '#toolbar',
                    queryParamsType: "undefined",
                    queryParams: function (params) {
                        var str_search = $('#txt_search_key').val();
                        return {
                            offset: params.offset,
                            limit: params.limit,
                            search: str_search
                        };
                    },
                    columns: [
                        {field: 'ck', checkbox: true},
                        {field: 'id', title: '编号', align: 'center'},
                        {field: 'taskId', title: '任务编号', align: 'center'},
                        {field: 'title', title: '任务名称'},
                        {field: 'operatorName', title: '操作人名称', align: 'center'},
                        {field: 'actionTypeName', title: '操作内容', align: 'center'},
                        {field: 'remark', title: '备注', align: 'center'},
                        {field: 'cTimeName', title: '创建时间', align: 'center'},
                        {field: 'action', title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                    ]
                });
            }
            );
            // 格式化操作按钮
            function actionFormatter(value, row, index) {
                return [
                    '<a class="update"  href="javascript:;" onclick="deleteHistory()" target="_parent" data-toggle="tooltip" title="删除"><i class="glyphicon glyphicon-remove"></i></a>　'
                    //'<button class="update" onclick="viewAction()" data-toggle="tooltip" title="查看"><i class="glyphicon glyphicon-eye-open"></i></button>　'
                    //'<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">删除</button> '
                ].join('');
            }
            // 删除单条记录    
            function deleteHistory() {
            	var rows = $table.bootstrapTable('getSelections');
            	if (rows.length != 1) {
            		$.confirm({
                        title: false,
                        content: '请选择一条记录！',
                        autoClose: 'cancel|3000',
                        backgroundDismiss: true,
                        buttons: {
                            cancel: {
                                text: '取消',
                                btnClass: 'waves-effect waves-button'
                            }
                        }
                    });
            	}else{
            		if(confirm('你确定要删除任务【'+rows[0].title+'】吗?')==true){
            			$.ajax({
  	                      type: "get",
  	                      url: "${basePath}/taskplatController/tpTaskOperHistorydel",
  	                      data: { "id": rows[0].id },
  	                      success: function (data, status) {
  	                          if (status == "success") {
  	                        	  alert('提交数据成功');
  	                              $("#table").bootstrapTable('refresh');
  	                          }
  	                      },
  	                      error: function () {
  	                    	alert('Error');
  	                      },
  	                      complete: function () {
  	  
  	                      }
  	  
  	                  });
            			
		                return true;
		             }else{
		                return false;
		             }
            	}
            }
         // 删除单条记录            
            /* function deleteHistory() {
            	var rows = $table.bootstrapTable('getSelections');
            	if (rows.length != 1) {
                    $.confirm({
                        title: false,
                        content: '请选择一条记录！',
                        autoClose: 'cancel|3000',
                        backgroundDismiss: true,
                        buttons: {
                            cancel: {
                                text: '取消',
                                btnClass: 'waves-effect waves-button'
                            }
                        }
                    });
                } else {
                	//$("#tishi").html("你确定要删除任务【"+rows[0].title+"】");
                	$.ajax({
	                      type: "get",
	                      url: "${basePath}/taskplatController/tpTaskOperHistorydel",
	                      data: { "taskId": rows[0].taskId },
	                      success: function (data, status) {
	                          if (status == "success") {
	                        	  taler('提交数据成功');
	                              $("#table").bootstrapTable('refresh');
	                          }
	                      },
	                      error: function () {
	                    	  taler('Error');
	                      },
	                      complete: function () {
	  
	                      }
	  
	                  });
                }
     		                  

            } */
            //折叠查询条件
            $('#collapseone').collapse('hide');

            function search() {

                // 刷新表格  
                $('#table').bootstrapTable('refresh');

            }

            //初始化搜索框
            $('#txt_search_key_placeholder').text("任务名称");
        </script>
    </body>
</html>