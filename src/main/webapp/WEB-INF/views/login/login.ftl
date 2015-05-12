<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>南方校园运营平台-登录</title>
<#include "../include/css.ftl">
<style type="text/css">
body{background:#f9f9f9;}
.login{width:598px;height:230px; margin:0 auto;padding-top:30px;background:#EEE;border:1px #ccc solid;border-top:0;}
.login .table{width:500px;margin:0 auto;}
.login .table td{border:0;}
.login .table label{font-size:16px;}
.login-hd{width:600px;margin:0 auto;overflow:hidden;margin-top:35px;font-size:20px;font-weight:600;height:40px;}
.login-hd div{float:left;width:200px; height:35px; line-height:40px;cursor:pointer;}
.login-hd div a{color:#FFF; display:block; text-decoration:none; text-align:center;}
.login-hd-bottom{width:600px;margin:0 auto;height:5px;background:#CCC;margin-top:-5px;}
</style>
</head>
<body >
<div class="login-hd">
    <div class="badge-success"><a href="javascript:void(0)" target="_blank">首页</a></div>
    <div class="badge-important"><a href="javascript:void(0)" target="_blank">登录</a></div>
    <div class="badge-info"><a href="javascript:void(0)" target="_blank">注册</a></div>
</div>
<div class="login-hd-bottom"></div>
<form id="login">
<div class="login" id="myLogin">
    <table class="table">
        <tr>
            <td style="width:120px;"><label>用户名：</label></td>
            <td><input type="text" class="span4" autocomplete="off" name="name" minlength="3" maxlength="15" required value="${(Cookies.name)!''}"></td>
        </tr>
        <tr>
            <td><label>密码：</label></td>
            <td><input type="password" class="span4" autocomplete="off" name="password" minlength="8" maxlength="20" required></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <label for="remember" class="checkbox inline">
                    <input type="checkbox" value="1" name="saveName" checked> 记住账号
                </label> &nbsp;&nbsp;
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" name="submit" class="btn span2" value="登录"/></td>
        </tr>
    </table>
</div>
</form>
<#include "../include/javascript.ftl">
<script type="text/javascript" src="${(ContextPath)!''}/script/login/loginManager.js"></script>
<script type="text/javascript">
$(function() {
    $('.login-hd div').each(function() {
        $(this).css({'border-bottom': '5px '+$(this).css("background-color")+' solid', 'background': 'none'});
        $(this).find('a').hide();
    });
    $('.login-hd').delegate("div", "mouseover", function(){
        $('.login-hd div').each(function() {
            $(this).css({'border-bottom': '5px '+$(this).css("border-bottom-color")+' solid', 'background': 'none'});
            $(this).find('a').hide();
        });
        $(this).css('background-color', $(this).css("border-bottom-color"));
        $(this).find('a').show();
    });
    $('.login-hd').mouseleave(function() {
        $('.login-hd div').each(function() {
            $(this).css({'border-bottom': '5px '+$(this).css("border-bottom-color")+' solid', 'background': 'none'});
            $(this).find('a').hide();
        });
    });
    loginManager.init();
});
</script>
</body>
</html>