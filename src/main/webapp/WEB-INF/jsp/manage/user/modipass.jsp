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
        <title>修改密码</title>
        <c:import url="/resources/inc/head.jsp" />
    </head>
    <body>
        <div id="modipass" class="container">
            <form id="modipassForm" method="post">
                <div class="form-group">
                    <label for="oldpassword">旧密码</label>
                    <input id="oldpassword" type="text" class="form-control" name="oldpassword" maxlength="32">
                </div>
                <div class="form-group">
                    <label for="newpassword">新密码</label>
                    <input id="newpassword" type="text" class="form-control" name="newpassword" maxlength="32">
                </div>
                <div class="form-group">
                    <label for="confirmnewpassword">确认新密码</label>
                    <input id="confirmnewpassword" type="text" class="form-control" name="confirmnewpassword" maxlength="32">
                </div>

                <div class="form-group text-center">
                    <button type="button" class="btn btn-default" onclick="createSubmit();">修改密码</a>           
                </div>
            </form>
        </div>
        <c:import url="/resources/inc/footer.jsp" />
        <script>
            function createSubmit() {
                if ($('#newpassword').val() != $('#confirmnewpassword').val()) {
                    alert("新密码二次输入不一致！");
                    return false;
                }
                $.ajax({
                    type: 'post',
                    url: '${basePath}/manage/user/modipassconfirm',
                    data: $('#modipassForm').serialize(),
                    beforeSend: function () {
                        if ($('#oldpassword').val() == '') {
                            $('#oldpassword').focus();
                            return false;
                        }
                        if ($('#newpassword').val() == '' || $('#newpassword').val().length < 5) {
                            $('#newpassword').focus();
                            return false;
                        }
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
                            alert("修改密码成功！");
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