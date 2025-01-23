<%--
  Created by IntelliJ IDEA.
  User: 34782
  Date: 2023/10/8
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    猫娘头部<br>
    猫娘身体<br>

<%--
    <jsp:include page=""></jsp:include>     这是动态包含
    page 属性是指定你要包含的jsp页面的路径
    动态包含也可以像静态包含一样。把被包含的内容执行输出到包含位置

    动态包含的特点：
        1. 动态包含会把包含的jsp页面也翻译成为java代码
        2. 动态包含底层代码使用如下代码去调用被包含的jsp页面执行输出。
            org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/include/footer.jsp", out, false);
        3. 动态包含，还可以传递参数
--%>
    <jsp:include page="/include/footer.jsp">
        <jsp:param name="username" value="bbj"/>
        <jsp:param name="password" value="root"/>
    </jsp:include>


</body>
</html>
