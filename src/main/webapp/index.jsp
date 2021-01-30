<%@ page contentType="text/html;charset=UTF-8" %>
<%--主页只负责请求转发--%>
<%--<%request.getRequestDispatcher("/client/BookServlet?action=page").forward(request, response);%>--%>
<jsp:forward page="/client/BookServlet?action=page"></jsp:forward>