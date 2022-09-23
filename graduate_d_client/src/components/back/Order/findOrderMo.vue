<template>
  <div>
    <el-page-header @back="goBack" content="查询页面"> </el-page-header>
    <div class="flex" style="margin-top: 20px; margin-bottom: 20px">
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
      <el-table-column prop="time" label="下单时间" width="160">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          {{ scope.row.time }}
        </template>
      </el-table-column>
      <el-table-column label="支付金额" width="160">
        <template slot-scope="scope">
          <i class="el-icon-info"></i>
          <span style="margin-left: 5px">{{ scope.row.totalsum }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预期收益" width="160">
        <template slot-scope="scope">
          <i class="el-icon-s-claim"></i>
          <span style="margin-left: 10px">{{ scope.row.exincome }}</span>
        </template>
      </el-table-column>
      <el-table-column label="支付方式" width="160">
        <template slot-scope="scope">
          <i class="el-icon-coin"></i>
          <span style="margin-left: 10px">{{ scope.row.payWay.pname }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单状态" width="160">
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
            <span style="margin-left: 10px">{{ scope.row.state.sname }}</span>
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作时间" width="160">
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
    <div class="fenye" style="margin-top: 20px">
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
  name: "findOrder_Mo",
  data() {
    return {
      total: 0,
      pageSize: 1,
      keywords: "",
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
    this.$http
      .get(
        "http://localhost:8081/order/findOrderMo/1/" +
          this.$route.params.keywords
      )
      .then((req) => {
        console.log(req.data);
        if (this.$route.params.keywords != null) {
          this.$message({
            showClose: true,
            message: "查询成功",
            type: "success",
          });
        }
        this.tableData = req.data.list;
        this.total = req.data.count;
        this.pageSize = req.data.list.length;
      });
  },
  methods: {
    goBack() {
      this.$router.push({ name: "orderIndex" });
    },
    changePage: function (index) {
      this.$http
        .get(
          "http://localhost:8081/order/findOrderMo/" +
            index +
            "/" +
            this.$route.params.keywords
        )
        .then((res) => {
          this.tableData = res.data.list;
        });
    },
    searchList: function () {
      this.$http
        .get("http://localhost:8081/order/findOrderMo/1/" + this.keywords)
        .then((req) => {
          this.tableData = req.data.list;
          this.total = req.data.count;
          this.pageSize = req.data.list.length;
        });
    },
  },
};
</script>
  
  <style>
</style>