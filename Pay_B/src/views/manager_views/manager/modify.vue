
<template>
    <div class="modify_container">
        <el-breadcrumb separator="/" class="mianbaoxie">
            <el-breadcrumb-item :to="{ path: '/system' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>修改密码</el-breadcrumb-item>
        </el-breadcrumb>
        <div class="modify_box">
            <div class="modify_title">
                修改密码
            </div>
            <el-form
                ref="modifyFormRef"
                :model="modifyForm"
                label-width="0px"
                class="modify_form">
                <el-form-item prop="old_key">
                    <el-input
                        v-model="modifyForm.old_key"
                        type="password"
                        prefix-icon=""
                        placeholder="请输入原密码">
                    </el-input>
                </el-form-item>
                <el-form-item prop="key1">
                    <el-input
                        v-model="modifyForm.key1"
                        prefix-icon=""
                        type="password"
                        placeholder="请输入新密码">
                    </el-input>
                </el-form-item>
                <el-form-item prop="key2">
                    <el-input
                        v-model="modifyForm.key2"
                        prefix-icon=""
                        type="password"
                        placeholder="请再次输入新密码">
                    </el-input>
                </el-form-item>
                <el-form-item class="btns">
                    <el-button type="primary" @click="Modify">修改</el-button>
                    <el-button @click="resetModifyForm">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<style>
.modify_container{
    background-color: #EAEDF1;
    height:100%;
}
.modify_box{
    width: 600px;
    height: 400px;
    background-color: #fff;
    border-radius: 3px;
    box-shadow: 0px 5px 20px #999;
    position: absolute;
    left: 50%;
    top: 55%;
    transform: translate(-50%, -50%);
}
.modify_title{
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
.modify_form{
    position: absolute;
    bottom: 0;
    width: 100%;
    padding: 0 80px;
    box-sizing: border-box;
}
</style>
<script>
import axios from "axios";
//import {Message} from "element-ui";

export default {
    data() {
        return {
            modifyForm: {
                key1: '',
                old_key: '',
                key2: '',
            },
        }
    },

    methods: {
        Modify() {


            if (!this.modifyForm.key1 || !this.modifyForm.old_key || !this.modifyForm.key2) {
                this.$message.error('请输入完整密码信息！');
                return;
            }
            if (this.modifyForm.key1 !== this.modifyForm.key2) {
                this.$message.error('两次输入的密码不一致，请重新输入！');
                return;
            }


            axios.defaults.baseURL='http://localhost:80'
            axios.post('/admin/modify',{
                    "old_key":this.modifyForm.old_key,
                    "key1":this.modifyForm.key1,
                    "key2":this.modifyForm.key2
                },
                {
                    headers:{
                        'Access-Control-Allow-Origin':'*', //解决cors头问题
                        'Access-Control-Allow-Credentials':'true', //解决session问题
                    }
                }
            ).then(response=>{


                if(String(response.data.code)==="1"){
                    this.$message.success(response.data.msg);
                    window.sessionStorage.clear();
                    this.$router.push("/manager");
                }
                else {
                    this.$message.error(response.data.msg);
                }
            },error=>{
                console.log('错误',error.message)
            })

        },
        resetModifyForm() {
            this.$refs.modifyFormRef.resetFields();
        }
    }
}
</script>