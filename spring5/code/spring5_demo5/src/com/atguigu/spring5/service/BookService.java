package com.atguigu.spring5.service;

import com.atguigu.spring5.dao.BookDao;
import com.atguigu.spring5.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* @author  i-s-j-h-d
 * @version 1.0 */
@Service
public class BookService {
    // 注入dao
    @Autowired
    private BookDao bookDao;

    // 添加的方法
    public void addBook(Book book) {
        bookDao.add(book);
    }

    // 修改的方法
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    // 删除的方法
    public void deleteBook(String id) {
        bookDao.deleteBook(id);
    }

    // 查询表记录数
    public int findCount() {
        return bookDao.selectCount();
    }

    // 查询表记录数
    public Book findOne(int id) {
        return bookDao.findBookInfo(id);
    }

    // 查询返回集合
    public List<Book> findAll() {
        return bookDao.findAllBook();
    }

    // 批量添加
    public void batchAdd(List<Object[]> batchArgs) {
        bookDao.batchAddBook(batchArgs);
    }

    // 批量修改
    public void batchUpdate(List<Object[]> batchArgs) {
        bookDao.batchUpdateBook(batchArgs);
    }

    // 批量删除
    public void batchDelete(List<Object[]> batchArgs) {
        bookDao.batchDeleteBook(batchArgs);
    }

}
