package com.hspedu.homework;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */
import java.util.*;

public class Homework01 {
    public static void main(String[] args) {

        DAO<User> dao = new DAO<>();
        dao.save("0001", new User("0001", 18, "唯"));
        System.out.println(dao.get("0001"));
        dao.update("0001", new User("0002", 17, "优"));
        System.out.println(dao.list());
        dao.delete("0001");
    }
}
class DAO<T> {
    Map<String, T> map = new HashMap<>();

    public void save(String id, T entity) {
        map.put(id, entity);
    }

    public T get(String id) {
        return map.get(id);
    }

    public void update(String id, T entity) {
        map.put(id, entity);
    }

    public List<T> list() {
        List<T> list = new ArrayList<>();
        Collection<T> values = map.values();
        for(T value: values){
            list.add(value);
        }
        return list;
    }

    public void delete(String id) {
        map.remove(id);
    }

    @Override
    public String toString() {
        return map.toString();
    }
}

class User {
    private String id;
    private int age;
    private String name;

    public User(String id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }


}