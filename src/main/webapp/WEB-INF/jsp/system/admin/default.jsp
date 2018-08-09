<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">

<!-- jsp文件头和头部 -->
<%@ include file="top.jsp"%>

</head>
<body>

	<div class="container-fluid" id="main-container">
		

			<div id="page-content" class="clearfix">

				<div class="page-header position-relative">
					<h1>
						说明 <small><i class="icon-double-angle-right"></i> </small>
					</h1>
				</div>
				<!--/page-header-->

				<div class="row-fluid" style="font-size: 20px">

					&nbsp&nbsp目前以开放功能:店铺管理,顾客管理(买家用户),员工管理.
					<br/>
					&nbsp&nbsp店铺管理:包括店铺列表/店铺详细信息查看/店铺验证状态修改.
					<br/>
					&nbsp&nbsp员工管理:包括员工列表/员工详细信息查看/添加员工/删除员工/员工信息修改.
					<br/>
					&nbsp&nbsp顾客管理:包括顾客列表/顾客详细信息查看/顾客状态变更/顾客类型变更/顾客信息修改
					<br/>
					&nbsp&nbsp商品管理:包括商品列表/商品详细信息查看/商品新增/商品删除/商品信息修改
					<br/>
					&nbsp&nbsp订单管理:包括订单列表/订单详细信息查看
					<br/>

				</div>
			</div>
				<!--/row-->










		</div>
		<!-- #main-content -->
	</div>

</body>
</html>
