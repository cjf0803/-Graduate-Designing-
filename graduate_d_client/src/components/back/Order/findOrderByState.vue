<template>
  <div>
    <div style="margin-bottom: 20px">
      <el-page-header @back="goBack" content="理财产品"> </el-page-header>
    </div>
    <div style="margin: 0 70px 18px 0">
    <el-progress type="circle" :percentage="0"></el-progress>
    <el-progress type="circle" :percentage="25"></el-progress>
    <el-progress type="circle" :percentage="100" status="success"></el-progress>
    <el-progress type="circle" :percentage="70" status="warning"></el-progress>
    <el-progress
      type="circle"
      :percentage="50"
      status="exception"
    ></el-progress>
    </div>
    <el-table
      :data="tableData"
      ref="tableData"
      :row-key="getRowKeys"
      @selection-change="handleSelectionChange"
      style="width: 100%; text-align: center"
    >
      <el-table-column
        type="selection"
        width="55"
        :reserve-selection="true"
      ></el-table-column>
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
      <el-table-column label="支付金额" width="140">
        <template slot-scope="scope">
          <i class="el-icon-info"></i>
          <span style="margin-left: 5px">{{ scope.row.totalsum }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预期收益" width="140">
        <template slot-scope="scope">
          <i class="el-icon-s-claim"></i>
          <span style="margin-left: 10px">{{ scope.row.exincome }}</span>
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
          <el-tag
            :type="
              scope.row.state.sid === '0'
                ? 'info'
                : scope.row.state.sid === '1'
                ? 'warning'
                : scope.row.state.sid === '2'
                ? 'warning'
                : scope.row.state.sid === '3'
                ? 'success'
                : ''
            "
          >
            <i class="el-icon-loading"></i>
            <span style="margin-left: 10px">{{
              scope.row.state.sname
            }}</span></el-tag
          >
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
      <el-table-column label="操作">
        <!-- scope作用域插槽，可以通过scope绑定当前行的数据 -->
        <template slot-scope="scope">
          <el-tooltip
            effect="dark"
            :disabled="scope.row.state.sid=='2'?true:false"
            content="不允许操作！"
            placement="left"
            transition
          >
            <span>
              <el-button
                :disabled="scope.row.state.sid=='2'?false:true"
                @click="success(scope.row)"
                type="info"
                icon="el-icon-info"
                style="margin: 9px 0px 10px 0px"
                >完成</el-button
              >
            </span>
          </el-tooltip>
          <el-tooltip
            effect="dark"
            :disabled="currentRole > 2 ? true : false"
            content="用户权限不足"
            placement="left"
            transition
          >
            <span style="margin-right: 7px">
              <el-button
                :disabled="currentRole > 2 ? false : true"
                @click="delOrder(scope.row)"
                type="danger"
                icon="el-icon-delete"
                >删除</el-button
              >
            </span>
          </el-tooltip>
        </template>
      </el-table-column>
    </el-table>

    <el-button
      :disabled="currentRole > 2 ? false : true"
      @click="delAll"
      style="float: left; margin-left: 12px; margin-top: 15px"
      >批量删除</el-button
    >

    <el-button
      :disabled="currentRole > 2 ? false : true"
      @click="clear"
      style="float: left; margin-left: 13px; margin-top: 15px"
      >清空选择</el-button
    >
    <div class="fenye" style="margin-top: 20px; text-align: right">
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
  name: "findOrderBy_State",
  inject: ["reload"],
  data() {
    return {
      getRowKeys(row) {
        return row.oid;
      },
      currentRole: "",
      currentPage: 1,
      total: 0,
      pageSize: 1,
      keywords: "",
      state: "",
      multipleSelection: [],
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
    let state = this.$route.query.state;
    this.currentRole = this.$store.getters["uInfo/getUserInfo"].ROLE_ID;
    this.$http
      .get(
        "http://localhost:8081/order/findOrderByState/1/" + state,
        { xhrFields: { withCredentials: true } },
        { crossDomain: true }
      )
      .then((req) => {
        console.log(req.data);
        this.tableData = req.data.list;
        this.total = req.data.count;
        this.index = req.data.index;
        this.state = req.data.state;
        this.pageSize = req.data.list.length;
      });
  },
  methods: {
    goBack() {
      this.$router.push({ name: "orderIndex" });
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
              "http://localhost:8081/order/delOrderAll/" +
                this.multipleSelection,
              { xhrFields: { withCredentials: true } },
              { crossDomain: true }
            )
            .then((res) => {
              if (res != null) {
                this.$message({
                  showClose: true,
                  message: "批量删除成功!",
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
        this.$refs.tableData.clearSelection(); //elementUI 官方文档的方法 不过直接使用会报错
        this.multipleSelection = [];
      });
    },
    delOrder: function (row) {
      console.log(row);
      this.$confirm("此操作将永久删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http
            .get(
              "http://localhost:8081/order/deleteOrder/" + row.oid,
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
    changePage: function (index) {
      this.$http
        .get(
          "http://localhost:8081/order/findOrderByState/" +
            index +
            "/" +
            this.state
        )
        .then((res) => {
          this.tableData = res.data.list;
        });
    },
    handleSelectionChange(val) {
      //this.multipleSelection = val // 返回的是选中的列的数组集合
      val.forEach((item) => {
        this.multipleSelection.push(item.oid); //把所有的id放进multipleSelection
      });
      console.log(this.multipleSelection);
    },
  },
};
</script>
  
  <style>
</style>