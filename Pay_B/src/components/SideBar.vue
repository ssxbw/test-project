<template>
    <div id="sidebar">
        <ul>
            <li class="power"><a style="text-align:left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;个人账户管理</a></li>
            <li id="UserView"><router-link id="tag1" to="/UserView">账号信息</router-link></li>
            <li id="personal_information"><router-link id="tag2" to="/personal_information">个人信息</router-link></li>
            <li id="cash_information"><router-link id="tag3" to="/cash_information">资金信息</router-link></li>
            <li id="pay_list"><router-link id="tag4" to="/pay_list">我的账单</router-link></li>
            <li class="power" v-if="this.type === '0'"><router-link style="text-align:left" to="/buyer">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;选购商品</router-link></li>
            <li class="power" v-if="this.type === '1'"><router-link style="text-align:left" to="/seller">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;管理商品</router-link></li>
            <li class="power"><router-link style="text-align:left" to="/order">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交易记录</router-link></li>
            <li class="power"><router-link style="text-align:left" to="/hotel">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在线预定</router-link></li>
        </ul>
    </div>
</template>

<style>
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
}

header {
    background-color: #307fd4;
    color: #fff;
    padding: 30px;
    text-align: center;
}

h1 {
    margin: 0;
}


#sidebar {
    background-color: #fffefe;
    width: 200px;
    height: 93.7vh;
    float: left;
    padding-top: 20px;
}

#sidebar ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
}

#sidebar li {
    margin-bottom: 10px;
    /* text-align: center; */
    background-color: rgb(252, 250, 248);
}

#sidebar a {
    display: block;
    color: #333;
    text-decoration: none;
    padding: 10px;
    padding-left: 5px;
    text-align: center;
    transition: background-color 0.3s;
}

#sidebar a:hover {
    background-color: #307fd4;

}

#sidebar .power {
    margin-bottom: 10px;
    text-align: left;
    background-color: rgb(249, 247, 244);
    font-weight: bold;
    font-size: large;
}

.container {
    max-width: 1100px;
    margin: 20px auto;
    padding: 20px;
}

table {
    width: 100%;
    border-collapse: collapse;
}

table th,
table td {
    border: 1px solid #ccc;
    padding: 8px;
    text-align: left;
}

table th {
    background-color: #333;
    color: #fff;
}
</style>

<script>
// @ is an alias to /src

import axios from "axios";

export default {
  name: 'SideBar',
  components: {

  },
  props: {
    msg: String,
  },
    data(){
      return {
          type:'',
      }
    },

  mounted() {
      this.init();
  },
    methods:{
      init(){
          axios.get('http://localhost/order/type').then(res => {
              console.log(res.data);
              if (String(res.data.code) === '0') {
                  this.$notify.error({
                      title: '错误',
                      message: res.data.msg
                  });
              } else {
                  this.type = res.data.type;
                  console.log(this.type)
              }
          })
      }
    }
}
</script>
