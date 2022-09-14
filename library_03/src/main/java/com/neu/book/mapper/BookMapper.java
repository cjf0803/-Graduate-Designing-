package com.neu.book.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neu.book.entity.Book;
import com.neu.lend.entity.Lend;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper {

    public List<Book> findBook(Map<String,Object> map);

    public int count();

    public boolean deleteBook(String id);

    public boolean updateBook(Book book);

    public  List<HashMap<String, Object>> findById(String id);

    public boolean addBook(Book book);

    public boolean addLend(Lend lend);
    //模糊查询
    public List<Book> findBookMo(Map<String,Object> map);

    //模糊查询总记录数
    public int countMo(Map<String,Object> map);
}
