<template>
  <div id="box">
    <div class="container1">
        <div class="card" v-for="(item,index) in tableData" :key="index">
            <div class="card-cover" style="margin-top:50px;"> 
              <img :src="require('@/assets/images/appearance/'+item.pimg)" style="height:60%;width:100%; border-radius: 8%;">
              <h1 style="margin-bottom: 5px;">{{ item.pname }}</h1>
              <h2 style="color:crimson;font-size: 18px;margin-bottom: 5px;">平均收益率%:{{ item.averagerate }}%</h2>
              <p style="padding-right: 15px;margin-bottom: 15px;">产品描述：{{ item.pdesc }}</p>
              
              <el-link type="info" style="font-weight:bolder;font-size: 17px;"> <router-link style="text-decoration:none" :to="{path:'/bondDetail',query:{id:tableData[index].id}}">了解更多</router-link></el-link>
            </div>
        </div>
    </div>
    

    
  </div>
</template>

<script>
  export default {
    name:"Bond_v",
    data () {

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
        url: require('@/assets/images/flowers/ad1.png'),
        discount: require('@/assets/images/flowers/dis.png'),
        carousel_items: [
          require('@/assets/images/flowers/ca1.png'),
          require('@/assets/images/flowers/ca2.png'),
          require('@/assets/images/flowers/ca3.png')
        ],
        video_images: [
          require('@/assets/images/flowers/video1.png'),
          require('@/assets/images/flowers/video2.png')
        ],
       
      }
    },
    mounted(){
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

    }

  }
</script>

<style scoped>
  *{
    /* 初始化 */
    margin: 0;
    padding: 0;
}

#box{
   
     /* 100%的窗口高度 */
     min-height: 1000px;
    /* 弹性布局 水平+垂直居中 */
    float: left;
    background-image: url("@/assets/images/appearance/bg.jpg");
    margin-top: -30px;

}

.container1{
    width: 100%;
    width: 1200px;
    display: flex;
    overflow: hidden;
    flex-flow: wrap;
    margin-left: 278px;
}

.card{
    /* 相对定位 */
    position: relative;
    /* 自动放大占满剩余空间 */
    height: 560px;
    width:35%;
    margin: 0 35px;
    overflow: hidden;
    display: flex;
    flex-direction: column;
    border-radius: 20%;
  
}
/* 投影效果 */
.card::before{
    content: "";
    width: 90%;
    height: 90%;
    background-color: rgba(85, 94, 105, 0.5);
    /* 绝对定位 */
    position: absolute;
    left: 5%;
    top: 5%;
    /* 投影 */
    /* box-shadow: 0 60px 12px 12px rgba(0, 0, 0, 0.4); */
    box-shadow: 0 6px 12px 6px rgba(61, 61, 61, 0.4);
    opacity: 1;
    /* 设置旋转元素的基点位置 */
    transform-origin: top center;
    /* 设置过渡时间:时长 加速后减速 */
    transition: 0.3s ease-in-out;
    border-radius: 20%;
}

/* 鼠标移上,投影的变化 */
.card:hover:before{
    opacity: 0.5;
    transform: rotateX(7deg) translateY(-6px) scale(1.05);
}
/* 封面 */
.card-cover{
    /* 绝对定位 */
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background-size: cover;
    background-position: center;
    /* 溢出隐藏 */
    overflow: hidden;
    /* 开启3D效果 */
    transform-style: preserve-3d;
    /* 设置旋转元素的基点位置 */
    transform-origin: top center;
    /* 设置过渡 */
    transition: transform 0.3s ease-in-out;    
}

.card-cover::after{
    content: "";
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 120%;
    /* 背景渐变(高光效果) */
    background: linear-gradient(226deg,rgba(255,255,255,0) 40%,rgba(255,255,255,0.3) 50%,rgba(255,255,255,0) 60%);
    /* background: linear-gradient(226deg,rgba(225,255,255,0) 40%,rgba(225,255,255,0.3) 50%,rgba(225,255,255,0) 60%,); */
    /* 默认上移的可视化范围 */
    transform: translateY(-100%);
    /* 设置过渡 */
    /* transform: transform 0.85s cubic-bezier(0.165, 0.84, 0.44, 1) ; */
    transition: transform 0.85s cubic-bezier(0.165, 0.84, 0.44, 1);

}

.card:hover .card-cover{
    transform: rotateX(7deg) translateY(-6px);
}

.card:hover .card-cover::after{
    transform: translateY(100%);
}
</style>
