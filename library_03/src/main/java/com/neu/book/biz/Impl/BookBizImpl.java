package com.neu.book.biz.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neu.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.book.biz.BookBiz;
import com.neu.book.mapper.BookMapper;
import com.neu.book.entity.Book;
import com.neu.lend.entity.Lend;

/**
 * @author 陈锦房
 */
@Service("bookBiz")
public class BookBizImpl implements BookBiz {
	@Autowired
	private BookMapper bookMapper;
	

    @Override
    public List<Book> findBook(int index, int size) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);
        List<Book> list = bookMapper.findBook(map);
        return list;
    }

    @Override
    public int count() {
        int count =bookMapper.count();
        return count;
    }

    @Override
    public boolean deleteBook(String id) {
        return bookMapper.deleteBook(id);
    }

    @Override
    public boolean updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    @Override
    public List<HashMap<String, Object>> findById(String id) {
        return bookMapper.findById(id);
    }

    
    @Override
    public boolean addBook(Book book) {
        return bookMapper.addBook(book);
    }

	@Override
	public boolean addLend(Lend lend) {
		// TODO Auto-generated method stub
		return bookMapper.addLend(lend);
	}

    @Override
    public List<Book> findBookMo(int index, int size, String bookname) {
        Map<String,Object> map=new HashMap<String,Object>();
        //将参数传入map
        map.put("index",index);
        map.put("size",size);
        map.put("bookname",bookname);
        List<Book> list=bookMapper.findBookMo(map);

        return list;
    }

    @Override
    public int countMo(String bookname) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("bookname",bookname);

        return  bookMapper.countMo(map);
    }

}
