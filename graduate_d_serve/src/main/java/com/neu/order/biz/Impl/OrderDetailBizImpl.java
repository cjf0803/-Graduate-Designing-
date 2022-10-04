package com.neu.order.biz.Impl;

import com.neu.order.biz.OrderDetailBiz;
import com.neu.order.entity.Detail;
import com.neu.order.mapper.OrderDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cjf
 * @Date 2022/10/2
 **/
@Service("orderDetailBiz")
public class OrderDetailBizImpl implements OrderDetailBiz {
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Override
    public List<Detail> findDetailById(int index, int size,String id) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("index",index);
        map.put("size",size);
        map.put("id",id);
        List<Detail> list=orderDetailMapper.findDetailById(map);
        return list;
    }

    @Override
    public int countDetailById(String id) {
        return orderDetailMapper.countDetailById(id);
    }

    @Override
    public void deleteDetail(String id) {
         orderDetailMapper.deleteDetail(id);
    }

    @Override
    public List<Detail> findDetailByUsername(int index, int size, String username) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("index",index);
        map.put("size",size);
        map.put("username",username);
        List<Detail> list=orderDetailMapper.findDetailByUsername(map);
        return list;
    }

    @Override
    public int countDetailByUsername(String username) {
        return orderDetailMapper.countDetailByUsername(username);
    }
}
