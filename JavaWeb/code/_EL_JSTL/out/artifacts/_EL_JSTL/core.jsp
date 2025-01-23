<%--
  Created by IntelliJ IDEA.
  User: 34782
  Date: 2023/10/10
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%--
        <c:choose> <c:when> <c:otherwise>标签
        作用：多路判断。跟 switch ... case .... default 非常接近
        choose 标签开始选择判断
        when 标签表示每一种判断情况
        test 属性表示当前这种判断情况的值
        otherwise 标签表示剩下的情况

        <c:choose> <c:when> <c:otherwise>标签使用时需要注意的点：
            1、标签里不能使用 html 注释，要使用 jsp 注释
            2、when 标签的父标签一定要是 choose 标签
    --%>

    <%
        request.setAttribute("星际牛仔", 1998);
    %>

    <c:choose>
        <%-- 标签里不能使用 html 注释，要使用 jsp 注释 --%>
        <c:when test="${ requestScope.星际牛仔 > 2010 }">
            <h2>比较老</h2>
        </c:when>
        <c:when test="${ requestScope.星际牛仔 > 2000 }">
            <h2>很老</h2>
        </c:when>
        <c:when test="${ requestScope.星际牛仔 > 1990 }">
            <h2>泡沫时代</h2>
        </c:when>
        <c:otherwise>
            <h2>更老</h2>
        </c:otherwise>
    </c:choose>

</body>
</html>
