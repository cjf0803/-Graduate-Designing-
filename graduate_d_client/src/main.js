import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import ElementUI from 'element-ui'
import echarts from "echarts";
import jquery from 'jquery'
import 'element-ui/lib/theme-chalk/index.css';
import store from './store'
import SlideVerify from 'vue-monoplasty-slide-verify';
Vue.use(ElementUI);
Vue.use(SlideVerify);

Vue.prototype.$echarts = echarts;
//解决sessionid，如果是axios请求用下面的配置
axios.defaults.withCredentials=true
Vue.config.productionTip = false
Vue.prototype.$http=axios
Vue.prototype.$jquery=jquery

new Vue({
  render: h => h(App),
  router,
  store
  
}).$mount('#app')
