<%--
  User: OneIce
  Date: 2020/12/14
  Time: 20:40
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">
    //实现"到第几页"
    $(function () {
        $('#searchPageButton').click(function () {
            let $pageNo = $('#pn_input').val() //获得指定的页码
            if ($pageNo <= 0 || $pageNo > ${requestScope.page.pageTotal}) {
                $pageNo = 1
            }
            //设置window.location对象的href属性
            location.href = "${pageContext.request.contextPath}/${requestScope.page.url}&pageNo=" + $pageNo + "&pageSize=${requestScope.page.pageSize}"
        })
    })
</script>

<%--分页栏--%>
<div id="page_nav">
    <a href="${pageContext.request.contextPath}/${requestScope.page.url}&pageNo=1&pageSize=${requestScope.page.pageSize}">首页</a>
    <%--页码大于1才显示"上一页"--%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${pageContext.request.contextPath}/${requestScope.page.url}&pageNo=${requestScope.page.pageNo - 1}&pageSize=${requestScope.page.pageSize}">上一页</a>
    </c:if>

    <%--开始输出页码--%>
    <%-- 首先获取页码范围, 保存在pageScope中 --%>
    <c:choose>
        <%-- 情况1: 总页数≤5, 页码范围: [1, 总页数] --%>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:set scope="page" var="begin" value="1"></c:set>
            <c:set scope="page" var="end" value="${requestScope.page.pageTotal}"></c:set>
        </c:when>
        <%-- 情况2：总页码>5 --%>
        <c:otherwise>
            <c:choose>
                <%-- 当前页码是前2页, 页码范围：[1, 5] --%>
                <c:when test="${requestScope.page.pageNo <=2}">
                    <c:set scope="page" var="begin" value="1"></c:set>
                    <c:set scope="page" var="end" value="5"></c:set>
                </c:when>
                <%-- 当前页码是后2页，页码范围：[总页数-5+1, 总页数] --%>
                <c:when test="${requestScope.page.pageNo >= requestScope.page.pageTotal - 1}">
                    <c:set scope="page" var="begin" value="${requestScope.page.pageTotal - 4}"></c:set>
                    <c:set scope="page" var="end" value="${requestScope.page.pageTotal}"></c:set>
                </c:when>
                <%-- 当前页码是中间页，页码范围：[当前页码-2, 当前页码+2] --%>
                <c:otherwise>
                    <c:set scope="page" var="begin" value="${requestScope.page.pageNo - 2}"></c:set>
                    <c:set scope="page" var="end" value="${requestScope.page.pageNo + 2}"></c:set>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
    <%-- 从pageContext中取出页码范围, 输出页码 --%>
    <c:forEach var="i" begin="${pageScope.begin}" end="${pageScope.end}">
        <%-- 当前页码不可跳转 --%>
        <c:if test="${requestScope.page.pageNo == i}">
            【${i}】
        </c:if>
        <%-- 其他页码可以跳转 --%>
        <c:if test="${requestScope.page.pageNo != i}">
            <a href="${pageContext.request.contextPath}/${requestScope.page.url}&pageNo=${i}&pageSize=${requestScope.page.pageSize}">${i}</a>
        </c:if>
        <%=' '%>
    </c:forEach>
    <%--结束输出页码--%>

    <%--页码小于总页数才显示"下一页"--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${pageContext.request.contextPath}/${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}&pageSize=${requestScope.page.pageSize}">下一页</a>
    </c:if>
    <a href="${pageContext.request.contextPath}/${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}&pageSize=${requestScope.page.pageSize}">末页</a>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    <%--"到第几页", 通过JQuery绑定单击事件来实现--%>
    到第<input name="pn" value="${requestScope.page.pageNo}" id="pn_input"/>页
    <input type="button" id="searchPageButton" value="确定">
</div>
