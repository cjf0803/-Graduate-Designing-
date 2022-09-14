package com.neu.lend.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.neu.book.entity.Book;
import com.neu.lend.entity.Lend;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 陈锦房
 */
@Mapper
public interface LendMapper {

    public List<Lend> findLendByUsername(Map<String, Object> map);

    public int count();


    public List<Lend> findLend(Map<String, Object> map);

    public boolean deleteLend(String id);


    public boolean updateLend(Lend lend);

    public List<HashMap<String, Object>> findById(String id);


    public boolean reback(Lend lend);
    //模糊查询
    public List<Lend> findLendMo(Map<String,Object> map);

    //模糊查询总记录数
    public int countMo(Map<String,Object> map);

}
