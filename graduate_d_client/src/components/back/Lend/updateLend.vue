<template>
  <div id="box">
    <h1>借阅情况修改</h1>
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
      <el-form-item label="书籍编号" prop="bookid">
        <el-input
          v-model="ruleForm.bookid"
          autocomplete="off"
          readonly="true"
        ></el-input>
      </el-form-item>

      <el-form-item label="书名" prop="bookname">
        <el-input
          v-model="ruleForm.bookname"
          autocomplete="off"
          readonly="true"
        ></el-input>
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input
          v-model="ruleForm.author"
          autocomplete="off"
          readonly="true"
        ></el-input>
      </el-form-item>
      <el-form-item label="用户编号" prop="userid">
        <el-input v-model="ruleForm.userid" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="用户昵称" prop="realname">
        <el-input v-model="ruleForm.realname" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="借书日期" prop="lenddate" :formatter="dateFormat">
        <el-input
          type="date"
          v-model="ruleForm.lenddate"
          autocomplete="off"
        ></el-input>
      </el-form-item>
      <el-form-item label="还书日期" prop="returnDate" :formatter="dateFormat1" >
        <el-input
          type="date"
          v-model="ruleForm.returnDate"
          autocomplete="off"
        ></el-input>
      </el-form-item>
      <el-form-item label="余额" prop="debit">
        <el-input v-model="ruleForm.debit" autocomplete="off"></el-input>
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
      <el-form-item label="借阅者" prop="username">
        <el-input v-model="ruleForm.username" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <div style="margin-bottom: 25px; margin-right: 100px">
          <el-button type="primary" @click="submitForm('ruleForm')"
            >提交</el-button
          >
          <el-button type="danger" @click="resetForm('ruleForm')"
            >重置</el-button
          >
        </div>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import moment from "moment";
export default {
  name: "updateLend",
  data() {
    return {
      ruleForm: {
        id: "",
        bookid: "",
        bookname: "",
        author: "",
        userid: "",
        realname: "",
        lenddate: "",
        returnDate: "",
        debit: "",
        status: "",
        username: "",
      },
      dateFormat(row, column) {
        let date = row[column.property];
        if (date == undefined) {
          return "";
        }
        return moment(date).format("YYYY-MM-DD HH:mm:ss  ");
      },
       dateFormat1(row, column) {
        let date = row[column.property];
        if (date == undefined) {
          return "";
        }
        return moment(date).format("YYYY-MM-DD HH:mm:ss  ");
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
        id: [{ required: true, message: " 此项不能为空", trigger: "blur" }],
        bookid: [{ required: true, message: " 此项不能为空", trigger: "blur" }],
        bookname: [
          { required: true, message: " 此项不能为空", trigger: "blur" },
        ],
        author: [{ required: true, message: " 此项不能为空", trigger: "blur" }],
        userid: [{ required: true, message: " 此项不能为空", trigger: "blur" }],
        realname: [
          { required: true, message: " 此项不能为空", trigger: "blur" },
        ],
        lenddate: [
          { required: true, message: " 此项不能为空", trigger: "blur" },
        ],
        returnDate: [
          { required: true, message: " 此项不能为空", trigger: "blur" },
        ],
        debit: [{ required: true, message: " 此项不能为空", trigger: "blur" }],
        status: [{ required: true, message: " 此项不能为空", trigger: "blur" }],
        username: [
          { required: true, message: " 此项不能为空", trigger: "blur" },
        ],
      },
    };
  },
  mounted() {
    let id = this.$route.params.id;
   
    this.$http
      .get(
        "http://localhost:8081/lend/findById?id=" + id,
        { xhrFields: { withCredentials: true } },
        { crossDomain: true }
      )
      .then((req) => {
        console.log(req.data);
        this.ruleForm.id = req.data[0].ID;
        this.ruleForm.bookid = req.data[0].BOOKID;
        this.ruleForm.bookname = req.data[0].BOOKNAME;
        this.ruleForm.author = req.data[0].AUTHOR;
        this.ruleForm.userid = req.data[0].USERID;
        this.ruleForm.realname =req.data[0].REALNAME
        this.ruleForm.lenddate = moment(req.data[0].LENDDATE).format("YYYY-MM-DD HH:mm:ss ");
        this.ruleForm.returnDate = moment(req.data[0].RETURNDATE).format("YYYY-MM-DD HH:mm:ss ");
        this.ruleForm.debit = req.data[0].DEBIT;
        this.ruleForm.status = req.data[0].STATUS;
        this.ruleForm.username = req.data[0].USERNAME;
     
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
              let bookid = this.ruleForm.bookid;
              let bookname = this.ruleForm.bookname;
              let author = this.ruleForm.author;
              let userid = this.ruleForm.userid;
              let realname = this.ruleForm.realname;
              let lenddate = this.ruleForm.lenddate;
              let returnDate = this.ruleForm.returnDate;
              let debit = this.ruleForm.debit;
              let status = this.ruleForm.status;
              let username = this.ruleForm.username;
              this.$http
                .get(
                  "http://localhost:8081/lend/updateLend?id=" +
                    id +
                    "&bookid=" +
                    bookid +
                    "&bookname=" +
                    bookname +
                    "&author=" +
                    author +
                    "&userid=" +
                    userid +
                    "&realname=" +
                    realname +
                    "&lenddate=" +
                    lenddate +
                    "&returnDate=" +
                    returnDate +
                    "&debit=" +
                    debit +
                    "&status=" +
                    status +
                    "&username=" +
                    username,
                  { xhrFields: { withCredentials: true } },
                  { crossDomain: true }
                )
                .then((req) => {
                  if (req.data != null) {
                    this.$router.push({ name: "lendIndex" });
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