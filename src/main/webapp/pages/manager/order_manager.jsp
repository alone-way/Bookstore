<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>订单管理</title>
    <%@include file="../common/head.jsp" %>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">订单管理系统</span>
    <%@include file="../common/manager_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>详情</td>
            <td>发货</td>
        </tr>
        <c:forEach var="order" items="${requestScope.orders}">
            <tr>
                <td>${order.createTime}</td>
                <td>${order.price}</td>
                <td><a href="OrderServlet?action=orderDetail&orderId=${order.orderId}">查看详情</a></td>
                <td>
                    <c:if test="${order.status==0}">
                        <a href="manager/OrderServlet?action=sendOrder&orderId=${order.orderId}">点击发货</a>
                    </c:if>
                    <c:if test="${order.status==1}">
                        <span>已发货</span>
                    </c:if>
                    <c:if test="${order.status==2}">
                        <span>已收货</span>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@include file="../common/footer.jsp" %>
</body>
</html>
