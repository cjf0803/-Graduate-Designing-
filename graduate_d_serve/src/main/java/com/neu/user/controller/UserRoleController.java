package com.neu.user.controller;

import com.neu.user.biz.UserBiz;
import com.neu.user.biz.UserRoleBiz;
import com.neu.user.entity.Role;
import com.neu.user.entity.State;
import com.neu.user.entity.User;
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
 * @Date 2022/9/10
 **/
@RestController
@RequestMapping("userRole")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class UserRoleController {
    @Autowired
    private UserRoleBiz userRoleBiz;
    @Autowired
    private UserBiz userBiz;
    @ApiOperation("所有角色信息接口")
    @GetMapping(value="/findRole/{index}")
    public Map<String, Object> findRole(@ApiParam("分页的参数") @PathVariable("index") Integer index){
        if (index==null){
            index=1;
        }
        int size=3;
        List<Role> list = userRoleBiz.findRole(index,size);

        int count = userRoleBiz.countRole();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("index", index);
        map.put("count", count);
        return map;
    }
    @ApiOperation("根据id删除角色接口")
    @GetMapping(value="/deleteRole/{rid}")
    public boolean deleteRole(@ApiParam("删除的参数") @PathVariable("rid") String rid) {
        System.out.println(rid);
        boolean c;
       boolean a= userRoleBiz.deleteRole(rid);
       boolean b= userBiz.updateRoleById(rid);
            if(a&&b){
                c=true;
            }else{
                c=false;
            }
            return  c;
    }
    @ApiOperation("根据id查询角色接口")
    @GetMapping(value="/findById/{rid}")
    public Role findById(@ApiParam("查询的参数") @PathVariable("rid") String rid) {
        return userRoleBiz.findById(rid);
    }

    @ApiOperation("修改角色接口")
    @GetMapping(value="/updateRole/{rid}/{rname}/{rdesc}")
    public boolean updateRole(@ApiParam("修改的参数") @PathVariable("rid") String rid,
                              @PathVariable("rname") String rname,
                              @PathVariable("rdesc") String rdesc,
                              HttpServletRequest request
                              ) {
        String rauthor=(String) request.getSession().getAttribute("username");
        Role role=new Role(rid,rname,rdesc,rauthor);
        System.out.println(rid);
        return userRoleBiz.updateRole(role);


    }
    @ApiOperation("添加角色接口")
    @GetMapping(value="/addRole/{rname}/{rdesc}")
    public boolean addUser(@ApiParam("添加角色的参数") @PathVariable("rname") String rname,
                           @PathVariable("rdesc") String rdesc,
                           HttpServletRequest request
                           ) {
        String rauthor=(String) request.getSession().getAttribute("username");
        Role role=new Role(rname,rdesc,rauthor);
        return  userRoleBiz.addRole(role);
    }

}
