<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
 
<script>
    $(function(){
        $("button.orderPageCheckOrderItems").click(function(){
            var oid = $(this).attr("oid");
            $("tr.orderPageOrderItemTR[oid="+oid+"]").toggle();/* toggle函数 切换*/
        });
    });
</script>
<title>订单管理</title>
 
<div class="workingArea">
    <h1 class="label label-info" >订单管理</h1>
    <br>
    <br>
 
    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover1  table-condensed">
            <thead>
            <tr class="success">
                <th>订单编号</th>
                <th width="60px">状态</th>
                <th>金额</th>
                <th width="70px">鲜花数量</th>
                <th width="70px">买家名称</th>
                <th>联系方式</th>
                <th width="100px">买家留言</th>
                <th width="100px">创建时间</th>
                <th width="100px">支付时间</th>
                <th width="100px">发货时间</th>
                <th width="100px">确认收货时间</th>
                <th>收货地址</th>
                <th width="120px">操作</th>
            </tr>
            </thead>
            <tbody>
            <!-- 把订单集合遍历出来 -->
            <c:forEach items="${os}" var="o">
                <tr>
                    <td>${o.orderCode}</td>
                    <td>${o.statusDesc}</td>
                    <td>￥<fmt:formatNumber type="number" value="${o.total}" minFractionDigits="2"/></td>
                    <td align="center">${o.totalNumber}</td>
                    <td align="center">${o.user.name}</td>
                    <td align="center">${o.user.phone}</td>
                    <td>${o.userMessage}</td>
                    <td><fmt:formatDate value="${o.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${o.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${o.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${o.confirmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>${o.address}</td>
                    <td>
                        <button oid=${o.id} class="orderPageCheckOrderItems btn btn-primary btn-xs">查看详情</button>
 
                        <c:if test="${o.status=='waitDelivery'}">
                            <a href="admin_order_delivery?id=${o.id}">
                                <button class="btn btn-primary btn-xs">发货</button>
                            </a>
                        </c:if>
                    </td>
                </tr>
                <tr class="orderPageOrderItemTR"  oid=${o.id}>
                    <td colspan="13" align="center">
 
                        <div class="orderPageOrderItem">
                            <table width="800px" align="center"  class="table table-striped table-bordered table-hover1  table-condensed orderPageOrderItemTable">
                                     <tr class="success">
                                            <td align="center">商品图片</td>
                                            <td align="center">商品名称</td>
                                            <td align="center">数量</td>
                                            <td align="center">单价</td>
                                    </tr>
                                <!-- 遍历订单的时候，再把当前订单的orderItem订单项集合遍历出来 -->
                                <c:forEach items="${o.orderItems}" var="oi">
                                    <tr>
                                        <td align="center">
                                            <img width="40px" height="40px" src="img/productSingle/${oi.product.firstProductImage.id}.jpg">
                                        </td>
 
                                        <td align="center">
                                            <a href="foreproduct?pid=${oi.product.id}">
                                                <span>${oi.product.name}</span>
                                            </a>
                                        </td>
                                        <td align="center">
                                            <span class="text-muted">${oi.number}个</span>
                                        </td>
                                        <td align="center">
                                            <span class="text-muted">单价：￥${oi.product.promotePrice}</span>
                                        </td>
 
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>
</div>
 
<%@include file="../include/admin/adminFooter.jsp"%>