<template>
  <div>
    <el-row style="text-align: center; margin-top: 5px">
      <el-col :span="14" :offset="5">
        <el-collapse v-model="activeName" accordion>
          <el-collapse-item
            v-for="(item, index) in awardTitle"
            :key="index"
            :title="item.awardEntity"
            :name="item.name"
          >
            <ul
              style="
                text-align: left;
                font-size: 16px;
                line-height: 22px;
                font-family: 'PingFang SC', 'Microsoft YaHei';
              "
            >
              <li
                style="margin-bottom: 8px; color: #0a0a0a"
                v-for="myEntity in item.details"
                :key="myEntity"
              >
                {{ myEntity }}
              </li>
            </ul>
          </el-collapse-item>
        </el-collapse>
      </el-col>
    </el-row>
    <div class="container1"  v-for="(item, index) in tableData" :key="index">
      <div class="item active" style="margin-left:230px">
        <img :src="require('@/assets/images/appearance/' + item.pimg)" style="position:relative;top:0px;width: 100%;"> 
        <div class="shadow"></div>
        <div class="content">
          <div class="icon">
            <i class="fa fa-sun-o" aria-hidden="true"></i>
          </div>
          <el-link type="warning" style="font-size: 20px;width:110px;font-weight: bolder;position:relative; left:451px;bottom: 0px;" ><router-link style="text-decoration:none;color: #fc6e51;" :to="{path:'/insuranceDetail',query:{id:tableData[index].id}}">了解详情</router-link>
</el-link>
          <div class="text">
            <div class="tit">{{item.pname}}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Insurance_v",
  data() {
    return {
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
      activeName: "1",
      awardTitle: [
        {
          name: "1",
          awardEntity: "一、产品口碑类",
          details: [
          "2017年度天猫IP合作杰出影响力品牌",
            "2017年度屈臣氏公益合作伙伴",
            "2017年度家乐福地区商品合作伙伴",
            "2017年度高鑫零售精诚合作奖",
            "2017年度新浪微博蓝V品牌营销价值单位",
            "2017年度新榜内容营销奖",
            "2017年度金触点Top Markting全球商业创新银奖",
            "2016年度爱奇艺网络剧营销领导品牌"
          ],
        },
        {
          name: "2",
          awardEntity: "二、品牌价值类",
          details: [
            "2018头条越影响力品牌",
            "2018消费者指数消费者十大首选品牌",
            "2018凯度消费者指数最具成长力品牌",
            "2017年度全球移动营销峰会MORKETING十大移动影响力品牌",
            "2017年度天猫IP合作杰出影响力品牌",
            "2017年度屈臣氏公益合作伙伴",
            "2017年度家乐福地区商品合作伙伴",
            "2017年度高鑫零售精诚合作奖",
            "2017年度第一财经“金字招牌”明星品牌",
            "2017年度天下网商新网商年度创新奖",
            "2016年度品观网中国蓝玫奖风云品牌大奖"
          ],
        },
        {
          name: "3",
          awardEntity: "三、创意营销类",
          details: [
            "2018金鼠标数字营销传播银奖",
            "2018华扬联众数字传播力最佳表现中国品牌",
            "2017清大数据营销案例传播奖",
            "2017年度中华创意节 One Show社交新媒体营销铜奖",
            "2017年度新周刊营销创意品牌",
            "2017年度新浪微博蓝V品牌营销价值单位",
            "2017年度新榜内容营销奖",
            "2017年度金触点Top Markting全球商业创新银奖",
            "2016年度爱奇艺网络剧营销领导品牌",
            "2015年度中国长城奖营销传播金奖",
            "2015年度中国长城奖广告主奖"
          ],
        },
      ],
    };
  },
  mounted() {
    // //获取所有的.item元素
    // let items = document.querySelectorAll(".item");

    // //设置选中状态样式
    // function setActive() {
    //   //遍历所有的.item样式 移出 active样式
    //   items.forEach((item) => {
    //     item.classList.remove("active");
    //   });
    //   //为当前选中的这个添加active样式
    //   this.classList.add("active");
    // }
    // //遍历所有的.item元素 ,分别为其设置点击事件
    // items.forEach((item) => {
    //   item.addEventListener("click", setActive);
    // });
    let ptype = this.$route.query.ptype;
    this.$http
      .get(
        "http://localhost:8081/product/CustomerfindProductByType/" + ptype,
        { xhrFields: { withCredentials: true } },
        { crossDomain: true }
      )
      .then((req) => {
        console.log(req.data);
        this.tableData = req.data.list;
      });
  },
};
</script>

<style scoped>

* {
  padding: 0;
  margin: 0;
}

.container1 {
  /* 弹性布局 */
  display: flex;
  width: 90vw;
  max-width: 900px;
  /* 溢出隐藏 */
  overflow: hidden;
  height: 46vh;
  margin-top: 50px;
 margin-left: 181px;
 background-image: url("@/assets/images/appearance/bg.jpg");
}

.item {
  /* 相对定位 */
  position: relative;
  width: 60px;
  margin: 10px;
  cursor: pointer;
  border-radius: 30px;
  /* 保持原有尺寸比例,裁剪长边 */
  background-size: cover;
  /* 定位背景图片为最中间 */
  background-position: center;
  /* 过渡 时长 贝塞尔曲线 */
  transition: 0.5s cubic-bezier(0.05, 0.61, 0.41, 0.95);
  overflow: hidden;
}

.item .shadow {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  height: 100px;
  transition: 0.5s cubic-bezier(0.05, 0.61, 0.41, 0.95);
}

.item .content {
  display: flex;
  position: absolute;
  left: 10px;
  right: 0;
  bottom: 10px;
  height: 40px;
  transition: 0.5s cubic-bezier(0.05, 0.61, 0.41, 0.95);
}

.item .content .icon {
  min-width: 40px;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background-color: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 16px;
}

.item:nth-child(1) .fa {
  color: #fc6e51;
}
.item:nth-child(2) .fa {
  color: #ffce54;
}
.item:nth-child(3) .fa {
  color: #2ecc71;
}
.item:nth-child(4) .fa {
  color: #5d9cec;
}
.item:nth-child(5) .fa {
  color: #ac92ec;
}

.item .content .text {
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-left: 10px;
  color: #fff;
  width: 100%;
}
.item .content .text div {
  /* 超出显示省略号（需要设置width） */
  width: calc(100% - 70px);
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
  opacity: 0;
  transition: opacity 0.5s ease-out;
  /* color:#e7942e */
}

.item .content .text .tit {
  font-weight: bold;
  font-size: 18px;
}

.item .content .text .sub {
  /* 设置过渡效果延迟时间 */
  transition-delay: 0.1s;
}

/* 选中态样式 */
.item.active {
  flex: 1;
  margin: 0;
  border-radius: 0.1s;
}

.item.active .shadow {
  background: linear-gradient(to top, rgba(0, 0, 0, 0.35) 65%, transparent);
}

.item.active .content {
  bottom: 20px;
  left: 20px;
}

.item.active .content .text div {
  opacity: 1;
}
</style>
