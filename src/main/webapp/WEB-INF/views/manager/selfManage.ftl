<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="X-UA-Compatible" content="IE=8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>南方校园运营平台-账户管理</title>
<#include "../include/css.ftl">
</head>
<body>
    <div class="main">
        <form class="form-horizontal form" id="selfManage">
            <h4>管理员信息修改</h4>
            <table class="tb">
                <tbody>
                <tr>
                    <th><label for="">管理员帐号</label></th>
                    <td>
                        <input type="text" name="name" class="span6" value="${(Manager.name)!''}" readonly>
                    </td>
                </tr>
                <tr>
                    <th><label for="">管理员密码</label></th>
                    <td>
                        <input type="password" name="oldPwd" class="span6" value="" minlength="8" maxlength="20" required>
                        <div class="help-block">请输入当前的管理员密码</div>
                    </td>
                </tr>
                <tr>
                    <th style="color:red;">新密码</th>
                    <td>
                        <input type="password" name="newPwd" id="newPwd" class="span6" value="" minlength="8" maxlength="20" required>
                        <div class="help-block">请填写密码，长度为 8 - 20 个字符</div>
                    </td>
                </tr>
                <tr>
                    <th style="color:red;">确认密码</th>
                    <td>
                        <input type="password" name="newPwdConfirm" class="span6" value="" minlength="8" maxlength="20" equalTo="#newPwd" required>
                        <div class="help-block">重复输入密码，确认正确输入</div>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <input name="submit" type="submit" value="提交" class="btn btn-primary span3">
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
</div>
<#include "../include/javascript.ftl">
<script type="text/javascript" src="${(ContextPath)!''}/script/manager/managerManager.js"></script>
<script type="text/javascript">
managerManager.initSelfManage();
</script>
</body>
</html>