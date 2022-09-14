package com.neu.user.biz.Impl;

import com.neu.user.biz.UserRoleBiz;
import com.neu.user.entity.Role;
import com.neu.user.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cjf
 * @Date 2022/9/10
 **/
@Service("userRoleBiz")
public class UserRoleBizImpl implements UserRoleBiz {
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public List<Role> findRole(int index, int size) {
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("index",index);
        map.put("size",size);
        List<Role> list =userRoleMapper.findRole(map);
        return list;
    }

    @Override
    public int countRole() {
        return userRoleMapper.countRole();
    }

    @Override
    public boolean deleteRole(String rid) {
        return userRoleMapper.deleteRole(rid);
    }

    @Override
    public boolean updateRole(Role role) {
        return userRoleMapper.updateRole(role);
    }

    @Override
    public Role findById(String rid) {
        return userRoleMapper.findById(rid);
    }

    @Override
    public boolean addRole(Role role) {
        return userRoleMapper.addRole(role);
    }
}
