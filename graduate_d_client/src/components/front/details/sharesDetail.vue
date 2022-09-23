<template>
  <div id="box">
    <div class="content" v-for="(item, index) in tableData" :key="index">
      <el-col :span="14" style="margin-top: 30px">
        <h2
          style="
            font-size: 34px;
            color: #999999;
            margin-bottom: 14px;
            font-family: Microsoft YaHei, sans-serif;
          "
        >
          股票介绍视频
        </h2>
        <video
          src="@/assets/videos/ProductDetails/shares.mp4"
          loop
          muted
          autoplay
          type="video/mp4"
          width="580px"
        ></video>
        <h2
          style="
            font-size: 14px;
            color: #999999;
            margin-top: 14px;
            font-family: Microsoft YaHei, sans-serif;
          "
        >
          {{ item.ptype.tdesc }}
        </h2>
      </el-col>
      <div style="width: 500px">
        <p class="big-text">{{ item.pname }}</p>
        <p class="middle-text">平均收益率：{{ item.price }}%</p>
        <el-tag
          size="medium"
          style="height: 50px; line-height: 50px; font-size: 15px"
        >
          <span>1月:{{ item.recentlyRate.jan }}%，</span>
          <span>3月:{{ item.recentlyRate.mar }}%，</span>
          <span>5月:{{ item.recentlyRate.may }}%，</span>
          <span>7月:{{ item.recentlyRate.july }}%，</span>
          <span>9月:{{ item.recentlyRate.sep }}%，</span>
          <span>11月:{{ item.recentlyRate.nov }}%</span>
        </el-tag>
        <p class="small-text">总资产：{{ item.psize }}</p>
        <p class="small-text">上市时间：{{ item.ptime }}</p>
        <p class="small-text">产品经理：{{ item.pmanager.mname }}</p>
        <p class="small-text">产品经理简介：{{ item.pmanager.mdesc }}</p>
        <p class="small-text">产品描述：{{ item.pdesc }}</p>
        <div class="btn1">
          <el-button type="danger" @click="addNum">加入预购</el-button>
          <el-button type="success" @click="goPay">立即结算</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "shares_detail",
  data() {
    return {
      tableData: [
        {
          id: "",
          pname: "",
          pimg: "",
          ptype: {
            tid: "",
            tname: "",
            tdesc: "",
          },
          price: "",
          pstate: {
            sid: "",
            sname: "",
            sdesc: "",
          },
          totalnum: "",
          averagerate: "",
          recentlyRate: {
            rid: "",
            jan: "",
            mar: "",
            may: "",
            july: "",
            sep: "",
            nov: "",
          },
          psize: "",
          ptime: "",
          pmanager: {
            mid: "",
            mname: "",
            mdesc: "",
          },
          pdesc: "",
        },
      ],
    };
  },
  mounted() {
    let id = this.$route.query.id;
    this.$http
      .get(
        "http://localhost:8081/product/CustomerfindProductById/" + id,
        { xhrFields: { withCredentials: true } },
        { crossDomain: true }
      )
      .then((req) => {
        console.log(req.data);
        this.tableData = req.data.list;
      });
  },
  methods: {
    goPay: function () {
      //console.log(this.$route.query.id)
      this.$prompt("请输入数量", "购入数量确认", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputValue: "1",
        center: "true",
      })
        .then(({ value }) => {
          if (value == null) {
            value = 1;
          }
          this.$router.push({path:'/order',query:{id:this.$route.query.id,pnum:value}})

        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "取消输入",
          });
        });
    },
    addNum: function () {
      this.$prompt("请输入数量", "购入数量确认", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputValue: "1",
        center: "true",
      })
        .then(({ value }) => {
          if (value == null) {
            value = 1;
          }
          this.$http
            .get(
              "http://localhost:8081/product/addCart/" +
                this.tableData[0].id +
                "/" +
                value
            )
            .then((res) => {
              console.log(res.data)
              if (res.data != null) {
                this.$notify({
                  title: "添加成功",
                  message: "可继续选购我们的理财产品~",
                  type: "success",
                });
              }
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "取消输入",
          });
        });
    },
  },
};
</script>

<style scoped>
#box {
  background-image: url("@/assets/images/appearance/bg.jpg");
  height: 470px;
}
.content {
  display: flex;
  flex-flow: wrap;
}
.big-text {
  font-size: 34px;
  color: #1e1c1c;
  margin-bottom: 24px;
  font-family: Microsoft YaHei, sans-serif;
}
.middle-text {
  font-size: 30px;
  color: crimson;
  margin-bottom: 24px;
  font-family: Microsoft YaHei, sans-serif;
}
.small-text {
  font-size: 20px;
  color: #8a8282;
  font-family: Microsoft YaHei, sans-serif;
  line-height: 24px;
  margin-top: 15px;
}
.btn1 {
  margin-top: 20px;
}
</style>