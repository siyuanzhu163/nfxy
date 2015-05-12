<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="X-UA-Compatible" content="IE=8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>南方校园运营平台-轮播图编辑</title>
<#include "../include/css.ftl">
<style>
.tt-dropdown-menu {
    min-width: 160px;
    margin-top: 2px;
    padding: 5px 0;
    background-color: #fff;
    border: 1px solid #ccc;
    border: 1px solid rgba(0,0,0,.2);
    *border-right-width: 2px;
    *border-bottom-width: 2px;
    -webkit-border-radius: 6px;
    -moz-border-radius: 6px;
    border-radius: 6px;
    -webkit-box-shadow: 0 5px 10px rgba(0,0,0,.2);
    -moz-box-shadow: 0 5px 10px rgba(0,0,0,.2);
    box-shadow: 0 5px 10px rgba(0,0,0,.2);
    -webkit-background-clip: padding-box;
    -moz-background-clip: padding;
    background-clip: padding-box;
}
 
.tt-suggestion {
    display: block;
    padding: 3px 20px;
}
 
.tt-suggestion.tt-is-under-cursor {
    color: #fff;
    background-color: #0081c2;
    background-image: -moz-linear-gradient(top, #0088cc, #0077b3);
    background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#0088cc), to(#0077b3));
    background-image: -webkit-linear-gradient(top, #0088cc, #0077b3);
    background-image: -o-linear-gradient(top, #0088cc, #0077b3);
    background-image: linear-gradient(to bottom, #0088cc, #0077b3);
    background-repeat: repeat-x;
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff0088cc', endColorstr='#ff0077b3', GradientType=0)
}
 
.tt-suggestion.tt-is-under-cursor a {
    color: #fff;
}
 
.tt-suggestion p {
    margin: 0;
}
</style>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${(ContextPath)!''}/manager/banner">管理轮播图</a></li>
    <li class="active"><a href="javascript:void(0)"><i class="icon-plus"></i><#if info??>编辑<#else>添加</#if>轮播图</a></li>
</ul>
<div class="main">
    <form class="form-horizontal form" id="banner" onsubmit="return false;">
        <h4>轮播图</h4>
        <table class="tb">
            <tbody>
                <tr>
                    <th style="width:150px">版块</th>
                    <td>
                        <input type="hidden" id="id" value="${(banner.id?c)!''}"/>
                        <select id="part">
                            <#list parts as part>
                            <option value="${part!''}" <#if banner?? && banner.part == part>selected</#if>>${(part)!''}</option>
                            </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>标题</th>
                    <td>
                        <input type="text" name="title" id="title" class="span6" value="${(banner.title)!''}" maxlength="64" required>
                        <div class="help-block">0-64个字符</div>   
                    </td>
                </tr>
                <tr>
                    <th>轮播图</th>
                    <td>
                        <div id="container">
                            <img width="320" height="220" id="picture" name="picture"
                                src="<#if (banner.picture)??>${(banner.picture)!''}<#else>${(ContextPath)!''}/img/blank.jpg</#if>">
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
                        <textarea style="width: 444px;height: 110px;overflow: hidden;" name="summary" id="summary" maxlength="120">${(banner.summary)!''}</textarea>
                        <div class="help-block">0-120个字符</div>  
                    </td>
                </tr>
                <tr>
                    <th>关联资讯（选填）</th>
                    <td>
                        <input type="hidden" name="infoId" id="infoId" class="span6" value="${(banner.info.id?c)!''}">
                        <input type="text" name="infoTitle" id="infoTitle" class="span6" value="${(banner.info.title)!''}">
                        <div class="help-block">(输入关键词检索对应的资讯进行选择)</div>
                    </td>
                </tr>
                <tr>
                    <th>外部链接（选填）</th>
                    <td>
                        <input type="text" name="url" id="url" class="span6" value="${(banner.url)!''}" url="true" maxlength="256">
                        <div class="help-block">(输入外部链接的URL地址)</div>
                    </td>
                </tr>
                <tr>
                    <th>权重</th>
                    <td>
                        <input type="text" name="weight" id="weight" class="span6" value="${(banner.weight?c)!50}" 
                            maxlength="3" digits="true" range="[0,100]" required>
                        <div class="help-block">(0-100,默认为50,权重大的优先显示)</div>  
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
<script type="text/javascript" src="${(ContextPath)!''}/script/jQuery/typeahead/typeahead.jquery.js"></script>
<script type="text/javascript" src="${(ContextPath)!''}/script/banner/bannerEditor.js"></script>
</body>
</html>