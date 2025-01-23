package com.atguigu.spring5.test;

import com.atguigu.spring5.entity.Book;
import com.atguigu.spring5.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class TestBook {

    @Test
    public void testJdbcTemplate() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
//        Book book = new Book(13, "老婆不如右手", 3, new BigDecimal(100), new BigDecimal(200));
//        bookService.addBook(book);
//        bookService.updateBook(book);
//        bookService.deleteBook("13");
//        System.out.println(bookService.findCount());
//        System.out.println(bookService.findOne(10));
//        System.out.println(bookService.findAll());

        // 批量添加测试
//        List<Object[]> batchArgs = new ArrayList<>();
//        Object[] o1 = {null, "老婆不如右手1", 4, new BigDecimal(100), new BigDecimal(200), "1234567890"};
//        Object[] o2 = {null, "老婆不如右手2", 3, new BigDecimal(100), new BigDecimal(200), "1234567890"};
//        batchArgs.add(o1);
//        batchArgs.add(o2);
//        // 调用批量添加
//        bookService.batchAdd(batchArgs);

//        List<Object[]> batchArgs = new ArrayList<>();
//        Object[] o1 = {"轻音少女", 2, 20};
//        Object[] o2 = {"星际牛仔", 3, 21};
//        batchArgs.add(o1);
//        batchArgs.add(o2);
//        bookService.batchUpdate(batchArgs);

        List<Object[]> batchArgs = new ArrayList<>();
        Object[] o1 = {20};
        Object[] o2 = {21};
        batchArgs.add(o1);
        batchArgs.add(o2);
        bookService.batchDelete(batchArgs);


    }

}
