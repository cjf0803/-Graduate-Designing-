package com.neu.product.biz;

import com.neu.product.entity.RecentlyRate;
import com.neu.user.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @Author cjf
 * @Date 2022/9/15
 **/
public interface RecentlyRateBiz {
    public List<RecentlyRate> findAll(int index,int size);
    public int count();
    public boolean delete(String rid);
    public boolean update(RecentlyRate recentlyRate);
}
