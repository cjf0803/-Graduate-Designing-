import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '../store/index.js'
import ElementUI from 'element-ui'
import login from '../components/back/login.vue'
import frontLogin from '../components/front/frontLogin.vue'
import home from '../components/back/home.vue'
import Index_v from '../components/back/User/index.vue'
import add_v from '../components/back/User/addUser.vue'
import role from '../components/back/User/role.vue'
import productIndex from '../components/back/Product/productIndex.vue'
import findProductByType from '../components/back/Product/findProductByType.vue'
import Index1_v from '../components/back/index1.vue'
import findUserMo from '../components/back/User/findUserMo.vue'
import findProductMo from '../components/back/Product/findProductMo.vue'
import CartIndex from '../components/back/Cart/CartIndex.vue'
import findCartMo from '../components/back/Cart/findCartMo.vue'
import echarts from '../components/back/Data/index.vue'
import frontIndex from '../components/front/frontIndex.vue'
import orderIndex from '../components/back/Order/orderIndex.vue'
import findOrderMo from '../components/back/Order/findOrderMo.vue'
import findOrderByState from '../components/back/Order/findOrderByState.vue'
import frontHome from '../components/front/Home.vue'
import Shares from '../components/front/Shares.vue'
import Fund from '../components/front/Fund.vue'
import Bond from '../components/front/Bond.vue'
import Bank from '../components/front/Bank.vue'
import Insurance from '../components/front/Insurance.vue'
import Gold from '../components/front/Gold.vue'
import Personal from '../components/front/Personal.vue'
import Pay from '../components/front/Pay/index.vue'
import Order from '../components/front/order/index.vue'
import Cart from '../components/front/Cart.vue'
import sharesDetail from '../components/front/details/sharesDetail.vue'
import fundDetail from '../components/front/details/fundDetail.vue'
import bondDetail from '../components/front/details/bondDetail.vue'
import bankDetail from '../components/front/details/bankDetail.vue'
import insuranceDetail from '../components/front/details/insuranceDetail.vue'
import goldDetail from '../components/front/details/goldDetail.vue'
Vue.use(VueRouter)
Vue.use(ElementUI)
const router = new VueRouter({

  routes: [
    {
      path: '/',
      name: 'frontLogin',
      component: frontLogin
    },
    {
      path: '/frontLogin',
      name: 'frontLogin',
      component: frontLogin
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    
    {
      path: '/frontIndex',
      name: 'frontIndex',
      redirect:'/frontHome',
      component: frontIndex,
      children: [
        {
        path: '/frontHome',
        name: 'frontHome',
        component: frontHome
      },
      {
        path: '/shares',
        name: 'Shares',
        component: Shares
      },
      {
        path: '/fund',
        name: 'Fund',
        component: Fund
      },
      {
        path: '/bond',
        name: 'Bond',
        component: Bond
      },
      {
        path: '/bank',
        name: 'Bank',
        component: Bank
      },
      {
        path: '/insurance',
        name: 'Insurance',
        component: Insurance
      },
      {
        path: '/gold',
        name: 'Gold',
        component: Gold
      },
      {
        path: '/personal',
        name: 'Personal',
        component: Personal
      },
      {
        path: '/cart',
        name: 'Cart',
        component: Cart
      },
      {
        path: '/sharesDetail',
        name: 'sharesDetail',
        component: sharesDetail
      },
      {
        path: '/fundDetail',
        name: 'fundDetail',
        component: fundDetail
      },
      {
        path: '/bondDetail',
        name: 'bondDetail',
        component: bondDetail
      },
      {
        path: '/bankDetail',
        name: 'bankDetail',
        component: bankDetail
      },
      {
        path: '/insuranceDetail',
        name: 'insuranceDetail',
        component: insuranceDetail
      },
      {
        path: '/goldDetail',
        name: 'goldDetail',
        component: goldDetail
      },
      {
        path: '/pay',
        name: 'Pay',
        component: Pay
      },
      {
        path: '/order',
        name: 'Order',
        component: Order
      },
      
    ]
    },

    {
      path: '/index1',
      name: 'index1',
      component: Index1_v,
      redirect: 'home',
      children: [
        {
          path: '/home',
          name: 'home',
          component: home,
        },
        {
          path: '/index',
          name: 'index',
          component: Index_v,
         
        },
       
        {
          path: '/productIndex',
          name: 'productIndex',
          component: productIndex
        },
        {
          path: '/cartIndex',
          name: 'cartIndex',
          component: CartIndex
        },
        {
          path: '/findCartMo',
          name: 'findCartMo',
          component: findCartMo
        },
        
        {
          path: '/findProductByType',
          name: 'findProductByType',
          component: findProductByType
        },
        {
          path: '/orderIndex',
          name: 'orderIndex',
          component: orderIndex
        },
        {
          path: '/findOrderMo',
          name: 'findOrderMo',
          component: findOrderMo
        },
        {
          path: '/findOrderByState',
          name: 'findOrderByState',
          component: findOrderByState
        },
        {
          path: '/role',
          name: 'role',
          component: role,
          
        },
        {
          path: '/add',
          name: 'add',
          component: add_v,
         
        },
      
        {
          path: '/findUserMo',
          name: 'findUserMo',
          component: findUserMo,
         
        },
        {
          path: '/findProductMo',
          name: 'findProductMo',
          component: findProductMo
        },
        {
          path: '/echarts',
          name: 'echarts',
          component: echarts
        },
        
      ]
    },



  ]
})
// const originalPush = router.prototype.push
// router.prototype.push = function push(location) {
//   return originalPush.call(this, location).catch(err => err)
// }
router.beforeEach((to, from, next) => {
 
  window.scrollTo(0,0);
  /*
    to:从哪个页面
    from:到哪个页面
    next：只有执行next()页面才会进行跳转
  */

  //判断用户是否登录

  const uInfo = store.state.uInfo.userInfo
  if (!uInfo) {

    //未登录,跳转到login
    if (to.path === "/frontIndex") {
      next()
      return
    }
    next("/frontIndex")

  } else {
    next()
  }
})
export default router;





