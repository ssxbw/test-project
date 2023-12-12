## 运行后端

1. 打开 `Payment_2.0`
2. 建一个空 `MySQL` 数据库 `payment`
3. 修改配置文件 `Payment_2.0/src/main/resources/application.yml` ，将 `username` 和 `password` 替换为你的 `MySQL` 用户名和密码
4. 运行 `SQL` 脚本 `Payment_2.0/src/main/resources/total.sql` 
5. 运行 `Payment_2.0/src/main/java/com/payment/PaymentApplication.java` 中的 `main` 启动后端



## 运行前端

1. 打开 `Pay_B`
2. 在根目录下执行 `npm install` 
3. 安装完包后 执行 `npm run serve` 启动前端，访问 `localhost:8080` 端口即可查看网页