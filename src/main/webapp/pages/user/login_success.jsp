<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>尚硅谷会员注册页面</title>
<%--    静态包含base标签,meta标签,css样式, jQuery文件--%>
    <%@include file="../common/head.jsp"%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <%--    静态包含div标签--%>
    <%@include file="../common/login_success_menu.jsp" %>
</div>

<div id="main">

    <h1>欢迎回来 <a href="index.jsp">转到主页</a></h1>

</div>
<%--静态包含页脚信息--%>
<%@include file="../common/footer.jsp"%>
</body>
</html>
