package com.neu.book.biz;

import java.util.HashMap;
import java.util.List;

import com.neu.book.entity.Book;
import com.neu.lend.entity.Lend;



/**
 * @author 陈锦房
 */
public interface BookBiz {

    public List<Book> findBook(int index, int size);
    public int count();
    public boolean deleteBook(String id);
    public boolean updateBook(Book book);
    public  List<HashMap<String, Object>> findById(String id);
    public boolean addBook(Book book);
    public boolean addLend(Lend lend);
    public List<Book> findBookMo(int index , int size, String bookname);
    public int countMo(String bookname);

}
