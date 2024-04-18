<template>
  <div>
    <el-row :gutter="10" style="margin-bottom: 150px">
      <el-col :span="6">
        <el-card style="color: #e6a23c">
          <div><i class="el-icon-user-solid"></i>用户量</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            100
          </div>
        </el-card>
      </el-col>

      <el-col :span="6">
        <el-card style="color: #f56c6c">
          <div><i class="el-icon-reading"></i>阅读量</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            200
          </div>
        </el-card>
      </el-col>

      <el-col :span="6">
        <el-card style="color: #67c23a">
          <div><i class="el-icon-chat-line-round"></i>信息量</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            300
          </div>
        </el-card>
      </el-col>

      <el-col :span="6">
        <el-card style="color: #409eff">
          <div><i class="el-icon-light-rain"></i>降雨量</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            400
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="14">
        <div id="bar"></div>
      </el-col>

      <el-col :span="10">
        <div id="pie"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from "echarts";

export default {
  name: "EchartsDataScreen",
  data() {
    return {};
  },
  mounted() {
    var barChartDom = document.getElementById("bar");
    var barChart = echarts.init(barChartDom);
    var barOption;

    barOption = {
      title: {
        text: "排名前十省份统计图",
        left: "center",
      },
      tooltip: {
        trigger: "item",
      },
      xAxis: {
        type: "category",
        data: [],
      },
      yAxis: {
        type: "value",
      },
      series: [
        {
          data: [],
          type: "line",
        },
        {
          data: [],
          type: "bar",
        },
      ],
    };

    this.getRequest("/echarts/bar").then((resp) => {
      if (resp) {
        barOption.xAxis.data = resp.data.province;
        barOption.series[0].data = resp.data.num;
        barOption.series[1].data = resp.data.num;
        barChart.setOption(barOption);
      }
    });

    //饼图
    var pieChartDom = document.getElementById("pie");
    var pieChart = echarts.init(pieChartDom);

    var pieOption = {
      title: {
        text: "各省份销售额占比",
        subtext: "比例图",
        left: "center",
      },
      tooltip: {
        trigger: "item",
      },
      legend: {
        orient: "vertical",
        left: "left",
      },
      series: [
        {
          type: "pie",
          radius: "50%",
          data: [
            { value: 1048, name: "辽宁省" },
            { value: 735, name: "福建省" },
            { value: 580, name: "贵州省" },
            { value: 484, name: "山西省" },
            { value: 300, name: "河南省" },
          ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: "rgba(0, 0, 0, 0.5)",
            },
          },
        },
      ],
    };
    pieChart.setOption(pieOption);
  },
  methods: {},
};
</script>

<style scoped>
#bar {
  width: 800px;
  height: 400px;
}
#pie {
  width: 500px;
  height: 400px;
}
</style>