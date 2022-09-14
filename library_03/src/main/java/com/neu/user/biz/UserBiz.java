package com.neu.user.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neu.user.entity.User;

/**
 * @author 陈锦房
 */
public interface UserBiz {
	public List<HashMap<String, Object>>  login( String username ,String password);
	public boolean regist(User user);

    public List<User> findUser(int index, int size);
    

    public int countUser();
    
    public boolean deleteUser(String id);
    public boolean updateUser(User user);
    public List<HashMap<String, Object>> findById(String id);
    public User findByUsername(String username);
    
    public boolean updateUserByUsername(User user);
    public boolean addUser(User user);
    public List<User> findUserMo(int index ,int size, String username);
    public int countMo(String username);
}
