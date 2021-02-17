<%--
  User: OneIce
  Date: 2020/11/30
  Time: 19:05
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
    <a href="OrderServlet?action=myOrders">我的订单</a>
    <a href="UserServlet?action=logout">注销</a>&nbsp;&nbsp;
    <a href="javaScript:history.back()">返回</a>
</div>
