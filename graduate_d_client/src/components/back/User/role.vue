<template>
  <div>
    <!-- 面包屑 -->
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: 'home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index' }">用户列表</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/role' }"> 角色管理</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/add' }"> 添加用户</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/findUserMo' }"
        >模糊查询</el-breadcrumb-item
      >
      <el-breadcrumb-item>promotion detail</el-breadcrumb-item>
    </el-breadcrumb>

    <div style="margin: 20px 0px 20px 0px; text-align: left">
      <el-button
        type="primary"
        icon="el-icon-circle-plus-outline"
        @click="addRole"
        >添加角色</el-button
      >
    </div>
    <el-table :data="tableData" style="width: 100%; text-align: center">
      <el-table-column prop="rid" label="编号" width="100"> </el-table-column>
      <el-table-column label="角色名称" width="150">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.rname }}</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="角色描述" width="300">
        <template slot-scope="scope">
          <i class="el-icon-chat-line-round"></i>
          <span style="margin-left: 10px">{{ scope.row.rdesc }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改/添加时间" width="220">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.rdate }}</span>
        </template>
      </el-table-column>
      <el-table-column label="执行者" width="220">
        <template slot-scope="scope">
          <i class="el-icon-s-custom"></i>
          <span style="margin-left: 10px">{{ scope.row.rauthor }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <!-- scope作用域插槽，可以通过scope绑定当前行的数据 -->
        <template slot-scope="scope">
          <el-button
            @click="delRole(scope.row)"
            type="danger"
            icon="el-icon-delete"
            >删除</el-button
          >
          <el-button
            @click="updateRole(scope.row)"
            type="info"
            icon="el-icon-info"
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
    </div>
    <el-drawer
      title="角色信息修改"
      :visible.sync="dialogFormEVisible"
      direction="rtl"
      custom-class="demo-drawer"
      ref="drawer"
    >
      <div class="demo-drawer__content">
        <el-form :model="ruleForm" ref="ruleForm" :rules="rules">
          <el-form-item
            label="角色名称"
            prop="rname"
            :label-width="formLabelWidth"
          >
            <el-input
              v-model="ruleForm.rname"
              @input="change($event)"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item
            label="角色描述"
            prop="rdesc"
            :label-width="formLabelWidth"
          >
            <el-input
              v-model="ruleForm.rdesc"
              @input="change($event)"
              clearable
            ></el-input>
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
    <el-dialog title="添加角色" :visible.sync="dialogFormVisible">
        <el-form :model="userData" :rules="rules" ref="userData">
          <el-form-item
            label="角色名称"
            prop="rname"
            :label-width="formLabelWidth"
          >
            <el-input
              v-model="userData.rname"
              @input="change($event)"
              autocomplete="off"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item
            label="角色描述"
            prop="rdesc"
            :label-width="formLabelWidth"
          >
            <el-input
              v-model="userData.rdesc"
              @input="change($event)"
              autocomplete="off"
              clearable
            ></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="cancel">取 消</el-button>
          <el-button type="primary" @click="submitForm1('userData')"
            >确 定</el-button
          >
        </div>
      </el-dialog>
  </div>
</template>

<script>
export default {
  name: "role_v",
  inject: ["reload"],
  data() {
    return {
      formLabelWidth: "120px",
      total: 0,
      pageSize: 1,
      currentPage:1,
      dialogFormEVisible: false,
      dialogFormVisible:false,
      rules: {
        rname: [{ required: true, message: "此项为必填项", trigger: "blur" }],
        rdesc: [{ required: true, message: "此项为必填项", trigger: "blur" }],
      },
      userData:{
        rname:"",
        rdesc:""
      },
      tableData: [
        {
          rid: "",
          rname: "",
          rdesc: "",
          rauthor: "",
        },
      ],
      ruleForm: {
        rid: "",
        rname: "",
        rdesc: "",
        rauthor: "",
      },
    };
  },
  mounted() {
    this.$http.get("http://localhost:8081/userRole/findRole/1").then((req) => {
      console.log(req.data);
      this.tableData = req.data.list;
      this.total = req.data.count;
      this.pageSize = req.data.list.length;
    });
  },
  methods: {
    change() {
      this.$forceUpdate();
    },
    changePage: function (index) {
      this.$http
        .get("http://localhost:8081/userRole/findRole/" + index)
        .then((res) => {
          this.tableData = res.data.list;
        });
    },
    updateRole: function (row) {
      this.dialogFormEVisible = true;
      this.ruleForm = {
        rid: row.rid,
        rname: row.rname,
        rdesc: row.rdesc,
        rdate: row.rdate,
        rauthor: row.rauthor,
      };
    },
    delRole: function (row) {
      console.log(row);
      this.$confirm("此操作将删除该角色,请谨慎考虑!!!, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http
            .get(
              "http://localhost:8081/userRole/deleteRole/" + row.rid,
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
    cancel: function () {
      this.dialogFormVisible = false;
      this.reload();
    },
    submitForm1(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm("是否添加？", "tips", {
            confirmButtonText: "OK",
            cancelButtonText: "Cancel",
            type: "warning",
          })
            .then(() => {
              
              let rname = this.userData.rname;
              let rdesc = this.userData.rdesc;
              this.$http
                .get(
                  "http://localhost:8081/userRole/addRole/" +
                    rname +
                    "/" +
                    rdesc,
                  { xhrFields: { withCredentials: true } },
                  { crossDomain: true }
                )
                .then((req) => {
                  if (req.data != null) {
                    this.dialogFormVisible = false;
                    this.$message({
                      showClose: true,
                      message: "添加成功,添加者为当前用户！",
                      type: "success",
                    });
                    this.reload();
                  }
                });
            })
            .catch(() => {
              this.$message({
                type: "info",
                message: "已取消添加!",
              });
            });
        } else {
          return false;
        }
      });
    },
    addRole: function () {
      this.dialogFormVisible = true;
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
              let rid = this.ruleForm.rid;
              let rname = this.ruleForm.rname;
              let rdesc = this.ruleForm.rdesc;
              this.$http
                .get(
                  "http://localhost:8081/userRole/updateRole/" +
                    rid +
                    "/" +
                    rname +
                    "/" +
                    rdesc,
                  { xhrFields: { withCredentials: true } },
                  { crossDomain: true }
                )
                .then((req) => {
                  if (req.data != null) {
                    this.dialogFormEVisible = false;
                    this.$refs.drawer.closeDrawer();
                    this.$message({
                      showClose: true,
                      message: "修改成功,修改者为当前用户！",
                      type: "success",
                    });
                    this.reload();
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