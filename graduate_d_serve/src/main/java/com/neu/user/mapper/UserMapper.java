package com.neu.user.mapper;

import com.neu.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cjf
 * @Date 2022/9/6
 **/
@Mapper
public interface UserMapper {
    public List<HashMap<String, Object>> login(@Param("username") String username, @Param("password") String password);
    public boolean regist(User user);
    public List<User> findUser(Map<String, Object> map);
    public int countUser();
    public boolean deleteUser(String id);
    public boolean updateUser(User user);
    public  List<HashMap<String, Object>> findById(String id);
    public User findByUsername(String username);
    public boolean updateUserByUsername(User user);
    public boolean updateState(User user);
    public boolean addUser(User user);
    public List<User> findUserMo(Map<String,Object> map);
    public int countMo(Map<String,Object> map);
    public void addMoney();
    public boolean updateRoleById(Map<String, Object> map);

}
