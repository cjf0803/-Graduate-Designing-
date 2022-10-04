package com.neu.order.controller;

import com.neu.order.biz.OrderDetailBiz;
import com.neu.order.entity.Detail;
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
 * @Date 2022/10/2
 **/
@RestController
@RequestMapping("orderDetail")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class OrderDetailController {
    @Autowired
    private OrderDetailBiz orderDetailBiz;
    @ApiOperation("根据订单id查询接口")
    @GetMapping(value = "/findDetailById/{index}/{id}")
    public Map<String, Object> findDetailById(@ApiParam("分页的参数") @PathVariable("index") Integer index,@PathVariable("id") String id) {
        if (index == null) {
            index = 1;
        }
        int size = 3;
        List<Detail> list = orderDetailBiz.findDetailById(index,size,id);

        int count = orderDetailBiz.countDetailById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("index", index);
        map.put("count", count);
        return map;
    }
    @ApiOperation("根据下单者查询接口(去重)")
    @GetMapping(value = "/findDetailByUsername/{index}")
    public Map<String, Object> findDetailByUsername(@ApiParam("分页的参数") @PathVariable("index") Integer index, HttpServletRequest request) {
        if (index == null) {
            index = 1;
        }
        int size = 10;
        String username=(String) request.getSession().getAttribute("username");
        List<Detail> list = orderDetailBiz.findDetailByUsername(index,size,username);

        int count = orderDetailBiz.countDetailByUsername(username);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("index", index);
        map.put("count", count);
        return map;
    }

}
