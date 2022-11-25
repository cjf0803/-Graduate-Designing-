<template>
  <div id="app">
    <el-backtop :bottom="100">
      <div
        style="
           {
            height: 100%;
            width: 140%;
            background-color: #f2f5f6;
            box-shadow: 0 0 6px rgba(0, 0, 0, 0.12);
            text-align: center;
            line-height: 40px;
            color: #1989fa;
          }
        "
      >
          <i class="el-icon-top"></i>
      </div>
      </el-backtop>
    <router-view v-if="showRouterView"></router-view>
  </div>
</template>

<script>
export default {
  name: "App",
  provide() {
    return {
      reload: this.reload,
    };
  },
  data() {
    return {
      showRouterView: true,
    };
  },

  methods: {
    reload() {
      // 改变this.showRouterView的状态，控制路由出口的显示隐藏
      this.showRouterView = false;
      this.$nextTick(() => {
        // 必须使用nextTick，否则最新dom可能未更新，导致刷新失败
        this.showRouterView = true;
      });
    },
  },
};
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
.el-table th.el-table__cell > .cell {
  z-index: 0;
}
.el-table--enable-row-transition .el-table__body td.el-table__cell {
  z-index: 0;
}
</style>
