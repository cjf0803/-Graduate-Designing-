<template>
  <div>
     <!-- 面包屑 -->
     <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: 'home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index' }">用户列表</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/role' }">
        角色管理</el-breadcrumb-item
      >
      <el-breadcrumb-item :to="{ path: '/add' }"> 添加用户</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/findUserMo' }">模糊查询</el-breadcrumb-item>
      <el-breadcrumb-item>promotion detail</el-breadcrumb-item>
    </el-breadcrumb>
    <div id="box">
      <h1 style="margin: 0px 0px 20px 89px">用户添加</h1>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
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
        <el-form-item label="角色" prop="role_id" :label-width="formLabelWidth">
          <el-select
            v-model="ruleForm.role_id"
            clearable
            placeholder="请选择角色哇~"
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
        <el-form-item label="状态" prop="state" :label-width="formLabelWidth">
          <el-select
            v-model="ruleForm.state"
            clearable
            placeholder="请选择类型哇~"
            style="width: 100%"
          >
            <el-option
              v-for="item in options1"
              :key="item.value1"
              :label="item.label1"
              :value="item.value1"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <div class="cjf">
            <el-button type="primary" @click="submitForm('ruleForm')">添加</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </div>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "addUser",
  data() {
    return {
      formLabelWidth: "120px",
      ruleForm: {
        username: "",
        role_id: "",
        state: 1,
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

      options1: [
        {
          value1: 1,
          label1: "正常",
        },
        {
          value1: 0,
          label1: "停用",
        },
      ],

      rules: {
        username: [
          { required: true, message: " 此项不能为空", trigger: "blur" },
        ],
        role_id: [
          { required: true, message: " 此项不能为空", trigger: "blur" },
        ],
        state: [{ required: true, message: " 此项不能为空", trigger: "blur" }],
      },
    };
  },
  mounted() {
    this.$message({
      type: "warning",
      message: "&小tips:只需输入用户名即可,登录密码默认为 123",
    });
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm("进行添加数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "info",
      })
        .then(() => {
          let username = this.ruleForm.username;
          let role_id = this.ruleForm.role_id;
          let state = this.ruleForm.state;
          this.$http
            .get(
              "http://localhost:8081/user/addUser/" +
                username +
                "/" +
                role_id +
                "/" +
                state,
              { xhrFields: { withCredentials: true } },
              { crossDomain: true }
            )
            .then((req) => {
              if (req != null) {
                this.$router.push({ name: "index" });
                this.$message({
                  showClose: true,
                  message: "添加成功",
                  type: "success",
                });
              }
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消添加!",
          });
        });
        }else {
          return false;
        }
      });
     
    },

    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>

<style scoped>
#box {
  margin: 65px 0px 0px -154px;
}
.cjf {
  margin: 0px 0px 0px 92px;
}
</style>