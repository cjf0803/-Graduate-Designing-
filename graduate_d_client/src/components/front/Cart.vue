<template>
  <div id="box">
    <!-- @select="handlerSelect" -->
    <el-table
      show-summary
      ref="tableData"
      :data="tableData"
      :row-key="getRowKeys"
      tooltip-effect="dark"
      style="width: 88%; margin-left: 125px"
      :summary-method="getSummaries"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
        :reserve-selection="true"
      ></el-table-column>
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
      <el-table-column label="单份价格" sortable>
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
        <template slot-scope="scope">
          <el-input-number
            v-model="scope.row.pnum"
            @change="handleChange(scope.row)"
            :min="1"
            :max="9999"
          ></el-input-number>
        </template>
      </el-table-column>
      <el-table-column label="小计" sortable width="170px">
        <template slot-scope="scope">
          <i class="el-icon-coin"></i>&nbsp;
          <span style="color: darkorange; font-weight: bolder"
            >{{ (scope.row.pnum * scope.row.price) | numFilter }}元</span
          >
        </template>
      </el-table-column>
      <el-table-column label="预购者" width="180px">
        <template slot-scope="scope">
          <el-tag>{{ scope.row.username }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="100" style="z-index: 0">
        <template slot-scope="scope">
          <el-button @click="delProduct(scope.row)" type="text" size="small"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-button
      @click="delAll"
      style="float: left; margin-left: 185px; margin-top: 15px"
      type="warning"
      plain
      >批量删除</el-button
    >
    <el-button
      @click="clear"
      style="float: left; margin-left: 13px; margin-top: 15px"
      type="info"
      plain
      >清空选择</el-button
    >
    <el-button
      @click="goBuy"
      style="float: left; margin-left: 13px; margin-top: 15px"
      type="success"
      plain
      >继续购物</el-button
    >

    <div class="fenye" style="margin-top: 20px; margin-right: 245px">
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
      <!-- <el-pagination
                    background
                    @current-change="changePage"
                    layout="prev, pager, next"
                    :total="50">
            </el-pagination> -->
      <span
        style="
          font-size: x-large;
          position: relative;
          bottom: 109px;
          left: 435px;
        "
        >预购总价：￥{{ totalSum > 0 ? totalSum.toFixed(2) : "0.00" }}元</span
      >
    </div>
    <el-button
      @click="dialogFormVisible = true"
      type="danger"
      style="position: relative; bottom: 143px; left: 629px"
      >结算</el-button
    >
    <el-dialog title="确认支付信息" :visible.sync="dialogFormVisible">
      <el-form>
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input
            v-model="tableData[0].username"
            autocomplete="off"
            readonly
            suffix-icon="el-icon-user"
          ></el-input>
        </el-form-item>
        <el-form-item label="总额" :label-width="formLabelWidth">
          <el-input
            v-model="totalSum"
            autocomplete="off"
            readonly
            suffix-icon="el-icon-coin"
          ></el-input>
        </el-form-item>
        <el-form-item label="支付方式" :label-width="formLabelWidth">
          <el-select v-model="payWay" placeholder="请选择支付方式">
            <el-option label="支付宝" value="0"></el-option>
            <el-option label="微信" value="1"></el-option>
            <el-option label="银行卡" value="2"></el-option>
            <el-option label="蚂蚁花呗" value="3"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addOrder">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// import axios from "axios";
// import qs from "qs";
export default {
  name: "Cart_v",
  inject: ["reload"],
  data() {
    return {
      dialogFormVisible: false,
      formLabelWidth: "120px",
      toggleIndex: 0,
      total: 1,
      pageSize: 1,
      currentPage: 1,
      totalSum: 0.00,
      payWay: "",
      balance: 0.0,
      selNum: 0,
      sum: 0.0,
      flag: true,
      username:"",
      i: 0,
      getRowKeys(row) {
        return row.cid;
      },
      multipleSelection: [],
      selectionIds: [],
      tableData: [
        {
          cid: "",
          username: "",
          pimg: "",
          pname: "",
          price: "",
          averagerate: "",
          pnum: "",
          sum: "",
          selected: 1,
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
    this.$http
      .get("http://localhost:8081/cart/findCartByUsername/1")
      .then((res) => {
        if (res.data != null) {
          this.tableData = res.data.list;
          this.total = res.data.count;
          this.pageSize = res.data.list.length;
        }
      });
    // this.tableData.forEach((item)=>{
    //   //让tableData中每一个item都增加一个属性select并且为true
    //   this.$set(item,'select',true);
    // })
  },

  methods: {
    addOrder: function () {
      this.$confirm("信息是否正确, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.balance = this.$store.getters["uInfo/getUserMoneyInfo"].balance;
          if (this.balance > this.totalSum) {
            let detail_id = Math.round(Math.random() * 1000);
            let detail = JSON.stringify(this.multipleSelection);
            var that = this;
            this.$jquery.ajax({
              url: "http://localhost:8081/cart/addOrder",
              data:
                "detail="+detail+"&totalSum="+this.totalSum+"&payWay="+this.payWay+"&detail_id="+detail_id+"&username="+this.username
                ,
              type: "post",
              dataType: "json",
              success: function (res) {
                if (res) {
                  that.dialogFormVisible = false;
                  that.$notify({
                    title: "成功",
                    message: "请支付",
                    type: "success",
                  });
                  window.location.href = "/pay" + that.totalSum.toFixed(2);
                }
              },
            });
            // this.$http
            //   .get(
            //     "http://localhost:8081/cart/addOrder/" +
            //       this.totalSum +
            //       "/" +
            //       this.payWay+
            //       "/"+detail_id
            //      ,
            //     { xhrFields: { withCredentials: true } },
            //     { crossDomain: true }
            //   )
            //   .then((res) => {
            //     if (res != null) {
            //       this.dialogFormVisible = false;
            //       this.$notify({
            //         title: "成功",
            //         message: "请支付",
            //         type: "success",
            //       });

            //       window.location.href = "/pay" + this.totalSum;
            //     }
            //   });
          } else {
            this.$notify.error({
              title: "错误",
              message: "用户余额不足，无法支付结算！",
            });
            this.dialogFormVisible = false;
          }
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消!",
          });
        });
    },
    delAll: function () {
      this.$confirm("批量删除数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          console.log(this.selectionIds);
          this.$http
            .get(
              "http://localhost:8081/cart/delCartAll/" + this.selectionIds,
              { xhrFields: { withCredentials: true } },
              { crossDomain: true }
            )
            .then((res) => {
              if (res != null) {
                this.$message({
                  showClose: true,
                  message: "删除成功",
                  type: "success",
                });
                this.reload();
              }
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除!",
          });
        });
    },

    clear() {
      this.clearSelection();
    },
    //清除选中的数据
    clearSelection() {
      this.$nextTick(() => {
        this.totalSum = 0.0;
        this.flag = false;
        this.$refs.tableData.clearSelection(); //elementUI 官方文档的方法 不过直接使用会报错
        this.multipleSelection = [];
      });
    },
    getSummaries(param) {
      const { columns } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = "合计";
          return;
        }
        if (index === 4) {
          sums[index] = "已选中商品" + this.selNum + "件";
        }
      });

      return sums;
    },
    handleSelectionChange(val) {
      this.selNum = val.length;
      this.multipleSelection = val;
      this.selectionIds = this.multipleSelection.map(function (item) {
        return item["cid"];
      });
      console.log("选中的商品id数组：" + this.selectionIds);

      this.totalSum = 0.0; //每次调用清零 重新计算！！！
      for (var index of val) {
        this.totalSum += index.price * index.pnum;
      }

      console.log(this.multipleSelection);
    },

    handleChange(row) {
      this.totalSum = 0.0;
      this.$http
        .get(
          "http://localhost:8081/cart/updateNumById/" + row.cid + "/" + row.pnum
        )
        .then(() => {
          // this.reload();
        });
      let val = this.multipleSelection;
      for (var index of val) {
        this.totalSum += index.price * index.pnum;
      }
    },
    changePage: function (index) {
      this.$http
        .get("http://localhost:8081/cart/findCartByUsername/" + index)
        .then((res) => {
          this.tableData = res.data.list;
        });
    },
    goBuy: function () {
      this.$router.go(-1);
    },
    delProduct: function (row) {
      console.log(row);
      this.$confirm("删除一只预购产品, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http
            .get(
              "http://localhost:8081/cart/deleteCart/" + row.cid,
              { xhrFields: { withCredentials: true } },
              { crossDomain: true }
            )
            .then((res) => {
              if (res != null) {
                this.$message({
                  showClose: true,
                  message: "删除成功",
                  type: "success",
                });
                this.reload();
              }
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除!",
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
}
.image {
  height: 80px;
  width: 80px;
}
div .cell {
  z-index: 0;
}
</style>