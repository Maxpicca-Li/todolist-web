import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/Login.vue'
import Register from '@/views/Register.vue'
import Todo from "@/views/Todo";
import TodoEdit from "@/views/TodoEdit";
import Home from "@/views/Home";
import MyMain from "@/views/MyMain";
import pwdEdit from "@/views/pwdEdit";
import NameEdit from "@/views/NameEdit";
// TODO temp测试
import Temp from "@/components/temp/Temp";
import TempIndex from "@/components/temp/TempIndex";
import LibraryIndex from "@/components/temp/LibraryIndex";


Vue.use(VueRouter)

const routes = [
  // { path: '/', name: 'Home', redirect:'/index'},

  { path: '/', name: 'Home', component: Home, redirect: '/index',
    children:[
      { path:'/my', name: 'MyMain', component: MyMain, meta: { requireAuth: true,} },
      { path:'/index', name: 'Todo', component: Todo, meta: { requireAuth: true,} },
  ]},

  { path: '/login', name: 'Login', component: Login },

  /*{ path:'/index', name: 'Index', component: AppIndex,
    meta: {
      requireAuth: true  //  需要登录字后才能访问的受限资源，=> 路由权限拦截
    }
  },*/


  { path: '/register', name: 'Register', component: Register },

  { path:'/todoedit', name: 'TodoEdit', component: TodoEdit, meta: { requireAuth: true,} },
  { path:'/pwdEdit',name: 'pwdEdit',component: pwdEdit,meta: {requireAuth: true}},
  { path:'/nameEdit',name: 'NameEdit',component: NameEdit,meta: {requireAuth: true}},
/*  // TODO temp测试
  { path: '/tempindex', name: 'TempIndex', component: TempIndex},
  { path:'/temp', name: 'Temp', component: Temp,
    children:[
      { path: '/tempindex', name: 'TempIndex', component: TempIndex},
      { path: '/library', name: 'Library', component: LibraryIndex},
    ]
  }*/

]

const router = new VueRouter({
  mode: 'history',
  // base: process.env.BASE_URL,
  routes
})

export default router
