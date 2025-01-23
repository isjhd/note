<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
Created by IntelliJ IDEA.
User: 34782
Date: 2023/9/27
Time: 14:23
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>

    <%-- 静态包含 base标签，css样式，jQuery文件 --%>
    <%@include file="/pages/common/head.jsp"%>

</head>
<body>

很抱歉，你访问的页面不存在<br>
<a href="index.jsp">返回首页</a>

<%-- 静态包含页脚内容 --%>
<%@include file="/pages/common/footer.jsp"%>

</body>
</html>