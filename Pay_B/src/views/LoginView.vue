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
                <h2 class="form_title title">登陆系统</h2>
                <div class="form-group">
                        <span class="fa-stack fa-lg">
                            <i class="fas fa-square fa-stack-2x"></i>
                            <i class="fas fa-user fa-stack-1x fa-inverse"></i>
                        </span>
                    <input type="text" class="form_input" placeholder="输入用户名" v-model="inputUserName">
                </div>
                <div class="form-group">
                        <span class="fa-stack fa-lg">
                            <i class="fas fa-square fa-stack-2x"></i>
                            <i class="fas fa-lock fa-stack-1x fa-inverse"></i>
                        </span>
                    <input type="password" class="form_input" placeholder="输入密码" v-model="inputPassword">
                </div>
                <div class="error left-align" v-show="showLoginError">{{ loginErrorMessage }}</div>
                <button class="button submit" ref="loginBtn" @click="loginCheck">登录</button>
                <form style="display: inline-block;">
                    <button class="button3" @click="jump1">管理员登录</button>
                    <button class="button3" @click="jump">忘记密码？</button>
                </form>
            </form>
        </div>

        <div class="container b-container" id="b-container" ref="bContainer">
            <form action="" method="" class="form" id="b-form">
                <h2 class="form_title title"><span id="b-title">创建账号</span></h2>
                <div class="form-group">
                        <span class="fa-stack fa-lg">
                            <i class="fas fa-square fa-stack-2x"></i>
                            <i class="fas fa-user fa-stack-1x fa-inverse" id="icon1"></i>
                        </span>
                    <input type="text" class="form_input" id="input1" placeholder="输入用户名" v-model="input1Value"
                           @input="handleInput1Change">
                </div>
                <div class="error left-align" v-show="showInput1Error">{{ errorMessage1 }}</div>
                <div class="form-group">
                        <span class="fa-stack fa-lg">
                            <i class="fas fa-square fa-stack-2x"></i>
                            <i class="fas fa-lock fa-stack-1x fa-inverse" id="icon2"></i>
                        </span>
                    <input type="password" class="form_input" id="input2" placeholder="输入密码" v-model="input2Value"
                           @input="handleInput2Change">
                </div>
                <div class="error left-align" v-show="showInput2Error">{{ errorMessage2 }}</div>
                <div class="form-group">
                        <span class="fa-stack fa-lg">
                            <i class="fas fa-square fa-stack-2x"></i>
                            <i class="fas fa-user-check fa-stack-1x fa-inverse" id="icon3"></i>
                        </span>
                    <input type="password" class="form_input" id="input3" placeholder="确认密码" v-model="input3Value"
                           @input="handleInput3Change">
                </div>
                <div class="error left-align" v-show="showInput3Error">{{ errorMessage3 }}</div>
                <div class="form-group">
                        <span class="fa-stack fa-lg">
                            <i class="fas fa-square fa-stack-2x"></i>
                            <i class="fas fa-smile fa-stack-1x fa-inverse" id="icon4"></i>
                        </span>
                    <input type="text" class="form_input" id="input4" placeholder="设置昵称" v-model="input4Value"
                           @input="handleInput4Change">
                </div>
                <div class="error left-align" v-show="showInput4Error">{{ errorMessage4 }}</div>
                <div class="form-group" id="input5">
                    <label1><input type="radio" name="role" value="B" checked v-model="type"
                                   @input="handleInput5ChangeB"> 买家</label1>
                    <label2><input type="radio" name="role" value="S" v-model="type" @input="handleInput5ChangeS"> 卖家
                    </label2>
                </div>
                <div class="form-group">
                        <span class="fa-stack fa-lg" id="icon5" style="display: none;">
                            <i class="fas fa-square fa-stack-2x"></i>
                            <i class="fas fa-lock fa-stack-1x fa-inverse"></i>
                        </span>
                    <input type="password" class="form_input1" id="input6" placeholder="输入支付密码"
                           v-model="paymentPassword" @input="handleInput6Change">
                </div>
                <div class="error left-align" v-show="showInput6Error">{{ errorMessage6 }}</div>
                <div class="form-group">
                        <span class="fa-stack fa-lg" id="icon6" style="display: none;">
                            <i class="fas fa-square fa-stack-2x"></i>
                            <i class="fas fa-user-check fa-stack-1x fa-inverse"></i>
                        </span>
                    <input type="password" class="form_input1" id="input7" placeholder="确认支付密码"
                           v-model="paymentPassword1" @input="handleInput7Change">
                </div>
                <div class="error left-align" v-show="showInput7Error">{{ errorMessage7 }}</div>
                <button class="button1 submit" id="button1" ref="button1" type="submit" @click="nextStep">下一步</button>
                <button class="button2 submit" id="button2" ref="button2" type="submit" @click="complete">完成</button>
            </form>
        </div>

        <div class="switch" id="switch-cnt" ref="switchCnt">
            <div class="switch_circle" ref="switchCircle1"></div>
            <div class="switch_circle switch_circle-t" ref="switchCircle2"></div>
            <div class="switch_container" id="switch-c1" ref="switchC1">
                <h2 class="switch_title title" style="letter-spacing: 0;">在线支付系统</h2>
                <p class="switch_description description">还没有账号？点击这里注册</p>
                <button class="switch_button button switch-btn" ref="switchBtn1">注册</button>
            </div>

            <div class="switch_container is-hidden" id="switch-c2" ref="switchC2">
                <h2 class="switch_title title" style="letter-spacing: 0;">欢迎您，新朋友</h2>
                <p class="switch_description description">填写个人信息，完成账号注册，成为我们的一份子吧！</p>
                <button class="switch_button button switch-btn" ref="switchBtn2">返回</button>
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
    /* background-color: #ecf0f3; */
    color: #a0a5a8;
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

.form_input1 {
    width: 350px;
    height: 40px;
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
    display: none;
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

.error {
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
}

.button2 {
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

.button3 {
    color: #181818;
    font-size: 15px;
    margin-top: 25px;
    margin-left: 20px;
    margin-right: 20px;
    background-color: #ecf0f3;
    border-bottom: 1px solid #a0a5a8;
    line-height: 2;
    border: none;
    cursor: pointer;
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

.is-txr {
    left: calc(100% - 400px);
    transition: 1.25s;
    transform-origin: left;
}

.is-txl {
    left: 0;
    transition: 1.25s;
    transform-origin: right;
}

.is-z {
    z-index: 200;
    transition: 1.25s;
}

.is-hidden {
    visibility: hidden;
    opacity: 0;
    position: absolute;
    transition: 1.25s;
}

.is-gx {
    animation: is-gx 1.25s;
}

@keyframes is-gx {

    0%,
    10%,
    100% {
        width: 400px;
    }

    30%,
    50% {
        width: 500px;
    }
}
</style>

<script>
import CryptoJS from 'crypto-js';
export default {
    name: 'LoginView',
    components: {

    },
    data() {
        return {
            inputUserName: "",
            inputPassword: "",
            input1Value: "",
            input2Value: "",
            input3Value: "",
            input4Value: "",
            input1Binding: "username",
            input2Binding: "password",
            input3Binding: "password1",
            input4Binding: "nickname",

            username: "",
            password: "",
            password1: "",
            nickname: "",
            type: "B",
            realName: "",
            idNumber: "",
            email: "",
            phone: "",
            paymentPassword: "",
            paymentPassword1: "",
            code: "",
            errorMsg: "",
            // API: {
            //     'modify_email': 'http://localhost:8080/user/modify/email',
            //     'modify_nickname': 'http://localhost:8080/user/modify/nickname',
            //     'modify_password': 'http://localhost:8080/user/modify/password',
            //     'modify_paymentPassword': 'http://localhost:8080/user/modify/paymentPassword',
            //     'modify_phone': 'http://localhost:8080/user/modify/phone',
            //     'reservation_flight': 'http://localhost:8080/user/reservation/flight',
            //     'reservation_hotel': 'http://localhost:8080/user/reservation/hotel',
            //     'retrieve_password': 'http://localhost:8080/user/retrieve/password',
            //     'user_info': 'http://localhost:8080/user/info',
            //     'user_login': 'http://localhost:8080/user/login',
            //     'user_logout': 'http://localhost:8080/user/logout',
            //     'user_order': 'http://localhost:8080/user/order',
            //     'user_recharge': 'http://localhost:8080/user/recharge',
            //     'user_register': 'http://localhost:8080/user/register',
            //     'user_find': 'http://localhost:8080/user/check/username'
            // }


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
    mounted() {
        this.init();
        let switchCnt = this.$refs.switchCnt;
        let switchCircle = [this.$refs.switchCircle1, this.$refs.switchCircle2];
        let switchC1 = this.$refs.switchC1;
        let switchC2 = this.$refs.switchC2;
        let aContainer = this.$refs.aContainer;
        let bContainer = this.$refs.bContainer;
        let switchBtn = [this.$refs.switchBtn1, this.$refs.switchBtn2];
        let allButtons = [this.$refs.loginBtn, this.$refs.button1];

        let getButtons = (e) => e.preventDefault()
        let changeForm = () => {
            // 修改类名
            switchCnt.classList.add("is-gx");
            setTimeout(function () {
                switchCnt.classList.remove("is-gx");
            }, 1500)
            switchCnt.classList.toggle("is-txr");
            switchCircle[0].classList.toggle("is-txr");
            switchCircle[1].classList.toggle("is-txr");

            switchC1.classList.toggle("is-hidden");
            switchC2.classList.toggle("is-hidden");
            aContainer.classList.toggle("is-txl");
            bContainer.classList.toggle("is-txl");
            bContainer.classList.toggle("is-z");
        }
        // 登录/注册界面切换
        let shell = () => {
            for (var i = 0; i < allButtons.length; i++)
                allButtons[i].addEventListener("click", getButtons);
            for (var j = 0; j < switchBtn.length; j++)
                switchBtn[j].addEventListener("click", changeForm)
        }

        window.addEventListener("load", shell);

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
        loginCheck() {
            event.preventDefault();
            if (this.inputUserName == "") {
                this.showLoginError = true;
                this.loginErrorMessage = "用户名不能为空";
                this.$forceUpdate();
            } else if (this.inputPassword == "") {
                this.showLoginError = true;
                this.loginErrorMessage = "密码不能为空";
                this.$forceUpdate();
            } else {
                // 用户名与密码正确性校验：向后端发送请求，校验用户名和对应的密码
                console.log(this.encrypt(this.inputPassword, this.inputUserName)+'\n');
                this.user_login({ username: this.inputUserName, password: this.encrypt(this.inputPassword, this.inputUserName) });
            }
        },
        jump() {
            window.sessionStorage.setItem('needRefresh', 'true');
            this.$router.push("/RetrievePassword");
        },
        jump1() {
            this.$router.push("/manager");
        },
        nextStep() {
            event.preventDefault();
            // 表示所有输入框的变量
            let input1 = document.getElementById("input1");
            let input2 = document.getElementById("input2");
            let input3 = document.getElementById("input3");
            let input4 = document.getElementById("input4");
            let input5 = document.getElementById("input5");
            let input6 = document.getElementById("input6");
            let input7 = document.getElementById("input7");
            let bTitle = document.getElementById("b-title");
            let button1 = document.getElementById("button1");
            let button2 = document.getElementById("button2");
            let icon1 = document.getElementById("icon1");
            let icon2 = document.getElementById("icon2");
            let icon3 = document.getElementById("icon3");
            let icon4 = document.getElementById("icon4");
            let icon5 = document.getElementById("icon5");
            let icon6 = document.getElementById("icon6");

            if (this.input1Value == "") {
                this.showInput1Error = true;
                this.errorMessage1 = "用户名不能为空";
            }

            if (this.input2Value == "") {
                this.showInput2Error = true;
                this.errorMessage2 = "密码不能为空";
            }

            if (this.input3Value == "") {
                this.showInput3Error = true;
                this.errorMessage3 = "请确认密码";
            }

            if (this.input4Value == "") {
                this.showInput4Error = true;
                this.errorMessage4 = "昵称不能为空";
            }

            this.$forceUpdate();

            if (!this.showInput1Error && !this.showInput2Error && !this.showInput3Error && !this.showInput4Error) {
                // 写入输入内容并更改输入框的绑定
                this[this.input1Binding] = this.input1Value;
                this.input1Binding = "realName";
                this[this.input2Binding] = this.input2Value;
                this.input2Binding = "idNumber";
                this[this.input3Binding] = this.input3Value;
                this.input3Binding = "email";
                this[this.input4Binding] = this.input4Value;
                this.input4Binding = "phone";

                // 修改输入框及按钮属性
                bTitle.innerHTML = "填写个人信息";
                input1.setAttribute("placeholder", "输入真实姓名");
                input2.setAttribute("placeholder", "输入身份证号");
                input2.setAttribute("type", "text");
                input3.setAttribute("placeholder", "输入邮箱地址");
                input3.setAttribute("type", "text");
                input4.setAttribute("placeholder", "输入手机号");
                input5.remove();
                if (this.type == "B") {     // 只有是买家时才显示
                    input6.style.display = "inline-block";
                    input7.style.display = "inline-block";
                    icon5.style.display = "inline-block";
                    icon6.style.display = "inline-block";
                }
                button1.remove();
                button2.style.display = "block";
                icon1.classList.remove("fa-user");
                icon1.classList.add("fa-user-tie");
                icon2.classList.remove("fa-lock");
                icon2.classList.add("fa-id-card");
                icon3.classList.remove("fa-user-check");
                icon3.classList.add("fa-envelope");
                icon4.classList.remove("fa-smile");
                icon4.classList.add("fa-phone");

                // 清空inputValue便于下一次输入
                this.input1Value = "";
                this.input2Value = "";
                this.input3Value = "";
                this.input4Value = "";
            }
        },
        complete() {
            event.preventDefault();

            if (this.input1Value == "") {
                this.showInput1Error = true;
                this.errorMessage1 = "真实姓名不能为空";
            }

            if (this.input2Value == "") {
                this.showInput2Error = true;
                this.errorMessage2 = "身份证号码不能为空";
            }

            if (this.type == "B" && this.paymentPassword == "") {
                this.showInput6Error = true;
                this.errorMessage6 = "支付密码不能为空";
            }

            if (this.type == "B" && this.paymentPassword1 == "") {
                this.showInput7Error = true;
                this.errorMessage7 = "请确认支付密码";
            }

            this.$forceUpdate();

            if (!this.showInput1Error && !this.showInput2Error && !this.showInput3Error && !this.showInput4Error && !this.showInput6Error && !this.showInput7Error) {
                this[this.input1Binding] = this.input1Value;
                this[this.input2Binding] = this.input2Value;
                this[this.input3Binding] = this.input3Value;
                this[this.input4Binding] = this.input4Value;

                console.log(this.username);
                console.log(this.password);
                console.log(this.password1);
                console.log(this.nickname);
                console.log(this.type);
                console.log(this.realName);
                console.log(this.idNumber);
                console.log(this.email);
                console.log(this.phone);
                console.log(this.paymentPassword);
                console.log(this.paymentPassword1);

                this.user_register({
                    username: this.username, password: this.encrypt(this.password, this.username), type: this.type,
                    nickname: this.nickname, phone: this.phone, email: this.email, realName: this.realName,
                    idNumber: this.idNumber, paymentPassword: this.paymentPassword
                })
            }
        },
        handleInput1Change() {
            if (this.input1Binding == "username") {
                var pattern = /^[a-zA-Z0-9_]+$/;
                if (this.input1Value == "") {
                    this.showInput1Error = true;
                    this.errorMessage1 = "用户名不能为空";
                } else if (this.input1Value.length < 6 || this.input1Value.length > 20) {
                    this.showInput1Error = true;
                    this.errorMessage1 = "用户名长度应在6-20个字符之间";
                } else if (!pattern.test(this.input1Value)) {
                    this.showInput1Error = true;
                    this.errorMessage1 = "用户名应由字符、数字和下划线组成";
                } else {
                    this.user_find({ username: this.input1Value });
                }
            } else {
                if (this.input1Value == "") {
                    this.showInput1Error = true;
                    this.errorMessage1 = "真实姓名不能为空";
                } else if (!this.validateRealName(this.input1Value)) {
                    this.showInput1Error = true;
                    this.errorMessage1 = "请输入正确的中文名";
                } else {
                    this.showInput1Error = false;
                    this.errorMessage1 = "";
                }
            }
            this.$forceUpdate();
        },
        handleInput2Change() {
            if (this.input2Binding == "password") {
                var pattern = /^[a-zA-Z0-9_]+$/;
                if (this.input2Value == "") {
                    this.showInput2Error = true;
                    this.errorMessage2 = "密码不能为空";
                } else if (this.input2Value.length < 6 || this.input2Value.length > 20) {
                    this.showInput2Error = true;
                    this.errorMessage2 = "密码长度应在6-20个字符之间";
                } else if (!pattern.test(this.input2Value)) {
                    this.showInput2Error = true;
                    this.errorMessage2 = "密码应由字符、数字和下划线组成";
                } else {
                    this.showInput2Error = false;
                    this.errorMessage2 = "";
                }

                if (this.input3Value != "" && this.input3Value != this.input2Value) {
                    this.showInput3Error = true;
                    this.errorMessage3 = "两次输入的密码不一致";
                } else {
                    this.showInput3Error = false;
                    this.errorMessage3 = "";
                }
            } else {
                if (this.input2Value == "") {
                    this.showInput2Error = true;
                    this.errorMessage2 = "身份证号不能为空";
                } else if (!this.validateIDCard(this.input2Value)) {
                    this.showInput2Error = true;
                    this.errorMessage2 = "请输入正确的身份证号";
                } else {
                    this.showInput2Error = false;
                    this.errorMessage2 = "";
                }
            }
            this.$forceUpdate();
        },
        handleInput3Change() {
            if (this.input3Binding == "password1") {
                if (this.input3Value != this.input2Value) {
                    this.showInput3Error = true;
                    this.errorMessage3 = "两次输入的密码不一致";
                } else {
                    this.showInput3Error = false;
                    this.errorMessage3 = "";
                }
            } else {
                if (!this.validateEmail(this.input3Value)) {
                    this.showInput3Error = true;
                    this.errorMessage3 = "请输入正确的邮箱地址";
                } else {
                    this.showInput3Error = false;
                    this.errorMessage3 = "";
                }
            }
            this.$forceUpdate();
        },
        handleInput4Change() {
            if (this.input4Binding == "nickname") {
                if (this.input4Value == "") {
                    this.showInput4Error = true;
                    this.errorMessage4 = "昵称不能为空";
                }else if (this.input4Value.length > 6) {
                    this.showInput4Error = true;
                    this.errorMessage4 = "昵称长度应在6个字符之内";
                } else {
                    this.showInput4Error = false;
                    this.errorMessage4 = "";
                }
            } else {
                if (!this.validatePhone(this.input4Value)) {
                    this.showInput4Error = true;
                    this.errorMessage4 = "请输入正确的手机号";
                } else {
                    this.showInput4Error = false;
                    this.errorMessage4 = "";
                }
            }
            this.$forceUpdate();
        },
        handleInput5ChangeB() {
            this.type = "B";
            this.$forceUpdate();
        },
        handleInput5ChangeS() {
            this.type = "S";
            this.$forceUpdate();
        },
        handleInput6Change() {
            var pattern = /^[0-9]+$/;
            if (this.paymentPassword == "") {
                this.showInput6Error = true;
                this.errorMessage6 = "支付密码不能为空";
            } else if (this.paymentPassword.length != 6 || !pattern.test(this.paymentPassword)) {
                this.showInput6Error = true;
                this.errorMessage6 = "支付密码应由6位数字构成";
            } else {
                this.showInput6Error = false;
                this.errorMessage6 = "";
            }

            if (this.paymentPassword1 != "" && this.paymentPassword1 != this.paymentPassword) {
                this.showInput7Error = true;
                this.errorMessage7 = "两次输入的支付密码不一致";
            } else {
                this.showInput7Error = false;
                this.errorMessage7 = "";
            }
            this.$forceUpdate();
        },
        handleInput7Change() {
            if (this.paymentPassword1 != "" && this.paymentPassword1 != this.paymentPassword) {
                this.showInput7Error = true;
                this.errorMessage7 = "两次输入的支付密码不一致";
            } else {
                this.showInput7Error = false;
                this.errorMessage7 = "";
            }
            this.$forceUpdate();
        },
        validateIDCard(idCard) {
            // 身份证号码长度应该为 18（中国大陆）
            if (idCard.length !== 18) {
                return false;
            }

            // 前 17 位应该都是数字
            const regExp = /^[0-9]{17}$/;
            if (!regExp.test(idCard.slice(0, -1))) {
                return false;
            }

            // 校验码计算
            const factor = [
                7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2,
            ];
            const checkCodeList = [
                "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2",
            ];

            let sum = 0;
            for (let i = 0; i < factor.length; i++) {
                sum += factor[i] * parseInt(idCard.charAt(i), 10);
            }
            const index = sum % 11;

            // 检查校验码是否匹配
            return checkCodeList[index] === idCard.charAt(17);
        },
        validateRealName(realName) {
            // 姓名应该为 2-4 个中文字符
            const regExp = /^[\u4e00-\u9fa5]{2,4}$/;
            return regExp.test(realName);
        },
        validateEmail(email) {
            // 使用正则表达式匹配邮箱格式
            var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
            return emailRegex.test(email);
        },
        validatePhone(phone) {
            // 使用正则表达式匹配手机号格式
            var phoneRegex = /^1[3456789]\d{9}$/;
            return phoneRegex.test(phone);
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
        user_login(parameter) {
            var url = this.API['user_login'] + this.translate(parameter);
            console.log("url = " + url + '\n');
            fetch(url, { method: 'GET', credentials: 'include' })
                .then(response => response.json())
                .then(json_data => {
                    // 处理响应数据
                    console.log(json_data);
                    this.code = json_data.code;

                    this.$store.commit('login', 'true')
                    console.log(json_data.data.id)
                    localStorage.setItem('username', json_data.data.username);
                    localStorage.setItem('userid', json_data.data.id);
                    localStorage.setItem('usertype', 'B');
                    localStorage.setItem('userpaymentpassword', json_data.data.paymentPassword);

                    console.log(this.code);
                    if(this.code == '1'){
                        this.$router.push("/UserView");
                    } else {
                        this.showLoginError = true;
                        this.loginErrorMessage = json_data.msg;
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
                    this.idNumber = my_data.idNumber;
                })
                .catch(error => {
                    // 处理错误
                    console.error(error);
                });
        },
        user_register(parameter) {
            var url = this.API['user_register'] + this.translate(parameter);
            console.log("url = " + url + '\n');
            fetch(url, { method: 'GET', credentials: 'include' })
                .then(response => response.json())
                .then(json_data => {
                    // 处理响应数据
                    this.code = json_data.code;
                    console.log("register\n");
                    console.log(json_data);
                    if (this.code == "0") {
                        this.showInput7Error = true;
                        this.errorMessage7 = json_data.msg;
                    } else {
                        location.reload();
                    }
                })
                .catch(error => {
                    // 处理错误
                    console.error(error);
                });
        },
        user_find(parameter) {
            var url = this.API['user_find'] + this.translate(parameter);
            console.log("url = " + url + '\n');
            fetch(url, { method: 'GET', credentials: 'include' })
                .then(response => response.json())
                .then(json_data => {
                    // 处理响应数据
                    console.log(json_data);
                    if (json_data.code == "0") {
                        this.showInput1Error = true;
                        this.errorMessage1 = "该用户名已存在";
                        this.$forceUpdate();
                    } else {
                        this.showInput1Error = false;
                        this.errorMessage1 = "";
                        this.$forceUpdate();
                    }
                })
                .catch(error => {
                    // 处理错误
                    console.error(error);
                });
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
