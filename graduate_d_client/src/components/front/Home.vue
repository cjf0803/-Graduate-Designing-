<template>
  <div>
    <el-backtop
      target=".page-component__scroll .el-scrollbar__wrap"
    ></el-backtop>
    <el-main class="content">
      <el-row style="margin-top:-23px">
        <el-col :span="16" :offset="4">
          <el-carousel height="300px"  type="card" :interval="1500">
            <el-carousel-item
              v-for="item in advertises"
              :key="item"
              class="content-carousel"
            >
              <el-image style="height: 300px" :src="item"></el-image>
            </el-carousel-item>
          </el-carousel>
        </el-col>
      </el-row>
      <div style="display: flex">
        <div id="drawChart" style="width: 50%; height: 500px"></div>
        <div id="drawChart2" style="width: 50%; height: 500px"></div>
      </div>
      <el-row class="tech">
        <el-col :span="8">
          <p class="tech-content1">我们致力于用科技让普通人和小企业</p>
          <p class="tech-content2">享受平等的金融和理财服务。</p>
        </el-col>
        <el-col :span="16">
          <el-container>
            <el-card
              v-for="(tech, index) in technologys"
              :key="index"
              :body-style="{ padding: '0px' }"
              class="tech-card"
            >
              <el-image :src="tech.url" style="height: 280px"></el-image>
              <div class="tech-content-link">
                <el-link :underline="false">{{ tech.name }}</el-link>
              </div>
            </el-card>
          </el-container>
        </el-col>
      </el-row>
    </el-main>
    <el-main class="news">
      <el-row>
        <el-col :span="20" :offset="2">
          <p class="news-title">耀眼东方</p>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="20" :offset="2">
          <el-container style="margin-left: 10px">
            <el-card
              v-for="(news, index) in ferodeNews"
              :key="index"
              :body-style="{ padding: '0px' }"
              class="news-card"
            >
              <el-image :src="news.url" style="height: 280px"></el-image>
              <div style="padding: 14px">
                <el-link :underline="false" class="news-info">{{
                  news.info
                }}</el-link>
              </div>
            </el-card>
          </el-container>
        </el-col>
      </el-row>
    </el-main>
    <el-row class="goods">
      <el-col :span="22" :offset="1">
        <el-image
          v-for="(item, index) in appList"
          :src="item"
          :key="index"
        ></el-image>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "frontHome",
  data() {
    let appList = new Array(6);
    for (let i = 1; i <= 6; i++) {
      appList[i - 1] = require("@/assets/images/appearance/shares" +
        i +
        ".png");
    }
    return {
      appList: appList,
      advertises: [
        require("@/assets/images/advertisement/home1.png"),
        require("@/assets/images/advertisement/home7.png"),
        require("@/assets/images/advertisement/home3.png"),
        require("@/assets/images/advertisement/home4.png"),
      ],
      technologys: [
        {
          name: "追求黄金配比，分享股债机遇",
          url: require("@/assets/images/techs/tech4.jpg"),
        },
        {
          name: "收益有优势，越久越显著",
          url: require("@/assets/images/techs/tech1.jpg"),
        },
        {
          name: "稳健理财人气精品Top10",
          url: require("@/assets/images/techs/tech2.jpg"),
        },
        {
          name: "力争降低回撤，保护收益成果",
          url: require("@/assets/images/techs/tech3.jpg"),
        },
      ],
      ferodeNews: [
        {
          info: "我们致力于用区块链技术构建新一代的信任机制，提高价值流转和多方协同的效率",
          url: require("@/assets/images/news/news-01.png"),
        },
        {
          info: "万物互通互联是IoT技术的核心，也是我们重要的技术战略.",
          url: require("@/assets/images/news/news-02.png"),
        },
        {
          info: "计算技术的发展和普及，是实现普惠金融的基础。我们通过十多年的探索和实践。",
          url: require("@/assets/images/news/news-03.png"),
        },
        {
          info: "我们致力于推动智能风控、生物核身、数据安全三大安全技术领域的研究和探索",
          url: require("@/assets/images/news/news-04.png"),
        },
      ],
    };
  },
  mounted() {
    this.$nextTick(() => {
      this.drawChart();
      this.drawChart2();
    });
    this.$notify.info({
      title: "消息提醒",
      message: "登录成功后记得点击右上角开通账户~",
    });
  },
  methods: {
    drawChart() {
      let rname = [];
      let jan = [];
      let mar = [];
      let may = [];
      let july = [];
      let sep = [];
      let nov = [];
      this.$http
        .get("http://localhost:8081/recentlyRate/findAll/4")
        .then((req) => {
          //请求成功时执行该函数内容，result即为服务器返回的json对象
          if (req.data) {
            //  let data= req.data.list
            for (var i = 0; i < req.data.list.length; i++) {
              //recentlyRate.push(String(req.data.list[i].recentlyRate));
              rname.push(String(req.data.list[i].rname));
              jan.push(String(req.data.list[i].jan));
              mar.push(String(req.data.list[i].mar));
              may.push(String(req.data.list[i].may));
              july.push(String(req.data.list[i].july));
              sep.push(String(req.data.list[i].sep));
              nov.push(String(req.data.list[i].nov));
              // this.values.push(req.data[i].value);
            }
            var myChart = this.$echarts.init(
              document.getElementById("drawChart")
            );
            let option = {
              title: {
                text: "产品月收益率",
                left: "1%",
              },
              tooltip: {
                trigger: "axis",
              },
              grid: {
                left: "5%",
                right: "15%",
                bottom: "10%",
              },
              xAxis: {
                data: ["jan", "mar", "may", "july", "sep"],
              },
              yAxis: {},
              toolbox: {
                right: 10,
                feature: {
                  dataZoom: {
                    yAxisIndex: "none",
                  },
                  restore: {},
                  saveAsImage: {},
                },
              },
              dataZoom: [
                {
                  startValue: "jan",
                },
                {
                  type: "inside",
                },
              ],
              visualMap: {
                top: 50,
                right: 10,
                pieces: [
                  {
                    gt: 0,
                    lte: 5,
                    color: "#93CE07",
                  },
                  {
                    gt: 5,
                    lte: 10,
                    color: "#FBDB0F",
                  },
                  {
                    gt: 10,
                    lte: 15,
                    color: "#FC7D02",
                  },
                  {
                    gt: 15,
                    lte: 30,
                    color: "#FD0100",
                  },
                  {
                    gt: 30,
                    lte: 45,
                    color: "#AA069F",
                  },
                  {
                    gt: 55,
                    color: "#AC3B2A",
                  },
                ],
                outOfRange: {
                  color: "#999",
                },
              },
              series: [
                {
                  name: rname[0],
                  type: "line",
                  data: jan,
                  markLine: {
                    silent: true,
                    lineStyle: {
                      color: "#93CE07",
                    },
                  },
                },
                {
                  name: rname[1],
                  type: "line",
                  data: mar,
                  markLine: {
                    silent: true,
                    lineStyle: {
                      color: "#333",
                    },
                  },
                },
                {
                  name: rname[2],
                  type: "line",
                  data: may,
                  markLine: {
                    silent: true,
                    lineStyle: {
                      color: "#333",
                    },
                  },
                },
                {
                  name: rname[3],
                  type: "line",
                  data: july,
                  markLine: {
                    silent: true,
                    lineStyle: {
                      color: "#333",
                    },
                  },
                },
                {
                  name: rname[4],
                  type: "line",
                  data: sep,
                  markLine: {
                    silent: true,
                    lineStyle: {
                      color: "#333",
                    },
                  },
                },
              ],
            };

            myChart.setOption(option);
          }
        });
    },
    drawChart2() {
      let rname = [];
      let jan = [];
      let mar = [];
      let may = [];
      let july = [];
      let sep = [];
      let nov = [];
      this.$http
        .get("http://localhost:8081/recentlyRate/findAll/6")
        .then((req) => {
          //请求成功时执行该函数内容，result即为服务器返回的json对象
          if (req.data) {
            //  let data= req.data.list
            for (var i = 0; i < req.data.list.length; i++) {
              //recentlyRate.push(String(req.data.list[i].recentlyRate));
              rname.push(String(req.data.list[i].rname));
              jan.push(String(req.data.list[i].jan));
              mar.push(String(req.data.list[i].mar));
              may.push(String(req.data.list[i].may));
              july.push(String(req.data.list[i].july));
              sep.push(String(req.data.list[i].sep));
              nov.push(String(req.data.list[i].nov));
              // this.values.push(req.data[i].value);
            }
            var myChart = this.$echarts.init(
              document.getElementById("drawChart2")
            );
             // 绘制图表
             let option = {
              color: ["#80FFA5", "#00DDFF", "#37A2FF", "#FF0087", "#FFBF00"],
             
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
                data: rname,
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
                  name: rname[0],
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
                  name: rname[1],
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
                    jan[1],
                    mar[1],
                    may[1],
                    july[1],
                    sep[1],
                    nov[1],
                  ],
                },
                {
                  name: rname[2],
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
                  name: rname[3],
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
                  name: rname[4],
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
  },
};
</script>

<style scoped>
.content {
  background-image: url("@/assets/images/appearance/bg.jpg");
}
.content .content-carousel {
  border-radius: 6px;
  z-index: 0.1;
  margin-bottom: 15px;
}

.content .tech {
  margin: 40px 100px 25px 100px;
}
.content .tech .tech-content1 {
  font-size: 20px;
  font-weight: bold;
  margin-top: 80px;
}
.content .tech .tech-content2 {
  font-size: 40px;
  margin-top: 40px;
}
.content .tech .tech-card {
  width: 180px;
  margin: 0 10px;
  height: 380px;
}
.content .tech .tech-card > .el-image {
  width: 100%;
}
.content .tech .tech-card .tech-content-link {
  padding: 14px;
}

.news {
  background-color: azure;
}
.news .news-title {
  font-size: 30px;
  margin: 20px 0 0 22px;
}
.news .news-card {
  width: 280px;
  height: 380px;
  border: none;
  padding: 20px;
  background-color: azure;
  margin-left: 25px;
  margin-top: 10px;
}
.news .news-card > .el-image {
  width: 100%;
}
.news .news-card .news-info {
  font-size: 16px;
}
</style>
