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
      >批量删除</el-button
    >
    <el-button
      @click="clear"
      style="float: left; margin-left: 13px; margin-top: 15px"
      >清空选择</el-button
    >

    <div class="fenye" style="margin-top: 20px; margin-right: 670px">
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
          left: 642px;
        "
        >预购总价：￥{{ totalSum | numFilter }}元</span
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
      totalSum: 0.0,
      payWay: "",
      balance: 0.0,
      selNum: 0,
      sum: 0.0,
      flag: true,
      i: 0,
      getRowKeys(row) {
        return row.cid;
      },
      multipleSelection: [],
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
          selected: false,
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
    this.$http
      .get("http://localhost:8081/cart/findCartByUsername/1")
      .then((res) => {
        if (res.data != null) {
          this.tableData = res.data.list;
          this.total = res.data.count;
          this.pageSize = res.data.list.length;
        }
      });
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
            this.$http
              .get(
                "http://localhost:8081/cart/addOrder/" +
                  this.totalSum +
                  "/" +
                  this.payWay,
                { xhrFields: { withCredentials: true } },
                { crossDomain: true }
              )
              .then((res) => {
                if (res != null) {
                  this.dialogFormVisible = false;
                  this.$notify({
                    title: "成功",
                    message: "请支付",
                    type: "success",
                  });

                  window.location.href = "/pay" + this.totalSum;
                }
              });
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
          this.$http
            .get(
              "http://localhost:8081/cart/delCartAll/" + this.multipleSelection,
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
     
      for (var index1 in val) {
        this.multipleSelection.push(val[index1].cid);
      }
      for (var index in val) {
        console.log(index);
        this.totalSum += val[this.selNum - 1].price*val[this.selNum - 1].pnum;
        
        break;
      }

    
      console.log(this.selNum1);
      // for (var i in this.tableData) {
      //   if (this.multipleSelection.indexOf(this.tableData[i].cid) != -1) {
      //     this.tableData[i].selected = 1;
      //   } else {
      //     this.tableData[i].selected = 0;
      //   }
      // }
      this.i += 1;
      console.log(this.i);
      console.log(this.multipleSelection);
    },
    // handlerSelect(val,row) {

    //   if(val.length==1){
    //     val.forEach((item) => {
    //       console.log("已被选")
    //     this.multipleSelection.push(item.cid); //把所有的id放进multipleSelection
    //     this.totalSum +=row.pnum*row.price;
    //     this.sum=row.pnum*row.price
    //   });
    //   }else{
    //     console.log("未被选")
    //     this.totalSum -=this.sum;
    //   }
    //   console.log(this.sum);

    // },

    // handleSelectionChange(val) {
    //   //this.multipleSelection = val // 返回的是选中的列的数组集合
    //   if(val.length==1){
    //     val.forEach((item) => {
    //       console.log(item)
    //     this.multipleSelection.push(item.cid); //把所有的id放进multipleSelection
    //     })
    //   }else if (val.length > 1) {
    //     console.log(val)
    //     let flag=false;
    //     val.forEach((item) => {
    //       if(flag){
    //       this.multipleSelection.push(item.cid); //把所有的id放进multipleSelection
    //       }
    //       flag=true;
    //     });
    //   } else {
    //     console.log("取消勾选");
    //     console.log(val)

    //   }

    //   console.log(this.multipleSelection);
    // },
    handleChange(row) {
      this.$http
        .get(
          "http://localhost:8081/cart/updateNumById/" + row.cid + "/" + row.pnum
        )
        .then(() => {
          // this.reload();
        });
    },
    changePage: function (index) {
      this.$http
        .get("http://localhost:8081/cart/findCartByUsername/" + index)
        .then((res) => {
          this.tableData = res.data.list;
        });
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