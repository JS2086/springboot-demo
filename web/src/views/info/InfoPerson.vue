<template>
  <div>
    <div style="display: flex; justify-content: space-between">
      <div>
        <el-input
          class="search-box"
          v-model="personName"
          @keydown.enter.native="initPersonInfoData"
          @clear="initPersonInfoData"
          prefix-icon="el-icon-search"
          placeholder="请输入人员姓名进行搜索"
          clearable
        ></el-input>
        <el-button
          type="primary"
          icon="el-icon-search"
          @click="
            currentPage = 1;
            initPersonInfoData();
          "
          >搜索
        </el-button>
      </div>
      <div>
        <el-button type="success" @click="exportTemplate"> 下载模板 </el-button>
        <el-upload
          action="/person/import"
          :before-upload="beforeUpload"
          :headers="headers"
          :on-success="onSuccess"
          :on-error="onError"
          :disabled="importDataDisabled"
          style="display: inline-flex; margin: 0 10px"
          :show-file-list="false"
        >
          <el-button
            :disabled="importDataDisabled"
            type="success"
            :icon="importDataBtnIcon"
          >
            {{ importDataBtnText }}
          </el-button>
        </el-upload>
        <el-button type="success" @click="exportData" icon="el-icon-download">
          导出数据
        </el-button>
        <el-button
          type="primary"
          icon="el-icon-plus"
          @click="showAddPersonInfoView"
        >
          添加人员信息
        </el-button>
      </div>
    </div>

    <div>
      <el-table
        :data="personInfoData"
        style="width: 100%"
        border
        v-loading="loading"
        element-loading-text="正在加载..."
        element-loading-spinner="el-icon-loading"
        element-loading-background="rgba(0, 0, 0, 0.8)"
      >
        <el-table-column type="selection" width="70" align="center">
        </el-table-column>
        <el-table-column
          prop="name"
          label="姓名"
          width="150"
          fixed
          align="left"
        >
        </el-table-column>
        <el-table-column prop="age" label="年龄" width="100"> </el-table-column>
        <el-table-column prop="gender" label="性别" width="100">
        </el-table-column>
        <el-table-column prop="birth" align="left" width="200" label="出生日期">
        </el-table-column>
        <el-table-column
          prop="idCard"
          align="left"
          width="300"
          label="身份证号码"
        >
        </el-table-column>
        <el-table-column prop="province.name" width="150" label="省份">
        </el-table-column>
        <el-table-column prop="weight" width="100" label="体重">
        </el-table-column>
        <el-table-column fixed="right" width="200" label="操作">
          <template slot-scope="scope">
            <el-button
              type="primary"
              style="padding: 3px"
              size="mini"
              @click="showPersonInfoView(scope.row)"
              >查看</el-button
            >
            <el-button
              style="padding: 3px"
              size="mini"
              @click="showEditPersonInfoView(scope.row)"
              >编辑</el-button
            >
            <el-button
              type="danger"
              style="padding: 3px"
              size="mini"
              @click="deletePerson(scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <div style="display: flex; justify-content: flex-end">
        <el-pagination
          background
          @current-change="currentChange"
          @size-change="sizeChange"
          layout="sizes,prev,pager,next,jumper,->,total,slot"
          :total="total"
        ></el-pagination>
      </div>
    </div>
    <el-dialog :title="title" :visible.sync="dialogVisible" width="80%">
      <div>
        <el-form ref="personInfoForm" :model="personInfoList" :rules="rules">
          <el-row>
            <el-col :span="6">
              <el-form-item label="姓名：" prop="name">
                <el-input
                  style="width: 230px"
                  v-model="personInfoList.name"
                  placeholder="请输入姓名"
                  prefix-icon="el-icon-edit"
                  :disabled="isDisabled"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="身份证号：" prop="idCard">
                <el-input
                  style="width: 270px"
                  v-model="personInfoList.idCard"
                  placeholder="请输入身份证号码"
                  prefix-icon="el-icon-edit"
                  :disabled="isDisabled"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="省份：" prop="provinceId">
                <el-select
                  v-model="personInfoList.provinceId"
                  placeholder="省份："
                  style="width: 200px"
                  :disabled="isDisabled"
                >
                  <el-option
                    v-for="item in provinces"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="性别：" prop="gender">
                <el-radio-group
                  v-model="personInfoList.gender"
                  :disabled="isDisabled"
                >
                  <el-radio label="男">男</el-radio>
                  <el-radio label="女">女</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="6">
              <el-form-item label="年龄：" prop="age">
                <el-input
                  style="width: 180px"
                  v-model="personInfoList.age"
                  placeholder="请输入年龄"
                  prefix-icon="el-icon-edit"
                  :disabled="isDisabled"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="出生日期：" prop="birth">
                <el-date-picker
                  v-model="personInfoList.birth"
                  type="date"
                  value-format="yyyy-MM-dd"
                  style="width: 200px"
                  placeholder="出生日期"
                  :disabled="isDisabled"
                >
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="体重：" prop="weight">
                <el-input
                  style="width: 200px"
                  v-model="personInfoList.weight"
                  placeholder="请输入体重"
                  prefix-icon="el-icon-edit"
                  :disabled="isDisabled"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button
          @click="
            dialogVisible = false;
            isDisabled = false;
          "
          >取 消</el-button
        >
        <el-button
          v-if="title != '查看人员信息'"
          type="primary"
          @click="addOrUpdatePersonInfo"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "InfoPerson",
  data() {
    return {
      personInfoData: [],
      provinces: [],
      title: "",
      personName: "",
      importDataBtnText: "导入数据",
      importDataBtnIcon: "el-icon-upload2",
      isDisabled: false,
      loading: false,
      dialogVisible: false,
      importDataDisabled: false,
      total: 0,
      currentPage: 1,
      size: 10,
      headers: {
        Authorization: window.sessionStorage.getItem("tokenStr"),
      },
      personInfoList: {
        id: null,
        name: "",
        idCard: "",
        provinceId: "",
        gender: "",
        age: "",
        birth: "",
        weight: "",
      },
      rules: {
        name: [
          {
            required: true,
            message: "请输入姓名",
            trigger: "blur",
          },
        ],
        idCard: [
          {
            required: true,
            message: "请输入身份证号码",
            trigger: "blur",
          },
          {
            pattern:
              /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,
            message: "身份证号码格式不正确",
            trigger: "blur",
          },
        ],
        provinceId: [
          {
            required: true,
            message: "请输入省份",
            trigger: "blur",
          },
        ],
        gender: [
          {
            required: true,
            message: "请输入性别",
            trigger: "blur",
          },
        ],
        age: [
          {
            required: true,
            message: "请输入年龄",
            trigger: "blur",
          },
        ],
        birth: [
          {
            required: true,
            message: "请输入出生日期",
            trigger: "blur",
          },
        ],
        weight: [
          {
            required: true,
            message: "请输入体重",
            trigger: "blur",
          },
        ],
      },
    };
  },
  mounted() {
    this.initProvinceData();
    this.initPersonInfoData();
  },
  methods: {
    exportTemplate() {
      axios({
        method: "get",
        url: "/person/export_template",
        responseType: "blob",
      })
        .then((res) => {
          const blob = new Blob([res]); //获取Blob对象
          let link = document.createElement("a"); //创建一个超链接对象实例
          let url = window.URL.createObjectURL(blob); //// 创建一个包含Blob的对象链接
          link.href = url;
          link.download = "人员信息模板.xls"; //下载后文件名
          document.body.appendChild(link);
          link.click(); //点击下载
          document.body.removeChild(link); //下载完成移除元素
          window.URL.revokeObjectURL(url); //释放掉Blob对象
        })
        .catch((error) => {
          console.error("下载文件失败:", error);
        });
    },
    exportData() {
      this.downloadRequest("/person/export_data");
    },
    beforeUpload() {
      this.importDataBtnText = "正在导入";
      this.importDataBtnIcon = "el-icon-loading";
      this.importDataDisabled = true;
    },
    onSuccess(res) {
      this.importDataBtnText = "导入数据";
      this.importDataBtnIcon = "el-icon-upload2";
      this.importDataDisabled = false;
      if (res.code == 200) {
        this.$message({
          message: res.message,
          type: "success",
        });
      } else {
        this.$message.error(res.message);
      }
      this.initPersonInfoData();
    },
    onError() {
      this.importDataBtnText = "导入数据";
      this.importDataBtnIcon = "el-icon-upload2";
      this.importDataDisabled = false;
      this.$message.error("导入失败");
    },
    initProvinceData() {
      if (!window.sessionStorage.getItem("provinces")) {
        this.getRequest("/province").then((resp) => {
          if (resp) {
            this.provinces = resp;
            window.sessionStorage.setItem("provinces", JSON.stringify(resp));
          }
        });
      } else {
        this.provinces = JSON.parse(window.sessionStorage.getItem("provinces"));
      }
    },
    initPersonInfoData() {
      this.loading = true;
      let url =
        "/person/get/?currentPage=" +
        this.currentPage +
        "&size=" +
        this.size +
        "&name=" +
        this.personName;
      this.getRequest(url).then((resp) => {
        this.loading = false;
        if (resp) {
          this.personInfoData = resp.data.list;
          this.total = resp.data.total;
        }
      });
    },
    currentChange(currentPage) {
      this.currentPage = currentPage;
      this.initPersonInfoData();
    },
    sizeChange(currentSize) {
      this.size = currentSize;
      this.initPersonInfoData();
    },
    showPersonInfoView(data) {
      this.title = "查看人员信息";
      this.personInfoList = data;
      this.dialogVisible = true;
      this.isDisabled = true;
    },
    showAddPersonInfoView() {
      this.isDisabled = false;
      this.title = "添加人员信息";
      this.personInfoList = {
        id: null,
        name: "",
        idCard: "",
        provinceId: "",
        gender: "",
        age: "",
        birth: "",
        weight: "",
      };
      this.dialogVisible = true;
    },
    showEditPersonInfoView(data) {
      this.isDisabled = false;
      this.title = "编辑人员信息";
      this.personInfoList = data;
      this.dialogVisible = true;
    },
    addOrUpdatePersonInfo() {
      if (this.personInfoList.id) {
        this.$refs["personInfoForm"].validate((valid) => {
          if (valid) {
            this.putRequest("/person/update", this.personInfoList).then(
              (resp) => {
                if (resp) {
                  this.dialogVisible = false;
                  this.initPersonInfoData();
                }
              }
            );
          }
        });
      } else {
        this.$refs["personInfoForm"].validate((valid) => {
          if (valid) {
            this.postRequest("/person/add", this.personInfoList).then(
              (resp) => {
                if (resp) {
                  this.dialogVisible = false;
                  this.initPersonInfoData();
                }
              }
            );
          }
        });
      }
    },
    deletePerson(data) {
      this.$confirm("是否删除该人员信息", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.deleteRequest("/person/delete/" + data.id).then((resp) => {
            if (resp) {
              this.initPersonInfoData();
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
  },
};
</script>

<style scoped>
.search-box {
  width: 300px;
  margin-right: 10px;
}
</style>