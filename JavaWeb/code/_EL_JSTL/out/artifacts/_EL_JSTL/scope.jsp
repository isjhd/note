<%--
  Created by IntelliJ IDEA.
  User: 34782
  Date: 2023/10/10
  Time: 8:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%
        pageContext.setAttribute("key2", "pageContext");
        request.setAttribute("key2", "request");
        session.setAttribute("key2", "session");
        application.setAttribute("key2", "application");
    %>

    ${pageScope.key2}
    ${requestScope.key2}
    ${sessionScope.key2}
    ${applicationScope.key2}

</body>
</html>
