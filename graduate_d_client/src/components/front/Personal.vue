<template>
  <div id="box">
    <div id="main" style="width: 500px; height: 500px;float: left;"></div>
    <el-button type="success" @click="recharge" plain round  icon="el-icon-s-finance" style="width:65%;height:50px;margin-right: 18px;">点击充值</el-button>
    <el-table
      :data="tableData"
      ref="tableData"
      style="width: 65%; text-align: center;margin-top: 15px;"
    >
      <el-table-column label="下单用户" width="140">
        <template slot-scope="scope">
          <el-tag size="medium">{{ scope.row.username }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="time" label="下单时间" width="140" sortable>
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          {{ scope.row.time }}
        </template>
      </el-table-column>
      <el-table-column label="支付金额" width="140" sortable>
        <template slot-scope="scope">
          <i class="el-icon-info"></i>
          <span style="margin-left: 5px">{{ scope.row.totalsum }}</span>
        </template>
      </el-table-column>
      <el-table-column label="支付方式" width="140">
        <template slot-scope="scope">
          <i class="el-icon-coin"></i>
          <span style="margin-left: 10px">{{ scope.row.payWay.pname }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单状态" width="140">
        <template slot-scope="scope">
          <el-tag :type="scope.row.state.sid==='0'? 'info':(scope.row.state.sid==='1'?'warning':(scope.row.state.sid==='2'? 'warning':(scope.row.state.sid==='3'?'success':'success')))"> 
            <i class="el-icon-loading"></i>
          <span style="margin-left: 10px">{{ scope.row.state.sname }}</span></el-tag>
         
        </template>
      </el-table-column>
      <el-table-column label="操作时间" width="140">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.executetime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作者" width="120">
        <template slot-scope="scope">
          <i class="el-icon-s-custom"></i>
          <span style="margin-left: 10px">{{ scope.row.author }}</span>
        </template>
      </el-table-column>
    </el-table>
    <div class="fenye" style="margin-top: 20px; ">
      <!-- 分页 -->
      <el-pagination
        background
        @current-change="changePage"
        layout="total,prev, pager, next,jumper"
        :current-page="currentPage"
        :page-size="pageSize"
        :total="total"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "Perosnal_v",
  data() {
    return {
      balance: 0.0,
      pay: 0.0,
      totalmoney: 0.0,
      income: 0.0,
      moneyid:"",
      username:"",
      currentPage:1,
      total: 0,
      pageSize: 1,
      tableData: [
        {
          oid: "",
          username: "",
          totalsum: 0.0,
          exincome: 0.0,
          time: "",
          payWay: {
            pid: "",
            pname: "",
            pdesc: "",
          },
          state: {
            sid: "",
            sname: "",
            pdesc: "",
          },
          executetime: "",
          author: "",
        },
      ],
    };
  },
  mounted() {
    this.username = this.$store.getters["uInfo/getUserInfo"].USERNAME;
    this.balance = this.$store.getters["uInfo/getUserMoneyInfo"].balance;
    this.pay = this.$store.getters["uInfo/getUserMoneyInfo"].pay;
    this.totalmoney = this.$store.getters["uInfo/getUserMoneyInfo"].totalmoney;
    this.income = this.$store.getters["uInfo/getUserMoneyInfo"].income;
    this.moneyid = this.$store.getters["uInfo/getUserInfo"].MONEYID;
    this.$nextTick(() => {
      this.drawChart();
    });
    this.$http
      .get(
        "http://localhost:8081/order/findOrderByUsername/1",
        { xhrFields: { withCredentials: true } },
        { crossDomain: true }
      )
      .then((req) => {
        console.log(req.data);
      this.tableData = req.data.list;
      this.total = req.data.count;
      this.pageSize = req.data.list.length;
      });
  },
  methods: {
    recharge:function(){
      this.$prompt("请输入充值金额", "金额数量确认", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputValue: "100",
        center: "true",
      })
        .then(({ value }) => {
          if (value == null) {
            value = 100;
          }
          this.$http
            .get(
              "http://localhost:8081/user/updateMoney/" +
                this.moneyid +
                "/" +
                value
            )
            .then((res) => {
                console.log(res.data)
              if (res.data != null) {
                this.$notify({
                  title: "成功",
                  message: "充值成功~",
                  type: "success",
                });
              }
              window.location.href = '/pay' + value;
            });

        }) .catch(() => {
          this.$message({
            type: "info",
            message: "取消充值！",
          });
        });
    },
    changePage: function (index) {
      this.$http
        .get("http://localhost:8081/order/findOrderByUsername/" + index)
        .then((res) => {
          this.tableData = res.data.list;
        });
    },
    drawChart() {
      // 基于准备好的dom，初始化echarts实例  这个和上面的main对应
      let myChart = this.$echarts.init(document.getElementById("main"));
      // 指定图表的配置项和数据
      let option = {
        title: {
          text: "My Total Money",
          subtext:  this.username,
          left: "center",
        },
        tooltip: {
          trigger: "item",
        },
        legend: {
          orient: "vertical",
          left: "left",
        },
        series: [
          {
            name: "各类占比",
            type: "pie",
            radius: "50%",
            data: [
              { value: this.totalmoney, name: "个人总资产" },
              { value: this.pay, name: "支出" },
              { value: this.income, name: "收入" },
              { value: this.balance, name: "余额" },
              
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      };
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    },
  },
};
</script>

<style scoped>
  #box{
    background-image: url("@/assets/images/appearance/bg.jpg");
    min-height: 500px;
  }
</style>