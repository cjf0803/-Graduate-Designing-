package com.neu.order.biz;

import com.neu.order.entity.Order;

import java.util.List;


/**
 * @Author cjf
 * @Date 2022/9/19
 **/
public interface OrderBiz {
    public List<Order> findOrder(int index,int size);
    public int countOrder();
    public List<Order> findOrderByState(int index,int size,String state);
    public int countOrderByState(String state);
    public boolean deleteOrder(String oid);
    public List<Order> findOrderMo(int index,int size,String username);
    public int countMo(String username);
    public boolean delOrderAll(String[] ids);
    public void updateTwo(Order order);
    public Order findById(String oid);
    public void updateSuccess(String oid);
    public List<Order> findOrderByUsername(int index,int size,String username);
    public int countOrderByUsername(String username);
    public List<Order> findBySuccess(String username);
}
