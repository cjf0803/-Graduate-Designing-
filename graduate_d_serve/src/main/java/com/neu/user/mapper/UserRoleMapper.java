package com.neu.user.mapper;

import com.neu.user.entity.Role;

import org.apache.ibatis.annotations.Mapper;


import java.util.List;
import java.util.Map;

/**
 * @Author cjf
 * @Date 2022/9/10
 **/
@Mapper
public interface UserRoleMapper {
    public List<Role> findRole(Map<String,Object> map);
    public int countRole();
    public boolean deleteRole(String rid);
    public boolean updateRole(Role role);
    public  Role findById(String rid);
    public boolean addRole(Role role);
}
