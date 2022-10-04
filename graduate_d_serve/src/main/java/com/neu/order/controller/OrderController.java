package com.neu.order.controller;

import com.neu.order.biz.OrderBiz;
import com.neu.order.biz.OrderDetailBiz;
import com.neu.order.entity.Order;
import com.neu.user.biz.UserBiz;
import com.neu.user.entity.Money;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @Author cjf
 * @Date 2022/9/19
 **/
@RestController
@RequestMapping("order")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class OrderController {
    @Autowired
    private OrderBiz orderBiz;
    @Autowired
    private UserBiz userBiz;
    @Autowired
    private OrderDetailBiz orderDetailBiz;
    @ApiOperation("订单信息接口")
    @GetMapping(value = "/findOrder/{index}")
    public Map<String, Object> findOrder(@ApiParam("分页的参数") @PathVariable("index") Integer index) {
        if (index == null) {
            index = 1;
        }
        int size = 5;
        List<Order> list = orderBiz.findOrder(index,size);

        int count = orderBiz.countOrder();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("index", index);
        map.put("count", count);
        return map;
    }

    @ApiOperation("根据状态查询订单信息接口")
    @GetMapping(value = "/findOrderByState/{index}/{state}")
    public Map<String, Object> findOrderByState(@ApiParam("分页的参数") @PathVariable("index") Integer index, @PathVariable("state") String state) {
        if (index == null) {
            index = 1;
        }
        int size = 3;
        List<Order> list = orderBiz.findOrderByState(index,size,state);
        int count = orderBiz.countOrderByState(state);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("index", index);
        map.put("state", state);
        map.put("count", count);
        return map;
    }
    @ApiOperation("根据id删除订单接口")
    @GetMapping(value = "/deleteOrder/{oid}")
    public boolean deleteProduct(@ApiParam("删除的参数") @PathVariable("oid") String oid) {
       Order order= orderBiz.findById(oid);
         String id=order.getDetail_id();
        orderDetailBiz.deleteDetail(id);
        return orderBiz.deleteOrder(oid);

    }
    @ApiOperation("根据用户名模糊查询订单")
    @GetMapping(value = "/findOrderMo/{index}/{username}")
    public Map<String, Object> findOrderMo(@ApiParam("模糊查询产品的参数") @PathVariable("index") Integer index,
                                             @PathVariable("username") String username) {
        if (index == null) {
            index = 1;
        }
        int size = 3;
        List<Order> list = orderBiz.findOrderMo(index,size,username);
        Map<String, Object> map = new HashMap<String, Object>();
        //调用查询总记录的方法
        int count =orderBiz.countMo(username);
        map.put("list", list);
        map.put("index", index);
        map.put("username", username);
        map.put("count", count);
        return map;

    }
    @ApiOperation("批量删除订单接口")
    @GetMapping(value="/delOrderAll/{ids}")
    public boolean delOrderAll(@ApiParam("删除的参数") @PathVariable("ids") String[] ids) {
        HashSet<String> set = new HashSet<String>();//利用Hashset数组去重
        for (String item:ids){
            if(set.add(item)){
                Order order= orderBiz.findById(item);
                String id=order.getDetail_id();
                orderDetailBiz.deleteDetail(id);
            }

        }
        return orderBiz.delOrderAll(ids);

    }
    @ApiOperation("根据id完成订单接口")
    @GetMapping(value = "/updateTwo/{oid}/{username}")
    public boolean updateTwo(@ApiParam("完成的参数") @PathVariable("oid") String oid,
                                 @PathVariable("username") String username,
                                 HttpServletRequest request) {
        //获取当前管理员用户
        String author=(String) request.getSession().getAttribute("username");
        Order order=new Order(oid,author);
        //根据传入的订单购买的用户者，查询该用户的资产id
        List<HashMap<String, Object>> list=userBiz.findByUsername1(username);
        String mid= (String) list.get(0).get("MONEYID");
        //再根据得到的资产id，查出用户资产表中的数据
        Money money = userBiz.findByMoneyid(mid);
        //用户总资产
        double Totalmoney=money.getTotalmoney();
        //用户支出
        double pay=money.getPay();
        //用户收入
        double income=money.getIncome();
        //用户余额
        double balance=money.getBalance();
        //根据id查询该条订单的总额和预期收益
        Order order1=orderBiz.findById(oid);
        double totalsum=order1.getTotalsum();
        double exincome=order1.getExincome();
        //修改当前订单的执行时间和执行管理员
        orderBiz.updateTwo(order);
        //修改当前订单状态
        orderBiz.updateSuccess(oid);
       double  recentTotalMoney=Totalmoney+exincome;
       double recentPay=pay+totalsum;
       double recentIncome=income+exincome;
       double recentBalance=balance+exincome;
       Money money1=new Money(mid,recentTotalMoney,recentIncome,recentPay,recentBalance);

       //根据计算好的新总资产，收入，支出，余额，修改用户资产表中数据
       boolean b= userBiz.updateMoney(money1);
        return b;

    }
    @ApiOperation("根据当前用户名查询订单接口")
    @GetMapping(value="/findOrderByUsername/{index}")
    public  Map<String, Object> findOrderByUsername(@ApiParam("分页的参数") @PathVariable("index") Integer index, HttpServletRequest request) {
       if(index==null){
           index=1;
       }
       int size=5;
        Map<String, Object> map = new HashMap<String, Object>();
        String username=(String) request.getSession().getAttribute("username");
        int count =orderBiz.countOrderByUsername(username);
        List<Order> list= orderBiz.findOrderByUsername(index,size,username);
        map.put("list",list);
        map.put("index",index);
        map.put("count",count);
        return map;

    }
    @ApiOperation("根据当前用户名且状态为已完成订单查询接口")
    @GetMapping(value="/findBySuccess")
    public  Map<String, Object> findBySuccess(HttpServletRequest request) {
        String username=(String) request.getSession().getAttribute("username");
        Map<String, Object> map = new HashMap<String, Object>();
        List<Order> list=orderBiz.findBySuccess(username);
        map.put("list",list);
        return map;

    }
}
