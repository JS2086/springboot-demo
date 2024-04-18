<template>
  <div class="home-container">
    <el-container>
      <el-header class="home-header">
        <div class="title">Spring-Vue-Demo</div>
        <el-dropdown class="user-info" @command="commandHandle">
          <span class="el-dropdown-link">
            {{ user.name }}<i><img :src="profilePicture" /></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="individual">个人中心</el-dropdown-item>
            <el-dropdown-item command="setting">设置</el-dropdown-item>
            <el-dropdown-item command="logout">退出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <el-menu
            router
            class="el-menu-vertical-demo"
            background-color="#545c64"
            text-color="#fff"
            default-active="/home"
            active-text-color="#ffd04b"
          >
            <el-menu-item index="/home">
              <i class="el-icon-menu"></i>
              <span slot="title">首页</span>
            </el-menu-item>
            <el-submenu
              :index="i + ''"
              v-for="(item, i) in routes"
              :key="i"
              v-if="!item.hidden"
            >
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>{{ item.name }}</span>
              </template>
              <el-menu-item
                :index="children.path"
                v-for="(children, j) in item.children"
              >
                {{ children.name }}
              </el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main>
          <div class="welcome" v-if="this.$router.currentRoute.path == '/home'">
            欢迎使用
          </div>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import ProfilePicture from "@/assets/profile_picture.jpg";

export default {
  name: "Home",
  data() {
    return {
      user: JSON.parse(window.sessionStorage.getItem("user")),
      profilePicture: ProfilePicture,
    };
  },
  computed: {
    routes() {
      return this.$store.state.routes;
    },
  },
  methods: {
    commandHandle(command) {
      if (command == "logout") {
        this.$confirm("此操作将退出, 是否确定?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
          .then(() => {
            //注销
            this.postRequest("/logout");
            //清除用户信息
            window.sessionStorage.removeItem("user");
            window.sessionStorage.removeItem("tokenStr");
            //清空菜单
            this.$store.commit("initRoutes", []);
            //跳转到首页
            this.$router.replace("/");
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消",
            });
          });
      }
      if (command == "individual") {
        this.$router.push("/personal_center");
      }
    },
  },
};
</script>

<style scoped>
.home-container {
  height: 100%;
}
.el-container {
  padding: 0;
  margin: 0;
  height: 100%;
}
.home-header {
  background: rgb(11, 121, 218);
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 15px;
  box-sizing: border-box;
}
.home-header .title {
  font-size: 30px;
  font-family: 华文楷体;
  color: white;
}
.home-header .user-info {
  cursor: pointer;
}
.el-dropdown-link {
  display: flex;
  align-items: center;
  font-size: 20px;
  color: white;
}
.el-dropdown-link img {
  width: 48px;
  height: 48px;
  border-radius: 24px;
  margin-left: 8px;
  margin-top: 5px;
}
.welcome {
  text-align: center;
  font-size: 100px;
  font-family: 华文楷体;
  color: rgb(10, 153, 215);
  padding-top: 300px;
}
.el-menu-vertical-demo {
  height: 100%;
}
</style>