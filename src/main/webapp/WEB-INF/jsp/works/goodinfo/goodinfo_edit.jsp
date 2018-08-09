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
		<style>
			.img_get,.img_up{
				padding:0 15px;
				height: 26px;
				background-color: #438eb9;
				color: #fff;
				font-size: 14px;
				display: inline-block;
				line-height: 26px;
				text-align: center;
				border-radius: 4px;
				margin-top: 20px;
				margin-left: 35%;
				cursor: pointer;
			}
			.img_up{
				padding:0 15px;
				height: 26px;
				background-color: #438eb9;
				color: #fff;
				font-size: 14px;
				display: inline-block;
				line-height: 26px;
				text-align: center;
				border-radius: 4px;
				margin-bottom: 10px;
				margin-top: 10px;
				margin-left: 43.5%;
			}
		</style>
<script type="text/javascript">


	//保存
	function save(){

        <c:if test="${msg=='save'}">

				if($("#shop_id2").find("option:selected").text()==""){
				    $("#shop_id2").tips({
						side:3,
						msg:'请选择店铺',
						bg:'#AE81FF',
						time:2
					});
					$("#shop_id2").focus();
					return false;
				}


        if($("#good_name").val()==""){
            $("#good_name").tips({
                side:3,
                msg:'请输入商品名称',
                bg:'#AE81FF',
                time:2
            });
            $("#good_name").focus();
            return false;
        }


        if($("#one").find("option:selected").text()==""){
            $("#one").tips({
                side:3,
                msg:'请选择一级分类',
                bg:'#AE81FF',
                time:2
            });
            $("#one").focus();
            return false;
        }

        if($("#two").find("option:selected").text()=="请选择"){
            $("#two").tips({
                side:3,
                msg:'请选择二级分类',
                bg:'#AE81FF',
                time:2
            });
            $("#two").focus();
            return false;
        }


        if($("#three").find("option:selected").text()==""){
            $("#three").tips({
                side:3,
                msg:'请选择三级分类',
                bg:'#AE81FF',
                time:2
            });
            $("#three").focus();
            return false;
        }

        if($("#acronym").val()==""){
            $("#acronym").tips({
                side:3,
                msg:'请输入缩略语',
                bg:'#AE81FF',
                time:2
            });
            $("#acronym").focus();
            return false;
        }

        if($("#good_unit_id").find("option:selected").text()==""){
            $("#good_unit_id").tips({
                side:3,
                msg:'请选择商品单位',
                bg:'#AE81FF',
                time:2
            });
            $("#good_unit_id").focus();
            return false;
        }

        if($("#good_introduce").val()==""){
            $("#good_introduce").tips({
                side:3,
                msg:'请输入商品介绍',
                bg:'#AE81FF',
                time:2
            });
            $("#good_introduce").focus();
            return false;
        }


        if($("#virtual_count").val()==""){
            $("#virtual_count").tips({
                side:3,
                msg:'请输入虚拟库存数量',
                bg:'#AE81FF',
                time:2
            });
            $("#virtual_count").focus();
            return false;
        }

        if($("#good_price").val()==""){
            $("#good_price").tips({
                side:3,
                msg:'请输入价格',
                bg:'#AE81FF',
                time:2
            });
            $("#good_price").focus();
            return false;
        }
        </c:if>


	    $("#Form").attr("enctype","");
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="good/${msg}.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
		<input  type="file"   multiple id="file" name="file" style="display: none"><br/>
		<input type="hidden" name="universalid" id="universalid" value="${pd.universalid}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<c:if test="${msg=='save'}">
				<tr>
					<td style="width:150px;text-align: right;padding-top: 13px;">店铺名称:</td>
					<td>
						<select name="shop_id2" id="shop_id2" style="display: block;margin: 0 auto"   onchange="findNavigationOne(this)">
							<option selected="selected" value="${pd.shop_id}">${pd.shop_name}</option>
							<c:forEach items="${shop}" var="s">
								<option  value="${s.shop_id}">${s.shop_name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</c:if>
			<c:if test="${msg=='edit'}">
				<tr>
					<td style="width:150px;text-align: right;padding-top: 13px;">店铺名称:</td>
					<td><input style="display: block;margin: 0 auto" type="text"  name="shop_name" readonly id="shop_name" value="${pd.shop_name}"   /></td>
				</tr>
			</c:if>

				<tr>
					<td style="width:70px;text-align: right;padding-top: 13px;">商品名称:</td>
					<td><input style="display: block;margin: 0 auto"  type="text" name="good_name" id="good_name" value="${pd.good_name}"  maxlength="32" /></td>
				</tr>

				<tr>
					<td style="width:70px;text-align: right;padding-top: 13px;">商品一级分类:</td>
					<td>
						<select style="display: block;margin: 0 auto" name="good_type_one_id"  id="one"  onchange="findGood_type_two(this)">
							<option   selected="selected" value="${pd.good_type_one_id}">${pd.good_type_one_name}</option>
							<c:forEach items="${good_type_one}" var="one">
								<option  value="${one.good_type_one_id}">${one.good_type_one_name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>

				<tr>
					<td style="width:70px;text-align: right;padding-top: 13px;">商品二级分类:</td>
					<td>

						<select id="two" style="display: block;margin: 0 auto" name="good_type_two_id"   onchange="findGood_type_three(this)">

							<option   selected="selected" value="${pd.good_type_two_id}">${pd.good_type_two_name}</option>
						</select>
					</td>
				</tr>

				<tr>
					<td style="width:70px;text-align: right;padding-top: 13px;">商品三级分类:</td>
					<td>
						<select id="three" style="display: block;margin: 0 auto" name="good_type_three_id" >
							<option   selected="selected" value="${pd.good_type_three_id}">${pd.good_type_three_name}</option>

						</select>
					</td>
				</tr>

				<tr>
					<td style="width:70px;text-align: right;padding-top: 13px;">缩略语:</td>
					<td><input style="display: block;margin: 0 auto" type="text" name="acronym" id="acronym" value="${pd.acronym}" maxlength="32"  /></td>
				</tr>

				<tr>
					<td style="width:70px;text-align: right;padding-top: 13px;">商品单位:</td>
					<td>
						<select style="display: block;margin: 0 auto" name="good_unit_id" id="good_unit_id">
							<c:if test="${msg=='edit'}">
							   <option  selected="selected" value="${pd.good_unit_id}">${pd.dictionary_name}</option>
							</c:if>
							<c:forEach items="${unit}" var="u">
								<option  value="${u.good_unit_id}">${u.dictionary_name}</option>
							</c:forEach>
						</select>
					</td>

				</tr>
				<c:if test="${msg=='edit'}">
					<tr>
						<td style="width:70px;text-align: right;padding-top: 13px;">销售状态:</td>
						<td><input style="display: block;margin: 0 auto" type="text" name="sale_good_id" id="sale_good_id" value="${pd.sale_name}" maxlength="32" readonly /></td>
					</tr>
				</c:if>
				<tr>
					<td style="width:70px;text-align: right;padding-top: 13px;">商品介绍:</td>
					<td><textarea  style="display: block;margin: 0 auto;width:500px;height: 200px;" type="text" name="good_introduce" id="good_introduce"  maxlength="32">${pd.good_introduce}</textarea></td>
				</tr>

				<tr>
					<td style="width:70px;text-align: right;padding-top: 13px;">商品虚拟库存数量:</td>
					<td><input style="display: block;margin: 0 auto" type="text" name="virtual_count" id="virtual_count" value="${pd.virtual_count}" maxlength="32"  /></td>
				</tr>

				<c:if test="${msg=='edit'}">
					<tr>
						<td style="width:70px;text-align: right;padding-top: 13px;">商品实际库存数量:</td>

						<td><input style="display: block;margin: 0 auto" type="text" name="good_count" id="good_count" value="${pd.good_count}" maxlength="32" readonly /></td>

					</tr>
				</c:if>
				<tr>
					<td style="width:70px;text-align: right;padding-top: 13px;">商品价格:</td>
					<td>
						<input style="display: block;margin: 0 auto" type="text" name="good_price" id="good_price" value="${pd.good_price}" maxlength="32" />
						<input style="display: block;margin: 0 auto" type="hidden" name="good_id" id="good_id" value="${pd.good_id}" maxlength="32" />
						<input style="display: block;margin: 0 auto" type="hidden" name="shop_id" id="shop_id" value="${pd.shop_id}" maxlength="32" />
					</td>
				</tr>

				<c:if test="${msg=='save'}">
					<tr>
						<td style="width:70px;text-align: right;padding-top: 13px;">商品图片:</td>
						<td>
							<input type="hidden" name="good_img_id" id="good_img_id" value=""/>
							<img id="selectImg" style="width: 350px;height: 250px;margin-left: 100px;" src="static/images/add.jpg" title="无图"><br/>



							<a class="img_get" onclick="img()">选择图片，点击上传</a>
								<%--路径：--%><input type="hidden" id="url" name="filedir" value="good"/><br/>
							<a class="img_up" onclick="upImg()">上传</a>
							<input type="hidden" id="delFile" value="" />



							<%--选择文件:<a onclick="img()">选择文件</a>
							&lt;%&ndash;路径：&ndash;%&gt;<input type="hidden" id="url" name="filedir" value="good"/><br/>
							<a onclick="upImg()">上传</a>
							<input type="hidden" id="delFile" value="" />--%>
						</td>
					</tr>
				</c:if>
				<c:if test="${msg=='edit'}">
					<tr>
						<td style="width:70px;text-align: right;padding-top: 13px;">商品图片:</td>
						<td>
							<img style="width: 400px;height: 250px;margin-left: 100px;" src="${pd.good_img_id}" alt="无图">
						</td>
					</tr>
				</c:if>
				<tr>
					<td style="width:70px;text-align: right;padding-top: 13px;">一级导航名称:</td>
					<td>
						<select style="display: block;margin: 0 auto" id="one_navigation" name="navigation_id" onchange="findTwoNavigation(this)">
							<c:if test="${msg=='edit'}">
							    <option  selected="selected" value="${pd.one_navigation_id}">${pd.one_navigation_name}</option>
							</c:if>

								<c:forEach items="${navigation}" var="n">
							    	<option  value="${n.navigation_id}">${n.navigation_name}</option>
						    	</c:forEach>
						</select>
					</td>
				</tr>

			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">二级导航名称:</td>
				<td>
					<select style="display: block;margin: 0 auto" id="two_navigation" name="navigation_id2" id="two_navigation">
						<option  selected="selected" value="${pd.two_navigation_id}">${pd.two_navigation_name}</option>
					</select>
				</td>
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








        function findNavigationOne(o) {
            //alert(o.value);
            var shop_id = o.value;

            $.ajax({
                type : "post",
                dataType :"json",
                cache : true,
                url : "good/findNavigationOne",
                data : {"shop_id": shop_id},//$('#offer_form').serialize()
                async : false,
                error : function(request) {
                    alert("Connection error");
                },
                success : function(data) {

                    $("#one_navigation").val(data);
                    var str='<option selected="selected" value="">请选择</option>';
                    $("#one_navigation").children().remove();
                    for (var i=0;i<data.length;i++){
                        str+='<option  value="'+data[i].navigation_id+'">'+data[i].navigation_name+'</option>'
                    }
                    $("#one_navigation").append(str);
                    $("#two_navigation").children().remove();

                }
            });

        }

      function findGood_type_two(o) {
		    //alert(o.value);
		    var one_id = o.value;

          $.ajax({
              type : "post",
              dataType :"json",
              cache : true,
              url : "good/findGood_type_two",
              data : {"one_id": one_id},//$('#offer_form').serialize()
              async : false,
              error : function(request) {
                  alert("Connection error");
              },
              success : function(data) {

                  $("#two").val(data);
                  var str='<option   selected="selected" value="">请选择</option>';
                  $("#two").children().remove();
                 for (var i=0;i<data.length;i++){
                     str+='<option  value="'+data[i].good_type_two_id+'">'+data[i].good_type_two_name+'</option>'
				 }
                  $("#two").append(str);
                  $("#three").children().remove();

              }
          });

      }



        function findGood_type_three(o) {
            //alert(o.value);
            var two_id = o.value;

            $.ajax({
                type : "post",
                dataType :"json",
                cache : true,
                url : "good/findGood_type_three",
                data : {"two_id": two_id},//$('#offer_form').serialize()
                async : false,
                error : function(request) {
                    alert("Connection error");
                },
                success : function(data) {

                    $("#three").val(data);
                    var str="";
                    $("#three").children().remove();
                    for (var i=0;i<data.length;i++){
                        str+='<option  value="'+data[i].good_type_three_id+'">'+data[i].good_type_three_name+'</option>'
                    }
                    $("#three").append(str);
                }
            });
        }


        function findTwoNavigation(o){
		    //alert(o.value);
		    var one_navigation = o.value;
            $.ajax({
                type : "post",
                dataType :"json",
                cache : true,
                url : "good/findTwoNavigation",
                data : {"one_navigation": one_navigation},//$('#offer_form').serialize()
                async : false,
                error : function(request) {
                    alert("Connection error");
                },
                success : function(data) {

                    var str="";
                    $("#two_navigation").children().remove();
                    for (var i=0;i<data.length;i++){
                        str+='<option  value="'+data[i].navigation_id+'">'+data[i].navigation_name+'</option>'
                    }
                    $("#two_navigation").append(str);
                }
            });
		}


  function img() {
	  $("#file").trigger("click");
  }

		function upImg() {
		    var Form = $("#Form");
            var formData = new FormData(Form[0]);
            $.ajax({
                url:"api/imgUpload",
                type:"POST",
                dataType:"json",
                data:formData,
                contentType: false,
                processData: false,
                success:function(data) {
                    //alert(JSON.stringify(data));
                    var img = data.data.imgUrl;
                    $("#good_img_id").val(img);
                    $("#selectImg").attr("src",img);
                    $("#delFile").val(data.data.objectName);
                    alert("图片上传成功");
                }
            });
        }


        function delOssFile() {
            $.ajax({
                type : "post",
                dataType :"json",
                cache : true,
                url : "api/delOssFile",
                data : {"objectName": $("#delFile").val()},
                async : false,
                error : function(request) {
                    alert("Connection error");
                },
                success : function(data) {
                    alert(JSON.stringify(data));
                }
            })
        }




        $("input[type='file']").change(function(){
            var file = this.files[0];
            if (window.FileReader) {
                var reader = new FileReader();
                reader.readAsDataURL(file);
                //监听文件读取结束后事件
                reader.onloadend = function (e) {
                    $("#selectImg").attr("src",e.target.result);    //e.target.result就是最后的路径地址
                };
            }
        });

		</script>
</body>
</html>