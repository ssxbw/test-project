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
    <el-container>
        <el-aside><SideBar></SideBar></el-aside>
        <el-main>
        <div>
            <div class="search" style="margin-left:2%;margin-bottom: 3%">
                <el-main></el-main>
                <el-input
                    placeholder="商品名"
                    prefix-icon="el-icon-search"
                    v-model="search_name"
                    style="width: 500px; margin-left: 5%; margin-bottom: 10px"
                    clearable>
                </el-input>
                <el-input
                    placeholder="价格下界"
                    prefix-icon="el-icon-search"
                    v-model="search_price1"
                    style="width: 150px; margin-left: 10px; margin-bottom: 10px"
                    clearable>
                </el-input>
                -
                <el-input
                    placeholder="价格上界"
                    prefix-icon="el-icon-search"
                    v-model="search_price2"
                    style="width: 150px; margin-right: 10px; margin-bottom: 10px"
                    clearable>
                </el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
                <el-button type="primary" icon="el-icon-circle-close" @click="reset">重置</el-button>
            </div>
            <el-col style="width: 25%; margin-left: 5%; margin-bottom: 5%" v-for="item in itemData" :key="item.itemId" :offset="1">
                <el-card shadow="hover" :body-style="{ padding: '1%' }">
                    <el-image :src="getImage(item.image)"  class="image"/>
                    <div style="padding: 14px;">
                        <span>{{ item.name }}</span>
                        <div class="bottom clearfix">
                            ￥{{ item.price }}
                            <el-button type="text" class="button" @click="detailVisible = true, detail(item)" style="margin-left: 5%">详细信息</el-button>
                            <el-button @click="pwd = '', pwdDialog = true, detail(item)" type="text" class="button" style="margin-left: 6%;">
                                购买
                            </el-button>
                            <el-dialog
                                title="请输入密码"
                                :visible.sync="pwdDialog"
                                width="30%"
                                :before-close="handleClose">
                                <el-input placeholder="请输入密码" v-model="pwd" show-password></el-input>
                                <span slot="footer" class="dialog-footer">
                                    <el-button @click="pwdDialog = false">取 消</el-button>
                                    <el-button type="primary" @click="pwdDialog = false, buy()">确 定</el-button>
                                </span>
                            </el-dialog>
                        </div>
                        <el-dialog title="商品信息" :visible.sync="detailVisible">
                            <el-descriptions>
                                <el-descriptions-item label="商品名">{{ detailData.item.name }}</el-descriptions-item>
                                <el-descriptions-item label="价格">{{ detailData.item.price }}</el-descriptions-item>
                                <el-descriptions-item label="商家">{{ detailData.item.sellerId }}</el-descriptions-item>
                                <el-descriptions-item label="库存">{{ detailData.item.count }}</el-descriptions-item>
                                <el-descriptions-item label="详细信息">{{ detailData.item.itemDescription }}</el-descriptions-item>
                            </el-descriptions>
                        </el-dialog>
                    </div>
                </el-card>
            </el-col>
        </div>
        <div class="overlay" :class="{ 'show-overlay': isModalVisible }"></div>


        <div class="modal" :class="{ 'show-modal': isLogoutModalVisible }" style="width: 250px; height: 180px;">
            <!-- 弹框内容 -->
            <h3 class="title">是否退出系统</h3>
            <div style="display:flex; justify-content: center; align-items: center; width:98%;">
                <button style="cursor: pointer;" @click="logout">确认</button>
                <button style="cursor: pointer; margin-left: 10px;" @click="isLogoutModalVisible=false">取消</button>
            </div>
        </div>
        </el-main>
    </el-container>
    </body>
    </html>
</template>

<style>
.demo-table-expand {
    font-size: 0;
}
.demo-table-expand label {
    width: 90px;
    color: #99a9bf;
}
.demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
}

.bottom {
    margin-top: 13px;
    line-height: 12px;
}
.button {
    padding: 0;
    float: right;
}
.clearfix:before,
.clearfix:after {
    display: table;
    content: "";
}
.clearfix:after {
    clear: both
}
.image {
    height: 400px;
    object-fit: contain;
    display: flex;
    justify-content: center;
}
.white {
    background: #ffffff;
    border-radius: 4px;
    min-height: 36px;
}
</style>

<script>
import axios from "axios";
import md5 from 'js-md5';
import IconView from "@/components/Icon.vue";
import SideBar from "@/components/SideBar.vue";
import router from "@/router";

export default {
    components: {SideBar, IconView},
    item: ["name", "price", "itemId", "sellerId", "itemDescription", "count", "image"],
    data(){
        return {
            allItems: [],
            itemData: [],
            search_name:"",
            search_price1:'',
            search_price2:'',
            detailVisible: false,
            pwdDialog: false,
            direction: 'btt',
            num: 1,
            pwd: '',
            detailData : {
                item:[],
            },
            isLogoutModalVisible: false,
            isModalVisible: false,
        }
    },
    mounted() {
        axios.get('http://localhost/order/type').then(res => {
            if (String(res.data.code) === '0') {
                this.$notify.error({
                    title: '错误',
                    message: "非法访问！"
                });
                this.logout();
            } else {
                this.type = res.data.type;
                if(this.type !== "0"){
                    this.$notify.error({
                        title: '错误',
                        message: "非法访问！"
                    });
                    this.logout();
                }
            }
        })
    },
    created() {
        this.load()
    },
    methods:{
        load(){
            axios.get('http://localhost/item').then(res=>{
                console.log(res.data);
                if(String(res.data.code) === '0') {
                    this.$notify.error({
                        title: '错误',
                        message: res.data.msg
                    });
                }else{
                    this.allItems = res.data.data;
                    this.itemData = res.data.data;
                }
            })
        },
        getImage(image){
            return `http://localhost${image}`
        },
        handleClose(done) {
            this.$confirm('确认关闭？')
                .then(_ => {
                    done();
                })
                .catch(_ => {});
        },
        handleChange(value) {
            console.log(value);
        },
        buy(){
            const order={
                sellerId: this.detailData.item.sellerId,
                itemId: this.detailData.item.itemId,
                totalPrice: this.detailData.item.price,
                orderStateString: 0,
                complaintRecord: this.detailData.item.itemDescription
            }
            console.log(order);
            axios.post('http://localhost/item/'+md5(this.pwd), order)
                .then((res)=> {
                    console.log(res);
                    if(String(res.data.code) === '1'){
                        this.$notify({
                            title: '成功',
                            message: res.data.msg,
                            type: 'success'
                        });
                        this.load();
                    }else{
                        this.$notify.error({
                            title: '错误',
                            message: res.data.msg
                        });
                    }
                })
                .catch((error)=> {
                    console.log(error);
                    alert('输入格式错误');
                });
        },
        detail(item){
            this.detailData.item = item;
        },
        logout() {
            var url = "http://localhost/user/logout";
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
        handleSearch(){
            // 商品名筛选
            this.itemData = this.allItems.filter(item=>{
                if(this.search_name === ""){
                    return item;
                }else{
                    if(Number(item.name.indexOf(this.search_name)) >= 0){
                        return item;
                    }
                }
            })
            // 价格筛选
            this.itemData = this.itemData.filter(item=>{
                if(this.search_price1 === "" && this.search_price2 === ""){
                    return item;
                }else if(this.search_price1 !== "" && this.search_price2 !== "" &&
                    item.price >= this.search_price1 && item.price <= this.search_price2){
                    return item;
                }else if(this.search_price1 === "" && item.price <= this.search_price2){
                    return item;
                }else if(this.search_price2 === "" && item.price >= this.search_price1){
                    return item;
                }
            })
        },
        reset(){
            this.search_name = "";
            this.search_price1 = "";
            this.search_price2 = "";
            this.itemData = this.allItems;
        }
    }
}
</script>
