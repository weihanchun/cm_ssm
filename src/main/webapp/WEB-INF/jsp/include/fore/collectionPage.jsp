<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	

<script>
var deleteCollection = false;
var deleteCollectionid = 0;
$(function(){

	$("a.deleteCollection").click(function(){
		deleteCollection = false;
		var clid = $(this).attr("clid")
		deleteCollectionid = clid;
		$("#deleteConfirmModal").modal('show');	   
	});
	$("button.deleteConfirmButton").click(function(){
		deleteCollection = true;
		$("#deleteConfirmModal").modal('hide');
	});
	
	$('#deleteConfirmModal').on('hidden.bs.modal', function (e) {
		if(deleteCollection){
			var page="foredeleteCollection";
			/* alert(deleteCollectionid); */
			$.post(
				    page,
				    {"clid":deleteCollectionid},
				    function(result){
						if("success"==result){
							$("tr.cartProductItemTR[clid="+deleteCollectionid+"]").remove();
						}
						else{
							location.href="loginPage";
						}
				    }
				);
		}
	})	
})
</script>	

<title>我的收藏</title>
<div class="cartDiv">
	<div class="cartProductList">
		<table class="cartProductTable">
			<thead>
				<tr>
					<th width="120px">图片</th>
					<th width="250px">名称</th>
					<th width="400px">花语</th>
					<th>价格</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cls}" var="cl">
					<tr class="cartProductItemTR" clid="${cl.id}">
						<td>
							<img class="collectionProductImg"  src="img/productSingle_middle/${cl.product.firstProductImage.id}.jpg">
						</td>
						<td>
							<div class="cartProductLinkOutDiv">
							<br><br>
								<a href="foreproduct?pid=${cl.product.id}" class="cartProductLink">${cl.product.name}</a>
							</div>
						</td>
						<td>
						  <span>${cl.flowerLanguage}</span>
						</td>
						<td>
							<span class="cartProductItemOringalPrice">￥${cl.product.originalPrice}</span>
							<span  class="cartProductItemPromotionPrice">￥${cl.product.promotePrice}</span>
							
						</td>
						<td width="100px">
							<a class="deleteCollection" clid="${cl.id}"  href="#nowhere">移出收藏夹</a>
						</td>
					</tr>
				</c:forEach>				
			</tbody>
		
		</table>
	</div>
</div>
<div class="pageDiv" align="center">
		<%@include file="forePage.jsp"%>
</div>