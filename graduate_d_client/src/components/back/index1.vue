<template>
  <el-container class="index-container">
    <!--头部-->
    <el-header style="height: 100px">
      <div class="head-logo">
        <img src="@/assets/img/log.svg" />
        <span class="font">吾爱理财后台系统</span>
      </div>
      <div class="head-button">
        <el-image
          :src="require('@/assets/img/user/' + userData.img)"
          class="headimg"
        ></el-image>

        <p
          style="
            float: left;
            margin: 23px -24px 0px;
            font-family: fantasy;
            font-size: large;
          "
        >
          {{ username }}
        </p>
        <el-button type="success" @click="updateUser" icon="el-icon-tickets"
          >信息修改</el-button
        >
        <el-button type="danger" @click="loginOut" icon="el-icon-switch-button"
          >退出</el-button
        >
      </div>
      <!-- 编辑弹窗(修改个人信息)  rules有问题！(已解决)-->
      <el-dialog title="修改个人信息" :visible.sync="dialogFormVisible">
        <el-form :model="userData" :rules="rules" ref="userData">
          <el-form-item
            label="用户名"
            prop="username"
            :label-width="formLabelWidth"
          >
            <el-input
              v-model="userData.username"
              @input="change($event)"
              autocomplete="off"
              clearable
              readonly="true"
            ></el-input>
          </el-form-item>
          <el-form-item
            label="密码"
            prop="password"
            :label-width="formLabelWidth"
          >
            <el-input
              v-model="userData.password"
              @input="change($event)"
              autocomplete="off"
              show-password
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item
            label="昵称"
            prop="nickname"
            :label-width="formLabelWidth"
          >
            <el-input
              v-model="userData.nickname"
              @input="change($event)"
              autocomplete="off"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="sex" :label-width="formLabelWidth">
            <el-input
              v-model="userData.sex"
              @input="change($event)"
              autocomplete="off"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item
            label="联系方式"
            prop="mobile"
            :label-width="formLabelWidth"
          >
            <el-input
              v-model="userData.mobile"
              @input="change($event)"
              autocomplete="off"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email" :label-width="formLabelWidth">
            <el-input
              v-model="userData.email"
              @input="change($event)"
              autocomplete="off"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item class="userimg" prop="img">
            <el-upload
              class="upload-demo"
              action="http://localhost:8081/user/upload"
              :file-list="fileList"
              list-type="picture"
              :on-success="handleAvatarSuccess"
            >
              <el-button size="small" type="primary" class="upload"
                >点击上传</el-button
              >
              <div slot="tip" class="el-upload__tip">
                只能上传jpg/png文件，且不超过10MB
              </div>
            </el-upload>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="cancel">取 消</el-button>
          <el-button type="primary" @click="submitForm('userData')"
            >确 定</el-button
          >
        </div>
      </el-dialog>
    </el-header>

    <!--页面主体区域-->
    <el-container>
      <!--页面左侧区域-->
      <el-aside width="200px">
        <!--侧边栏菜单区域-->
        <el-menu
          background-color="#454b5d"
          text-color="#fff"
          active-text-color="#409eff"
          :router="true"
          ><!--二级菜单颜色,unique-opened菜单只打开一个.:router="true":开启菜单路由模式-->
          <!--一级菜单-->

          <el-menu-item index="home">
            <!--一级菜单模板区-->
            <template slot="title">
              <!--图标-->
              <i class="el-icon-s-home"></i>
              <!--文本-->
              <span style="margin-right: 27px">首页</span>
            </template>
          </el-menu-item>
        </el-menu>

        <el-menu
          background-color="#454b5d"
          text-color="#fff"
          active-text-color="#409eff"
          :router="true"
          ><!--二级菜单颜色,unique-opened菜单只打开一个.:router="true":开启菜单路由模式-->
          <!--一级菜单-->

          <el-submenu index="1">
            <!--一级菜单模板区-->
            <template slot="title">
              <!--图标-->
              <i class="el-icon-user"></i>
              <!--文本-->
              <span>用户管理</span>
            </template>
            <!--二级菜单-->
            <!--index对应要跳转到的视图-->
            <el-menu-item index="index">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-menu"></i
                  ><!--二级菜单图标-->
                  <!--文本-->
                  <span>用户列表</span>
                </div>
              </template>
            </el-menu-item>
            <el-tooltip
              effect="dark"
              :disabled="(disabled = currentRole >= 4 ? true : false)"
              content="权限不足,不能角色管理"
              placement="left"
              transition
            >
              <el-menu-item
                index="role"
                :disabled="currentRole >= 4 ? false : true"
              >
                <template slot="title">
                  <!--图标-->
                  <div class="space">
                    <i class="el-icon-guide"></i
                    ><!--二级菜单图标-->
                    <!--文本-->
                    <span>角色管理</span>
                  </div>
                </template>
              </el-menu-item>
            </el-tooltip>
            <el-tooltip
              effect="dark"
              :disabled="(disabled = currentRole >= 4 ? true : false)"
              content="权限不足,不能添加"
              placement="left"
              transition
            >
              <el-menu-item
                index="add"
                :disabled="currentRole >= 4 ? false : true"
              >
                <template slot="title">
                  <!--图标-->
                  <div class="space">
                    <i class="el-icon-circle-plus-outline"></i
                    ><!--二级菜单图标-->
                    <!--文本-->
                    <span>添加用户</span>
                  </div>
                </template>
              </el-menu-item>
            </el-tooltip>
            <el-menu-item index="findUserMo">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-s-operation"></i
                  ><!--二级菜单图标-->
                  <!--文本-->
                  <span>模糊查询</span>
                </div>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>

        <!--侧边栏菜单区域-->
        <el-menu
          background-color="#454b5d"
          text-color="#fff"
          active-text-color="#409eff"
          :router="true"
        >
          <!--一级菜单-->
          <el-submenu index="1">
            <!--一级菜单模板区-->
            <template slot="title">
              <!--图标-->
              <i class="el-icon-reading"></i>
              <!--文本-->
              <span>理财管理</span>
            </template>
            <!--二级菜单-->
            <el-menu-item index="bookIndex">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-set-up"></i>
                  <!--文本-->
                  <span>总览</span>
                </div>
              </template>
            </el-menu-item>
            <el-menu-item index="addBook">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-document-add"></i>
                  <!--文本-->
                  <span>股票</span>
                </div>
              </template>
            </el-menu-item>
            <el-menu-item index="findBookMo">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-loading"></i>
                  <!--文本-->
                  <span>基金</span>
                </div>
              </template>
            </el-menu-item>
            <el-menu-item index="zhaiquan">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-view"></i>
                  <!--文本-->
                  <span>债券</span>
                </div>
              </template>
            </el-menu-item>
            <el-menu-item index="bank">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-c-scale-to-original"></i>
                  <!--文本-->
                  <span>银行</span>
                </div>
              </template>
            </el-menu-item>
            <el-menu-item index="baoxian">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-paperclip"></i>
                  <!--文本-->
                  <span>保险</span>
                </div>
              </template>
            </el-menu-item>
            <el-menu-item index="gold">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-search"></i>
                  <!--文本-->
                  <span>黄金</span>
                </div>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>

        <!--侧边栏菜单区域-->
        <el-menu
          background-color="#454b5d"
          text-color="#fff"
          active-text-color="#409eff"
          :router="true"
        >
          <!--一级菜单-->
          <el-submenu index="1">
            <!--一级菜单模板区-->
            <template slot="title">
              <!--图标-->
              <i class="el-icon-truck"></i>
              <!--文本-->
              <span>订单管理</span>
            </template>
            <!--二级菜单-->
            <el-menu-item index="lendIndex">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-set-up"></i>
                  <!--文本-->
                  <span>总览</span>
                </div>
              </template>
            </el-menu-item>

            <el-menu-item index="findLendMo">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-lollipop"></i>
                  <!--文本-->
                  <span>已完成订单</span>
                </div>
              </template>
            </el-menu-item>
            <el-menu-item index="cancel">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-lollipop"></i>
                  <!--文本-->
                  <span>已取消订单</span>
                </div>
              </template>
            </el-menu-item>
            <el-menu-item index="pay">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-lollipop"></i>
                  <!--文本-->
                  <span>已支付订单</span>
                </div>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>

        <!--侧边栏菜单区域-->
        <el-menu
          background-color="#454b5d"
          text-color="#fff"
          active-text-color="#409eff"
        >
          <!--一级菜单-->
          <el-submenu index="1">
            <!--一级菜单模板区-->
            <template slot="title">
              <!--图标-->
              <i class="el-icon-shopping-cart-full"></i>
              <!--文本-->
              <span>公告管理</span>
            </template>
            <!--二级菜单-->
            <el-menu-item index="1-4-1">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-menu"></i>
                  <!--文本-->
                  <span>公告</span>
                </div>
              </template>
            </el-menu-item>
            <el-menu-item index="1-4-1">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-menu"></i>
                  <!--文本-->
                  <span>导航二</span>
                </div>
              </template>
            </el-menu-item>
            <el-menu-item index="1-4-1">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-menu"></i>
                  <!--文本-->
                  <span>导航三</span>
                </div>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
        <!--侧边栏菜单区域-->
        <el-menu
          background-color="#454b5d"
          text-color="#fff"
          active-text-color="#409eff"
        >
          <!--一级菜单-->
          <el-submenu index="1">
            <!--一级菜单模板区-->
            <template slot="title">
              <!--图标-->
              <i class="el-icon-data-analysis"></i>
              <!--文本-->
              <span>数据统计</span>
            </template>
            <!--二级菜单-->
            <el-menu-item index="1-4-1">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-menu"></i>
                  <!--文本-->
                  <span>导航一</span>
                </div>
              </template>
            </el-menu-item>
            <el-menu-item index="1-4-1">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-menu"></i>
                  <!--文本-->
                  <span>导航二</span>
                </div>
              </template>
            </el-menu-item>
            <el-menu-item index="1-4-1">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-menu"></i>
                  <!--文本-->
                  <span>导航三</span>
                </div>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
        <el-menu
          background-color="#454b5d"
          text-color="#fff"
          active-text-color="#409eff"
        >
          <!--一级菜单-->
          <el-submenu index="1">
            <!--一级菜单模板区-->
            <template slot="title">
              <!--图标-->
              <i class="el-icon-coin"></i>
              <!--文本-->
              <span>流水管理</span>
            </template>
            <!--二级菜单-->
            <el-menu-item index="1-4-1">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-menu"></i>
                  <!--文本-->
                  <span>导航一</span>
                </div>
              </template>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="1">
            <!--一级菜单模板区-->
            <template slot="title">
              <!--图标-->
              <i class="el-icon-news"></i>
              <!--文本-->
              <span>目标管理</span>
            </template>
            <!--二级菜单-->
            <el-menu-item index="1-4-1">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-menu"></i>
                  <!--文本-->
                  <span>导航一</span>
                </div>
              </template>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="1">
            <!--一级菜单模板区-->
            <template slot="title">
              <!--图标-->
              <i class="el-icon-chat-dot-round"></i>
              <!--文本-->
              <span>意见管理</span>
            </template>
            <!--二级菜单-->
            <el-menu-item index="1-4-1">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-menu"></i>
                  <!--文本-->
                  <span>导航一</span>
                </div>
              </template>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="1">
            <!--一级菜单模板区-->
            <template slot="title">
              <!--图标-->
              <i class="el-icon-place"></i>
              <!--文本-->
              <span>系统管理</span>
            </template>
            <!--二级菜单-->
            <el-menu-item index="1-4-1">
              <template slot="title">
                <!--图标-->
                <div class="space">
                  <i class="el-icon-menu"></i>
                  <!--文本-->
                  <span>导航一</span>
                </div>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>

      <!--页面右侧区域-->
      <el-main>
        <!--路由的占位符-->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>

export default {
  name: "index1_v",
  data() {
    return {
      rules: {
        password: [
          { required: true, message: "此项为必填项", trigger: "blur" },
        ],
        nickname: [
          { required: true, message: "此项为必填项", trigger: "blur" },
        ],
        sex: [{ required: true, message: "此项为必填项", trigger: "blur" }],
        mobile: [
          {
            required: false,
            pattern: /^1(2|3|4|5|6|7|8|9)\d{9}$/,
            message: "请填写正确手机号",
            trigger: "blur",
          },
        ],
        email: [
          {
            required: false,
            pattern:
              /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
            message: "请填写正确邮箱",
            trigger: "blur",
          },
        ],
      },
      username: "",
      dialogFormVisible: false,
      currentRole: "",
      formLabelWidth: "120px",
      fileList: [
        {
          name: "点击上传进行修改头像",
          url: "",
        },
      ],
      userData: {
        username: "",
        password: "",
        img: "",
        nickname: "",
        sex: "",
        mobile: "",
        email: "",
      },
    };
  },
  mounted() {
    this.username = this.$store.getters["uInfo/getUserInfo"].USERNAME;
    this.currentRole = this.$store.getters["uInfo/getUserInfo"].ROLE_ID;
    this.$http.get("http://localhost:8081/user/findByUsername").then((res) => {
      if (res) {
        this.userData.username = res.data.username;
        this.userData.password = res.data.password;
        this.userData.img = res.data.img;
        this.userData.nickname = res.data.nickname;
        this.userData.sex = res.data.sex;
        this.userData.mobile = res.data.mobile;
        this.userData.email = res.data.email;
      }
    });
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.userData.img = res; //从后台获取filename给img赋值
      this.fileList.name = res;
      this.fileList.url = URL.createObjectURL(file.raw);
    },
    loginOut: function () {
      this.$confirm("退出当前用户, 是否退出?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http
            .get(
              "http://localhost:8081/user/loginOut",
              { xhrFields: { withCredentials: true } },
              { crossDomain: true }
            )
            .then((res) => {
              console.log(res);
              localStorage.removeItem("loginData");
              this.$store.commit("setUserInfo", {});
              if (res.data == true) {
                this.$message({
                  type: "success",
                  message: "退出成功!",
                });
                this.$router.replace({ path: "/login" });
              }
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消操作!",
          });
        });
    },
    updateUser: function () {
      this.dialogFormVisible = true;
    },
    cancel: function () {
      this.dialogFormVisible = false;
      setTimeout(() => {
        location.reload();
      }, 10);
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm("是否修改？", "tips", {
            confirmButtonText: "OK",
            cancelButtonText: "Cancel",
            type: "warning",
          })
            .then(() => {
              let username = this.userData.username;
              let password = this.userData.password;
              let img = this.userData.img;
              let nickname = this.userData.nickname;
              let sex = this.userData.sex;
              let mobile = this.userData.mobile;
              let email = this.userData.email;
              this.$http
                .get(
                  "http://localhost:8081/user/updateUserByUsername/" +
                    username +
                    "/" +
                    password +
                    "/" +
                    img +
                    "/" +
                    nickname +
                    "/" +
                    sex +
                    "/" +
                    mobile +
                    "/" +
                    email,
                  { xhrFields: { withCredentials: true } },
                  { crossDomain: true }
                )
                .then((req) => {
                  if (req.data != null) {
                    this.dialogFormEVisible = false;
                    this.$message({
                      showClose: true,
                      message: "修改成功",
                      type: "success",
                    });
                    setTimeout(() => {
                      location.reload();
                    }, 333);
                  }
                });
            })
            .catch(() => {
              this.$message({
                type: "info",
                message: "已取消修改!",
              });
            });
        } else {
          return false;
        }
      });
    },
  },
};
</script>

<style scoped>
.index-container {
  height: 750px;
  margin-top: -30px;
}
.el-header {
  background-color: #132a3c;
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  align-items: center;
  color: #fff;
  height: 200px;
}
span {
  margin-left: 20px;
}
.head-logo {
  display: flex;
  align-items: center;
}
.head-logo img {
  width: 80px;
  height: 80px;
  margin-left: 10px;
}
.font-left {
  font-size: 20px;
  color: white;
  font-weight: bolder;
  margin-left: -920px;
  margin-top: 10px;
}
.head-button {
  align-items: center;
}

.el-aside {
  background-color: #454b5d;
  overflow: hidden;
}
.el-main {
  background-color: #eaedf1;
}
.space {
  margin-left: 17px;
}
.font {
  font-family: sans-serif;
  font-weight: bolder;
  font-size: large;
}
.upload-demo {
  margin-left: 50px;
}
.userimg {
  text-align: right;
}
.headimg {
  height: 40px;
  width: 40px;
  border-radius: 50%;
  position: relative;
  top: 15px;
  right: 77px;
}
.userimg .upload {
  margin-right: 95px;
}
</style>