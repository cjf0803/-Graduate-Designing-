<template>
  <div id="box">
    <h1 style="margin-bottom:20px">添加图书</h1>
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="80px">
      <el-form-item label="书名" prop="bookname">
        <el-input v-model="ruleForm.bookname" clearable></el-input>
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input v-model="ruleForm.author" clearable></el-input>
      </el-form-item>
      <el-form-item label="出版社" prop="pressname">
        <el-input v-model="ruleForm.pressname" clearable></el-input>
      </el-form-item>
      <el-form-item label="借还状态" prop="status">
        <el-select
          v-model="ruleForm.status"
          clearable
          placeholder="请选择状态哇~"
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
      <el-form-item label="书籍图片">
        <el-upload
          class="upload-demo"
          action="http://localhost:8081/upload/upload"
          :file-list="fileList"
          list-type="picture"
          :on-success="handleAvatarSuccess"
        >
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">
            只能上传jpg/png文件，且不超过10MB
          </div>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit('ruleForm')">提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "upload_v",
  data() {
    return {
      fileList: [
        {
          name: "点击上传进行上传图片",
          url: "",
        },
      ],
      ruleForm: {
        bookname: "",
        author: "",
        pressname: "",
        status: "",
        path: "",
      },
      options: [
        {
          value: "1",
          label: "未借",
        },
        {
          value: "0",
          label: "已借",
        },
      ],
      rules: {
        bookname: [
          { required: true, message: " 此项不能为空", trigger: "blur" },
        ],
        author: [{ required: true, message: " 此项不能为空", trigger: "blur" }],
        pressname: [
          { required: true, message: " 此项不能为空", trigger: "blur" },
        ],
        status: [{ required: true, message: " 此项不能为空", trigger: "blur" }],
        path: [{ required: true, message: " 此项不能为空", trigger: "blur" }],
      },
    };
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.ruleForm.path = res; //从后台获取filename给path赋值
      this.fileList.name = res;
      this.fileList.url = URL.createObjectURL(file.raw);
    },
    submit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm("进行添加图片, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "info",
          })
            .then(() => {
              let bookname = this.ruleForm.bookname;
              let author = this.ruleForm.author;
              let pressname = this.ruleForm.pressname;
              let status = this.ruleForm.status;
              let path = this.ruleForm.path;
              this.$http
                .get(
                  "http://localhost:8081/book/addBook?bookname=" +
                    bookname +
                    "&author=" +
                    author +
                    "&pressname=" +
                    pressname +
                    "&status=" +
                    status +
                    "&path=" +
                    path
                )
                .then((req) => {
                  if (req.data != null) {
                    this.$message({
                      showClose: true,
                      message: "添加成功",
                      type: "success",
                    });
                    this.$router.push({ name: "bookIndex" });
                  }
                });
            })
            .catch(() => {
              this.$message({
                type: "info",
                message: "已取消上传!",
              });
            });
        } else {
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

<style>
</style>