<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>购物车</title>
    <%@include file="../common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("a.deleteItem").click(function () {
                    var name = $(this).parent().parent().find(":first").text()
                    return confirm("你确定要删除【" + name + "】吗?")
                }
            )
            $("#clearCart").click(function () {
                return confirm("你确定要清空购物车吗?")
            })
            $(".updateCount").change(function () {
                var count = this.value
                var itemId = $(this).attr("itemId")
                var name = $(this).parent().parent().find(":first").text()
                if (confirm("你确定要将【" + name + "】修改成【" + count + "】吗？")) {
                    location.href = "${pageContext.request.contextPath}/CartServlet?action=updateCount&itemId=" +
                        itemId + "&count=" + count
                } else {
                    //defaultValue表示标签对象的初始value值
                    this.value = this.defaultValue
                }
            })
        })
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>
    <%@include file="../common/login_success_menu.jsp" %>
</div>

<div id="main">
    <%--购物车为空--%>
    <c:if test="${empty sessionScope.cart.items}">
        <table>
            <tr>
                <td>商品名称</td>
                <td>数量</td>
                <td>单价</td>
                <td>总价</td>
                <td>操作</td>
            </tr>
            <tr>
                <th colspan="5"><a href="index.jsp">亲, 当前购物车为空, 快去添加点商品吧!</a></th>
            </tr>
        </table>
    </c:if>

    <%--购物车非空--%>
    <c:if test="${not empty sessionScope.cart.items}">
        <table>
            <tr>
                <td>商品名称</td>
                <td>数量</td>
                <td>单价</td>
                <td>总价</td>
                <td>操作</td>
            </tr>
            <c:forEach var="entry" items="${sessionScope.cart.items}">
                <tr>
                    <td>${entry.value.name}</td>
                    <td><input class="updateCount" itemId="${entry.value.id}" type="text" style="width:50px"
                               value="${entry.value.count}"></td>
                    <td>${entry.value.price}</td>
                    <td>${entry.value.totalPrice}</td>
                    <td><a class="deleteItem"
                           href="${pageContext.request.contextPath}/CartServlet?action=deleteItem&itemId=${entry.value.id}">删除
                    </a></td>
                </tr>
            </c:forEach>
        </table>

        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span
                    class="cart_span"><a id="clearCart"
                                         href="${pageContext.request.contextPath}/CartServlet?action=clear">清空购物车</a></span>
            <span class="cart_span"><a href="${pageContext.request.contextPath}/OrderServlet?action=checkout">去结账</a></span>
        </div>
    </c:if>

</div>

<%@include file="../common/footer.jsp" %>
</body>
</html>
