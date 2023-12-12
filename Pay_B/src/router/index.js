import Vue from 'vue'
import Router from 'vue-router'

/*import UserView from '../views/UserView.vue'
import personal_information from '../views/personal_information.vue'
import cash_information from '../views/cash_information.vue'
import pay_list from '../views/pay_list.vue'
import LoginView from '../views/LoginView.vue'
import RetrievePassword from '../views/RetrievePassword.vue'
import BuyerView from "@/views/BuyerView.vue";
import SellerView from "@/views/SellerView.vue";
import OrderView from "@/views/OrderView.vue";
import Dept from "@/views/hotelviews/system/Dept.vue";
import Variable from "@/views/hotelviews/system/Variable.vue";
import Order from "@/views/hotelviews/pay/Order.vue";
import user from "@/views/hotelviews/system/user.vue";
import Role from "@/views/hotelviews/system/Role.vue";
import Permission from "@/views/hotelviews/system/Permission.vue";
import Config from "@/views/hotelviews/pay/Config.vue";
import hotel from "@/views/hotelviews/hotel/index.vue";
import yhhotel from '@/views/hotelviews/hotel/yhindex';
import jdorder from '@/views/hotelviews/order/jdorder';
import flight from '@/views/hotelviews/flight/index';
import yhflight from '@/views/hotelviews/flight/yhindex';
import hborder from '@/views/hotelviews/order/hborder';
import Module from '@/views/hotelviews/system/Module';
import index from '@/views/hotelviews/index';
import login from "@/views/hotelviews/login.vue";*/
import managerLogin from "@/views/manager_views/login.vue";
/*import System from "@/views/manager_views/system.vue";
import Welcome from "@/views/manager_views/Welcome.vue";
import Register from "@/views/manager_views/manager/register.vue";
import Modify from "@/views/manager_views/manager/modify.vue";
import Information from "@/views/manager_views/manager/information.vue";*/
import ReconMenu from "@/views/ReconMenu.vue";
import ReconView from "@/views/recon_views/ReconView.vue";
import AbnormalView from "@/views/recon_views/AbnormalView.vue";

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: '/manager'
    },
    /*{
      path: '/LoginView',
      name: 'LoginView',
      component: LoginView
    },
    {
      path: '/UserView',
      name: 'UserView',
      component: UserView
    },
    {
      path: '/personal_information',
      name: 'personal_information',
      component: personal_information
    },
    {
      path: '/cash_information',
      name: 'cash_information',
      component: cash_information
    },
    {
      path: '/pay_list',
      name: 'pay_list',
      component: pay_list
    },
    {
      path: '/RetrievePassword',
      name: 'RetrievePassword',
      component: RetrievePassword
    },
    {
      path: '/buyer',
      name: 'buyer_item',
      component: BuyerView
    },
    {
      path: '/seller',
      name: 'seller_item',
      component: SellerView
    },
    {
      path: '/order',
      name: 'order',
      component: OrderView
    }, {
      path: '/hotel',
      name: '首页',
      component: index,
      iconCls: 'el-icon-tickets',
      children: [{
        path: '/pay/Order',
        name: '交易订单',
        component: Order,
        meta: {
          requireAuth: true
        }
      }, {
        path: '/system/user',
        name: '用户管理',
        component: user,
        meta: {
          requireAuth: true
        }
      }, {
        path: '/system/Module',
        name: '菜单管理',
        component: Module,
        meta: {
          requireAuth: true
        }
      }, {
        path: '/system/Role',
        name: '角色管理',
        component: Role,
        meta: {
          requireAuth: true
        }
      }, {
        path: '/system/Dept',
        name: '公司管理',
        component: Dept,
        meta: {
          requireAuth: true
        }
      }, {
        path: '/system/Variable',
        name: '系统环境变量',
        component: Variable,
        meta: {
          requireAuth: true
        }
      }, {
        path: '/system/Permission',
        name: '权限管理',
        component: Permission,
        meta: {
          requireAuth: true
        }
      }, {
        path: '/pay/Config',
        name: '支付配置',
        component: Config,
        meta: {
          requireAuth: true
        }
      }, {
        path: '/hotel/index',
        name: '酒店管理',
        component: hotel,
        meta: {
          requireAuth: true
        }
      }, {
        path: '/hotel/yhindex',
        name: '酒店查询',
        component: yhhotel,
        meta: {
          requireAuth: true
        }
      }, {
        path: '/order/jdorder',
        name: '酒店订单',
        component: jdorder,
        meta: {
          requireAuth: true
        }
      },{
        path: '/flight/index',
        name: '航班管理',
        component: flight,
        meta: {
          requireAuth: true
        }
      }, {
        path: '/flight/yhindex',
        name: '航班查询',
        component: yhflight,
        meta: {
          requireAuth: true
        }
      }, {
        path: '/order/hborder',
        name: '航班订单',
        component: hborder,
        meta: {
          requireAuth: true
        }
      }],
    },*/
    {
      path: '/manager',
      component: managerLogin,
    },
    /*{
      path:'/system',
      component:System,
      redirect:'/welcome',
      children:[{
        path:'/welcome',
        component:Welcome,
        beforeEnter(to, from, next) {
          const tokenstr = window.sessionStorage.getItem('token')
          if (!tokenstr) return next('/manager')
          next()
        }
      },
        {
          path:'/register',
          component:Register,
          props: { name: 'name' ,password:'password'},
          beforeEnter(to, from, next) {
            const tokenstr = window.sessionStorage.getItem('token')
            if (!tokenstr) return next('/manager')
            next()
          }
        },
        {
          path:'/modify',
          component:Modify,
          beforeEnter(to, from, next) {
            const tokenstr = window.sessionStorage.getItem('token')
            if (!tokenstr) return next('/manager')
            next()
          }
        },
        {
          path:'/information',
          component:Information,
          beforeEnter(to, from, next) {
            const tokenstr = window.sessionStorage.getItem('token')
            if (!tokenstr) return next('/manager')
            next()
          }
        }],
      beforeEnter(to, from, next) {
        const tokenstr = window.sessionStorage.getItem('token')
        if (!tokenstr) return next('/manager')
        next()
      }
    },*/




    {
      path:'/recon',
      component:ReconMenu,
      redirect:'/recon/reconlist',
      children:[{
        path:'/recon/reconlist',
        component:ReconView
      },
        {
          path:'/recon/abnormal',
          component:AbnormalView,
        },],
        beforeEnter(to, from, next) {
          const tokenstr = window.sessionStorage.getItem('token')
          if (!tokenstr) return next('/manager')
          next()
        },
    },
  ]
})