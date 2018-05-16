<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div class="fade_layer"></div>
<div class="detail_layer select_peo">
    <div class="title">选择人员</div>
    <div class="select_peo_con">
        <div class="left">
            <div class="areas_list">
                <ul class="yiji">
                    <li class="areas_list_one"><a>委领导</a> 
                        <dl>
                            <dd><a>领导二级</a>
                                <dl>
                                    <dd><a>领导三级</a></dd>
                                    <ul class="areas_list_two">
                                        <li><span id="99">张三3</span></li>
                                        <li><span id="100">梁四3</span></li>
                                        <li><span id="101">王王3</span></li>
                                        <li><span id="102">董73</span></li>
                                        <li><span id="103">陈四3</span></li>
                                        <li><span id="104">杨过3</span></li> 
                                    </ul>
                                    <dd><a>领导三级2</a>
                                        <dl>
                                            <dd><a>领导四级</a></dd>
                                            <ul class="areas_list_two">
                                                <li><span id="99">张淘32</span></li>
                                                <li><span id="100">梁齐超32</span></li>
                                                <li><span id="101">王得力32</span></li>
                                                <li><span id="102">董亚军32</span></li>
                                                <li><span id="103">陈贝斯32</span></li>
                                                <li><span id="104">杨过32</span></li> 
                                            </ul>
                                        </dl>
                                    </dd>

                                </dl>   
                            </dd>
                            <dd><a>委领导二级2</a>
                                <dl>
                                    <dd><a>领导三级</a>
                                        <dl>
                                            <dd><a>领导四级</a></dd>
                                            <ul class="areas_list_two">
                                                <li><span id="99">张三3</span></li>
                                                <li><span id="100">梁四3</span></li>
                                                <li><span id="101">王王3</span></li>
                                                <li><span id="102">董73</span></li>
                                                <li><span id="103">陈四3</span></li>
                                                <li><span id="104">杨过3</span></li> 
                                            </ul>
                                        </dl>
                                    </dd>
                                </dl>
                            </dd>
                            <dd><a>委领导二级3</a></dd>
                            <ul class="areas_list_two">
                                <li><span id="637">隋志</span></li>
                                <li><span id="730">杨过</span></li>
                                <li><span id="731">王泽涛</span></li>
                                <li><span id="103">陈广</span></li>
                                <li><span id="104">杨勇</span></li>
                                <li><span id="637">隋文</span></li>
                                <li><span id="730">杨治国</span></li>
                                <li><span id="731">王民</span></li>
                            </ul>
                        </dl>
                    </li>
                    <li class="areas_list_one"><a>本地领导</a></li>
                    <ul class="areas_list_two">
                        <li><span id="105">武华</span></li>
                        <li><span id="106">李文</span></li>
                        <li><span id="107">田俊</span></li>
                        <li><span id="108">陈菲</span></li>
                    </ul>
                    <li class="areas_list_one"><a>北京领导</a></li>
                    <ul class="areas_list_two">
                        <li><span id="109">田军</span></li>
                        <li><span id="110">侯涛</span></li>
                        <li><span id="111">李国</span></li>
                        <li><span id="112">王诺</span></li>
                        <li><span id="113">孙婷</span></li>
                        <li><span id="114">李安健</span></li>
                        <li><span id="115">王卢欣</span></li>
                        <li><span id="116">李曹静</span></li>
                        <li><span id="117">张吴剑</span></li>
                        <li><span id="118">陈佟庆</span></li>
                        <li><span id="119">李唐琪</span></li>
                    </ul>
                </ul>
            </div>
        </div>
        <div class="center">
            <a id="list_add"><img src="<c:url value="/resources/admin/plugins/personselect/images/addicon.jpg" />"></a>
        </div>
        <div class="right">
            <ul class="send_to">
            </ul>
        </div>
        <div class="clear"></div>
        <div class="bot_btn">
            <a onclick="do_add(this)" class="a_con do_add">确定</a><a class="a_con close_btn">取消</a> 
        </div>
    </div>
</div>

<script>
                
</script>
