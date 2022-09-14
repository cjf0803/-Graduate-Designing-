package com.neu.book.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.neu.book.bookstatus.entity.BookStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.book.biz.BookBiz;
import com.neu.book.entity.Book;
import com.neu.lend.entity.Lend;

import com.neu.user.biz.UserBiz;
import com.neu.user.entity.User;

@Controller
@RequestMapping("book")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class BookController {
    @Autowired
    private BookBiz bookBiz;
    @Autowired
    private UserBiz userBiz;

    @RequestMapping(value = "/findBook")
    @ResponseBody
    public Map<String, Object> findBook(Integer index) {
        if (index == null) {
            index = 1;
        }
        int size = 4;

        List<Book> list1 = bookBiz.findBook(index, size);

        int count = bookBiz.count();

        int totalPage = count % size == 0 ? count / size : count / size + 1;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list1", list1);
        map.put("index", index);
        map.put("totalPage", totalPage);
        map.put("count", count);
        return map;

    }


    @RequestMapping(value = "/findById")
    @ResponseBody
    public List<HashMap<String, Object>> findById(String id) {
        System.out.println(id);
        return bookBiz.findById(id);
    }

    @RequestMapping(value = "/deleteBook")
    @ResponseBody
    public boolean deleteBook(String id) {
        return bookBiz.deleteBook(id);

    }

    @RequestMapping(value = "/updateBook")
    @ResponseBody
    public boolean updateBook(String id, String bookname, String author, String pressname, String status, String path) {
        BookStatus bookStatus = new BookStatus();
        bookStatus.setStatus_id(status);
        Book book = new Book(id, bookname, author, pressname, bookStatus, path);
        System.out.println(id);
        return bookBiz.updateBook(book);


    }

    @RequestMapping(value = "/addBook")
    @ResponseBody
    public boolean addBook(String bookname, String author, String pressname, String status, String path) {
        BookStatus bookStatus1 = new BookStatus();
        bookStatus1.setStatus_id(status);
        Book book = new Book(bookname, author, pressname, bookStatus1, path);
        return bookBiz.addBook(book);
    }

    @RequestMapping(value = "/addLend")
    @ResponseBody
    public boolean addLend(String id, HttpServletRequest request) {
        List<HashMap<String, Object>> list = bookBiz.findById(id);
        BookStatus bookStatus=new BookStatus();
        bookStatus.setStatus_id("0");
        Book book1 = new Book((String)list.get(0).get("ID"), (String)list.get(0).get("BOOKNAME"), (String)list.get(0).get("AUTHOR"),  (String)list.get(0).get("PRESSNAME"),bookStatus,(String)list.get(0).get("PATH"));
        bookBiz.updateBook(book1);
        User user = userBiz.findByUsername((String) request.getSession().getAttribute("username"));
        Lend lend = new Lend(book1.getId(), book1.getBookname(), book1.getAuthor(), user.getId(), user.getRealname(), user.getUsername());

        return bookBiz.addLend(lend);
    }

    @RequestMapping(value = "/findBookMo")
    @ResponseBody
    public Map<String, Object> findBookMo(Integer index, String bookname) {
        if (index == null) {
            index = 1;

        }
        int size = 3;
        List<Book> list = bookBiz.findBookMo(index, size, bookname);
        Map<String, Object> map = new HashMap<String, Object>();
        //调用查询总记录的方法
        int count = bookBiz.countMo(bookname);
        map.put("list", list);
        map.put("index", index);
        map.put("bookname", bookname);
        map.put("count", count);
        return map;

    }
}
