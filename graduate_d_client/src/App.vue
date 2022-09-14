<template>
  <div id="app">
    
      <router-view v-if="showRouterView"/>
    
    
  </div>
</template>

<script>


export default {
  name: 'App',
  components: {
    
  },
  provide(){
    return{
      reload:this.reload
    }
  },
  data(){
    return{
      showRouterView:true
    }
  },
  methods: {
    reload (){
       // 改变this.showRouterView的状态，控制路由出口的显示隐藏
       this.showRouterView = false
       this.$nextTick(()=>{   // 必须使用nextTick，否则最新dom可能未更新，导致刷新失败
          this.showRouterView = true
       })
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 30px;
  
}

</style>
