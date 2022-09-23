package com.neu.cart.controller;

import com.neu.cart.biz.CartBiz;
import com.neu.cart.entity.Cart;

import com.neu.order.entity.Order;
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
 * @Date 2022/9/18
 **/
@RestController
@RequestMapping("cart")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class CartController {
    @Autowired
    private CartBiz cartBiz;
    @ApiOperation("所有用户预购信息接口")
    @GetMapping(value="/findCart/{index}")
    public Map<String, Object> findCart(@ApiParam("分页的参数") @PathVariable("index") Integer index){
        if (index==null){
            index=1;
        }
        int size=5;
        List<Cart> list = cartBiz.findCart(index,size);
        int count = cartBiz.countCart();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("index", index);
        map.put("count", count);
        return map;
    }
    @ApiOperation("根据用户名查询预购信息接口")
    @GetMapping(value="/findCartByUsername/{index}")
    public Map<String, Object> findCartByUsername(@ApiParam("分页的参数") @PathVariable("index") Integer index, HttpServletRequest request){
        if (index==null){
            index=1;
        }
        int size=4;
        String username=(String) request.getSession().getAttribute("username");
        List<Cart> list = cartBiz.findCartByUsername(index,size,username);
        int count = cartBiz.countCartByUsername(username);
        double totalsum= cartBiz.totalSum(username);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("index", index);
        map.put("username",username);
        map.put("count", count);
        map.put("totalsum",totalsum);
        return map;
    }
    @ApiOperation("批量删除产品接口")
    @GetMapping(value="/delCartAll/{ids}")
    public boolean delCartAll(@ApiParam("删除的参数") @PathVariable("ids") String[] ids) {
        return cartBiz.delCartAll(ids);

    }
    @ApiOperation("根据id删除产品接口")
    @GetMapping(value="/deleteCart/{cid}")
    public boolean deleteCart(@ApiParam("删除的参数") @PathVariable("cid") String cid) {
        return cartBiz.deleteCart(cid);

    }
    @ApiOperation("根据id修改预购份数接口")
    @GetMapping(value="/updateNumById/{cid}/{pnum}")
    public boolean updateNumById(@ApiParam("修改的参数") @PathVariable("cid") String cid,@PathVariable("pnum") Integer pnum) {
        if(pnum==null){
            pnum=1;
        }
        boolean c=false;
        Cart cart=cartBiz.findById(cid);
        String price=cart.getPrice();
        double sum=Double.parseDouble(price)*pnum;
        Cart cart1=new Cart(cid,sum);
        Cart cart2=new Cart(cid,pnum);
        boolean a=cartBiz.updateNumById(cart2);
        boolean b=cartBiz.updateSumById(cart1);
        if(a&&b){
            c=true;
        }else{
            c=false;
        }
        return c;
    }
    @ApiOperation("添加订单接口")
    @GetMapping(value="/addOrder/{totalsum}/{payway}")
    public boolean addOrder(@ApiParam("传入的参数")@PathVariable("totalsum") double totalsum,
                            @PathVariable("payway") String payway,
                            HttpServletRequest request) {
        String username=(String) request.getSession().getAttribute("username");
        double exincome= totalsum*(cartBiz.avg(username)/100);
       return cartBiz.addOrder(username,totalsum,exincome,payway);
    }
    @ApiOperation("添加已取消订单接口")
    @GetMapping(value="/addCancelOrder/{totalsum}/{payway}")
    public boolean addCancelOrder(@ApiParam("传入的参数")@PathVariable("totalsum") double totalsum,
                            @PathVariable("payway") String payway,
                            HttpServletRequest request) {
        String username=(String) request.getSession().getAttribute("username");
        double exincome= totalsum*(cartBiz.avg(username)/100);
        return cartBiz.addCancelOrder(username,totalsum,exincome,payway);
    }
    @ApiOperation("根据用户名模糊查询预购")
    @GetMapping(value = "/findCartMo/{index}/{username}")
    public Map<String, Object> findCartMo(@ApiParam("模糊查询产品的参数") @PathVariable("index") Integer index,
                                           @PathVariable("username") String username) {
        if (index == null) {
            index = 1;
        }
        int size = 5;
        List<Cart> list = cartBiz.findCartMo(index,size,username);
        Map<String, Object> map = new HashMap<String, Object>();
        //调用查询总记录的方法
        int count = cartBiz.countMo(username);
        map.put("list", list);
        map.put("index", index);
        map.put("username", username);
        map.put("count", count);
        return map;

    }

}
