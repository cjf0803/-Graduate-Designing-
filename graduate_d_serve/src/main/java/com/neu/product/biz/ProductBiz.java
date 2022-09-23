package com.neu.product.biz;

import com.neu.cart.entity.Cart;
import com.neu.product.entity.Product;
import com.neu.product.entity.RecentlyRate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cjf
 * @Date 2022/9/14
 **/
public interface ProductBiz {
    public List<Product> findProduct(int index,int size);
    public int countProduct();
    public boolean deleteProduct(String id);
    public boolean updateProduct(Product product);
    public  List<HashMap<String, Object>> findById(String id);
    public boolean updateState(Product product);
    public boolean addProduct(Product product);
    public List<Product> findProductMo(int index,int size,String pname);
    public int countMo(String pname);
    public void addRecentlyRate(RecentlyRate recentlyRate);
    public List<Product> findProductByType(int index,int size,String ptype);
    public int countProductByType(String ptype);
    public List<Product> CustomerfindProductByType(String ptype);
    public List<Product> CustomerfindProductById(String id);
    public boolean addCart(Cart cart);
}
