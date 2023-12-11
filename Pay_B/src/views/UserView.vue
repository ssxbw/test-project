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
                <h2>账号信息</h2>
                <label>
                    <h3>用户名</h3>
                    <input type="contents" class="info" v-bind:value="username" disabled>
                    <button class="button" style="border:none; cursor: pointer; color: red"
                        @click="logoff_account">注销账号</button>


                    <HR>
                    </HR>

                </label>

                <label>
                    <h3>昵称</h3>
                    <input type="text" class="info" v-bind:value="nickname" disabled>
                    <button style="border:none; cursor: pointer;" @click="pop1">修改昵称</button>
                    <HR>
                    </HR>
                </label>

                <label>
                    <h3>角色</h3>
                    <p type="text" class="info">{{ role }}</p>
                    <HR>
                    </HR>
                </label>

                <label>
                    <h3>VIP积分</h3>
                    <p type="text" class="info">{{ vipValue }}</p>
                    <HR>
                    </HR>
                </label>

                <label>
                    <h3>密码</h3>
                    <input id="Password" type="password" class="info" v-bind:value="password" disabled>
                    <button style="border:none; cursor: pointer;" @click="pop">修改密码</button>


                    <div class="modal" :class="{ 'show-modal': isModalVisible }">
                        <!-- 弹框内容 -->
                        <h3 style="display:flexbox" class="title">修改密码</h3>
                        <label style="margin-right: 10px; font-size: medium;"
                            for="old-password">&nbsp;&nbsp;&nbsp;&nbsp;原密码</label>
                        <input class="form_input" style="width:350px;" type="password" id="old-password"
                            v-model="oldPassword">

                        <br>
                        <label style="margin-right: 10px; font-size: medium;"
                            for="new-password">&nbsp;&nbsp;&nbsp;&nbsp;新密码</label>
                        <input class="form_input" style="width:350px;" type="password" id="new-password"
                            v-model="newPassword">
                        <div style="display:flex;justify-content: center; align-items: center;width:100%;">
                            <button style="border:none; cursor: pointer;" @click="Modify_Password">确认</button>
                            <button style="border:none; cursor: pointer;" @click="hideModal">关闭</button>
                        </div>

                    </div>
                    <div class="overlay" :class="{ 'show-overlay': isModalVisible }"></div>


                    <div class="modal" :class="{ 'show-modal': isLogoutModalVisible }" style="width: 250px; height: 180px;">
                        <!-- 弹框内容 -->
                        <h3 class="title">是否退出系统</h3>
                        <div style="display:flex; justify-content: center; align-items: center; width:98%;">
                            <button style="cursor: pointer;" @click="logout">确认</button>
                            <button style="cursor: pointer; margin-left: 10px;" @click="hideLogoutModal">取消</button>
                        </div>
                    </div>

                    <div class="overlay" :class="{ 'show-overlay': isLogoutModalVisible }">
                    </div>

                    <div class="overlay" :class="{ 'show-overlay': isLogoutModalVisible }">
                    </div>

                    <div class="modal" :class="{ 'show-modal': isLogoffModalVisible }" style="width: 350px; height: 200px;">
                        <!-- 弹框内容 -->
                        <h3 class="title">是否注销账号</h3>
                        <div style="display:flex; justify-content: center; align-items: center; width:98%;">
                            <button style="cursor: pointer;" @click="logoff">确认</button>
                            <button style="cursor: pointer; margin-left: 10px;" @click="hideLogoffModal">取消</button>
                        </div>

                    </div>

                    <div class="modal" :class="{ 'show-modal': isModalVisible1 }">
                        <!-- 弹框内容 -->

                        <h3 style="display:flexbox" class="title">修改昵称</h3>
                        <label style="margin-right: 10px; font-size: medium;"
                            for="old-password">&nbsp;&nbsp;&nbsp;原昵称</label>
                        <input class="form_input" style="width:350px;" type="text" id="nickname1" v-model="nickname"
                            disabled>
                        <br>
                        <label style="margin-right: 10px; font-size: medium;"
                            for="new-password">&nbsp;&nbsp;&nbsp;新昵称</label>

                        <input class="form_input" style="width:350px;" type="text" id="newNickname" v-model="newNickname">
                        <br>
                        <div style="display:flex;justify-content: center; align-items: center;width:96%;">
                            <button style="border:none; cursor: pointer;" @click="Modify_Nickname">确认</button>
                            <button style="border:none; cursor: pointer;" @click="hideModal1">关闭</button>
                        </div>
                    </div>
                    <div class="overlay" :class="{ 'show-overlay': isModalVisible1 }"></div>

                    <div class="overlay" :class="{ 'show-overlay': isLogoffModalVisible }">
                    </div>
                    <!-- <button id="Switch" @click="Switch"
                        style="width: 40px;height: 30px;border:none; margin:3px;">显示</button> -->
                    <HR>
                    </HR>
                </label>


            </div>
            <div style="height:100px;width:100%;"></div>
        </div>

    </body>

    </html>
</template>

<script>
// @ is an alias to /src
import CryptoJS from 'crypto-js';
import IconView from '@/components/Icon.vue';
import SideBar from '@/components/SideBar.vue';
// import axios from 'axios';
export default {
    name: 'UserView',
    components: {
        IconView,
        SideBar
    },
    data() {
        return {
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

            isModalVisible: false,
            isModalVisible1: false,
            isLogoffModalVisible: false,
            isLogoutModalVisible: false,
            showErrorMsg: false,

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
            console.log(this.API);
          console.log("hufdsahfi-------------------u")
          console.log(localStorage.getItem("username"))
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
        modify_nickname(parameter) {
            //oldPassword newPassword
            var url = this.API['modify_nickname'] + this.translate(parameter);
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
                        alert("昵称修改未成功");
                    } else {
                        alert('昵称' + my_data);
                    }
                    this.user_info();
                    this.$forceUpdate();
                })
                .catch(error => {
                    // 处理错误
                    console.error(error);
                });
        },
        pop1() {
            this.showModal1();
        },
        Modify_Nickname() {
            // this.showModal();
            // var old_ = document.getElementById("old-password1");
            var new_ = document.getElementById("newNickname");
            // var old_password = old_.value;
            var newNickname = new_.value;
            console.log('newEmail' + newNickname);
            this.modify_nickname({ 'nickname': newNickname });
            //oldPassword newPassword
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
                    this.username = my_data.username;
                    this.mail = my_data.mail;
                    this.phone = my_data.phone;
                    this.password = this.decrypt(my_data.password, my_data.username);
                    this.balance = my_data.balance;
                    this.email = my_data.email;
                    this.idNumber = my_data.idNumber;
                    this.nickname = my_data.nickname;
                    this.paymentPassword = my_data.paymentPassword;
                    this.realName = my_data.realName;
                    this.type = my_data.type;
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
            location.reload();
        },
        showModal1() {
            this.isModalVisible1 = true;
        },
        hideModal1() {
            this.isModalVisible1 = false;
            location.reload();
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
            console.log("##########\n");
            console.log('old_password' + old_password + 'new_password' + new_password);
            this.modify_password({ 'oldPassword': this.encrypt(old_password, this.username), 'newPassword': this.encrypt(new_password, this.username) });
            //oldPassword newPassword

        },
        logoff() {
            var url = this.API['user_logoff'];
            console.log("url = " + url + '\n');
            fetch(url, { method: 'GET', credentials: 'include' })
                .then(response => response.json())
                .then(json_data => {
                    // 处理响应数据
                    console.log(json_data);
                    if (json_data.code == "0") {
                        alert("注销失败，请检查是否有未完成的业务");
                    } else {
                        alert('注销成功');
                        this.logout();
                    }

                })
                .catch(error => {
                    // 处理错误
                    console.error(error);
                    alert("注销失败，请检查是否有未完成的业务");
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
        logoff_account() {
            this.isLogoffModalVisible = true;
        },
        hideLogoffModal() {
            this.isLogoffModalVisible = false;
        },
        logout_account() {
            this.isLogoutModalVisible = true;
        },
        hideLogoutModal() {
            this.isLogoutModalVisible = false;
        },
        encrypt(message, key) {
            // 将密钥转换为字节数组
            const keyBytes = CryptoJS.enc.Utf8.parse(key);
            // 对明文进行加密
            const encrypted = CryptoJS.AES.encrypt(message, keyBytes, {
                mode: CryptoJS.mode.ECB,
                padding: CryptoJS.pad.Pkcs7
            });
            // 返回加密后的密文
            return encrypted.toString();
        },
        decrypt(ciphertext, key) {
            // 将密钥转换为字节数组
            const keyBytes = CryptoJS.enc.Utf8.parse(key);
            // 对密文进行解密
            const decrypted = CryptoJS.AES.decrypt(ciphertext, keyBytes, {
                mode: CryptoJS.mode.ECB,
                padding: CryptoJS.pad.Pkcs7
            });
            console.log(decrypted.toString(CryptoJS.enc.Utf8));
            // 返回解密后的明文
            return decrypted.toString(CryptoJS.enc.Utf8);
        }
    },
    mounted: function () {
        this.init();
        var nav = document.getElementById("UserView");
        nav.setAttribute("style", "background:#307fd4;color:white;");
        nav = document.getElementById("tag1");
        nav.setAttribute("style", "color:white;");
        console.log("ok!");

        this.user_login({ username: this.username, password: this.password });
        this.user_info({});
        this.$forceUpdate();
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

input:disabled {
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

