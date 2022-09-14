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
                  width: 110px;
                  position: relative;
                  bottom: 10px;
                "
            /></router-link>
          </h1>
          <nav class="nav">
            <el-link class="menu-item"
              ><router-link class="amireux-link" to="/frontHome"
                >主页</router-link
              ></el-link
            >
            <el-link class="menu-item"
              ><router-link class="amireux-link" to="/appearance"
                >股票</router-link
              ></el-link
            >
            <el-link class="menu-item"
              ><router-link class="amireux-link" to="/elaboration"
                >基金</router-link
              ></el-link
            >
            <el-link class="menu-item"
              ><router-link class="amireux-link" to="/flower"
                >债券</router-link
              ></el-link
            >
            <el-link class="menu-item"
              ><router-link class="amireux-link" to="/blogs"
                >银行储蓄</router-link
              ></el-link
            >
            <el-link class="menu-item"
              ><router-link class="amireux-link" to="/info"
                >保险</router-link
              ></el-link
            >
            <el-link class="menu-item"
              ><router-link class="amireux-link" to="/research"
                >黄金</router-link
              ></el-link
            >
            <el-link class="menu-item"
              ><router-link class="amireux-link" to="/research"
                >个人中心</router-link
              ></el-link
            >
            <el-link class="menu-item">
              <el-dropdown @command="handleCommand">
                <span class="el-dropdown-link" style="color:lightblue;font-size: 18px;">
                  登录<i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item command="a">用户登录</el-dropdown-item>
                  <el-dropdown-item command="b">管理员登录</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </el-link>
          </nav>
        </el-container>
      </el-header>
      <router-view></router-view>
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
                ><i class="el-icon-s-management"></i>管理系统</el-link
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
  </div>
</template>
  
  <script>
export default {
  name: "frontIndex",
  data() {
    return {
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
  },
  methods: {
      handleCommand(command) {
        if(command=="a"){
          this.$router.push({name:'frontLogin'})
        }else{
          this.$router.push({name:'login'})
        }
      }
    }
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

.topbar .nav {
  margin: auto;
  text-align: right;
  margin-right: 200px;
  font-family: sans-serif;
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
</style>
  