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
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<!--提示框-->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
<script type="text/javascript">
	$(top.hangge());
	$(document).ready(function(){
		if($("#user_id").val()!=""){
			$("#loginname").attr("readonly","readonly");
			$("#loginname").css("color","gray");
		}
	});
	
	//保存
	function save(){

		

			$("#userForm").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();

	}
	
	function ismail(mail){
		return(new RegExp(/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/).test(mail));
		}
	
	//判断用户名是否存在
	function hasU(){
		var USERNAME = $.trim($("#loginname").val());
		$.ajax({
			type: "POST",
			url: '<%=basePath%>user/hasU.do',
	    	data: {USERNAME:USERNAME,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" == data.result){
					$("#userForm").submit();
					$("#zhongxin").hide();
					$("#zhongxin2").show();
				 }else{
					$("#loginname").css("background-color","#D16E6C");
					setTimeout("$('#loginname').val('此用户名已存在!')",500);
				 }
			}
		});
	}
	
	//判断邮箱是否存在
	function hasE(USERNAME){
		var EMAIL = $.trim($("#EMAIL").val());
		$.ajax({
			type: "POST",
			url: '<%=basePath%>user/hasE.do',
	    	data: {EMAIL:EMAIL,USERNAME:USERNAME,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" != data.result){
					 $("#EMAIL").tips({
							side:3,
				            msg:'邮箱已存在',
				            bg:'#AE81FF',
				            time:3
				        });
					setTimeout("$('#EMAIL').val('')",2000);
				 }
			}
		});
	}
	
	//判断编码是否存在
	function hasN(USERNAME){
		var NUMBER = $.trim($("#NUMBER").val());
		$.ajax({
			type: "POST",
			url: '<%=basePath%>user/hasN.do',
	    	data: {NUMBER:NUMBER,USERNAME:USERNAME,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" != data.result){
					 $("#NUMBER").tips({
							side:3,
				            msg:'编号已存在',
				            bg:'#AE81FF',
				            time:3
				        });
					setTimeout("$('#NUMBER').val('')",2000);
				 }
			}
		});
	}
	
</script>
	</head>
<body>
	<form action="${msg}.do" name="userForm" id="userForm" method="post">
		<input type="hidden" name="USER_ID" id="user_id" value="${shop.shop_id}"/>
		<div id="zhongxin"  style="margin-top: 10px;margin-left: 30px" >
		<table>
			

			<tr class="info">
				<td>店铺状态：</td>
				<td>
				<select class="chzn-select" name="shop_validate_status_id"  style="vertical-align:top;">
				<c:forEach items="${shopTypes}" var="et">
					<option value="${et.dictionary_id}" <c:if test="${et.dictionary_id == shop.shop_validate_status_id }">selected</c:if>>${et.dictionary_name}</option>
				</c:forEach>
				</select>
				</td>
			</tr>

			
			<tr>
				<td>店铺名称：</td>
				<td><input type="text" name="shop_name" id="shop_name" value="${shop.shop_name}" readonly  title="店铺名称"/></td>
			</tr>
			<tr>
				<td>店铺编号：</td>
				<td><input type="text" name="shop_id" id="shop_id" value="${shop.shop_id}" readonly title="编号" /></td>
			</tr>
			<tr>
				<td>店铺类别：</td>
				<td><input type="text" name="shop_type_name" value="${shop.shop_type_name}" readonly /></td>
			</tr>

			 <tr>
				<td>店铺地址：</td>
				<td><input type="text" name="address" value="${shop.province}${shop.province}${shop.city}${shop.area}" readonly /></td>
			</tr>

			<tr>
				<td>责任人名称：</td>
				<td><input type="text" name="director_name" value="${shop.director_name}" readonly /></td>
			</tr>
			<tr>
				<td>责任人电话：</td>
				<td><input type="text" name="director_tel" value="${shop.director_tel}" readonly /></td>
			</tr>
			<tr>
				<td>责任人身份证号：</td>
				<td><input type="text" name="director_no" value="${shop.director_no}" readonly /></td>
			</tr>

			<tr>
				<td>营业执照编号：</td>
				<td><input type="text" name="license_no" value="${shop.license_no}" readonly /></td>
			</tr>
			<tr>
				<td>营业执照照片：</td>
				<td>
					<c:if test="${shop.license_photo!=null}">
						<img src="${shop.license_photo}" style="width: 150px ;height:150px;margin-bottom: 10px "   alt="" onclick="bigimg('${shop.license_photo}')" >
					</c:if>

				</td>
			</tr>
			<tr>
				<td>责任人身份证照片正面：</td>
				<td>
					<c:if test="${shop.director_photo_a!=null}">
					<img id="img_a" src="${shop.director_photo_a}" style="width: 150px ;height:150px ;margin-bottom: 10px"  alt="" onclick="bigimg('${shop.director_photo_a}')">
				</c:if>
					</td>
			</tr>
			<tr>
				<td>责任人身份证照片反面：</td>
				<td><c:if test="${shop.director_photo_b!=null}">
					<img src="${shop.director_photo_b}" style="width: 150px ;height:150px;margin-bottom: 10px " alt="" onclick="bigimg('${shop.director_photo_b}')" >
				</c:if>

					</td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="2">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green"></h4></div>
		
	</form>
		<div class="pan" style="display: none;position: absolute;top: 0;left: 0; bottom:0;right: 0; background: white"  onclick="pan_none()" >
			<img  style="position: absolute;top: 50%;left: 35%;" class="pan_img" src="" alt="">

		</div>
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		
		<script type="text/javascript">
		
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		});
		function bigimg(srcc) {
		    	$(".pan_img").show()
                console.log(srcc)
                $(".pan").show()
				var str = $("#img_a").attr("src")
				$(".pan_img").attr("src",srcc);
        }
		function pan_none(){
			$(".pan_img").hide()
			$(".pan").hide()
        }
		</script>
	
</body>
</html>