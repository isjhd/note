package com.atguigu.json;

import com.atguigu.pojo.Person;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class JsonTest {
    // javaBean 和 json 的互转
    @Test
    public void test1() {
        Person person = new Person(1, "轻音少女");
        // 创建 Gson 对象实例
        Gson gson = new Gson();
        // toJson 方法可以把java对象转换成为 json 字符串
        String personJsonString = gson.toJson(person);
        System.out.println(personJsonString);
        // fromJson 把 json 字符串转换回 Java 对象
        // 第一个参数是 json 字符串
        // 第二个参数是转换回去的 Java 对象类
        Person person1 = gson.fromJson(personJsonString, Person.class);
        System.out.println(person1);
    }

    // List 和 json 的互转
    @Test
    public void test2() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "轻音少女"));
        personList.add(new Person(2, "星际牛仔"));

        Gson gson = new Gson();

        // 把 list 转换为 json 字符串
        String personListJsonString = gson.toJson(personList);
        System.out.println(personListJsonString);

        // 把 json 字符串 转换为 list 集合
        List<Person> list = gson.fromJson(personListJsonString, new PersonListType().getType());
        System.out.println(list);
        Person person = list.get(0);
        System.out.println(person);

    }

    // map 和 json 的互转
    @Test
    public void test3() {
        Map<Integer,Person> personMap = new HashMap<>();
        personMap.put(1, new Person(1, "无间道"));
        personMap.put(2, new Person(2, "一一"));

        Gson gson = new Gson();
        //  把 map 集合转换成为 json 字符串
        String personMapJsonString = gson.toJson(personMap);
        System.out.println(personMapJsonString);

        // Map<Integer,Person> personMap2 = gson.fromJson(personMapJsonString, new PersonMapType().getType());
        // 使用匿名内部类
        Map<Integer,Person> personMap2 = gson.fromJson(personMapJsonString, new TypeToken<HashMap<Integer,Person>>(){}.getType());

        System.out.println(personMap2);
        Person p = personMap2.get(1);
        System.out.println(p);

    }

}
