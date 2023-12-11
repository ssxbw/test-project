/**
* 基础菜单 商品管理
*/
<template>
  <div class="hotel">
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>酒店管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="搜索：">
        <el-input size="small" v-model="formInline.name" placeholder="输入酒店名称"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
        <el-button size="small" type="primary" icon="el-icon-plus" @click="handleEdit()">添加</el-button>
      </el-form-item>
    </el-form>
    <!--列表-->
    <el-table size="small" :data="listData" @selection-change="handleSelectionChange" highlight-current-row
              v-loading="loading" border element-loading-text="拼命加载中" style="width: 100%;">
      <el-table-column align="center" type="selection" width="60">
      </el-table-column>
      <el-table-column prop="hotelId"   width="100"  >
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">编号</span>
        </template>
      </el-table-column>
      <el-table-column prop="name" width="200" id="2">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">酒店名称</span>
        </template>
      </el-table-column>
      <el-table-column prop="address" width="300" id="3">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">酒店地址</span>
        </template>
      </el-table-column>
      <el-table-column sortable prop="rating" width="80" id="4">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">评分</span>
        </template>
      </el-table-column>
      <el-table-column sortable prop="star" label="星级" width="150" id="5">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">星级</span>
        </template>
        <template slot-scope="star">
          <el-rate v-model="star.row.star" disabled></el-rate>
        </template>
      </el-table-column>
      <el-table-column sortable prop="discount" width="100">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">是否折扣</span>
        </template>
      </el-table-column>
      <el-table-column sortable prop="minPrice" width="100">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">最低价格</span>
        </template>
      </el-table-column>
      <el-table-column prop="images" align="center" width="150">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">配图</span>
        </template>
        <template slot-scope="scope">
          <el-image v-for="(item, index) in scope.row.images" :key="index" :src="item" :preview-src-list="[item]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column align="center" min-width="300">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">操作</span>
        </template>
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" @click="findEditRoom(scope.$index, scope.row)">查看房型</el-button>
          <el-button size="mini" type="danger" @click="deleteHotel(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <!-- <Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination> -->
    <!-- 编辑界面 -->
    <el-dialog :title="title" :visible.sync="editFormVisible" width="50%" @click="closeDialog">
      <el-form label-width="120px" :model="editForm" :rules="rules" ref="editForm">
        <el-form-item label="酒店名称" prop="name">
          <el-input size="small" v-model="editForm.name" auto-complete="off" placeholder="请输入酒店名称"></el-input>
        </el-form-item>
        <el-form-item label="酒店地址" prop="address">
          <el-input size="small" v-model="editForm.address" auto-complete="off" placeholder="请输入酒店地址"></el-input>
        </el-form-item>
        <el-form-item label="评分" prop="rating">
          <el-input-number v-model="editForm.rating" :min="1" :max="5" label="评分"></el-input-number>

        </el-form-item>
        <el-form-item label="折扣" prop="discount">
          <el-select size="small" v-model="editForm.discount">
            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="星级" prop="star">
          <el-rate v-model="editForm.star"></el-rate>
        </el-form-item>
        <el-form-item label="最低价格" prop="minPrice">
          <el-input size="small" v-model="editForm.minPrice" auto-complete="off" placeholder="请输入最低价格"></el-input>
        </el-form-item>
        <el-form-item label="酒店图片" prop="pdfImage">
          <el-upload ref="upload1" :action="uploadFileUrl" accept=".jpg, .png, jpeg" list-type="picture-card"
                     :on-preview="handlePictureCardPreview" :on-remove="handleRemove" :on-success="handSuccess"
                     :before-remove="beforeRemove" :http-request="selectPicUpload">

            <i class="el-icon-plus"></i>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="closeDialog">取消</el-button>
        <el-button size="small" type="primary" :loading="loading" class="title" @click="submitForm('editForm')">保存
        </el-button>
      </div>
    </el-dialog>

    <el-dialog title="房型列表" :visible.sync="dialogTableVisible" width="60%">
      <el-button size="mini" @click="editRoom()">添加</el-button>
      <el-table :data="roomListData">
        <el-table-column property="roomId" width="100" id="1" class="niubi">
          <template slot="header" slot-scope="scope">
            <span style="color: #1f2d3d">编号</span>
          </template>
        </el-table-column>
        <el-table-column property="hotelId" width="100">
          <template slot="header" slot-scope="scope">
            <span style="color: #1f2d3d">酒店编号</span>
          </template>
        </el-table-column>
        <el-table-column property="type" width="150">
          <template slot="header" slot-scope="scope">
            <span style="color: #1f2d3d">房型</span>
          </template>
        </el-table-column>
        <el-table-column property="price">
          <template slot="header" slot-scope="scope">
            <span style="color: #1f2d3d">价格</span>
          </template>
        </el-table-column>
        <el-table-column property="discount">
          <template slot="header" slot-scope="scope">
            <span style="color: #1f2d3d">折扣</span>
          </template>
        </el-table-column>
        <el-table-column property="area">
          <template slot="header" slot-scope="scope">
            <span style="color: #1f2d3d">面积</span>
          </template>
        </el-table-column>
        <el-table-column prop="roomimages"  align="center" width="150">
          <template slot="header" slot-scope="scope">
            <span style="color: #1f2d3d">配图</span>
          </template>
          <template slot-scope="scope">
            <el-image v-for="(item, index) in scope.row.roomimages" :key="index" :src="item" :preview-src-list="[item]">
            </el-image>
          </template>
        </el-table-column>
        <el-table-column align="center" min-width="300">
          <template slot="header" slot-scope="scope">
            <span style="color: #1f2d3d">操作</span>
          </template>
          <template slot-scope="scope">
            <el-button size="mini" @click="editRoom(scope.$index, scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="deleteRoom(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

    <el-drawer
        :title="roomtitle"
        :visible.sync="dialogroom"
        direction="rtl"
        custom-class="demo-drawer"
        ref="drawer"
    >
      <div class="demo-drawer__content">
        <el-form :model="roomForm" ref="roomForm">
          <el-form-item label="房型" prop="type" :label-width="formLabelWidth">
            <el-input v-model="roomForm.type" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="酒店编号" hidden prop="hotelId" :label-width="formLabelWidth">
            <el-input v-model="roomForm.hotelId" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="价格"  prop="price" :label-width="formLabelWidth">
            <el-input v-model="roomForm.price" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="折扣"  prop="discount" :label-width="formLabelWidth">
            <el-input v-model="roomForm.discount" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="面积"  prop="area" :label-width="formLabelWidth">
            <el-input v-model="roomForm.area" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="房型图片" prop="roomImage">
            <el-upload ref="upload2" :action="uploadFileUrl" accept=".jpg, .png, jpeg" list-type="picture-card"
                       :on-preview="handlePictureCardPreview" :on-remove="handleRemove" :on-success="handSuccess"
                       :before-remove="beforeRoomRemove" :http-request="selectRoomPicUpload">
              <i class="el-icon-plus"></i>
            </el-upload>
            <el-dialog :visible.sync="dialogVisible">
              <img width="100%" :src="dialogImageUrl" alt="">
            </el-dialog>
          </el-form-item>
        </el-form>
        <div class="demo-drawer__footer">
          <el-button @click="cancelForm">取 消</el-button>
          <el-button type="primary" @click="submitRoomForm('roomForm')">确 定</el-button>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import Pagination from '../../../components/Pagination';
import axios from "axios";
export default {
  data() {
    return {
      roomHotelId:'',
      formLabelWidth: '100px',
      dialogroom: false,
      dialogTableVisible: false, //房间弹窗
      uploadFileUrl: this.HOST + "/hotel/upload", // 上传文件服务器地址
      fileIds: [],
      fileRoomIds: [],
      options: [{
        value: 'Y',
        label: '有折扣'
      }, {
        value: 'N',
        label: '无折扣'
      }],
      allmenu: [],
      data: [],
      nshow: true, //switch开启
      fshow: false, //switch关闭
      loading: false, //是显示加载
      editFormVisible: false, //控制编辑页面显示与隐藏
      title: '添加',
      editForm: {
        name: '',
        address: '',
        rating: 0,
        discount: '0',
        minPrice: 0,
        star: 0,
        // fileIds: ''
      },
      roomForm: {
        roomId: '',
        hotelId: '',
        type: '',
        price: 0,
        discount: '0',
        area: 0,
      },
      // rules表单验证
      rules: {
        name: [{
          required: true,
          message: '请输入酒店名称',
          trigger: 'blur'
        }],
        address: [{
          required: true,
          message: '请输入酒店地址',
          trigger: 'blur'
        }],
        // rating: [{
        //   required: true,
        //   message: '请点击选择评分',
        //   trigger: 'blur'
        // }],
        minPrice: [{
          required: true,
          message: '请输入最低价格',
          trigger: 'blur'
        }]
      },
      formInline: {
        page: 1,
        limit: 10,
        varLable: '',
        varName: ''
      },
      // 删除部门
      seletedata: {
        ids: '',
        token: localStorage.getItem('logintoken')
      },
      userparm: [], //搜索权限
      listData: [], //酒店数据
      roomListData: [], //房间数据
      // 分页参数
      pageparm: {
        currentPage: 1,
        pageSize: 10,
        total: 10
      }
    }
  },
  // 注册组件
  components: {
    Pagination
  },
  /**
   * 数据发生改变
   */

  /**
   * 创建完毕
   */
  created() {
    this.getdata(this.formInline)
  },

  /**
   * 里面的方法只有被调用才会执行
   */
  methods: {
    // 获取酒店列表
    getdata(parameter) {

      this.loading = true;
      var that = this;
      that.listData = [];
      var url = '';
      if (that.formInline.name == "" || that.formInline.name == null || that.formInline.name == undefined) {
        url = this.HOST + '/hotel/list';
      } else {
        url = this.HOST + '/hotel/list?name=' + that.formInline.name
      }
      axios.get(url).then(res => {
        for (var i = 0; i < res.data.list.length; i++) {

          var array = [];
          var strs = res.data.list[i].fileIds;
          if (strs != "" && strs != null && strs != undefined) {
            array = strs.split(',');
          }
          var html = {
            hotelId: res.data.list[i].hotelId,
            name: res.data.list[i].name,
            address: res.data.list[i].address,
            discount: res.data.list[i].discount,
            rating: res.data.list[i].rating,
            minPrice: res.data.list[i].minPrice,
            star: res.data.list[i].star,
            images: array,
            // names: res.data.list[i].names,
            // urls: res.data.list[i].fileIds
          };
          that.listData.push(html);
        }
        this.loading = false;
      });

    },
    // 分页插件事件
    callFather(parm) {
      this.formInline.page = parm.currentPage
      this.formInline.limit = parm.pageSize
      this.getdata(this.formInline)
    },
    // 搜索事件
    search() {
      this.getdata(this.formInline)
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.hotelId);
      console.log("this.ids:" + this.ids);
    },
    //显示编辑界面
    handleEdit: function(index, row) {
      this.editFormVisible = true

      if (row != undefined && row != 'undefined') {
        this.title = '修改'
        this.editForm.hotelId = row.hotelId
        this.editForm.name = row.name
        this.editForm.address = row.address
        this.editForm.star = row.star
        this.editForm.discount = row.discount
        this.editForm.fileIds = row.fileIds
        this.editForm.rating = row.rating
        this.editForm.minPrice = row.minPrice
      } else {
        this.title = '添加'
        this.editForm.hotelId = ''
        this.editForm.name = ''
        this.editForm.address = ''
        this.editForm.star = ''
        this.editForm.discount = ''
        this.editForm.fileIds = ''
        this.editForm.rating = ''
        this.editForm.minPrice = 0
      }
    },  //显示房间编辑界面
    editRoom: function(index, row) {
      this.dialogroom = true;

      if (row != undefined && row != 'undefined') {
        this.roomtitle = '修改'
        this.roomForm.roomId = row.roomId
        this.roomForm.hotelId = row.hotelId
        this.roomForm.type = row.type
        this.roomForm.price = row.price
        this.roomForm.discount = row.discount
        this.roomForm.area = row.area

      } else {
        this.title = '添加'
        this.roomForm.hotelId = ''
        this.roomForm.type = ''
        this.roomForm.price = 0
        this.roomForm.discount = ''
        this.roomForm.area = 0
      }
    },
    findEditRoom: function(index, row) {
      this.dialogTableVisible = true;
      this.roomListData=[];
      axios.get(this.HOST + '/room/list?hotelId=' + row.hotelId).then(res => {
        for (var i = 0; i < res.data.list.length; i++) {
          var roomarray = [];
          var strs = res.data.list[i].fileIds;
          if (strs != "" && strs != null && strs != undefined) {
            roomarray = strs.split(',');
          }
          var html = {
            roomId: res.data.list[i].roomId,
            hotelId: res.data.list[i].hotelId,
            type: res.data.list[i].type,
            price: res.data.list[i].price,
            discount: res.data.list[i].discount,
            area: res.data.list[i].area,
            roomimages: roomarray
          };
          this.roomListData.push(html);
        }
        this.roomHotelId = row.hotelId;
        this.loading = false;
      });
    },
    // 编辑、增加页面保存方法
    submitForm(editData) {
      this.$refs[editData].validate(valid => {
        if (valid) {
          var formId = this.editForm.hotelId;
          console.log("formId:" + formId);
          var url = '';
          if (formId == "" || formId == null || formId == undefined) {
            url = this.HOST + '/hotel/add';
          } else {
            url = this.HOST + '/hotel/edit';
          }
          //拼接图片id
          this.editForm.fileIds = this.fileIds.join(",");
          console.log(""+this.editForm.fileIds)
          axios({
            method: 'post',
            url: url,
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            },
            data: this.editForm,
            traditional: true,
            transformRequest: [
              function(data) {
                let ret = ''
                for (let it in data) {
                  ret +=
                      encodeURIComponent(it) +
                      '=' +
                      encodeURIComponent(data[it]) +
                      '&'
                }
                return ret
              }
            ]
          }).then(res => {
            this.editFormVisible = false;
            this.loading = false;
            this.fileIds = [];
            this.$refs.upload1.clearFiles();
            this.getdata(this.formInline)
            this.$message({
              type: 'success',
              message: '保存成功！'
            })
          });

        } else {
          return false
        }
      })
    },
    // 房型编辑、增加页面保存方法
    submitRoomForm(editData) {
      this.$refs[editData].validate(valid => {
        if (valid) {
          var formId = this.roomForm.roomId;
          console.log("formId:" + formId);
          var url = '';
          if (formId == "" || formId == null || formId == undefined) {
            url = this.HOST + '/room/add';
          } else {
            url = this.HOST + '/room/edit';
          }
          console.log("this.fileRoomIds:"+this.fileRoomIds)
          //拼接图片id
          this.roomForm.fileIds = this.fileRoomIds.join(",");
          this.roomForm.hotelId = this.roomHotelId;
          axios({
            method: 'post',
            url: url,
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            },
            data: this.roomForm,
            traditional: true,
            transformRequest: [
              function(data) {
                let ret = ''
                for (let it in data) {
                  ret +=
                      encodeURIComponent(it) +
                      '=' +
                      encodeURIComponent(data[it]) +
                      '&'
                }
                return ret
              }
            ]
          }).then(res => {
            this.fileRoomIds = [];
            this.$refs.upload2.clearFiles();
            this.dialogroom = false;
            // this.dialogTableVisible = false;
            this.roomListData = [];
            axios.get(this.HOST + '/room/list?hotelId=' + this.roomHotelId).then(res => {
              for (var i = 0; i < res.data.list.length; i++) {
                var roomarray = [];
                var strs = res.data.list[i].fileIds;
                if (strs != "" && strs != null && strs != undefined) {
                  roomarray = strs.split(',');
                }
                var html = {
                  roomId: res.data.list[i].roomId,
                  hotelId: res.data.list[i].hotelId,
                  type: res.data.list[i].type,
                  price: res.data.list[i].price,
                  discount: res.data.list[i].discount,
                  area: res.data.list[i].area,
                  roomimages: roomarray
                };
                this.roomListData.push(html);
              }
            });
            this.$message({
              type: 'success',
              message: '保存成功！'
            })
          });

        } else {
          return false
        }
      })
    },
    // 删除
    deleteHotel(index, row) {
      this.$confirm('确定要删除吗?', '信息', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
          .then(() => {

            axios({
              method: 'post',
              url: this.HOST + '/hotel/delete',
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
              },
              data: {
                hotelId: row.hotelId
              },
              traditional: true,
              transformRequest: [
                function(data) {
                  let ret = ''
                  for (let it in data) {
                    ret +=
                        encodeURIComponent(it) +
                        '=' +
                        encodeURIComponent(data[it]) +
                        '&'
                  }
                  return ret
                }
              ]
            }).then(res => {
              this.dialogTableVisible = false;
              this.$message({
                type: 'success',
                message: '删除成功！'
              })
            });

          })
          .catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            })
          })
    },
    // 删除
    deleteRoom(index, row) {
      this.$confirm('确定要删除吗?', '信息', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
          .then(() => {
            axios({
              method: 'post',
              url: this.HOST + '/room/delete',
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
              },
              data: {
                roomId: row.roomId
              },
              traditional: true,
              transformRequest: [
                function(data) {
                  let ret = ''
                  for (let it in data) {
                    ret +=
                        encodeURIComponent(it) +
                        '=' +
                        encodeURIComponent(data[it]) +
                        '&'
                  }
                  return ret
                }
              ]
            }).then(res => {
              this.roomListData = [];
              axios.get(this.HOST + '/room/list?hotelId=' + this.roomHotelId).then(res => {
                for (var i = 0; i < res.data.list.length; i++) {
                  var roomarray = [];
                  var strs = res.data.list[i].fileIds;
                  if (strs != "" && strs != null && strs != undefined) {
                    roomarray = strs.split(',');
                  }
                  var html = {
                    roomId: res.data.list[i].roomId,
                    hotelId: res.data.list[i].hotelId,
                    type: res.data.list[i].type,
                    price: res.data.list[i].price,
                    discount: res.data.list[i].discount,
                    area: res.data.list[i].area,
                    roomimages: roomarray
                  };
                  this.roomListData.push(html);
                }
              });
              this.$message({
                type: 'success',
                message: '删除成功！'
              })
            });

          })
          .catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            })
          })
    },
    // 关闭编辑、增加弹出框
    closeDialog() {
      this.editFormVisible = false
    },
    // 上传图片
    selectPicUpload(param) {
      let fd = new FormData(); //参数的格式是formData格式的
      fd.append("file", param.file); //参数
      fd.append("path", "train");
      console.log(fd.get("file"))
      this.$axios.post(this.HOST + "/hotel/upload", fd).then((res) => {
        console.log(res.data.msg);
        if (res.data.code == 1) {
          this.fileIds.push(res.data.data.fileId);
          this.$message(res.data.msg);
        } else {
          this.$message(res.data.msg);
        }
      });
    },
    // 房型上传图片
    selectRoomPicUpload(obj) {
      let fd = new FormData(); //参数的格式是formData格式的
      fd.append("file", obj.file); //参数
      fd.append("path", "train");
      this.$axios.post(this.HOST + "/hotel/upload", fd).then((res) => {
        console.log(res.data.msg);
        if (res.data.code == 1) {
          this.fileRoomIds.push(res.data.data.fileId);
          this.$message(res.data.msg);
        } else {
          this.$message(res.data.msg);
        }
      });
    },
    handSuccess(response, file, fileList) {},
    handleRemove(file, fileList) {
      console.log("fileIds:" + this.fileIds)

    },
    beforeRemove(file, fileList) {
      var index = fileList.findIndex((item) => item.url == file.url);
      this.fileIds.splice(index, 1);
    },
    beforeRoomRemove(file, fileList) {
      var index = fileList.findIndex((item) => item.url == file.url);
      this.fileRoomIds.splice(index, 1);
    },
    handlePictureCardPreview() {
      console.log(1)
    },
    cancelForm() {
      this.dialogroom = false;
    }


  }
}
</script>

<style scoped>
.user-search {
  margin-top: 20px;
}

.userRole {
  width: 100%;
}
.el-date-table th,
.el-date-table td,
.hotel th,
.hotel td {
  border:none;
  padding-left: 10px;
  padding-right: 10px;
  text-align: center;
}
.niubi {
  color: #000;
}
.hotel, el-table-column {
  color: #000000;
  padding: 12px;
  text-align: left;
}
</style>
