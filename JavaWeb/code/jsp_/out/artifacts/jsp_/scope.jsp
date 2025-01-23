
<%--
  Created by IntelliJ IDEA.
  User: 34782
  Date: 2023/10/7
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <h1>scope.jsp 页面</h1>
    <%
        // 往4个域中分别保存了数据
        pageContext.setAttribute("key", "pageContext");
        request.setAttribute("key", "request");
        session.setAttribute("key", "session");
        application.setAttribute("key", "application");
    %>
    pageContext 域是否有值：<%=pageContext.getAttribute("key")%> <br>
    request 域是否有值：<%=request.getAttribute("key")%> <br>
    session 域是否有值：<%=session.getAttribute("key")%> <br>
    application 域是否有值：<%=application.getAttribute("key")%> <br>
<%--    <%--%>
<%--        request.getRequestDispatcher("/scope2.jsp").forward(request, response);--%>
<%--    %>--%>
    <jsp:forward page="/scope2.jsp"></jsp:forward>

</body>
</html>
