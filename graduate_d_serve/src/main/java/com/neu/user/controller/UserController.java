package com.neu.user.controller;

import com.neu.user.biz.UserBiz;
import com.neu.user.entity.Money;
import com.neu.user.entity.Role;
import com.neu.user.entity.State;
import com.neu.user.entity.User;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class UserController {
    @Autowired
    private UserBiz userBiz;
    @ApiOperation("登录的接口")
    @GetMapping(value="/login/{username}/{password}")
    public List<HashMap<String, Object>> login(@ApiParam("用户名") @PathVariable("username") String username, @ApiParam("密码") @PathVariable("password") String password, HttpServletRequest request){
        List<HashMap<String, Object>> list = userBiz.login(username, password);
        if (list != null) {
            String userName = (String) list.get(0).get("USERNAME");
            request.getSession().setAttribute("username", userName);

        }
        return list;

    }
    @ApiOperation("登出的接口")
    @GetMapping(value = "/loginOut")
    public boolean loginOut(HttpSession session){
        boolean b = false;
        //清除session 中的用户
        session.removeAttribute("username");
        if(session.getAttribute("username")==null){
            b=true;
        }
        return  b;
    }
    @ApiOperation("注册的接口")
    @GetMapping(value = "/regist/{username}/{password}/{email}")
    public boolean regist(@ApiParam("用户名") @PathVariable("username") String username, @ApiParam("密码") @PathVariable("password") String password,@ApiParam("邮箱") @PathVariable("email") String email){
        User user = new User(username, password,email);
        userBiz.addMoney();
        return userBiz.regist(user);
    }
    @ApiOperation("用户信息接口")
    @GetMapping(value="/findUser/{index}")
    public Map<String, Object> findUser(@ApiParam("分页的参数") @PathVariable("index") Integer index){
        if (index==null){
            index=1;
        }
        int size=5;
        List<User> list = userBiz.findUser(index, size);

        int count = userBiz.countUser();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("index", index);
        map.put("count", count);
        return map;
    }
    @ApiOperation("根据id删除用户接口")
    @GetMapping(value="/deleteUser/{id}")
    public boolean deleteUser(@ApiParam("删除的参数") @PathVariable("id") String id) {
        System.out.println(id);
        return userBiz.deleteUser(id);

    }

    @ApiOperation("修改用户接口")
    @GetMapping(value="/updateUser/{id}/{username}/{password}/{img}/{nickname}/{sex}/{role_id}/{mobile}/{email}")
    public boolean updateUser(@ApiParam("修改的参数") @PathVariable("id") String id,
                              @PathVariable("username") String username,@PathVariable("password") String password,
                              @PathVariable("img") String img,
                              @PathVariable("nickname") String nickname,
                              @PathVariable("sex") String sex,@PathVariable("role_id") String role_id,
                              @PathVariable("mobile") String mobile,@PathVariable("email") String email) {

        Role role_id1 =new Role();
        role_id1.setRid(role_id);
        User user = new User(id,username,password,img,nickname,sex,role_id1,mobile,email);
        System.out.println(id);
        return userBiz.updateUser(user);


    }


    @ApiOperation("根据用户名修改用户接口")
    @GetMapping(value="/updateUserByUsername/{username}/{password}/{img}/{nickname}/{sex}/{mobile}/{email}")
    public boolean updateUserByUsername(@ApiParam("修改的参数")
                                        @PathVariable("username") String username,
                                        @PathVariable("password") String password,
                                        @PathVariable("img") String img,
                                        @PathVariable("nickname") String nickname,
                                        @PathVariable("sex") String sex,
                                        @PathVariable("mobile") String mobile,
                                        @PathVariable("email") String email) {
        User user = new User(username, password,img,nickname,sex,mobile,email);
        return userBiz.updateUserByUsername(user);

    }

    @ApiOperation("根据id查询用户接口")
    @GetMapping(value="/findById/{id}")
    public List<HashMap<String, Object>> findById(@ApiParam("查询的参数") @PathVariable("id") String id) {
        return userBiz.findById(id);
    }

    @ApiOperation("根据username查询用户接口")
    @GetMapping(value="/findByUsername")
    public User findByUsername(HttpServletRequest request) {
        User user = userBiz.findByUsername((String) request.getSession().getAttribute("username"));
        return user;
    }
    @ApiOperation("根据moneyid查询用户资产接口")
    @GetMapping(value="/findByMoneyid/{mid}")
    public Money findByMoneyid(@ApiParam("查询的参数") @PathVariable("mid") String mid) {
        return userBiz.findByMoneyid(mid);
    }
    @ApiOperation("根据id修改用户状态")
    @GetMapping(value="/updateState/{id}/{state}")
    public boolean updateState(@ApiParam("修改状态的参数") @PathVariable("id") String id,@PathVariable("state") Integer state){
        if(state==null){
            state=1;
        }
        State state1=new State();
        state1.setSid(state);
        User user=new User(id,state1);
        return userBiz.updateState(user);
    }

    @ApiOperation("添加用户")
    @GetMapping(value="/addUser/{username}/{role_id}/{state}")
    public boolean addUser(@ApiParam("添加用户的参数") @PathVariable("username") String username,
                           @PathVariable("role_id") String role_id,
                           @PathVariable("state") Integer state) {
        if(state==null){
            state=1;
        }
        State state2=new State();
        state2.setSid(state);
        Role role1=new Role();
        role1.setRid(role_id);
        userBiz.addMoney();
        User user = new User(username,role1,state2);
        return  userBiz.addUser(user);
    }
    @ApiOperation("模糊查询用户")
    @GetMapping(value="/findUserMo/{index}/{username}")
    public  Map<String,Object> findUserMo(@ApiParam("模糊查询用户的参数") @PathVariable("index") Integer index,
                                          @PathVariable("username") String username){
        if(index==null){
            index=1;
        }
        int size=3;
        List<User> list=userBiz.findUserMo(index,size,username);
        Map<String,Object> map=new HashMap<String,Object>();
        //调用查询总记录的方法
        int count=userBiz.countMo(username);
        map.put("list",list);
        map.put("index",index);
        map.put("username",username);
        map.put("count",count);
        return  map;

    }
    @ApiOperation("查询普通用户资产")
    @GetMapping(value="/findCustomer")
    public  Map<String,Object> findCustomer(){
        Map<String,Object> map=new HashMap<String,Object>();
        List<User> list= userBiz.findCustomer();
        map.put("list",list);
        return map;

    }
    @ApiOperation("根据用户资产id修改用户金额")
    @GetMapping(value="/updateMoney/{mid}/{money}")
    public  boolean updateMoney(@ApiParam("参数") @PathVariable("mid") String mid ,@PathVariable("money") double money){
        //再根据得到的资产id，查出用户资产表中的数据
        Money money1 = userBiz.findByMoneyid(mid);
        //用户总资产
        double Totalmoney=money1.getTotalmoney();
        //用户余额
        double balance=money1.getBalance();
        //用户支出
        double pay=money1.getPay();
        //用户收入
        double income=money1.getIncome();
        //添加完金额后的资产和余额
        double recentTotalmoney=Totalmoney+money;
        double recentbalance=balance+money;
        Money money2=new Money(mid,recentTotalmoney,income,pay,recentbalance);
        return userBiz.updateMoney(money2);
    }


}
