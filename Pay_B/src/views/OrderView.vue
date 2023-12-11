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
            @click="isLogoutModalVisible = true">退出系统</button>
        </header>
        <SideBar></SideBar>
        <div>
            <div class="search" style="margin-left: 20%">
                <el-main></el-main>
                订单状态筛选
                <el-dropdown @command="handleCommand">
                  <el-button type="primary" style="margin-left: 10%"> {{values}}
                      <i class="el-icon-arrow-down el-icon--right"></i></el-button>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item command="-1">全部</el-dropdown-item>
                        <el-dropdown-item command="2">未付款</el-dropdown-item>
                        <el-dropdown-item command="4">待处理</el-dropdown-item>
                        <el-dropdown-item command="5">已发货</el-dropdown-item>
                        <el-dropdown-item command="6">运输中</el-dropdown-item>
                        <el-dropdown-item command="7">已收货</el-dropdown-item>
                        <el-dropdown-item command="8">订单成功</el-dropdown-item>
                        <el-dropdown-item command="9" divided>取消订单</el-dropdown-item>
                        <el-dropdown-item command="10">申请退款</el-dropdown-item>
                        <el-dropdown-item command="0">订单失败</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
                <el-date-picker
                    v-model="time"
                    type="daterange"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                    style="margin-left: 6%">
                </el-date-picker>
                <el-button type="small" style="margin-left: 15px" @click="findTime()">查询</el-button>
                <el-button type="small" style="margin-left: 15px" @click="reset()">重置</el-button>
                <el-button type="small" style="margin-left: 20px">
                    订单总数: {{tableData.length}}
                </el-button>
            </div>

            <el-table
                :data="tableData"
                stripe
                style="width: 80%; margin: 0 auto;"
                class="recon"
                @sort-change="onSortChange">
                <el-table-column type="expand">
                    <template slot-scope="props">
                        <el-timeline style="margin-top: 20px">
                            <el-timeline-item
                                v-for="(transaction, index) in props.row.transactionFlowList"
                                :key="index"
                                :timestamp="transaction.createTime">
                                {{transaction.orderStateString}}
                            </el-timeline-item>
                        </el-timeline>
                        <div class="buttons" style="margin-bottom: 20px">
                          <el-button type="primary" style="margin-left: 50px" v-if = "usertype === '0'" :disabled="props.row.orderState === 0 || props.row.orderState === 2" @click="open(props.row)">退款</el-button>
                          <el-button type="primary" style="margin-left: 20px" v-if = "usertype === '0'" @click="complaintBox = true" >投诉</el-button>
                          <el-button type="primary" style="margin-left: 20px"  @click="send(props.row)" v-if = "usertype === '1'" :disabled="props.row.orderState >= 5 || props.row.orderState <= 2">确认发货</el-button>
                          <el-button type="primary" style="margin-left: 20px" @click="receive(props.row)" v-if = "usertype === '0'" :disabled="props.row.orderState < 5 || props.row.orderState >= 7">确认收货</el-button>
                        </div>
                        <el-dialog title="投诉申请" :visible.sync="complaintBox">
                            <el-form :model="form">
                                <el-form-item label="投诉内容" :label-width="formLabelWidth">
                                    <el-input
                                        v-model="form.content"
                                        autocomplete="off"
                                        clearable>
                                    </el-input>
                                </el-form-item>
                            </el-form>
                            <div slot="footer" class="dialog-footer">
                                <el-button :plain="true" @click="open2()">取 消</el-button>
                                <el-button :plain="true" type="primary"  @click="open1(props.row)">确定</el-button>
                            </div>
                        </el-dialog>
                    </template>
                </el-table-column>
                <el-table-column
                    label="订单编号"
                    prop="orderId"
                    width="100%"
                    sortable="custom"
                    :sort-orders="['ascending','descending']">
                </el-table-column>
                <el-table-column
                    label="买方id"
                    prop="buyerId"
                    width="100%"
                    sortable="custom"
                    :sort-orders="['ascending','descending']">
                </el-table-column>
                <el-table-column
                    label="买方名"
                    prop="buyer_name"
                    width="100%">
                </el-table-column>
                <el-table-column
                    label="卖方id"
                    prop="sellerId"
                    width="100%"
                    sortable="custom">
                </el-table-column>
                <el-table-column
                    label="卖方名"
                    prop="seller_name"
                    width="100%"
                    :sort-orders="['ascending','descending']">
                </el-table-column>
                <el-table-column
                    label="商品id"
                    prop="itemId"
                    width="100%"
                    sortable="custom"
                    :sort-orders="['ascending','descending']">
                </el-table-column>
                <el-table-column
                    label="金额"
                    prop="totalPrice"
                    width="100"
                    sortable="custom"
                    :sort-orders="['ascending','descending']">
                </el-table-column>
                <el-table-column
                    label="订单状态"
                    prop="orderStateString"
                    width="100"
                    sortable="custom">
                </el-table-column>
                <el-table-column
                    label="投诉记录"
                    prop="complaintRecord">
                </el-table-column>
            </el-table>
<!--            <el-col :span="15"><div class="grid-content bg-purple-light" style="margin-left: 4%">订单总数: {{tableData.length}}</div></el-col>-->
        </div>
        <div class="modal" :class="{ 'show-modal': isLogoutModalVisible }" style="width: 250px; height: 180px;">
            <!-- 弹框内容 -->
            <h3 class="title">是否退出系统</h3>
            <div style="display:flex; justify-content: center; align-items: center; width:98%;">
                <button style="cursor: pointer;" @click="logout">确认</button>
                <button style="cursor: pointer; margin-left: 10px;" @click="isLogoutModalVisible = false">取消</button>
            </div>
        </div>

        <div class="overlay" :class="{ 'show-overlay': isLogoutModalVisible }"></div>

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

</style>

<script>
import axios from "axios";

export default {
    data() {
        return {
            allOrder:[],
            tableData: [],
            complaintBox: false,
            values: "",
            form: {
                content: ''
            },
            formLabelWidth: '100px',
            dataList: [],

            time: "",
            commands: "",
            usertype: '',
            url: "http://localhost",
            isLogoutModalVisible: false,
        }
    },
    mounted(){
        this.init();
        this.values = "全部"
        this.commands = -1;
    },
    created() {
        this.load();
    },
    methods: {
        init(){
            axios.get('http://localhost/order/type').then(res => {
                console.log(res.data);
                if (String(res.data.code) === '0') {
                    this.$notify.error({
                        title: '错误',
                        message: "非法访问！"
                    });
                    //this.logout();
                } else {
                    this.usertype = res.data.type;
                    console.log(this.type)
                }
            })
        },
        load() {
            axios.get('http://localhost/order').then(res => {
                console.log(res.data);
                if (String(res.data.code) === '0') {
                    this.$notify.error({
                        title: '错误',
                        message: res.data.msg
                    });
                } else {
                    this.allOrder = res.data.data;
                    this.tableData = res.data.data;
                    console.log(this.usertype)
                }
            })
        },
        send(row) {
            this.$confirm('是否确认发货?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                const Order = {
                    orderId: row.orderId,
                    buyerId: row.buyerId,
                    sellerId: row.sellerId,
                    totalPrice: row.totalPrice,
                    itemId: row.itemId,
                    orderState: row.orderState,
                    complaintRecord: row.complaintRecord
                }
                axios.put('http://localhost/order/ship', Order)
                    .then((res) => {
                        console.log(res);
                        if (String(res.data.code) === '1') {
                            this.$notify({
                                title: '成功',
                                message: res.data.msg,
                                type: 'success'
                            });
                            this.load();
                        } else {
                            this.$notify.error({
                                title: '错误',
                                message: res.data.msg
                            });
                        }
                    })
                    .catch((error) => {
                        console.log(error);
                        alert('输入格式错误');
                    });
            })
                .catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消发货'
                    });
                });
        },
        receive(row) {
            this.$confirm('是否确认收货?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                const reOrder = {
                    orderId: row.orderId,
                    buyerId: row.buyerId,
                    sellerId: row.sellerId,
                    totalPrice: row.totalPrice,
                    itemId: row.itemId,
                    orderState: row.orderState,
                    complaintRecord: row.complaintRecord
                }
                axios.put('http://localhost/order/received', reOrder)
                    .then((res) => {
                        console.log(res);
                        if (String(res.data.code) === '1') {
                            this.$notify({
                                title: '成功',
                                message: res.data.msg,
                                type: 'success'
                            });
                            this.load();
                        } else {
                            this.$notify.error({
                                title: '错误',
                                message: res.data.msg
                            });
                        }
                    })
                    .catch((error) => {
                        console.log(error);
                        alert('错误');
                    });
            })
                .catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消发货'
                    });
                });
        },
        open(row) {
            this.$confirm('是否确认退款', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                axios.put('http://localhost/order/refund/' + row.orderId)
                    .then((res) => {
                        console.log(res);
                        if (String(res.data.code) === '1') {
                            this.$notify({
                                title: '成功',
                                message: res.data.msg,
                                type: 'success'
                            });
                            this.load();
                        } else {
                            this.$notify.error({
                                title: '错误',
                                message:  res.data.msg
                            });
                        }
                    })
                    .catch((error) => {
                        console.log(error);
                        alert('输入格式错误');
                    });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消退款'
                });
            });
        },
        open1(row) {
            this.complaintBox = false;
            const userOrder = {
                orderId: row.orderId,
                buyerId: row.buyerId,
                sellerId: row.sellerId,
                totalPrice: row.totalPrice,
                itemId: row.itemId,
                orderState: row.orderState,
                complaintRecord: this.form.content
            }
            axios.put('http://localhost/order/complaint', userOrder)
                .then((res) => {
                    console.log(res);
                    if (String(res.data.code) === '1') {
                        this.$notify({
                            title: '成功',
                            message:  res.data.msg,
                            type: 'success'
                        });
                        this.load();
                    } else {
                        this.$notify.error({
                            title: '错误',
                            message:  res.data.msg
                        });
                    }
                })
                .catch((error) => {
                    console.log(error);
                    alert('输入格式错误');
                });
        },
        open2() {
            this.complaintBox = false;
            this.$message({
                type: 'info',
                message: '已取消投诉'
            });
        },
        /**
         * 表格排序事件处理函数
         * @param {object} {column,prop,order} 列数据|排序字段|排序方式
         */
        onSortChange({prop, order}) {
            this.tableData.sort(this.compare(prop, order));
        },

        /**
         * 排序比较
         * @param {string} propertyName 排序的属性名
         * @param {string} sort ascending(升序)/descending(降序)
         * @return {function}
         */
        compare(propertyName, sort) {
            return function (obj1, obj2) {
                var value1 = obj1[propertyName]
                var value2 = obj2[propertyName]
                if (typeof value1 === 'string' && typeof value2 === 'string') {
                    const res = value1.localeCompare(value2, 'zh')
                    return sort === 'ascending' ? res : -res
                } else {
                    if (value1 <= value2) {
                        return sort === 'ascending' ? -1 : 1
                    } else if (value1 > value2) {
                        return sort === 'ascending' ? 1 : -1
                    }
                }
            }
        },
        // filterChange(filters) {
        //     // 判断是哪一列筛选
        //     if (filters.name) {
        //         //赋值这一列绑定的值
        //         this.dataList.name = filters.name
        //         this.dataList.value = filters.value
        //     }
        //     this.getTableList(); // 通过接口获取数据的方法
        // },
        findTime() {
            // this.loading  = true;
            if (null == this.time) {
                this.reset();
            }
            if (0 !== this.time.length) {
                // const SortMessage = {
                //     StartTime: (new Date(this.time[0]).getTime()).toString(),
                //     EndTime: (new Date(this.time[1]).getTime()).toString(),
                    // StartTime: this.time[0].toString(),
                    // EndTime: this.time[1].toString(),
                    // StartTime: this.msToDate(this.time[0]),
                    // EndTime: this.msToDate(this.time[1]),
                // }
                // console.log(SortMessage)
                axios.get('http://localhost/order/sort/'+ new Date(this.time[0]).getTime() +'/'+ new Date(this.time[1]).getTime()+'/' + this.commands)
                    .then((res) => {
                        console.log(res);
                        if (String(res.data.code) === '1') {
                            this.$notify({
                                title: '成功',
                                message:  res.data.msg,
                                type: 'success'
                            });
                            this.usertype = res.data.type;
                            this.tableData = res.data.data;
                        } else {
                            this.$notify.error({
                                title: '错误',
                                message:  res.data.msg
                            });
                        }
                    })
                    .catch((error) => {
                        console.log(error);
                        alert('输入格式错误');
                    });
            }
        },
        reset() {
            this.tableData = this.allOrder;
            this.commands = -1;
            this.values = "全部";
        },
        handleCommand(command) {
            var filterArr= [{text: '全部', value:'-1'}, {text: '订单失败', value: '0'}, {text: null, value:'1'},
                {text: '未付款', value: '2'}, {text: null, value:'3'}, {text: '待处理', value: '4'},
                {text: '已发货', value: '5'}, {text: '运输中', value: '6'},
                {text: '已收货', value: '7'}, {text: '订单成功', value: '8'},
                {text: '取消订单', value: '9'}, {text: '申请退款', value: '10'}];
            this.commands = command;
            this.values = filterArr[Number(command)+1].text;
            this.tableData = this.allOrder.filter(order=>{
                //console.log(order);
                if(this.commands === '-1' || order.orderStateString === this.values){
                    return order;
                }
                })
            //console.log(this.commands, this.values, this.tableData);

            // this.$message('click on item ' + this.commands);
        },
        msToDate(msec) {
            let datetime = new Date(msec);
            let year = datetime.getFullYear();
            let month = datetime.getMonth();
            let date = datetime.getDate();
            let hour = datetime.getHours();
            let minute = datetime.getMinutes();
            let second = datetime.getSeconds();

            let result1 = year +
                '-' +
                ((month + 1) >= 10 ? (month + 1) : '0' + (month + 1)) +
                '-' +
                ((date + 1) < 10 ? '0' + date : date) +
                ' ' +
                ((hour + 1) < 10 ? '0' + hour : hour) +
                ':' +
                ((minute + 1) < 10 ? '0' + minute : minute) +
                ':' +
                ((second + 1) < 10 ? '0' + second : second);

            let result2 = year +
                '-' +
                ((month + 1) >= 10 ? (month + 1) : '0' + (month + 1)) +
                '-' +
                ((date + 1) < 10 ? '0' + date : date);

            let result = {
                hasTime: result1,
                withoutTime: result2
            };
            return result;
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
    }
}

</script>
<script setup>
import IconView from "@/components/Icon.vue";
import SideBar from "@/components/SideBar.vue";
</script>