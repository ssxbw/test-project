/**
* 基础菜单 商品管理
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
      <el-form-item label="出发地:">
        <el-input size="small" v-model="formInline.departureAirport" placeholder="输入出发地"></el-input>
      </el-form-item>
      <el-form-item label="目的地:">
        <el-input size="small" v-model="formInline.arrivalAirport" placeholder="输入目的地"></el-input>
      </el-form-item>
      <el-form-item label="起飞时间:">
        <el-date-picker v-model="formInline.beginDate" type="datetime" @blur="$forceUpdate()" placeholder="选择日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="到达时间:">
        <el-date-picker v-model="formInline.endDate" type="datetime" @blur="$forceUpdate()" placeholder="选择日期">
        </el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
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
          <el-button size="mini" @click="findEditRoom(scope.$index, scope.row)">查看舱位</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <!-- <Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination> -->


    <el-dialog title="房型列表" :visible.sync="dialogTableVisible" width="60%">
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
            <el-button size="mini" @click="bookRoom(scope.$index, scope.row)">预订</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
import Pagination from '../../../components/Pagination';
import axios from "axios";
export default {
  data() {
    return {
      roomHotelId: '',
      formLabelWidth: '100px',
      dialogroom: false,
      dialogTableVisible: false, //房间弹窗
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
        varName: '',
        departureAirport: '',
        arrivalAirport: '',
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
    const start = new Date(); //获取当前的日期
    const end = new Date(); //获取当前的日期
    start.setTime(start.getTime())
    end.setTime(end.getTime() + 3600 * 1000 * 24 * 3)
    // this.formInline.beginDate = this.dateFormat(start);
    // this.formInline.endDate = this.dateFormat(end);
    this.formInline.beginDate = start;
    this.formInline.endDate = end;
    this.getdata(this.formInline);

  },

  /**
   * 里面的方法只有被调用才会执行
   */
  methods: {
    // dateFormat(dateData) {
    //   var date = new Date(dateData)
    //   var y = date.getFullYear()
    //   var m = date.getMonth() + 1
    //   m = m < 10 ? ('0' + m) : m
    //   var d = date.getDate()
    //   d = d < 10 ? ('0' + d) : d
    //   const time = y + '-' + m + '-' + d
    //   return time
    // },
    // 获取酒店列表
    getdata(parameter) {

      this.loading = true;
      var that = this;
      that.listData = [];
      var url = '';
      var name = that.formInline.departureAirport;
      var address = that.formInline.arrivalAirport;
      console.log(that.formInline.beginDate,that.formInline.endDate)
      if (name == "" || name == null || name == undefined) {
        if (address == "" || address == null || address == undefined) {
          url = this.HOST + '/flight/list1?beginDate='+that.formInline.beginDate + '&endDate='+that.formInline.endDate;
        } else {
          url = this.HOST + '/flight/list1?arrivalAirport=' + that.formInline.arrivalAirport+'&beginDate='+that.formInline.beginDate + '&endDate='+that.formInline.endDate;
        }
      } else {
        if (address == "" || address == null || address == undefined) {
          url = this.HOST + '/flight/list1?departureAirport=' + that.formInline.departureAirport+'&beginDate='+that.formInline.beginDate + '&endDate='+that.formInline.endDate;
        } else {
          url = this.HOST + '/flight/list1?departureAirport=' + that.formInline.departureAirport + '&arrivalAirport=' + that.formInline.arrivalAirport+'&beginDate='+that.formInline.beginDate + '&endDate='+that.formInline.endDate;
        }
      }
      //url = this.HOST + '/flight/list1?beginDate=' + that.formInline.beginDate + '&endDate=' + that.formInline.endDate;
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

    findEditRoom: function(index, row) {
      this.dialogTableVisible = true;
      this.roomListData = [];
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
    bookRoom: function(index, row) {
      var userid = localStorage.getItem('userid');
      var paymentPassword = localStorage.getItem('userpaymentpassword');
      var classId = row.classId;
      console.log("1111:" + userid, paymentPassword, classId)
      axios({
        method: 'post',
        url: this.HOST + '/order/createFlightOrder',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        data: {
          buyerId: userid,
          paymentPassword: paymentPassword,
          classId: classId,
          beginDate: this.formInline.beginDate,
          endDate: this.formInline.endDate
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
        this.editFormVisible = false;

        this.getdata(this.formInline)
        if (res.data.code == 1) {
          this.$message({
            type: 'success',
            message: '预订成功！'
          })
        } else {
          this.$message({
            type: 'success',
            message: res.data.msg
          })
        }

      });
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
