<template>
  <div>
    <!-- 面包屑 -->
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: 'home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/orderIndex' }"
        >订单总览</el-breadcrumb-item
      >
      <el-breadcrumb-item :to="{ path: '/findOrderMo' }"
        >模糊查询</el-breadcrumb-item
      >
      <el-breadcrumb-item>promotion detail</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="flex">
      <div class="input_box" style="width: 250px">
        <el-input
          v-model="keywords"
          placeholder="请输入用户关键字~"
          class="input-with-select"
          clearable
        >
          <template #append>
            <el-button @click="searchList" icon="el-icon-search"></el-button>
          </template>
        </el-input>
      </div>
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
  name: "order_v",
  inject: ["reload"],
  data() {
    return {
      getRowKeys(row) {
        return row.oid;
      },
      currentRole: "",
      currentPage:1,
      total: 0,
      pageSize: 1,
      keywords: "",
     
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
    this.currentRole = this.$store.getters["uInfo/getUserInfo"].ROLE_ID;

    //先从sessionStorage 取出数据
    // this.uname1=sessionStorage.getItem("uname")
    this.$http.get("http://localhost:8081/order/findOrder/1").then((req) => {
      console.log(req.data);
      this.tableData = req.data.list;
      this.total = req.data.count;
      this.pageSize = req.data.list.length;
    });
   
  },
  methods: {
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
    success: function (row) {
      console.log(row);
      this.$confirm("是否结算?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http
            .get(
              "http://localhost:8081/order/updateTwo/" + row.oid+"/"+row.username,
              { xhrFields: { withCredentials: true } },
              { crossDomain: true }
            )
            .then((res) => {
              if (res != null) {
                this.$message({
                  showClose: true,
                  message: "执行成功,可前往用户管理查看用户资产！",
                  type: "success",
                });
                this.reload();
              }
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消执行!",
          });
        });
    },
    changePage: function (index) {
      this.$http
        .get("http://localhost:8081/order/findOrder/" + index)
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
    searchList: function () {
      this.$router.push({
        name: "findOrderMo",
        params: { keywords: this.keywords },
      });
    },
  },
};
</script>

<style scoped>
.btn {
  text-align: left;
}
.fenye {
  margin-top: 10px;
}
.input_box {
  display: flex;
  width: 200px;
  margin-right: 15px;
  margin-bottom: 10px;
}
.flex {
  float: left;
  margin-top: 15px;
}
.el-button--info {
  color: #fff;
  background-color: #909399;
  border-color: #909399;
  height: 42px;
}
.image {
  height: 80px;
  width: 80px;
}
.upload-demo {
  margin-left: 50px;
}
.userimg {
  text-align: right;
}
.userimg .upload {
  margin-right: 95px;
}
.el-drawer__body {
  flex: 1;
  overflow: auto;
}
.demo-drawer__footer {
  margin: 0px 0px 40px 150px;
}
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
.item {
  margin-top: 5px;
  margin-right: 40px;
}
</style>