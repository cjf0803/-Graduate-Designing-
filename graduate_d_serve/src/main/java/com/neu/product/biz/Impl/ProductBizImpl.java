package com.neu.product.biz.Impl;

import com.neu.cart.entity.Cart;
import com.neu.product.biz.ProductBiz;
import com.neu.product.entity.Product;
import com.neu.product.entity.RecentlyRate;
import com.neu.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cjf
 * @Date 2022/9/14
 **/
@Service("productBiz")
public class ProductBizImpl implements ProductBiz {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> findProduct(int index, int size) {
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("index",index);
        map.put("size",size);
        List<Product> list =productMapper.findProduct(map);
        return list;
    }

    @Override
    public int countProduct() {
        return productMapper.countProduct();
    }

    @Override
    public boolean deleteProduct(String id) {
        return productMapper.deleteProduct(id);
    }

    @Override
    public boolean updateProduct(Product product) {
        return productMapper.updateProduct(product);
    }

    @Override
    public List<HashMap<String, Object>> findById(String id) {
        return productMapper.findById(id);
    }

    @Override
    public boolean updateState(Product product) {
        return productMapper.updateState(product);
    }

    @Override
    public boolean addProduct(Product product) {
        return productMapper.addProduct(product);
    }

    @Override
    public List<Product> findProductMo(int index, int size, String pname) {
        Map<String,Object> map=new HashMap<String,Object>();
        //将参数传入map
        map.put("index",index);
        map.put("size",size);
        map.put("pname",pname);
        List<Product> list=productMapper.findProductMo(map);

        return list;
    }

    @Override
    public int countMo(String pname) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("pname",pname);
        return  productMapper.countMo(map);
    }

    @Override
    public void addRecentlyRate(RecentlyRate recentlyRate) {
        productMapper.addRecentlyRate(recentlyRate);
    }

    @Override
    public List<Product> findProductByType(int index, int size, String ptype) {
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("index",index);
        map.put("size",size);
        map.put("ptype",ptype);
        List<Product> list =productMapper.findProductByType(map);
        return list;
    }

    @Override
    public int countProductByType(String ptype) {
        return productMapper.countProductByType(ptype);
    }

    @Override
    public List<Product> CustomerfindProductByType(String ptype) {
        return productMapper.CustomerfindProductByType(ptype);

    }

    @Override
    public List<Product> CustomerfindProductById(String id) {
        return productMapper.CustomerfindProductById(id);
    }

    @Override
    public boolean addCart(Cart cart) {
        return productMapper.addCart(cart);
    }
}
