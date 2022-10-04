package com.neu.cart.mapper;

import com.alibaba.fastjson.JSONArray;
import com.neu.cart.entity.Cart;
import com.neu.order.entity.Order;
import com.neu.product.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author cjf
 * @Date 2022/9/18
 **/
@Mapper
public interface CartMapper {
    public List<Cart> findCart(Map<String, Object> map);
    public int countCart();
    public List<Cart> findCartByUsername(Map<String, Object> map);
    public int countCartByUsername(String username);
    public boolean deleteCart(String cid);
    public boolean updateNumById(Cart cart);
    public boolean updateSumById(Cart cart);
    public Cart findById(String cid);
    public boolean delCartAll(String[] ids);
    public boolean updateCart(Cart cart);
    public Cart findByPname(String pname,String username);
    public double totalSum(String username);
    public boolean addOrder(Map<String, Object> map);
    public double avg(String id);
    public boolean addCancelOrder(Map<String, Object> map);
    public List<Cart> findCartMo(Map<String,Object> map);
    public int countMo(Map<String,Object> map);
    public boolean insertAll(@Param("ids") String ids, @Param("detail") JSONArray detail, @Param("username") String username);


}
