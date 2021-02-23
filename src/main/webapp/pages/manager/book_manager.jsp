<%--suppress CheckTagEmptyBody --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>图书管理</title>
    <%@include file="../common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            //给用于删除的a标签绑定单击事件, 提示用户是否确认删除
            $("a.deleteClass").click(function () {
                /**
                 * confirm()是一个确认提示框,返回类型boolean
                 * return false → 阻止标签的默认行为 (对于a标签就是阻止跳转)
                 * */
                return confirm("你确定要删除《" + $(this).parent().parent().find("td:first").text() + "》吗?")
            })
        })
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
    <%@include file="../common/manager_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>

        <%--核心代码--%>
        <%--取出request域的books, 显示所有图书信息--%>
        <c:forEach items="${requestScope.page.items}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a href="${pageContext.request.contextPath}/${requestScope.page.url}?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a>
                </td>
                <td><a class="deleteClass"
                       href="${pageContext.request.contextPath}/${requestScope.page.url}?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
        </tr>
    </table>
    <br/>

    <%--引入分页栏--%>
    <%@include file="/pages/common/page_nav.jsp"%>
</div>

<%@include file="../common/footer.jsp" %>
</body>
</html>
