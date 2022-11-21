<template>
  <div id="box">
    <div class="container1">
      <div class="from-box">
        <!-- 注册 -->
        <div class="register-box hidden">
          <h1>register</h1>
          <input type="text" v-model="ruleForm.username" placeholder="用户名" />
          <input type="email" v-model="ruleForm.email" placeholder="邮箱" />
          <input
            type="password"
            v-model="ruleForm.password"
            placeholder="密码"
          />
          <input type="password" placeholder="确认密码" />

          <button @click="register">注册</button>
        </div>
        <!-- 登录 -->
        <div class="login-box">
          <h1>login</h1>
          <input type="text"  v-model="tabelData.username" placeholder="用户名~" />
          <input type="password"    v-model="tabelData.password" placeholder="密码~" />
          <button @click="login">登录</button>
        </div>
      </div>
      <div class="con-box left">
        <h2>欢迎来到<span>吾爱理财</span></h2>
        <p>快去购买你心爱的<span>理财产品</span>吧</p>
        <img src="@/assets/img/log.svg" alt="" />
        <p>已有账号</p>
        <button id="login">去登录</button>
      </div>
      <div class="con-box right">
        <h2>欢迎来到<span>吾爱理财</span></h2>
        <p>快去购入一只高收益<span></span>理财产品吧</p>
        <img src="@/assets/img/register.svg" alt="" />
        <p>没有账号?</p>
        <button id="register">去注册</button>
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
  </div>
</template>

<script>
export default {
  name: "front_login",
  inject: ["reload"],
  data() {
    return {
    dialogbuttonVisible:false,
    rules: {
          required: true, message: "请输入用户名", trigger: "blur" 
      },
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
        email: "",
        role_id: "",
        error: 0,
        state:1,
      },
      tabelData:{
        username:"",
        password:""
      }
    };
  },
  mounted() {
    //要操作到的元素
    let login = document.getElementById("login");
    let register = document.getElementById("register");
    let form_box = document.getElementsByClassName("from-box")[0];
    let register_box = document.getElementsByClassName("register-box")[0];
    let login_box = document.getElementsByClassName("login-box")[0];
    //点击去注册按钮点击事件
    register.addEventListener("click", () => {
      form_box.style.transform = "translateX(80%)";
      login_box.classList.add("hidden");
      register_box.classList.remove("hidden");
    });
    //点击去登录按钮点击事件
    login.addEventListener("click", () => {
      form_box.style.transform = "translateX(0%)";
      register_box.classList.add("hidden");
      login_box.classList.remove("hidden");
    });
  },
  methods: {
    register: function () {
      this.$confirm("确定注册?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "info",
      })
        .then(() => {
          let username = this.ruleForm.username;
          let password = this.ruleForm.password;
          let email = this.ruleForm.email;
          this.$http
            .get(
              "http://localhost:8081/user/regist/" +
                username +
                "/" +
                password +
                "/" +
                email,
              { xhrFields: { withCredentials: true } },
              { crossDomain: true }
            )
            .then((res) => {
              console.log(res);
              if (res.data) {
                this.$message({
                  showClose: true,
                  message: "恭喜你注册成功！请点击去登录",
                  type: "success",
                });
              }
              setTimeout(()=>{
                this.reload();
              },1000)
              
            })
            .catch(() => {
              this.$message({
                type: "error",
                message: "注册失败，该用户已注册！",
              });
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消注册",
          });
        });
    },
    login: function () {
        this.dialogbuttonVisible = true;
          let username = this.tabelData.username;
          let password = this.tabelData.password;
          this.$http
            .get(
              "http://localhost:8081/user/login/" +
                username +
                "/" +
                password,
              { xhrFields: { withCredentials: true } },
              { crossDomain: true }
            )
            .then((res) => {
              console.log(res);
              if (res.data!=null) {
                this.$store.commit("uInfo/setUserInfo", res.data[0]);
                localStorage.setItem("loginData", JSON.stringify(res.data[0]));
                this.ruleForm.role_id = res.data[0].ROLE_ID;
                this.ruleForm.state = res.data[0].STATE;
                //this.ruleForm.error = res.data.status;
                this.msg=true;
              }
            })
        
    },
    onSuccess() {
      this.dialogbuttonVisible = false;
      if (this.ruleForm.role_id == "0"&&this.msg==true&&this.ruleForm.state==1) {
        this.$message({
          showClose: true,
          message: "恭喜你,登录成功！",
          type: "success",
        });
        this.$router.push({ name: "frontIndex" });
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
  },
};
</script>

<style scoped>
* {
  /* 初始化 */
  padding: 0;
  margin: 0;
}
#box {
  width: 100%;
  height: 741px;
  background-image: url("@/assets/img/beijing.jpg");
  position: relative;
  top: -30px;
  bottom: 0px;
  overflow: hidden;
  background-size: 1800px 800px;
  background-repeat: no-repeat;
}

.container1 {
  background-color: #fff;
  width: 650px;
  height: 415px;
  /* 阴影 */
  box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.1);
  /* 相对定位 */
  position: relative;
  margin: auto;
  top: 150px;
  /* 渐变背景 */
  background: linear-gradient(200deg, #5ec7ed, #e3eeff);
}
.from-box {
  /* 绝对定位 */
  position: absolute;
  top: -10%;
  left: 5%;
  background-color: #316688;
  width: 320px;
  height: 500px;
  border-radius: 5px;
  box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
  /* 弹性布局 水平+垂直居中 */
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 2;
  /* 动画过渡 加速之后 减速 */
  transition: 0.5s ease-in-out;
}

.register-box,
.login-box {
  /* 弹性布局 垂直排列 */
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;

}
.hidden {
  display: none;
  transition: 0.5s;
}

h1 {
  text-align: center;
  margin-bottom: 25px;
  /* 大写字母 */
  text-transform: uppercase;
  color: #fff;
  /* 字间距 */
  letter-spacing: 5px;
}

input {
  background-color: transparent;
  width: 70%;
  color: #fff;
  border: none;
  /* 下边框样式 */
  border-bottom: 1px solid rgba(255, 255, 255, 0.4);
  padding: 10px 0;
  text-indent: 10px;
  margin: 8px 0;
  font-size: 14px;
  /* 字间距 */
  letter-spacing: 2px;
}
input::placeholder {
  color: #fff;
}

input:focus {
  color: #61d5e1;
  outline: none;
  border-bottom: 1px solid #6298ad80;
  transition: 0.5s;
}
input:focus::placeholder {
  opacity: 0;
}

.from-box button {
  width: 70%;
  margin-top: 35px;
  background-color: #f6f6f6;
  outline: none;
  border-radius: 8px;
  padding: 13px;
  color: #a262ad;
  letter-spacing: 2px;
  border: none;
  cursor: pointer;
}

.from-box button:hover {
  background-color: #628bad;
  color: #f6f6f6;
  transition: background-color 0.5s ease;
}

.con-box {
  width: 50%;
  /* 弹性布局 垂直排列 居中 */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  /* 绝对定位 */
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
}

.con-box.left {
  left: -2%;
}

.con-box.right {
  right: -2%;
}

.con-box h2 {
  color: #8e9aaf;
  font-size: 25px;
  font-weight: bold;
  letter-spacing: 3px;
  text-align: center;
  margin-bottom: 4px;
}

.con-box p {
  font-size: 12px;
  letter-spacing: 2px;
  color: #8e9aaf;
  text-align: center;
}
.con-box span {
  color: #757ecf;
}

.con-box img {
  width: 150px;
  height: 150px;
  opacity: 0.9;
  margin: 40px 0;
}

.con-box button {
  margin-top: 3%;
  background-color: #fff;
  color: #a262ad;
  border: 1px solid #d3b7d8;
  padding: 6px 10px;
  letter-spacing: 1px;
  outline: none;
  /* 鼠标放上变小手 */
  cursor: pointer;
}
.con-box button:hover {
  background-color: #a262ad;
  color: #f6f6f6;
}
.slide-verify {
  left: 50%;
  top: 0;
  transform: translate(-50%);
}
.input-field{
  height:44px;
  align-items: center;
  color:#8e9aaf
}
</style>