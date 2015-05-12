<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>南方校园运营平台</title>
<#include "include/css.ftl">
</head>
<body style="height:100%; overflow:hidden;" scroll="no">
<div id="header">
    <div class="logo pull-left" style="background:#36393a ">南方校园</div>
    <!-- 导航 -->
    <div class="hnav clearfix">
        <div class="row-fluid">
            <!-- 右侧管理菜单 -->
            <ul class="hnav-manage text-center unstyled pull-right">
                <li class="hnav-parent"><a href="javascript:void(0)"><i class="icon-user icon-large"></i>${(Manager.name)!''}</a></li>
                <li class="hnav-parent"><a href="${(ContextPath)!''}/manager/logout"><i class="icon-signout icon-large"></i>退出</a></li>
            </ul>
            <!-- end -->
        </div>
    </div>
    <!-- end -->
</div>
<!-- 头部 end -->
<div class="content-main">
    <table width="100%" height="100%" cellspacing="0" cellpadding="0" id="frametable">
        <tbody>
            <tr>
                <td valign="top" height="100%" class="content-left mCustomScrollbar _mCS_1" style="height: 205px;"><div class="mCustomScrollBox mCS-dark-thin" id="mCSB_1" style="position:relative; height:100%; overflow:hidden; max-width:100%;"><div class="mCSB_container mCS_no_scrollbar" style="position: relative; top: 0px;">
                    <div class="sidebar-nav" style="">
                        <div id="snav-big" style="height: 205px;">
                            <span class="snav-big on" type="0"><i class="icon-star"></i><span>南方校园</span></span>
                        </div>
                        <div id="snav">
                            <#if (Manager.menus)??>
                            <#list Manager.menus as menu>
                            <ul class="snav unstyled" id="0">
                                <li class="snav-header-list"><a href="${(ContextPath)!''}${(menu.servletPath)!''}" target="main">${(menu.name)!''}</a></li>
                            </ul>
                            </#list>
                            </#if>
                            <ul class="snav unstyled" id="0">
                                <li class="snav-header-list"><a href="${(ContextPath)!''}/manager/manager/self" target="main">账户管理</a></li>
                            </ul>
                        </div>
                    </div>
                </div><div class="mCSB_scrollTools" style="position: absolute; display: none;"><a class="mCSB_buttonUp" oncontextmenu="return false;"></a><div class="mCSB_draggerContainer"><div class="mCSB_dragger" style="position: absolute; top: 0px;" oncontextmenu="return false;"><div class="mCSB_dragger_bar" style="position:relative;"></div></div><div class="mCSB_draggerRail"></div></div><a class="mCSB_buttonDown" oncontextmenu="return false;"></a></div></div></td>
                <td valign="top" height="100%" style="">
                    <iframe width="100%" scrolling="yes" height="100%" frameborder="0" style="min-width:800px; overflow:visible; height:100%;" name="main" id="main" src="${(ContextPath)!''}/manager/welcome">
                    </iframe>
                </td>
            </tr>
        </tbody>
    </table>
</div>
<#include "include/javascript.ftl">
<script type="text/javascript">
function max(a) {
    var b = a[0];
    for(var i=1;i<a.length;i++){ if(b<a[i])b=a[i]; }
    return b;
}
function currentMenuItem(a) {
    window.frames['main'].location.href= a;
}
function switchHandler(s) {
    var mainurl = window.frames['main'].location;
    if (window.frames['main'].location.href.indexOf('global') > -1) {
        window.top.location.href = '?do=profile';
    }
    window.top.location.href = '?do=profile';
    $('#current-account').html(s);
}
function strlen(str) {
        var n = 0;
        for(i=0;i<str.length;i++){
            var leg=str.charCodeAt(i);
            n+=1;
        }
        return n;
}
function leftScrollbar() {
    $('.content-left').mCustomScrollbar("destroy");
    $('.content-left').mCustomScrollbar({
        scrollButtons:{
            enable:true
        },
        theme:"dark-thin",
        autoHideScrollbar:false
    });
}
$(document).ready(function() {
    //顶部子导航
    $(".hnav").delegate(".hnav-parent", "mouseover", function(){
        var $this = this;
        if ($(this).attr('id') == 'wechatpanel') {
            if ($(this).attr('loading') == '1'){
                return false;
            }
            position();
            if (cookie.get('wechatloaded') == '1') {
                return true;
            }
            $($this).find(".hnav-child").html('<li><a>加载中</a></li>');
            $(this).attr('loading', '1');
            ajaxopen('member.php?act=wechat&', function(s){
                var obj = $($this).find(".hnav-child");
                var html = '';
                for (i in s) {
                    html += '<li><a href="account.php?act=switch&id='+s[i]['weid']+'" onclick="return ajaxopen(this.href, function(s) {switchHandler(s)})">'+s[i]['name']+'</a></li>';
                }
                obj.html(html);
                $('#wechatpanel').attr('loading', '0');
            });
        } else {
            position();
        }
        function position() {
            var tmp = new Array();
            $($this).find(".hnav-child").show();
            $($this).find(".hnav-child li").each(function(i) {
                tmp[i] = strlen($(this).find("a").html());
            });
            $($this).find(".hnav-child li a").css("width", max(tmp)*18);
            $($this).find(".hnav-child").css("left", $($this).offset().left);
        }
        return false;
    });
    $(".hnav").delegate(".hnav-parent", "mouseout", function(){
        $(".hnav-child").hide();
    });
    //左侧导航
    var snav_type = $(".sidebar-nav #snav-big .snav-big:eq(0)").attr("type");
    $(".sidebar-nav #snav-big .snav-big:eq(0)").addClass("on");
    $(".sidebar-nav .snav[id="+snav_type+"]").removeClass("hide");
    $(".sidebar-nav #snav-big").delegate(".snav-big", "click", function(){
        var a = $(this).attr("type");
        $(".sidebar-nav #snav-big .snav-big").removeClass("on");
        $(this).addClass("on");
        $(".sidebar-nav .snav").addClass("hide");
        $(".sidebar-nav .snav[id="+a+"]").removeClass("hide");
        //右侧菜单滚动控制
        leftScrollbar();
    });
    $(".sidebar-nav").delegate(".snav-header", "click", function(){
        var a = $(this).hasClass("open");
        $(".sidebar-nav .snav-header").removeClass("open");
        $(".sidebar-nav .snav-list").hide();
        if(a) {
            $(this).removeClass("open");
            $(this).parent().find(".snav-list").each(function(i) {
                $(this).hide();
            });
        } else {
            $(this).addClass("open");
            $(this).parent().find(".snav-list").each(function(i) {
                $(this).show();
            });
        }
        //右侧菜单滚动控制
        leftScrollbar();
        return false;
    });
    $(".sidebar-nav .snav").each(function() {
        if($(this).find(".snav-header").hasClass("open")) {
            $(this).find(".snav-list").each(function() {
                $(this).toggle();
            });
        }
        $(this).find(".snav-list").each(function() {
            if($(this).hasClass("current")) {
                $(this).parent().find(".snav-header").toggleClass("open");
                $(this).parent().find(".snav-list").each(function() {
                    $(this).toggle();
                });
            }
        });
        $(this).find(".snav-list a,.snav-header-list a").click(function() {
            $(".snav-list,.snav-header-list").removeClass("current");
            $(this).parent().addClass("current");
            currentMenuItem($(this).attr("href"));
            return false;
        });
    });
});
$(function() {
    //调整框架宽高 兼容ie8
    $(".content-main, .content-main table td, .sidebar-nav #snav-big").height($(window).height()-40);
    $("#main").width($(window).width()-200);
    //右侧菜单滚动控制
    leftScrollbar();
});
$(window).resize(function(){
    //调整框架宽高 兼容ie8
    $(".content-main, .content-main table td, .sidebar-nav #snav-big").height($(window).height()-40);
    $("#main").width($(window).width()-200);
    //右侧菜单滚动控制
    leftScrollbar();
});
</script>
</body>
</html>