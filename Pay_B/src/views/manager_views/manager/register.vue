<template>
    <div class="register_container">
        <el-breadcrumb separator="/" class="mianbaoxie">
            <el-breadcrumb-item :to="{ path: '/system' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>管理员注册</el-breadcrumb-item>
        </el-breadcrumb>
        <div class="register_box">
            <div class="register_title">
                管理员注册
            </div>
            <el-form
                ref="registerFormRef"
                :model="registerForm"
                label-width="0px"
                class="register_form">
                <el-form-item prop="name">
                    <el-input
                        v-model="registerForm.name"
                        prefix-icon=""
                        placeholder="请输入用户名">
                    </el-input>
                </el-form-item>
                <el-form-item prop="key1">
                    <el-input
                        v-model="registerForm.key1"
                        prefix-icon=""
                        type="password"
                        placeholder="请输入密码">
                    </el-input>
                </el-form-item>
                <el-form-item prop="key2">
                    <el-input
                        v-model="registerForm.key2"
                        prefix-icon=""
                        type="password"
                        placeholder="请再次输入密码">
                    </el-input>
                </el-form-item>
                <el-select v-model="registerForm.type" placeholder="请选择">
                    <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select>
                <el-form-item class="btns">
                    <el-button type="primary" @click="validateRegisterForm">注册</el-button>
                    <el-button @click="resetRegisterForm">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<style>
.register_container{
    background-color: #EAEDF1;
    height:100%;
}
.register_box{
    width: 400px;
    height: 400px;
    background-color: #fff;
    border-radius: 3px;
    box-shadow: 0px 5px 20px #999;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
}
.register_title{
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
.register_form{
    position: absolute;
    bottom: 0;
    width: 100%;
    padding: 0 80px;
    box-sizing: border-box;
}
</style>
<script>
import axios from "axios";
import {Message} from "element-ui";

export default {
    data() {
        return {
            registerForm: {
                name: '',
                key1: '',
                key2: '',
                type:'system'
            },
            options: [{
                value: 'system',
                label: '系统管理员'
            }, {
                value: 'book',
                label: '在线预定管理员'
            }, {
                value: 'check',
                label: '审核员'
            },],
            value: '系统管理员'
        }

    },

    methods: {
        validateRegisterForm() {
            if (!this.registerForm.name || !this.registerForm.key1 || !this.registerForm.key2) {
                this.$message.error('请输入完整注册信息！');
                return;
            }
            if (this.registerForm.key1 !== this.registerForm.key2) {
                this.$message.error('两次输入的密码不一致，请重新输入！');
                return;
            }


            axios.defaults.baseURL='http://localhost:80'
            axios.post('/admin/register',{

                    "name":this.registerForm.name,
                    "key1":this.registerForm.key1,
                    "key2":this.registerForm.key2,
                    "type":this.registerForm.type,
                },
                {
                    headers:{
                        'Access-Control-Allow-Origin':'*', //解决cors头问题
                        'Access-Control-Allow-Credentials':'true', //解决session问题
                        // 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8', //将表单数据传递转化为form-data类型
                        //  'token': '131231',
                    }
                }).then(response=>{

                //console.log('/login',response.data)
                //const {code,msg,data}=response;


                if(String(response.data.code)==="1"){
                    //console.log("123");

                    //this.$message.success("963963");
                    //Message.success(response.data.msg);

                    this.$message.success(response.data.msg);
                    this.$refs.registerFormRef.resetFields();
                }
                else
                {
                    // Message.error(msg);
                    this.$message.error(response.data.msg);
                }
            },error=>{
                console.log('错误',error.message)
            })




            //const {name, key1,type} = this.registerForm;

        },
        resetRegisterForm() {
            this.$refs.registerFormRef.resetFields();
        }
    }
}
</script>