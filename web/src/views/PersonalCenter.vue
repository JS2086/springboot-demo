<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>个人信息</span>
      </div>
      <div>
        <div class="avatar">
          <img title="点击修改头像" :src="profilePicture" alt="" />
        </div>
        <div>
          用户姓名：
          <el-tag>{{ userInfo.name }}</el-tag>
        </div>
        <div>
          手机号码：
          <el-tag>{{ userInfo.phone }}</el-tag>
        </div>
        <div>
          联系地址：
          <el-tag>{{ userInfo.address }}</el-tag>
        </div>
        <div class="button">
          <el-button type="primary" @click="showModifyInformationDialog"
            >修改信息</el-button
          >
          <el-button type="danger" @click="showChangePasswordDialog"
            >修改密码</el-button
          >
        </div>
      </div>
    </el-card>
    <el-dialog
      title="修改个人信息"
      :visible.sync="modifyInformationDialogVisible"
      width="30%"
    >
      <div>
        <table>
          <tr>
            <td>用户姓名：</td>
            <td><el-input v-model="user.name"></el-input></td>
          </tr>
          <tr>
            <td>手机号码：</td>
            <td><el-input v-model="user.phone"></el-input></td>
          </tr>
        </table>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="modifyInformationDialogVisible = false"
          >取 消</el-button
        >
        <el-button
          type="primary"
          @click="modifyInformationDialogVisible = false"
        >
          确 定
        </el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="修改密码"
      :visible.sync="changePasswordDialogVisible"
      width="30%"
    >
      <div>
        <el-form
          :model="ruleForm"
          status-icon
          :rules="rules"
          ref="ruleForm"
          label-width="130px"
          class="demo-ruleForm"
        >
          <el-form-item label="请输入旧密码" prop="oldPassword">
            <el-input
              type="password"
              v-model="ruleForm.oldPassword"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="请输入新密码" prop="newPassword">
            <el-input
              type="password"
              v-model="ruleForm.newPassword"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="请再次输入新密码" prop="checkPassword">
            <el-input
              type="password"
              v-model="ruleForm.checkPassword"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')"
              >提交</el-button
            >
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-dialog>
  </div>
</template>>

<script>
import ProfilePicture from "@/assets/profile_picture.jpg";

export default {
  name: "PersonalCenter",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.ruleForm.checkPass !== "") {
          this.$refs.ruleForm.validateField("checkPass");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      profilePicture: ProfilePicture,
      modifyInformationDialogVisible: false,
      changePasswordDialogVisible: false,
      userInfo: JSON.parse(window.sessionStorage.getItem("user")),
      user: {
        name: "",
        phone: "",
      },
      ruleForm: {
        oldPassword: "",
        newPassword: "",
        checkPassword: "",
      },
      rules: {
        oldPassword: [{ validator: validatePass, trigger: "blur" }],
        newPassword: [{ validator: validatePass, trigger: "blur" }],
        checkPassword: [{ validator: validatePass2, trigger: "blur" }],
      },
    };
  },
  methods: {
    showModifyInformationDialog() {
      this.modifyInformationDialogVisible = true;
    },
    showChangePasswordDialog() {
      this.changePasswordDialogVisible = true;
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert("submit!");
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>

<style scoped>
.box-card {
  width: 400px;
  margin: 180px auto;
}
.avatar {
  display: flex;
  justify-content: center;
}
.avatar img {
  width: 100px;
  height: 100px;
  border-radius: 50px;
}
.button {
  display: flex;
  justify-content: space-around;
  margin-top: 10px;
}
</style>