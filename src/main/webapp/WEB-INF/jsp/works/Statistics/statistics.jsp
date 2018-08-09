<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en">
<head>
<base href="<%=basePath%>"><!-- jsp文件头和头部 -->
    <%@ include file="../../system/admin/top.jsp"%>
    <script type="text/javascript" src="static/js/echarts.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>chart</title>
    <style>
        html,body{
            width: 100%;
            height:100%;
            background-color: #222d57;
            color: #fff;
            padding: 10px;
        }
        *{
            padding: 0;
            margin: 0;
            box-sizing: border-box;
        }
        .chart_con{
            display: flex;
            flex-direction: column;
            height: 100%;
        }
        .con_tittle{
            padding-bottom: 15px;
            /*border: 1px solid #2a53de;*/
            display: flex;
            justify-content: space-around;
        }
        .tittle_one,.tittle_two,.tittle_three{
            width: 30%;
            height: 150px;
            text-align: center;
            line-height: 150px;
            font-size: 24px;
        }
        .box_container{
            display: flex;
            flex-direction: column;
            justify-content: space-around;
            height: 100%;
            padding-top: 15px;

        }
        .box_one,.box_two,.box_three,.box_four,.box_five,.box_six{
           width: 27%;
            height: 100%;
            margin-bottom: 10px;
            text-align: center;

        }
        .box_top,.box_bottom{
            display: flex;
            justify-content: space-around;
            height: 50%;
            margin-bottom: 50px;
        }

        .bod{
            border: 2px solid #005075;
            border-radius: 5px;
            width: 100%;
            height: 80%;
        }
        .tet{
            text-align: center;
        }
        .chart_con{
            border: 2px solid #005075;
            padding: 5px;
            height: 100%;
        }
    </style>
</head>
<body>
<div class="chart_con">
    <div class="con_tittle" >
       <%-- <div class="tittle_one"style="font-family: UNIT-A2; color: #02e5ff; word-spacing:4px; letter-spacing:6px;">123456789  </div>--%>
        <div class="tittle_two">万象大数据统计图</div>
        <%--<div class="tittle_three">头部三</div>--%>
    </div>
    <div class="box_container">
        <div class="box_top">
            <div  class="box_one">
                <div id="one" class="bod"></div>
                <div class="tet" >店铺分布图</div>
            </div>
            <div  class="box_two">
                <div id="two" class="bod"></div>
                <div class="tet" >七日订单金额(元)分布图</div>
            </div>
            <div  class="box_three">
                <div id="three" class="bod"></div>
                <div class="tet" >商品分类图</div>
            </div>
        </div>

        <div class="box_bottom">
            <div class="box_four">
                <div id="four"  class="bod"></div>
                <div class="tet" >店铺订单总金额(元)排名图</div>
            </div>
            <div  class="box_five">
                <div id="five" class="bod"></div>
                <div class="tet" >用户分类图</div>
            </div>
            <div class="box_six">
                <div  id="six"  class="bod"></div>
                <div class="tet" >买家排行榜</div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    one();
    two();
    three();
    four();
    five();
    six();
    function one() {
        var dom = document.getElementById("one");
        var myChart = echarts.init(dom);
        var app = {};
        var data = [
            {value:${shop.shopCountUSE}, name:'正常使用店铺'},
            {value:${shop.shopCountAPP}, name:'审核中的店铺'},
            {value:${shop.shopCountSHOP_FRE}, name:'冻结的店铺'}
        ];
        option = null;
        option = {
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            series : [
                {
                    name: '访问来源',
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data: data,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        ;
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
    }


    function two() {
        var dom = document.getElementById("two");
        var myChart = echarts.init(dom);
        var app = {};
        option = null;
        option = {
            xAxis: {
                type: 'category',
                data: ['${shopOrderSum.OrderDate0}', '${shopOrderSum.OrderDate1}', '${shopOrderSum.OrderDate2}', '${shopOrderSum.OrderDate3}', '${shopOrderSum.OrderDate4}', '${shopOrderSum.OrderDate5}', '${shopOrderSum.OrderDate6}'],
                axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#2ceaff',//左边线的颜色
                        width:'2'//坐标线的宽度
                    }
                }
            },
            yAxis: {
                type: 'value',
                axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color:'#2ceaff',
                        width:'2'
                    }
                }
            },
            series: [{
                data: [${shopOrderSum.orderAmounts0}, ${shopOrderSum.orderAmounts1}, ${shopOrderSum.orderAmounts2}, ${shopOrderSum.orderAmounts3}, ${shopOrderSum.orderAmounts4}, ${shopOrderSum.orderAmounts5}, ${shopOrderSum.orderAmounts6}],
                type: 'line',
                symbol: 'triangle',
                symbolSize: 20,
                lineStyle: {
                    normal: {
                        color: 'green',
                        width: 1,
                        type: 'solid'
                    }
                },
                itemStyle: {
                    normal: {
                        borderWidth: 3,
                        borderColor: 'yellow',
                        color: 'blue'
                    }
                }
            }]
        };
        ;
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
    }




    function three() {
        var dom = document.getElementById("three");
        var myChart = echarts.init(dom);
        var app = {};
        var data = [
            {value:${shopSort.ShopSortAmount0}, name:'${shopSort.ShopSortName0}'},
            {value:${shopSort.ShopSortAmount1}, name:'${shopSort.ShopSortName1}'},
            {value:${shopSort.ShopSortAmount2}, name:'${shopSort.ShopSortName2}'},
            {value:${shopSort.ShopSortAmount3}, name:'${shopSort.ShopSortName3}'},
            {value:${shopSort.ShopSortAmount4}, name:'${shopSort.ShopSortName4}'},
            {value:${shopSort.ShopSortAmount5}, name:'${shopSort.ShopSortName5}'},
            {value:${shopSort.ShopSortAmount6}, name:'${shopSort.ShopSortName6}'},
            {value:${shopSort.ShopSortAmount7}, name:'${shopSort.ShopSortName7}'},
            {value:${shopSort.ShopSortAmount8}, name:'${shopSort.ShopSortName8}'},
            {value:${shopSort.ShopSortAmount9}, name:'${shopSort.ShopSortName9}'},
            {value:${shopSort.ShopSortAmount10}, name:'${shopSort.ShopSortName10}'}
        ];
        option = null;
        option = {
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            series : [
                {
                    name: '访问来源',
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data: data,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        ;
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
    }






    
    function  four() {
        var dom = document.getElementById("four");
        var myChart = echarts.init(dom);
        var app = {};
        option = null;
        app.title = '坐标轴刻度与标签对齐';

        option = {
            color: ['#FFD700'],
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    data : ['${shopRanking.ShopName0}', '${shopRanking.ShopName1}', '${shopRanking.ShopName2}', '${shopRanking.ShopName3}', '${shopRanking.ShopName4}', '${shopRanking.ShopName5}', '${shopRanking.ShopName6}'],
                    axisTick: {
                        alignWithLabel: true
                    },
                    /*axisLine: {
                        lineStyle: {
                            type: 'solid',
                            color: '#2ceaff',//左边线的颜色
                            width:'2'//坐标线的宽度
                        }
                    },*/
                    axisLabel:{
                        interval:0,
                        rotate:-30,
                        margin: 20,
                        textStyle:{
                            align: 'center',
                            color: '#2ceaff',//左边线的颜色
                        },
                        lineStyle: {
                            type: 'solid',
                            color: '#2ceaff',//左边线的颜色
                            width:'2'//坐标线的宽度
                        }
                    },
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    axisLine: {
                        lineStyle: {
                            type: 'solid',
                            color:'#2ceaff',
                            width:'2'
                        }
                    }
                }

            ],
            series : [
                {
                    name:'直接访问',
                    type:'bar',
                    barWidth: '60%',
                    data:[${shopRanking.ShopAmount0}, ${shopRanking.ShopAmount1}, ${shopRanking.ShopAmount2}, ${shopRanking.ShopAmount3}, ${shopRanking.ShopAmount4}, ${shopRanking.ShopAmount5}, ${shopRanking.ShopAmount6}]
                }
            ]
        };
        ;
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
    }
    
    
    function five() {
        var dom = document.getElementById("five");
        var myChart = echarts.init(dom);
        var app = {};
        var data = [
            {value:${user.NORMAL_USER}, name:'正常在线用户'},
            {value:${user.IMNORMAL_USER}, name:'异常用户'}
        ];
        option = null;
        option = {
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            series : [
                {
                    name: '访问来源',
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data: data,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        ;
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
    }
    
    function six() {
        var dom = document.getElementById("six");
        var myChart = echarts.init(dom);
        var app = {};
        option = null;
        app.title = '坐标轴刻度与标签对齐';

        option = {
            color: ['#FFD700'],
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    data : ['${shopCustomer.ShopCustomerName0}', '${shopCustomer.ShopCustomerName1}', '${shopCustomer.ShopCustomerName2}', '${shopCustomer.ShopCustomerName3}', '${shopCustomer.ShopCustomerName4}', '${shopCustomer.ShopCustomerName5}', '${shopCustomer.ShopCustomerName6}'],
                    axisTick: {
                        alignWithLabel: true
                    },
                    /*axisLine: {
                        lineStyle: {
                            type: 'solid',
                            color: '#2ceaff',//左边线的颜色
                            width:'2'//坐标线的宽度
                        }
                    },*/
                    axisLabel:{
                        interval:0,
                        rotate:-30,
                        margin: 30,
                        textStyle:{
                            align: 'center',
                            color: '#2ceaff',//左边线的颜色
                        },
                        lineStyle: {
                            type: 'solid',
                            color: '#2ceaff',//左边线的颜色
                            width:'2'//坐标线的宽度
                        }
                    },
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    axisLine: {
                        lineStyle: {
                            type: 'solid',
                            color:'#2ceaff',
                            width:'2'
                        }
                    }
                }

            ],
            series : [
                {
                    name:'直接访问',
                    type:'bar',
                    barWidth: '60%',
                    data:[${shopCustomer.ShopCustomerAmount0}, ${shopCustomer.ShopCustomerAmount1}, ${shopCustomer.ShopCustomerAmount2}, ${shopCustomer.ShopCustomerAmount3}, ${shopCustomer.ShopCustomerAmount4}, ${shopCustomer.ShopCustomerAmount5}, ${shopCustomer.ShopCustomerAmount6}]
                }
            ]
        };
        ;
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
    }
    
</script>

</body>
</html>