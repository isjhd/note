package com.atguigu.spring5.dao;

/* @author  i-s-j-h-d
 * @version 1.0 */

import com.atguigu.spring5.entity.Book;

import java.util.List;

public interface BookDao {

    // 添加的方法
    void add(Book book);

    // 修改的方法
    void updateBook(Book book);

    // 删除的方法
    void deleteBook(String id);

    // 查询表记录数
    int selectCount();

    Book findBookInfo(int id);

    List<Book> findAllBook();

    void batchAddBook(List<Object[]> batchArgs);

    void batchUpdateBook(List<Object[]> batchArgs);

    void batchDeleteBook(List<Object[]> batchArgs);

}
