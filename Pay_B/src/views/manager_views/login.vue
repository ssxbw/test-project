<template>
    <div class="login_container">
        <div class="login_box">
            <div class="login_title">
                管理员登录
            </div>
            <el-form ref="loginFormRef" :model="loginForm" label-width="0px" class="login_form">
                <el-form-item>
                    <el-input v-model="loginForm.name" prefix-icon="" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input v-model="loginForm.key" prefix-icon="" type="password" placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item class="btns">
                    <el-button type="primary" @click="login">登录</el-button>
                    <el-button type="info" @click="reset_form">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<style>
.login_container{
    background-color: #2b4b6b;
    height:100%;
}
.login_box{
    width: 400px;
    height: 300px;
    background-color: #fff;
    border-radius: 3px;
    box-shadow: 0px 5px 20px #999;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
}
.login_title{
    position: absolute;
    left: 50%;
    top:20%;
    transform: translate(-50%,-50%);
    text-align: center;
    color: #333;
    text-shadow: 2px 2px #ddd;
    font-size: 36px;
    font-weight: bold;
}
.btns{
    display:flex;
    justify-content: flex-end;
}
.login_form{
    position: absolute;
    bottom: 0;
    width: 100%;
    padding: 0 80px;
    box-sizing: border-box;
}
</style>
<script>
import axios from "axios";


export default {
    data() {
        return {
            loginForm: {
                name: '',
                key: '',
            },
        };
    },
    methods: {
        reset_form() {
            this.loginForm.key='';
            this.loginForm.name='';
            //this.$refs.loginFormRef.resetFields();
        },
        login:function (){
/*
            window.sessionStorage.setItem("token",'132')
            const{name,key}=this.loginForm;
            this.$router.push({
                path:'/system',
                query:{name,key}
            });*/

            axios.defaults.baseURL='http://localhost:80'
            axios.post('/admin/login',{

                "name":this.loginForm.name,
                "key":this.loginForm.key
            }).then(response=>{

                if(String(response.data.code)==="1"){

                    if(response.data.data.token){
                        document.cookie="token="+response.data.data.token+";"
                        window.sessionStorage.setItem("token",response.data.data.token)
                    }
                    if(response.data.data.managerType==="system") {
                        const {name, key} = this.loginForm;
                        this.$router.push({
                            path: '/system',
                            query: {name}
                        });
                    }
                    else if(response.data.data.managerType==="book")
                    {
                        this.$store.commit('login', 'true')
                        localStorage.setItem('username', response.data.data.managerId)
                        localStorage.setItem('usertype', 'S')
                        console.log(localStorage.getItem('username'))
                        this.$router.push({
                            path: '/hotel',
                        });
                    }
                    else if(response.data.data.managerType==="check")
                    {
                        this.$router.push({
                            path: '/recon',
                        });
                    }

                }
                else
                {
                    this.$message.error(response.data.msg);
                }
            },error=>{
                console.log('错误',error.message)
            })
        }
    },
}
</script>
