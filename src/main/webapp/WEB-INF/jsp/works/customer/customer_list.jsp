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
	<!-- jsp文件头和头部 -->
	<%@ include file="../../system/admin/top.jsp"%>
</head>
<body>

<div class="container-fluid" id="main-container">


	<div id="page-content" class="clearfix">

		<div class="row-fluid">


			<div class="row-fluid">

				<!-- 检索  -->
				<form action="custmoer/listCustomer.do" method="post" name="userForm" id="userForm">
					<table>
						<tr>
							<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="USERNAME" value="${pd.USERNAME }" placeholder="这里输入关键词" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
							</td>

							<c:if test="${QX.cha == 1 }">
								<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();" title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
								</c:if>
						</tr>
					</table>
					<!-- 检索  -->


					<table id="table_report" class="table table-striped table-bordered table-hover">

						<thead>
						<tr>

							<th>序号</th>
							<th>顾客编号</th>
							<th>顾客名称</th>
                            <th>顾客昵称</th>
							<th>顾客状态</th>
							<th>顾客类型</th>

							<th class="center">操作</th>
						</tr>
						</thead>

						<tbody>

						<!-- 开始循环 -->
						<c:choose>
							<c:when test="${not empty customerList}">
								<c:if test="${QX.cha == 1 }">
									<c:forEach items="${customerList}" var="user" varStatus="vs">

										<tr>

											<td class='center' style="width: 30px;">${vs.index+1}</td>
											<td>${user.user_id }</td>
											<td>${user.user_name }</td>
											<td>${user.user_nickname }></td>
											<td>${user.user_status_name }</td>
											<td>${user.user_type_name }</td>

											<td style="width: 60px;">
												<div class='hidden-phone visible-desktop btn-group'>

													<c:if test="${QX.edit == 1 }">
													<a class='btn btn-mini btn-info' title="编辑" onclick="editUser('${user.universalid }');"><i class='icon-edit'></i></a>

													</c:if>

												</div>
											</td>
										</tr>

									</c:forEach>
								</c:if>

								<c:if test="${QX.cha == 0 }">
									<tr>
										<td colspan="10" class="center">您无权查看</td>
									</tr>
								</c:if>
							</c:when>
							<c:otherwise>
								<tr class="main_info">
									<td colspan="10" class="center">没有相关数据</td>
								</tr>
							</c:otherwise>
						</c:choose>


						</tbody>
					</table>

					<div class="page-header position-relative">
						<table style="width:100%;">
							<tr>

								<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
							</tr>
						</table>
					</div>
				</form>
			</div>




			<!-- PAGE CONTENT ENDS HERE -->
		</div><!--/row-->

	</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->

<!-- 返回顶部  -->
<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
	<i class="icon-double-angle-up icon-only"></i>
</a>

<!-- 引入 -->
<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/ace-elements.min.js"></script>
<script src="static/js/ace.min.js"></script>

<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
<!-- 引入 -->


<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
<script type="text/javascript">

    $(top.hangge());

    //检索
    function search(){
        top.jzts();
        $("#userForm").submit();
    }




    //修改
    function editUser(uuid){
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.Title ="修改顾客信息";
        diag.URL = '<%=basePath%>custmoer/goEditCustomer.do?universalid='+uuid;
        diag.Width = 400;
        diag.Height = 420;
        diag.CancelEvent = function(){ //关闭事件
            if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
                nextPage(${page.currentPage});
            }
            diag.close();
        };
        diag.show();
    }



</script>

<script type="text/javascript">

    $(function() {

        //日期框
        $('.date-picker').datepicker();

        //下拉框
        $(".chzn-select").chosen();
        $(".chzn-select-deselect").chosen({allow_single_deselect:true});

        //复选框
        $('table th input:checkbox').on('click' , function(){
            var that = this;
            $(this).closest('table').find('tr > td:first-child input:checkbox')
                .each(function(){
                    this.checked = that.checked;
                    $(this).closest('tr').toggleClass('selected');
                });

        });

    });





</script>

</body>
</html>

