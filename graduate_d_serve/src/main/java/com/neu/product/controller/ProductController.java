package com.neu.product.controller;

import com.neu.product.biz.ProductBiz;
import com.neu.product.entity.Manager;
import com.neu.product.entity.Product;
import com.neu.product.entity.State;
import com.neu.product.entity.Type;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cjf
 * @Date 2022/9/14
 **/
@RestController
@RequestMapping("product")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class ProductController {
    @Autowired
    private ProductBiz productBiz;
    @ApiOperation("产品信息接口")
    @GetMapping(value="/findProduct/{index}")
    public Map<String, Object> findUser(@ApiParam("分页的参数") @PathVariable("index") Integer index){
        if (index==null){
            index=1;
        }
        int size=3;
        List<Product> list =productBiz.findProduct(index,size);

        int count = productBiz.countProduct();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("index", index);
        map.put("count", count);
        return map;
    }
    @ApiOperation("根据id删除产品接口")
    @GetMapping(value="/deleteProduct/{id}")
    public boolean deleteProduct(@ApiParam("删除的参数") @PathVariable("id") String id) {
        System.out.println(id);
        return productBiz.deleteProduct(id);

    }
    @ApiOperation("根据id查询产品接口")
    @GetMapping(value="/findById/{id}")
    public List<HashMap<String, Object>> findById(@ApiParam("查询的参数") @PathVariable("id") String id) {
        return productBiz.findById(id);
    }

    @ApiOperation("修改产品接口")
    @GetMapping(value="/updateProduct/{id}/{pname}/{pimg}/{ptype}/{price}/{totalnum}/{averagerate}/{psize}/{pmanager}/{pdesc}")
    public boolean updateProduct(@ApiParam("修改的参数") @PathVariable("id") String id,
                              @PathVariable("pname") String pname,@PathVariable("pimg") String pimg,
                              @PathVariable("ptype") String ptype,
                              @PathVariable("price") String price,
                              @PathVariable("totalnum") String totalnum,@PathVariable("averagerate") String averagerate,
                              @PathVariable("psize") String psize,@PathVariable("pmanager") String pmanager,
                                 @PathVariable("pdesc") String pdesc) {

        Type type=new Type();
        type.setTid(ptype);
        Manager manager=new Manager();
        manager.setMid(pmanager);
        Product product=new Product(id,pname,pimg,type,price,totalnum,averagerate,psize,manager,pdesc);
        System.out.println(id);
        return productBiz.updateProduct(product);


    }
    @ApiOperation("根据id修改产品状态")
    @GetMapping(value="/updateState/{id}/{pstate}")
    public boolean updateState(@ApiParam("修改状态的参数") @PathVariable("id") String id,@PathVariable("pstate") Integer pstate){
        if(pstate==null){
            pstate=1;
        }
        State state =new State();
        state.setSid(pstate);
        Product product=new Product(id,state);
        return productBiz.updateState(product);
    }
    @ApiOperation("添加产品")
    @GetMapping(value="/addProduct/{pname}/{ptype}/{price}/{totalnum}/{averagerate}/{psize}/{pmanager}/{pdesc}")
    public boolean addProduct(@ApiParam("添加产品的参数") @PathVariable("pname") String pname,
                              @PathVariable("ptype") String ptype,
                              @PathVariable("price") String price,
                              @PathVariable("totalnum") String totalnum,
                              @PathVariable("averagerate") String averagerate,
                              @PathVariable("psize") String psize,
                              @PathVariable("pmanager") String pmanager,
                              @PathVariable("pdesc") String pdesc
                              ) {
        Type type=new Type();
        type.setTid(ptype);
        Manager manager=new Manager();
        manager.setMid(pmanager);
        productBiz.addRecentlyRate();
        Product product=new Product(pname,type,price,totalnum,averagerate,psize,manager,pdesc);
        return  productBiz.addProduct(product);
    }
    @ApiOperation("模糊查询用户")
    @GetMapping(value="/findProductMo/{index}/{pname}")
    public  Map<String,Object> findProductMo(@ApiParam("模糊查询产品的参数") @PathVariable("index") Integer index,
                                          @PathVariable("pname") String pname){
        if(index==null){
            index=1;
        }
        int size=3;
        List<Product> list=productBiz.findProductMo(index,size,pname);
        Map<String,Object> map=new HashMap<String,Object>();
        //调用查询总记录的方法
        int count=productBiz.countMo(pname);
        map.put("list",list);
        map.put("index",index);
        map.put("pname",pname);
        map.put("count",count);
        return  map;

    }

}
