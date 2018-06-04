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
    </head>
    <body>
        <div id='wrap'>
            <div class='content_wrap'>
                <div class='panel panel-default'>                   
                    <form class='form-condensed' method='post' id='addtaskform'>                    
                        <table class='table table-form'>                             
                            <tr>
                                <th>任务类型</th>
                                <td><div class="row">
                                        <div class="col-xs-3">
                                            <select id="task_type" name="taskType" class="form-control">                
                                                <option value="0">系统运维</option>
                                                <option value="1">系统升级</option>
                                                <option value="2">应急演练</option>
                                                <option value="3">其他</option>
                                            </select>
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
                                            <select id="priority" name="priority" class="form-control">                
                                                <option value="0">低</option>
                                                <option value="1">中</option>
                                                <option value="2">高</option>        
                                            </select>
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
                                <th>责任人</th>
                                <td><div class="row">
                                        <div class="col-xs-3">                                           
                                            <input type='text' name='showresponsibleman' id='area_btn_ysingle-responsibleman' value='' onclick="show_lay('single-responsibleman')" class='form-control' autocomplete='off' />                                           
                                            <input id="nruid_ysingle-responsibleman" type="hidden" class="form-control" name="responsibleman" value="">
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
                                            <input type='text' name='showexecutor' id='area_btn_yexecutor' value='' onclick="show_lay('executor')" class='form-control' autocomplete='off' />                                           
                                            <input id="nruid_yexecutor" type="hidden" class="form-control" name="executor" value="">
                                        </div>
                                        <div class="col-xs-6"></div>                                      
                                    </div>
                                </td>
                            </tr>  
                            <tr>
                                <th>抄送人</th>
                                <td><div class="row">
                                        <div class="col-xs-6">                                           
                                            <input type='text' name='showcc' id='area_btn_ycc' value='' onclick="show_lay('cc')" class='form-control' autocomplete='off' />                                           
                                            <input id="nruid_ycc" type="hidden" class="form-control" name="cc" value="">
                                        </div>
                                        <div class="col-xs-6"></div>                                      
                                    </div>
                                </td>
                            </tr> 
                            <tr>
                                <th>任务名称</th>
                                <td><div class="row">
                                        <div class="col-xs-12">                                           
                                            <input type='text' name='title' id='title' value='' class='form-control' autocomplete='off' />
                                        </div>                                                                            
                                    </div>
                                </td>
                            </tr>      
                            <tr>
                                <th>任务描述</th>
                                <td><div class="row">
                                        <div class="col-xs-12">                                           
                                            <textarea name='description' id='description' rows='10' class='form-control'></textarea>
                                        </div>                                                                            
                                    </div>
                                </td>
                            </tr>   
                            <tr>
                                <th>开始日期</th>
                                <td><div class="row">
                                        <div class="col-xs-3 input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_starttime" data-link-format="yyyy-mm-dd"">                                           
                                            <input class="form-control" size="16" type="text" value="" readonly>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                            <input type="hidden" id="dtp_starttime" value="" class="form-control" name='showStarttime'/><br/>
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
                                            <input class="form-control" size="16" type="text" value="" readonly>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                            <input type="hidden" id="dtp_endtime" value="" class="form-control" name='showEndtime'/><br/>
                                        </div>
                                        <div class="col-xs-3"></div>
                                        <div class="col-xs-3"></div>
                                        <div class="col-xs-3"></div>
                                    </div>
                                </td>
                            </tr>                                             
                        </table> 
                        <center><button type="button" class="btn btn-primary" onclick="javascript:addtask();">保存</button></center>
                    </form>
                </div>
            </div>                
        </div>
        <c:import url="/resources/inc/personselect.jsp" />
        <c:import url="/resources/inc/footer.jsp" />
        <script>
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

            function addtask() {
                $.ajax({
                    type: 'post',
                    url: '${basePath}/manage/task/addtask',
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
        </script>
    </body>
</html>