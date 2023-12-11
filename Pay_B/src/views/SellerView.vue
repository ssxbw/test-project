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
            <div style="margin-left: 5%">
                <el-main></el-main>
                <el-button type="primary" @click="dialogFormVisible = true, resetForm()" icon="el-icon-circle-plus-outline" style="margin-bottom: 20px; margin-left: 4%">添加商品</el-button>
                <el-dialog title="添加商品" :visible.sync="dialogFormVisible">
                    <el-form :model="insertForm">
                        <el-form-item label="商品名" :label-width="formLabelWidth">
                            <el-input v-model="insertForm.name" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="价格" :label-width="formLabelWidth">
                            <el-input v-model="insertForm.price" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="简介" :label-width="formLabelWidth">
                            <el-input type="textarea" v-model="insertForm.itemDescription" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="库存" :label-width="formLabelWidth">
                            <el-input v-model.number="insertForm.count" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-upload class="upload"
                                   action="http://localhost/common/upload"
                                   :on-preview="handlePreview"
                                   :on-remove="handleRemove"
                                   :on-success="handleSuccess"
                                   :file-list="fileList"
                                   list-type="picture">
                            <el-button size="small" type="primary" style="margin-left: 50px; margin-top: 10px">点击上传图片</el-button>
                            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                        </el-upload>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="dialogFormVisible = false">取 消</el-button>
                        <el-button type="primary" @click="dialogFormVisible = false, insert()">确 定</el-button>
                    </div>
                </el-dialog>
                <el-table
                  :data="tableData"
                  stripe
                  height="800px"
                  border
                  style="width: 80%; margin: 0 auto;"
                  class="recon"
                  @sort-change="onSortChange"
                  el-table-column type="expand"
                  :default-sort = "{prop: 'itemId', order: 'ascending'}">
                    <el-table-column
                        fixed
                        prop="itemId"
                        label="编号"
                        sortable
                        width="100">
                    </el-table-column>
                    <el-table-column
                        prop="name"
                        label="商品名称"
                        width="200">
                    </el-table-column>
                    <el-table-column
                        prop="price"
                        label="价格"
                        sortable
                        width="100">
                    </el-table-column>
                    <el-table-column
                        prop="itemDescription"
                        label="商品简介">
                    </el-table-column>
                    <el-table-column
                        prop="count"
                        label="库存"
                        sortable
                        width="100">
                    </el-table-column>
                    <el-table-column
                        label="操作"
                        width="100">
                        <template slot-scope="scope">
                            <el-button @click="editFormVisible = true, fillEditForm(scope.row)" type="text" size="small">编辑</el-button>
                            <el-button type="text" size="small" @click="dele(scope.row)">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <el-dialog title="编辑信息" :visible.sync="editFormVisible">
                    <el-form :model="editForm">
                        <el-form-item label="商品名" :label-width="formLabelWidth">
                            <el-input v-model="editForm.name" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="价格" :label-width="formLabelWidth">
                            <el-input v-model="editForm.price" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="简介" :label-width="formLabelWidth">
                            <el-input type="textarea" v-model="editForm.itemDescription" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="库存" :label-width="formLabelWidth">
                            <el-input v-model.number="editForm.count" autocomplete="off"></el-input>
                        </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="editFormVisible = false">取 消</el-button>
                        <el-button type="primary" @click="editFormVisible = false, edit()">确 定</el-button>
                    </div>
                </el-dialog>
                <div class="modal" :class="{ 'show-modal': isLogoutModalVisible }" style="width: 250px; height: 180px;">
                    <!-- 弹框内容 -->
                    <h3 class="title">是否退出系统</h3>
                    <div style="display:flex; justify-content: center; align-items: center; width:98%;">
                        <button style="cursor: pointer;" @click="logout">确认</button>
                        <button style="cursor: pointer; margin-left: 10px;" @click="isLogoutModalVisible = false">取消</button>
                    </div>
                </div>

                <div class="overlay" :class="{ 'show-overlay': isLogoutModalVisible }"></div>
            </div>
        </div>
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
import IconView from "@/components/Icon.vue";
import SideBar from "@/components/SideBar.vue";
import router from "@/router";

export default {
    components: {SideBar, IconView},
    data(){
        return {
            dialogFormVisible: false,
            insertForm: {
                name: '',
                price:'',
                itemDescription:'',
                count: '',
                image: '',
            },
            editFormVisible: false,
            editForm: {
                name: '',
                price:'',
                itemDescription:'',
                count: '',
                itemId:''
            },
            formLabelWidth: '80px',
            tableData: [],
            isLogoutModalVisible:false,
            fileList:[],
            type:'',
        }
    },
    mounted(){
        axios.get('http://localhost/order/type').then(res => {
            if (String(res.data.code) === '0') {
                this.$notify.error({
                    title: '错误',
                    message: "非法访问！"
                });
                this.logout();
            } else {
                this.type = res.data.type;
                if(this.type !== "1"){
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
    methods: {
        load() {
            axios.get('http://localhost/item/seller').then(res => {
                console.log(res.data);
                if (String(res.data.code) === '0') {
                    this.$notify.error({
                        title: '错误',
                        message: res.data.msg
                    });
                } else {
                    this.tableData = res.data.data;
                }
            })
        },
        fillEditForm(row) {
            this.editForm = Object.assign({}, row);
        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePreview(file) {
            console.log(file);
        },
        handleSuccess(res){
            this.insertForm.image = '/common/download?name='+res.data;
        },
        resetForm(){
            this.insertForm.name= '';
            this.insertForm.price='';
            this.insertForm.itemDescription='';
            this.insertForm.count= '';
            this.insertForm.image= '';
            this.fileList = [];
        },
        insert() {
            const item = {
                name: this.insertForm.name,
                price: this.insertForm.price,
                itemDescription: this.insertForm.itemDescription,
                count: this.insertForm.count,
                image: this.insertForm.image
            }
            axios.post('http://localhost/item/seller', item)
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
        },
        edit() {
            const item = {
                itemId: this.editForm.itemId,
                sellerId: this.sellerId,
                name: this.editForm.name,
                price: this.editForm.price,
                itemDescription: this.editForm.itemDescription,
                count: this.editForm.count
            }
            axios.put('http://localhost/item/seller', item)
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
        },
        dele(row) {
            this.$confirm('此操作将永久删除该商品, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                axios.delete('http://localhost/item/seller/' + row.itemId)
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
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
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
