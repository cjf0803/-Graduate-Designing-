<template>
  <div>
    <el-container class="index-container">
      <!--头部-->
      
      <el-header style="height: 100px">
        <div class="head-logo">
          <img src="../../../assets/logo.png" />
          <span style="font-family: fantasy; font-size: large"
            >普通用户借还书</span
          >
        </div>
        <div class="head-button">
          <p
            style="
              float: left;
              margin: 7px 10px 0px;
              font-family: fantasy;
              font-size: large;
            "
          >
            欢迎 {{ username }} 登录
           
          </p>
          <el-button type="success" @click="updateUser" icon="el-icon-tickets"
            >信息修改</el-button
          >
          <el-button type="info" @click="findByUsername" icon="el-icon-discount"
            >查看个人借书</el-button
          >
          <el-button
            type="danger"
            @click="loginOut"
            icon="el-icon-switch-button"
            >退出</el-button
          >
        </div>
      </el-header>
    </el-container>

    <div class="cjf">
      <div class="flex">
        <div class="input_box" style="width: 250px">
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
        <el-table-column prop="id" label="图书编号" width="210px">
        </el-table-column>
        <el-table-column prop="bookname" label="书名" width="210px">
        </el-table-column>
        <el-table-column prop="author" label="作者" width="210px">
        </el-table-column>
        <el-table-column prop="pressname" label="出版社" width="210px">
        </el-table-column>
        <el-table-column
          prop="bookStatus.status_name"
          label="借还状态"
          width="210px"
        >
        </el-table-column>
        <el-table-column width="210px"  label="书籍图片">
          <template slot-scope="scope">
            <img
              style="width:100px;height:100px"
              :src="require('../../../assets/img/' + scope.row.path)"
            />
          </template>
        </el-table-column>
        <el-table-column label="操作" >
          <!-- scope作用域插槽，可以通过scope绑定当前行的数据 -->
          <template slot-scope="scope">
            <el-button
              v-if ="scope.row.bookStatus.status_name=='未借'"
              @click="lend(scope.row)"
              type="primary"
              icon="el-icon-notebook-2"
             
              >借书</el-button
            >
          </template>
         
        </el-table-column>
      </el-table>
      <!-- 编辑弹窗 -->
      <el-dialog title="修改信息" :visible.sync="dialogFormEVisible">
        <el-form V-model="formData">
          <el-form-item label="用户名" :label-width="formLabelWidth">
            <el-input
              v-model="formData.username"
              readonly="true"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item label="密码" :label-width="formLabelWidth">
            <el-input
              v-model="formData.password"
              @input="change($event)"
            ></el-input>
          </el-form-item>
          <el-form-item label="昵称" :label-width="formLabelWidth">
            <el-input
              v-model="formData.realname"
              @input="change($event)"
            ></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormEVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitForm()">确 定</el-button>
        </div>
      </el-dialog>
      <!-- 个人借书情况 -->
      <el-dialog title="个人借书情况" :visible.sync="dialogFormVisible" width="70%">
        <el-table :data="LendData">
          <el-table-column property="bookid" label="图书编号"></el-table-column>
          <el-table-column property="bookname" label="书名"></el-table-column>
          <el-table-column property="author" label="作者"></el-table-column>
          <el-table-column property="realname" label="昵称"></el-table-column>
          <el-table-column
            property="lenddate"
            label="借书日期"
          ></el-table-column>
          <el-table-column
            property="returnDate"
            label="还书日期"
          ></el-table-column>
          <el-table-column property="debit" label="余额"></el-table-column>
          <el-table-column
            prop="bookStatus.status_name"
            label="借还状态"
           
          >
          </el-table-column>
          <el-table-column prop="username" label="借书者" >
          </el-table-column>
          <el-table-column label="操作" >
        <!-- scope作用域插槽，可以通过scope绑定当前行的数据 -->
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.returnDate==null"
            @click="reback1(scope.row)"
            type="success"
            icon="el-icon-delete"
            class="btn"
            style="margin-top:2px;"
            >还书</el-button
          >
          </template>
          </el-table-column>
        </el-table>
      </el-dialog>
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

    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "welcome_v",
  data() {
    return {
      formLabelWidth: "120px",
      username: "",
      total: 0,
      pageSize: 0,
      currentPage: 1,
      keywords: "",
      dialogFormEVisible: false,
      dialogFormVisible: false,
      lendVisible:false,
      tableData: [
        {
          id: "",
          bookname: "",
          author: "",
          pressname: "",
          bookStatus: {
            status_id: "",
            status_name: "",
          },
          path: "",
        },
      ],
      rules: {
        username: [
          { required: true, message: " 此项不能为空", trigger: "blur" },
        ],
        password: [
          { required: true, message: " 此项不能为空", trigger: "blur" },
        ],
        realname: [
          { required: true, message: " 此项不能为空", trigger: "blur" },
        ],
      },

      formData: [
        {
          username: "",
          password: "",
          realname: "",
        },
      ],
      LendData: [
        {
          id: "",
          bookid: "",
          bookname: "",
          author: "",
          userid: "",
          realname: "",
          lenddate: "",
          returnDate: "",
          debit: "",
          bookStatus: {
            status_id: "",
            status_name: "",
          },
          username: "",
        },
      ],
    };
  },
  mounted() {
    this.username = sessionStorage.getItem("username");
    
    this.$http
      .get(
        "http://localhost:8081/book/findBook?index=1",
        { xhrFields: { withCredentials: true } },
        { crossDomain: true }
      )
      .then((req) => {
       
        this.tableData = req.data.list1;
        this.total = req.data.count;
        this.index = req.data.index;
        this.pageSize = req.data.list1.length;
       
       
       
      });
      //  if(this.tableData.bookStatus&&this.tableData.bookStatus.status_name=="已借"){
      //     console.log("111")
      //     this.lendVisible=true;
      // }
      
      
        
    this.$http
      .get(
        "http://localhost:8081/user/findByUsername?username=" + this.username
      )
      .then((res) => {
        
        if (res) {
          this.formData.username = res.data.username;
          this.formData.password = res.data.password;
          this.formData.realname = res.data.realname;
        }
      });
       this.$http
      .get(
        "http://localhost:8081/lend/findLend"
      )
      .then((res) => {
        console.log(res.data);
        if (res.data.list!=null) {
          this.LendData=res.data.list
         
        }
       
      });
    
  },
  methods: {
    reback1:function(row){
      let bookid =row.bookid
       this.$confirm("是否归还本书, 还不?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http
            .get(
              "http://localhost:8081/lend/reback?bookid="+bookid,
              { xhrFields: { withCredentials: true } },
              { crossDomain: true }
            )
            .then((res) => {
              console.log(res);
              if (res.data == true) {
                this.$router.push({ name: "welcome" });
                 this.$message({
                  showClose: true,
                  message: "还书成功",
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
            message: "已取消操作!",
          });
        });
    },
    change() {
      this.$forceUpdate();
    },
    lend:function(row){
      let id=row.id
       this.$confirm("是否借此本书, 借不?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http
            .get(
              "http://localhost:8081/book/addLend?id="+id,
              { xhrFields: { withCredentials: true } },
              { crossDomain: true }
            )
            .then((res) => {
              console.log(res);
              if (res.data == true) {
                this.$router.push({ name: "welcome" });
                 this.$message({
                  showClose: true,
                  message: "借书成功",
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
            message: "已取消操作!",
          });
        });
    },
    loginOut: function () {
      this.$confirm("退出当前用户, 是否退出?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http
            .get(
              "http://localhost:8081/user/loginOut",
              { xhrFields: { withCredentials: true } },
              { crossDomain: true }
            )
            .then((res) => {
              console.log(res);
              if (res.data == true) {
                this.$router.push({ name: "login" });
              }
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消操作!",
          });
        });
    },
    changePage: function (index) {
      this.$http
        .get("http://localhost:8081/book/findBook?index=" + index)
        .then((res) => {
          this.tableData = res.data.list1;
        });
    },
    searchList: function () {
      this.$router.push({
        name: "findCustomerMo",
        params: { keywords: this.keywords },
      });
    },
    updateUser: function () {
      this.dialogFormEVisible = true;
    },
    submitForm() {
      let username = this.formData.username;
      let password = this.formData.password;
      let realname = this.formData.realname;

      this.$http
        .get(
          "http://localhost:8081/user/updateUserByUsername?username=" +
            username +
            "&password=" +
            password +
            "&realname=" +
            realname,
          { xhrFields: { withCredentials: true } },
          { crossDomain: true }
        )
        .then((req) => {
          if (req.data != null) {
            this.dialogFormEVisible = false;
            this.$message({
              showClose: true,
              message: "修改成功",
              type: "success",
            });
          }
        });
    },
    findByUsername:function(){
      this.dialogFormVisible = true;
      
    }
  },
};
</script>

<style scoped>
.index-container {
  margin-top: -30px;
}
.el-header {
  background-color: rgb(149, 210, 230);
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  align-items: center;
  color: #fff;
  height: 210px;
}

.head-logo {
  display: flex;
  align-items: center;
}
.head-logo img {
  width: 80px;
  height: 80px;
  margin-left: 10px;
}
.font-left {
  font-size: 20px;
  color: white;
  font-weight: bolder;
  margin-left: -920px;
  margin-top: 10px;
}
.head-button {
  align-items: center;
}
.el-aside {
  background-color: #333744;
}
.el-main {
  background-color: #eaedf1;
}
.cjf {
  margin-top: 10px;
}
.el-button{
  text-align: left;
}
button.el-button.el-button--primary {
    margin-top: -50px;
    height: 37px;
   
}
</style>