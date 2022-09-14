<template>
  <div class="home">
    <div class="home-head">
      <el-descriptions
        class="margin-top"
        title="实时信息"
        :column="3"
        :size="size"
      >
        <template slot="extra">
          <el-button type="primary" size="small">操作</el-button>
        </template>
        <el-descriptions-item label="地点"
          ><el-tag size="small"
            >{{ weather[0].province }}{{ weather[0].city }}</el-tag
          ></el-descriptions-item
        >
        <el-descriptions-item label="天气">{{
          weather[0].weather
        }}</el-descriptions-item>
        <el-descriptions-item label="温度"
          >{{ weather[0].temperature }}℃</el-descriptions-item
        >
        <el-descriptions-item label="当前时间">
          {{ currentDate }}
        </el-descriptions-item>
        <el-descriptions-item label="风向"
          >{{ weather[0].winddirection }}</el-descriptions-item
        >
        <el-descriptions-item label="风力"
          >{{ weather[0].windpower }}</el-descriptions-item
        >
      </el-descriptions>
    
    </div>
    <el-divider></el-divider>
    <div class="home-content">
      <div
        class="
          col-sm-9 col-sm-offset-3 col-md-10 col-lg-10 col-md-offset-2
          main
        "
        id="main"
        style="margin-left: -31px; margin-top: 10px"
      >
        <h1 class="page-header">信息总览</h1>
        <div class="row placeholders" style="display: flex; margin-left: 546px">
          <div class="col-xs-6 col-sm-3 placeholder">
            <h4>日志</h4>
            <span class="text-muted">99 条</span>
          </div>
          <div class="col-xs-6 col-sm-3 placeholder">
            <h4>评论</h4>
            <span class="text-muted">30 条 </span>
          </div>
          <div class="col-xs-6 col-sm-3 placeholder">
            <h4>友链</h4>
            <span class="text-muted">60 条</span>
          </div>
          <div class="col-xs-6 col-sm-3 placeholder">
            <h4>访问量</h4>
            <span class="text-muted">201 条</span>
          </div>
        </div>
        <h1 class="page-header">状态</h1>
        <div class="table-responsive">
          <table
            class="table table-striped table-hover"
            style="margin: auto; border: 1px solid #e8e8e8"
          >
            <tbody>
              <tr>
                <td>
                  登录者: <span>admin</span>，这是您第 <span>7</span> 次登录
                </td>
              </tr>
              <tr>
                <td>
                  上次登录时间: 2022-01-05 15:50:28 , 上次登录IP: ::1:55570
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <h1 class="page-header">系统信息</h1>
        <div class="table-responsive">
          <table
            class="table table-striped table-hover"
            style="margin: auto; border: 1px solid #e8e8e8"
          >
            <thead>
              <tr></tr>
            </thead>
            <tbody>
              <tr>
                <td>管理员个数:</td>
                <td>3人</td>
                <td>服务器软件:</td>
                <td>Apache/2.4.10 (Win32) OpenSSL/1.0.1i mod_fcgid/2.3.9</td>
              </tr>
              <tr>
                <td>浏览器:</td>
                <td>Chrome47</td>
                <td>PHP版本:</td>
                <td>5.6.1</td>
              </tr>
              <tr>
                <td>操作系统:</td>
                <td>Windows 10</td>
                <td>PHP运行方式:</td>
                <td>CGI-FCGI</td>
              </tr>
              <tr>
                <td>登录者IP:</td>
                <td>::1:55570</td>
                <td>ORACLE版本:</td>
                <td>5.7.31</td>
              </tr>
              <tr>
                <td>程序版本:</td>
                <td class="version">
                  TigerCjf <span size="-6" color="#BBB">(20160108160215)</span>
                </td>
                <td>上传文件:</td>
                <td>
                  可以
                  <span size="-6" color="#BBB">(最大文件：2M ，表单：8M )</span>
                </td>
              </tr>
              <tr>
                <td>程序编码:</td>
                <td>UTF-8</td>
                <td>当前时间:</td>
                <td>2022-01-05 15:50:28</td>
              </tr>
            </tbody>
            <tfoot>
              <tr></tr>
            </tfoot>
          </table>
        </div>
        <footer>
          <h1 class="page-header">程序信息</h1>
          <div class="table-responsive">
            <table
              class="table table-striped table-hover"
              style="margin: auto; border: 1px solid #e8e8e8"
            >
              <tbody>
                <tr>
                  <td>
                    <span style="display: inline-block; width: 8em"
                      >版权所有</span
                    >
                    POWERED BY WY ALL RIGHTS RESERVED
                  </td>
                </tr>
                <tr>
                  <td>
                    <span style="display: inline-block; width: 8em"
                      >页面加载时间</span
                    >
                    PROCESSED IN 1.0835s SECONDS 更多模板：<a
                      href="http://www.mycodes.net/"
                      target="_blank"
                      >源码之家</a
                    >
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </footer>
      </div>
    </div>
    <el-divider></el-divider>

    <el-row>
      <el-col :sm="12" :lg="6">
        <el-result
          icon="success"
          title="成功提示"
          subTitle="请根据提示进行操作"
        >
          <template slot="extra">
            <el-button type="primary" size="medium">返回</el-button>
          </template>
        </el-result>
      </el-col>
      <el-col :sm="12" :lg="6">
        <el-result
          icon="warning"
          title="警告提示"
          subTitle="请根据提示进行操作"
        >
          <template slot="extra">
            <el-button type="primary" size="medium">返回</el-button>
          </template>
        </el-result>
      </el-col>
      <el-col :sm="12" :lg="6">
        <el-result icon="error" title="错误提示" subTitle="请根据提示进行操作">
          <template slot="extra">
            <el-button type="primary" size="medium">返回</el-button>
          </template>
        </el-result>
      </el-col>
      <el-col :sm="12" :lg="6">
        <el-result icon="info" title="信息提示" subTitle="请根据提示进行操作">
          <template slot="extra">
            <el-button type="primary" size="medium">返回</el-button>
          </template>
        </el-result>
      </el-col>
    </el-row>
    <el-steps :active="active" finish-status="success">
      <el-step title="步骤 1"></el-step>
      <el-step title="步骤 2"></el-step>
      <el-step title="步骤 3"></el-step>
    </el-steps>

    <el-button style="margin-top: 12px" @click="next">下一步</el-button>
  </div>
</template>

<script >
export default {
  name: "home_v",
  data() {
    return {
      weather: [
        {
          city: "",
          province: "",
          weather: "",
          temperature: "",
          winddirection: "",
          windpower: "",
        },
      ],
      currenttime: "",
      currentDate: null,
    };
  },
  methods: {
    gettime() {
      this.currentDate = new Date().toLocaleString();
    },
  },
  created() {
    this.currenttime = setInterval(this.gettime, 1000);
  },
  mounted() {
    this.$http.get("/api").then((res) => {
      console.log(res.data);
      // this.weather=res.data.lives
      this.weather[0].city = res.data.lives[0].city;
      this.weather[0].province = res.data.lives[0].province;
      this.weather[0].weather = res.data.lives[0].weather;
      this.weather[0].temperature = res.data.lives[0].temperature;
      this.weather[0].winddirection = res.data.lives[0].winddirection;
      this.weather[0].windpower = res.data.lives[0].windpower;
    });
  },
};
</script>

<style scoped >
/* @import "/src/assets/css/bootstrap.min.css"; */
.home {
  padding: 24px;
  background: #fff;
  min-height: 1050px;
}
.home-head {
  /* position: absolute;
  top: 180px; */
  width: 100%;

  display: flex;
  list-style: none;
}
.home-content {
  margin-bottom: 25px;

}
.page-header {
  margin-bottom: 10px;
  margin-top: 10px;
}
</style>