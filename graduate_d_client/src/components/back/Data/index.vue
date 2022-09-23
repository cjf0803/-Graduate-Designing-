<template>
  <div>
   
    <div id="main1" style="width: 100%; height: 600px"></div>
    <el-divider></el-divider>
    <div
      id="employeeSex"
      style="width: 100%; height: 500px; margin-bottom: 50px"
    ></div>
  </div>
</template>

<script>
export default {
  name: "data_v",
  data() {
    return {};
  },
  mounted() {
    this.$nextTick(() => {
      this.drawChart1();
      this.initEmployeeSex();
    });
  },
  methods: {
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
};
</script>

<style>
</style>