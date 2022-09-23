<template>
  <div id="box">
    <div class="content">
      <el-table
        ref="tableData"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 68%; margin-left: 294px"
      >
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="产品类型描述：">
                <span>{{ props.row.ptype.tdesc }}</span>
              </el-form-item>
              <el-form-item label="最近收益率：">
                <el-tag>
                  <span>1月:{{ props.row.recentlyRate.jan }}%，</span>
                  <span>3月:{{ props.row.recentlyRate.mar }}%，</span>
                  <span>5月:{{ props.row.recentlyRate.may }}%，</span>
                  <span>7月:{{ props.row.recentlyRate.july }}%，</span>
                  <span>9月:{{ props.row.recentlyRate.sep }}%，</span>
                  <span>11月:{{ props.row.recentlyRate.nov }}%</span>
                </el-tag>
              </el-form-item>
              <el-form-item label="上市时间：">
                <span>{{ props.row.ptime }}</span>
              </el-form-item>
              <el-form-item label="产品经理：">
                <el-tag type="success"
                  ><span>{{ props.row.pmanager.mname }}</span></el-tag
                >
              </el-form-item>
              <el-form-item label="产品经理简介：">
                <span>{{ props.row.pmanager.mdesc }}</span>
              </el-form-item>
              <el-form-item label="产品描述：">
                <el-tag type="warning"
                  ><span>{{ props.row.pdesc }}</span></el-tag
                >
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column label="产品图片" width="120">
          <template slot-scope="scope">
            <img
              class="image"
              :src="require('@/assets/images/appearance/' + scope.row.pimg)"
          /></template>
        </el-table-column>
        <el-table-column label="产品名称" width="180px">
          <template slot-scope="scope">
            <el-tag>{{ scope.row.pname }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="单份价格" width="180px" sortable>
          <template slot-scope="scope">
            <el-badge is-dot class="item"> {{ scope.row.price }}元</el-badge>
          </template>
        </el-table-column>
        <el-table-column
          label="平均收益率%"
          prop="averagerate"
          sortable
          width="160px"
        >
          <template slot-scope="scope"
            ><span style="color: crimson"> {{ scope.row.averagerate }}%</span>
          </template>
        </el-table-column>
        <el-table-column label="预购数量" width="220px">
          <el-input-number
            v-model="pnum"
            @change="handleChange"
            :min="1"
            :max="9999"
            disabled
          ></el-input-number>
        </el-table-column>
      </el-table>
      <div style="margin: 20px 760px 0px 0px">
        <el-link style="font-size: 18px" @click="goBuy">继续选购</el-link>
      </div>
      <div class="totalsum">
        <span>总价：{{ totalsum | numFilter }}元</span>
      </div>
      <div style="margin: 20px 739px 0px 0px">
        <el-tag type="success">选购者:{{ username }}</el-tag>
      </div>

      <div class="btn1">
        <el-button type="primary" plain @click="pay">支付</el-button>
        <el-button type="danger" plain @click="cancel">取消</el-button>
      </div>
    </div>
    <div style="margin: 30px 739px 0px 0px">选择支付方式</div>
    <div style="margin: 20px 438px 0px 0px">
      <!-- radio不会用 ,已解决！-->
      <el-radio-group v-model="radio" @change="handleChange">
        <el-radio :label="0">支付宝</el-radio>
        <el-radio :label="1">微信支付</el-radio>
        <el-radio :label="2">银行卡</el-radio>
        <el-radio :label="3">蚂蚁花呗</el-radio>
      </el-radio-group>
    </div>
    <div class="block">
      <span class="demonstration" style="margin: 0px 10px 10px 0px"
        >选择服务星级</span
      >

      <el-rate
        v-model="value2"
        :colors="colors"
        style="margin: 9px 0px 0px 50px"
      >
      </el-rate>
    </div>
  </div>
</template>

<script>
export default {
  name: "order_v",
  inject: ["reload"],
  data() {
    return {
      totalsum: 0.0,
      username: "",
      pnum: 1,
      radio: 5,
      payWay:"",
      balance:0.00,
      value2: null,
      colors: ["#99A9BF", "#F7BA2A", "#FF9900"],
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
          price: 0.0,
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

  filters: {
    numFilter(value) {
      // 截取当前数据到小数点后两位
      let realVal = parseFloat(value).toFixed(2);
      return realVal;
    },
  },

  mounted() {
    this.username = this.$store.getters["uInfo/getUserInfo"].USERNAME;
    let id = this.$route.query.id;
    this.pnum = this.$route.query.pnum;
    this.$http
      .get(
        "http://localhost:8081/product/CustomerfindProductById/" + id,
        { xhrFields: { withCredentials: true } },
        { crossDomain: true }
      )
      .then((req) => {
        console.log(req.data);
        this.tableData = req.data.list;
        this.totalsum = this.tableData[0].price * this.pnum;
      });
  },
  methods: {
    goBuy: function () {
      this.$router.go(-2);
    },
    pay: function () {
      this.$confirm("信息是否正确, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.balance = this.$store.getters["uInfo/getUserMoneyInfo"].balance;
          if(this.balance > this.totalsum){
            this.$http
            .get(
              "http://localhost:8081/cart/addOrder/" + this.totalsum + "/" + this.payWay,
              { xhrFields: { withCredentials: true } },
              { crossDomain: true }
            )
            .then((res) => {
              if (res != null) {
                this.dialogFormVisible = false;
                this.$notify({
                  title: "成功",
                  message: "下单成功，请支付",
                  type: "success",
                });
                window.location.href = '/pay' + this.totalsum;
              }
            });
          }else {
            this.$notify.error({
              title: "错误",
              message: "用户余额不足，无法支付结算！",
            });
           
          }
          
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消!",
          });
        });
    },
    handleChange(val) {
     this.payWay=val;
    },
    cancel: function () {
      this.$confirm("确定取消么?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http
            .get(
              "http://localhost:8081/cart/addCancelOrder/" +
                this.totalsum +
                "/" +
                1,
              { xhrFields: { withCredentials: true } },
              { crossDomain: true }
            )
            .then((res) => {
              if (res != null) {
                this.dialogFormVisible = false;
                this.$notify({
                  title: "取消",
                  message: "已取消",
                  type: "info",
                });
                this.$router.go(-1);
              }
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消!",
          });
        });
    },
  },
};
</script>

<style scoped>
#box {
  background-image: url("@/assets/images/appearance/bg.jpg");
  min-height: 500px;
  overflow: hidden;
}

.image {
  height: 80px;
  width: 80px;
}
.totalsum {
  color: rgb(255, 144, 0);
  font-weight: 600;
  font-size: 30px;
  margin-left: 580px;
}
.btn1 {
  position: relative;
  bottom: -94px;
  left: 336px;
}
.block {
  position: relative;
  bottom: 117px;
  right: 369px;
}
</style>