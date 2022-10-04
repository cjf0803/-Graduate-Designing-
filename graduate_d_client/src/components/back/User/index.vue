<template>
  <div id="box">
    <!-- 面包屑 -->
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: 'home' }" >首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index' }">用户列表</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/findUserMo' }"
        >模糊查询</el-breadcrumb-item
      >
      <el-breadcrumb-item>promotion detail</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="flex">
      <div class="input_box">
        <el-input
          v-model="keywords"
          placeholder="请输入关键字"
          class="input-with-select"
          clearable
        >
          <template #append>
            <el-button @click="searchList" icon="el-icon-search"></el-button>
          </template>
        </el-input>
      </div>
    </div>
    <!-- <div >
      <div class="btn">
        <el-button
          type="primary"
          icon="el-icon-circle-plus-outline"
          @click="addUser"
         
          >添加用户</el-button
        >
      </div>
      
    </div> -->
    <el-table :data="tableData" style="width: 100%; text-align: center">
      <el-table-column label="头像" width="120">
        <template slot-scope="scope">
          <img
            class="image"
            :src="require('@/assets/img/user/' + scope.row.img)"
          />
        </template>
      </el-table-column>

      <el-table-column label="姓名" width="140">
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
      <el-table-column prop="password" label="密码" width="130">
        <template slot-scope="scope">
          <el-button
            class="view"
            type="text"
            @click.native.prevent="view(scope.row)"
            ><i class="el-icon-view"></i></el-button
          ><span style="margin-left: 5px">{{ scope.row.password_d }}</span>
        </template>
      </el-table-column>
      <el-table-column label="昵称" width="170">
        <template slot-scope="scope">
          <i class="el-icon-info"></i>
          <span style="margin-left: 5px">{{ scope.row.nickname }}</span>
        </template>
      </el-table-column>
      <el-table-column label="角色" width="170">
        <template slot-scope="scope">
          <i class="el-icon-user"></i>
          <span style="margin-left: 10px">{{ scope.row.role_id.rname }}</span>
        </template>
      </el-table-column>
      <!-- 加状态开关 -->
      <el-table-column prop="state.sname" label="状态" width="160">
        <template slot-scope="scope">
          <el-switch
          :disabled="scope.row.role_id.rid < currentRole ? false: true"
            v-model="scope.row.state.sname"
            active-color="#13ce66"
            inactive-color="orange"
            active-value="正常"
            inactive-value="停用"
            @change="switchChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>

      <el-table-column label="余额" width="160">
        <template slot-scope="scope">
          <i class="el-icon-coin"></i>
          <span style="margin-left: 10px">{{ scope.row.moneyid.balance }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <!-- scope作用域插槽，可以通过scope绑定当前行的数据 -->
        <template slot-scope="scope">
          <!-- v-if="scope.row.role_id.rid<currentRole" -->
          <el-tooltip
            effect="dark"
            :disabled="scope.row.role_id.rid < currentRole ? true: false"
            content="用户权限不足"
            placement="left"
            transition
          >
          <span style="margin-right:7px">
            <el-button
              :disabled="scope.row.role_id.rid < currentRole ? false : true"
              @click="delUser(scope.row)"
              type="danger"
              icon="el-icon-delete"
              >删除</el-button
            >
          </span>
          </el-tooltip>
         
          <el-button
            :disabled="scope.row.role_id.rid < currentRole ? false : true"
            @click="updateUser(scope.row)"
            type="info"
            icon="el-icon-info"
            >修改</el-button
          >
        
          <el-tooltip
            effect="dark"
            :disabled="scope.row.role_id.rid < currentRole ? true: false"
            content="用户权限不足"
            placement="bottom-end"
            transition
          >
          <span>
          <el-button
            :disabled="scope.row.role_id.rid < currentRole ? false : true"
            @click="detail(scope.row)"
            type="primary"
            icon="el-icon-info"
            style="margin: 9px 0px 0px 47px"
            >详情</el-button
          >
          </span>
          </el-tooltip>
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
    <!-- 编辑弹窗(修改用户信息)  rules有问题(已解决)-->
    <el-drawer
      title="用户信息修改"
      :visible.sync="dialogFormEVisible"
      direction="rtl"
      custom-class="demo-drawer"
      ref="drawer"
    >
      <div class="demo-drawer__content">
        <!-- <el-dialog title="修改用户信息" :visible.sync="dialogFormEVisible"> -->
        <el-form :model="ruleForm" ref="ruleForm" :rules="rules">
          <el-form-item
            label="用户名"
            prop="username"
            :label-width="formLabelWidth"
          >
            <el-input
              v-model="ruleForm.username"
              @input="change($event)"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item
            label="密码"
            prop="password"
            :label-width="formLabelWidth"
          >
            <el-input
              v-model="ruleForm.password"
              @input="change($event)"
              clearable
              show-password
            ></el-input>
          </el-form-item>
          <el-form-item
            label="昵称"
            prop="nickname"
            :label-width="formLabelWidth"
          >
            <el-input
              v-model="ruleForm.nickname"
              @input="change($event)"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="sex" :label-width="formLabelWidth">
            <el-input
              v-model="ruleForm.sex"
              @input="change($event)"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item
            label="角色"
            prop="role_id"
            :label-width="formLabelWidth"
          >
            <el-select
              v-model="ruleForm.role_id"
              clearable
              placeholder="请选择类型哇~"
              style="width: 100%"
            >
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="联系方式"
            prop="mobile"
            :label-width="formLabelWidth"
          >
            <el-input
              v-model="ruleForm.mobile"
              @input="change($event)"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email" :label-width="formLabelWidth">
            <el-input
              v-model="ruleForm.email"
              @input="change($event)"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item class="userimg" prop="img">
            <el-upload
              class="upload-demo"
              action="http://localhost:8081/upload/user"
              :file-list="fileList"
              list-type="picture"
              :on-success="handleAvatarSuccess"
            >
              <el-button size="small" type="primary" class="upload"
                >点击上传</el-button
              >
              <div slot="tip" class="el-upload__tip">
                只能上传jpg/png文件，且不超过10MB
              </div>
            </el-upload>
          </el-form-item>
        </el-form>
        <div class="demo-drawer__footer">
          <el-button @click="dialogFormEVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitForm('ruleForm')"
            >确 定</el-button
          >
        </div>
      </div>
    </el-drawer>
    <!-- 详情信息查看 yi解决 -->
    <el-drawer
      title="详情信息"
      :visible.sync="dialogFormDisible"
      :before-close="handleClose"
      direction="btt"
      custom-class="demo-drawer"
      ref="drawer"
    >
      <el-table :data="gridData">
        <el-table-column
          property="id"
          label="编号"
          width="100"
        ></el-table-column>
        <el-table-column label="头像" width="120">
          <template slot-scope="scope">
            <img
              style="height: 50px; width: 50px"
              :src="require('@/assets/img/user/' + scope.row.img)"
            />
          </template>
        </el-table-column>
        <el-table-column
          property="username"
          label="用户名"
          width="100"
        ></el-table-column>
        <el-table-column
          property="password"
          label="密码"
          width="100"
        ></el-table-column>
        <el-table-column
          property="nickname"
          label="昵称"
          width="100"
        ></el-table-column>
        <el-table-column
          property="sex"
          label="性别"
          width="100"
        ></el-table-column>
        <el-table-column
          property="mobile"
          label="联系方式"
          width="150"
        ></el-table-column>
        <el-table-column
          property="email"
          label="邮箱"
          width="200"
        ></el-table-column>
        <el-table-column
          property="createtime"
          label="创建时间"
          width="200"
        ></el-table-column>
        <el-table-column
          property="role_id"
          label="角色id"
          width="100"
        ></el-table-column>
        <el-table-column
          property="rname"
          label="角色"
          width="100"
        ></el-table-column>
        <el-table-column
          property="sname"
          label="状态"
          width="100"
        ></el-table-column>
        <el-table-column
          property="moneyid"
          label="资产id"
          width="100"
        ></el-table-column>
        <el-table-column
          property="totalmoney"
          label="总资产"
          width="100"
        ></el-table-column>
        <el-table-column
          property="pay"
          label="支出"
          width="100"
        ></el-table-column>
        <el-table-column
          property="income"
          label="收入"
          width="100"
        ></el-table-column>
        <el-table-column
          property="balance"
          label="余额"
          width="100"
        ></el-table-column>
      </el-table>
    </el-drawer>
  </div>
</template>

<script>

export default {
  name: "index_v",
  inject: ["reload"],
  data() {
    return {
      gridData: [
        {
          id: "",
          username: "",
          password: "",
          img: "",
          nickname: "",
          sex: "",
          role_id: "",
          mobile: "",
          email: "",
          createtime: "",
          rname: "",
          sname: "",
          balance: "",
          income: "",
          moneyid: "",
          totalmoney: "",
          pay: "",
        },
      ],
      formLabelWidth: "120px",
      uname: "",
      total: 0,
      keywords: "",
      pageSize: 1,
      index: 1,
      currentPage: 1,
      currentRole: "",
      disabled: false,
      dialogFormEVisible: false,
      dialogFormVisible: false,
      dialogFormDisible: false,
      // 获取每一行的数据
      index_x: [],
      rules: {
        username: [
          { required: true, message: "此项为必填项", trigger: "blur" },
        ],
        password: [
          { required: true, message: "此项为必填项", trigger: "blur" },
        ],
        nickname: [
          { required: true, message: "此项为必填项", trigger: "blur" },
        ],
        sex: [{ required: true, message: "此项为必填项", trigger: "blur" }],
        role_id: [{ required: true, message: "此项为必填项", trigger: "blur" }],
        mobile: [
          {
            required: false,
            pattern: /^1(2|3|4|5|6|7|8|9)\d{9}$/,
            message: "请填写正确手机号",
            trigger: "blur",
          },
        ],
        email: [
          {
            required: false,
            pattern:
              /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
            message: "请填写正确邮箱",
            trigger: "blur",
          },
        ],
      },
      fileList: [
        {
          name: "点击上传进行修改头像",
          url: "",
        },
      ],
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
      ruleForm: {
        id: "",
        username: "cjf",
        password: "",
        img: "",
        nickname: "",
        sex: "",
        role_id: "",
        mobile: "",
        email: "",
        createtime: "",
        rname: "",
        sname: "",
        balance: "",
        income: "",
        moneyid: "",
        totalmoney: "",
        pay: "",
      },
      options: [
        {
          value: "0",
          label: "普通用户",
        },
        {
          value: "1",
          label: "工作人员",
        },
        {
          value: "2",
          label: "管理员",
        },
        {
          value: "3",
          label: "经理",
        },
        {
          value: "4",
          label: "boss",
        },
      ],
    };
  },
  mounted() {
    this.currentRole = this.$store.getters["uInfo/getUserInfo"].ROLE_ID;

    //先从sessionStorage 取出数据
    // this.uname1=sessionStorage.getItem("uname")
    this.$http.get("http://localhost:8081/user/findUser/1").then((req) => {
      console.log(req.data);
      this.tableData = req.data.list;
      this.total = req.data.count;
      this.pageSize = req.data.list.length;
    });
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.ruleForm.img = res; //从后台获取filename给path赋值
      this.fileList.name = res;
      this.fileList.url = URL.createObjectURL(file.raw);
    },
    change() {
      this.$forceUpdate();
    },
    changePage: function (index) {
      this.$http
        .get("http://localhost:8081/user/findUser/" + index)
        .then((res) => {
          this.tableData = res.data.list;
        });
    },
    delUser: function (row) {
      console.log(row);
      this.$confirm("此操作将永久删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http
            .get(
              "http://localhost:8081/user/deleteUser/" + row.id,
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
    // 点击查看密码
    view(row) {
      //查看密码
      this.tableData.forEach((item, index) => {
        this.$set(this.tableData[index], "password_d", "******");
        if (item.password_d == "******" && item.id == row.id) {
          item.password_d = item.password;
        } else if (item.password_d != "******" && item.id == row.id) {
          item.password_d = "******";
        }
      });
    },
    updateUser: function (row) {
      this.dialogFormEVisible = true;
      this.ruleForm = {
        id: row.id,
        username: row.username,
        password: row.password,
        img: row.img,
        nickname: row.nickname,
        sex: row.sex,
        role_id: row.role_id.rid,
        mobile: row.mobile,
        email: row.email,
      };
    },
    switchChange: function (row) {
      if (row.state.sid == 1) {
        this.$http
          .get(
            "http://localhost:8081/user/updateState/" + row.id + "/" + 0,
            { xhrFields: { withCredentials: true } },
            { crossDomain: true }
          )
          .then((res) => {
            if (res.data != null) {
              this.$router.push({ name: "index" }).catch(() => {});
            }
          });
      } else if (row.state.sid == 0) {
        this.$http
          .get(
            "http://localhost:8081/user/updateState/" + row.id + "/" + 1,
            { xhrFields: { withCredentials: true } },
            { crossDomain: true }
          )
          .then((res) => {
            if (res.data != null) {
              this.$router.push({ name: "index" }).catch(() => {});
            }
          });
      }
    },
    detail: function (row) {
      console.log(row);
      this.gridData[0] = {
        id: row.id,
        username: row.username,
        password: row.password,
        img: row.img,
        nickname: row.nickname,
        sex: row.sex,
        createtime: row.createtime,
        role_id: row.role_id.rid,
        rname: row.role_id.rname,
        sname: row.state.sname,
        balance: row.moneyid.balance,
        income: row.moneyid.income,
        moneyid: row.moneyid.mid,
        totalmoney: row.moneyid.totalmoney,
        pay: row.moneyid.pay,
        mobile: row.mobile,
        email: row.email,
      };
      this.dialogFormDisible = true;
    },
    upload: function () {
      this.$router.push({ name: "upload" });
    },
    duobiao: function () {
      this.$router.push({ name: "duobiao" });
    },
    searchList: function () {
      this.$router.push({
        name: "findUserMo",
        params: { keywords: this.keywords },
      });
    },
    handleClose() {
      this.dialogFormDisible = false;
      //this.$refs.drawer.closeDrawer();
      // 页面逻辑执行完毕后调用reload方法
      this.reload();
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm("是否修改？", "tips", {
            confirmButtonText: "OK",
            cancelButtonText: "Cancel",
            type: "warning",
          })
            .then(() => {
              let id = this.ruleForm.id;
              let username = this.ruleForm.username;
              let password = this.ruleForm.password;
              let img = this.ruleForm.img;
              let nickname = this.ruleForm.nickname;
              let sex = this.ruleForm.sex;
              let role_id = this.ruleForm.role_id;
              let mobile = this.ruleForm.mobile;
              let email = this.ruleForm.email;
              this.$http
                .get(
                  "http://localhost:8081/user/updateUser/" +
                    id +
                    "/" +
                    username +
                    "/" +
                    password +
                    "/" +
                    img +
                    "/" +
                    nickname +
                    "/" +
                    sex +
                    "/" +
                    role_id +
                    "/" +
                    mobile +
                    "/" +
                    email,
                  { xhrFields: { withCredentials: true } },
                  { crossDomain: true }
                )
                .then((req) => {
                  if (req.data != null) {
                    this.dialogFormEVisible = false;
                    this.$refs.drawer.closeDrawer();
                    this.$message({
                      showClose: true,
                      message: "修改成功",
                      type: "success",
                    });
                    setTimeout(() => {
                      location.reload();
                    }, 333);
                  }
                });
            })
            .catch(() => {
              this.$message({
                type: "info",
                message: "已取消修改!",
              });
            });
        } else {
          return false;
        }
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
  margin: 0px 0px 40px 70px;
}
</style>