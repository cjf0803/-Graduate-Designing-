<template>
  <div>
    <!-- 面包屑 -->
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: 'home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/productIndex' }"
        >理财总览</el-breadcrumb-item
      >
      <el-breadcrumb-item :to="{ path: '/findProductMo' }"
        >模糊查询</el-breadcrumb-item
      >
      <el-breadcrumb-item>promotion detail</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="flex">
      <div class="input_box" style="width: 250px">
        <el-input
          v-model="keywords"
          placeholder="请输入产品名称关键字"
          class="input-with-select"
          clearable
        >
          <template #append>
            <el-button @click="searchList" icon="el-icon-search"></el-button>
          </template>
        </el-input>
      </div>
      <el-tooltip
        effect="dark"
        :disabled="currentRole > 2 ? true : false"
        content="用户权限不足"
        placement="right"
        transition
      >
        <div
          style="position: absolute; top: 149px; left: 495px; text-align: left"
        >
          <el-button
            :disabled="currentRole > 2 ? false : true"
            type="primary"
            icon="el-icon-circle-plus-outline"
            @click="addProduct"
            >添加产品</el-button
          >
        </div>
      </el-tooltip>
      <el-tooltip
        effect="dark"
        :disabled="currentRole > 2 ? true : false"
        content="用户权限不足"
        placement="right"
        transition
      >
        <div
          style="position: absolute; top: 149px; left: 1312px; text-align: left"
        >
          <el-button
            :disabled="currentRole > 2 ? false : true"
            type="warning"
            icon="el-icon-circle-plus-outline"
            @click="editRecently"
            >查看产品近期收益率</el-button
          >
        </div>
      </el-tooltip>
    </div>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="产品类型描述：">
              <span>{{ props.row.ptype.tdesc }}</span>
            </el-form-item>
            <el-form-item label="最近收益率：">
              <el-tag>
                <span>1月:{{ props.row.recentlyRate.jan }}%，</span>
                <span>3月:{{ props.row.recentlyRate.mar }}%，</span>
                <span>5月:{{ props.row.recentlyRate.may }}%，</span>
                <span>7月:{{ props.row.recentlyRate.july }}%，</span>
                <span>9月:{{ props.row.recentlyRate.sep }}%，</span>
                <span>11月:{{ props.row.recentlyRate.nov }}%</span>
              </el-tag>
            </el-form-item>
            <el-form-item label="上市时间：">
              <span>{{ props.row.ptime }}</span>
            </el-form-item>
            <el-form-item label="产品经理：">
              <el-tag type="success"
                ><span>{{ props.row.pmanager.mname }}</span></el-tag
              >
            </el-form-item>
            <el-form-item label="产品经理简介：">
              <span>{{ props.row.pmanager.mdesc }}</span>
            </el-form-item>
            <el-form-item label="产品描述：">
              <el-tag type="warning"
                ><span>{{ props.row.pdesc }}</span></el-tag
              >
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="产品图片" width="120">
        <template slot-scope="scope">
          <img
            class="image"
            :src="require('@/assets/images/appearance/' + scope.row.pimg)"
          />
        </template>
      </el-table-column>
      <el-table-column label="产品名称" width="180px">
        <template slot-scope="scope">
          <el-tag>{{ scope.row.pname }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="类型">
        <template slot-scope="scope">
          <el-tag type="info">{{ scope.row.ptype.tname }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="单份价格">
        <template slot-scope="scope">
          <el-badge is-dot class="item"> {{ scope.row.price }}元</el-badge>
        </template>
      </el-table-column>
      <!-- 加状态开关 -->
      <el-table-column prop="pstate.sname" label="产品状态" width="160">
        <template slot-scope="scope">
          <el-switch
            :disabled="currentRole > 2 ? false : true"
            v-model="scope.row.pstate.sname"
            active-color="#13ce66"
            inactive-color="gray"
            active-value="正常"
            inactive-value="停用"
            @change="switchChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="总份额">
        <template slot-scope="scope">
          <el-tag type="warning">{{ scope.row.totalnum }}份</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="平均收益率%" prop="averagerate" sortable>
        <template slot-scope="scope"> {{ scope.row.averagerate }}% </template>
      </el-table-column>
      <el-table-column label="总资产" prop="psize">
        <template slot-scope="scope">
          <el-tag type="danger">{{ scope.row.psize }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="100">
        <template slot-scope="scope">
          <el-button
            @click="delProduct(scope.row)"
            type="text"
            size="small"
            :disabled="currentRole > 2 ? false : true"
            >删除</el-button
          >
          <el-button
            type="text"
            size="small"
            @click="updateProduct(scope.row)"
            :disabled="currentRole > 2 ? false : true"
            >编辑</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- 编辑弹窗(修改用户信息)  rules有问题(已解决)-->
    <el-drawer
      title="产品信息修改"
      :visible.sync="dialogFormEVisible"
      direction="rtl"
      custom-class="demo-drawer"
      ref="drawer"
    >
      <div class="demo-drawer__content">
        <!-- <el-dialog title="修改用户信息" :visible.sync="dialogFormEVisible"> -->
        <el-form :model="ruleForm" ref="ruleForm">
          <el-form-item
            label="产品名称"
            prop="pname"
            :label-width="formLabelWidth"
          >
            <el-input
              v-model="ruleForm.pname"
              @input="change($event)"
              clearable
              readonly="true"
            ></el-input>
          </el-form-item>
          <el-form-item
            label="产品类型"
            prop="ptype"
            :label-width="formLabelWidth"
          >
            <el-select
              v-model="ruleForm.ptype"
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
            label="单份价格"
            prop="price"
            :label-width="formLabelWidth"
          >
            <el-input
              v-model="ruleForm.price"
              @input="change($event)"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item
            label="总份额"
            prop="totalnum"
            :label-width="formLabelWidth"
          >
            <el-input
              v-model="ruleForm.totalnum"
              @input="change($event)"
              clearable
            ></el-input>
          </el-form-item>

          <el-form-item
            label="平均收益率%"
            prop="averagerate"
            :label-width="formLabelWidth"
          >
            <el-input
              v-model="ruleForm.averagerate"
              @input="change($event)"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item
            label="总资产"
            prop="psize"
            :label-width="formLabelWidth"
          >
            <el-input
              v-model="ruleForm.psize"
              @input="change($event)"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item
            label="产品经理"
            prop="pmanager"
            :label-width="formLabelWidth"
          >
            <el-select
              v-model="ruleForm.pmanager"
              placeholder="请选择经理哇~"
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
          <el-form-item
            label="产品描述"
            prop="pdesc"
            :label-width="formLabelWidth"
          >
            <el-input
              type="textarea"
              :rows="2"
              autosize
              maxlength="100"
              show-word-limit
              v-model="ruleForm.pdesc"
              @input="change($event)"
              clearable
            ></el-input>
          </el-form-item>

          <el-form-item class="userimg" prop="pimg">
            <el-upload
              class="upload-demo"
              action="http://localhost:8081/upload/product"
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
    <!-- 编辑产品近期收益率弹窗 -->
    <el-drawer
      title="产品近期收益率"
      direction="ttb"
      :visible.sync="recentlyVisible"
      size="66%"
    >
      <el-table :data="RecentlyData" style="width: 100%" max-height="350">
        <el-table-column fixed prop="rname" label="产品名称" width="250">
        </el-table-column>
        <el-table-column label="一月收益率%" sortable width="170">
          <template slot-scope="scope"> {{ scope.row.jan }}% </template>
        </el-table-column>
        <el-table-column label="三月收益率%" sortable width="170">
          <template slot-scope="scope"> {{ scope.row.mar }}% </template>
        </el-table-column>
        <el-table-column label="五月收益率%" sortable width="170">
          <template slot-scope="scope"> {{ scope.row.may }}% </template>
        </el-table-column>
        <el-table-column label="七月收益率%" sortable width="170">
          <template slot-scope="scope"> {{ scope.row.july }}% </template>
        </el-table-column>
        <el-table-column label="九月收益率%" sortable width="170">
          <template slot-scope="scope"> {{ scope.row.sep }}% </template>
        </el-table-column>
        <el-table-column label="十一月收益率%" sortable width="170">
          <template slot-scope="scope"> {{ scope.row.nov }}% </template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="120">
          <template slot-scope="scope">
            <el-button @click="delete1(scope.row)" type="text" size="small">
              移除
            </el-button>

            <el-button @click="update(scope.row)" type="text" size="small">
              编辑
            </el-button>
            <el-drawer
              title="编辑产品近期收益率"
              :append-to-body="true"
              :visible.sync="innerDrawer"
            >
              <div class="demo-drawer__content">
                <!-- <el-dialog title="修改用户信息" :visible.sync="dialogFormEVisible"> -->
                <el-form :model="RecentlyData" ref="RecentlyData">
                  <el-form-item
                    label="产品名称："
                    prop="rname"
                    :label-width="formLabelWidth"
                  >
                    <el-input
                      v-model="RecentlyData.rname"
                      @input="change($event)"
                      readonly="true"
                    ></el-input>
                  </el-form-item>
                  <el-form-item
                    label="一月收益率%："
                    prop="jan"
                    :label-width="formLabelWidth"
                  >
                    <el-input
                      v-model="RecentlyData.jan"
                      @input="change($event)"
                      clearable
                      autocomplete="off"
                    ></el-input>
                  </el-form-item>
                  <el-form-item
                    label="三月收益率%："
                    prop="mar"
                    :label-width="formLabelWidth"
                  >
                    <el-input
                      v-model="RecentlyData.mar"
                      @input="change($event)"
                      clearable
                      autocomplete="off"
                    ></el-input>
                  </el-form-item>
                  <el-form-item
                    label="五月收益率%："
                    prop="may"
                    :label-width="formLabelWidth"
                  >
                    <el-input
                      v-model="RecentlyData.may"
                      @input="change($event)"
                      clearable
                      autocomplete="off"
                    ></el-input>
                  </el-form-item>

                  <el-form-item
                    label="七月收益率%："
                    prop="july"
                    :label-width="formLabelWidth"
                  >
                    <el-input
                      v-model="RecentlyData.july"
                      @input="change($event)"
                      clearable
                      autocomplete="off"
                    ></el-input>
                  </el-form-item>
                  <el-form-item
                    label="九月收益率%："
                    prop="sep"
                    :label-width="formLabelWidth"
                  >
                    <el-input
                      v-model="RecentlyData.sep"
                      @input="change($event)"
                      clearable
                      autocomplete="off"
                    ></el-input>
                  </el-form-item>
                  <el-form-item
                    label="十一月收益率%："
                    prop="nov"
                    label-width="150px"
                  >
                    <el-input
                      v-model="RecentlyData.nov"
                      @input="change($event)"
                      clearable
                      autocomplete="off"
                    ></el-input>
                  </el-form-item>
                </el-form>
                <div class="demo-drawer__footer">
                  <el-button @click="innerDrawer = false">取 消</el-button>
                  <el-button type="primary" @click="submitForm3('RecentlyData')"
                    >确 定</el-button
                  >
                </div>
              </div>
            </el-drawer>
          </template>
        </el-table-column>
      </el-table>
      <div class="fenye">
        <!-- 分页 -->
        <el-pagination
          background
          @current-change="changePage1"
          layout="total,prev, pager, next,jumper"
          :current-page="currentPage1"
          :page-size="pageSize1"
          :total="total1"
        >
        </el-pagination>
        <!-- <el-pagination
                    background
                    @current-change="changePage"
                    layout="prev, pager, next"
                    :total="50">
            </el-pagination> -->
      </div>
    </el-drawer>
    <el-dialog title="添加产品" :visible.sync="dialogFormVisible">
      <el-form :model="userData" ref="userData">
        <el-form-item
          label="产品名称:"
          prop="pname"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="userData.pname"
            @input="change($event)"
            autocomplete="off"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item
          label="产品类型:"
          prop="ptype"
          :label-width="formLabelWidth"
        >
          <el-select
            v-model="userData.ptype"
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
          label="单份价格:"
          prop="price"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="userData.price"
            @input="change($event)"
            autocomplete="off"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item
          label="总份额:"
          prop="totalnum"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="userData.totalnum"
            @input="change($event)"
            autocomplete="off"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item
          label="平均收益率%:"
          prop="averagerate"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="userData.averagerate"
            @input="change($event)"
            autocomplete="off"
            clearable
            placeholder="不需要输入%"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="总资产:"
          prop="psize"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="userData.psize"
            @input="change($event)"
            autocomplete="off"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item
          label="产品经理:"
          prop="pmanager"
          :label-width="formLabelWidth"
        >
          <el-select
            v-model="userData.pmanager"
            placeholder="请选择经理哇~"
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
        <el-form-item
          label="产品简单描述:"
          prop="pdesc"
          :label-width="formLabelWidth"
        >
          <div style="width: 250px">
            <el-input
              type="textarea"
              :rows="2"
              autosize
              maxlength="100"
              show-word-limit
              v-model="userData.pdesc"
              @input="change($event)"
              autocomplete="off"
              clearable
            ></el-input>
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm1('userData')"
          >确 定</el-button
        >
      </div>
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
  name: "productIndex",
  inject: ["reload"],
  data() {
    return {
      total: 0,
      pageSize: 0,
      currentPage: 1,
      total1: 0,
      pageSize1: 0,
      currentPage1: 1,
      keywords: "",
      currentRole: "",
      dialogFormEVisible: false,
      dialogFormVisible: false,
      recentlyVisible: false,
      innerDrawer: false,
      formLabelWidth: "120px",
      ruleForm: {
        id: "",
        pname: "",
        pimg: "",
        ptype: "",
        price: "",
        totalnum: "",
        averagerate: "",
        psize: "",
        pmanager: "",
        pdesc: "",
      },
      userData: {
        pname: "",
        ptype: "",
        price: "",
        totalnum: "",
        averagerate: "",
        psize: "",
        pmanager: "",
        pdesc: "",
      },
      options: [
        {
          value: "1",
          label: "基金",
        },
        {
          value: "2",
          label: "股票",
        },
        {
          value: "3",
          label: "债券",
        },
        {
          value: "4",
          label: "银行储蓄",
        },
        {
          value: "5",
          label: "保险",
        },
        {
          value: "6",
          label: "黄金",
        },
      ],
      options1: [
        {
          value1: "1",
          label1: "张坤",
        },
        {
          value1: "2",
          label1: "李四",
        },
        {
          value1: "3",
          label1: "王五",
        },
        {
          value1: "4",
          label1: "刘六",
        },
        {
          value1: "5",
          label1: "嗨七",
        },
        {
          value1: "6",
          label1: "巴巴",
        },
      ],

      fileList: [
        {
          name: "点击上传进行修改产品图片",
          url: "",
        },
      ],
      RecentlyData: [
        {
          rid: "",
          jan: "",
          mar: "",
          may: "",
          july: "",
          sep: "",
          nov: "",
          rname: "",
        },
      ],
      tableData: [
        {
          id: "",
          pname: "",
          pimg: "",
          ptype: {
            tid: "",
            tname: "",
            tdesc: "",
          },
          price: "",
          pstate: {
            sid: "",
            sname: "",
            sdesc: "",
          },
          totalnum: "",
          averagerate: "",
          recentlyRate: {
            rid: "",
            jan: "",
            mar: "",
            may: "",
            july: "",
            sep: "",
            nov: "",
          },
          psize: "",
          ptime: "",
          pmanager: {
            mid: "",
            mname: "",
            mdesc: "",
          },
          pdesc: "",
        },
      ],
    };
  },
  mounted() {
    this.currentRole = this.$store.getters["uInfo/getUserInfo"].ROLE_ID;
    this.$http
      .get(
        "http://localhost:8081/product/findProduct/1",
        { xhrFields: { withCredentials: true } },
        { crossDomain: true }
      )
      .then((req) => {
        console.log(req.data);
        this.tableData = req.data.list;
        this.total = req.data.count;
        this.index = req.data.index;
        this.pageSize = req.data.list.length;
      });
    this.$http
      .get(
        "http://localhost:8081/recentlyRate/findAll/1",
        { xhrFields: { withCredentials: true } },
        { crossDomain: true }
      )
      .then((req) => {
        console.log(req.data);
        this.RecentlyData = req.data.list;
        this.total1 = req.data.count;
        this.index1 = req.data.index;
        this.pageSize1 = req.data.list.length;
      });
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.ruleForm.pimg = res; //从后台获取filename给path赋值
      this.fileList.name = res;
      this.fileList.url = URL.createObjectURL(file.raw);
    },
    addProduct: function () {
      this.dialogFormVisible = true;
    },
    changePage: function (index) {
      this.$http
        .get("http://localhost:8081/product/findProduct/" + index)
        .then((res) => {
          this.tableData = res.data.list;
        });
    },
    changePage1: function (index) {
      this.$http
        .get("http://localhost:8081/recentlyRate/findAll/" + index)
        .then((res) => {
          this.RecentlyData = res.data.list;
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
    updateProduct: function (row) {
      this.dialogFormEVisible = true;
      this.ruleForm = {
        id: row.id,
        pname: row.pname,
        pimg: row.pimg,
        ptype: row.ptype.tid,
        price: row.price,
        totalnum: row.totalnum,
        averagerate: row.averagerate,
        psize: row.psize,
        pmanager: row.pmanager.mid,
        pdesc: row.pdesc,
      };
    },
    searchList: function () {
      this.$router.push({
        name: "findProductMo",
        params: { keywords: this.keywords },
      });
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
              let pname = this.userData.pname;
              let ptype = this.userData.ptype;
              let price = this.userData.price;
              let totalnum = this.userData.totalnum;
              let averagerate = this.userData.averagerate;
              let psize = this.userData.psize;
              let pmanager = this.userData.pmanager;
              let pdesc = this.userData.pdesc;
              this.$http
                .get(
                  "http://localhost:8081/product/addProduct/" +
                    pname +
                    "/" +
                    ptype +
                    "/" +
                    price +
                    "/" +
                    totalnum +
                    "/" +
                    averagerate +
                    "/" +
                    psize +
                    "/" +
                    pmanager +
                    "/" +
                    pdesc,
                  { xhrFields: { withCredentials: true } },
                  { crossDomain: true }
                )
                .then((req) => {
                  if (req.data != null) {
                    this.dialogFormVisible = false;
                    this.$message({
                      showClose: true,
                      message: "添加成功！",
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
    delProduct: function (row) {
      console.log(row);
      this.$confirm("此操作将永久删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http
            .get(
              "http://localhost:8081/product/deleteProduct/" + row.id,
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
    change() {
      this.$forceUpdate();
    },
    update: function (row) {
      this.innerDrawer = true;
      this.RecentlyData = {
        rid: row.rid,
        rname: row.rname,
        jan: row.jan,
        mar: row.mar,
        may: row.may,
        july: row.july,
        sep: row.sep,
        nov: row.nov,
      };
    },
    delete1: function (row) {
      console.log(row);
      this.$confirm("此操作将永久删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http
            .get(
              "http://localhost:8081/recentlyRate/delete/" + row.rid,
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
    editRecently: function () {
      this.recentlyVisible = true;
    },
    // 跨域已解决！400传参不能带%
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
              let pname = this.ruleForm.pname;
              let pimg = this.ruleForm.pimg;
              let ptype = this.ruleForm.ptype;
              let price = this.ruleForm.price;
              let totalnum = this.ruleForm.totalnum;
              let averagerate = this.ruleForm.averagerate;
              let psize = this.ruleForm.psize;
              let pmanager = this.ruleForm.pmanager;
              let pdesc = this.ruleForm.pdesc;
              this.$http
                .get(
                  "http://localhost:8081/product/updateProduct/" +
                    id +
                    "/" +
                    pname +
                    "/" +
                    pimg +
                    "/" +
                    ptype +
                    "/" +
                    price +
                    "/" +
                    totalnum +
                    "/" +
                    averagerate +
                    "/" +
                    psize +
                    "/" +
                    pmanager +
                    "/" +
                    pdesc,
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
    submitForm3(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm("是否修改？", "tips", {
            confirmButtonText: "OK",
            cancelButtonText: "Cancel",
            type: "warning",
          })
            .then(() => {
              let rid = this.RecentlyData.rid;
              let jan = this.RecentlyData.jan;
              let mar = this.RecentlyData.mar;
              let may = this.RecentlyData.may;
              let july = this.RecentlyData.july;
              let sep = this.RecentlyData.sep;
              let nov = this.RecentlyData.nov;
              this.$http
                .get(
                  "http://localhost:8081/recentlyRate/update/" +
                    rid +
                    "/" +
                    jan +
                    "/" +
                    mar +
                    "/" +
                    may +
                    "/" +
                    july +
                    "/" +
                    sep +
                    "/" +
                    nov,
                  { xhrFields: { withCredentials: true } },
                  { crossDomain: true }
                )
                .then((req) => {
                  if (req.data != null) {
                    this.innerDrawer = false;

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
    switchChange: function (row) {
      if (row.pstate.sid == 1) {
        this.$http
          .get(
            "http://localhost:8081/product/updateState/" + row.id + "/" + 0,
            { xhrFields: { withCredentials: true } },
            { crossDomain: true }
          )
          .then((res) => {
            if (res.data != null) {
              this.$router.push({ name: "productIndex" }).catch(() => {});
            }
          });
      } else if (row.pstate.sid == 0) {
        this.$http
          .get(
            "http://localhost:8081/product/updateState/" + row.id + "/" + 1,
            { xhrFields: { withCredentials: true } },
            { crossDomain: true }
          )
          .then((res) => {
            if (res.data != null) {
              this.$router.push({ name: "productIndex" }).catch(() => {});
            }
          });
      }
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
  margin: 0px 0px 40px 150px;
}
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
.item {
  margin-top: 5px;
  margin-right: 40px;
}
</style>