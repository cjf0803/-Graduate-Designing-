<template>
  <div id="box">
    <h1>书籍修改</h1>
    <el-form
      :model="ruleForm"
      status-icon
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="编号" prop="id">
        <el-input
          v-model="ruleForm.id"
          autocomplete="off"
          readonly="true"
        ></el-input>
      </el-form-item>
      <el-form-item label="书名" prop="bookname">
        <el-input v-model="ruleForm.bookname" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="作者" prop="author">
        <el-input
          v-model="ruleForm.author"
          autocomplete="off"
        ></el-input>
      </el-form-item>
      <el-form-item label="出版社" prop="pressname">
        <el-input v-model="ruleForm.pressname" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="借还状态" prop="status">
        <el-select v-model="ruleForm.status" clearable placeholder="请选择类型哇~" style="width:100%">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="图片" prop="path">
        <el-input
          v-model="ruleForm.path"
          autocomplete="off"
        ></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')"
          >提交</el-button
        >
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "updateBook",
  data() {
    return {
      ruleForm: {
        id: "",
        bookname: "",
        author: "",
        pressname: "",
        status:"",
        path: "",
      },
       options: [{
          value: '1',
          label: '未借'
        }, {
          value: '0',
          label: '已借'
        }
        ],
        
      
      rules: {
        id: [{ required: true, message: " 此项不能为空", trigger: "blur" }],
        bookname: [
          { required: true, message: " 此项不能为空", trigger: "blur" },
        ],
        author: [
          { required: true, message: " 此项不能为空", trigger: "blur" },
        ],
        pressname: [
          { required: true, message: " 此项不能为空", trigger: "blur" },
        ],
        bookStatus: [{ required: true, message: " 此项不能为空", trigger: "blur" }],
        path: [
          { required: true, message: " 此项不能为空", trigger: "blur" },
        ],
      },
    };
  },
  mounted() {
    let id = this.$route.params.id;

    this.$http
      .get(
        "http://localhost:8081/book/findById?id=" + id,
        { xhrFields: { withCredentials: true } },
        { crossDomain: true }
      )
      .then((req) => {
        console.log(req.data);
        this.ruleForm.id = req.data[0].ID;
        this.ruleForm.bookname = req.data[0].BOOKNAME;
        this.ruleForm.author = req.data[0].AUTHOR;
        this.ruleForm.pressname = req.data[0].PRESSNAME;
        this.ruleForm.status = req.data[0].STATUS;
        this.ruleForm.path = req.data[0].PATH;
      });
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm("进行修改数据, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "info",
          })
            .then(() => {
              let id = this.ruleForm.id;
              let bookname = this.ruleForm.bookname;
              let author = this.ruleForm.author;
              let pressname = this.ruleForm.pressname;
              let status = this.ruleForm.status;
              let path = this.ruleForm.path;
              this.$http
                .get(
                  "http://localhost:8081/book/updateBook?id=" +
                    id +
                    "&bookname=" +
                    bookname +
                    "&author=" +
                    author +
                    "&pressname=" +
                    pressname +
                    "&status=" +
                    status +
                    "&path=" +
                    path,
                  { xhrFields: { withCredentials: true } },
                  { crossDomain: true }
                )
                .then((req) => {
                  if (req.data != null) {
                    this.$router.push({ name: "bookIndex" });
                    this.$message({
                      showClose: true,
                      message: "修改成功",
                      type: "success",
                    });
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
          console.log("error submit!!");
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
  width: 700px;
  height: 200px;
  margin: auto;
}
</style>