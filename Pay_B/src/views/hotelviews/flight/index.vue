/**
* 基础菜单 航班管理
*/
<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>航班管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="搜索：">
        <el-input size="small" v-model="formInline.company" placeholder="输入航班名称"></el-input>
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
      <el-table-column prop="flightId" label="编号" width="100">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">编号</span>
        </template>
      </el-table-column>
      <el-table-column prop="company" label="公司名称" width="100">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">公司名称</span>
        </template>
      </el-table-column>
      <el-table-column prop="flightNumber" label="航班号" width="100">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">航班号</span>
        </template>
      </el-table-column>
      <el-table-column prop="departureTime" label="出发时间" width="100">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">出发时间</span>
        </template>
      </el-table-column>
      <el-table-column sortable prop="arrivalTime" label="到达时间" width="100">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">到达时间</span>
        </template>
      </el-table-column>
      <el-table-column sortable prop="departureAirport" label="出发机场" width="150">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">出发机场</span>
        </template>
      </el-table-column>
      <el-table-column sortable prop="arrivalAirport" label="到达机场" width="150">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">到达机场</span>
        </template>
      </el-table-column>
      <el-table-column sortable prop="discount" label="是否折扣" width="100">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">是否折扣</span>
        </template>
      </el-table-column>
      <el-table-column sortable prop="minPrice" label="最低价格" width="100">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">最低价格</span>
        </template>
      </el-table-column>
      <el-table-column prop="images" label="图片" align="center" width="150">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">图片</span>
        </template>
        <template slot-scope="scope">
          <el-image v-for="(item, index) in scope.row.images" :key="index" :src="item" :preview-src-list="[item]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" min-width="300">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">操作</span>
        </template>
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" @click="findEditRoom(scope.$index, scope.row)">查看舱位</el-button>
          <el-button size="mini" type="danger" @click="deleteHotel(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <!-- <Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination> -->
    <!-- 编辑界面 -->
    <el-dialog :title="title" :visible.sync="editFormVisible" width="50%" @click="closeDialog">
      <el-form label-width="120px" :model="editForm" :rules="rules" ref="editForm">
        <el-form-item label="公司名称" prop="company">
          <el-input size="small" v-model="editForm.company" placeholder="公司名称"></el-input>
        </el-form-item>
        <el-form-item label="航班号" prop="flightNumber">
          <el-input size="small" v-model="editForm.flightNumber"  placeholder="航班号"></el-input>
        </el-form-item>
        <el-form-item label="出发时间:" prop="departureTime">
          <el-date-picker v-model="editForm.departureTime" type="datetime" @blur="$forceUpdate()" placeholder="选择时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="到达时间:" prop="arrivalTime">
          <el-date-picker v-model="editForm.arrivalTime" type="datetime" @blur="$forceUpdate()" placeholder="选择时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="出发机场" prop="departureAirport">
          <el-input size="small" v-model="editForm.departureAirport"  placeholder="请输入出发机场"></el-input>
        </el-form-item>
        <el-form-item label="到达机场" prop="arrivalAirport">
          <el-input size="small" v-model="editForm.arrivalAirport"  placeholder="请输入到达机场"></el-input>
        </el-form-item>
        <el-form-item label="折扣" prop="discount">
          <el-select size="small" v-model="editForm.discount">
            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="最低价格" prop="minPrice">
          <el-input size="small" v-model="editForm.minPrice"  placeholder="请输入最低价格"></el-input>
        </el-form-item>
        <el-form-item label="航班图片" prop="pdfImage">
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

    <el-dialog title="舱位列表" :visible.sync="dialogTableVisible" width="60%">
      <el-button size="mini" @click="editRoom()">添加</el-button>
      <el-table :data="roomListData">
        <el-table-column property="classId" label="编号" width="100">
          <template slot="header" slot-scope="scope">
            <span style="color: #1f2d3d">编号</span>
          </template>
        </el-table-column>
        <el-table-column property="flightId" label="航班编号" width="100">
          <template slot="header" slot-scope="scope">
            <span style="color: #1f2d3d">航班编号</span>
          </template>
        </el-table-column>
        <el-table-column property="cabin" label="客舱" width="150">
          <template slot="header" slot-scope="scope">
            <span style="color: #1f2d3d">客舱</span>
          </template>
        </el-table-column>
        <el-table-column property="price" label="价格">
          <template slot="header" slot-scope="scope">
            <span style="color: #1f2d3d">价格</span>
          </template>
        </el-table-column>
        <el-table-column property="discount" label="折扣">
          <template slot="header" slot-scope="scope">
            <span style="color: #1f2d3d">折扣</span>
          </template>
        </el-table-column>
        <el-table-column prop="roomimages" label="舱位图片" align="center" width="150">
          <template slot="header" slot-scope="scope">
            <span style="color: #1f2d3d">舱位图片</span>
          </template>
          <template slot-scope="scope">
            <el-image v-for="(item, index) in scope.row.roomimages" :key="index" :src="item" :preview-src-list="[item]">
            </el-image>
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作" min-width="300">
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
          <el-form-item label="客舱" prop="cabin" :label-width="formLabelWidth">
            <el-input v-model="roomForm.cabin" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="航班编号" hidden prop="flightId" :label-width="formLabelWidth">
            <el-input v-model="roomForm.flightId" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="价格"  prop="price" :label-width="formLabelWidth">
            <el-input v-model="roomForm.price" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="折扣"  prop="discount" :label-width="formLabelWidth">
            <el-input v-model="roomForm.discount" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="舱位图片" prop="roomImage">
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
      roomtitle:'',
      roomHotelId:'',
      formLabelWidth: '100px',
      dialogroom: false,
      dialogTableVisible: false, //舱位弹窗
      uploadFileUrl: this.HOST + "/hotel/upload", // 上传文件服务器地址
      fileIds: [],
      fileRoomIds: [],
      options: [{
        value: '0',
        label: '有折扣'
      }, {
        value: '1',
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
        flightNumber: '',
        departureTime: '',
        arrivalTime: '',
        departureAirport: '',
        arrivalAirport: '',
        minPrice: '',
        discount: '',
        company: ''
        // fileIds: ''
      },
      roomForm: {
        flightId: '',
        cabin: '',
        price: '',
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
    dateFormat(dateData) {
      var date = new Date(dateData)
      var y = date.getFullYear()
      var m = date.getMonth() + 1
      m = m < 10 ? ('0' + m) : m
      var d = date.getDate()
      d = d < 10 ? ('0' + d) : d
      const time = y + '-' + m + '-' + d
      return time
    },
    // 获取航班列表
    getdata(parameter) {

      this.loading = true;
      var that = this;
      that.listData = [];
      var url = '';
      if (that.formInline.company == "" || that.formInline.company == null || that.formInline.company == undefined) {
        url = this.HOST + '/flight/list';
      } else {
        url = this.HOST + '/flight/list?company=' + that.formInline.company
      }
      axios.get(url).then(res => {
        for (var i = 0; i < res.data.list.length; i++) {

          var array = [];
          var strs = res.data.list[i].fileIds;
          if (strs != "" && strs != null && strs != undefined) {
            array = strs.split(',');
          }
          var html = {
            flightId: res.data.list[i].flightId,
            flightNumber: res.data.list[i].flightNumber,
            departureTime: res.data.list[i].departureTime,
            arrivalTime: res.data.list[i].arrivalTime,
            departureAirport: res.data.list[i].departureAirport,
            arrivalAirport: res.data.list[i].arrivalAirport,
            minPrice: res.data.list[i].minPrice,
            discount: res.data.list[i].discount,
            company: res.data.list[i].company,
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
        this.editForm.flightId = row.flightId
        this.editForm.flightNumber = row.flightNumber
        this.editForm.departureTime = row.departureTime
        this.editForm.arrivalTime = row.arrivalTime
        this.editForm.departureAirport = row.departureAirport
        this.editForm.fileIds = row.fileIds
        this.editForm.arrivalAirport = row.arrivalAirport
        this.editForm.minPrice = row.minPrice
        this.editForm.discount = row.discount
        this.editForm.company = row.company
      } else {
        this.title = '添加'
        this.editForm.flightId = ''
        this.editForm.flightNumber = ''
        this.editForm.departureTime = ''
        this.editForm.arrivalTime = ''
        this.editForm.departureAirport = ''
        this.editForm.arrivalAirport = ''
        this.editForm.discount = ''
        this.editForm.fileIds = ''
        this.editForm.minPrice = ''
        this.editForm.company = ''
      }
    },  //显示房间编辑界面
    editRoom: function(index, row) {
      this.dialogroom = true;

      if (row != undefined && row != 'undefined') {
        this.roomtitle = '修改'
        this.roomForm.classId = row.classId
        this.roomForm.flightId = row.flightId
        this.roomForm.cabin = row.cabin
        this.roomForm.price = row.price
        this.roomForm.discount = row.discount

      } else {
        this.title = '添加'
        this.roomForm.flightId = ''
        this.roomForm.cabin = ''
        this.roomForm.price = ''
        this.roomForm.discount = ''
      }
    },
    findEditRoom: function(index, row) {
      this.dialogTableVisible = true;
      this.roomListData=[];
      axios.get(this.HOST + '/flightClass/list?flightId=' + row.flightId).then(res => {
        for (var i = 0; i < res.data.list.length; i++) {
          var roomarray = [];
          var strs = res.data.list[i].fileIds;
          if (strs != "" && strs != null && strs != undefined) {
            roomarray = strs.split(',');
          }
          var html = {
            classId: res.data.list[i].classId,
            flightId: res.data.list[i].flightId,
            cabin: res.data.list[i].cabin,
            price: res.data.list[i].price,
            discount: res.data.list[i].discount,
            roomimages: roomarray
          };
          this.roomListData.push(html);
        }
        this.roomHotelId = row.flightId;
        this.loading = false;
      });
    },
    // 编辑、增加页面保存方法
    submitForm(editData) {
      this.$refs[editData].validate(valid => {
        if (valid) {
          var formId = this.editForm.flightId;
          console.log("formId:" + formId);
          console.log("departureTime:" + this.editForm.departureTime);
          var url = '';
          if (formId == "" || formId == null || formId == undefined) {
            url = this.HOST + '/flight/add';
          } else {
            url = this.HOST + '/flight/edit';
          }
          //拼接图片id
          this.editForm.fileIds = this.fileIds.join(",");
          this.editForm.beginDate = this.editForm.departureTime;
          this.editForm.endDate = this.editForm.arrivalTime;
          this.editForm.departureTime = '';
          this.editForm.arrivalTime = '';
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
    // 舱位编辑、增加页面保存方法
    submitRoomForm(editData) {
      this.$refs[editData].validate(valid => {
        if (valid) {
          var formId = this.roomForm.classId;
          console.log("formId:" + formId);
          var url = '';
          if (formId == "" || formId == null || formId == undefined) {
            url = this.HOST + '/flightClass/add';
          } else {
            url = this.HOST + '/flightClass/edit';
          }
          console.log("this.fileRoomIds:"+this.fileRoomIds)
          //拼接图片id
          this.roomForm.fileIds = this.fileRoomIds.join(",");
          this.roomForm.flightId = this.roomHotelId;
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
            axios.get(this.HOST + '/flightClass/list?flightId=' + this.roomHotelId).then(res => {
              for (var i = 0; i < res.data.list.length; i++) {
                var roomarray = [];
                var strs = res.data.list[i].fileIds;
                if (strs != "" && strs != null && strs != undefined) {
                  roomarray = strs.split(',');
                }
                var html = {
                  classId: res.data.list[i].classId,
                  flightId: res.data.list[i].flightId,
                  cabin: res.data.list[i].cabin,
                  price: res.data.list[i].price,
                  discount: res.data.list[i].discount,
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
              url: this.HOST + '/flight/delete',
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
              },
              data: {
                flightId: row.flightId
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
              this.getdata(this.formInline)
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
              url: this.HOST + '/flightClass/delete',
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
              },
              data: {
                classId: row.classId
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
              axios.get(this.HOST + '/filghtClass/list?flightId=' + this.roomHotelId).then(res => {
                for (var i = 0; i < res.data.list.length; i++) {
                  var roomarray = [];
                  var strs = res.data.list[i].fileIds;
                  if (strs != "" && strs != null && strs != undefined) {
                    roomarray = strs.split(',');
                  }
                  var html = {
                    classId: res.data.list[i].classId,
                    flightId: res.data.list[i].flightId,
                    cabin: res.data.list[i].cabin,
                    price: res.data.list[i].price,
                    discount: res.data.list[i].discount,
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
    selectPicUpload(obj) {
      let fd = new FormData(); //参数的格式是formData格式的
      fd.append("file", obj.file); //参数
      fd.append("path", "train");
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
</style>
