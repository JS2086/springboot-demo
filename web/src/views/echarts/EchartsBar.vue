<template>
  <div>
    <el-row style="margin-top: 100px">
      <el-col :span="12">
        <div id="bar-one"></div>
      </el-col>

      <el-col :span="12">
        <div id="bar-two"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from "echarts";

export default {
  name: "EchartsBar",
  data() {
    return {};
  },
  mounted() {
    var barOneOption = {
      tooltip: {
        trigger: "axis",
        axisPointer: {
          type: "shadow",
        },
      },
      grid: {
        left: "3%",
        right: "4%",
        bottom: "3%",
        containLabel: true,
      },
      xAxis: [
        {
          type: "category",
          data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
          axisTick: {
            alignWithLabel: true,
          },
        },
      ],
      yAxis: [
        {
          type: "value",
        },
      ],
      series: [
        {
          name: "Direct",
          type: "bar",
          barWidth: "60%",
          data: [10, 52, 200, 334, 390, 330, 220],
        },
      ],
    };

    var barTwoOption = {
      tooltip: {
        trigger: "axis",
        axisPointer: {
          type: "cross",
          crossStyle: {
            color: "#999",
          },
        },
      },
      legend: {
        data: ["Evaporation", "Precipitation", "Temperature"],
      },
      xAxis: [
        {
          type: "category",
          data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
          axisPointer: {
            type: "shadow",
          },
        },
      ],
      yAxis: [
        {
          type: "value",
          name: "Precipitation",
          min: 0,
          max: 250,
          interval: 50,
          axisLabel: {
            formatter: "{value} ml",
          },
        },
        {
          type: "value",
          name: "Temperature",
          min: 0,
          max: 25,
          interval: 5,
          axisLabel: {
            formatter: "{value} °C",
          },
        },
      ],
      series: [
        {
          name: "Evaporation",
          type: "bar",
          tooltip: {
            valueFormatter: function (value) {
              return value + " ml";
            },
          },
          data: [
            2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3,
          ],
        },
        {
          name: "Precipitation",
          type: "bar",
          tooltip: {
            valueFormatter: function (value) {
              return value + " ml";
            },
          },
          data: [
            2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3,
          ],
        },
        {
          name: "Temperature",
          type: "line",
          yAxisIndex: 1,
          tooltip: {
            valueFormatter: function (value) {
              return value + " °C";
            },
          },
          data: [
            2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2,
          ],
        },
      ],
    };

    var barOneChartDom = document.getElementById("bar-one");
    var barOneChart = echarts.init(barOneChartDom);
    barOneChart.setOption(barOneOption);

    var barTwoChartDom = document.getElementById("bar-two");
    var barTwoChart = echarts.init(barTwoChartDom);
    barTwoChart.setOption(barTwoOption);
  },
  methods: {},
};
</script>

<style scoped>
#bar-one {
  width: 600px;
  height: 500px;
}
#bar-two {
  width: 600px;
  height: 500px;
}
</style>