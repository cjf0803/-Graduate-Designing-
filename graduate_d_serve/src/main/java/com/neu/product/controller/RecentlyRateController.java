package com.neu.product.controller;

import com.neu.product.biz.RecentlyRateBiz;

import com.neu.product.entity.RecentlyRate;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cjf
 * @Date 2022/9/15
 **/
@RestController
@RequestMapping("recentlyRate")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class RecentlyRateController {
    @Autowired
    private RecentlyRateBiz recentlyRateBiz;
    @ApiOperation("近期收益率信息接口")
    @GetMapping(value="/findAll/{index}")
    public Map<String, Object> findAll(@ApiParam("分页的参数") @PathVariable("index") Integer index){
        if (index==null){
            index=1;
        }
        int size=5;
        List<RecentlyRate> list =recentlyRateBiz.findAll(index,size);

        int count = recentlyRateBiz.count();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("index", index);
        map.put("count", count);
        return map;
    }
    @ApiOperation("根据id删除近期收益率接口")
    @GetMapping(value="/delete/{rid}")
    public boolean deleteProduct(@ApiParam("删除的参数") @PathVariable("rid") String rid) {
        System.out.println(rid);
        return recentlyRateBiz.delete(rid);

    }
    @ApiOperation("修改近期收益率接口")
    @GetMapping(value="/update/{rid}/{jan}/{mar}/{may}/{july}/{sep}/{nov}")
    public boolean update(@ApiParam("修改的参数") @PathVariable("rid") String rid,
                                 @PathVariable("jan") String jan,@PathVariable("mar") String mar,
                                 @PathVariable("may") String may,
                                 @PathVariable("july") String july,
                                 @PathVariable("sep") String sep,@PathVariable("nov") String nov) {

       RecentlyRate recentlyRate=new RecentlyRate(rid,jan,mar,may,july,sep,nov);
        return recentlyRateBiz.update(recentlyRate);


    }
}
