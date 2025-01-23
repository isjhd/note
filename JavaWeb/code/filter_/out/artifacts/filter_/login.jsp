<%--
  Created by IntelliJ IDEA.
  User: 34782
  Date: 2023/10/26
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    这是登陆页面 login.jsp<br>
    <form action="http://localhost:8080/filter_/loginServlet" method="get">
        用户名：<input type="text" name="username"/> <br>
        密  码：<input type="password" name="password"/> <br>
        <input type="submit"/>
    </form>

</body>
</html>
