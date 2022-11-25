import Vue from 'vue'
import VueRouter from 'vue-router'
//import store from '../store/index.js'
import ElementUI from 'element-ui'
import login from '../components/back/login.vue'
//import frontLogin from '../components/front/frontLogin.vue'
//import home from '../components/back/home.vue'
//import Index_v from '../components/back/User/index.vue'
//import add_v from '../components/back/User/addUser.vue'
//import role from '../components/back/User/role.vue'
//import productIndex from '../components/back/Product/productIndex.vue'
//import findProductByType from '../components/back/Product/findProductByType.vue'
import Index1_v from '../components/back/index1.vue'
//import findUserMo from '../components/back/User/findUserMo.vue'
//import findProductMo from '../components/back/Product/findProductMo.vue'
//import CartIndex from '../components/back/Cart/CartIndex.vue'
//import findCartMo from '../components/back/Cart/findCartMo.vue'
//import echarts from '../components/back/Data/index.vue'
import frontIndex from '../components/front/frontIndex.vue'
//import orderIndex from '../components/back/Order/orderIndex.vue'
//import findOrderMo from '../components/back/Order/findOrderMo.vue'
//import findOrderByState from '../components/back/Order/findOrderByState.vue'
//import frontHome from '../components/front/Home.vue'
//import Shares from '../components/front/Shares.vue'
//import Fund from '../components/front/Fund.vue'
//import Bond from '../components/front/Bond.vue'
//import Bank from '../components/front/Bank.vue'
import Insurance from '../components/front/Insurance.vue'
import Gold from '../components/front/Gold.vue'
//import Personal from '../components/front/Personal.vue'
//import Order from '../components/front/order/index.vue'
//import Cart from '../components/front/Cart.vue'
//import sharesDetail from '../components/front/details/sharesDetail.vue'
//import fundDetail from '../components/front/details/fundDetail.vue'
//import bondDetail from '../components/front/details/bondDetail.vue'
//import bankDetail from '../components/front/details/bankDetail.vue'
//import insuranceDetail from '../components/front/details/insuranceDetail.vue'
//import goldDetail from '../components/front/details/goldDetail.vue'
Vue.use(VueRouter)
Vue.use(ElementUI)
const router = new VueRouter({

  routes: [
    {
      path: '/',
      name: 'frontLogin',
      component: ()=>import('../components/front/frontLogin.vue')
    },
    {
      path: '/frontLogin',
      name: 'frontLogin',
      component: ()=>import('../components/front/frontLogin.vue')
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
        component: ()=>import('../components/front/Home.vue')
      },
      {
        path: '/shares',
        name: 'Shares',
        component: ()=>import('../components/front/Shares.vue')
      },
      {
        path: '/fund',
        name: 'Fund',
        component: ()=>import('../components/front/Fund.vue')
      },
      {
        path: '/bond',
        name: 'Bond',
        component: ()=>import('../components/front/Bond.vue')
      },
      {
        path: '/bank',
        name: 'Bank',
        component: ()=>import('../components/front/Bank.vue')
      },
      {
        path: '/insurance',
        name: 'Insurance',
        component: Insurance
      },
      {
        path: '/gold',
        name: 'Gold',
        component:Gold
      },
      {
        path: '/personal',
        name: 'Personal',
        component:()=>import('../components/front/Personal.vue')
      },
      {
        path: '/cart',
        name: 'Cart',
        component:()=>import('../components/front/Cart.vue')
      },
      {
        path: '/sharesDetail',
        name: 'sharesDetail',
        component: ()=>import('../components/front/details/sharesDetail.vue')
      },
      {
        path: '/fundDetail',
        name: 'fundDetail',
        component: ()=>import('../components/front/details/fundDetail.vue')
      },
      {
        path: '/bondDetail',
        name: 'bondDetail',
        component:()=>import('../components/front/details/bondDetail.vue')
      },
      {
        path: '/bankDetail',
        name: 'bankDetail',
        component:()=>import('../components/front/details/bankDetail.vue')
      },
      {
        path: '/insuranceDetail',
        name: 'insuranceDetail',
        component: ()=>import('../components/front/details/insuranceDetail.vue')
      },
      {
        path: '/goldDetail',
        name: 'goldDetail',
        component: ()=>import('../components/front/details/goldDetail.vue')
      },
      {
        path: '/order',
        name: 'Order',
        component: ()=>import('../components/front/order/index.vue')
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
          component: ()=>import('../components/back/home.vue'),
        },
        {
          path: '/index',
          name: 'index',
          component: ()=>import('../components/back/User/index.vue'),
         
        },
       
        {
          path: '/productIndex',
          name: 'productIndex',
          component: ()=>import('../components/back/Product/productIndex.vue')
        },
        {
          path: '/cartIndex',
          name: 'cartIndex',
          component: ()=>import('../components/back/Cart/CartIndex.vue')
        },
        {
          path: '/findCartMo',
          name: 'findCartMo',
          component:  ()=>import('../components/back/Cart/findCartMo.vue')
        },
        
        {
          path: '/findProductByType',
          name: 'findProductByType',
          component: ()=>import('../components/back/Product/findProductByType.vue')
        },
        {
          path: '/orderIndex',
          name: 'orderIndex',
          component: ()=>import('../components/back/Order/orderIndex.vue')
        },
        {
          path: '/findOrderMo',
          name: 'findOrderMo',
          component: ()=>import('../components/back/Order/findOrderMo.vue')
        },
        {
          path: '/findOrderByState',
          name: 'findOrderByState',
          component: ()=>import('../components/back/Order/findOrderByState.vue')
        },
        {
          path: '/role',
          name: 'role',
          component:()=>import('../components/back/User/role.vue')
          
        },
        {
          path: '/add',
          name: 'add',
          component: ()=>import('../components/back/User/addUser.vue'),
         
        },
      
        {
          path: '/findUserMo',
          name: 'findUserMo',
          component: ()=>import('../components/back/User/findUserMo.vue'),
         
        },
        {
          path: '/findProductMo',
          name: 'findProductMo',
          component: ()=>import('../components/back/Product/findProductMo.vue')
        },
        {
          path: '/echarts',
          name: 'echarts',
          component:()=>import('../components/back/Data/index.vue')
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
 
  //window.scrollTo(0,0);
  /*
    to:从哪个页面
    from:到哪个页面
    next：只有执行next()页面才会进行跳转
  */

  //判断用户是否登录

  const uInfo = localStorage.getItem('loginData')

    //未登录,跳转到login
    if (!uInfo && to.path !== "/frontLogin"&&to.path!=='/login') {
      next('/frontLogin')
    }
    next();
})
export default router;






