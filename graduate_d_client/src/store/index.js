import Vue from 'vue'
import Vuex from 'vuex'
import uInfo from './state/userInfo.state.js'
Vue.use(Vuex)
export default new Vuex.Store({
  modules:{
    uInfo
  }
  
})
