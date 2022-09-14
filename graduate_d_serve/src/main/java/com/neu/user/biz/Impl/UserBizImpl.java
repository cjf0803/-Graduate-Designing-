package com.neu.user.biz.Impl;

import com.neu.user.biz.UserBiz;
import com.neu.user.entity.User;
import com.neu.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cjf
 * @Date 2022/9/7
 **/
@Service("userBiz")
public class UserBizImpl implements UserBiz {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<HashMap<String, Object>> login(String username, String password) {
        return userMapper.login(username, password);
    }

    @Override
    public boolean regist(User user) {
        return userMapper.regist(user);
    }

    @Override
    public List<User> findUser(int index, int size) {
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("index",index);
        map.put("size",size);
        List<User>list =userMapper.findUser(map);
        return list;
    }

    @Override
    public int countUser() {
        return userMapper.countUser();
    }

    @Override
    public boolean deleteUser(String id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public List<HashMap<String, Object>> findById(String id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public boolean updateUserByUsername(User user) {
        return userMapper.updateUserByUsername(user);
    }

    @Override
    public boolean updateState(User user) {
        return userMapper.updateState(user);
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public List<User> findUserMo(int index, int size, String username) {
        Map<String,Object> map=new HashMap<String,Object>();
        //将参数传入map
        map.put("index",index);
        map.put("size",size);
        map.put("username",username);
        List<User> list=userMapper.findUserMo(map);

        return list;
    }

    @Override
    public int countMo(String username) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("username",username);
        return  userMapper.countMo(map);
    }

    @Override
    public void addMoney() {
         userMapper.addMoney();
    }

    @Override
    public boolean updateRoleById(String rid) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("rid",rid);
        return userMapper.updateRoleById(map);
    }
}
