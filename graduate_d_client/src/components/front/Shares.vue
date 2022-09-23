<template>
  <div id="box">
    <div class="content">
      <div class="goods">
        <!-- <div class="content"> -->
        <div class="card" v-for="(item, index) in tableData" :key="index">
          <div class="photo">
            <img
              :src="require('@/assets/images/appearance/' + item.pimg)"
              style="width: 700px"
            />
          </div>
          <h1>{{ item.pname }}</h1>
          <h2>平均收益率%:{{ item.averagerate }}%</h2>
          <p style="padding-right: 15px">产品描述：{{ item.pdesc }}</p>
          <!-- <a href="#">了解更多</a> -->
          <router-link  :to="{path:'/sharesDetail',query:{id:tableData[index].id}}">了解更多</router-link>
        </div>
      </div>
    </div>
    <div>
      <img src="@/assets/images/appearance/shares7.png" alt="" style="margin-left:80px" />
    </div>
  </div>
</template>

<script>
export default {
  name: "shares_v",
  inject: ["reload"],
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
    };
  },
  mounted() {
    
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
.goods {
  background-repeat: repeat;
  display: flex;
  width: 1200px;
  overflow: hidden;
  margin-left: 273px;
  flex-flow: wrap;
  margin-top: -20px;
}
.content {
  background-image: url("@/assets/images/appearance/bg.jpg");
  min-height: 1000px;
  float: left;
}
* {
  /* 初始化 */
  margin: 0;
  padding: 0;
}

.card {
  /* 相对定位 */
  position: relative;
  width: 300px;
  height: 450px;
  margin: 20px;
  background-color: #758a99;
  border-radius: 20px;
  /* 溢出隐藏 */
  overflow: hidden;
  /* 弹性布局 */
  display: flex;
  /* 元素纵向排列 */
  flex-direction: column;
  /* 居中 */
  align-items: center;
  color: #fff;
  /* 阴影 */
  box-shadow: 0 0 30px rgba(0, 0, 0, 0.5);
  /* 不让其他被挤压 */
  flex-shrink: 0;
}
.card .photo img {
  width: 100%;
  height: 100%;
  /* 保持原有尺寸比例,裁切长边 */
  object-fit: cover;
}

/* 默认大图 */
.card .photo {
  /* 绝对定位 */
  position: absolute;
  top: 0;
  width: 100%;
  height: 100%;
  border-radius: 0%;
  overflow: hidden;
  /* 动画过渡 */
  transition: 0.5s;
}

/* 鼠标移入变小图 */
.card:hover .photo {
  top: 30px;
  width: 120px;
  height: 120px;
  border-radius: 50%;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.8);
}

/* 这里加个黑色到透明的渐变背景，可以更好的看清楚名字 */
.card .photo::before {
  content: "";
  position: absolute;
  width: 100%;
  height: 100%;
  background: linear-gradient(to bottom, transparent 50%, #222);
}

.card h1 {
  position: absolute;
  top: 370px;
  transition: 0.5s;
  padding-left: 17px;
}

.card:hover h1 {
  top: 170px;
}

.card h2 {
  margin-top: 220px;
  width: 80%;
  border-bottom: 1px solid rgba(255, 255, 255, 0.3);
  font-size: 17px;
  text-align: center;
  font-weight: bolder;
  color: darkred;
  margin-bottom: 20px;
  padding-bottom: 20px;
}

.card p {
  width: 90%;
  text-indent: 32px;
  font-size: 16px;
  margin-bottom: 15px;
  line-height: 30px;
}

.card a {
  font-size: 14px;
  color: rgba(255, 255, 255, 0.8);
  text-decoration: none;
  border: 1px solid rgba(255, 255, 255, 0.5);
  padding: 8px 32px;
  border-radius: 8px;
}
.card a:hover {
  color: #fff;
  background-color: rgba(255, 255, 255, 0.2);
}
</style>
