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
                <h2>个人信息</h2>
                <label>
                    <h3>姓名</h3>
                    <p class="info">{{ realName }}</p>
                    <HR>
                    </HR>
                </label>
                <label>
                    <h3>身份证号</h3>
                    <p type="text" class="info">{{ idNumber }}</p>
                    <HR>
                    </HR>
                </label>
                <label>
                    <h3>邮箱</h3>
                    <input type="text" class="info" v-bind:value="email" disabled>
                    <button style="border:none; cursor: pointer;" @click="pop2">修改邮箱</button>
                    <HR>
                    </HR>
                </label>
                <label>
                    <h3>手机号</h3>
                    <input type="text" class="info" v-bind:value="phone" disabled>
                    <button style="border:none;width:90px;cursor: pointer;" @click="pop3">修改手机号</button>
                    <HR>
                    </HR>
                </label>

                <h3 style="margin-top: 30px;">个人信息完成度</h3>
                <div class="progress-bar">
                    <div class="progress" :style="{ width: progressBarWidth }"></div>
                </div>

                <div class="modal" :class="{ 'show-modal': isModalVisible2 }">
                    <!-- 弹框内容 -->
                    <h3 style="display:flexbox" class="title">修改邮箱</h3>
                    <label style="margin-right: 10px; font-size: medium;" for="old-password">&nbsp;&nbsp;&nbsp;原邮箱</label>
                    <input class="form_input" style="width:350px;" type="text" id="email1" v-model="email" disabled>

                    <br>
                    <label style="margin-right: 10px; font-size: medium;" for="new-password">&nbsp;&nbsp;&nbsp;新邮箱</label>
                    <input class="form_input" style="width:350px;" type="text" id="newEmail" v-model="newEmail">
                    <div style="display:flex;justify-content: center; align-items: center;width:96%;">

                        <button style="border:none; cursor: pointer;" @click="Modify_Email">确认</button>
                        <button style="border:none; cursor: pointer;" @click="hideModal2">关闭</button>
                    </div>
                </div>
                <div class="overlay" :class="{ 'show-overlay': isModalVisible2 }"></div>

                <div class="modal" :class="{ 'show-modal': isLogoutModalVisible }" style="width: 250px; height: 180px;">
                    <!-- 弹框内容 -->
                    <h3 class="title">是否退出系统</h3>
                    <div style="display:flex; justify-content: center; align-items: center; width:98%;">
                        <button style="cursor: pointer;" @click="logout">确认</button>
                        <button style="cursor: pointer; margin-left: 10px;" @click="hideLogoutModal">取消</button>
                    </div>
                </div>

                <div class="overlay" :class="{ 'show-overlay': isLogoutModalVisible }"></div>

                <div class="modal" :class="{ 'show-modal': isModalVisible3 }">
                    <!-- 弹框内容 -->
                    <h3 style="display:flexbox" class="title">修改手机号</h3>
                    <label style="margin-right: 10px; font-size: medium;" for="old-password">&nbsp;&nbsp;&nbsp;原号码</label>
                    <input class="form_input" style="width:350px;" type="text" id="phone1" v-model="phone" disabled>

                    <br>
                    <label style="margin-right: 10px; font-size: medium;" for="new-password">&nbsp;&nbsp;&nbsp;新号码</label>
                    <input class="form_input" style="width:350px;" type="text" id="newPhone" v-model="newPhone">
                    <div style="display:flex;justify-content: center; align-items: center;width:96%;">
                        <button style="border:none; cursor: pointer;" @click="Modify_Phone">确认</button>
                        <button style="border:none; cursor: pointer;" @click="hideModal3">关闭</button>
                    </div>
                </div>
                <div class="overlay" :class="{ 'show-overlay': isModalVisible3 }"></div>



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
import CryptoJS from 'crypto-js';
export default {
    name: 'personal_information',
    data() {
        return {
            isLogoutModalVisible: false,
            id: 1,
            user_id: '',
            role: '买方',
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
            verificationStatus: 2,
            vipValue: 0,

            isModalVisible2: false,
            isModalVisible3: false,


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
    computed: {
        progressBarWidth() {
            if (this.verificationStatus === 1) {
                return '33.3%';
            } else if (this.verificationStatus === 2) {
                return '66.6%';
            } else if (this.verificationStatus === 3) {
                return '100%';
            } else {
                return '0%';
            }
        }
    },
    mounted: function () {
        this.init();
        var nav = document.getElementById("personal_information");
        nav.setAttribute("style", "background:#307fd4;color:white;");
        nav = document.getElementById("tag2");
        nav.setAttribute("style", "color:white;");
        console.log(nav);

        this.user_login({ username: this.username, password: this.encrypt(this.password) });
        this.user_info({});
        this.$forceUpdate();
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
        }, user_login(parameter) {
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
        modify_paymentPassword(parameter) {
            /*String oldPaymentPassword, String newPaymentPassword*/
            var url = this.API['modify_paymentPassword'] + this.translate(parameter);
            console.log("url = " + url + '\n');
            fetch(url, { method: 'GET', credentials: 'include' })

                .then(response => response.json())

                .then(json_data => {
                    // 处理响应数据
                    console.log(json_data);
                    var my_data = json_data.data;

                    var keys = Object.keys(my_data);
                    for (var i = 0; i < keys.length; i++) {
                        // console.log(keys[i] + "=" + parameter[keys[i]] + '\n');
                        this.data[keys[i]] = my_data[keys[i]];
                    }
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
        modify_email(parameter) {
            //oldPassword newPassword
            var url = this.API['modify_email'] + this.translate(parameter);
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
                        alert("邮箱修改未成功");
                    } else {
                        alert('邮箱' + my_data);
                    }
                })
                .catch(error => {
                    // 处理错误
                    console.error(error);
                });
        },
        Modify_Phone() {
            // var old_ = document.getElementById("old-password3");
            var new_ = document.getElementById("newPhone");
            // var old_password = old_.value;
            var newPhone = new_.value;
            console.log('newPhone' + newPhone);
            this.modify_phone({ 'phone': newPhone });
        },
        modify_phone(parameter) {
            var url = this.API['modify_phone'] + this.translate(parameter);
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
                        alert("手机号修改未成功");
                    } else {
                        alert('手机号' + my_data);
                    }
                })
                .catch(error => {
                    // 处理错误
                    console.error(error);
                });
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
        pop2() {
            this.showModal2();
        },
        pop3() {
            this.showModal3();
        },
        Modify_Email() {
            // var old_ = document.getElementById("old-password2");
            var new_ = document.getElementById("newEmail");
            // var old_password = old_.value;
            var newEmail = new_.value;
            console.log('newEmail' + newEmail);
            this.modify_email({ 'email': newEmail });
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
            // 返回解密后的明文
            return decrypted.toString(CryptoJS.enc.Utf8);
        }
    },
    components: {
        IconView,
        SideBar
    }
}
</script>

<style scoped>
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

input:disabled {
    background-color: rgb(255, 255, 255);
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

.progress-bar {
    margin-left: 3.5%;
    width: 300px;
    height: 22px;
    border: 1px solid #ccc;
}

.progress {
    height: 100%;
    background-color: #307fd4;
}

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


/* button:focus{
        transform: rotate(360deg);
    } */
</style>
    
    