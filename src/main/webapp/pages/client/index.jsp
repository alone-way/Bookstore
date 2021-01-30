<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>书城首页</title>
    <%@include file="../common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            /* 将图书加入购物车 */
            $(".add_cart").click(function () {
                var bookId = $(this).attr(`bookId`)
                $.getJSON("http://localhost:8080/book/CartServlet", "action=ajaxAddItem&bookId=" + bookId, function (data) {
                    $("#cartTotalCount").text(data.totalCount)
                    $("#cartLastName").text(data.lastItemName)
                })
            })
        })
    </script>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">网上书城</span>
    <div>
        <%--如果用户未登录, 显示登录和注册菜单--%>
        <c:if test="${empty sessionScope.user}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        </c:if>
        <%--如果用户已登录, 显示购物车, 后台管理等菜单--%>
        <c:if test="${not empty sessionScope.user}">
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
            <a href="${pageContext.request.contextPath}/UserServlet?action=logout">注销</a>&nbsp;&nbsp;
            <a href="pages/cart/cart.jsp">购物车</a>
            <a href="${pageContext.request.contextPath}/pages/order/order.jsp">我的订单</a>
            <a href="pages/manager/manager.jsp">后台管理</a>
        </c:if>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/BookServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice"/>
                <%--pageNo必须等于1 (不能写成${requestScope.page.pageNo}, 否则一旦重新点击搜索的话, 跳转到的并不是第一页) --%>
                <input type="hidden" name="pageNo" value="1"/>
                <input type="hidden" name="pageSize" value="${requestScope.page.pageSize}"/>
                <%--min和max要等于用户上一次搜索时的价格区间, 否则切页时min,max会消失--%>
                价格：<input id="min" type="text" name="min"
                          value="${param.min!=0&&param.max!=2147483647? param.min: ""}"> 元 -
                <input id="max" type="text" name="max" value="${param.min!=0&&param.max!=2147483647? param.max: ""}"> 元
                <input type="submit" value="查询"/>
            </form>
        </div>

        <div style="text-align: center">
            <%--购物车有商品才显示--%>
            <c:if test="${sessionScope.cart.totalCount > 0}">
                您的购物车中有<span id="cartTotalCount">${sessionScope.cart.totalCount}</span>件商品
                <div>
                    您刚刚将<span id="cartLastName"  style="color: red">${sessionScope.lastItemName}</span>加入到了购物车中
                </div>
            </c:if>
        </div>

        <%--遍历输出图书列表--%>
        <c:forEach var="book" items="${requestScope.page.items}">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${pageContext.request.contextPath}/${book.imgPath}"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <button bookId="${book.id}" class="add_cart">加入购物车</button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <%--引入分页栏--%>
    <%@include file="/pages/common/page_nav.jsp" %>
</div>

<%@include file="../common/footer.jsp" %>
</body>
</html>
