<template>
    <div id="myChart" :style="{width: '800px', height: '600px'}"></div>
</template>

<script>
    export default {
        name: 'Echarts',
        mounted() {
            let myChart = this.$echarts.init(document.getElementById('myChart'))
            // 绘制图表
            myChart.setOption({
                backgroundColor: '#2c343c',

                title: {
                    text: '水果饼图',
                    left: 'center',
                    top: 20,
                    textStyle: {
                        color: '#E9EEF3;'
                    }
                },

                tooltip: {
                    trigger: 'item'
                },

                visualMap: {
                    show: false,
                    min: 80,
                    max: 600,
                    inRange: {
                        colorLightness: [0, 1]
                    }
                },
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '50%'],
                        data: [
                            {
                                value: 335,
                                itemStyle: {
                                    color: "#3fb1e3"
                                },
                                name: '芊芊'
                            },
                            {
                                value: 310,

                                name: '邮件营销'
                            },
                            {value: 274, name: '联盟广告'},
                            {value: 235, name: '视频广告'},
                            {value: 400, name: '搜索引擎'}
                        ].sort(function (a, b) {
                            return a.value - b.value;
                        }),
                        roseType: 'radius',
                        label: {
                            color: 'rgba(255, 255, 255, 0.3)'
                        },
                        labelLine: {
                            lineStyle: {
                                color: 'rgba(255, 255, 255, 0.3)'
                            },
                            smooth: 0.2,
                            length: 10,
                            length2: 20
                        },
                        itemStyle: {
                            color: '#c23531',
                            shadowBlur: 200,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        },

                        animationType: 'scale',
                        animationEasing: 'elasticOut',
                        animationDelay: function (idx) {
                            return Math.random() * 200;
                        }
                    }]
            })
        }
    }
</script>