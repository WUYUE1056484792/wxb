<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8" />
	<title></title>
	<meta name="description" content="overview & stats" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link href="static/css/bootstrap.min.css" rel="stylesheet" />
	<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="static/css/font-awesome.min.css" />
	<!-- 下拉框 -->
	<link rel="stylesheet" href="static/css/chosen.css" />
	<link rel="stylesheet" href="static/css/ace.min.css" />
	<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
	<link rel="stylesheet" href="static/css/ace-skins.min.css" />
    <link rel="stylesheet" href="static/css/shop_detail.css">
	<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>商品信息</title>


</head>
<body>
<div class="contain">
    <table class="table_head" >
        <thead>
        <tr class="table_head_tr" >
            <th colspan="6" >订单信息</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="title" >订单编号</td>
            <td>${pd.order_id }</td>
            <td class="title">订单类型</td>
            <td>${pd.order_type_name }</td>
            <td class="title">订单状态</td>
            <td>${pd.order_status_name }</td>
        </tr>
        <tr>
            <td class="title">创建时间</td>
            <td>${pd.create_time }</td>
            <td class="title">订单金额</td>
            <td>${pd.order_amount }</td>

        </tr>
        <tr>
            <td class="title">店铺名称</td>
            <td>${pd.shop_name }</td>
            <td class="title">支付时间</td>
            <td>${pd.pay_time } </td>
            <td class="title">收货人名称</td>
            <td>${pd.receiver_name }</td>
        </tr>
        <tr>
            <td  class="title">收货电话</td>
            <td>${pd.receiver_tel }</td>
            <td class="title" >收货地址</td>
            <td colspan="3" >${pd.receiver_address }</td>
        </tr>
        </tbody>

    </table>
    <div class="con">
        <table class="table_mid" >
            <thead>
            <tr class="table_head_tr" >
                <th colspan="5"  >商品信息</th>
            </tr>
            <tr class="table_mid_tr" >
                <th>序号</th>
                <th>商品缩略图</th>
                <th>商品名称</th>
                <th>数量</th>
                 <th>商品标价</th>
            </tr>
            </thead>
            <tbody>
            <!-- 开始循环 -->
            <c:choose>
            <c:when test="${not empty orderGood}">
            <c:if test="${QX.cha == 1 }">
            <c:forEach items="${orderGood}" var="og" varStatus="vs">
            <tr>
                <td>${vs.index+1}</td>
                <td>
                    <img class="good_img"  src='${og.good_img_id}' >
                </td>
                <td>${og.good_name}</td>
                <td>${og.good_count}${og.good_unit_name}</td>
                <td>${og.good_price}</td>
            </tr>


            </c:forEach>
            </c:if>
            </c:when>
            </c:choose>
            </tbody>
        </table>

    </div>

</div>
</body>
</html>