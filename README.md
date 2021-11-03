### 简介

学生成绩管理系统

### 前序准备

你需要在本地安装 [node](http://nodejs.org/) 、 [git](https://git-scm.com/)  、 [MySQL](https://www.mysql.com/) 、    [Redis](https://redis.io/) 、 [Maven](https://maven.apache.org/)

本项目技术栈如下：

前端：

- [vue](https://cn.vuejs.org/)
- [vuex](https://vuex.vuejs.org/zh/)
- [vue-router](https://router.vuejs.org/zh/)
- [vue-cli](https://cli.vuejs.org/zh/guide/)
- [axios](https://github.com/axios/axios)
- [element-ui](https://element.eleme.cn/#/zh-CN)

后端：

- [springboot](https://spring.io/projects/spring-boot)
- [mybatis-plus](https://mp.baomidou.com/)
- [EasyExcel](https://alibaba-easyexcel.github.io/)
- [Druid](https://druid.apache.org/)
- [Swagger](https://swagger.io/)
- [Hutool](https://www.hutool.cn/)
- [MySQL](https://www.mysql.com/)
- [Redis](https://redis.io/)
- [Maven]((https://maven.apache.org/))

### 部署步骤

#### 前端

```bash
#克隆项目
git clone https://github.com/MJX-0309/student-achievement-management-system-web.git

# 进入项目目录
cd student-achievement-management-system-web

# 安装依赖
npm install

# 启动服务
npm run dev
```

#### 后端

```bash
git clone https://github.com/MJX-0309/student-achievement-management-system.git
```

将src/main/resources/application.yml中的spring.datasource.url和spring.redis.host改为你的地址



运行src/main/java/edu/zhku/boot/StudentAchievementManagementSystemApplication

 浏览器访问localhost:9527
