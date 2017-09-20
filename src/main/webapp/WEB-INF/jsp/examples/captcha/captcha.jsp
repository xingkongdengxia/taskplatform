<%-- 
    Document   : 验证码示例
    Created on : 2017-9-17, 18:42:41
    Author     : justincai
--%>

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
        <title>验证码示例</title>
        <c:import url="/resources/inc/head.jsp" />
    </head>
    <body>
        <div class="panel panel-default">
            <div class="panel-body">
                <form id="createForm" method="post">
                    <div class="form-group">
                        姓名：<input id="name" class="form-control" name="name" type="text"/>
                    </div>
                    <div class="form-group">
                        密码：<input id="password" class="form-control" name="password" type="password"/>
                    </div>
                    <div class="container-fluid">
                        <div class="form-group">
                            验证码：<input id="validateCode" class="form-control" name="validateCode" type="text" maxlength="4"/>                        
                        </div>                        
                        <img type="image" src="${basePath}/captcha/authCode" id="codeImage" onclick="flushcode()"/>
                    </div>
                    <div class="form-group">
                        <br>
                        <button type="button" class="btn btn-default"  onclick="createSubmit()">登录</button>
                    </div>
                </form>
            </div>
        </div>
        <c:import url="/resources/inc/footer.jsp" />
        <script>
            function flushcode() {                
                $('#codeImage').attr('src', '${basePath}/captcha/authCode?abc=' + Math.random());                
            }     

            function createSubmit() {
                $.ajax({
                    type: 'post',
                    url: '${basePath}/captcha/checkImgCode',
                    data: $('#createForm').serialize(),
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
                                    content: result.data,
                                    buttons: {
                                        confirm: {
                                            text: '确认',
                                            btnClass: 'waves-effect waves-button waves-light'
                                        }
                                    }
                                });
                            }
                        } else {
                            alert("验证码正确！");
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
