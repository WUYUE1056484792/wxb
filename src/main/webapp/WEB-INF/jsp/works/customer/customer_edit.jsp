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


</head>
<body>
<form action="custmoer/${msg }.do" name="userForm" id="userForm" method="post">
	<input type="hidden" name="universalid" id="universalid" value="${pd.universalid }"/>

	<div id="zhongxin" style="margin-top: 10px;margin-left: 56px" >
		<table>
			<tr>
				<td>顾客编号:</td>
				<td><input type="text" name="user_id" id="user_id" value="${pd.user_id }" maxlength="32" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>顾客名称:</td>
				<td><input type="text" name="user_name" id="user_name" value="${pd.user_name }" maxlength="32"/></td>
			</tr>
			<tr>
				<td>顾客昵称:</td>
				<td><input type="text" name="user_nickname" id="user_nickname" value="${pd.user_nickname }" maxlength="32" /></td>
			</tr>
			<tr>
				<td>顾客状态:</td>
               <td>
				   <select name="user_status_id" id="user_status_id" >

                    <option value="${pd.user_status_id}" > ${pd.user_status_name }</option>
                    <c:choose>
                    <c:when test="${not empty statusList}">
                    <c:forEach items="${statusList}" var="status" >
                        <c:if test="${status.dictionary_id !=pd.user_status_id }">
                            <option value="${status.dictionary_id}" > ${status.dictionary_name } </option>
                        </c:if>
                    </c:forEach>
                    </c:when>
                    </c:choose>
                </select>
			   </td>
			</tr>
			<tr>
				<td>顾客类型:</td>
				<td>

					<select name="user_type_id" id="user_type_id" >

						<option value="${pd.user_type_id}" > ${pd.user_type_name }</option>
						<c:choose>
							<c:when test="${not empty typeList}">
								<c:forEach items="${typeList}" var="type" >
									<c:if test="${type.dictionary_id !=pd.user_type_id }">
										<option value="${type.dictionary_id}" > ${type.dictionary_name } </option>
									</c:if>
								</c:forEach>
							</c:when>
						</c:choose>
					</select>

				</td>
			</tr>
			<tr>
				<td>顾客密码:</td>
				<td><input type="text" name="user_password" id="user_password" value="${pd.user_password }" maxlength="32" /></td>
			</tr>
			<tr>
				<td>顾客邮箱:</td>
				<td><input type="text" name="user_email" id="user_email" value="${pd.user_email }" maxlength="32" /></td>
			</tr>
			<tr>
				<td>顾客电话:</td>
				<td><input type="text" name="user_tel" id="user_tel" value="${pd.user_password }" maxlength="32" readonly="readonly"/></td>
			</tr>



		</table>
		<div style="margin-top: 10px;display: flex;justify-content: space-around;padding: 0 50px;width: 175px;" >
		<button  class="btn btn-mini btn-primary" type="submit">确定</button>
		<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
		</div>
	</div>

	<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green"></h4></div>

</form>

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

</script>

</body>
</html>