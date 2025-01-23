<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: 34782
  Date: 2023/10/5
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--  代码脚本----if 语句--%>
    <%
        int i = 13;
        if (i == 12) {
    %>
            <h1>星际牛仔</h1>
    <%
        } else {
    %>
            <h1>轻音少女</h1>
    <%
        }
    %>

<%--  代码脚本----for 循环语句--%>
    <table border="1" cellspacing="0">
    <%
        for (int j = 0; j < 10; j++) {
    %>
            <tr>
                <td>第 <%=j + 1%>行</td>
            </tr>
    <%
        }
    %>
    </table>

<%-- 翻译后 java 文件中_jspService 方法内的代码都可以写--%>
    <%
        String username = request.getParameter("username");
        System.out.println("用户名的请求参数值是：" + username);
    %>


</body>
</html>
