package com.neu.order.biz;

import com.neu.order.entity.Detail;

import java.util.List;


/**
 * @Author cjf
 * @Date 2022/10/2
 **/
public interface OrderDetailBiz {
    public List<Detail> findDetailById(int index,int size,String id);
    public int countDetailById(String id);
    public void deleteDetail(String id);
    public List<Detail> findDetailByUsername(int index,int size,String username);
    public int countDetailByUsername(String username);

}
