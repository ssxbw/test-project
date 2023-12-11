<template>
    <div>
        <el-breadcrumb separator="/" class="mianbaoxie">
            <el-breadcrumb-item :to="{ path: '/system' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>用户信息</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card class="box-card">
            <el-table :show-header="status" :data="list" border stripe height="50" class="table">
                <el-table-column label="id" prop="id"></el-table-column>
                <el-table-column label="用户名" prop="username"></el-table-column>
                <el-table-column label="类型" prop="type"></el-table-column>
                <el-table-column label="电话" prop="phone"></el-table-column>
                <el-table-column label="邮箱" prop="email"></el-table-column>
                <el-table-column label="VIP点数" prop="vipValue"></el-table-column>
                <el-table-column label="状态" prop="isBlocked"></el-table-column>
                <el-table-column label="结束时间" prop="endDate"></el-table-column>
                <el-table-column label="封禁" prop="fengjing">
                </el-table-column>
            </el-table>
            <el-table :show-header="status" :data="userlist" border stripe height="700" class="table">
                <el-table-column label="id" prop="id"></el-table-column>
                <el-table-column label="用户名" prop="username"></el-table-column>
                <el-table-column label="类型" prop="type"></el-table-column>
                <el-table-column label="电话" prop="phone"></el-table-column>
                <el-table-column label="邮箱" prop="email"></el-table-column>
                <el-table-column label="VIP点数" prop="vipValue"></el-table-column>
                <el-table-column label="状态" :filters="[{ text: '封禁', value: true }, { text: '正常', value: false }]" :filter-method="filterBlocked">
                    <template slot-scope="scope">
                        <span v-if="scope.row.isBlocked">禁用</span>
                        <span v-else>正常</span>
                    </template>
                </el-table-column>
                <el-table-column label="结束时间" prop="endDate"></el-table-column>
                <el-table-column label="封禁">
                    <template slot-scope="scope">
                        <el-tooltip effect="dark" content="封禁用户" placement="top" :enterable="false">
                            <el-button type="warning" icon="el-icon-setting" size="mini" @click="arbitrate(scope.row.id)"></el-button>
                        </el-tooltip>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
        <el-dialog
            title="封禁用户"
            :visible.sync="arbitrateVisible"
            width="50%"
            @close="editidClosed">
            <el-form :model="editid" :rules="editidRules" ref="editidRef" label-width="70px" >
                <el-form-item label="用户名" >
                    <el-input v-model="editid" disabled></el-input>
                </el-form-item>
                <el-form-item label="封禁时间" prop="time" >
                    <el-input v-model="edittime"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
    <el-button @click="arbitrateVisible = false">取 消</el-button>
    <el-button type="primary" @click="editidInfo">确 定</el-button>
  </span>
        </el-dialog>
    </div>

</template>
<script>
import axios from "axios";
export default {

    data(){
        return {
            status:false,
            userlist: [],
            list:[{ id: "id", username: '用户名', type: '类型', phone: '电话', email: '邮箱', vipValue: "VIP等级", isBlocked: "状态", endDate: '结束时间' ,fengjing:'封禁'}],

            arbitrateVisible:false,
            editid:'',
            edittime:'',
            editidRules:{

                time:[
                    {
                        required:true,message:'请输入封禁时间（数字）单位为小时',trigger:'blur'
                    },
                    {
                        type: 'number',
                        message: '封禁时间必须为数字',
                        trigger: 'blur'

                    }
                ]
            }
        }
    },
    // data() {
    //     return {
    //
    //     }
    // },



    created() {

        this.getUserList()
    },

    methods:{
        filterBlocked(value, row) {
            return value === row.isBlocked;
        },
        getUserList(){
            axios.defaults.baseURL='http://localhost:80'
            axios.post('/admin/userlist',{

                "name":'123',
            }).then(response=>{
                if(response.data.code!==1) {
                    return this.$message.error(response.data.msg)
                }

                else{
                    this.userlist=response.data.data
                }



            },error=>{
                console.log('错误',error.message)
            })
        },
        arbitrate(id) {

            this.editid=id
            this.arbitrateVisible=true
        },
        editidClosed(){
            this.$refs.editidRef.resetFields()
        },
        editidInfo(){
            let time;
            time=this.edittime*60*60*1000
            axios.defaults.baseURL='http://localhost:80'
            axios.post('/admin/arbitrate',{

                "userid":this.editid,
                "arbitrationtime":time,
            }).then(response=>{
                if(response.data.code!==1) {
                    return this.$message.error(response.data.msg)
                }

                else{
                    this.arbitrateVisible = false
                    this.getUserList()
                    return this.$message.success(response.data.msg)
                }



            },error=>{
                console.log('错误',error.message)
            })
        },

    },
    filters: {
        isBlockedFilter(value) {
            if (value) {
                return '正常';
            } else {
                return '被封禁';
            }
        }
    }
}
</script>
<style>
.mianbaoxie{
    margin-bottom: 15px;
}
.box-card{
    box-shadow: 0 1px 1px rgba(0,0,0,0.15);
}
.table{
    margin-top: 15px;
}
</style>