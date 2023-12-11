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
                <h2>资金信息</h2>
                <label>
                    <h3>姓名</h3>
                    <p class="info">{{ realName }}</p>
                    <HR>
                    </HR>
                </label>
                <label>
                    <h3>账户余额</h3>
                    <input id="text" type="text" class="info" v-bind:value="balance" disabled>
                    <!-- <p type="text" class="info">{{ balance }}元</p> -->
                    <button style="border:none; cursor: pointer;" @click="pop1">充值</button>
                    <HR>
                    </HR>
                </label>

                <label>
                    <h3>交易密码</h3>
                    <input id="Password" type="password" class="info" v-bind:value="paymentPassword" disabled>
                    <button style="border:none; cursor: pointer;" @click="pop2">修改密码</button>
                    <!-- <button id="Switch" @click="Switch"
                        style="width: 40px;height: 30px; margin:3px;  border:none;">显示</button> -->
                    <HR>
                    </HR>
                </label>

                <div class="modal" :class="{ 'show-modal': isModalVisible1 }" style="height: 350px;">
                    <!-- 弹框内容 -->
                    <h3 style="display:flexbox" class="title">&nbsp;账户充值</h3>
                    <label style="margin-right: 10px; font-size: medium;">&nbsp;&nbsp;账户余额</label>
                    <input class="form_input" style="width:350px;" type="contents" id="old-password1"
                        v-bind:value="balance" disabled>

                    <br>
                    <label style="margin-right: 10px; font-size: medium;" for="new-password">&nbsp;&nbsp;输入卡号</label>
                    <input class="form_input" style="width:350px;" type="text" id="number" v-model="number">
                    <br>
                    <label style="margin-right: 10px; font-size: medium;" for="new-password">&nbsp;&nbsp;输入密码</label>
                    <input class="form_input" style="width:350px;" type="password" id="password" v-model="password1">
                    <br>
                    <label style="margin-right: 10px; font-size: medium;" for="new-password">&nbsp;&nbsp;充值金额</label>
                    <input class="form_input" style="width:350px;" type="text" id="delta" v-model="delta">
                    <br>
                    <div style="display:flex;justify-content: center; align-items: center;width:100%;">
                        &nbsp;&nbsp;
                        <button style="border:none; cursor: pointer;" @click="User_Recharge">确认</button>
                        <button style="border:none; cursor: pointer;" @click="hideModal1">关闭</button>
                    </div>
                </div>
                <div class="overlay" :class="{ 'show-overlay': isModalVisible1 }"></div>

                <div class="modal" :class="{ 'show-modal': isLogoutModalVisible }" style="width: 250px; height: 180px;">
                        <!-- 弹框内容 -->
                        <h3 class="title">是否退出系统</h3>
                        <div style="display:flex; justify-content: center; align-items: center; width:98%;">
                            <button style="cursor: pointer;" @click="logout">确认</button>
                            <button style="cursor: pointer; margin-left: 10px;" @click="hideLogoutModal">取消</button>
                        </div>
                    </div>

                    <div class="overlay" :class="{ 'show-overlay': isLogoutModalVisible }"></div>

                <div class="modal" :class="{ 'show-modal': isModalVisible2 }">
                    <!-- 弹框内容 -->
                    <h3 style="display:flexbox" class="title">&nbsp;&nbsp;修改交易密码</h3>
                    <label for="old-password" style="margin-right: 10px; font-size: medium;">旧交易密码</label>
                    <input class="form_input" style="width:350px;" type="text" id="old_paymentPassword"
                        v-model="old_paymentPassword">

                    <br>
                    <label for="new-password" style="margin-right: 10px; font-size: medium;">新交易密码</label>

                    <input class="form_input" style="width:350px;" type="text" id="new_paymentPassword"
                        v-model="new_paymentPassword">
                    <br>
                    <div style="display:flex;justify-content: center; align-items: center;width:100%;">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <button style="border:none; cursor: pointer;" @click="Modify_PaymentPassword">确认</button>
                        <button style="border:none; cursor: pointer;" @click="hideModal2">关闭</button>
                    </div>
                </div>
                <div class="overlay" :class="{ 'show-overlay': isModalVisible2 }"></div>
                <div style="width:850px;position:relative;left:10%; ">
                    <div id="myChart1" style="width: 350px;height: 350px; display:inline-block"></div>
                    <div id="myChart2" style="width: 350px;height: 350px; display:inline-block"></div>
                </div>
            </div>
            <div style="height:100px;width:100%;"></div>
        </div>

    </body>

    </html>
</template>

<script>
// @ is an alias to /src
import IconView from '@/components/Icon.vue'
import SideBar from '@/components/SideBar.vue'
// import router from '@/router';
import * as echarts from "echarts";
export default {
    name: 'cash_information',
    data() {
        return {
            isLogoutModalVisible: false,
            id: 0,
            user_id: '',
            number: '',
            password1: '',
            role: '买方',
            mail: '',
            phone: '',
            password: '',
            balance: 0,
            email: '',
            idNumber: '333',
            nickname: '##########',
            paymentPassword: '',
            realName: '',
            type: 'B',
            username: '',
            verificationStatus: 3,
            vipValue: 0,
            message: "",
            order_statistics_list: [1000, 1000, 1000, 1000, 1000, 1000],

            isModalVisible1: false,
            isModalVisible2: false,

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
                'user_find': '/user/check/username',
                'order_statistics': "/user/order/statistics"
            },
            chart: null,
            options: [{},{}],
            pie_option: {
                legend: {
                    // 图例
                    data: ["江苏", "浙江", "山东", "广东", "湖南"],
                    right: "10%",
                    top: "30%",
                    orient: "vertical"
                },
                title: {
                    // 设置饼图标题，位置设为顶部居中
                    text: "消费类型分布",
                    top: "0%",
                    left: "center"
                },
                series: [
                    {
                        type: "pie",
                        label: {
                            show: true,
                            formatter: "{b}:{c}" // b代表名称，c代表对应值，d代表百分比
                        },
                        data: [
                            {value: 1720,name: "交通"},
                            {value: 4314,name: "餐饮"},
                            {value: 1546,name: "住宿"},
                        ]
                    }
                ]
            },


        }
    },
    mounted: function () {
        this.init();
        var nav = document.getElementById("cash_information");
        nav.setAttribute("style", "background:#307fd4;color:white;");
        nav = document.getElementById("tag3");
        nav.setAttribute("style", "color:white;");
        console.log("ok!");
        // this.modify_paymentPassword({"oldPaymentPassword":"","newPaymentPassword":"saf54s4f"});

        this.user_login({ username: this.username, password: this.password });
        this.user_info({});
        this.$forceUpdate();

        this.initOptions();
        // this.pie_chart();
        this.order_statistics({ year: 2023 });
        this.order_statistics({ year: 2022 });
        // this.initCharts();
    },
    methods: {
        init() {
            this.API.modify_email = this.url + this.API.modify_email;
            this.API.modify_nickname = this.url + this.API.modify_nickname;
            this.API.modify_password = this.url + this.API.modify_password;
            this.API.modify_paymentPassword = this.url + this.API.modify_paymentPassword;
            this.API.modify_phone = this.url + this.API.modify_phone;
            this.API.reservation_flight = this.url + this.API.reservation_flight;
            this.API.reservation_hotel = this.url + this.API.reservation_hotel;
            this.API.retrieve_password = this.url + this.API.retrieve_password;
            this.API.user_info = this.url + this.API.user_info;
            this.API.user_login = this.url + this.API.user_login;
            this.API.user_logout = this.url + this.API.user_logout;
            this.API.user_logoff = this.url + this.API.user_logoff;
            this.API.user_order = this.url + this.API.user_order;
            this.API.user_recharge = this.url + this.API.user_recharge;
            this.API.user_register = this.url + this.API.user_register;
            this.API.user_find = this.url + this.API.user_find;
            this.API.order_statistics = this.url + this.API.order_statistics;
            console.log(this.API);
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
        }, translate(parameter) {
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
                    this.$forceUpdate();
                })
                .catch(error => {
                    // 处理错误
                    console.error(error);
                });
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
        logout_account() {
            this.isLogoutModalVisible = true;
        },
        hideLogoutModal() {
            this.isLogoutModalVisible = false;
        },
        modify_paymentPassword(parameter) {
            /*String oldPaymentPassword, String newPaymentPassword*/
            var url = this.API['modify_paymentPassword'] + this.translate(parameter);
            console.log("url = " + url + '\n');
            fetch(url, { method: 'GET', credentials: 'include' })

                .then(response => response.json())

                .then(json_data => {
                    // 处理响应数据
                    console.log(json_data);
                    // var my_data = json_data.data;
                    if (json_data.code == '1') {
                        alert('修改成功');
                        
                    }else{
                        alert("修改失败");
                    }
                    
                })
                .catch(error => {
                    // 处理错误
                    console.error(error);
                });
        },
        Modify_PaymentPassword() {
            var old_ = document.getElementById("old_paymentPassword");
            var new_ = document.getElementById("new_paymentPassword");
            var old_paymentpassword = old_.value;
            var new_paymentPassword = new_.value;
            console.log('old_paymentpassword' + old_paymentpassword + 'new_paymentPassword' + new_paymentPassword);
            this.modify_paymentPassword({ 'oldPaymentPassword': old_paymentpassword, 'newPaymentPassword': new_paymentPassword });
        },
        user_recharge(parameter) {
            //Double delta 
            var url = this.API['user_recharge'] + this.translate(parameter);
            console.log("url = " + url + '\n');
            fetch(url, { method: 'GET', credentials: 'include' })

                .then(response => response.json())

                .then(json_data => {
                    // 处理响应数据
                    console.log(json_data);
                    // var my_data = json_data.data;
                    if (json_data.code == '1') {
                        
                        console.log('充值成功');
                        alert('充值成功');
                        this.user_info();
                    }else{
                        alert(json_data.msg);
                    }
                    
                    

                })
                .catch(error => {
                    // 处理错误
                    console.error(error);
                });
        },
        order_statistics(parameter) {
            //Double delta 
            var url = this.API['order_statistics'] + this.translate(parameter);
            console.log("url = " + url + '\n');
            fetch(url, { method: 'GET', credentials: 'include' })

                .then(response => response.json())

                .then(json_data => {
                    // 处理响应数据
                    console.log(json_data);
                    // var my_data = json_data.data;
                    if (json_data.code == 1) {
                        if(parameter.year==2023){
                            this.options[1].series[0].data = json_data.data;
                            this.initCharts(2);
                        }else if(parameter.year==2022){
                            this.options[0].series[0].data = json_data.data;
                            this.initCharts(1);
                        }
                    }
                    console.log("this.options=\n");
                    console.log(this.options);
                    console.log(json_data.data);
                    // this.initCharts();

                })
                .catch(error => {
                    // 处理错误
                    console.error(error);
                });
        },
        User_Recharge() {
            // var old_ = document.getElementById("old-password1");
            var new_ = document.getElementById("delta");
            // var old_password = old_.value;
            var delta = new_.value;
            console.log('delta' + delta);
            this.user_recharge({ 'delta': delta,'number': this.number,'password': this.password1 });
        },
        showModal1() {
            this.isModalVisible1 = true;
        },
        hideModal1() {
            this.isModalVisible1 = false;
            location.reload();
        },
        showModal2() {
            this.isModalVisible2 = true;
        },
        hideModal2() {
            this.isModalVisible2 = false;
            location.reload();
        },
        showModal3() {
            this.isModalVisible3 = true;
        },
        hideModal3() {
            this.isModalVisible3 = false;
            location.reload();
        },
        pop1() {
            this.showModal1();
        },
        pop2() {
            this.showModal2();
        },
        pop3() {
            this.showModal3();
        },
        Modify_Nickname() {
            // this.showModal();
            var old_ = document.getElementById("old-password1");
            var new_ = document.getElementById("newNickname");
            var old_password = old_.value;
            var newNickname = new_.value;
            console.log('old_password' + old_password + 'newEmail' + newNickname);
            this.modify_nickname({ 'nickname': newNickname });
            //oldPassword newPassword

        }, initOptions() {
            this.options = [
            {
                title: {
                    show: true, //显示策略，默认值true,可选为：true（显示） | false（隐藏）
                    text: '2022年每月支出', //主标题文本
                    x: 'center',
                },
                xAxis: {
                    type: 'category',
                    data: ['1', '2', '3', '4', '5', '6','7','8','9','10','11','12']
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: [820, 932, 901, 934, 1290, 1086, 1320, 450, 782, 452, 891, 156],
                    type: 'line',
                    label: {
                            show: true,
                            formatter: "{c}" // b代表名称，c代表对应值，d代表百分比
                        }
                }]
            },
            {
                title: {
                    show: true, //显示策略，默认值true,可选为：true（显示） | false（隐藏）
                    text: '2023年每月支出', //主标题文本
                    x: 'center',
                },
                xAxis: {
                    type: 'category',
                    data: ['1', '2', '3', '4', '5', '6']
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: [820, 932, 901, 934, 1290, 1330, 1320, 0, 0, 0, 0, 0],
                    type: 'line',
                    label: {
                            show: true,
                            formatter: "{c}" // b代表名称，c代表对应值，d代表百分比
                        }
                }]
            },
        ]
        },
        initCharts(x) {
            if(x==1){
                this.chart = echarts.init(document.getElementById('myChart1'));
                this.chart.setOption(this.options[0]);
            }else if(x==2){
                this.chart = echarts.init(document.getElementById('myChart2'));
                this.chart.setOption(this.options[1]);
            }
            
            // this.chart=setOption(this.options);
        },
        pie_chart() {
            this.chart = echarts.init(document.getElementById('pie_chart'));
            this.chart.setOption(this.pie_option);
        }

    },
    components: {
        IconView,
        SideBar,
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

input:disabled {
    background-color: rgb(255, 255, 255);
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
    height: 750px;
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

/* button:focus{
        transform: rotate(360deg);
    } */
</style>
    
    