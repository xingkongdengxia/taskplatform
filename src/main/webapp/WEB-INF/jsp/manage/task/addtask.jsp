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
                    <div id='titlebar'>
                        <div class='heading'>
                            <span class='prefix'><i class='icon-check-sign'></i></span>
                            <strong><small class='text-muted'><i class='icon-plus'></i></small></strong>
                        </div>
                        <div class='actions'>
                            <button type="button" class="btn btn-default" data-toggle="customModal"><i class='icon icon-cog'></i> </button>
                        </div>
                    </div>
                    <form class='form-condensed' method='post' enctype='multipart/form-data' id='dataform' data-type='ajax'>                    
                        <table class='table table-form'>                             
                            <tr>
                                <th>任务类型</th>
                                <td><div class="row">
                                        <div class="col-xs-3">
                                            <select id="task_type" name="task_type" class="form-control">                
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
                                <th>日程规划</th>
                                <td id='planAndMailCell' colspan='5' class='has-mail-col'>
                                    <div class='row-table'>
                                        <div class='col-table' id='taskPlanCol'>
                                            <div class='input-group' id='dataPlanGroup'>                              
                                                <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_starttime" data-link-format="yyyy-mm-dd">
                                                    <input class="form-control" size="16" type="text" value="" readonly>
                                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                </div>
                                                <input type="hidden" id="dtp_starttime" value="" /><br/>
                                                &nbsp;&nbsp;&nbsp;
                                                <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_endtime" data-link-format="yyyy-mm-dd">
                                                    <input class="form-control" size="16" type="text" value="" readonly>
                                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                </div>
                                                <input type="hidden" id="dtp_starttime" value="" /><br/>
                                            </div>
                                        </div>                                 
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>附件 </th>
                                <td colspan='5'><style>
                                        .fileBox {margin-bottom: 10px; width: 100%}
                                        table.fileBox td {padding: 0!important}
                                        .fileBox .input-control > input[type='file'] {width: 100%; height: 100%; height: 26px; line-height: 26px; border: none; position: relative;}
                                        .fileBox td .btn {border-radius: 0; border-left: none}
                                        .file-wrapper.form-control {border-right: 0}
                                        .file-wrapper.form-control .fileControl {width:100%;}
                                        @-moz-document url-prefix(){.file-wrapper.form-control .fileControl {margin-top:-3px;}}
                                    </style>
                                    <div id='fileform'>
                                        <script language='Javascript'>dangerFiles = "php,php3,php4,phtml,php5,jsp,py,rb,asp,aspx,ashx,asa,cer,cdx,aspl,shtm,shtml,html,htm";</script>
                                        <table class='fileBox' id='fileBox1'>
                                            <tr>
                                                <td class='w-p45'><div class='form-control file-wrapper'><input type='file' name='files[]' class='fileControl'  tabindex='-1' onchange='checkSizeAndType(this)'/></div></td>
                                                <td class=''><input type='text' name='labels[]' class='form-control' placeholder='标题：' tabindex='-1' /></td>
                                                <td class='w-30px'><a href='javascript:void(0);' onclick='addFile(this)' class='btn btn-block'><i class='icon-plus'></i></a></td>
                                                <td class='w-30px'><a href='javascript:void(0);' onclick='delFile(this)' class='btn btn-block'><i class='icon-remove'></i></a></td>
                                            </tr>
                                        </table></div>                               
                                </td>
                            </tr>
                            <tr >
                                <th>添加之后</th>
                                <td colspan='5'><label class='radio-inline'><input type='radio' name='after' value='continueAdding'  checked ='checked' id='aftercontinueAdding' /> 继续为该需求添加任务</label><label class='radio-inline'><input type='radio' name='after' value='toTaskList'  id='aftertoTaskList' /> 返回任务列表</label><label class='radio-inline'><input type='radio' name='after' value='toStoryList'  id='aftertoStoryList' /> 返回需求列表</label></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td colspan='5'> <button type='submit' id='submit' class='btn btn-primary'  data-loading='稍候...'>保存</button><a href='javascript:history.go(-1);' class='btn btn-back ' >返回</a></td>
                            </tr>
                        </table>
                        <span id='responser'></span>
                    </form>
                </div>
            </div>                
        </div>
        <c:import url="/resources/inc/personselect.jsp" />
        <c:import url="/resources/inc/footer.jsp" />
        <script>

        </script>
    </body>
</html>