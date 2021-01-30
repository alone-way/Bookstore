<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh_cn">
<head>
    <title>尚硅谷会员注册页面</title>
    <%@include file="../common/head.jsp" %>


    <script type="text/javascript">
        //页面加载完后
        $(function () {
            //为验证码图片绑定单击事件
            $("#code_img").click(function () {
                //修改img的src属性, 让浏览器重新请求图片
                //同时加个时间戳参数, 避免浏览器加载缓存
                this.src = "${pageContext.request.contextPath}/kaptcha.jpg?t=" + Date.now()
            })
            //实现用户名的Ajax验证
            $("#username").change(function () {
                var param = "action=ajaxExistingUsername&" + $(this).serialize()
                $.getJSON("http://localhost:8080/book/UserServlet", param, function (data) {
                    if (data.existingUsername) {
                        $(".errorMsg").text("用户名已存在!")
                    }else{
                        $(".errorMsg").empty()
                    }
                })
            })
            //为注册按钮绑定单击事件
            $("#sub_btn").click(
                function () {
                    // 验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
                    let username = $("#username").val();
                    let usernamePat = /^\w{5,12}$/;
                    if (!usernamePat.test(username)) {
                        $("span.errorMsg").text("用户名不合法");
                        return false;
                    }
                    // 验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位验证确认密码：和密码相同
                    let password = $("#password").val();
                    let repwd = $("#repwd").val();
                    let passwordPat = /^\w{5,12}$/;
                    if (password !== repwd || !passwordPat.test(password)) {
                        $("span.errorMsg").text("密码不合法");
                        return false;
                    }
                    // 邮箱验证：xxxxx@xxx.com
                    let email = $("#email").val();
                    let emailPat = /^([\w-])+@([\w-])+(.[\w-])+/;
                    if (!emailPat.test(email)) {
                        $("span.errorMsg").text("邮箱不合法");
                        return false;
                    }
                    // 验证码：现在只需要验证用户已输入。因为还没讲到服务器验证码生成。
                    let code = $("#code").val().trim();
                    if (code === "") {
                        $("span.errorMsg").text("验证码不合法");
                        return false;
                    }
                    //验证通过, 清空errorMsg, 返回true
                    $("span.errorMsg").text("");
                    return true;
                }
            )
        })


    </script>

    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }
    </style>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <%--回显错误信息--%>
                    <span class="errorMsg"><%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg")%></span>
                    ${requestScope.msg}
                </div>
                <div class="form">
                    <!--设置action和method属性-->
                    <form action="UserServlet" method="post">
                        <%--添加隐藏域--%>
                        <input type="hidden" name="action" value="regist">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" id="username"
                        <%--回显用户名--%>
                        <%--                               value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"--%>
                               value="${requestScope.username}"
                        />
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                               name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
                               name="email" id="email"
                        <%--回显邮箱--%>
                        <%--                               value="<%=request.getAttribute("email")==null?"":request.getAttribute("email")%>"--%>
                               value="${requestScope.email}"
                        />
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" name="code" style="width: 150px;" id="code"/>
                        <img id="code_img" alt="验证码" src="${pageContext.request.contextPath}/kaptcha.jpg" style="float:
                        right;
                        margin-right:
                        40px">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
