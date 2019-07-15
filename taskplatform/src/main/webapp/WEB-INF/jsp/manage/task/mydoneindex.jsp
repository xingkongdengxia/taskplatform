
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
        <title>我的已办</title>
        <c:import url="/resources/inc/head.jsp" /> 
    </head>
    <body>
    <input type="hidden" id="username" value="${upmsUser.username}">
        <div id="main">
            <c:import url="/resources/inc/search.jsp" />
            <div id="toolbar">

            </div>
            <table id="table"></table>
        </div>
        <c:import url="/resources/inc/footer.jsp" />
        <script>
            var $table = $('#table');
            $(function () {
                // bootstrap table初始化
                $table.bootstrapTable({
                    url: '${basePath}/manage/task/mydonelist',
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
                    idField: 'taskId',
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
                        {field: 'taskId', title: '编号', align: 'center'},
                        {field: 'title', title: '任务名称'},
                        {field: 'initiator', title: '发起人id',visible: false},
                        {field: 'initiatorRealname', title: '发起人', align: 'center'},
                        {field: 'responsiblemanRealname', title: '责任人', align: 'center'},
                        {field: 'executorRealname', title: '执行人', align: 'center'},
                        {field: 'taskSourceName', title: '任务来源', align: 'center'},
                        {field: 'taskTypeName', title: '任务类型', align: 'center'},
                        {field: 'taskStatusName', title: '任务状态', align: 'center'},
                        {field: 'priorityName', title: '优先级', align: 'center'},
                        {field: 'action', title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
                    ]
                });
            }
            );
            // 格式化操作按钮
            function actionFormatter(value, row, index) {
            	var initiator = row.initiator;
            	var username = $(" #username ").val();
                if(initiator == username){
                	return [
                            '<a class="update" href="javascript:;" onclick="updateAction()" data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a> ',
                            '<a class="delete" href="javascript:;" onclick="deleteAction()" data-toggle="tooltip" title="Remove"><i class="glyphicon glyphicon-remove"></i></a>'
                            
                        ].join('');
                }else{
                	return [
                            '<a class="update" href="javascript:;" onclick="updateAction()" data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a> '
                        ].join('');
                }
                /* return [
                    '<a class="update" href="javascript:;" onclick="updateAction()" data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a>　',
                    '<a class="delete" href="javascript:;" onclick="deleteAction()" data-toggle="tooltip" title="Remove"><i class="glyphicon glyphicon-remove"></i></a>'
                ].join(''); */
            }

            
          //更新
			function updateAction(){
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
                	var taId = rows[0].taskId;
    	    		window.location = "${basePath}/taskplatController/taskinfoupdatesel?taskId="+taId;
                }
				
			}
			
			//删除
			function deleteAction(){
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
	           				type:'POST',
	                   	    url:'${basePath}/taskplatController/deltask',
	                   	    dataType:'json',
	                   	    data:{'taskId':rows[0].taskId},
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
            
            
            
            
            

            //折叠查询条件
            $('#collapseone').collapse('hide');

            function search() {

                // 刷新表格  
                $('#table').bootstrapTable('refresh');

            }

            //初始化搜索框
            $('#txt_search_key_placeholder').text("任务编号、任务名称");
        </script>
    </body>
</html>