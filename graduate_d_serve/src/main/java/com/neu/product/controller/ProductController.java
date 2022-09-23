package com.neu.product.controller;

import com.neu.cart.biz.CartBiz;
import com.neu.cart.entity.Cart;
import com.neu.product.biz.ProductBiz;
import com.neu.product.biz.RecentlyRateBiz;
import com.neu.product.entity.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cjf
 * @Date 2022/9/14
 **/
@RestController
@RequestMapping("product")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class ProductController {
    @Autowired
    private ProductBiz productBiz;
    @Autowired
    private RecentlyRateBiz recentlyRateBiz;
    @Autowired
    private CartBiz cartBiz;

    @ApiOperation("产品信息接口")
    @GetMapping(value = "/findProduct/{index}")
    public Map<String, Object> findUser(@ApiParam("分页的参数") @PathVariable("index") Integer index) {
        if (index == null) {
            index = 1;
        }
        int size = 5;
        List<Product> list = productBiz.findProduct(index, size);

        int count = productBiz.countProduct();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("index", index);
        map.put("count", count);
        return map;
    }

    @ApiOperation("根据类型查询产品信息接口")
    @GetMapping(value = "/findProductByType/{index}/{ptype}")
    public Map<String, Object> findProductByType(@ApiParam("分页的参数") @PathVariable("index") Integer index, @PathVariable("ptype") String ptype) {
        if (index == null) {
            index = 1;
        }
        int size = 3;
        List<Product> list = productBiz.findProductByType(index, size, ptype);
        int count = productBiz.countProductByType(ptype);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("index", index);
        map.put("ptype", ptype);
        map.put("count", count);
        return map;
    }

    @ApiOperation("普通用户根据类型查询产品（不分页）")
    @GetMapping(value = "/CustomerfindProductByType/{ptype}")
    public Map<String, Object> CustomerfindProductByType(@PathVariable("ptype") String ptype) {
        List<Product> list = productBiz.CustomerfindProductByType(ptype);
        int count = productBiz.countProductByType(ptype);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("ptype", ptype);
        map.put("count", count);
        return map;
    }

    @ApiOperation("普通用户根据id查询产品")
    @GetMapping(value = "/CustomerfindProductById/{id}")
    public Map<String, Object> CustomerfindProductById(@PathVariable("id") String id) {
        List<Product> list = productBiz.CustomerfindProductById(id);
        //System.out.println(list.get(0).getPname());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        return map;
    }

    @ApiOperation("根据id删除产品接口")
    @GetMapping(value = "/deleteProduct/{id}")
    public boolean deleteProduct(@ApiParam("删除的参数") @PathVariable("id") String id) {
        System.out.println(id);
        return productBiz.deleteProduct(id);

    }

    @ApiOperation("根据id查询产品接口")
    @GetMapping(value = "/findById/{id}")
    public List<HashMap<String, Object>> findById(@ApiParam("查询的参数") @PathVariable("id") String id) {
        return productBiz.findById(id);
    }

    @ApiOperation("修改产品接口")
    @GetMapping(value = "/updateProduct/{id}/{pname}/{pimg}/{ptype}/{price}/{totalnum}/{averagerate}/{psize}/{pmanager}/{pdesc}")
    public boolean updateProduct(@ApiParam("修改的参数") @PathVariable("id") String id,
                                 @PathVariable("pname") String pname, @PathVariable("pimg") String pimg,
                                 @PathVariable("ptype") String ptype,
                                 @PathVariable("price") String price,
                                 @PathVariable("totalnum") String totalnum, @PathVariable("averagerate") String averagerate,
                                 @PathVariable("psize") String psize, @PathVariable("pmanager") String pmanager,
                                 @PathVariable("pdesc") String pdesc) {

        Type type = new Type();
        type.setTid(ptype);
        Manager manager = new Manager();
        manager.setMid(pmanager);
        Product product = new Product(id, pname, pimg, type, price, totalnum, averagerate, psize, manager, pdesc);
        System.out.println(id);
        return productBiz.updateProduct(product);


    }

    @ApiOperation("根据id修改产品状态")
    @GetMapping(value = "/updateState/{id}/{pstate}")
    public boolean updateState(@ApiParam("修改状态的参数") @PathVariable("id") String id, @PathVariable("pstate") Integer pstate) {
        if (pstate == null) {
            pstate = 1;
        }
        State state = new State();
        state.setSid(pstate);
        Product product = new Product(id, state);
        return productBiz.updateState(product);
    }

    @ApiOperation("添加产品")
    @GetMapping(value = "/addProduct/{pname}/{ptype}/{price}/{totalnum}/{averagerate}/{psize}/{pmanager}/{pdesc}")
    public boolean addProduct(@ApiParam("添加产品的参数") @PathVariable("pname") String pname,
                              @PathVariable("ptype") String ptype,
                              @PathVariable("price") String price,
                              @PathVariable("totalnum") String totalnum,
                              @PathVariable("averagerate") String averagerate,
                              @PathVariable("psize") String psize,
                              @PathVariable("pmanager") String pmanager,
                              @PathVariable("pdesc") String pdesc
    ) {
        Type type = new Type();


        type.setTid(ptype);
        Manager manager = new Manager();
        manager.setMid(pmanager);
        RecentlyRate recentlyRate = new RecentlyRate();
        recentlyRate.setRname(pname);
        productBiz.addRecentlyRate(recentlyRate);
        Product product = new Product(pname, type, price, totalnum, averagerate, psize, manager, pdesc);
        return productBiz.addProduct(product);
    }

    @ApiOperation("模糊查询产品")
    @GetMapping(value = "/findProductMo/{index}/{pname}")
    public Map<String, Object> findProductMo(@ApiParam("模糊查询产品的参数") @PathVariable("index") Integer index,
                                             @PathVariable("pname") String pname) {
        if (index == null) {
            index = 1;
        }
        int size = 3;
        List<Product> list = productBiz.findProductMo(index, size, pname);
        Map<String, Object> map = new HashMap<String, Object>();
        //调用查询总记录的方法
        int count = productBiz.countMo(pname);
        map.put("list", list);
        map.put("index", index);
        map.put("pname", pname);
        map.put("count", count);
        return map;

    }

    @ApiOperation("添加产品到预购")
    @GetMapping(value = "/addCart/{id}/{pnum}")
    public boolean addCart(@ApiParam("添加产品的参数") @PathVariable("id") String id,@PathVariable("pnum") Integer pnum, HttpServletRequest request) {
        if(pnum==null){
            pnum=1;
        }
        String username = (String) request.getSession().getAttribute("username");
        List<Product> list= productBiz.CustomerfindProductById(id);
        String pname=list.get(0).getPname();
        Cart cart=cartBiz.findByPname(pname,username);
        String price=list.get(0).getPrice();
        if(cart!=null){
            int pnum1=cart.getPnum();
            int pnum2=pnum1+pnum;
            double sum1=Double.parseDouble(price)*pnum2;

            Cart cart2=new Cart(pname,pnum2,sum1);
            return cartBiz.updateCart(cart2);
        }else{
            double sum=Double.parseDouble(price)*pnum;
            Cart cart1 = new Cart(username, list.get(0).getPimg(), pname,price, list.get(0).getAveragerate(),pnum,sum);
            return productBiz.addCart(cart1);
        }


    }

}
