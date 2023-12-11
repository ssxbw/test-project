<template>
  <!-- <el-container class="index-con">
    <el-header class="index-header">
      <navcon></navcon>
    </el-header>
    <el-container class="index-con">
      <el-aside :class="showclass">
        <leftnav></leftnav>
      </el-aside>
      <el-container class="main-con">
        <el-main clss="index-main">
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </el-container> -->

  <el-container class="index-con">
    <el-aside :class="showclass">
      <!-- <leftnav></leftnav> -->
      <el-menu default-active="2" :collapse="collapsed" collapse-transition router :default-active="$route.path" unique-opened class="el-menu-vertical-demo" background-color="rgb(30, 106, 186)" text-color="#fff" active-text-color="#ffd04b">
        <div class="logobox">
          <img class="logoimg" src="../../assets/img/icon.png" alt="">
        </div>
        <el-submenu v-for="menu in allmenu" :key="menu.menuid" :index="menu.menuname">
          <template slot="title">
            <i class="iconfont" :class="menu.icon"></i>
            <span>{{menu.menuname}}</span>
          </template>
          <el-menu-item-group>
            <el-menu-item v-for="chmenu in menu.menus" :index="'/'+chmenu.url" :key="chmenu.menuid">
              <i class="iconfont" :class="chmenu.icon"></i>
              <span>{{chmenu.menuname}}</span>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-aside>
    <el-container class="main-con">
      <el-header style="height: 15%;background-color: rgb(30, 106, 186);border-left: 2px solid #333;">
        <navcon></navcon>
      </el-header>

      <el-header style="height: 5px;background-color: #fff;border-left: 2px solid #333;">
      </el-header>
      <el-main class="index-main">
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
// 导入组件
import navcon from '../../components/navcon.vue'
import leftnav from '../../components/leftnav.vue'
export default {
  name: 'index',
  data() {
    return {
      showclass: 'asideshow',
      showtype: false
    }
  },
  // 注册组件
  components: {
    navcon,
    leftnav
  },
  methods: {},
  created() {
    var type = localStorage.getItem('usertype');

    // // 监听
    // this.$root.Bus.$on('toggle', value => {
    //   if (value) {
    //     this.showclass = 'asideshow'
    //   } else {
    //     setTimeout(() => {
    //       this.showclass = 'aside'
    //     }, 300)
    //   }
    // })

    if (type == 'B'){
      let res = {
        success: true,
        data: [
          {
            menuid: 1,
            icon: 'li-icon-xiangmuguanli',
            menuname: '酒店',
            hasThird: null,
            url: null,
            menus: [
              {
                menuid: 2,
                icon: 'icon-cat-skuQuery',
                menuname: '酒店查询',
                hasThird: 'N',
                url: 'hotel/yhindex',
                menus: null
              }
            ]
          },
          {
            menuid: 33,
            icon: 'li-icon-dingdanguanli',
            menuname: '订单管理',
            hasThird: null,
            url: null,
            menus: [
              {
                menuid: 34,
                icon: 'icon-order-manage',
                menuname: '酒店订单',
                hasThird: 'N',
                url: 'order/jdorder',
                menus: null
              },{
                menuid: 35,
                icon: 'icon-order-manage',
                menuname: '航班订单',
                hasThird: 'N',
                url: 'order/hborder',
                menus: null
              }
            ]
          },

          {
            menuid: 151,
            icon: 'li-icon-shangchengxitongtubiaozitihuayuanwenjian91',
            menuname: '航班',
            hasThird: null,
            url: null,
            menus: [
              {
                menuid: 160,
                icon: 'icon-provider-manage',
                menuname: '航班查询',
                hasThird: 'N',
                url: 'flight/yhindex',
                menus: null
              }
            ]
          }
        ],
        msg: 'success'
      }
      this.allmenu = res.data
    }else{
      let res1 = {
        success: true,
        data: [
          {
            menuid: 33,
            icon: 'li-icon-dingdanguanli',
            menuname: '酒店',
            hasThird: null,
            url: null,
            menus: [
              {
                menuid: 34,
                icon: 'icon-order-manage',
                menuname: '酒店管理',
                hasThird: 'N',
                url: 'hotel/index',
                menus: null
              }
            ]
          }, {
            menuid: 35,
            icon: 'li-icon-dingdanguanli',
            menuname: '航班',
            hasThird: null,
            url: null,
            menus: [
              {
                menuid: 36,
                icon: 'icon-order-manage',
                menuname: '航班管理',
                hasThird: 'N',
                url: 'flight/index',
                menus: null
              }
            ]
          }
        ],
        msg: 'success'
      }

      this.allmenu = res1.data
    }


  },
  beforeUpdate() {},
  // 挂载前状态(里面是操作)
  beforeMount() {
    // 弹出登录成功
    // this.$message({
    //   message: '登录成功',
    //   type: 'success'
    // })
  }
}
</script>
<style >
.index-con {
  height: 100%;
  width: 100%;
  box-sizing: border-box;
}

.aside {
  width: 64px !important;
  height: 100%;
  background-color: rgb(30, 106, 186);
  margin: 0px;
}
.asideshow {
  width: 240px !important;
  height: 100%;
  background-color: rgb(30, 106, 186);
  margin: 0px;
}
.index-header{
  height: 100%;
  padding: 0px;
  border-left: 2px solid #333;
}
.index-main {
  padding: 0px;
  border-left: 2px solid #333;
}
</style>
