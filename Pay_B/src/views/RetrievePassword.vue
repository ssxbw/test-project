<template>
    <!DOCTYPE html>
    <html lang="en">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>在线支付系统</title>
    </head>

    <body>
    <img src="../style/background.png" style="z-index:-1;position:absolute;width:100%;height:100%;">
    <div class="shell">
        <div class="container a-container" id="a-container" ref="aContainer">
            <form action="" method="" class="form" id="a-form">
                <h2 class="form_title title">找回密码</h2>
                <div class="step">
                    <div class="circle active" id="circle1">1</div>
                    <div class="line"></div>
                    <div class="circle" id="circle2">2</div>
                </div>

                <div class="step">
                    <div class="tips">身份认证</div>
                    <div class="tips">找回密码</div>
                </div>

                <div class="form-group" id="input1">
                        <span class="fa-stack fa-lg">
                            <i class="fas fa-square fa-stack-2x"></i>
                            <i class="fas fa-user fa-stack-1x fa-inverse"></i>
                        </span>
                    <input type="text" class="form_input" placeholder="输入用户名" v-model="username">
                </div>
                <div class="form-group" id="input2">
                        <span class="fa-stack fa-lg">
                            <i class="fas fa-square fa-stack-2x"></i>
                            <i class="fas fa-user-tie fa-stack-1x fa-inverse"></i>
                        </span>
                    <input type="text" class="form_input" placeholder="输入真实姓名" v-model="realName">
                </div>
                <div class="form-group" id="input3">
                        <span class="fa-stack fa-lg">
                            <i class="fas fa-square fa-stack-2x"></i>
                            <i class="fas fa-id-card fa-stack-1x fa-inverse"></i>
                        </span>
                    <input type="text" class="form_input" placeholder="输入身份证号" v-model="idNumber">
                </div>
                <div class="form-group" id="input4">
                        <span class="fa-stack fa-lg">
                            <i class="fas fa-square fa-stack-2x"></i>
                            <i class="fas fa-smile fa-stack-1x fa-inverse"></i>
                        </span>
                    <input type="text" class="form_input" placeholder="输入昵称" v-model="nickname">
                </div>
                <div class="error left-align" v-show="showRetrieveError">{{ retrieveErrorMessage }}</div>
                <button class="button switch-btn submit" id="nextBtn1" @click="userCheck">下一步</button>
                <div class="successInfo" id="successInfo">
                    <p>您的密码是:</p>
                    <p>&nbsp;&nbsp;&nbsp;&nbsp;{{ this.decrypt(this.oldPassword, this.username) }}</p>
                    <p>请妥善保存密码！</p>
                </div>
                <button class="button1 submit" id="confirmBtn" @click="complete">确认</button>
            </form>
        </div>

        <div class="switch" id="switch-cnt" ref="switchCnt">
            <div class="switch_circle" ref="switchCircle1"></div>
            <div class="switch_circle switch_circle-t" ref="switchCircle2"></div>
            <div class="switch_container" id="switch-c1" ref="switchC1">
                <h2 class="switch_title title" style="letter-spacing: 0;">在线支付系统</h2>
                <p class="switch_description description">点击这里返回登录界面</p>
                <button class="switch_button button switch-btn" id="returnBtn" @click="returnLogin">返回</button>
            </div>
        </div>
    </div>
    </body>

    </html>
</template>

<style scoped>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    /* 字体无法选中 */
    user-select: none;
}

body {
    width: 100%;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 12px;
    color: #a0a5a8;
}

.step {
    display: flex;
    align-items: center;
    margin-bottom: 0px;
}

.circle {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-left: 20px;
    margin-right: 20px;
    margin-bottom: 0px;
    width: 30px;
    height: 30px;
    border-radius: 50%;
    background-color: #ccc;
    color: #fff;
    font-size: 18px;
    font-weight: bold;
}

.circle.active {
    background-color: #4B70E2;
}

.line {
    border: none;
    border-top: 3px dashed #ccc;
    margin-bottom: 0px;
    width: 120px;
}

.step-content {
    width: 400px;
    padding: 30px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    text-align: center;
}

.tips {
    font-size: 12px;
    color: #605f5f;
    margin-left: 70px;
    margin-right: 70px;
    margin-top: 5px;
    margin-bottom: 25px;
}

.shell {
    position: relative;
    width: 1000px;
    min-width: 1000px;
    min-height: 600px;
    height: 600px;
    padding: 25px;
    background-color: #ecf0f3;
    box-shadow: 10px 10px 10px #d1d9e6, -10px -10px 10px #f9f9f9;
    border-radius: 12px;
    overflow: hidden;
}

.left-align {
    align-self: flex-start;
    margin-left: 120px;
}

/* 设置响应式 */
@media (max-width: 1200px) {
    .shell {
        transform: scale(0.7);
    }
}

@media (max-width: 1000px) {
    .shell {
        transform: scale(0.6);
    }
}

@media (max-width: 800px) {
    .shell {
        transform: scale(0.5);
    }
}

@media (max-width: 600px) {
    .shell {
        transform: scale(0.4);
    }
}

.fa-stack {
    width: 40px;
    height: 40px;
}

.fa-stack .fa-square {
    color: #333;
}

.container {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    top: 0;
    width: 600px;
    height: 100%;
    padding: 25px;
    background-color: #ecf0f3;
    transition: 1.25s;
}

.form {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    width: 100%;
    height: 100%;
}

.form_input {
    width: 350px;
    height: 35px;
    margin: 4px 0;
    padding-left: 25px;
    font-size: 13px;
    letter-spacing: 0.15px;
    border: none;
    outline: none;
    background-color: #ecf0f3;
    transition: 0.25s ease;
    border-radius: 8px;
    box-shadow: inset 2px 2px 4px #d1d9e6, inset -2px -2px 4px #f9f9f9;
}

.form_input:focus {
    box-shadow: inset 4px 4px 4px #d1d9e6, inset -4px -4px 4px #f9f9f9;
}

.form_span {
    margin-top: 0px;
    margin-bottom: 10px;
    display: inline-block;
}

.form_div {
    text-align: left;
    justify-content: flex-start;
}

.form_link {
    color: #181818;
    font-size: 15px;
    margin-top: 25px;
    border-bottom: 1px solid #a0a5a8;
    line-height: 2;
}

.error{
    color: red;
    text-align: left;
    margin-top: 5px;
    margin-bottom: 5px;
}

.title {
    font-size: 34px;
    font-weight: 700;
    line-height: 3;
    color: #181818;
    letter-spacing: 10px;
}

.successInfo {
    font-size: 18px;
    font-weight: 700;
    line-height: 3;
    color: #181818;
    letter-spacing: 5px;
    display: none;
}

.description {
    font-size: 14px;
    letter-spacing: 0.25px;
    text-align: center;
    line-height: 1.6;
}

.button {
    width: 180px;
    height: 50px;
    border-radius: 25px;
    margin-top: 30px;
    font-weight: 700;
    font-size: 14px;
    letter-spacing: 1.15px;
    background-color: #4B70E2;
    color: #f9f9f9;
    box-shadow: 8px 8px 16px #d1d9e6, -8px -8px 16px #f9f9f9;
    border: none;
    outline: none;
}

.button1 {
    width: 180px;
    height: 50px;
    border-radius: 25px;
    margin-top: 30px;
    font-weight: 700;
    font-size: 14px;
    letter-spacing: 1.15px;
    background-color: #4B70E2;
    color: #f9f9f9;
    box-shadow: 8px 8px 16px #d1d9e6, -8px -8px 16px #f9f9f9;
    border: none;
    outline: none;
    display: none;
}

.a-container {
    z-index: 100;
    left: calc(100% - 600px);
}

.b-container {
    left: calc(100% - 600px);
    z-index: 0;
}

form label1 {
    display: inline-block;
    margin-right: 55px;
    margin-left: 15px;
    padding: 10px 0px 10px 10px;
    border-radius: 5px;
    font-size: 13px;
    letter-spacing: 0.15px;
}

form label2 {
    display: inline-block;
    margin-right: 25px;
    padding: 10px 0px 10px 10px;
    border-radius: 5px;
    font-size: 13px;
    letter-spacing: 0.15px;
}

.switch {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    top: 0;
    left: 0;
    height: 100%;
    width: 400px;
    padding: 50px;
    z-index: 200;
    transition: 1.25s;
    background-color: #ecf0f3;
    overflow: hidden;
    box-shadow: 4px 4px 10px #d1d9e6, -4px -4px 10px #d1d9e6;
}

.switch_circle {
    position: absolute;
    width: 500px;
    height: 500px;
    border-radius: 50%;
    background-color: #ecf0f3;
    box-shadow: inset 8px 8px 12px #b8bec7, inset -8px -8px 12px #fff;
    bottom: -60%;
    left: -60%;
    transition: 1.25s;
}

.switch_circle-t {
    top: -30%;
    left: 60%;
    width: 300px;
    height: 300px;
}

.switch_container {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    position: absolute;
    width: 400px;
    padding: 50px 55px;
    transition: 1.25s;
}

.switch_button {
    cursor: pointer;
}

.switch_button:hover,
.submit:hover {
    box-shadow: 6px 6px 10px #d1d9e6, -6px -6px 10px #f9f9f9;
    transform: scale(0.985);
    transition: 0.25s;
}

.switch_button:active,
.switch_button:focus {
    box-shadow: 2px 2px 6px #d1d9e6, -2px -2px 6px #f9f9f9;
    transform: scale(0.97);
    transition: 0.25s;
}
</style>

<script>
import CryptoJS from 'crypto-js';
export default {
    name: 'RetrievePassword',
    components: {
    },
    data() {
        return {
            username: "",
            realName: "",
            idNumber: "",
            password: "",
            password1: "",
            nickname: "",
            oldPassword: "123456",
            code: "",
            errorMsg: "",

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

            // API: {
            //     'modify_email': 'http://10.186.253.111:8080/user/modify/email',
            //     'modify_nickname': 'http://10.186.253.111:8080/user/modify/nickname',
            //     'modify_password': 'http://10.186.253.111:8080/user/modify/password',
            //     'modify_paymentPassword': 'http://10.186.253.111:8080/user/modify/paymentPassword',
            //     'modify_phone': 'http://10.186.253.111:8080/user/modify/phone',
            //     'reservation_flight': 'http://10.186.253.111:8080/user/reservation/flight',
            //     'reservation_hotel': 'http://10.186.253.111:8080/user/reservation/hotel',
            //     'retrieve_password': 'http://10.186.253.111:8080/user/retrieve/password',
            //     'user_info': 'http://10.186.253.111:8080/user/info',
            //     'user_login': 'http://10.186.253.111:8080/user/login',
            //     'user_logout': 'http://10.186.253.111:8080/user/logout',
            //     'user_order': 'http://10.186.253.111:8080/user/order',
            //     'user_recharge': 'http://10.186.253.111:8080/user/recharge',
            //     'user_register': 'http://10.186.253.111:8080/user/register',
            //     'user_find': 'http://10.186.253.111:8080/user/check/username'
            // }
        }
    },
    mounted() {
        this.init();
        if (window.sessionStorage.getItem('needRefresh')) {
            window.sessionStorage.removeItem('needRefresh');
            location.reload();
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
        userCheck() {
            event.preventDefault();

            if (this.username == "") {
                this.showRetrieveError = true;
                this.retrieveErrorMessage = "用户名不能为空";
                this.$forceUpdate();
            } else if (this.realName == "") {
                this.showRetrieveError = true;
                this.retrieveErrorMessage = "真实姓名不能为空";
                this.$forceUpdate();
            } else if (this.idNumber == "") {
                this.showRetrieveError = true;
                this.retrieveErrorMessage = "身份证号不能为空";
                this.$forceUpdate();
            } else if (this.nickname == "") {
                this.showRetrieveError = true;
                this.retrieveErrorMessage = "昵称不能为空";
                this.$forceUpdate();
            } else {
                // 用户名、真实姓名、身份证正确性校验：向后端发送请求，校验信息正确性
                this.retrieve_password({username: this.username, realName: this.realName, idNumber: this.idNumber, nickname: this.nickname});
            }
        },
        translate(parameter) {
            var result = "";
            var keys = Object.keys(parameter);
            if (keys != []) {
                result = "?";
            }
            for (var i = 0; i < keys.length; i++) {
                result = result + keys[i] + '=' + parameter[keys[i]];
                if (i != keys.length - 1) {
                    result = result + "&";
                }
            }
            return result;
        },
        retrieve_password(parameter) {
            let input1 = document.getElementById("input1");
            let input2 = document.getElementById("input2");
            let input3 = document.getElementById("input3");
            let input4 = document.getElementById("input4");
            let button1 = document.getElementById("nextBtn1");
            let circle1 = document.getElementById("circle1");
            let circle2 = document.getElementById("circle2");
            let successInfo = document.getElementById("successInfo");

            var url = this.API['retrieve_password'] + this.translate(parameter);
            console.log("url = " + url + '\n');
            fetch(url, { method: 'GET', credentials: 'include' })
                .then(response => response.json())
                .then(json_data => {
                    // 处理响应数据
                    console.log(json_data);
                    if (json_data.code == "1") {
                        this.oldPassword = json_data.data;
                        console.log("retrieve\n");
                        console.log(json_data);
                        this.showRetrieveError = false;
                        this.retrieveErrorMessage = "";
                    } else {
                        this.showRetrieveError = true;
                        this.retrieveErrorMessage = "信息有误，认证失败";
                    }
                    if (!this.showRetrieveError) {
                        input1.remove();
                        input2.remove();
                        input3.remove();
                        input4.remove();
                        button1.remove();
                        circle1.classList.remove("active");
                        circle2.classList.add("active");
                        successInfo.style.display = "block";
                    }
                    this.$forceUpdate();
                })
                .catch(error => {
                    // 处理错误
                    console.error(error);
                });
        },
        modify_password(parameter) {
            var url = this.API['modify_password'] + this.translate(parameter);
            console.log("url = " + url + '\n');
            fetch(url, { method: 'GET', credentials: 'include' })
                .then(response => response.json())
                .then(json_data => {
                    // 处理响应数据
                    console.log(json_data);
                    this.code = json_data.code;
                    this.errorMsg = json_data.errorMsg;
                })
                .catch(error => {
                    // 处理错误
                    console.error(error);
                });
        },
        user_login(parameter) {
            var url = this.API['user_login'] + this.translate(parameter);
            console.log("url = " + url + '\n');
            fetch(url, { method: 'GET', credentials: 'include' })
                .then(response => response.json())
                .then(json_data => {
                    // 处理响应数据
                    console.log(json_data)
                    this.code = json_data.code;
                    console.log(this.code);
                    if(this.code != '1') {
                        this.showInput3Error = true;
                        this.errorMessage3 = "修改失败";
                        this.$forceUpdate();
                    }
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
                    this.oldPassword = my_data.password;
                })
                .catch(error => {
                    // 处理错误
                    console.error(error);
                });
        },
        handleInput2Change() {
            var pattern = /^[a-zA-Z0-9_]+$/;
            if (this.password == "") {
                this.showInput2Error = true;
                this.errorMessage2 = "新密码不能为空";
            } else if (this.password.length < 6 || this.password.length > 20) {
                this.showInput2Error = true;
                this.errorMessage2 = "密码长度应在6-20个字符之间";
            } else if (!pattern.test(this.password)) {
                this.showInput2Error = true;
                this.errorMessage2 = "密码应由字符、数字和下划线组成";
            } else {
                this.showInput2Error = false;
                this.errorMessage2 = "";
            }

            if (this.password1 != "" && this.password1 != this.password) {
                this.showInput3Error = true;
                this.errorMessage3 = "两次输入的密码不一致";
            } else {
                this.showInput3Error = false;
                this.errorMessage3 = "";
            }
            this.$forceUpdate();
        },
        handleInput3Change() {
            if (this.password1 != "" && this.password1 != this.password) {
                this.showInput3Error = true;
                this.errorMessage3 = "两次输入的密码不一致";
            } else {
                this.showInput3Error = false;
                this.errorMessage3 = "";
            }
            this.$forceUpdate();
        },

        complete() {
            let circle1 = document.getElementById("circle1");
            let circle2 = document.getElementById("circle2");
            circle1.classList.add("active");
            circle2.classList.remove("active");
            window.sessionStorage.setItem('needRefresh', 'true');
            this.$router.push("/LoginView");
        },
        returnLogin() {
            let circle1 = document.getElementById("circle1");
            let circle2 = document.getElementById("circle2");
            circle1.classList.add("active");
            circle2.classList.remove("active");
            window.sessionStorage.setItem('needRefresh', 'true');
            this.$router.push("/LoginView");
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
    }
}

</script>