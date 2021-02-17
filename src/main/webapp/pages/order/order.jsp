<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>订单详情</title>
    <%@include file="../common/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">我的订单</span>
    <%@include file="../common/login_success_menu.jsp" %>
</div>

<div id="main">

    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>状态</td>
            <td>详情</td>
        </tr>
        <c:forEach var="order" items="${requestScope.orders}">
            <tr>
                <td>${order.createTime}</td>
                <td>${order.price}</td>
                <td>
                    <c:if test="${order.status==0}">
                        <span>未发货</span>
                    </c:if>
                    <c:if test="${order.status==1}">
                        <span>已发货</span>
                    </c:if>
                    <c:if test="${order.status==2}">
                        <span>已收货</span>
                    </c:if>
                </td>
                <td><a href="OrderServlet?action=orderDetail&orderId=${order.orderId}">查看详情</a></td>
            </tr>
        </c:forEach>
    </table>


</div>

<%@include file="../common/footer.jsp" %>
</body>
</html>
