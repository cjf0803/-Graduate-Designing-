package com.neu.lend.biz;

import java.util.HashMap;
import java.util.List;

import com.neu.book.entity.Book;
import com.neu.lend.entity.Lend;

/**
 * @author 陈锦房
 */
public interface LendBiz {
    public List<Lend> findLendByUsername(int index, int size, String username);

    public int count();


    public List<Lend> findLend(int index, int size);

    public boolean deleteLend(String id);


    public boolean updateLend(Lend lend);

    public  List<HashMap<String, Object>>  findById(String id);


    public boolean reback(Lend lend);

    public List<Lend> findLendMo(int index , int size, String username);
    public int countMo(String username);



}
