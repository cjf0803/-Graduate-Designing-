package com.neu.user.biz;

import com.neu.user.entity.Money;
import com.neu.user.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cjf
 * @Date 2022/9/7
 **/
public interface UserBiz {
    public List<HashMap<String, Object>> login(String username , String password);
    public boolean regist(User user);
    public List<User> findUser(int index, int size);
    public int countUser();
    public boolean deleteUser(String id);
    public boolean updateUser(User user);
    public List<HashMap<String, Object>> findById(String id);
    public  List<HashMap<String, Object>> findByUsername1(String username);
    public User findByUsername(String username);
    public boolean updateUserByUsername(User user);
    public boolean updateState(User user);
    public boolean addUser(User user);
    public List<User> findUserMo(int index ,int size, String username);
    public int countMo(String username);
    public void addMoney();
    public boolean updateRoleById(String rid);
    public Money findByMoneyid(String mid);
    public boolean updateMoney(Money money);
    public List<User> findCustomer();
}
