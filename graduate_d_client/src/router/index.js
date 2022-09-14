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
import addBook from '../components/back/Book/addBook.vue'
import bookIndex from '../components/back/Book/bookIndex.vue'
import updateBook from '../components/back/Book/updateBook.vue'
import Index1_v from '../components/back/index1.vue'
//import welcome_v from '../components/back/customer/welcome.vue'
import findUserMo from '../components/back/User/findUserMo.vue'
import findBookMo from '../components/back/Book/findBookMo.vue'
//import findCustomerMo from '../components/back/customer/findCustomerMo.vue'
import findLendMo from '../components/back/Lend/findLendMo.vue'
import updateLend from '../components/back/Lend/updateLend.vue'
import lendIndex from '../components/back/Lend/lendIndex.vue'
import frontIndex from '../components/front/frontIndex.vue'
import frontHome from '../components/front/Home.vue'
import Appearance from '../components/front/Appearance.vue'
import Elaboration from '../components/front/Elaboration.vue'
import Flower from '../components/front/Flower.vue'
import Blogs from '../components/front/Blogs.vue'
import Info from '../components/front/Info.vue'
import Research from '../components/front/Research.vue'
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
        path: '/appearance',
        name: 'Appearance',
        component: Appearance
      },
      {
        path: '/elaboration',
        name: 'Elaboration',
        component: Elaboration
      },
      {
        path: '/flower',
        name: 'Flower',
        component: Flower
      },
      {
        path: '/blogs',
        name: 'Blogs',
        component: Blogs
      },
      {
        path: '/info',
        name: 'Info',
        component: Info
      },
      {
        path: '/research',
        name: 'Research',
        component: Research
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
          path: '/bookIndex',
          name: 'bookIndex',
          component: bookIndex
        },
        {
          path: '/updateBook',
          name: 'updateBook',
          component: updateBook
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
          path: '/addBook',
          name: 'addBook',
          component: addBook
        },
        {
          path: '/findUserMo',
          name: 'findUserMo',
          component: findUserMo,
         
        },
        {
          path: '/findBookMo',
          name: 'findBookMo',
          component: findBookMo
        },

        {
          path: '/findLendMo',
          name: 'findLendMo',
          component: findLendMo
        },
        {
          path: '/lendIndex',
          name: 'lendIndex',
          component: lendIndex
        },
        {
          path: '/updateLend',
          name: 'updateLend',
          component: updateLend
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





