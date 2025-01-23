<%--
  Created by IntelliJ IDEA.
  User: 34782
  Date: 2023/10/8
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--练习一：在 jsp 页面中输出九九乘法口诀表--%>
    <h1 align="center">九九乘法表</h1>
    <table align="center">
    <% for (int i = 1; i <= 9; i++) { %>
        <tr>
        <% for (int j = 1; j <= i; j++) { %>
            <td><%=j + "x" + i + "=" + (i*j)%></td>
        <% } %>
        </tr>
      <% } %>
    </table>

</body>
</html>
