/**
* 基础菜单 我的订单
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
      <el-form-item label="开始时间:">
        <el-date-picker v-model="formInline.beginDate" type="date" @blur="$forceUpdate()" placeholder="选择日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间:">
        <el-date-picker v-model="formInline.endDate" type="date" @blur="$forceUpdate()" placeholder="选择日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
      </el-form-item>
    </el-form>
    <!--列表-->
    <el-table size="small" :data="listData" highlight-current-row
              v-loading="loading" border element-loading-text="拼命加载中" style="width: 100%;">
      <el-table-column align="center" type="selection" width="60">
      </el-table-column>
      <el-table-column prop="orderId" label="订单编号" width="100">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">订单编号</span>
        </template>
      </el-table-column>
      <el-table-column prop="sellerName" label="酒店名称" width="150">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">酒店名称</span>
        </template>
      </el-table-column>
      <el-table-column prop="itemName" label="房型" width="100">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">房型</span>
        </template>
      </el-table-column>
      <el-table-column prop="totalPrice" label="金额" width="200">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">金额</span>
        </template>
      </el-table-column>
      <el-table-column prop="orderStateStr" label="订单状态" width="100">
        <template slot="header" slot-scope="scope">
          <span style="color: #1f2d3d">订单状态</span>
        </template>
      </el-table-column>


      <el-table-column align="center" label="操作" min-width="150">
        <template slot-scope="scope">

          <el-button v-if="scope.row.orderState==3" style="color: red;" size="mini"
                     @click="cancleRoom(scope.$index, scope.row)">退款</el-button>
          <el-button v-if="scope.row.orderState==8" style="color: greenyellow;" size="mini"
                     @click="ratingRoom(scope.$index, scope.row)">评价</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="评分" :visible.sync="dialogTableVisible" width="60%">
      <el-form label-width="120px" :model="editForm" ref="editForm">

        <el-form-item label="评分" prop="rating">
          <el-input-number v-model="editForm.rating" :min="1" :max="5" label="评分"></el-input-number>

        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="closeDialog">取消</el-button>
        <el-button size="small" type="primary" :loading="loading" class="title" @click="submitForm('editForm')">保存
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import Pagination from '../../../components/Pagination';
import axios from "axios";
export default {
  data() {
    return {
      ratingOrderId: '',
      dialogTableVisible: false, //评价弹窗
      userId: '',
      allmenu: [],
      loading: false, //是显示加载
      editForm: {
        rating: 1
      },
      listData: [], //酒店数据
      // 分页参数
      pageparm: {
        currentPage: 1,
        pageSize: 10,
        total: 10
      },
      formInline: {
        page: 1,
        limit: 10,
        varLable: '',
        varName: ''
      },
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
    end.setTime(start.getTime())
    start.setTime(end.getTime() - 3600 * 1000 * 24 * 3)
    this.formInline.beginDate = this.dateFormat(start);
    this.formInline.endDate = this.dateFormat(end);
    this.userId = localStorage.getItem('userid');
    this.getdata(this.formInline);

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
    // 获取酒店列表
    getdata(parameter) {

      this.loading = true;
      var that = this;
      that.listData = [];
      var url = this.HOST + '/order/orderList?userId=' + this.userId + '&beginDate=' + that.dateFormat(that.formInline
          .beginDate) + '&endDate=' + that.dateFormat(that.formInline.endDate);

      axios.get(url).then(res => {
        for (var i = 0; i < res.data.list.length; i++) {
          var state = res.data.list[i].orderState;
          var stateStr = '';
          if (state == 3) {
            stateStr = '已付款'
          } else if (state == 8) {
            stateStr = '已完成'
          } else if (state == 10) {
            stateStr = '退款'
          }
          var html = {
            orderId: res.data.list[i].orderId,
            sellerName: res.data.list[i].sellerName,
            itemName: res.data.list[i].itemName,
            totalPrice: res.data.list[i].totalPrice,
            orderState: res.data.list[i].orderState,
            orderStateStr: stateStr
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
    ratingRoom: function(index, row) {
      this.dialogTableVisible = true;
      this.ratingOrderId = row.orderId;
    },
    submitForm(editData){
      axios({
        method: 'post',
        url: this.HOST + '/order/ratingRoom',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        data: {
          orderId: this.ratingOrderId,
          rating: this.editForm.rating,
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
        this.editForm.rating=1;
        this.ratingOrderId = '';
        this.$message({
          type: 'success',
          message: '评价成功！'
        })
      });
    },
    cancleRoom: function(index, row) {
      var orderId = row.orderId;
      console.log("orderId:"+orderId)
      this.$confirm('确定要取消订单吗?', '信息', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
          .then(() => {

            axios({
              method: 'post',
              url: this.HOST + '/order/cancelRoom',
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
              },
              data: {
                orderId: row.orderId
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
              console.log(res.data)
              this.getdata(this.formInline)
              this.$message({
                type: 'success',
                message: '退款成功！'
              })
            });

          })
          .catch(() => {
            this.$message({
              type: 'info',
              message: '退款失败'
            })
          })
    },
    // 关闭编辑、增加弹出框
    closeDialog() {
      this.dialogTableVisible = false
      this.editForm.rating = 1
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
