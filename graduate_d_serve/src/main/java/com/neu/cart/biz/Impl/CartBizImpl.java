package com.neu.cart.biz.Impl;

import com.alibaba.fastjson.JSONArray;
import com.neu.cart.biz.CartBiz;
import com.neu.cart.entity.Cart;
import com.neu.cart.mapper.CartMapper;

import com.neu.order.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cjf
 * @Date 2022/9/18
 **/
@Service("CartBiz")
public class CartBizImpl implements CartBiz {
    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<Cart> findCart(int index, int size) {
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("index",index);
        map.put("size",size);
        List<Cart> list =cartMapper.findCart(map);
        return list;
    }

    @Override
    public int countCart() {
        return cartMapper.countCart();
    }

    @Override
    public List<Cart> findCartByUsername(int index, int size, String username) {
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("index",index);
        map.put("size",size);
        map.put("username",username);
        List<Cart> list =cartMapper.findCartByUsername(map);
        return list;
    }

    @Override
    public int countCartByUsername(String username) {
        return cartMapper.countCartByUsername(username);
    }

    @Override
    public boolean deleteCart(String cid) {
        return cartMapper.deleteCart(cid);
    }

    @Override
    public boolean updateNumById(Cart cart) {
        return cartMapper.updateNumById(cart);
    }

    @Override
    public boolean updateSumById(Cart cart) {
        return cartMapper.updateSumById(cart);
    }

    @Override
    public Cart findById(String cid) {
        return cartMapper.findById(cid);
    }

    @Override
    public boolean delCartAll(String[] ids) {
        return cartMapper.delCartAll(ids);
    }

    @Override
    public boolean updateCart(Cart cart) {
        return cartMapper.updateCart(cart);
    }

    @Override
    public Cart findByPname(String pname,String username) {
        return cartMapper.findByPname(pname,username);
    }

    @Override
    public double totalSum(String username) {
        return cartMapper.totalSum(username);
    }

    @Override
    public double avg(String id) {
        return cartMapper.avg(id);
    }

    @Override
    public List<Cart> findCartMo(int index, int size, String username) {
        Map<String,Object> map=new HashMap<String,Object>();
        //将参数传入map
        map.put("index",index);
        map.put("size",size);
        map.put("username",username);
        List<Cart> list=cartMapper.findCartMo(map);
        return list;
    }

    @Override
    public int countMo(String username) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("username",username);
        return cartMapper.countMo(map);
    }

    @Override
    public boolean addOrder(String username, double totalsum, double exincome, Date time, String payway, String detail_id) {
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("username",username);
        map.put("totalsum",totalsum);
        map.put("exincome",exincome);
        map.put("time",time);
        map.put("payway",payway);
        map.put("detail_id",detail_id);
        return cartMapper.addOrder(map);
    }

    @Override
    public boolean addCancelOrder(String username, double totalsum, double exincome, String payway) {
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("username",username);
        map.put("totalsum",totalsum);
        map.put("exincome",exincome);
        map.put("payway",payway);
        return cartMapper.addCancelOrder(map);
    }

    @Override
    public boolean insertAll(String ids, JSONArray detail, String username) {

        return cartMapper.insertAll(ids,detail,username);
    }
}
