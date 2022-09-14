package com.neu.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.neu.depart.entity.Depart;
import com.neu.status.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.neu.user.biz.UserBiz;
import com.neu.user.entity.User;


/**
 * @author 陈锦房
 */
@Controller
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class UserController {
    @Autowired
    private UserBiz userBiz;

    @RequestMapping(value = "/login")
    @ResponseBody
    public List<HashMap<String, Object>>  login(String username, String password, HttpServletRequest request) {
        List<HashMap<String, Object>> list = userBiz.login(username, password);
        if (list != null) {
            String userName = (String) list.get(0).get("USERNAME");
            request.getSession().setAttribute("username", userName);


        }
        return list;
    }
    @RequestMapping(value = "/loginOut")
    @ResponseBody
    public boolean loginOut(HttpSession session){
        boolean b = false;
        //清除session 中的用户
        session.removeAttribute("username");
        if(session.getAttribute("username")==null){
            b=true;
        }
        return  b;
    }

    @RequestMapping(value = "/regist")
    @ResponseBody
    public boolean regist(String username, String password, int status) {
        Status status1 = new Status();
        status1.setStatus_id(status);
        User user = new User(username, password, status1);
        return userBiz.regist(user);
    }

    @RequestMapping(value = "/findUser")
    @ResponseBody
    public Map<String, Object> findUser(Integer index) {
        if (index == null) {
            index = 1;
        }
        int size = 3;

        List<User> list = userBiz.findUser(index, size);

        int count = userBiz.countUser();
        Map<String, Object> map = new HashMap<String, Object>();


        map.put("list", list);
        map.put("index", index);
        map.put("count", count);
        return map;

    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public boolean deleteUser(String id) {
        System.out.println(id);
        return userBiz.deleteUser(id);

    }


    @RequestMapping("/updateUser")
    @ResponseBody
    public boolean updateUser(String id, String username, String password, String realname, Integer status, String departid) {
        if (status == null) {
            status = 2;
        }
        Depart depart = new Depart();
        depart.setDepartid(departid);
        Status status2 = new Status();
        status2.setStatus_id(status);
        User user = new User(id, username, password, realname, status2, depart);
        System.out.println(id);
        return userBiz.updateUser(user);


    }


    @RequestMapping(value="/updateUserByUsername")
    @ResponseBody
    public boolean updateUserByUsername(String username, String password, String realname) {
        User user = new User(username, password, realname);

        return userBiz.updateUserByUsername(user);

    }

    @RequestMapping("/findById")
    @ResponseBody
    public List<HashMap<String, Object>> findById(String id) {
        return userBiz.findById(id);
    }

    @RequestMapping("/findByUsername")
    @ResponseBody
    public User findByUsername(HttpServletRequest request) {
        User user = userBiz.findByUsername((String) request.getSession().getAttribute("username"));
        return user;
    }
    @RequestMapping(value="/addUser")
    @ResponseBody
    public boolean addUser( String username, String password, String realname, Integer status, String departid) {
        if (status == null) {
            status = 2;
        }
        Depart depart = new Depart();
        depart.setDepartid(departid);
        Status status2 = new Status();
        status2.setStatus_id(status);
        User user = new User(username, password, realname, status2, depart);
        return  userBiz.addUser(user);
    }
    @RequestMapping(value = "/findUserMo")
    @ResponseBody
    public  Map<String,Object> findUserMo(Integer index,String username){
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
        map.put("uname",username);
        map.put("count",count);
        return  map;

    }
}




	

