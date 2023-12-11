<template>
    <div className="container">
        <div className="jumbotron">
            <h1 className="display-4">欢迎来到系统管理员页面!</h1>
            <hr className="my-4">
            <p className="lead">您好，{{ name }}!</p>
            <p>在这里您可以注册管理员或者修改您的密码，用户验证等功能将会由系统自动进行。</p>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            name: this.$route.query.name,
            key: this.$route.query.key
        };
    }
};
</script>

<style>
.jumbotron {
    margin-top: 50px;
    padding: 40px;
    background-color: #f2f2f2;
}
</style>