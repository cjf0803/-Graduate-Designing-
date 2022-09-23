<template>
    <div>
      <el-backtop target=".page-component__scroll .el-scrollbar__wrap"></el-backtop>
      <el-main class="content">     
        <el-row>
          <el-col :span="16" :offset="4">
            <el-carousel height="400px" type="card"  :interval="4000">
              <el-carousel-item v-for="item in advertises" :key="item" class="content-carousel">
                <el-image style="height: 400px" :src="item"></el-image>
              </el-carousel-item>
            </el-carousel>

          </el-col>
        </el-row>
        <el-row class="tech">
          <el-col :span="8">
            <p class="tech-content1">我们致力于用科技让普通人和小企业</p>
            <p class="tech-content2">享受平等的金融和理财服务。</p>
          </el-col>
          <el-col :span="16">
            <el-container>
              <el-card v-for="(tech, index) in technologys" :key="index" :body-style="{padding: '0px'}" class="tech-card">
                <el-image :src="tech.url" style="height:280px"></el-image>
                <div class="tech-content-link">
                  <el-link :underline="false">{{tech.name}}</el-link>
                </div>
              </el-card>
            </el-container>
          </el-col>
        </el-row>
      </el-main>
      <el-main class="news">
        <el-row>
          <el-col :span="20" :offset="2">
            <p class="news-title">耀眼东方</p>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="20" :offset="2">
            <el-container style="margin-left:10px">
              <el-card  v-for="(news, index) in ferodeNews" :key="index" :body-style="{padding: '0px'}" class="news-card">
                <el-image :src="news.url" style="height:280px"></el-image>
                <div style="padding: 14px">
                  <el-link :underline="false" class="news-info">{{news.info}}</el-link>
                </div>
              </el-card>
            </el-container>
          </el-col>
        </el-row>
      </el-main>
      <el-row class="goods">
   <el-col :span="22" :offset="1">
     <el-image v-for="(item, index) in appList" :src="item" :key="index"></el-image>
   </el-col>
  </el-row>
    </div>
</template>

<script>
  export default {
    name: 'frontHome',
    data (){
      let appList = new Array(6)
      for(let i = 1; i<=6; i++){
        appList[i-1] = require('@/assets/images/appearance/shares'+i+'.png')
      }
      return {
        appList: appList,
        advertises: [
          require('@/assets/images/advertisement/home1.png'),
          require('@/assets/images/advertisement/home7.png'),
          require('@/assets/images/advertisement/home3.png'),
          require('@/assets/images/advertisement/home4.png')
        ],
        technologys:[
          {
            name: '追求黄金配比，分享股债机遇',
            url: require('@/assets/images/techs/tech4.jpg')
          },
          {
            name: '收益有优势，越久越显著',
            url: require('@/assets/images/techs/tech1.jpg')
          },
          {
            name: '稳健理财人气精品Top10',
            url: require('@/assets/images/techs/tech2.jpg')
          },
          {
            name: '力争降低回撤，保护收益成果',
            url: require('@/assets/images/techs/tech3.jpg')
          },
       
        ],
        ferodeNews:[
          {
            info: '我们致力于用区块链技术构建新一代的信任机制，提高价值流转和多方协同的效率',
            url: require('@/assets/images/news/news-01.png')
          },
          {
            info: '万物互通互联是IoT技术的核心，也是我们重要的技术战略.',
            url: require('@/assets/images/news/news-02.png')
          },
          {
            info: '计算技术的发展和普及，是实现普惠金融的基础。我们通过十多年的探索和实践。',
            url: require('@/assets/images/news/news-03.png')
          },
          {
            info: '我们致力于推动智能风控、生物核身、数据安全三大安全技术领域的研究和探索',
            url: require('@/assets/images/news/news-04.png')
          }
        ]
      }
    },
    mounted(){
    this.$nextTick(() => {
      this.drawChart();
     
    });
      this.$notify.info({
          title: '消息提醒',
          message: '登录成功后记得点击右上角开通账户~'
        });
    },
    methods:{}

  }
  
</script>

<style scoped>
  .content{
    background-image: url("@/assets/images/appearance/bg.jpg");
  }
  .content .content-carousel {
    border-radius: 6px;
    z-index: 0.1;
  }

  .content .tech {
    margin: 40px 100px 25px 100px;
  }
  .content .tech .tech-content1 {
    font-size: 20px;
    font-weight: bold;
    margin-top: 80px;
  }
  .content .tech .tech-content2{
    font-size: 40px;
    margin-top: 40px;
  }
  .content .tech .tech-card {
    width: 180px;
    margin: 0 10px;
    height: 380px;
  }
  .content .tech .tech-card > .el-image {
    width: 100%;
  }
  .content .tech .tech-card .tech-content-link{
    padding: 14px;
  }

  .news {
    background-color: azure
  }
  .news .news-title{
    font-size: 30px;
    margin: 20px 0 0 22px;

  }
  .news .news-card {
    width: 280px;
    height: 380px;
    border: none;
    padding: 20px;
    background-color: azure;
    margin-left: 25px;
    margin-top: 10px;
  }
  .news .news-card > .el-image{
    width: 100%;
  }
  .news .news-card .news-info {
    font-size: 16px;
  }
</style>
