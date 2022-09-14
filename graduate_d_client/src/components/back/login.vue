<template>
  <div>
    <div class="container">
      <div class="forms-container">
        <div class="signin-signup">
          <el-form
            :model="ruleForm"
            :rules="rules"
            ref="ruleForm"
            label-width="100px"
            class="sign-in-form"
          >
            <h2 class="title" style="color:#4481eb">登录</h2>
            <el-form-item prop="username">
              <el-input
                v-model="ruleForm.username"
                clearable
                class="input-field"
                placeholder="*用户名"
              ></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input
                class="input-field"
                type="password"
                v-model="ruleForm.password"
                clearable
                placeholder="*密码"
              ></el-input>
            </el-form-item>
            <!-- <el-button type="primary" @click="verificationButton"
              >点击进行验证</el-button
            > -->
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')"
                >登 录</el-button
              >
              <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
            <p class="social-text">或者通过以下平台登录</p>
            <div class="social-media">
              <a href="#" class="social-icon">
                <i class="fab fa-weixin"></i>
              </a>
              <a href="#" class="social-icon">
                <i class="fab fa-qq"></i>
              </a>
              <a href="#" class="social-icon">
                <i class="fab fa-alipay"></i>
              </a>
              <a href="#" class="social-icon">
                <i class="fab fa-github"></i>
              </a>
            </div>
          </el-form>
          <form action="#" class="sign-up-form">
            <h2 class="title">注册</h2>
            <div class="input-field">
              <i class="fas fa-user"></i>
              <input type="text" placeholder="用户名" />
            </div>
            <div class="input-field">
              <i class="fas fa-envelope"></i>
              <input type="email" placeholder="邮箱" />
            </div>
            <div class="input-field">
              <i class="fas fa-lock"></i>
              <input type="password" placeholder="密码" />
            </div>
            <input type="submit" class="btn" value="注 册" />
            <p class="social-text">或者通过以下平台注册</p>
            <div class="social-media">
              <a href="#" class="social-icon">
                <i class="fab fa-weixin"></i>
              </a>
              <a href="#" class="social-icon">
                <i class="fab fa-qq"></i>
              </a>
              <a href="#" class="social-icon">
                <i class="fab fa-alipay"></i>
              </a>
              <a href="#" class="social-icon">
                <i class="fab fa-github"></i>
              </a>
            </div>
          </form>
        </div>
      </div>
      <div class="verification-wrap">
        <el-dialog
          title="请完成安全验证"
          :visible.sync="dialogbuttonVisible"
          width="30%"
        >
          <div class="verification-slide">
            <slide-verify
              :l="40"
              :r="10"
              :w="280"
              :h="150"
              ref="slideblock"
              @again="onAgain"
              @fulfilled="onFulfilled"
              @success="onSuccess"
              @fail="onFail"
              :imgs="puzzleImgList"
              @refresh="onRefresh"
              :accuracy="accuracy"
              :slider-text="text"
            ></slide-verify>
          </div>
        </el-dialog>
      </div>

      <div class="panels-container">
        <div class="panel left-panel">
          <div class="content">
            <h3 style="margin-bottom:40px">管理员登陆界面</h3>
            <el-link  style="font-size:20px;"><router-link to="/frontIndex"  style="color:aliceblue"><i class="el-icon-view el-icon--right"></i>Go 首页！</router-link></el-link>
          </div>
          <img src="../../assets/img/log.svg" class="image" alt="" />
        </div>
        <!-- <div class="panel right-panel">
          <div class="content">
            <h3>已经是我们自己人了吗?</h3>
            <p>那好兄弟,你直接点击登录按钮,登录到我们这优秀的系统里!!</p>
            <button class="btn transparent" id="sign-in-btn">登 录</button>
          </div>
          <img src="../../assets/img/register.svg" class="image" alt="" />
        </div> -->
      </div>
    </div>
  </div>
</template>

<script>
import "@/assets/js/app.js";
import "@/assets/css/style.css";

export default {
  name: "login_v",
  data() {
    return {
      dialogbuttonVisible: false,
      puzzleImgList: [
        require("@/assets/img/user/0.jpg"),
        require("@/assets/img/user/1.jpg"),
        require("@/assets/img/user/2.jpg"),
        require("@/assets/img/user/3.jpg"),
        require("@/assets/img/user/4.jpg"),
      ],
      text: "请向右滑哦~",
      // 精确度小，可允许的误差范围小；为1时，则表示滑块要与凹槽完全重叠，才能验证成功。默认值为5
      accuracy: 7,
      msg: false,
      ruleForm: {
        username: "",
        password: "",
        role_id: "",
        error: 0,
        state:1,
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.dialogbuttonVisible = true;
          let username = this.ruleForm.username;
          let password = this.ruleForm.password;
          this.$http
            .get(
              "http://localhost:8081/user/login/" + username + "/" + password,
              { xhrFields: { withCredentials: true } },
              { crossDomain: true }
            )
            .then((res) => {
              if (res.data != "") {
                // //数据存入userInfo中
                this.$store.commit("uInfo/setUserInfo", res.data[0]);
                localStorage.setItem("loginData", JSON.stringify(res.data[0]));
                this.ruleForm.role_id = res.data[0].ROLE_ID;
                this.ruleForm.state = res.data[0].STATE;
                //this.ruleForm.error = res.data.status;
                this.msg=true;
            //     if (this.msg === true) {
            //       this.$message({
            //         showClose: true,
            //         message: "登录成功",
            //         type: "success",
            //       });
            //       if (res.data[0].ROLE_ID == "0") {
            //         this.$router.push({ name: "welcome" });
            //       } else {
            //         this.$router.push({ name: "index1" });
            //       }
            //   }
             }
            });
        } else {
          return false;
        }
      });
    },
    // 验证成功，触发该方法
    onSuccess() {
      this.dialogbuttonVisible = false;
      if (this.ruleForm.role_id >=1&&this.msg==true&&this.ruleForm.state==1) {
        this.$message({
          showClose: true,
          message: "恭喜你登录成功！",
          type: "success",
        });
        this.$router.push({ name: "index1" });
      }else if(this.ruleForm.error==0){
        this.$message.error('登录失败，请检查用户名和密码！');
      }
         // 刷新
         this.$refs.slideblock.reset();
    },
    onFail() {
      console.log("验证不通过");
      this.msg = false;
    },
    onRefresh() {
      console.log("点击了刷新小图标");
    },
    onFulfilled() {
      console.log("刷新成功啦！");
    },
    onAgain() {
      console.log("检测到非人为操作的哦！");
      // 刷新
      this.$refs.slideblock.reset();
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    
  },
};
</script>

<style scoped>
.el-button + .el-button,
.el-checkbox.is-bordered + .el-checkbox.is-bordered {
  margin-left: 10px;
  height: 38px;
}
/* .verification-slide */
.slide-verify {
  left: 50%;
  top: 0;
  transform: translate(-50%);
}
a {
    text-decoration: none;
  }
  .router-link-active {
    text-decoration: none;
  }



</style>