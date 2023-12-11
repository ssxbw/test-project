import Vue from 'vue';
import App from './App.vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Plugin from 'v-fit-columns';
import router from './router';
import './assets/icon/iconfont.css'

// 引入echarts
import echarts from 'echarts'
Vue.prototype.$echarts = echarts

Vue.config.productionTip = false;

Vue.prototype.HOST = 'http://localhost:80';
Vue.prototype.$axios = axios;

axios.interceptors.request.use(config=>{
  console.log(config)
  config.headers.Authorization=window.sessionStorage.getItem('token')
  return config
})


// 过滤器
import * as custom from './utils/util'
import store from "@/vuex/store";
import axios from "axios";
Object.keys(custom).forEach(key => {
  Vue.filter(key, custom[key])
})
router.beforeEach((to, from, next) => {
  if (to.matched.length != 0) {
    if (to.meta.requireAuth) { // 判断该路由是否需要登录权限
      if (Boolean(localStorage.getItem("userInfo"))) { // 通过vuex state获取当前的user是否存在
        next();
      } else {
        next({
          path: '/manager',
          query: { redirect: to.fullPath } // 将跳转的路由path作为参数，登录成功后跳转到该路由
        })
      }
    } else {
      if (Boolean(localStorage.getItem("userInfo"))) { // 判断是否登录
        if (to.path !== "/" && to.path !== "/manager") { //判断是否要跳到登录界面
          next();
        } else {
          /**
           * 防刷新，如果登录，修改路由跳转到登录页面，修改路由为登录后的首页
           */
          next({
            path: '/recon'
          })
        }
      } else {
        next();
      }
    }
  } else {
    next({
      path: '/manager',
      query: { redirect: to.fullPath } // 将跳转的路由path作为参数，登录成功后跳转到该路由
    })
  }
})

Vue.use(Plugin);
Vue.use(ElementUI);

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
});
