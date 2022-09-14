package com.neu.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Date;

/**
 * @Author cjf
 * @Date 2022/9/6
 **/
@ApiModel("用户的实体类")
public class User {
    @ApiModelProperty("用户的id")
    private String  id;
    @ApiModelProperty("用户的用户名")
    private String username;
    @ApiModelProperty("用户的密码")
    private String password;
    @ApiModelProperty("用户的头像")
    private  String img;
    @ApiModelProperty("用户的昵称")
    private String nickname;
    @ApiModelProperty("用户的性别")
    private  String sex;
    @ApiModelProperty("用户创建日期")
    private Date createtime;
    @ApiModelProperty("用户的角色")
    private Role role_id=new Role();
    @ApiModelProperty("用户的状态")
    private  State state=new State();
    @ApiModelProperty("用户的手机号")
    private String mobile;
    @ApiModelProperty("用户的邮箱")
    private  String email;
    @ApiModelProperty("用户的资产")
    private  Money moneyid=new Money();


    public User() {
    }

    public User(String id, State state) {
        this.id = id;
        this.state = state;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, Role role_id, State state) {
        this.username = username;
        this.role_id = role_id;
        this.state = state;
    }

    public User(String username, String password, String img, String nickname, String sex, String mobile, String email) {
        this.username = username;
        this.password = password;
        this.img = img;
        this.nickname = nickname;
        this.sex = sex;
        this.mobile = mobile;
        this.email = email;
    }

    public User(String id, String username, String password, String img, String nickname, String sex, Role role_id, String mobile, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.img = img;
        this.nickname = nickname;
        this.sex = sex;
        this.role_id = role_id;
        this.mobile = mobile;
        this.email = email;
    }

    public User(String id, String username, String password, String img, String nickname, String sex, Date createtime, Role role_id, State state, String mobile, String email, Money moneyid) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.img = img;
        this.nickname = nickname;
        this.sex = sex;
        this.createtime = createtime;
        this.role_id = role_id;
        this.state = state;
        this.mobile = mobile;
        this.email = email;
        this.moneyid = moneyid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Role getRole_id() {
        return role_id;
    }

    public void setRole_id(Role role_id) {
        this.role_id = role_id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Money getMoneyid() {
        return moneyid;
    }

    public void setMoneyid(Money moneyid) {
        this.moneyid = moneyid;
    }
}
