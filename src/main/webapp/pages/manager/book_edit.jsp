<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <title>编辑图书</title>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }

        input {
            text-align: center;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">编辑图书</span>
    <%@include file="../common/manager_menu.jsp" %>
</div>

<div id="main">
    <%--提交到BookServlet--%>
    <form action="${pageContext.request.contextPath}/manager/BookServlet" method="post">
        <%--添加或修改完图书后, 要显示的页码--%>
        <input type="hidden" name="pageNo" value="${param.pageNo}"/>
        <%--通过判断请求中是否携带id参数, 来决定BookServlet要调用add()还是update()方法--%>
        <input type="hidden" name="action" value="${empty param.id?"add":"update"}"/>
        <%--用于修改图书时, 需要携带的图书id--%>
        <input type="hidden" name="id" value="${requestScope.book.id}"/>

        <table>
            <tr>
                <td>名称</td>
                <td>价格</td>
                <td>作者</td>
                <td>销量</td>
                <td>库存</td>
                <td colspan="2">操作</td>
            </tr>
            <tr>
                <td><input name="name" type="text" value="${requestScope.book.name}"/></td>
                <td><input name="price" type="text" value="${requestScope.book.price}"/></td>
                <td><input name="author" type="text" value="${requestScope.book.author}"/></td>
                <td><input name="sales" type="text" value="${requestScope.book.sales}"/></td>
                <td><input name="stock" type="text" value="${requestScope.book.stock}"/></td>
                <td><input type="submit" value="提交"/></td>
            </tr>
        </table>
    </form>


</div>

<%@include file="../common/footer.jsp" %>
</body>
</html>
