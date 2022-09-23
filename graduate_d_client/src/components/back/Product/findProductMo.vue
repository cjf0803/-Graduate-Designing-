<template>
  <div>
   <!-- 面包屑 -->
   <!-- <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: 'home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/productIndex' }">理财总览</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/findProductMo' }"
        >模糊查询</el-breadcrumb-item
      >
      <el-breadcrumb-item>promotion detail</el-breadcrumb-item>
    </el-breadcrumb> -->
    <el-page-header @back="goBack" content="查询页面">
    </el-page-header>
    <div class="flex" style="margin-top:10px">
      <div class="input_box">
        <el-input
          v-model="keywords"
          placeholder="请输入理财名称关键字"
          class="input-with-select"
          
        >
          <template #append>
            <el-button @click="searchList" icon="el-icon-search"></el-button>
          </template>
        </el-input>
      </div>
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
              <span>1月:{{ props.row.recentlyRate.jan }}，</span>
              <span>3月:{{ props.row.recentlyRate.mar }}，</span>
              <span>5月:{{ props.row.recentlyRate.may }}，</span>
              <span>7月:{{ props.row.recentlyRate.july }}，</span>
              <span>9月:{{ props.row.recentlyRate.sep }}，</span>
              <span>11月:{{ props.row.recentlyRate.nov }}</span>
            </el-tag>
            </el-form-item>
            <el-form-item label="上市时间：">
              <span>{{ props.row.ptime }}</span>
            </el-form-item>
            <el-form-item label="产品经理：">
              <el-tag type="success"><span>{{ props.row.pmanager.mname }}</span></el-tag>
            </el-form-item>
            <el-form-item label="产品经理简介：">
              <span>{{ props.row.pmanager.mdesc }}</span>
            </el-form-item>
            <el-form-item label="产品描述：">
              <el-tag type="warning"><span>{{ props.row.pdesc}}</span></el-tag>
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
      <el-table-column label="产品名称"  width="180px">
        <template slot-scope="scope">
                <el-tag>{{scope.row.pname}}</el-tag>
            </template>
      </el-table-column>
      <el-table-column label="类型" >
        <template slot-scope="scope">
                <el-tag type="info">{{scope.row.ptype.tname}}</el-tag>
            </template>
      </el-table-column>
      <el-table-column label="单份价格" >
            <template slot-scope="scope">
              <el-badge is-dot class="item"> {{scope.row.price}}元</el-badge>
            </template>
      </el-table-column>
       <!-- 加状态开关 -->
       <el-table-column prop="pstate.sname" label="产品状态" width="160">
        <template slot-scope="scope">
          <el-switch
            :disabled="currentRole>2? false: true"
            v-model="scope.row.pstate.sname"
            active-color="#13ce66"
            inactive-color="gray"
            active-value="正常"
            inactive-value="停用"
            @change="switchChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="总份额" >
        <template slot-scope="scope">
          <el-tag type="warning">{{scope.row.totalnum}}份</el-tag>
            </template>
       </el-table-column>
      <el-table-column label="平均收益率" prop="averagerate"> </el-table-column>
      <el-table-column label="总资产" prop="psize"> 
        <template slot-scope="scope">
          <el-tag type="danger">{{scope.row.psize}}</el-tag>
            </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small"
          :disabled="currentRole>2 ? false: true" >查看</el-button
          >
          <el-button type="text" size="small"  :disabled="currentRole>2 ? false: true">编辑</el-button>
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
  name: "findProductMo",
  data() {
    return {
      uname: "",
      total: 0,
      pageSize: 0,
      index: 1,
      keywords:'',
      currentPage: 1,
      currentRole:"",
      tableData: [
        {
          id: "",
          pname: "",
          pimg: "",
          ptype:{
            tid:"",
            tname:"",
            tdesc:"",

          },
          price:"",
          pstate:{
            sid:"",
            sname:"",
            sdesc:""
          },
          totalnum:"",
          averagerate:"",
          recentlyRate:{
            rid:"",
            jan:"",
            mar:"",
            may:"",
            july:"",
            sep:"",
            nov:""
          },
          psize:"",
          ptime:"",
          pmanager:{
            mid:"",
            mname:"",
            mdesc:""
          },
          pdesc:""


        },
      ],
    };
  },
  mounted() {
    this.currentRole = this.$store.getters["uInfo/getUserInfo"].ROLE_ID;
    this.$http
      .get("http://localhost:8081/product/findProductMo/1/"+this.$route.params.keywords)
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
    goBack() {
        this.$router.push({name:"productIndex"})
      },
    changePage: function (index) {
      this.$http
        .get("http://localhost:8081/product/findProductMo/" + index+"/"+this.$route.params.keywords)
        .then((res) => {
          this.tableData = res.data.list;

        });
    },
    searchList: function () {
       this.$http
      .get("http://localhost:8081/product/findProductMo/1/"+this.keywords)
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
.input_box {
  display: flex;
  width: 250px;
  margin-right: 15px;
  margin-bottom: 10px;
}
</style>