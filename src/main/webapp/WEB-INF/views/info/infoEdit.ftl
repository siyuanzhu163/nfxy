<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="X-UA-Compatible" content="IE=8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>南方校园运营平台-资讯编辑</title>
<#include "../include/css.ftl">
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${(ContextPath)!''}/manager/info/${part!''}">管理资讯-${part!''}</a></li>
    <li class="active"><a href="javascript:void(0)"><i class="icon-plus"></i><#if info??>编辑<#else>添加</#if>资讯-${part!''}</a></li>
</ul>
<div class="main">
    <form class="form-horizontal form" id="info" onsubmit="return false;">
        <h4>${part!''}</h4>
        <table class="tb">
            <tbody>
                <tr>
                    <th>分类</th>
                    <td>
                        <input type="hidden" id="id" value="${(info.id?c)!''}"/>
                        <input type="hidden" id="part" value="${(info.part)!part}"/>
                        <select id="type">
                            <#list types as type>
                            <option value="${type!''}" <#if info?? && info.type == type>selected</#if>>${(type.desc)!''}</option>
                            </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>标题</th>
                    <td>
                        <input type="text" name="title" id="title" class="span6" value="${(info.title)!''}" maxlength="64" required>
                        <div class="help-block">0-64个字符</div>   
                    </td>
                </tr>
                <tr>
                    <th>作者（选填）</th>
                    <td>
                        <input type="text" name="author" id="author" class="span6" value="${(info.author)!''}" maxlength="8">
                        <div class="help-block">0-8个字符</div>   
                    </td>
                </tr>
                <tr>
                    <th>封面</th>
                    <td>
                        <div id="container">
                            <img width="320" height="220" id="cover" name="cover"
                                src="<#if (info.cover)??>${(info.cover)!''}<#else>${(ContextPath)!''}/img/blank.jpg</#if>">
                            <img style="display:none" width='320' height="220" id="loading" 
                                src="${(ContextPath)!''}/img/loading.jpg"/>
                            <button class="btn" id="upload">上传封面</button>
                        </div>
                        <div class="help-block">大图片建议尺寸 900x500</div>  
                    </td>
                </tr>
                <tr>
                    <th>摘要（选填）</th>
                    <td>
                        <textarea style="width: 444px;height: 110px;overflow: hidden;" name="summary" id="summary" maxlength="120">${(info.summary)!''}</textarea>
                        <div class="help-block">0-120个字符</div>  
                    </td>
                </tr>
                <tr>
                    <th>正文</th>
                    <td>
                        <script name="content" id="content" type="text/plain">${(info.content)!''}</script>
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
<script type="text/javascript" src="${(ContextPath)!''}/script/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="${(ContextPath)!''}/script/plupload/i18n/zh_CN.js"></script>
<script type="text/javascript" src="${(ContextPath)!''}/script/plupload/pictureUploader.js"></script>
<script type="text/javascript" src="${(ContextPath)!''}/script/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${(ContextPath)!''}/script/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="${(ContextPath)!''}/script/info/infoEditor.js"></script>
</body>
</html>