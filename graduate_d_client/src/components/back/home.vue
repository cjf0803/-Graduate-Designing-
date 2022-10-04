<template>
  <div class="home">
    <div class="home-head">
      <el-descriptions
        class="margin-top"
        title="实时信息"
        :column="3"
       
      >
        <template slot="extra">
          <el-button type="primary" size="small">操作</el-button>
        </template>
        <el-descriptions-item label="地点"
          ><el-tag size="small"
            >浙江省{{ weather[0].city }}市</el-tag
          ></el-descriptions-item
        >
        <el-descriptions-item label="天气">
          <el-tag type="warning" size="small"> {{ weather[0].weather }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="温度">
          <el-tag type="success" size="small"
            >{{ weather[0].temperature }}℃</el-tag
          >
        </el-descriptions-item>
        <el-descriptions-item label="当前时间">
          {{ currentDate }}
        </el-descriptions-item>
        <el-descriptions-item label="最近更新时间">
          {{ weather[0].last_update }}
      
        </el-descriptions-item>
      </el-descriptions>
    </div>
    <el-divider></el-divider>
    <div id="main1" style="width: 100%; height: 600px"></div>
    <el-divider></el-divider>
    <div
      id="employeeSex"
      style="width: 100%; height: 500px; margin-bottom: 50px"
    ></div>
    <el-divider></el-divider>
    <div id="main" style="width: 600px; height: 400px; float: left"></div>
    <el-steps :active="1">
      <el-step title="Plan 1" icon="el-icon-edit"></el-step>
      <el-step title="Plan 2" icon="el-icon-upload"></el-step>
      <el-step title="Plan 3" icon="el-icon-picture"></el-step>
    </el-steps>
    <div class="block">
      <el-timeline>
        <el-timeline-item timestamp="2018/4/12" placement="top">
          <el-card>
            <h4>设计 用户 模块</h4>
            <p>boss 提交于 2022/9/5 20:46</p>
          </el-card>
        </el-timeline-item>
        <el-timeline-item timestamp="2018/4/3" placement="top">
          <el-card>
            <h4>设计 理财 模块</h4>
            <p>admin 提交于 2022/9/7 21:46</p>
          </el-card>
        </el-timeline-item>
        <el-timeline-item timestamp="2018/4/2" placement="top">
          <el-card>
            <h4>制作 Echarts 图表</h4>
            <p>cjf 提交于 2022/9/11 20:46</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
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
    <el-steps  finish-status="success">
      <el-step title="步骤 1"></el-step>
      <el-step title="步骤 2"></el-step>
      <el-step title="步骤 3"></el-step>
    </el-steps>

    <el-button style="margin-top: 12px" >下一步</el-button>
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
          weather: "",
          temperature: "",
          code: "",
          last_update: "",
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
    initEmployeeSex() {
      //  let recentlyRate=[];
      let pname = [];
      let jan = [];
      let mar = [];
      let may = [];
      let july = [];
      let sep = [];
      let nov = [];
      this.$http
        .get("http://localhost:8081/product/findProduct/1")
        .then((req) => {
          //请求成功时执行该函数内容，result即为服务器返回的json对象
          if (req.data) {
            for (var i = 0; i < req.data.list.length; i++) {
              //recentlyRate.push(String(req.data.list[i].recentlyRate));
              pname.push(String(req.data.list[i].pname));
              jan.push(String(req.data.list[i].recentlyRate.jan));
              mar.push(String(req.data.list[i].recentlyRate.mar));
              may.push(String(req.data.list[i].recentlyRate.may));
              july.push(String(req.data.list[i].recentlyRate.july));
              sep.push(String(req.data.list[i].recentlyRate.sep));
              nov.push(String(req.data.list[i].recentlyRate.nov));
              // this.values.push(req.data[i].value);
            }

            // 基于准备好的dom，初始化echarts实例
            var myChart = this.$echarts.init(
              document.getElementById("employeeSex")
            );
            // 绘制图表
            let option = {
              color: ["#80FFA5", "#00DDFF", "#37A2FF", "#FF0087", "#FFBF00"],
              title: {
                text: "financial products",
              },
              tooltip: {
                trigger: "axis",
                axisPointer: {
                  type: "cross",
                  label: {
                    backgroundColor: "#6a7985",
                  },
                },
              },
              legend: {
                data: pname,
              },
              toolbox: {
                feature: {
                  saveAsImage: {},
                },
              },
              grid: {
                left: "3%",
                right: "4%",
                bottom: "3%",
                containLabel: true,
              },
              xAxis: [
                {
                  type: "category",
                  boundaryGap: false,
                  data: ["jan", "mar", "may", "july", "sep", "nov"],
                },
              ],
              yAxis: [
                {
                  type: "value",

                  axisTick: {
                    inside: true,
                  },
                  scale: true,
                },
              ],
              series: [
                {
                  name: pname[0],
                  type: "line",
                  stack: "Total",
                  smooth: true,
                  lineStyle: {
                    width: 0,
                  },
                  showSymbol: false,
                  areaStyle: {
                    opacity: 0.8,
                    color: this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                      {
                        offset: 0,
                        color: "rgb(128, 255, 165)",
                      },
                      {
                        offset: 1,
                        color: "rgb(1, 191, 236)",
                      },
                    ]),
                  },
                  emphasis: {
                    focus: "series",
                  },

                  data: [
                    // req.data.list[0].recentlyRate.jan,
                    // req.data.list[0].recentlyRate.mar,
                    // req.data.list[0].recentlyRate.may,
                    // req.data.list[0].recentlyRate.july,
                    // req.data.list[0].recentlyRate.sep,
                    // req.data.list[0].recentlyRate.nov,
                    jan[0],
                    mar[0],
                    may[0],
                    july[0],
                    sep[0],
                    nov[0],
                  ],
                },
                {
                  name: pname[1],
                  type: "line",
                  stack: "Total",
                  smooth: true,
                  lineStyle: {
                    width: 0,
                  },
                  showSymbol: false,
                  areaStyle: {
                    opacity: 0.8,
                    color: this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                      {
                        offset: 0,
                        color: "rgb(0, 221, 255)",
                      },
                      {
                        offset: 1,
                        color: "rgb(77, 119, 255)",
                      },
                    ]),
                  },
                  emphasis: {
                    focus: "series",
                  },
                  data: [
                    // req.data.list[1].recentlyRate.jan,
                    // req.data.list[1].recentlyRate.mar,
                    // req.data.list[1].recentlyRate.may,
                    // req.data.list[1].recentlyRate.july,
                    // req.data.list[1].recentlyRate.sep,
                    // req.data.list[1].recentlyRate.nov,
                    jan[1],
                    mar[1],
                    may[1],
                    july[1],
                    sep[1],
                    nov[1],
                  ],
                },
                {
                  name: pname[2],
                  type: "line",
                  stack: "Total",
                  smooth: true,
                  lineStyle: {
                    width: 0,
                  },
                  showSymbol: false,
                  areaStyle: {
                    opacity: 0.8,
                    color: this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                      {
                        offset: 0,
                        color: "rgb(55, 162, 255)",
                      },
                      {
                        offset: 1,
                        color: "rgb(116, 21, 219)",
                      },
                    ]),
                  },
                  emphasis: {
                    focus: "series",
                  },
                  data: [
                    // req.data.list[2].recentlyRate.jan,
                    // req.data.list[2].recentlyRate.mar,
                    // req.data.list[2].recentlyRate.may,
                    // req.data.list[2].recentlyRate.july,
                    // req.data.list[2].recentlyRate.sep,
                    // req.data.list[2].recentlyRate.nov,
                    jan[2],
                    mar[2],
                    may[2],
                    july[2],
                    sep[2],
                    nov[2],
                  ],
                },
                {
                  name: pname[3],
                  type: "line",
                  stack: "Total",
                  smooth: true,
                  lineStyle: {
                    width: 0,
                  },
                  showSymbol: false,
                  areaStyle: {
                    opacity: 0.8,
                    color: this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                      {
                        offset: 0,
                        color: "rgb(255, 0, 135)",
                      },
                      {
                        offset: 1,
                        color: "rgb(135, 0, 157)",
                      },
                    ]),
                  },
                  emphasis: {
                    focus: "series",
                  },
                  data: [
                    // req.data.list[3].recentlyRate.jan,
                    // req.data.list[3].recentlyRate.mar,
                    // req.data.list[3].recentlyRate.may,
                    // req.data.list[3].recentlyRate.july,
                    // req.data.list[3].recentlyRate.sep,
                    // req.data.list[3].recentlyRate.nov,
                    jan[3],
                    mar[3],
                    may[3],
                    july[3],
                    sep[3],
                    nov[3],
                  ],
                },
                {
                  name: pname[4],
                  type: "line",
                  stack: "Total",
                  smooth: true,
                  lineStyle: {
                    width: 0,
                  },
                  showSymbol: false,
                  label: {
                    show: true,
                    position: "top",
                  },
                  areaStyle: {
                    opacity: 0.8,
                    color: this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                      {
                        offset: 0,
                        color: "rgb(255, 191, 0)",
                      },
                      {
                        offset: 1,
                        color: "rgb(224, 62, 76)",
                      },
                    ]),
                  },
                  emphasis: {
                    focus: "series",
                  },
                  data: [
                    // req.data.list[4].recentlyRate.jan,
                    // req.data.list[4].recentlyRate.mar,
                    // req.data.list[4].recentlyRate.may,
                    // req.data.list[4].recentlyRate.july,
                    // req.data.list[4].recentlyRate.sep,
                    // req.data.list[4].recentlyRate.nov,
                    jan[4],
                    mar[4],
                    may[4],
                    july[4],
                    sep[4],
                    nov[4],
                  ],
                },
              ],
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
          }
        });
    },
    drawChart() {
      // 基于准备好的dom，初始化echarts实例  这个和上面的main对应
      let myChart = this.$echarts.init(document.getElementById("main"));
      // 指定图表的配置项和数据
      let option = {
        title: {
          text: "ECharts",
        },
        tooltip: {},
        legend: {
          data: ["用户登录情况"],
        },
        xAxis: {
          data: ["boss", "admin", "cjf", "德布劳内", "内马尔", "阿扎尔"],
        },
        yAxis: {},
        series: [
          {
            name: "用户登录情况",
            type: "bar",
            data: [5, 20, 36, 10, 10, 20],
          },
        ],
      };
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    },
    drawChart1() {
      // 基于准备好的dom，初始化echarts实例  这个和上面的main对应
      let username = [];
      let pay = [];
      let income = [];
      let balance = [];
      let myChart = this.$echarts.init(document.getElementById("main1"));
      this.$http.get("http://localhost:8081/user/findCustomer").then((req) => {
        //请求成功时执行该函数内容，result即为服务器返回的json对象
        if (req.data) {
          for (var i = 0; i < req.data.list.length; i++) {
            pay.push(String(req.data.list[i].moneyid.pay));
            income.push(String(req.data.list[i].moneyid.income));
            balance.push(String(req.data.list[i].moneyid.balance));
            username.push(String(req.data.list[i].username));
          }
          // 指定图表的配置项和数据
          const colors = ["#5470C6", "#91CC75", "#EE6666"];
          let option = {
            color: colors,
            title: {
              text: "User Money",
            },
            tooltip: {
              trigger: "axis",
              axisPointer: {
                type: "cross",
              },
            },
            grid: {
              right: "20%",
            },
            toolbox: {
              feature: {
                dataView: { show: true, readOnly: false },
                restore: { show: true },
                saveAsImage: { show: true },
              },
            },
            legend: {
              data: ["收入", "支出", "余额"],
            },
            xAxis: [
              {
                type: "category",
                axisTick: {
                  alignWithLabel: true,
                },
                // prettier-ignore
                data: username,
              },
            ],
            yAxis: [
              {
                type: "value",
                name: "收入",
                position: "right",
                alignTicks: true,
                axisLine: {
                  show: true,
                  lineStyle: {
                    color: colors[0],
                  },
                },
                axisLabel: {
                  formatter: "{value} 元",
                },
              },
              {
                type: "value",
                name: "支出",
                position: "right",
                alignTicks: true,
                offset: 80,
                axisLine: {
                  show: true,
                  lineStyle: {
                    color: colors[1],
                  },
                },
                axisLabel: {
                  formatter: "{value} 元",
                },
              },
              {
                type: "value",
                name: "余额",
                position: "left",
                alignTicks: true,
                axisLine: {
                  show: true,
                  lineStyle: {
                    color: colors[2],
                  },
                },
                axisLabel: {
                  formatter: "{value} 元",
                },
              },
            ],
            series: [
              {
                name: "收入",
                type: "bar",
                data: income,
              },
              {
                name: "支出",
                type: "bar",
                yAxisIndex: 1,
                data: pay,
              },
              {
                name: "余额",
                type: "line",
                yAxisIndex: 2,
                data: balance,
              },
            ],
          };
          // 使用刚指定的配置项和数据显示图表。
          myChart.setOption(option);
        }
      });
    },
  },
  created() {
    this.currenttime = setInterval(this.gettime, 1000);
  },
  mounted() {
    this.$nextTick(() => {
      this.drawChart();
      this.drawChart1();
      this.initEmployeeSex();
    });
    this.$http.get("/api").then((res) => {
      console.log(res.data);
      // this.weather=res.data.lives
      this.weather[0].city = res.data.results[0].location.name;
      this.weather[0].weather = res.data.results[0].now.text;
      this.weather[0].temperature = res.data.results[0].now.temperature;
      this.weather[0].code = res.data.results[0].now.code;
      this.weather[0].last_update = res.data.results[0].last_update;
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