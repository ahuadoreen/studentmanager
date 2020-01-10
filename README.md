# studentmanager
a springboot mybatis system for student manager
这是一个用springboot加mybatis构建的学生管理系统的后台部分，登录用的是jwt生成token的验证方式，可以用于前后端分离开发的后台api开发架构。 
前端代码可查看https://github.com/ahuadoreen/schoolmanage-cli 
以及另一个springmvc加spring data jpa版本的后台https://github.com/ahuadoreen/studentmanage

运行环境：需安装java8以上，mysql5.7以上，redis
运行步骤：
1. 创建数据库studentmanage，导入src/main/resources目录下的studentmanage.sql脚本
2. 启动redis
3. 用2019版本的Intellij IDEA导入项目，等待IDEA编译完成后运行项目。或者自行安装4.7以上版本的gradle，在命令行运行gradle bootRun
4. 在浏览器输入http://localhost:8080/studentmanage/teacher/teachers ，如果显示code为401的json返回，证明运行成功。

本代码的分解教程，请至https://www.jianshu.com/nb/41542276 查看我的springboot教程系列。
