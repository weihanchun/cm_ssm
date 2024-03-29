<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script>
var deleteOrder = false;
var deleteOrderid = 0;

$(function(){
	$("a[orderStatus]").click(function(){
		var orderStatus = $(this).attr("orderStatus");
		if('all'==orderStatus){
			$("table[orderStatus]").show();	
		}
		else{
			$("table[orderStatus]").hide();
			$("table[orderStatus="+orderStatus+"]").show();			
		}
		
		$("div.orderType div").removeClass("selectedOrderType");
		$(this).parent("div").addClass("selectedOrderType");
	});
	
	$("a.deleteOrderLink").click(function(){
		deleteOrderid = $(this).attr("oid");
		deleteOrder = false;
		$("#deleteConfirmModal").modal("show");
	});
	
	$("button.deleteConfirmButton").click(function(){
		deleteOrder = true;
		$("#deleteConfirmModal").modal('hide');
	});	

	$('#deleteConfirmModal').on('hidden.bs.modal', function (e) {
		if(deleteOrder){
			var page="foredeleteOrder";
			$.post(
				    page,
				    {"oid":deleteOrderid},
				    function(result){
						if("success"==result){//当返回success隐藏这条订单数据
							$("table.orderListItemTable[oid="+deleteOrderid+"]").hide();
						}
						else{
							location.href="loginPage";
						}
				    }
				);
		}
	})		
});

</script>
	
<div class="boughtDiv">
	<div class="orderType">
		<div class="selectedOrderType"><a orderStatus="all" href="#nowhere">所有订单</a></div>
		<div><a  orderStatus="waitPay" href="#nowhere">待付款</a></div>
		<div><a  orderStatus="waitDelivery" href="#nowhere">待发货</a></div>
		<div><a  orderStatus="waitConfirm" href="#nowhere">待收货</a></div>
		<div><a  orderStatus="waitReview" href="#nowhere" class="noRightborder">待评价</a></div>
		<div><a  orderStatus="finish" href="#nowhere" class="noRightborder">已完成订单</a></div>
		<div class="orderTypeLastOne"><a class="noRightborder">&nbsp;</a></div>
	</div>
	<div style="clear:both"></div>
	<div class="orderListTitle">
		<table class="orderListTitleTable">
			<tr>
				<td>宝贝</td>
				<td width="100px">单价</td>
				<td width="100px">数量</td>
				<td width="120px">实付款</td>
				<td width="100px">交易操作</td>
			</tr>
		</table>
	
	</div>
	
	<div class="orderListItem">
		<c:forEach items="${os}" var="o">
			<table class="orderListItemTable" orderStatus="${o.status}" oid="${o.id}">
				<tr class="orderListItemFirstTR">
					<td colspan="2">
					<b><fmt:formatDate value="${o.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></b> 
					 <span>订单号:${o.orderCode}</span>
					</td>
					<td  colspan="2">此木花店</td>
					<td colspan="1">
					</td>
					<td class="orderItemDeleteTD">
						<a class="deleteOrderLink" oid="${o.id}" href="#nowhere">
							<span class="orderListItemDelete glyphicon glyphicon-trash"></span>
						</a>
					</td>
				</tr>
				<c:forEach items="${o.orderItems}" var="oi" varStatus="st">
					<tr class="orderItemProductInfoPartTR" >
						<td class="orderItemProductInfoPartTD"><img width="80" height="80" src="img/productSingle_middle/${oi.product.firstProductImage.id}.jpg"></td>
						<td class="orderItemProductInfoPartTD">
							<div class="orderListItemProductLinkOutDiv">
								<a href="foreproduct?pid=${oi.product.id}">${oi.product.name}</a>
								<div class="orderListItemProductLinkInnerDiv">
											<img src="img/site/creditcard.png" title="支持信用卡支付">
											<img src="img/site/7day.png" title="消费者保障服务,承诺7天退货">
											<img src="img/site/promise.png" title="消费者保障服务,承诺如实描述">						
								</div>
							</div>
						</td>
						<td  class="orderItemProductInfoPartTD" width="100px">
							<div class="orderListItemProductOriginalPrice">￥<fmt:formatNumber type="number" value="${oi.product.originalPrice}" minFractionDigits="2"/></div>
							<div class="orderListItemProductPrice">￥<fmt:formatNumber type="number" value="${oi.product.promotePrice}" minFractionDigits="2"/></div>
						</td>
							<td valign="top"  class="orderListItemNumberTD orderItemOrderInfoPartTD" width="100px">
								<span class="orderListItemNumber">${oi.number}</span>
							</td>
							<td valign="top"  width="120px" class="orderListItemProductRealPriceTD orderItemOrderInfoPartTD">
								<div class="orderListItemProductRealPrice">￥<fmt:formatNumber  minFractionDigits="2"  maxFractionDigits="2" type="number" value="${oi.product.promotePrice*oi.number}"/></div>
								<div class="orderListItemPriceWithTransport">(含运费：￥0.00)</div>
							</td>
							<c:if test="${o.status != 'waitReview'}">
							<c:if test="${st.count==1}">
						    <td valign="top" rowspan="${fn:length(o.orderItems)}" class="orderListItemButtonTD orderItemOrderInfoPartTD" width="100px">
								<c:if test="${o.status=='waitConfirm' }">
									<a href="foreconfirmPay?oid=${o.id}">
										<button class="orderListItemConfirm">确认收货</button>
									</a>
								</c:if>
								
								<c:if test="${o.status=='waitPay'}">
									<a href="forepay?oid=${o.id}&total=${o.total}">
										<button class="orderListItemConfirm">付款</button>
									</a>								
								</c:if>
								
							    <c:if test="${o.status=='finish'}">
									<span>订单已完成</span>
								</c:if>
								
								<c:if test="${o.status=='waitDelivery'}">
									<span>待发货</span>
								</c:if>
							</td>
						 </c:if>
						</c:if>
						<c:if test="${o.status=='waitReview'}">
						  <td valign="top" class="orderListItemButtonTD orderItemOrderInfoPartTD" width="100px">
							  <c:choose>
							    <c:when test="${oi.isreview=='Y'}">
							        <span>已评价</span>
							    </c:when>
							    <c:otherwise>
							       <a id="review" href="forereview?oid=${o.id}&oiid=${oi.id}">
				     			      <button class="orderListItemReview">评价</button>
							       </a>
							     </c:otherwise>
							  </c:choose>
				    	  </td>
						</c:if>
					</tr>
				</c:forEach>		
			</table>
		</c:forEach>
	</div>
</div>
