<template>
  <div>
    <el-container>
      <el-header class="topbar">
        <el-container>
          <h1 class="icon">
            <router-link class="amireux-link" to="/frontHome"
              ><img
                src="@/assets/img/log.svg"
                style="
                  height: 60px;
                  width: 150px;
                  position: relative;
                  bottom: -3px;
                "
            /></router-link>
          </h1>
          <nav class="nav">
            <!-- :to="{ path: '/gold', query: { ptype: '6' } }" -->
        
            <el-link class="menu-item">
              <router-link class="amireux-link"  to="/frontHome"
                >主页</router-link
              >
            </el-link>
            <el-link class="menu-item"
              ><router-link
                class="amireux-link"
                :to="{ path: '/shares', query: { ptype: '2' } }"
                >股票</router-link
              ></el-link
            >
            <el-link class="menu-item"
              ><router-link
                class="amireux-link"
                :to="{ path: '/fund', query: { ptype: '1' } }"
                >基金</router-link
              ></el-link
            >
            <el-link class="menu-item"
              ><router-link
                class="amireux-link"
                :to="{ path: '/bond', query: { ptype: '3' } }"
                >债券</router-link
              ></el-link
            >
            <el-link class="menu-item"
              ><router-link
                class="amireux-link"
                :to="{ path: '/bank', query: { ptype: '4' } }"
                >银行储蓄</router-link
              ></el-link
            >
            <el-link class="menu-item"
              ><router-link
                class="amireux-link"
                :to="{ path: '/insurance', query: { ptype: '5' } }"
                >保险</router-link
              ></el-link
            >
            <el-link class="menu-item"
              ><router-link
                class="amireux-link"
                :to="{ path: '/gold', query: { ptype: '6' } }"
                >黄金</router-link
              ></el-link
            >

            <el-link class="menu-item">
              <!-- <router-link class="amireux-link" to="/research">个人中心
              </router-link> -->
              <el-dropdown @command="handleCommand1">
                <span
                  class="el-dropdown-link "
                  style="color: #999999; font-size: 18px; font-weight: bold"
                >
                  个人中心<i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item command="d">个人详情</el-dropdown-item>
                  <el-dropdown-item command="e">我的预购</el-dropdown-item>
                  <el-dropdown-item command="c">信息修改</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </el-link>
            <el-link class="menu-item">
              <el-dropdown @command="handleCommand">
                <span
                  class="el-dropdown-link"
                  style=" color:#999999; font-size: 18px;font-weight: bold;"
                >
                  登录<i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item command="a">用户登录</el-dropdown-item>
                  <el-dropdown-item command="b">管理员登录</el-dropdown-item>
                  <el-dropdown-item command="f">退出登录</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </el-link>

            <!-- <p>用户名：{{userData.username}}</p> -->
            <el-image
              :src="require('@/assets/img/user/' + userData.img)"
              class="headimg"
            >
            </el-image>

            <el-button
              type="danger"
              @click="open"
              style="position: absolute; right: 127px; top: 11px"
              >开通账户</el-button
            >
            <el-button
              type="success"
              @click="update"
              style="position: absolute; right: 18px; top: 11px"
              >更新账户</el-button
            >
          </nav>
        </el-container>
      </el-header>

      <div style="margin-bottom: 60px"></div>
      <keep-alive>
        <router-view :key="key"></router-view>
      </keep-alive>

      <el-footer class="tail">
        <el-row>
          <el-col :span="6" class="tail-link">
            <el-row
              ><el-link :underline="false" class="tail-link-item"
                ><i class="el-icon-s-custom"
                  ><router-link to="/login" style="text-decoration: none"
                    >员工登录</router-link
                  ></i
                ></el-link
              ></el-row
            >
            <el-row
              ><el-link :underline="false" class="tail-link-item"
                ><i class="el-icon-suitcase"></i>供应商</el-link
              ></el-row
            >
            <el-row
              ><el-link :underline="false" class="tail-link-item"
                ><i class="el-icon-s-management"
                  ><router-link to="/login" style="text-decoration: none"
                    >管理系统</router-link
                  ></i
                ></el-link
              ></el-row
            >
          </el-col>
          <el-col :span="1">
            <el-divider class="tail-vdivider" direction="vertical"></el-divider>
          </el-col>
          <el-col
            :span="4"
            class="tail-qrcode"
            v-for="(QRCode, index) in QRCodes"
            :key="index"
          >
            <img :src="QRCode.img" alt="qrcode" />
            <p>{{ QRCode.name }}</p>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">地址：浙江省温州市龙湾区瑶溪街道999号</el-col>
        </el-row>
        <el-row>
          <el-col :span="24">电话：19816280861</el-col>
        </el-row>
        <el-row>
          <el-col :span="24"
            ><p style="margin-bottom: 20px">
              Copyrights &copy; 2022 - 温州吾爱理财有限公司
            </p></el-col
          >
        </el-row>
      </el-footer>
    </el-container>
    <!-- 编辑弹窗(修改个人信息)  rules有问题！(已解决)-->
    <el-dialog :visible.sync="dialogFormVisible">
      <el-form :model="userData" :rules="rules" ref="userData">
        <el-form-item prop="img">
          <img
            :src="require('@/assets/img/user/' + userData.img)"
            class="image"
          />
        </el-form-item>
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
            readonly
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
            action="http://localhost:8081/upload/user"
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
  </div>
</template>
  
  <script>
export default {
  name: "frontIndex",
  inject: ["reload"],
  data() {
    return {
      dialogFormVisible: false,
      activeName: "home",
      formLabelWidth: "120px",
      currentRole: "",
      username: "",
      moneyid: "",
      userData: {
        username: "",
        password: "",
        img: "",
        nickname: "",
        sex: "",
        mobile: "",
        email: "",
      },
      fileList: [
        {
          name: "点击上传进行修改头像",
          url: "",
        },
      ],
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
      QRCodes: [
        {
          name: "官方微信",
          img: require("@/assets/images/QRCode/official_chat.png"),
        },
        {
          name: "官方微博",
          img: require("@/assets/images/QRCode/official_weblog.png"),
        },
        {
          name: "官方公众号",
          img: require("@/assets/images/QRCode/official_public.png"),
        },
      ],
    };
  },
  mounted() {
    window.onresize = () => {
      return (() => {
        window.screenWidth = document.body.clientWidth;
        this.screenWidth = window.screenWidth;
      })();
    };
    this.username = this.$store.getters["uInfo/getUserInfo"].USERNAME;
    this.currentRole = this.$store.getters["uInfo/getUserInfo"].ROLE_ID;
    this.moneyid = this.$store.getters["uInfo/getUserInfo"].MONEYID;
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
  computed: {
    key() {
      return this.$route.path + Date.now();
    },
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.userData.img = res; //从后台获取filename给img赋值
      this.fileList.name = res;
      this.fileList.url = URL.createObjectURL(file.raw);
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
                    this.reload();
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
    cancel: function () {
      this.dialogFormVisible = false;
      this.reload();
    },
    open: function () {
      this.$confirm("确定开通账户么?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.$http
          .get(
            "http://localhost:8081/user/findByMoneyid/" + this.moneyid,
            { xhrFields: { withCredentials: true } },
            { crossDomain: true }
          )
          .then((res) => {
            console.log(res);
            // //数据存入userMoneyInfo中
            this.$store.commit("uInfo/setMoneyInfo", res.data);
            localStorage.setItem("money", JSON.stringify(res.data));
            if (res.data != null) {
              this.$message({
                type: "success",
                message: "开通账户成功!",
              });
            }
          });
      });
    },
    update: function () {
      this.$confirm("确定更新账户么?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.$http
          .get(
            "http://localhost:8081/user/findByMoneyid/" + this.moneyid,
            { xhrFields: { withCredentials: true } },
            { crossDomain: true }
          )
          .then((res) => {
            console.log(res);
            // //数据存入userMoneyInfo中
            this.$store.commit("uInfo/setMoneyInfo", res.data);
            localStorage.setItem("money", JSON.stringify(res.data));
            if (res.data != null) {
              this.$message({
                type: "success",
                message: "更新账户成功!",
              });
            }
            this.reload();
          });
      });
    },
    handleCommand(command) {
      if (command == "a") {
        this.$router.push({ name: "frontLogin" });
      } else if (command == "b") {
        localStorage.removeItem("loginData");
        this.$store.commit("setUserInfo", {});
        this.$router.push({ name: "login" });
      } else {
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
                localStorage.removeItem("money");
                this.$store.commit("setMoneyInfo", {});
                if (res.data == true) {
                  this.$message({
                    type: "success",
                    message: "退出成功!",
                  });
                  this.$router.replace({ path: "/frontLogin" });
                }
              });
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消操作!",
            });
          });
      }
    },
    handleCommand1(command) {
      if (command == "c") {
        this.dialogFormVisible = true;
      } else if (command == "d") {
        this.$router.push({ path: "/personal" });
      } else {
        this.$router.push({ path: "/cart" });
      }
    },
  },
};
</script>
  
  <style scoped>
* {
  padding: 0;
  margin: 0;
  border: 0;
  list-style: none;
}
.topbar {
  height: 60px;
  background-color: #000;
  color: #999;
  /*display: flex;*/
  align-items: center;
  /* 固定定位 */
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1;
  width: 100%;
  display: flex;
  align-items: center;
  transition: 0.6s;
}
.topbar .icon {
  font-family: serif;
  font-size: 60px;
  line-height: 60px;
  margin-left: 200px;
}

.amireux-link {
  text-decoration: none;
  color: inherit;
}
.amireux-link:hover{
  color: lightblue !important;
  text-decoration: none;
}
.topbar .nav {
  margin: auto;
  text-align: right;
  margin-right: 310px;
  font-family: sans-serif;
  margin-bottom: 15px;
}
.topbar .nav .menu-item {
  margin: 0.5em;
  color: #999;
  font-size: 18px;
}

.tail {
  margin: 0 400px;
  margin-top: 20px;
}
.tail > .el-row {
  text-align: center;
  margin: 4px 0;
}
.tail .tail-link {
  font-size: 18px;
  line-height: 1.5;
  /*border: 1px solid red;*/
  /*background-color: #999999;*/
  text-align: center;
}
.tail .tail-link .tail-link-item {
  font-size: 16px;
  border: 1px solid #999;
  border-radius: 4px;
  margin: 3px 0;
}
.tail .tail-qrcode {
  text-align: center;
  margin: 0 20px;
}
.tail .tail-qrcode > img {
  width: 80px;
}
.tail .tail-vdivider {
  height: 6em;
}
.el-button--small {
  margin-right: 15px;
}
.el-upload__tip {
  margin-left: 109px;
}
.el-upload-list--picture .el-upload-list__item {
  overflow: hidden;
  z-index: 0;
  background-color: #fff;
  border: 1px solid #c0ccda;
  border-radius: 6px;
  box-sizing: border-box;
  margin-top: 10px;
  padding: 10px 10px 10px 90px;
  height: 92px;
  margin-left: 18px;
}
.image {
  border-radius: 50%;
  margin-left: 86px;
  height: 150px;
  width: 150px;
}
li.el-upload-list__item.is-success {
  margin-right: 52px;
}
.headimg {
  height: 40px;
  width: 40px;
  border-radius: 50%;
  position: relative;
  top: 16px;
  right: -90px;
}
.router-link-active{
  color:lightblue;
}
</style>
  