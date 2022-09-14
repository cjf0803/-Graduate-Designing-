package com.neu.user.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neu.user.entity.User;

/**
 * @author 陈锦房
 */
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
    public boolean addUser(User user);
    //模糊查询
    public List<User> findUserMo(Map<String,Object> map);

    //模糊查询总记录数
    public int countMo(Map<String,Object> map);
}
