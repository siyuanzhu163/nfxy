<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="X-UA-Compatible" content="IE=8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>南方校园运营平台-资讯管理</title>
<#include "../include/css.ftl">
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="javascript:void(0)">管理资讯-${part!''}</a></li>
    <li><a href="${(ContextPath)!''}/manager/info/${part!''}/add"><i class="icon-plus"></i> 添加资讯-${part!''}</a></li>
</ul>
<div class="main">
<div class="search">
    <table class="table table-bordered tb">
        <tbody>
            <tr>
                <th style="width:130px">关键字(标题/作者)</th>
                <td>
                    <input class="span6" name="keyword" id="keyword" type="text" value="">
                </td>
            </tr>
             <tr class="search-submit">
                <td colspan="2"><button class="btn pull-right span2" id="search">搜索</button></td>
             </tr>
        </tbody>
    </table>
</div>
<div class="rule">
    <table class="table table-hover">
        <thead class="navbar-inner">
            <tr>
                <th>标题</th>
                <th>创建时间</th>
                <th>作者</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody id="infos">
        </tbody>
    </table>
    <textarea style="display:none" id="infoTemplate">
        {#foreach $T as item}
            <tr>
                <td>{$T.item.title}</td>
                <td>{$T.item.createTime}</td>
                <td>{$T.item.author}</td>
                <td>
                    <a href="${(ContextPath)!''}/manager/info/${part!''}/{$T.item.id}">编辑</a>
                    <a href="javascript:void(0)" onclick="infoManager.del({$T.item.id})">删除</a>
                </td>
            </tr>
        {#/for}
    </textarea>
    
    <div class='pagination pagination-right' id="infoPager">
    </div> 
    <textarea style="display:none" id="pagerTemplate">
        <ul>
            <li><a style='line-height:26px'>共{$T.totalCount}条记录</a></li>
            <li>
                <a style='line-height:26px'>
                    <select style='width:55px;height: 26px;color:#0088cc;' id='pageSize'>
                        {#foreach $T.pageSizeList as item}
                            <option value='{$T.item}' {#if $T.item == $T.pageSize}selected{#/if}>{$T.item}</option>
                        {#/for}
                    </select>
                </a>
            </li>
            <li><a style='line-height:26px' id='head' title='首页'>&lt;&lt;</a></li>
            <li><a style='line-height:26px' id='pre' title='上一页'>&lt;</a></li>
            <li>
                <a style='line-height:26px'>
                    <input style='width:30px;height:19px;padding: 0px 1px;color:#0088cc;text-align:center' id='pageNumber' value="{$T.pageNumber}"/> / {$T.pageCount}
                </a>
            </li>
            <li><a style='line-height:26px' id='next' title='下一页'>&gt;</a></li>
            <li><a style='line-height:26px' id='tail' title='尾页'>&gt;&gt;</a></li>
        </ul>
    </textarea>
</div>
</div>
<#include "../include/javascript.ftl">
<script type="text/javascript" src="${(ContextPath)!''}/script/jQuery/jtemplate/jquery-jtemplates.js"></script>
<script type="text/javascript" src="${(ContextPath)!''}/script/jQuery/jtemplate/jquery.jtemplate-ajaxlist.js"></script>
<script type="text/javascript">var part = "${part!''}";</script>
<script type="text/javascript" src="${(ContextPath)!''}/script/info/infoManager.js"></script>
<script type="text/javascript">
infoManager.init();
</script>
</body>
</html>