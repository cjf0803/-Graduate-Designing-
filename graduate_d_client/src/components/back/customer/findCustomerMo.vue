<template>
  <div>
    <div class="flex">
      <div class="input_box">
        <el-input
          v-model="keywords"
          placeholder="请输入关键字"
          class="input-with-select"
          
        >
          <template #append>
            <el-button @click="searchList" icon="el-icon-search"></el-button>
          </template>
        </el-input>
      </div>
    </div>
    
    
    <el-table :data="tableData" style="width: 100%; text-align: center">
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
          <img class="image" :src="require('../../../assets/img/' + scope.row.path)" />
        </template>
      </el-table-column>
       <el-table-column label="操作">
          <!-- scope作用域插槽，可以通过scope绑定当前行的数据 -->
          <template slot-scope="scope">
            <el-button
              @click="delUser(scope.row)"
              type="primary"
              icon="el-icon-notebook-2"
              
              >借书</el-button
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
    </div>
  </div>
</template>

<script>
export default {
  name: "findCustomerMo",
  data() {
    return {
      uname: "",
      total: 0,
      pageSize: 0,
      index: 1,
      keywords:'',
      currentPage: 1,
      tableData: [
        {
          id: '',
          bookname: "",
          author: "",
          pressname: '',
          bookStatus:{
            status_id:'',
            status_name:''
          },
          path:""
        },
      ],
    };
  },
  mounted() {
    this.uname = this.$store.state.uname;
    //先从sessionStorage 取出数据
    // this.uname1=sessionStorage.getItem("uname")
    this.$http
      .get("http://localhost:8081/book/findBookMo?index=1&bookname="+this.$route.params.keywords)
      .then((req) => {
        console.log(req.data);
        if(this.$route.params.keywords!=null){
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
    changePage: function (index) {
      this.$http
        .get("http://localhost:8081/book/findBookMo?index=" + index+"&bookname="+this.$route.params.keywords)
        .then((res) => {
          this.tableData = res.data.list;

        });
    },
    searchList: function () {
       this.$http
      .get("http://localhost:8081/book/findBookMo?index=1&bookname="+this.keywords)
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
</style>