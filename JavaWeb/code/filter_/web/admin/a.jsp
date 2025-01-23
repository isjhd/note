<%--
  Created by IntelliJ IDEA.
  User: 34782
  Date: 2023/10/26
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        Object user = session.getAttribute("user");
        // 如果等于null,说明还没登录
        if (user == null) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
    %>
    我是a.jsp页面
</body>
</html>
