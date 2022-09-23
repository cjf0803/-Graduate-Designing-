package com.neu.cart.biz;

import com.neu.cart.entity.Cart;
import com.neu.order.entity.Order;
import com.neu.product.entity.Product;

import java.util.List;
import java.util.Map;


/**
 * @Author cjf
 * @Date 2022/9/18
 **/
public interface CartBiz {
    public List<Cart> findCart(int index,int size);
    public int countCart();
    public List<Cart> findCartByUsername(int index,int size,String username);
    public int countCartByUsername(String username);
    public boolean deleteCart(String cid);
    public boolean updateNumById(Cart cart);
    public boolean updateSumById(Cart cart);
    public Cart findById(String cid);
    public boolean delCartAll(String[] ids);
    public boolean updateCart(Cart cart);
    public Cart findByPname(String pname,String username);
    public double totalSum(String username);
    public double avg(String username);
    public List<Cart> findCartMo(int index, int size, String username);
    public int countMo(String username);
    public boolean addOrder(String username,double totalsum,double exincome,String payway);
    public boolean addCancelOrder(String username,double totalsum,double exincome,String payway);

}
