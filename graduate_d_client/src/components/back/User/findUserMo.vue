<template>
  <div>
    <!-- 面包屑 -->
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: 'home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index' }">用户列表</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/findUserMo' }">模糊查询</el-breadcrumb-item>
      <el-breadcrumb-item>promotion detail</el-breadcrumb-item>
    </el-breadcrumb>
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
      <el-table-column label="头像" width="180">
        <template slot-scope="scope">
          <img class="image" :src="require('@/assets/img/user/' + scope.row.img)" />
        </template>
      </el-table-column>

      <el-table-column label="姓名" width="180">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p style="margin-bottom: 5px">姓名: {{ scope.row.username }}</p>
            <p>联系方式: {{ scope.row.mobile }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.username }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column prop="password" label="密码" width="180">
        <template slot-scope="scope">
          <el-button
            class="view"
            type="text"
            @click.native.prevent="view(scope.row)"
            ><i class="el-icon-view"></i></el-button
          ><span style="margin-left:5px">{{ scope.row.password_d}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="nickname" label="昵称" width="180">
      </el-table-column>
      <el-table-column label="角色" width="180">
        <template slot-scope="scope">
          <i class="el-icon-user"></i>
          <span style="margin-left: 10px">{{ scope.row.role_id.rname }}</span>
        </template>
      </el-table-column>
      <!-- 加状态开关 -->
      <el-table-column prop="state.sname" label="状态" width="180">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.state.sname"
            active-color="#13ce66"
            inactive-color="orange"
            active-value="正常"
            inactive-value="停用"
            disabled
          ></el-switch>
        </template>
      </el-table-column>

      <el-table-column label="余额" width="180">
        <template slot-scope="scope">
          <i class="el-icon-coin"></i>
          <span style="margin-left: 10px">{{ scope.row.moneyid.balance }}</span>
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
  name: "findUserMo",
  data() {
    return {
      
      total: 0,
      pageSize: 0,
      index: 1,
      keywords:'',
      currentPage: 1,
      tableData: [
        {
          id: "",
          username: "",
          password: "",
          password_d: "******",
          img: "",
          nickname: "",
          sex: "",
          createtime: "",
          role_id: {
            rid: "",
            rname: "",
            rdesc: "",
            rdate: "",
            rauthor: "",
          },
          state: {
            sid: 0,
            sname: "",
            sdesc: "",
          },
          mobile: "",
          email: "",
          moneyid: {
            mid: "",
            totalmoney: "",
            income: "",
            pay: "",
            balance: "",
          },
        },
      ],
    };
  },
  mounted() {
    this.$http
      .get("http://localhost:8081/user/findUserMo/1/"+this.$route.params.keywords)
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
        .get("http://localhost:8081/user/findUserMo/" + index+"/"+this.$route.params.keywords)
        .then((res) => {
          this.tableData = res.data.list;

        });
    },
    view(row) {
      //查看密码
      this.tableData.forEach((item,index) => {
        this.$set(this.tableData[index], 'password_d', '******') 
        if (item.password_d == "******" && item.id == row.id) {
          item.password_d = item.password;
        } else if (item.password_d != "******" && item.id == row.id) {
          item.password_d = "******";
        }
      });
    },
    searchList: function () {
       this.$http
      .get("http://localhost:8081/user/findUserMo/1/"+this.keywords)
      .then((req) => {
        this.tableData = req.data.list;
        this.total = req.data.count;
        this.pageSize = req.data.list.length;
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
.flex {
  float: left;
  margin-top: 15px;
}
.input_box {
  display: flex;
  width: 200px;
  margin-right: 15px;
  margin-bottom: 10px;
}
.image {
  height: 80px;
  width: 80px;
}
</style>