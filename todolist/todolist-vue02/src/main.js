import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './vue.config'

// md5加密
import md5 from 'js-md5'
Vue.prototype.$md5 = md5

// 格式化时间
import Moment from 'moment'
Vue.filter('formatDate', function(value) {
    return Moment(value).format('YYYY-MM-DD HH:mm:ss'); // 加上480分钟，即8小时
    // return Moment(value).utcOffset(480).format('YYYY-MM-DD HH:mm:ss'); // 加上480分钟，即8小时
})


// element ui
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
Vue.use(Element)

// axios引入
import axios from 'axios'
// var axios = require('axios')
// TODO 服务器部署
axios.defaults.baseURL = "http://localhost:9000"  // 本地
// axios.defaults.baseURL = "http://182.92.198.13:9000"   // 服务器
axios.defaults.headers.get['Content-Type'] = 'application/json;charset:UTF-8';
Vue.prototype.$axios = axios

Vue.config.productionTip = false




//使用钩子函数判断是否拦截,在访问每一个路由前调用。
router.beforeEach((to, from, next) => {
        if (to.meta.requireAuth) {
            if (store.state.user) {
                next()
            } else {
                next({
                    path: 'login',
                    query: {redirect: to.fullPath}
                })
            }
        } else {
            next()
        }
    }
)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')


