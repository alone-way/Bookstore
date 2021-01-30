<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>尚硅谷会员登录页面</title>
    <%@include file="../common/head.jsp" %>
    <script type="text/javascript">
        <%--$(function () {--%>
        <%--    $("#code_img").click(function () {--%>
        <%--        this.src = "${pageContext.request.contextPath}/kaptcha.jpg?t=" + Date.now()--%>
        <%--    })--%>
        <%--})--%>
    </script>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>尚硅谷会员</h1>
                    <a href="pages/user/regist.jsp">立即注册</a>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <!--设置错误提示-->
                    <span class="errorMsg">
<%--                        <%=request.getAttribute("msg") == null ? "请输入用户名和密码" : request.getAttribute("msg")%>--%>
                        ${empty requestScope.msg ? "请输入用户名和密码" : requestScope.msg}
                    </span>
                </div>
                <div class="form">
                    <!--设置action和method属性-->
                    <form action="UserServlet" method="post">
                        <%--添加隐藏域--%>
                        <input type="hidden" name="action" value="login"/>
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username"
                        <%--实现表单回显--%>
                        <%--                               value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"--%>
                               value="${requestScope.username}" <%--EL表达式会将null以空串输出--%>
                        />
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password"/>
                        <br/>
                        <br/>
                        <%--                        <label>验证码：</label>--%>
                        <%--                        <input class="itxt" type="text" name="code" style="width: 150px;" id="code"/>--%>
                        <%--                        <img id="code_img" alt="验证码" src="${pageContext.request.contextPath}/kaptcha.jpg"--%>
                        <%--                             style="float: right;--%>
                        <%--                        margin-right:--%>
                        <%--                        40px">--%>
                        <input type="submit" value="登录" id="sub_btn"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
