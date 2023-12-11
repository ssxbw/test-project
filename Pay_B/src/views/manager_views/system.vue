
<template>
    <div class="my">
        <el-container class="home">
            <el-header>
                <span class="title">系统管理员</span>
                <span class="info">
                <span class="hello">您好，{{ name }}</span>
                <el-button type="danger" size="mini" @click="logout">退出</el-button>
            </span>
            </el-header>
            <el-container>
                <el-aside width="200px"  class="left">
                    <el-menu
                        background-color="#333744"
                        text-color="#fff"
                        active-text-color="#409EFF"
                        :router="true">
                        <el-menu-item index="information" class="infor">
                            <i class="el-icon-message"></i>
                            <span slot="title">用户信息</span>
                        </el-menu-item>
                        <el-menu-item index="register">
                            <i class="el-icon-menu"></i>
                            <span slot="title">注册管理员</span>
                        </el-menu-item>
                        <el-menu-item index="modify">
                            <i class="el-icon-setting"></i>
                            <span slot="title">修改密码</span>
                        </el-menu-item>
                        <el-menu-item>
                        </el-menu-item>
                        <el-menu-item>
                        </el-menu-item>
                        <el-menu-item>
                        </el-menu-item>
                        <el-menu-item>
                        </el-menu-item>
                        <el-menu-item>
                        </el-menu-item>
                        <el-menu-item>
                        </el-menu-item>
                        <el-menu-item>
                        </el-menu-item>
                        <el-menu-item>
                        </el-menu-item>
                        <el-menu-item>
                        </el-menu-item>
                        <el-menu-item>
                        </el-menu-item>
                        <el-menu-item>
                        </el-menu-item>
                        <el-menu-item>
                        </el-menu-item>
                        <el-menu-item>
                        </el-menu-item>
                    </el-menu>
                </el-aside>
                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>

export default {
    data() {
        return {
            name: this.$route.query.name,
        };
    },
    methods: {
        logout() {
            //deleteCookie("token");
            window.sessionStorage.removeItem('token');
            this.$router.push("/manager");
        },


    }
};
</script>

<style scoped>

.home {
    height: 100%;
    width: 100%;
}

.left{
    height:100%!important
}

.el-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #409eff;
}

.title {
    color: #fff;
    font-size: 18px;
}

.info {
    display: flex;
    align-items: center;
}

.hello {
    margin-right: 10px;
    font-size: 14px;
    color: #fff;
}

.el-button {
    margin-left: 10px;
}
.el-menu-item {
    line-height: 40px;
}

.el-aside {
    background-color: #333744;
    color: #fff;
}

.el-main {
    background-color: #EAEDF1;
}
.infor{
    line-height: 50px;
    display: flex;
    align-items: center;
}
</style>