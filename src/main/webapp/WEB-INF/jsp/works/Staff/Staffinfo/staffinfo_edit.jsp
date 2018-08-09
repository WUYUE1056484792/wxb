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
		
		<link rel="stylesheet" href="static/css/datepicker.css" /><!-- 日期框 -->
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
<script type="text/javascript">
	
	
	//保存
	function save(){
			if($("#DEPOT_ID").val()==""){
			$("#DEPOT_ID").tips({
				side:3,
	            msg:'请输入仓库编号',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#DEPOT_ID").focus();
			return false;
		}
		if($("#DEPOT_NAME").val()==""){
			$("#DEPOT_NAME").tips({
				side:3,
	            msg:'请输入仓库名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#DEPOT_NAME").focus();
			return false;
		}
		if($("#DEPOT_TYPE").val()==""){
			$("#DEPOT_TYPE").tips({
				side:3,
	            msg:'请输入仓库类型',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#DEPOT_TYPE").focus();
			return false;
		}
		
		if($("#DEPOT_ADDRESS").val()==""){
			$("#DEPOT_ADDRESS").tips({
				side:3,
	            msg:'请输入仓库地址',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#DEPOT_ADDRESS").focus();
			return false;
		}
		if($("#DEPOT_CONTACTS").val()==""){
			$("#DEPOT_CONTACTS").tips({
				side:3,
	            msg:'请输入仓库联系人',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#DEPOT_CONTACTS").focus();
			return false;
		}
		if($("#DEPOT_CONTACTS_PHONE").val()==""){
			$("#DEPOT_CONTACTS_PHONE").tips({
				side:3,
	            msg:'请输入仓库联系人电话',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#DEPOT_CONTACTS_PHONE").focus();
			return false;
		}
		if($("#REMARK").val()==""){
			$("#REMARK").tips({
				side:3,
	            msg:'请输入备注',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#REMARK").focus();
			return false;
		}
		
		
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="staff/${msg}.do" name="Form" id="Form" method="post">
		<input type="hidden" name="universalid" id="universalid" value="${pd.universalid}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">

			<c:if test="${msg=='edit'}">
				<tr>
					<td style="width:70px;text-align: right;padding-top: 13px;">员工编号:</td>
					<td><input   style="display: block;margin: 0 auto" type="text"  name="shop_staff_name" readonly id="shop_staff_id" value="${pd.shop_staff_id}"   /></td>
				</tr>
				<tr>
					<td style="width:70px;text-align: right;padding-top: 13px;">员工名称:</td>
					<td><input style="display: block;margin: 0 auto"  type="text" name="shop_staff_name" id="shop_staff_name" value="${pd.shop_staff_name}"  maxlength="32" /></td>
				</tr>


				<tr>
					<td style="width:70px;text-align: right;padding-top: 13px;">手机号:</td>
					<td><input style="display: block;margin: 0 auto" type="text" name="shop_staff_tel" id="shop_staff_tel" value="${pd.user_tel}" maxlength="32" readonly /></td>
				</tr>
			</c:if>


			<c:if test="${msg=='save'}">
				<tr>
					<td style="width:70px;text-align: right;padding-top: 13px;">员工:</td>
					<td>
					<select style="display: block;margin: 0 auto" name="user_id" id="data">
						<c:forEach items="${user}" var="u">
							<option value="${u.user_id}">${u.user_tel}</option>
						</c:forEach>
					</select>
						<input type="hidden" name="user_tel" id="user_tel" value="" />
					</td>
				</tr>
			</c:if>

				<tr>
					<td style="width:70px;text-align: right;padding-top: 13px;">角色:</td>

					<c:if test="${msg=='save'}">
						<td><select style="display: block;margin: 0 auto" name="role_id">
							<option selected="selected" value="${pd.role_id}">${pd.role_name}</option>
							<c:forEach items="${role}" var="r">
								<option value="${r.role_id}">${r.role_name}</option>
							</c:forEach>
						</select>
						</td>
					</c:if>

					<c:if test="${pd.role_id=='1'}">
						<td><input style="display: block;margin: 0 auto" type="text" readonly="true" name="role_name" id="role_name" value="${pd.role_name}" maxlength="32"  /></td>
					</c:if>

					<c:if test="${pd.role_id=='2' || pd.role_id=='3' || pd.role_id=='4'}">
						<td><select style="display: block;margin: 0 auto" name="role_id">
							<option selected="selected" value="${pd.role_id}">${pd.role_name}</option>
							<c:forEach items="${role}" var="r">
								<option value="${r.role_id}">${r.role_name}</option>
							</c:forEach>
						</select>
						</td>
					</c:if>

				</tr>

			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">店铺名称:</td>
				<c:if test="${msg=='edit'}">
					<td><input style="display: block;margin: 0 auto" type="text" readonly="true" name="shop_name" id="shop_name" value="${pd.shop_name}" maxlength="32" /></td>
				</c:if>



				<c:if test="${msg=='save'}">
					<td><select style="display: block;margin: 0 auto" name="shop_id">
						<option selected="selected" value="${pd.shop_id}">${pd.shop_name}</option>
						<c:forEach items="${shop}" var="s">
							<option value="${s.shop_id}">${s.shop_id},${s.shop_name}</option>
						</c:forEach>
					</select>
					</td>
				</c:if>

			</tr>
			
			<tr>
				<td style="text-align: center;" colspan="10">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		
	</form>
	
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript">
		$(top.hangge());
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		});


		$("#data").on("change",function () {
            var  obj=document.getElementById("data");

            for (i=0;i<obj.length;i++) {//下拉框的长度就是它的选项数.
                if (obj[i].selected== true ) {
                    var text=obj[i].text;//获取当前选择项的文本.
                    //alert(text);
                    $("#user_tel").val(text);
                }
            }
        })


		</script>
</body>
</html>