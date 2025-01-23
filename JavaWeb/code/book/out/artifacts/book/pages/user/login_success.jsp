<%--
Created by IntelliJ IDEA.
User: 34782
Date: 2023/9/27
Time: 14:23
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <!--写base标签，永远固定相对路径跳转的结果-->
    <base href="http://localhost:8080/book/">


    <link type="text/css" rel="stylesheet" href="static/css/style.css" >
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color:red;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >

    <%-- 静态包含，登录成功之后的菜单 --%>
    <%@include file="/pages/common/login_sucess_menu.jsp"%>

</div>

<div id="main">

    <h1>欢迎回来 <a href="../../index.jsp">转到主页</a></h1>

</div>


<%-- 静态包含页脚内容 --%>
<%@include file="/pages/common/footer.jsp"%>

</body>
</html>