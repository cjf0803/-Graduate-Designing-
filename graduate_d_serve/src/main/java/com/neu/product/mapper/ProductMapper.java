package com.neu.product.mapper;

import com.neu.cart.entity.Cart;
import com.neu.product.entity.Product;
import com.neu.product.entity.RecentlyRate;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cjf
 * @Date 2022/9/14
 **/
@Mapper
public interface ProductMapper {
    public List<Product> findProduct(Map<String, Object> map);
    public int countProduct();
    public boolean deleteProduct(String id);
    public boolean updateProduct(Product product);
    public  List<HashMap<String, Object>> findById(String id);
    public boolean updateState(Product product);
    public boolean addProduct(Product product);
    public List<Product> findProductMo(Map<String,Object> map);
    public int countMo(Map<String,Object> map);
    public void addRecentlyRate(RecentlyRate recentlyRate);
    public List<Product> findProductByType(Map<String, Object> map);
    public int countProductByType(String ptype);
    public List<Product> CustomerfindProductByType(String ptype);
    public List<Product> CustomerfindProductById(String id);
    public boolean addCart(Cart cart);

}
