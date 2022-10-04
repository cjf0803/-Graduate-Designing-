package com.neu.order.biz.Impl;


import com.neu.order.biz.OrderBiz;
import com.neu.order.entity.Order;
import com.neu.order.mapper.OrderMapper;
import com.neu.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cjf
 * @Date 2022/9/19
 **/
@Service("orderBiz")
public class OrderBizImpl implements OrderBiz {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> findOrder(int index, int size) {
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("index",index);
        map.put("size",size);
        List<Order> list =orderMapper.findOrder(map);
        return list;
    }

    @Override
    public int countOrder() {
        return orderMapper.countOrder();
    }

    @Override
    public List<Order> findOrderByState(int index, int size, String state) {
        Map<String,Object> map=new HashMap<String,Object>();
        //将参数传入map
        map.put("index",index);
        map.put("size",size);
        map.put("state",state);
        List<Order> list=orderMapper.findOrderByState(map);
        return list;
    }

    @Override
    public int countOrderByState(String state) {
        return orderMapper.countOrderByState(state);
    }

    @Override
    public boolean deleteOrder(String oid) {
        return orderMapper.deleteOrder(oid);
    }

    @Override
    public List<Order> findOrderMo(int index, int size, String username) {
        Map<String,Object> map=new HashMap<String,Object>();
        //将参数传入map
        map.put("index",index);
        map.put("size",size);
        map.put("username",username);
        List<Order> list=orderMapper.findOrderMo(map);
        return list;
    }

    @Override
    public int countMo(String username) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("username",username);
        return  orderMapper.countMo(map);
    }

    @Override
    public boolean delOrderAll(String[] ids) {
        return orderMapper.delOrderAll(ids);
    }

    @Override
    public void updateTwo(Order order) {
        orderMapper.updateTwo(order);
    }

    @Override
    public Order findById(String oid) {
        return orderMapper.findById(oid);
    }

    @Override
    public void updateSuccess(String oid) {
        orderMapper.updateSuccess(oid);
    }

    @Override
    public List<Order> findOrderByUsername(int index, int size, String username) {
        Map<String,Object> map=new HashMap<String,Object>();
        //将参数传入map
        map.put("index",index);
        map.put("size",size);
        map.put("username",username);
        List<Order> list=orderMapper.findOrderByUsername(map);
        return list;

    }

    @Override
    public int countOrderByUsername(String username) {
        return orderMapper.countOrderByUsername(username);
    }

    @Override
    public List<Order> findBySuccess(String username) {
        return orderMapper.findBySuccess(username);
    }


}
