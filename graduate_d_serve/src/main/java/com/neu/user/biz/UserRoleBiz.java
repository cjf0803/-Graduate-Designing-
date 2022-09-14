package com.neu.user.biz;

import com.neu.user.entity.Role;

import java.util.List;

/**
 * @Author cjf
 * @Date 2022/9/10
 **/
public interface UserRoleBiz {
    public List<Role> findRole(int index,int size);
    public int countRole();
    public boolean deleteRole(String rid);
    public boolean updateRole(Role role);
    public  Role findById(String rid);
    public boolean addRole(Role role);
}
