package com.neu.product.mapper;

import com.neu.product.entity.RecentlyRate;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author cjf
 * @Date 2022/9/15
 **/
@Mapper
public interface RecentlyRateMapper {
    public List<RecentlyRate> findAll(Map<String,Object> map);
    public int count();
    public boolean delete(String rid);
    public boolean update(RecentlyRate recentlyRate);
}
