<template>
  <div class="container1">
    <el-row  style="margin: 0 30px">
      <div class="content" style="margin-top: 50px; " v-for="(item, index) in tableData" :key="index">
        <el-image :src="require('@/assets/images/appearance/' + item.pimg)" style="margin: 0 20px;height: 300px;width: 700px;"></el-image>
        <p style="margin-top: 10px;text-align: center; font-size: 1.25rem; line-height: 1.5rem; font-weight: 700; font-style: normal;">
         
          {{item.pname}}</p>
          <h2 style="color:crimson;margin-top: 10px;">平均收益率%:{{ item.averagerate }}%</h2>
          <p style="margin-top: 10px;">产品描述：{{item.pdesc}}</p>
          <el-link style="margin-top: 20px;"><router-link style="text-decoration:none;font-weight: bolder;" :to="{path:'/goldDetail',query:{id:tableData[index].id}}">查看详情</router-link>
</el-link>
          <el-divider></el-divider>
        </div>
       
    </el-row>
    <el-divider></el-divider>
  </div>
 
</template>

<script>
  export default {
    name:"Gold_v",
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
  .container1{
    background-image: url("@/assets/images/appearance/bg.jpg");
    flex-flow: wrap;
  }
.content{
 /* 溢出隐藏 */
  overflow: hidden;
 
 
}
</style>
