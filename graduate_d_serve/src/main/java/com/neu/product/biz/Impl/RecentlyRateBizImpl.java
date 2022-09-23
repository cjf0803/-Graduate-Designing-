package com.neu.product.biz.Impl;

import com.neu.product.biz.RecentlyRateBiz;
import com.neu.product.entity.RecentlyRate;
import com.neu.product.mapper.RecentlyRateMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cjf
 * @Date 2022/9/15
 **/
@Service("recentlyRateBiz")
public class RecentlyRateBizImpl implements RecentlyRateBiz {
    @Autowired
    private RecentlyRateMapper recentlyRateMapper;
    @Override

    public List<RecentlyRate> findAll(int index, int size) {
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("index",index);
        map.put("size",size);
        List<RecentlyRate> list =recentlyRateMapper.findAll(map);
        return list;
    }

    @Override
    public int count() {
        return recentlyRateMapper.count();
    }

    @Override
    public boolean delete(String rid) {
        return recentlyRateMapper.delete(rid);
    }

    @Override
    public boolean update(RecentlyRate recentlyRate) {
        return recentlyRateMapper.update(recentlyRate);
    }

}
