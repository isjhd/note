<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: 34782
  Date: 2023/10/9
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("a.a.a", "星际牛仔");
        map.put("b+b+b", "团子大家族");
        map.put("c-c-c", "轻音少女");

        request.setAttribute("map", map);
    %>

    ${ map['a.a.a'] } <br/>
    ${ map['b+b+b'] } <br/>
    ${ map['c-c-c'] } <br/>

</body>
</html>
