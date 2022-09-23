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
      ref="tableData"
      :data="tableData"
      :row-key="getRowKeys"
      tooltip-effect="dark"
      style="width: 100%; "
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" :reserve-selection="true"></el-table-column>
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
      <el-table-column label="单份价格" sortable width="140px">
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
            disabled
          ></el-input-number>
        </template>
      </el-table-column>
      <el-table-column label="小计" sortable width="150px">
        <template slot-scope="scope">
          <i class="el-icon-coin"></i>&nbsp;
          <span style="color: darkorange; font-weight: bolder"
            >{{ scope.row.sum }}元</span
          >
        </template>
      </el-table-column>
      <el-table-column label="预购者" width="150px">
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
    <el-button @click="delAll" style="float: left; margin-left: 12px; margin-top: 15px"
      >批量删除</el-button
    >
    <el-button
      @click="clear"
      style="float: left; margin-left: 13px; margin-top: 15px"
      >清空选择</el-button
    >

    <div class="fenye" style="margin-top: 20px;text-align: right;">
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
    </div>
  </div>
</template>

<script>
export default {
  name: "findCart_Mo",
  inject: ["reload"],
  data() {
    return {
        getRowKeys(row) {
        return row.cid;
      },
      multipleSelection: [],
      total: 1,
      pageSize: 1,
      currentPage: 1,
      keywords:"",
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
        },
      ],
    };
  },
  mounted() {
    this.$http
      .get(
        "http://localhost:8081/cart/findCartMo/1/" + this.$route.params.keywords
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
  methods:{
    goBack() {
      this.$router.go(-1);
    },
    delAll:function () {
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
    clearSelection() {
      this.$nextTick(() => {
        this.$refs.tableData.clearSelection(); //elementUI 官方文档的方法 不过直接使用会报错
        this.multipleSelection=[];
      });
    },
    changePage: function (index) {
      this.$http
        .get("http://localhost:8081/cart/findCartMo/" + index+"/"+  this.$route.params.keywords)
        .then((res) => {
          this.tableData = res.data.list;
        });
    },
   

    handleSelectionChange(val) {
      //this.multipleSelection = val // 返回的是选中的列的数组集合
      val.forEach((item) => {
        this.multipleSelection.push(item.cid); //把所有的id放进multipleSelection
      });
      console.log(this.multipleSelection);
    },
    searchList: function () {
      this.$http
        .get("http://localhost:8081/cart/findCartMo/1/" + this.keywords)
        .then((req) => {
          this.tableData = req.data.list;
          this.total = req.data.count;
          this.pageSize = req.data.list.length;
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
                  type: "warning",
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
  }
};
</script>

<style>
</style>