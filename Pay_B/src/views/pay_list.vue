<template>
  <!DOCTYPE html>
  <html>

  <head>
    <title>在线支付系统</title>

  </head>

  <body>
    <IconView></IconView>
    <header style="height:30px;background:#1e6aba;color:white">
      <h1>在线支付系统</h1>
      <button class="button"
        style="border:none; cursor: pointer; color: black; position: absolute; top: 30px; right: 30px; background-color: #1e6aba; font-size: 16px;"
        @click="logout_account">退出系统</button>
    </header>
    <SideBar></SideBar>
    <div id="content">
      <div style="height:30px;width:100%;"></div>
      <div class="user" id="user_information">
        <h2>我的账单</h2>
        <select id="year" v-bind:value="year">
          <option value="2018">2018</option>
          <option value="2019">2019</option>
          <option value="2020">2020</option>
          <option value="2021">2021</option>
          <option value="2022">2022</option>
          <option value="2023">2023</option>


        </select>年
        <select id="month" v-bind:value="month">
          <option value="0" selected>所有</option>
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
          <option value="6">6</option>
          <option value="7">7</option>
          <option value="8">8</option>
          <option value="9">9</option>
          <option value="10">10</option>
          <option value="11">11</option>
          <option value="12">12</option>
        </select>月
        &nbsp;
        <button style="border:none; cursor: pointer;" @click="user_order">查询订单</button>
        <br>
        <hr>
        <table id="table1" class="borderless-table">
          <tr>
            <th>卖家 / 类型</th>
            <th>商品名称</th>
            <th>交易金额</th>
            <th>交易状态</th>
            <th>交易时间</th>
          </tr>
          <tr v-for="record in pay_record" :key="record">
            <td>{{ record.seller }}</td>
            <td>{{ record.item }}</td>
            <td>{{ record.price }}元</td>
            <td>{{ record.state }}</td>
            <td>{{ record.time }}</td>
          </tr>
        </table>


      </div>
      <div style="height:100px;width:100%;"></div>
    </div>
    <div class="modal" :class="{ 'show-modal': isLogoutModalVisible }" style="width: 250px; height: 180px;">
      <!-- 弹框内容 -->
      <h3 class="title">是否退出系统</h3>
      <div style="display:flex; justify-content: center; align-items: center; width:98%;">
        <button style="cursor: pointer;" @click="logout">确认</button>
        <button style="cursor: pointer; margin-left: 10px;" @click="hideLogoutModal">取消</button>
      </div>
    </div>

    <div class="overlay" :class="{ 'show-overlay': isLogoutModalVisible }"></div>
  </body>

  </html>
</template>

<script>
// @ is an alias to /src
import IconView from '@/components/Icon.vue'
import SideBar from '@/components/SideBar.vue'
export default {
  name: 'pay_list',
  data() {
    return {
      isLogoutModalVisible: false,
      id: 1,
      user_id: '',
      role: '',
      mail: '',
      phone: '',
      password: '',
      balance: 0,
      email: '',
      idNumber: '',
      nickname: '',
      paymentPassword: '',
      realName: '',
      type: 'B',
      username: '',
      verificationStatus: 3,
      vipValue: 0,
      prefix: ["交易时间", "金额", "交易详情"],
      pay_record: [

      ],

      url: "http://localhost",

      API: {
        'modify_email': '/user/modify/email',
        'modify_nickname': '/user/modify/nickname',
        'modify_password': '/user/modify/password',
        'modify_paymentPassword': '/user/modify/paymentPassword',
        'modify_phone': '/user/modify/phone',
        'reservation_flight': '/user/reservation/flight',
        'reservation_hotel': '/user/reservation/hotel',
        'retrieve_password': '/user/retrieve/password',
        'user_info': '/user/info',
        'user_login': '/user/login',
        'user_logout': '/user/logout',
        'user_logoff': '/user/logoff',
        'user_order': '/user/order',
        'user_recharge': '/user/recharge',
        'user_register': '/user/register',
        'user_find': '/user/check/username'
      },
    }
  },
  methods: {
    init(){
            this.API.modify_email=this.url+this.API.modify_email;
            this.API.modify_nickname=this.url+this.API.modify_nickname;
            this.API.modify_password=this.url+this.API.modify_password;
            this.API.modify_paymentPassword=this.url+this.API.modify_paymentPassword;
            this.API.modify_phone=this.url+this.API.modify_phone;
            this.API.reservation_flight=this.url+this.API.reservation_flight;
            this.API.reservation_hotel=this.url+this.API.reservation_hotel;
            this.API.retrieve_password=this.url+this.API.retrieve_password;
            this.API.user_info=this.url+this.API.user_info;
            this.API.user_login=this.url+this.API.user_login;
            this.API.user_logout=this.url+this.API.user_logout;
            this.API.user_logoff=this.url+this.API.user_logoff;
            this.API.user_order=this.url+this.API.user_order;
            this.API.user_recharge=this.url+this.API.user_recharge;
            this.API.user_register=this.url+this.API.user_register;
            this.API.user_find=this.url+this.API.user_find;
            console.log(this.API);
        },
    user_order() {
      var year_ = document.getElementById("year");
      var month_ = document.getElementById("month");
      var year = year_.value;
      var month = month_.value;
      console.log('year ' + year + '\n');
      console.log('month ' + month + '\n');

      var url = this.API['user_order'] + this.translate({ 'year': year, 'month': month });
      console.log("url = " + url + '\n');
      fetch(url, { method: 'GET', credentials: 'include' })

        .then(response => response.json())

        .then(json_data => {
          // 处理响应数据
          if (json_data.code == '1') {
            var result = json_data.data;
            console.log(result);
            this.pay_record = result;
            for (var i = 0; i < this.pay_record.length; i++) {
              console.log('this.pay_record.length= ' + this.pay_record.length);
              console.log('i= ' + i + '\n');
              if (this.pay_record[i].state == 2) {
                this.pay_record[i].state = "未付款";
              } else if (this.pay_record[i].state == 3) {
                this.pay_record[i].state = "已付款";
              } else if (this.pay_record[i].state == 4) {
                this.pay_record[i].state = "待处理";
              } else if (this.pay_record[i].state == 5) {
                this.pay_record[i].state = "已发货";
              } else if (this.pay_record[i].state == 6) {
                this.pay_record[i].state = "运输中";
              } else if (this.pay_record[i].state == 7) {
                this.pay_record[i].state = "已付款";
              } else if (this.pay_record[i].state == 9) {
                this.pay_record[i].state = "取消订单";
              } else if (this.pay_record[i].state == 10) {
                this.pay_record[i].state = "退款";
              } else if (this.pay_record[i].state == 0) {
                this.pay_record[i].state = "订单失败";
              } else {
                this.pay_record[i].state = "订单成功";
              }

              if (this.pay_record[i].itemId % 3 == 2) {
                this.pay_record[i].seller = "航班";
                console.log("##############1");
              } else if (this.pay_record[i].itemId % 3 == 1) {
                console.log("##############2");
                this.pay_record[i].seller = "酒店";
              }

              this.pay_record[i].time = this.pay_record[i].time.substring(0, 10);
            }


          } else {
            this.pay_record = [];
          }

        })
        .catch(error => {
          // 处理错误
          console.error(error);
        });
    },
    Switch() {
      var Switch = document.getElementById("Switch");
      var password_input = document.getElementById("Password");
      if (Switch.textContent == "显示") {
        password_input.setAttribute("type", "text");
        Switch.textContent = "隐藏";
      } else {
        password_input.setAttribute("type", "password");
        Switch.textContent = "显示";
      }
    },
    logout_account() {
      this.isLogoutModalVisible = true;
    },
    hideLogoutModal() {
      this.isLogoutModalVisible = false;
    },
    logout() {
        var url = this.API['user_logout'];
        console.log("url = " + url + '\n');
        fetch(url, { method: 'GET', credentials: 'include' })
        .then(response => response.json())

        .then(json_data => {
            // 处理响应数据
            console.log(json_data);
            if (json_data.code == "1") {
                window.sessionStorage.setItem('needRefresh', 'true');
                this.$router.push("/LoginView");
            } else {
                alert("登出失败");
            }
        })
        .catch(error => {
            // 处理错误
            console.error(error);
            alert("登出失败");
        });
    },
    translate(parameter) {
      var result = "";
      var keys = Object.keys(parameter);
      if (keys != []) {
        result = "?";
      }
      for (var i = 0; i < keys.length; i++) {
        // console.log(keys[i] + "=" + parameter[keys[i]] + '\n');
        result = result + keys[i] + '=' + parameter[keys[i]];
        if (i != keys.length - 1) {
          result = result + "&";
        }
      }
      console.log(result);
      return result;
    },
    user_login(parameter) {
      var url = this.API['user_login'] + this.translate(parameter);
      console.log("url = " + url + '\n');
      fetch(url, { method: 'GET', credentials: 'include' })

        .then(response => response.json())

        .then(json_data => {
          // 处理响应数据
          console.log(json_data);

        })
        .catch(error => {
          // 处理错误
          console.error(error);
        });
    },
    user_info(parameter) {
      var url = this.API['user_info'] + this.translate(parameter);
      console.log("url = " + url + '\n');
      fetch(url, { method: 'GET', credentials: 'include' })

        .then(response => response.json())

        .then(json_data => {
          // 处理响应数据
          console.log(json_data);
          var my_data = json_data.data;
          // my_data.json();
          console.log(my_data);
          var keys = Object.keys(my_data);
          for (var i = 0; i < keys.length; i++) {
            console.log(keys[i] + "=" + my_data[keys[i]] + '\n');
            this[keys[i]] = my_data[keys[i]];
          }
          this.id = my_data.id;
          this.user_id = my_data.user_id;
          this.mail = my_data.mail;
          this.phone = my_data.phone;
          this.password = my_data.password;
          this.balance = my_data.balance;
          this.email = my_data.email;
          this.idNumber = my_data.idNumber;
          this.nickname = my_data.nickname;
          this.paymentPassword = my_data.paymentPassword;
          this.realName = my_data.realName;
          this.type = my_data.type;
          this.username = my_data.username;
          this.verificationStatus = my_data.verificationStatus;
          this.vipValue = my_data.vipValue;
          if (this.type == "B") {
            this.role = "买家";
          } else {
            this.role = "卖家";
          }
        })
        .catch(error => {
          // 处理错误
          console.error(error);
        });
    },
    modify_password(parameter) {
      //oldPassword newPassword
      var url = this.API['modify_password'] + this.translate(parameter);
      console.log("url = " + url + '\n');
      fetch(url, { method: 'GET', credentials: 'include' })

        .then(response => response.json())

        .then(json_data => {
          // 处理响应数据
          console.log(json_data);
          var my_data = json_data.data;
          // my_data.json();
          console.log(my_data);
          if (!my_data) {
            alert("密码修改未成功");
          } else {
            alert('密码' + my_data);
          }
        })
        .catch(error => {
          // 处理错误
          console.error(error);
        });
    },
    showModal() {
      this.isModalVisible = true;
    },
    hideModal() {
      this.isModalVisible = false;
    },
    pop() {
      this.showModal();
    },
    Modify_Password() {
      // this.showModal();
      var old_ = document.getElementById("old-password");
      var new_ = document.getElementById("new-password");
      var old_password = old_.value;
      var new_password = new_.value;
      console.log('old_password' + old_password + 'new_password' + new_password);
      this.modify_password({ 'oldPassword': old_password, 'newPassword': new_password });
      //oldPassword newPassword
    },
  },
  mounted: function () {
    this.init();
    const table = this.getElementById("table1");
    table.setAttribute('v-fit-columns', '');
    var nav = document.getElementById("pay_list");
    nav.setAttribute("style", "background:#307fd4;color:white;");
    nav = document.getElementById("tag4");
    nav.setAttribute("style", "color:white;");
    console.log("ok!");
  },
  watch: {
    // 配置监听watch
    // 监听firstName
  },
  components: {
    IconView,
    SideBar
  }
}
</script>

<style scoped>
.form_input {
  margin: 4px 0;
  padding-left: 25px;
  font-size: 13px;
  letter-spacing: 0.15px;
  border: none;
  outline: none;
  background-color: #dddde1;
  transition: 0.25s ease;
  border-radius: 8px;
  box-shadow: inset 1px 1px 2px #d1d9e6, inset -1px -1px 2px #f9f9f9;
}

.form_input:focus {
  box-shadow: inset 4px 4px 4px #d1d9e6, inset -4px -4px 4px #f9f9f9;
}

.title {
  justify-content: center;
  align-items: center;
  text-align: center;
  font-size: 20px;
  font-weight: 700;
  line-height: 3;
  color: #181818;
  letter-spacing: 10px;
}

.modal {
  width: 500px;
  height: 250px;
  font-size: 12px;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
  padding: 20px;
  border-radius: 5px;
  z-index: 9999;
  display: none;
}

.show-modal {
  display: block;
}

.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: none;
  z-index: 9998;
  pointer-events: none;
}

.show-overlay {
  display: block;
  pointer-events: auto;
}

#content {
  margin-left: 120px;
  background-color: #f1f0f0;
}

#user_information {
  position: relative;
  left: 8%;
  width: 1320px;
  height: 720px;
  /* border-radius: 5%;
    border: none;
    border-style: solid;
    border-color: rgb(71, 70, 70); */
  padding: 2%;
  background: #ffffff;
}

HR {
  color: dimgrey;
}

label {
  width: 80%;
  position: relative;
}

input {
  width: 80%;
  font-family: Arial, sans-serif;
  font-size: medium;
  height: 3em;
  border: none;
  color: #6a6f77;
  -web-kit-appearance: none;
  -moz-appearance: none;
  outline: 0;
  text-decoration: none;
}

input:hover {
  background-color: rgb(255, 255, 255);
  transition: background-color 0.5s;
}

input:focus {
  background-color: rgb(255, 255, 255);
}

button {
  width: 80px;
  height: 40px;
  font-size: 14px;
  color: #287cfa;
  background-color: #ffffff;
  border-radius: 5px;
  transition: all 0.5s;
}

button:hover {
  color: #033887;
  transition: background-color 0.5s;
}


table {
  width: 80%;
  position: relative;
  white-space: nowrap;
  display: inline-block;
  text-align: center;
}

/* .borderless-table {
  border-collapse: collapse;
} */
.borderless-table tr {
  text-align: center;
  width: 100%;
}

.borderless-table td {
  text-align: center;
  width: 330px;
}

.borderless-table th {
  text-align: center;
  width: 330px;

}

.borderless-table td {
  border-top: 1px solid #ddd;
}

.borderless-table td {
  border-top: none;
  border-left: none;
  border-right: none;
}

select {
  position: relative;
  width: 80px;
  height: 30px;
  padding: 5px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 4px;
  background-color: #fff;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

/* 鼠标悬停时的样式 */
select:hover {
  border-color: #999;
}

/* 获得焦点时的样式 */
select:focus {
  border-color: #555;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
}

/* 禁用状态的样式 */
select:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

/* 下拉箭头样式 */
select::-ms-expand {
  display: none;
}

select::after {
  content: '▼';
  position: absolute;
  top: 10px;
  right: 10px;
  padding: 0 5px;
  background-color: #f1f1f1;
  color: #555;
  pointer-events: none;
}

/* 禁用状态下的下拉箭头样式 */
select:disabled::after {
  color: #999;
}
</style>
