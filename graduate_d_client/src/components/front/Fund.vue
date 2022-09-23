<template>
  <div class="elab">
      <div class="productList wrap">
        <div class="content"  v-for="(item,index) in tableData" :key="index">
          <div class="productListContent" >
            <div class="hotKind"  >
              <div class="hotItem" style="height:200px;z-index: 0;">
                <a
                  class="hotImgA img-light" style="height:200px;width:500px" 
                >
                <img :src="require('@/assets/images/appearance/'+item.pimg)" style="width:700px">
                </a>
                <div class="hotInfo">
                  <div class="productName">{{item.pname}}</div>
                  <div
                    style="
                      color: gray;
                      font-weight: bold;
                      font-size: 14px;
                      padding-top: 10px;
                    "
                  >
                    {{item.pdesc}}
                  </div>
                  <div class="productPrice"><span>{{item.price}}</span>元起</div>
                  <div style="margin-top:10px;"> <router-link style="text-decoration: none;" :to="{path:'/fundDetail',query:{id:tableData[index].id}}">了解更多</router-link></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- <el-row>
       <el-col :span="7" :offset="3" style="margin-top: 280px; text-align: left">
         <p class="big-text">现代东方美</p>
         <p class="middle-text">MODERN ORIENTAL</p>
         <p class="small-text">千雀灵来自东方，源于中国，</p>
         <p class="small-text">讲究东方美学的平衡和谐之道。</p>
         <p class="small-text">相信对于肌肤，强施不如调养，雕琢不如自然。</p>
         <p class="small-text">以草本自然能量，滋润养护肌肤。</p>
         <p class="small-text">让属于你的现代东方之美，自然散发。</p>
       </el-col>
        <el-col :span="14" style="margin-top: 210px">
          <video :src="videos[0]" loop muted autoplay type="video/mp4" width="680px"></video>
        </el-col>
      </el-row> -->
    
    <div style="margin-top: 0;">
      <el-carousel height="850px" style="z-index: 0;">
        <el-carousel-item v-for="item in carousel_images" :key="item">
          <el-image :src="item" height="850px"></el-image>
        </el-carousel-item>
      </el-carousel>
    </div>
  </div>
</template>

<script>
export default {
  name: "fund_v",
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
      carousel_images: [
        require("@/assets/images/botany/BG_04.jpg"),
        require("@/assets/images/botany/BG_02.jpg"),
        require("@/assets/images/botany/BG_01.jpg"),
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
.elab {
  margin-top: -30px;
  border: 0;
  padding: 0;
  list-style: none;
}

.elab video {
  border-radius: 6px;
}


.big-text {
  font-size: 34px;
  color: #ffffff;
  margin-bottom: 24px;
  font-family: Microsoft YaHei, sans-serif;
}
.middle-text {
  font-size: 17px;
  color: #ffffff;
  margin-bottom: 24px;
  font-family: Microsoft YaHei, sans-serif;
}
.small-text {
  color: #ffffff;
  font-family: Microsoft YaHei, sans-serif;
  line-height: 24px;
}
.productList {
  background: #f5f5f5;
  padding: 20px 0;
  background-image: url("@/assets/images/appearance/bg.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  margin-top: 0;
  min-height: 2150px;
}

.productList .productListContent .title {
  height: 40px;
  line-height: 40px;
  font-size: 18px;
  font-weight: 600;
  margin-bottom: 20px;
}

.productList .productListContent .hotKind .hotItem {
  height: 400px;
  width: 80%;
  position: relative;
  padding-right: 300px;
  box-sizing: border-box;
  background: #fff;
  transition: 0.3s;
  margin-bottom: 20px;
}
.productList .productListContent .hotKind .hotItem:hover {
  box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
  transform: translate3d(0, -5px, 0);
  z-index: 3;
}
.productList .productListContent .hotKind .hotItem .hotImgA {
  display: block;
  width: 760px;
  height: 400px;
}
.productList .productListContent .hotKind .hotItem .hotImgA img {
  display: block;
  width: 110%;
  height: 200px;
}

.productList .productListContent .hotKind .hotItem .hotInfo {
  position: absolute;
  top: 20px;
  right: 0;
  width: 300px;
  padding: 30px 40px;
  box-sizing: border-box;
}

.productList .productListContent .hotKind .hotItem .hotInfo .productName {
  font-size: 18px;
  font-weight: bolder;
  margin-top:-30px
}
.productList .productListContent .hotKind .hotItem .hotInfo .easyWord {
  line-height: 30px;
  color: rgba(51, 51, 51, 0.7);
}
.productList .productListContent .hotKind .hotItem .hotInfo .productPrice span {
  font-size: 26px;
}
.productList .productListContent .hotKind .hotItem .hotInfo .productPrice {
  margin-top: 10px;
  height: 40px;
  line-height: 40px;
  font-size: 14px;
  color: #ff6700;
}

.content{
  width:80%;
  margin-left: 300px;
  z-index: 0;
}
</style>
