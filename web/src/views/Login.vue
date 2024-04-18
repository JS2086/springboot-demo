<template>
  <div class="login-container">
    <el-form
      class="login-form"
      :rules="rules"
      v-loading="loading"
      element-loading-text="登录中..."
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(0, 0, 0, 0.8)"
      ref="loginForm"
      :model="loginForm"
    >
      <h3 class="login-title">登录</h3>
      <el-form-item prop="username">
        <el-input
          type="text"
          auto-complete="false"
          v-model="loginForm.username"
          placeholder="请输入用户名"
        >
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          type="password"
          auto-complete="false"
          v-model="loginForm.password"
          placeholder="请输入密码"
        >
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="width: 100%" @click="submitLogin"
          >登录</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      loading: false,
      loginForm: {
        username: "admin",
        password: "123",
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
    };
  },
  methods: {
    submitLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          this.postRequest("/login", this.loginForm).then((resp) => {
            if (resp) {
              //存储用户token
              const tokenStr = resp.data.tokenHead + resp.data.token;
              window.sessionStorage.setItem("tokenStr", tokenStr);
              //页面跳转
              let path = this.$route.query.redirect;
              this.$router.replace(
                path == "/" || path == undefined ? "/home" : path
              );
            }
            this.loading = false;
          });
        } else {
          this.$message.error("请输入所有字段");
          return false;
        }
      });
    },
  },
};
</script>

<style scoped>
.login-container {
  position: absolute;
  width: 100%;
  height: 100%;
  background-image: linear-gradient(to right, #fbc2eb, #a6c1ee);
}
.login-form {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 188px auto;
  width: 350px;
  padding: 15px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
.login-title {
  margin: 0px auto 40px auto;
  text-align: center;
}
</style>