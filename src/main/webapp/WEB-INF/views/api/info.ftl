<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
  <meta content="yes"name="apple-mobile-web-app-capable"/>
  <meta content="black"name="apple-mobile-web-app-status-bar-style"/>
  <meta name="format-detection"content="telphone=no"/>
  <title>${(info.title)!''}</title>
  <link rel="stylesheet" href="${(ContextPath)!''}/api/css/reset.css">
  <link rel="stylesheet" href="${(ContextPath)!''}/api/css/word.css">
  <link rel="stylesheet" href="${(ContextPath)!''}/api/css/article.css">
</head>
<body>
  <div class="wrap-page">
    <img class="article_banner" src="${(info.cover)!''}" alt="封面">
    <div class="page">
      <h1 class="article_header" style="text-transform:Capitalize">${(info.title)!''}<small class="article_time">${part!''} ${(info.createTime?string("yyyy-MM-dd HH:mm"))!''}</small></h1>
      <div class="entry">
        ${(info.content)!''}
      </div>
    </div>
  </div>
</body>
</html>
