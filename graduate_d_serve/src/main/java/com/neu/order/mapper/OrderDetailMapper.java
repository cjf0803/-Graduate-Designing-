package com.neu.order.mapper;


import com.neu.order.entity.Detail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author cjf
 * @Date 2022/10/2
 **/
@Mapper
public interface OrderDetailMapper {
    public List<Detail> findDetailById(Map<String, Object> map);
    public int countDetailById(String id);
    public void deleteDetail(String id);
    public List<Detail> findDetailByUsername(Map<String, Object> map);
    public int countDetailByUsername(String username);



}
