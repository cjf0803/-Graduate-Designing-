<template>
  <div>
     <div class="flex">
      <div class="input_box" style="width:250px">
        <el-input
          v-model="keywords"
          placeholder="请输入图书关键字"
          class="input-with-select"
          clearable
          
        >
          <template #append>
            <el-button @click="searchList" icon="el-icon-search"></el-button>
          </template>
        </el-input>
      </div>
    </div>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="id" label="图书编号" width="180px"> </el-table-column>
      <el-table-column prop="bookname" label="书名" width="180px">
      </el-table-column>
      <el-table-column prop="author" label="作者" width="180px"> </el-table-column>
      <el-table-column prop="pressname" label="出版社" width="180px">
      </el-table-column>
      <el-table-column prop="bookStatus.status_name" label="借还状态" width="180px">
      </el-table-column>
      <el-table-column width="180px" label="书籍图片">
        <template slot-scope="scope" >
          <img class="image" :src="require('@/assets/img/' + scope.row.path)" />
        </template>
      </el-table-column>
      <el-table-column label="操作" >
        <!-- scope作用域插槽，可以通过scope绑定当前行的数据 -->
        <template slot-scope="scope">
          <el-button
            @click="delUser(scope.row)"
            type="primary"
            icon="el-icon-delete"
            class="btn"
            >删除</el-button
          >
          <el-button
            @click="updateUser(scope.row)"
            type="info"
            icon="el-icon-info"
            style="margin-left:0px"
            >修改</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div class="fenye">
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
  name: "bookIndex",
  data() {
    return {
      total: 0,
      pageSize: 0,
      currentPage: 1,
      keywords:"",
      tableData: [
        {
          id: "",
          bookname: "",
          author: "",
          pressname: "",
          bookStatus: {
            status_id:"",
            status_name: "",
          },
          path: "",
        },
      ],
    };
  },
  mounted() {
    this.$http
      .get(
        "http://localhost:8081/book/findBook?index=1",
        { xhrFields: { withCredentials: true } },
        { crossDomain: true }
      )
      .then((req) => {
        console.log(req.data);
        this.tableData = req.data.list1;
        this.total = req.data.count;
        this.index = req.data.index;
        this.pageSize = req.data.list1.length;
      });
  },
  methods: {
    changePage: function (index) {
      this.$http
        .get("http://localhost:8081/book/findBook?index=" + index)
        .then((res) => {
          this.tableData = res.data.list1;
        });
    },
    delUser:function(row){
      console.log(row);
      this.$confirm("此操作将永久删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http
            .get(
              "http://localhost:8081/book/deleteBook?id=" + row.id,
              { xhrFields: { withCredentials: true } },
              { crossDomain: true }
            )
            .then((res) => {
              if (res != null) {
                this.$router.push({ name: "bookIndex" });
                this.$message({
                  showClose: true,
                  message: "删除成功",
                  type: "success",
                });
                setTimeout(() => {
                  window.location.reload();
                }, 777);
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
    updateUser: function (row) {
      this.$router.push({ name: "updateBook", params: { id: row.id } });
    },
    searchList:function(){
          this.$router.push({ name: "findBookMo",params:{keywords:this.keywords} });
    }

  },
};
</script>

<style scoped>
.fenye {
  margin-top: 10px;
}
.image{
  width:80px;
  height: 80px;
}
.flex{
  float: left;
}
.input_box {
  display: flex;
  width: 200px;
  margin-right: 15px;
  margin-bottom: 10px;
}

button.el-button.btn.el-button--primary {
    margin-bottom: 10px;
    margin-top: 10px;
}
button.el-button.btn.el-button--primary {
    width: 91px;
    height: 42px;
}
</style>