<template>
  <div :key="appkey">
    <div style="margin-bottom: 20px">
      <el-page-header @back="goBack" content="理财产品"> </el-page-header>
    </div>
    <el-steps :active="3" align-center>
      <el-step title="步骤1" description="产品类型介绍"></el-step>
      <el-step title="步骤2" description="产品内容"></el-step>
      <el-step title="步骤3" description="优秀产品经理"></el-step>
      <el-step
        title="步骤4"
        description="产品近期收益率和平均收益率查看"
      ></el-step>
    </el-steps>
    <el-divider></el-divider>
    <el-carousel :interval="4000" type="card" height="200px">
      <el-carousel-item v-for="item in imgs" :key="item">
        <img :src="item.url" alt="" />
      </el-carousel-item>
    </el-carousel>
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
            disabled
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
  name: "findProductByType",
  data() {
    return {
      total: 0,
      appkey: 1,
      pageSize: 0,
      currentPage: 1,
      formLabelWidth: "120px",
      ptype: "",
      imgs: [
        {
          url: require("@/assets/images/appearance/shares1.png"),
        },
        {
          url: require("@/assets/images/appearance/insurance2.jpg"),
        },
        {
          url: require("@/assets/images/appearance/bank1.jpg"),
        },
        {
          url: require("@/assets/images/appearance/insurance3.jpg"),
        },
        {
          url: require("@/assets/images/appearance/fund2.jpg"),
        },
        {
          url: require("@/assets/images/appearance/insurance5.jpg"),
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
    let ptype = this.$route.query.ptype;
    this.$http
      .get(
        "http://localhost:8081/product/findProductByType/1/" + ptype,
        { xhrFields: { withCredentials: true } },
        { crossDomain: true }
      )
      .then((req) => {
        console.log(req.data);
        this.tableData = req.data.list;
        this.total = req.data.count;
        this.index = req.data.index;
        this.ptype = req.data.ptype;
        this.pageSize = req.data.list.length;
      });
  },

  methods: {
    changePage: function (index) {
      this.$http
        .get(
          "http://localhost:8081/product/findProductByType/" +
            index +
            "/" +
            this.ptype
        )
        .then((res) => {
          this.tableData = res.data.list;
        });
    },
    goBack() {
      this.$router.push({ name: "productIndex" });
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
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>