<template>
  <div>
    <el-row style="margin-top: 100px">
      <el-col :span="12">
        <div id="pie-one"></div>
      </el-col>

      <el-col :span="12">
        <div id="pie-two"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from "echarts";
export default {
  name: "EchartsPie",
  data() {
    return {};
  },
  mounted() {
    var pieOneOption = {
      tooltip: {
        trigger: "item",
      },
      legend: {
        top: "5%",
        left: "center",
      },
      series: [
        {
          name: "Access From",
          type: "pie",
          radius: ["40%", "70%"],
          avoidLabelOverlap: false,
          label: {
            show: false,
            position: "center",
          },
          emphasis: {
            label: {
              show: true,
              fontSize: 40,
              fontWeight: "bold",
            },
          },
          labelLine: {
            show: false,
          },
          data: [
            { value: 1048, name: "Search Engine" },
            { value: 735, name: "Direct" },
            { value: 580, name: "Email" },
            { value: 484, name: "Union Ads" },
            { value: 300, name: "Video Ads" },
          ],
        },
      ],
    };

    var pieTwoOption = {
      legend: {
        top: "bottom",
      },
      toolbox: {
        show: true,
        feature: {},
      },
      series: [
        {
          name: "Nightingale Chart",
          type: "pie",
          radius: [50, 180],
          center: ["50%", "50%"],
          roseType: "area",
          itemStyle: {
            borderRadius: 8,
          },
          data: [
            { value: 40, name: "rose 1" },
            { value: 38, name: "rose 2" },
            { value: 32, name: "rose 3" },
            { value: 30, name: "rose 4" },
            { value: 28, name: "rose 5" },
            { value: 26, name: "rose 6" },
            { value: 22, name: "rose 7" },
            { value: 18, name: "rose 8" },
          ],
        },
      ],
    };

    var pieOneChartDom = document.getElementById("pie-one");
    var pieOneChart = echarts.init(pieOneChartDom);
    pieOneChart.setOption(pieOneOption);

    var pieTwoChartDom = document.getElementById("pie-two");
    var pieTwoChart = echarts.init(pieTwoChartDom);
    pieTwoChart.setOption(pieTwoOption);
  },
  methods: {},
};
</script>

<style scoped>
#pie-one {
  width: 600px;
  height: 500px;
}
#pie-two {
  width: 600px;
  height: 500px;
}
</style>