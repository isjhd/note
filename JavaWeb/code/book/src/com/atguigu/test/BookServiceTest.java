package com.atguigu.test;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/* @author  i-s-j-h-d
 * @version 1.0 */public class BookServiceTest {

     private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"星际牛仔", "1125", new BigDecimal(1000000),
                100000000, 0, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(23,"星际牛仔", "1100", new BigDecimal(999999),
                10, 111110, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(23));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void page() {
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }

    @Test
    public void pageByPrice() {
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE, 10, 50));
    }


}