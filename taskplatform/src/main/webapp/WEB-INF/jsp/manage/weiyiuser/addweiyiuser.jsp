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
        <title>新建任务</title>
        <c:import url="/resources/inc/head.jsp" />
        <link href="<c:url value="/resources/admin/plugins/personselect/css/common.css" />" rel="stylesheet"/>
        <link href="<c:url value="/resources/admin/plugins/personselect/css/select.css" />" rel="stylesheet"/>
    </head>
    <body>
        <div id='wrap'>
            <div class='content_wrap'>
                <div class='panel panel-default'>                   
                    <form class='form-condensed' method='post' id='addtaskform'>   
                    <input type="hidden" name='id' id='id' value='' class='form-control' autocomplete='off' />                 
                        <table class='table table-form'>                             
                             <tr>
                             	 <th>名称</th>
                                <td><div class="row">
                                        <div class="col-xs-12">                                           
                                            <input type='text' name='paramName' id='paramName' value='' class='form-control' autocomplete='off' />
                                        </div>                                                                            
                                    </div>
                                </td>
                             </tr>                                          
                             <tr>
                             	 <th>密码</th>
                                <td><div class="row">
                                        <div class="col-xs-12">                                           
                                            <input type='text' name='paramValue' id='paramValue' value='' class='form-control' autocomplete='off' />
                                        </div>                                                                            
                                    </div>
                                </td>
                             </tr>                                          
                             <tr>
                             <th>权限</th>
                             	<td>
                             	<div class="col-xs-2">
                                            <select id="power" name="power" class="form-control">                
                                                <option value="0">管理员</option>
                                                <option value="1">普通员工</option>
                                            </select>
                                        </div>
                             	</td>
                             </tr>                                          
                        </table> 
                        <button type="button" class="btn btn-primary" id="addtask" onclick="javascript:addweiyiuser();">保存</button>
                        <button type="button" class="btn btn-primary" onclick="javascript:taskinfo_back();">返回</button>
                    </form>
                </div>
            </div>                
        </div>
        <c:import url="/resources/inc/personselect.jsp" />
        <c:import url="/resources/inc/footer.jsp" />
        <script src="<c:url value="/resources/admin/plugins/personselect/js/select.js" />"></script>
        <script>
        /* 初始化 */
    	var power = ${tpTaskOperWeiyiuser.power};
    	if(power != undefined){
    		//$("#power").find("option:contains('"+power+"')").prop("selected",true);//权限
    		$("#power").find("option[value='"+power+"']").prop("selected",true);
    	}

    	
            $('.form_date').datetimepicker({
                language: 'zh-CN',
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0
            });

            
            //新增
            function addweiyiuser() {
                $.ajax({
                    type: 'post',
                    url: '${basePath}/weiyiuserController/addweiyiuser',
                    data: $('#addtaskform').serialize(),
                    beforeSend: function () {
                        
                    },
                    success: function (result) {
                        if (result.code != 1) {
                            if (result.data instanceof Array) {
                                $.each(result.data, function (index, value) {
                                    $.confirm({
                                        theme: 'dark',
                                        animation: 'rotateX',
                                        closeAnimation: 'rotateX',
                                        title: false,
                                        content: value.errorMsg,
                                        buttons: {
                                            confirm: {
                                                text: '确认',
                                                btnClass: 'waves-effect waves-button waves-light'
                                            }
                                        }
                                    });
                                });
                            } else {
                                $.confirm({
                                    theme: 'dark',
                                    animation: 'rotateX',
                                    closeAnimation: 'rotateX',
                                    title: false,
                                    content: result.data.errorMsg || result.data,
                                    buttons: {
                                        confirm: {
                                            text: '确认',
                                            btnClass: 'waves-effect waves-button waves-light'
                                        }
                                    }
                                });
                            }
                        } else {
                            //alert("保存成功！");
                            window.location.href = "${basePath}/manage/task/result?message=保存成功！";
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        $.confirm({
                            theme: 'dark',
                            animation: 'rotateX',
                            closeAnimation: 'rotateX',
                            title: false,
                            content: textStatus,
                            buttons: {
                                confirm: {
                                    text: '确认',
                                    btnClass: 'waves-effect waves-button waves-light'
                                }
                            }
                        });
                    }
                });
            }
            
          //任务返回
        	function taskinfo_back() {
        		//var taskId = ${tpTaskChild.taskId};//任务编号
        		//window.location = "${basePath}/manage/task/mytodoindex";
        		window.history.go(-1);
        	}
          
        </script>
    </body>
</html>