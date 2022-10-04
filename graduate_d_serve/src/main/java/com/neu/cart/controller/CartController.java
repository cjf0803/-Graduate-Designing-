package com.neu.cart.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.neu.cart.biz.CartBiz;
import com.neu.cart.entity.Cart;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cjf
 * @Date 2022/9/18
 **/
@Controller
@RequestMapping("cart")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class CartController {
    @Autowired
    private CartBiz cartBiz;

    @ApiOperation("所有用户预购信息接口")
    @ResponseBody
    @GetMapping (value="/findCart/{index}")
    public Map<String, Object> findCart(@ApiParam("分页的参数") @PathVariable("index")Integer index){
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
    @ApiOperation("所有用户预购信息接口")
    @ResponseBody
    @GetMapping(value="/findCartByUsername/{index}")
    public Map<String, Object> findCartByUsername(@ApiParam("分页的参数") @PathVariable("index")Integer index, HttpServletRequest request){
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
    @ResponseBody
    @GetMapping(value="/delCartAll/{ids}")
    public boolean delCartAll(@ApiParam("删除的参数") @PathVariable("ids") String[] ids) {
        return cartBiz.delCartAll(ids);

    }
    @ApiOperation("根据id删除产品接口")
    @ResponseBody
    @GetMapping(value="/deleteCart/{cid}")
    public boolean deleteCart(@ApiParam("删除的参数") @PathVariable("cid") String cid) {
        return cartBiz.deleteCart(cid);

    }
    @ApiOperation("根据id修改预购份数接口")
    @ResponseBody
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

    @RequestMapping(value="/addOrder")
    @ResponseBody
    public boolean addOrder(String detail,double totalSum,String payWay,String detail_id ,String username) throws ParseException {
//        String username=(String) request.getSession().getAttribute("username");
//        String startStr = "2020.1.1 00:00:00";
//        String endStr = "2022.12.31 23:59:59";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
//        Date start =  sdf.parse(startStr);
//        Date end =  sdf.parse(endStr);
//        java.sql.Date startDate=new java.sql.Date(start.getTime());
//        java.sql.Date endDate=new java.sql.Date(end.getTime());
//        long random = (long) (Math.random()*(endDate.getTime()-startDate.getTime()+startDate.getTime()));
//        java.sql.Date time=new java.sql.Date(random);
        Date randomDate = randomDate("2020-01-01 ", "2022-11-30 ");
        java.sql.Date time=new java.sql.Date(randomDate.getTime());
        JSONArray detail1 =JSON.parseArray(detail);
        boolean flag=cartBiz.insertAll(detail_id,detail1,username);
        System.out.println(flag);
        double exincome= totalSum*(cartBiz.avg(detail_id)/100);
       return  cartBiz.addOrder(username,totalSum,exincome,time,payWay,detail_id);
    }
    /**
     * 获取随机日期
     *
     * @param beginDate
     *            起始日期，格式为：yyyy-MM-dd
     * @param endDate
     *            结束日期，格式为：yyyy-MM-dd
     * @return
     */

    private static Date randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
            Date start = format.parse(beginDate);// 构造开始日期
            Date end = format.parse(endDate);// 构造结束日期
            // getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());

            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }
    @ApiOperation("添加已取消订单接口")
    @ResponseBody
    @GetMapping(value="/addCancelOrder/{totalsum}/{payway}")
    public boolean addCancelOrder(@ApiParam("传入的参数")@PathVariable("totalsum") double totalsum,
                            @PathVariable("payway") String payway,
                            HttpServletRequest request) {
        String username=(String) request.getSession().getAttribute("username");
        double exincome= totalsum*(cartBiz.avg(username)/100);
        return cartBiz.addCancelOrder(username,totalsum,exincome,payway);
    }
    @ApiOperation("根据用户名模糊查询预购")
    @ResponseBody
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
