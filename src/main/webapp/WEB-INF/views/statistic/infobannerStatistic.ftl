<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="X-UA-Compatible" content="IE=8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>南方校园运营平台-数据统计</title>
<#include "../include/css.ftl">
</head>
<body>
<ul class="nav nav-tabs" id="parts">
    <#list parts as part>
        <#if part != 'ALL'>
            <li <#if part == 'NEW'>class="active"</#if> part="${(part?lower_case)!''}"><a href="javascript:void(0)">${part!''}</a></li>
        </#if>
    </#list>
</ul>
<div class="main">
<form class="form-horizontal form">
<h4>轮播图</h4>
<div class="rule">
    <table class="table table-hover">
        <thead class="navbar-inner">
            <tr>
                <th>标题</th>
                <th>创建时间</th>
                <th>阅读量</th>
                <th>分享量</th>
            </tr>
        </thead>
        <tbody id="banners">
        </tbody>
    </table>
    <textarea style="display:none" id="bannerTemplate">
        {#foreach $T as item}
            <tr>
                <td>{$T.item.title}</td>
                <td>{$T.item.createTime}</td>
                <td>{$T.item.viewedCount}</td>
                <td>{$T.item.sharedCount}</td>
            </tr>
        {#/for}
    </textarea>
    
    <div class='pagination pagination-right' id="bannerPager">
    </div> 
</div>

<h4>资讯</h4>
<div class="rule">
    <table class="table table-hover">
        <thead class="navbar-inner">
            <tr>
                <th>标题</th>
                <th>创建时间</th>
                <th>阅读量</th>
                <th>分享量</th>
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
                <td>{$T.item.viewedCount}</td>
                <td>{$T.item.sharedCount}</td>
            </tr>
        {#/for}
    </textarea>
    
    <div class='pagination pagination-right' id="infoPager">
    </div> 
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
</form>
</div>
<#include "../include/javascript.ftl">
<script type="text/javascript" src="${(ContextPath)!''}/script/jQuery/jtemplate/jquery-jtemplates.js"></script>
<script type="text/javascript" src="${(ContextPath)!''}/script/jQuery/jtemplate/jquery.jtemplate-ajaxlist.js"></script>
<script type="text/javascript" src="${(ContextPath)!''}/script/statistic/infobanner/infobannerStatistic.js"></script>
<script type="text/javascript">
infobannerStatistic.init();
</script>
</body>
</html>