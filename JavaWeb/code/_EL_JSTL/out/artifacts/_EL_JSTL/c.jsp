<%@ page import="com.atguigu.pojo.Person" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: 34782
  Date: 2023/10/9
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%
        Person person = new Person();
        person.setName("星际牛仔");
        person.setPhones(new String[]{"123456", "654321", "098765"});

        List<String> cities = new ArrayList<String>();
        cities.add("木星1");
        cities.add("木星2");
        cities.add("木星3");
        cities.add("木星4");
        person.setCities(cities);

        Map<String,Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        person.setMap(map);

        pageContext.setAttribute("p",person);
    %>

    输出Person：${ p }<br/>
    输出Person的name属性：${p.name}<br/>
    输出Person的phones数组属性值：${p.phones[1]}<br/>
    输出Person的cities集合中的元素值：${p.cities}<br/>
    输出Person的List集合中个别元素值：${p.cities[1]}<br/>
    输出Person的Map集合：${p.map}<br/>
    输出Person的Map集合中某个key的值：${p.map.key1}<br/>


</body>
</html>
