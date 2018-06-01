<%-- 
    Document   : newjsp
    Created on : 2018-6-1, 15:39:51
    Author     : chc51
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <script>
            var noResultsMatch = '没有匹配结果';
            var chooseUsersToMail = '选择要发信通知的用户...';
            var defaultChosenOptions = {no_results_text: noResultsMatch, width: '100%', allow_single_deselect: true, disable_search_threshold: 1, placeholder_text_single: ' ', placeholder_text_multiple: ' ', search_contains: true};
            $(document).ready(function ()
            {
                $("#mailto").attr('data-placeholder', chooseUsersToMail);
                $("#mailto, .chosen, #productID").chosen(defaultChosenOptions).on('chosen:showing_dropdown', function ()
                {
                    var $this = $(this);
                    var $chosen = $this.next('.chosen-container').removeClass('chosen-up');
                    var $drop = $chosen.find('.chosen-drop');
                    $chosen.toggleClass('chosen-up', $drop.height() + $drop.offset().top - $(document).scrollTop() > $(window).height());
                });
            });
        </script>
        <header id='header'>
            <div id='topbar'>
                <div class='pull-right' id='topnav'><div class='dropdown' id='userMenu'><a href='javascript:;' data-toggle='dropdown'><i class='icon-user'></i> 蔡华臣 <span class='caret'></span></a><ul class='dropdown-menu'><li><a href='/zentao/my-profile.html?onlybody=yes' class='iframe' data-width='600'>个人档案</a>
                            </li><li><a href='/zentao/my-changepassword.html?onlybody=yes' class='iframe' data-width='500'>更改密码</a>
                            </li><li class='divider'></li><li class='dropdown-submenu left'><a href='javascript:;'>主题</a><ul class='dropdown-menu'><li class='theme-option active'><a href='javascript:selectTheme("default");' data-value='default'>默认</a></li><li class='theme-option'><a href='javascript:selectTheme("green");' data-value='green'>绿色</a></li><li class='theme-option'><a href='javascript:selectTheme("red");' data-value='red'>红色</a></li><li class='theme-option'><a href='javascript:selectTheme("lightblue");' data-value='lightblue'>亮蓝</a></li><li class='theme-option'><a href='javascript:selectTheme("blackberry");' data-value='blackberry'>黑莓</a></li></ul></li><li class='dropdown-submenu left'><a href='javascript:;'>Language</a><ul class='dropdown-menu'><li class='lang-option active'><a href='javascript:selectLang("zh-cn");' data-value='zh-cn'>简体</a></li><li class='lang-option'><a href='javascript:selectLang("zh-tw");' data-value='zh-tw'>繁體</a></li><li class='lang-option'><a href='javascript:selectLang("en");' data-value='en'>English</a></li></ul></li></ul></div><a href='/zentao/user-logout.html' >退出</a>
                    <div class='dropdown'><a href='javascript:;' data-toggle='dropdown'>帮助 <span class='caret'></span></a><ul class='dropdown-menu pull-right'><li><a href='javascript:;' class='open-help-tab'>手册</a>
                            </li><li><a href='/zentao/tutorial-start.html' class='iframe' data-width='800' data-headerless='true' data-backdrop='true' data-keyboard='true'>新手教程</a>
                            </li><li><a href='/zentao/misc-changeLog.html' class='iframe' data-width='800' data-headerless='true' data-backdrop='true' data-keyboard='true'>修改日志</a>
                            </li></ul></div><a href='/zentao/misc-about.html' class='about iframe' data-width='900' data-headerless='true' data-backdrop='true' data-keyboard='true' data-class='modal-about'>关于</a>
                </div>
                <h5 id='companyname'>
                    中国铁汉项目管理系统    </h5>
            </div>
            <nav id='mainmenu'>
                <ul class='nav'>
                    <li  data-id='my'><a href='/zentao/my/' ><i class="icon-home"></i><span> 我的地盘</span></a></li>
                    <li  data-id='product'><a href='/zentao/product/' >产品</a></li>
                    <li class='active' data-id='project'><a href='/zentao/project/' class='active'>项目</a></li>
                    <li  data-id='qa'><a href='/zentao/qa/' >测试</a></li>
                    <li  data-id='doc'><a href='/zentao/doc/' >文档</a></li>
                    <li  data-id='report'><a href='/zentao/report/' >统计</a></li>
                    <li  data-id='company'><a href='/zentao/company/' >组织</a></li>
                    <li  data-id='admin'><a href='/zentao/admin/' >后台</a></li>
                    <li class='custom-item'><a href='/zentao/custom-ajaxMenu-task-create.html?onlybody=yes' data-toggle='modal' data-type='iframe' title='自定义导航' data-icon='cog' data-width='80%'><i class='icon icon-cog'></i></a></li></ul>
                <div class='input-group input-group-sm' id='searchbox'><div class='input-group-btn' id='typeSelector'><button type='button' class='btn dropdown-toggle' data-toggle='dropdown'><span id='searchTypeName'>任务</span> <span class='caret'></span></button><input type='hidden' name='searchType' id='searchType' value='task'  />
                        <ul class='dropdown-menu'><li><a href='javascript:;' data-value='bug'>Bug</a></li><li><a href='javascript:;' data-value='story'>需求</a></li><li><a href='javascript:;' data-value='task'>任务</a></li><li><a href='javascript:;' data-value='testcase'>用例库</a></li><li><a href='javascript:;' data-value='project'>项目</a></li><li><a href='javascript:;' data-value='product'>产品</a></li><li><a href='javascript:;' data-value='user'>用户</a></li><li><a href='javascript:;' data-value='build'>版本</a></li><li><a href='javascript:;' data-value='release'>发布</a></li><li><a href='javascript:;' data-value='productplan'>产品计划</a></li><li><a href='javascript:;' data-value='testtask'>测试版本</a></li><li><a href='javascript:;' data-value='doc'>文档</a></li><li><a href='javascript:;' data-value='testreport'>测试报告</a></li></ul></div><input type='text' name='searchQuery' id='searchQuery' value='' onclick='this.value = ""' onkeydown='if (event.keyCode == 13)
                                    shortcut()' class='form-control' placeholder='编号(ctrl+g)' />
                    <div id='objectSwitcher' class='input-group-btn'><a href='javascript:shortcut();' class='btn'>GO! </a></div></div>
            </nav>
            <nav id="modulemenu">
                <ul class='nav'>
                    <li data-id='list'><a id='currentItem' href="javascript:showSearchMenu('project', '8', 'task', 'create', '')">私人事务 <span class='icon-caret-down'></span></a><div id='dropMenu'><i class='icon icon-spin icon-spinner'></i></div></li>
                    <li class='right ' data-id='index'><a href='/zentao/project-index-no.html' ><i class='icon-home'></i>项目主页</a>
                    </li>
                    <li class=' active' data-id='task'><a href='/zentao/project-task-8.html' >任务</a>
                    </li>
                    <li class=' ' data-id='story'><a href='/zentao/project-story-8.html' >需求</a>
                    </li>
                    <li class=' ' data-id='bug'><a href='/zentao/project-bug-8.html' >Bug</a>
                    </li>
                    <li class=' ' data-id='build'><a href='/zentao/project-build-8.html' >版本</a>
                    </li>
                    <li class=' ' data-id='testtask'><a href='/zentao/project-testtask-8.html' >测试</a>
                    </li>
                    <li class=' ' data-id='team'><a href='/zentao/project-team-8.html' >团队</a>
                    </li>
                    <li class=' ' data-id='dynamic'><a href='/zentao/project-dynamic-8.html' >动态</a>
                    </li>
                    <li class=' ' data-id='doc'><a href='/zentao/doc-objectLibs-project-8-project.html' >文档</a>
                    </li>
                    <li class=' ' data-id='product'><a href='/zentao/project-manageproducts-8.html' >产品</a>
                    </li>
                    <li class=' ' data-id='view'><a href='/zentao/project-view-8.html' >概况</a>
                    </li>
                    <li class='right ' data-id='create'><a href='/zentao/project-create.html' ><i class='icon-plus'></i>&nbsp;添加项目</a>
                    </li>
                    <li class='right ' data-id='all'><a href='/zentao/project-all-undone-8.html' ><i class='icon-th-large'></i>&nbsp;所有项目</a>
                    </li>
                </ul>
            </nav>
        </header>

        <div id='wrap'  >
            <div class='outer'>
                <script src='/zentao/js/jquery/form/min.js?v=9.5' type='text/javascript'></script>
                <script src='/zentao/js/jquery/form/zentao.js?v=9.5' type='text/javascript'></script>
                <script language='javascript'>
                            $(function ()
                            {
                                $.fn.fixedDate = function ()
                                {
                                    return $(this).each(function ()
                                    {
                                        var $this = $(this);
                                        if ($this.offset().top + 200 > $(document.body).height())
                                        {
                                            $this.attr('data-picker-position', 'top-right');
                                        }

                                        if ($this.val() == '0000-00-00')
                                        {
                                            $this.focus(function () {
                                                if ($this.val() == '0000-00-00')
                                                    $this.val('').datetimepicker('update');
                                            }).blur(function () {
                                                if ($this.val() == '')
                                                    $this.val('0000-00-00')
                                            });
                                        }
                                    });
                                };

                                var options =
                                        {
                                            language: 'zh-cn',
                                            weekStart: 1,
                                            todayBtn: 1,
                                            autoclose: 1,
                                            todayHighlight: 1,
                                            startView: 2,
                                            forceParse: 0,
                                            showMeridian: 1,
                                            format: 'yyyy-mm-dd hh:ii',
                                            startDate: '1970-1-1'
                                        }

                                $('.form-datetime').fixedDate().datetimepicker(options);
                                $('.form-date').fixedDate().datetimepicker($.extend(options, {minView: 2, format: 'yyyy-mm-dd'}));
                                $('.form-time').fixedDate().datetimepicker($.extend(options, {startView: 1, minView: 0, maxView: 1, format: 'hh:ii'}));

                                $('.datepicker-wrapper').click(function ()
                                {
                                    $(this).find('.form-date, .form-datetime, .form-time').datetimepicker('show').focus();
                                });

                                window.datepickerOptions = options;
                            });
                </script>
                <script language='Javascript'>themeRoot = "\/zentao\/theme\/";</script>
                <script language='Javascript'>kuid = "5b10f6a0ba2ef";</script>
                <link rel="stylesheet" href="/zentao/js/kindeditor/themes/default/default.css" />
                <script src='/zentao/js/kindeditor/kindeditor-min.js' type='text/javascript'></script>
                <script src='/zentao/js/kindeditor/lang/zh_CN.js' type='text/javascript'></script>
                <script language='javascript'>
                    var editor = {"id": ["desc"], "tools": "simpleTools"};

                    var bugTools =
                            ['formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', '|',
                                'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist', 'insertunorderedlist', '|',
                                'emoticons', 'image', 'code', 'link', '|', 'removeformat', 'undo', 'redo', 'fullscreen', 'source', 'about'];

                    var simpleTools =
                            ['formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', '|',
                                'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist', 'insertunorderedlist', '|',
                                'emoticons', 'image', 'code', 'link', '|', 'removeformat', 'undo', 'redo', 'fullscreen', 'source', 'about'];

                    var fullTools =
                            ['formatblock', 'fontname', 'fontsize', 'lineheight', '|', 'forecolor', 'hilitecolor', '|', 'bold', 'italic', 'underline', 'strikethrough', '|',
                                'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', '|',
                                'insertorderedlist', 'insertunorderedlist', '|',
                                'emoticons', 'image', 'insertfile', 'hr', '|', 'link', 'unlink', '/',
                                'undo', 'redo', '|', 'selectall', 'cut', 'copy', 'paste', '|', 'plainpaste', 'wordpaste', '|', 'removeformat', 'clearhtml', 'quickformat', '|',
                                'indent', 'outdent', 'subscript', 'superscript', '|',
                                'table', 'code', '|', 'pagebreak', 'anchor', '|',
                                'fullscreen', 'source', 'preview', 'about'];

                    $(document).ready(initKindeditor);
                    function initKindeditor(afterInit)
                    {
                        $(':input[type=submit]').next('#uid').remove();
                        $(':input[type=submit]').after("<input type='hidden' id='uid' name='uid' value=" + kuid + ">");
                        var nextFormControl = 'input:not([type="hidden"]), textarea:not(.ke-edit-textarea), button[type="submit"], select';
                        $.each(editor.id, function (key, editorID)
                        {
                            editorTool = simpleTools;
                            if (editor.tools == 'bugTools')
                                editorTool = bugTools;
                            if (editor.tools == 'fullTools')
                                editorTool = fullTools;

                            var K = KindEditor, $editor = $('#' + editorID);
                            var placeholderText = $editor.attr('placeholder');
                            if (placeholderText == undefined)
                                placeholderText = '';
                            var pasted;
                            var options =
                                    {
                                        cssPath: [themeRoot + 'zui/css/min.css'],
                                        width: '100%',
                                        height: '200px',
                                        items: editorTool,
                                        filterMode: true,
                                        bodyClass: 'article-content',
                                        urlType: 'absolute',
                                        uploadJson: createLink('file', 'ajaxUpload', 'uid=' + kuid),
                                        allowFileManager: true,
                                        langType: 'zh_CN',
                                        afterChange: function () {
                                            $editor.change().hide();
                                        },
                                        afterCreate: function ()
                                        {
                                            var frame = this.edit;
                                            var doc = this.edit.doc;
                                            var cmd = this.edit.cmd;
                                            pasted = true;
                                            if (!K.WEBKIT && !K.GECKO)
                                            {
                                                var pasted = false;
                                                $(doc.body).bind('paste', function (ev)
                                                {
                                                    pasted = true;
                                                    return true;
                                                });
                                                setTimeout(function ()
                                                {
                                                    $(doc.body).bind('keyup', function (ev)
                                                    {
                                                        if (pasted)
                                                        {
                                                            pasted = false;
                                                            return true;
                                                        }
                                                        if (ev.keyCode == 86 && ev.ctrlKey)
                                                            alert('您的浏览器不支持粘贴图片！');
                                                    })
                                                }, 10);
                                            }
                                            if (pasted && placeholderText.indexOf('可以在编辑器直接贴图。') < 0)
                                            {
                                                if (placeholderText)
                                                    placeholderText += '<br />';
                                                placeholderText += ' 可以在编辑器直接贴图。';
                                            }

                                            /* Paste in chrome.*/
                                            /* Code reference from http://www.foliotek.com/devblog/copy-images-from-clipboard-in-javascript/. */
                                            if (K.WEBKIT)
                                            {
                                                $(doc.body).bind('paste', function (ev)
                                                {
                                                    var $this = $(this);
                                                    var original = ev.originalEvent;
                                                    var file = original.clipboardData.items[0].getAsFile();
                                                    if (file)
                                                    {
                                                        $('#submit').attr('disabled', 'disabled');
                                                        $("body").click(function () {
                                                            $('#submit').removeAttr('disabled');
                                                        });

                                                        var reader = new FileReader();
                                                        reader.onload = function (evt)
                                                        {
                                                            var result = evt.target.result;
                                                            var arr = result.split(",");
                                                            var data = arr[1]; // raw base64
                                                            var contentType = arr[0].split(";")[0].split(":")[1];

                                                            html = '<img src="' + result + '" alt="" />';
                                                            $.post(createLink('file', 'ajaxPasteImage', 'uid=' + kuid), {editor: html}, function (data)
                                                            {
                                                                cmd.inserthtml(data);
                                                                $('#submit').removeAttr('disabled');
                                                            });
                                                        };
                                                        reader.readAsDataURL(file);
                                                    }
                                                });
                                            }
                                            /* Paste in firefox and other firefox. */
                                            else
                                            {
                                                K(doc.body).bind('paste', function (ev)
                                                {
                                                    setTimeout(function ()
                                                    {
                                                        var html = K(doc.body).html();
                                                        if (html.search(/<img src="data:.+;base64,/) > -1)
                                                        {
                                                            $('#submit').attr('disabled', 'disabled');
                                                            $("body").click(function () {
                                                                $('#submit').removeAttr('disabled');
                                                            });
                                                            $.post(createLink('file', 'ajaxPasteImage', 'uid=' + kuid), {editor: html}, function (data)
                                                            {
                                                                if (data.indexOf('<img') == 0)
                                                                    data = '<p>' + data + '</p>';
                                                                frame.html(data);
                                                                $('#submit').removeAttr('disabled');
                                                            });
                                                        }
                                                    }, 80);
                                                });
                                            }
                                            /* End */

                                            /* Add for placeholder. */
                                            $(this.edit.doc).find('body').after('<span class="kindeditor-ph" style="width:100%;color:#888; padding:5px 5px 5px 7px; background-color:transparent; position:absolute;z-index:10;top:2px;border:0;overflow:auto;resize:none; font-size:13px;"></span>');
                                            var $placeholder = $(this.edit.doc).find('.kindeditor-ph');
                                            $placeholder.html(placeholderText);
                                            $placeholder.css('pointerEvents', 'none');
                                            $placeholder.click(function () {
                                                frame.doc.body.focus()
                                            });
                                            if (frame.html() != '')
                                                $placeholder.hide();
                                        },
                                        afterFocus: function ()
                                        {
                                            var frame = this.edit;
                                            var $placeholder = $(frame.doc).find('.kindeditor-ph');
                                            if ($placeholder.size() == 0)
                                            {
                                                setTimeout(function () {
                                                    $(frame.doc).find('.kindeditor-ph').hide();
                                                }, 50);
                                            } else
                                            {
                                                $placeholder.hide();
                                            }
                                            $editor.prev('.ke-container').addClass('focus');
                                        },
                                        afterBlur: function ()
                                        {
                                            this.sync();
                                            $editor.prev('.ke-container').removeClass('focus');
                                            var frame = this.edit;
                                            if (K(frame.doc.body).html() == '')
                                                $(frame.doc).find('.kindeditor-ph').show();
                                        },
                                        afterTab: function (id)
                                        {
                                            var $next = $editor.next(nextFormControl);
                                            if (!$next.length)
                                                $next = $editor.parent().next().find(nextFormControl);
                                            if (!$next.length)
                                                $next = $editor.parent().parent().next().find(nextFormControl);
                                            $next = $next.first().focus();
                                            var keditor = $next.data('keditor');
                                            if (keditor)
                                                keditor.focus();
                                            else if ($next.hasClass('chosen'))
                                                $next.trigger('chosen:activate');
                                        }
                                    };
                            try
                            {
                                if (!window.editor)
                                    window.editor = {};
                                var keditor = K.create('#' + editorID, options);
                                window.editor['#'] = window.editor[editorID] = keditor;
                                $editor.data('keditor', keditor);
                            } catch (e) {
                            }
                        });

                        if ($.isFunction(afterInit))
                            afterInit();
                    }
                </script>
                <div class='container mw-1400px'>
                    <div id='titlebar'>
                        <div class='heading'>
                            <span class='prefix'><i class='icon-check-sign'></i></span>
                            <strong><small class='text-muted'><i class='icon-plus'></i></small> 建任务</strong>
                        </div>
                        <div class='actions'>
                            <button type="button" class="btn btn-default" data-toggle="customModal"><i class='icon icon-cog'></i> </button>
                        </div>
                    </div>
                    <form class='form-condensed' method='post' enctype='multipart/form-data' id='dataform' data-type='ajax'>
                        <table class='table table-form'> 
                            <tr>
                                <th class='w-100px'>所属模块</th>
                                <td id='moduleIdBox' class='w-p25-f'><select name='module' id='module' class='form-control chosen' onchange='setStories(this.value, 8)'>
                                        <option value='0' selected='selected' data-keys='/ /'>/</option>
                                        <option value='5' data-keys='/xinxizhengli /xxzl'>/信息整理</option>
                                        <option value='6' data-keys='/jiawuchuli /jwcl'>/家务处理</option>
                                        <option value='11' data-keys='/caiwuguanli /cwgl'>/财务管理</option>
                                        <option value='13' data-keys='/nvershiyi /nesy'>/女儿事宜</option>
                                        <option value='29' data-keys='/fumushiyi /fmsy'>/父母事宜</option>
                                        <option value='45' data-keys='/gerenshiwu /grsw'>/个人事务</option>
                                        <option value='54' data-keys='/laoposhiyi /lpsy'>/老婆事宜</option>
                                    </select>
                                </td>
                                <td></td><td></td><td></td><td></td>
                            </tr>
                            <tr>
                                <th>任务类型</th>
                                <td><select name='type' id='type' class=form-control onchange="setOwners(this.value)">
                                        <option value='' selected='selected' data-keys=' '></option>
                                        <option value='design' data-keys='sheji sj'>设计</option>
                                        <option value='devel' data-keys='kaifa kf'>开发</option>
                                        <option value='test' data-keys='ceshi cs'>测试</option>
                                        <option value='study' data-keys='yanjiu yj'>研究</option>
                                        <option value='discuss' data-keys='taolun tl'>讨论</option>
                                        <option value='ui' data-keys='jiemian jm'>界面</option>
                                        <option value='affair' data-keys='shiwu sw'>事务</option>
                                        <option value='misc' data-keys='qita qt'>其他</option>
                                    </select>
                                </td><td></td>
                            </tr>
                            <tr>
                                <th>指派给</th>
                                <td><select name='assignedTo[]' id='assignedTo' class='form-control chosen'>
                                        <option value='' selected='selected' data-keys=' '></option>
                                        <option value='justincai' data-keys='j:caihuachen jchc'>J:蔡华臣</option>
                                    </select>
                                </td>
                                <td>
                                    <button type='button' class='btn btn-link hidden' id='selectAllUser'>全部</button>
                                </td>
                            </tr>
                            <tr>
                                <th>相关需求</th>
                                <td colspan='5'>
                                    <div class='input-group'>
                                        <span id='story'>没有可关联的相关需求，您可以为当前项目<a href='/zentao/project-linkStory-8.html'  target='_blank'>关联需求</a>
                                            ，然后<a href='javascript:loadStories(8)' >刷新</a>
                                        </span>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>任务名称</th>
                                <td colspan='5'>
                                    <div class='row-table'>
                                        <div class='col-table'>
                                            <div class="input-group w-p100">
                                                <input type='hidden' id='color' name='color' data-provide='colorpicker' data-wrapper='input-group-btn fix-border-right' data-pull-menu-right='false' data-btn-tip='颜色标签' data-update-text='#name'>
                                                <input type='text' name='name' id='name' value='' class='form-control' autocomplete='off' />
                                                <span class='input-group-btn'><a href='javascript:copyStoryTitle();' id='copyButton' class='btn'>同需求</a></span>
                                            </div>
                                        </div>
                                        <div class='col-table' id='priRowCol'>
                                            <div class='input-group'>
                                                <span class='input-group-addon fix-border br-0'>优先级</span>
                                                <div class='input-group-btn dropdown-pris'>
                                                    <button type='button' class='btn dropdown-toggle br-0' data-toggle='dropdown'>
                                                        <span class='pri-text'></span> &nbsp;<span class='caret'></span>
                                                    </button>
                                                    <ul class='dropdown-menu pull-right'></ul>
                                                    <select name='pri' id='pri' class='hide'>
                                                        <option value='0' data-keys=' '></option>
                                                        <option value='3' data-keys='3 3'>3</option>
                                                        <option value='1' data-keys='1 1'>1</option>
                                                        <option value='2' data-keys='2 2'>2</option>
                                                        <option value='4' data-keys='4 4'>4</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>

                                        <div class='col-table' id='estRowCol'>
                                            <div class='input-group'>
                                                <span class='input-group-addon fix-border br-0'>预</span>
                                                <input type='text' name='estimate' id='estimate' value='' class='form-control' placeholder='小时' autocomplete='off' />
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>任务描述</th>
                                <td colspan='5'><textarea name='desc' id='desc' rows='10' class='form-control'></textarea>
                                </td>
                            </tr>
                            <tr>
                                <th>日程规划</th>
                                <td id='planAndMailCell' colspan='5' class='has-mail-col'>
                                    <div class='row-table'>
                                        <div class='col-table' id='taskPlanCol'>
                                            <div class='input-group' id='dataPlanGroup'>
                                                <input type='text' name='estStarted' id='estStarted' value='' class='form-control form-date' placeholder='预计开始' />
                                                <span class='input-group-addon fix-border'>~</span>
                                                <input type='text' name='deadline' id='deadline' value='' class='form-control form-date' placeholder='截止日期' />
                                            </div>
                                        </div>
                                        <div class='col-table' id='mailCol'>
                                            <div id='mailtoGroup' class='input-group'>
                                                <span class='input-group-addon'>抄送给</span>
                                                <select name='mailto[]' id='mailto' multiple class='form-control'>
                                                    <option value='' selected='selected' data-keys=' '></option>
                                                    <option value='justincai' data-keys='j:caihuachen jchc'>J:蔡华臣</option>
                                                </select>
                                                <span class="input-group-btn"><a data-toggle="tooltip" title="维护列表" href="/zentao/my-managecontacts-0-new.html?onlybody=yes" target='_blank' data-icon='cog' data-title='维护列表' class='btn iframe'><i class='icon icon-cog'></i></a><a data-toggle="tooltip" title="刷新" href="###" class="btn" onclick="ajaxGetContacts(this)"><i class="icon icon-refresh"></i></a></span>                </div>
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

                                    <script language='javascript'>
                                        $(function ()
                                        {
                                            var maxUploadInfo = maxFilesize();
                                            parentTag = $('#fileform').parent();
                                            if (parentTag.get(0).tagName == 'TD')
                                            {
                                                parentTag.parent().find('th').append(maxUploadInfo);
                                            }
                                            if (parentTag.get(0).tagName == 'FIELDSET')
                                            {
                                                parentTag.find('legend').append(maxUploadInfo);
                                            }
                                        });

                                        /**
                                         * Check file size and type.
                                         * 
                                         * @param  obj $obj 
                                         * @access public
                                         * @return void
                                         */
                                        function checkSizeAndType(obj)
                                        {
                                            if (typeof ($(obj)[0].files) != 'undefined')
                                            {
                                                var maxUploadInfo = '50M';
                                                var sizeType = {'K': 1024, 'M': 1024 * 1024, 'G': 1024 * 1024 * 1024};
                                                var unit = maxUploadInfo.replace(/\d+/, '');
                                                var maxUploadSize = maxUploadInfo.replace(unit, '') * sizeType[unit];
                                                var fileSize = 0;
                                                $(obj).closest('#fileform').find(':file').each(function ()
                                                {
                                                    /* Check file type. */
                                                    fileName = $(this)[0].files[0].name;
                                                    dotPos = fileName.lastIndexOf('.');
                                                    fileType = fileName.substring(dotPos + 1);
                                                    if ((',' + dangerFiles + ',').indexOf((',' + fileType + ',')) != -1)
                                                        alert(' 您选择的文件存在安全风险，系统将不予上传。');

                                                    if ($(this).val())
                                                        fileSize += $(this)[0].files[0].size;
                                                })
                                                if (fileSize > maxUploadSize)
                                                    alert(' 文件大小已经超过限制，可能不能成功上传！');//Check file size.
                                            }
                                        }

                                        /**
                                         * Show the upload max filesize of config.  
                                         */
                                        function maxFilesize() {
                                            return "(<span class='red'>50M</span>)";}

                                        /**
                                         * Set the width of the file form.
                                         * 
                                         * @param  float  $percent 
                                         * @access public
                                         * @return void
                                         */
                                        function setFileFormWidth(percent)
                                        {
                                            totalWidth = Math.round($('#fileform').parent().width() * percent);
                                            titleWidth = totalWidth - $('.fileControl').width() - $('.fileLabel').width() - $('.icon').width();
                                            if ($.browser.mozilla)
                                                titleWidth -= 8;
                                            if (!$.browser.mozilla)
                                                titleWidth -= 12;
                                            $('#fileform .text-3').css('width', titleWidth + 'px');
                                        }
                                        ;

                                        /**
                                         * Add a file input control.
                                         * 
                                         * @param  object $clickedButton 
                                         * @access public
                                         * @return void
                                         */
                                        function addFile(clickedButton)
                                        {
                                            fileRow = "  <table class='fileBox' id='fileBox$i'>\n    <tr>\n      <td class='w-p45'><div class='form-control file-wrapper'><input type='file' name='files[]' class='fileControl'  tabindex='-1' onchange='checkSizeAndType(this)'\/><\/div><\/td>\n      <td class=''><input type='text' name='labels[]' class='form-control' placeholder='\u6807\u9898\uff1a' tabindex='-1' \/><\/td>\n      <td class='w-30px'><a href='javascript:void(0);' onclick='addFile(this)' class='btn btn-block'><i class='icon-plus'><\/i><\/a><\/td>\n      <td class='w-30px'><a href='javascript:void(0);' onclick='delFile(this)' class='btn btn-block'><i class='icon-remove'><\/i><\/a><\/td>\n    <\/tr>\n  <\/table>";
                                            fileRow = fileRow.replace('$i', $('.fileID').size() + 1);

                                            /* Get files and labels name.*/
                                            filesName = $(clickedButton).closest('tr').find('input[type="file"]').attr('name');
                                            labelsName = $(clickedButton).closest('tr').find('input[type="text"]').attr('name');

                                            /* Add file input control and set files and labels name in it.*/
                                            $fileBox = $(clickedButton).closest('.fileBox').after(fileRow).next('.fileBox');
                                            $fileBox.find('input[type="file"]').attr('name', filesName);
                                            $fileBox.find('input[type="text"]').attr('name', labelsName);

                                            setFileFormWidth(0.9);
                                            updateID();
                                        }

                                        /**
                                         * Delete a file input control.
                                         * 
                                         * @param  object $clickedButton 
                                         * @access public
                                         * @return void
                                         */
                                        function delFile(clickedButton)
                                        {
                                            if ($('.fileBox').size() == 1)
                                                return;
                                            $(clickedButton).closest('.fileBox').remove();
                                            updateID();
                                        }

                                        /**
                                         * Update the file id labels.
                                         * 
                                         * @access public
                                         * @return void
                                         */
                                        function updateID()
                                        {
                                            i = 1;
                                            $('.fileID').each(function () {
                                                $(this).html(i++)
                                            });
                                        }

                                        $(function () {
                                            setFileFormWidth(0.9)
                                        });
                                    </script>
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
                <style>
                    #customModal .checkbox-inline{width:90px}
                    #customModal .checkbox-inline+.checkbox-inline{margin-left:0px;}
                </style>
                <div class="modal fade" id="customModal" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog w-800px">
                        <div class="modal-content">
                            <form class='form-condensed' method='post' target='hiddenwin' action='/zentao/custom-ajaxSaveCustomFields-task-custom-createFields.html'>
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                    <h4 class="modal-title">
                                        <i class="icon-cog"></i> 自定义            <div class='pull-right' style='margin-right:15px;'> <button type='submit' id='submit' class='btn btn-primary'  data-loading='稍候...'>保存</button></div>
                                    </h4>
                                </div>
                                <div class="modal-body">
                                    <p><label class='checkbox-inline'><input type='checkbox' name='fields[]' value='story'  checked ='checked' id='fieldsstory' /> 相关需求</label><label class='checkbox-inline'><input type='checkbox' name='fields[]' value='estStarted'  checked ='checked' id='fieldsestStarted' /> 预计开始</label><label class='checkbox-inline'><input type='checkbox' name='fields[]' value='deadline'  checked ='checked' id='fieldsdeadline' /> 截止日期</label><label class='checkbox-inline'><input type='checkbox' name='fields[]' value='mailto'  checked ='checked' id='fieldsmailto' /> 抄送给</label><label class='checkbox-inline'><input type='checkbox' name='fields[]' value='pri'  checked ='checked' id='fieldspri' /> 优先级</label><label class='checkbox-inline'><input type='checkbox' name='fields[]' value='estimate'  checked ='checked' id='fieldsestimate' /> 最初预计</label></p>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <script>
                    $("button[data-toggle='customModal']").click(function () {
                        $('#customModal').modal('show')
                    });
                    $(function ()
                    {
                        $table = $('.outer form table:first');
                        $form = $table.closest('form');
                        if ($table.width() > $form.width())
                            $form.css('overflow-x', 'auto')
                    })
                </script>
            </div>  <script>setTreeBox()</script>

            <div id='divider'></div>
            <iframe frameborder='0' name='hiddenwin' id='hiddenwin' scrolling='no' class='debugwin hidden'></iframe>
        </div><div id='footer'>
            <div id='crumbs'>
                <a href='/zentao/my/' >禅道</a>
                &nbsp;<i class="icon-angle-right"></i>&nbsp;<a href='/zentao/project/' >项目</a>
                &nbsp;<i class="icon-angle-right"></i>&nbsp;<a href='/zentao/project-browse-8-task.html' >私人事务</a>
                &nbsp;<i class="icon-angle-right"></i>&nbsp;任务&nbsp;<i class="icon-angle-right"></i>&nbsp;建任务  </div>
            <div id='poweredby'>
                <a href='http://www.zentao.net' target='_blank' class='text-primary'><i class='icon-zentao'></i> 禅道9.5</a> &nbsp;
                <a href='http://api.zentao.net/goto.php?item=proversion&from=footer' target='_blank' id='proLink' class='text-important'>专业版 <i class='text-danger icon-pro-version'></i></a> &nbsp;     <a href='/zentao/misc-downNotify.html' title='下载桌面提醒'><i class='icon-bell'></i></a>
                &nbsp;   </div>
        </div>

        <script>
            browserNotice = '你目前使用的浏览器可能无法得到最佳浏览效果，建议使用Chrome、火狐、IE9+、Opera、Safari浏览器。'
            function ajaxIgnoreBrowser() {
                $.get(createLink('misc', 'ajaxIgnoreBrowser'));
            }
            $(function () {
                showBrowserNotice()
            });


        </script>


        <script>config.onlybody = 'no';</script>
        <script language='Javascript'>/* If left = 0, warning. */
            function checkLeft()
            {
                value = $("#left").val();
                if (isNaN(parseInt(value)) || value == 0)
                {
                    if (confirm(confirmFinish))
                    {
                        return true;
                    } else
                    {
                        return false;
                    }
                }
            }
            /* Copy story title as task title. */
            function copyStoryTitle()
            {
                var storyTitle = $('#story option:selected').text();
                startPosition = storyTitle.indexOf(':') + 1;
                endPosition = storyTitle.lastIndexOf('(');
                storyTitle = storyTitle.substr(startPosition, endPosition - startPosition);
                $('#name').attr('value', storyTitle);
            }

            /* Set the assignedTos field. */
            function setOwners(result)
            {
                if (result == 'affair')
                {
                    $('#assignedTo').attr('multiple', 'multiple');
                    $('#assignedTo').chosen('destroy');
                    $('#assignedTo').chosen(defaultChosenOptions);
                    $('#selectAllUser').removeClass('hidden');
                } else if ($('#assignedTo').attr('multiple') == 'multiple')
                {
                    $('#assignedTo').removeAttr('multiple');
                    $('#assignedTo').chosen('destroy');
                    $('#assignedTo').chosen(defaultChosenOptions);
                    $('#selectAllUser').addClass('hidden');
                }
            }

            /* Set preview and module of story. */
            function setStoryRelated()
            {
                setPreview();
                if ($('#module').val() == 0)
                    setStoryModule();
            }

            /* Set the story module. */
            function setStoryModule()
            {
                var storyID = $('#story').val();
                if (storyID)
                {
                    var link = createLink('story', 'ajaxGetModule', 'storyID=' + storyID);
                    $.get(link, function (moduleID)
                    {
                        $('#module').val(moduleID);
                        $("#module").trigger("chosen:updated");
                    });
                }
            }

            /* Set the story priview link. */
            function setPreview()
            {
                if (!$('#story').val())
                {
                    $('#preview').addClass('hidden');
                    $('#copyButton').parent().addClass('hidden');
                } else
                {
                    storyLink = createLink('story', 'view', "storyID=" + $('#story').val());
                    var concat = config.requestType != 'GET' ? '?' : '&';
                    storyLink = storyLink + concat + 'onlybody=yes';
                    $('#preview').removeClass('hidden');
                    $('#preview a').attr('href', storyLink);
                    $('#copyButton').parent().removeClass('hidden');
                }

                setAfter();
            }

            /**
             * Set after locate. 
             * 
             * @access public
             * @return void
             */
            function setAfter()
            {
                if ($("#story").length == 0 || $("#story").select().val() == '')
                {
                    if ($('input[value="continueAdding"]').attr('checked') == 'checked')
                    {
                        $('input[value="toTaskList"]').attr('checked', 'checked');
                    }
                    $('input[value="continueAdding"]').attr('disabled', 'disabled');
                } else
                {
                    $('input[value="continueAdding"]').attr('checked', 'checked');
                    $('input[value="continueAdding"]').attr('disabled', false);
                }
            }

            /**
             * Load stories.
             * 
             * @param  int    $projectID 
             * @access public
             * @return void
             */
            function loadStories(projectID)
            {
                moduleID = $('#module').val();
                setStories(moduleID, projectID);
            }

            /**
             * load stories of module.
             * 
             * @access public
             * @return void
             */
            function loadModuleRelated()
            {
                moduleID = $('#module').val();
                projectID = $('#project').val();
                setStories(moduleID, projectID);
            }

            /* Get select of stories.*/
            function setStories(moduleID, projectID)
            {
                link = createLink('story', 'ajaxGetProjectStories', 'projectID=' + projectID + '&productID=0&branch=0&moduleID=' + moduleID);
                $.get(link, function (stories)
                {
                    var storyID = $('#story').val();
                    if (!stories)
                        stories = '<select id="story" name="story" class="form-control"></select>';
                    $('#story').replaceWith(stories);
                    $('#story').val(storyID);
                    setPreview();
                    $('#story_chosen').remove();
                    $("#story").chosen(defaultChosenOptions);
                });
            }

            $(document).ready(function ()
            {
                setPreview();

                $('#selectAllUser').on('click', function ()
                {
                    var $assignedTo = $('#assignedTo');
                    if ($assignedTo.attr('multiple'))
                    {
                        $assignedTo.children('option').attr('selected', 'selected');
                        $assignedTo.trigger('chosen:updated');
                    }
                });

                $('[data-toggle=tooltip]').tooltip();

                // adjust form controls layout
                var ajustFormControls = function ()
                {
                    // adjust style for file box
                    applyCssStyle('.fileBox > tbody > tr > td:first-child {transition: none; width: ' + ($('#dataPlanGroup').width() - 1) + 'px}', 'filebox');

                    // adjust #priRowCol and #estRowCol size
                    var $priRowCol = $('#priRowCol'),
                            $estRowCol = $('#estRowCol');
                    $priRowCol.css('width', 54 + $priRowCol.find('.input-group-addon').outerWidth());
                    $estRowCol.css('width', 55 + $estRowCol.find('.input-group-addon').outerWidth());
                };
                ajustFormControls();
                $(window).resize(ajustFormControls);

                /* First unbind ajaxForm for form.*/
                $("form[data-type='ajax']").unbind('submit');
                setForm();

                /* Bind ajaxForm for form again. */
                $.ajaxForm("form[data-type='ajax']", function (response)
                {
                    if (response.message)
                        alert(response.message);
                    if (response.locate)
                    {
                        if (response.locate == 'reload' && response.target == 'parent')
                        {
                            parent.$.cookie('selfClose', 1);
                            parent.$.closeModal(null, 'this');
                        } else
                        {
                            location.href = response.locate;
                        }
                    }
                    return false;
                });
            });

        </script>
    </body>
</html>
