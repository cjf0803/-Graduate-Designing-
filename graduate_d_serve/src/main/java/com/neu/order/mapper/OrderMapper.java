package com.neu.order.mapper;

import com.neu.order.entity.Order;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author cjf
 * @Date 2022/9/19
 **/
@Mapper
public interface OrderMapper {
    public List<Order> findOrder(Map<String, Object> map);
    public int countOrder();
    public List<Order> findOrderByState(Map<String, Object> map);
    public int countOrderByState(String state);
    public boolean deleteOrder(String oid);
    public List<Order> findOrderMo(Map<String,Object> map);
    public int countMo(Map<String,Object> map);
    public boolean delOrderAll(String[] ids);
    public void updateTwo(Order order);
    public Order findById(String oid);
    public void updateSuccess(String oid);
    public List<Order> findOrderByUsername(Map<String, Object> map);
    public int countOrderByUsername(String username);
    public List<Order> findBySuccess(String username);




}
