<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="X-UA-Compatible" content="IE=8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>南方校园运营平台-系统用户编辑</title>
<#include "../include/css.ftl">
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${(ContextPath)!''}/manager/manager">管理系统用户</a></li>
    <li class="active"><a href="javascript:void(0)"><i class="icon-plus"></i><#if manager??>编辑<#else>添加</#if>系统用户</a></li>
</ul>
<div class="main">
    <form class="form-horizontal form" id="manager" onsubmit="return false;">
        <h4>系统用户</h4>
        <table class="tb">
            <tbody>
                <tr>
                    <th>用户名</th>
                    <td>
                        <input type="hidden" id="id" value="${(manager.id)!''}"/>
                        <input type="text" name="name" id="name" class="span6" 
                            value="${(manager.name)!''}" minlength="3" maxlength="15" required <#if manager??>readOnly</#if>>
                    </td>
                </tr>
                <#if !(manager??)>
                <tr>
                    <th>密码</th>
                    <td>
                        <input type="password" name="password" id="password" class="span6" 
                            value="${(manager.password)!''}" minlength="8" maxlength="20" required>
                    </td>
                </tr>
                <tr>
                    <th>确认密码</th>
                    <td>
                        <input type="password" name="passwordConfirm" id="passwordConfirm" class="span6" 
                            value="${(manager.password)!''}" minlength="8" maxlength="20" equalTo="#password" required>
                    </td>
                </tr>
                </#if>
                <tr>
                    <th>权限分配</th>
                    <td>
                        <#list menus as menu>
                            <label><input type="checkbox" name="menus" value="${menu.id}">${menu.name}</label>
                        </#list>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <input type="submit" name="submit" value="提交" class="btn btn-primary span3">
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</div>
<#include "../include/javascript.ftl">
<script type="text/javascript" src="${(ContextPath)!''}/script/manager/managerEditor.js"></script>
<script type="text/javascript">
managerEditor.init();
<#if manager.menus??>
    <#list manager.menus as menu>
    $(":input[name='menus'][value='${menu.id}']").attr("checked", true);
    </#list>
</#if>
</script>
</body>
</html>