<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="navitagorDiv">
	<nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
		 <a class="navbar-brand" href="#nowhere">花店后台</a>
		<c:if test="${empty admin}">
			<a class="navbar-brand" href="adminLoginPage">管理员登录</a>
		</c:if>
		<c:if test="${!empty admin}">
			<a class="navbar-brand" href="admin_category_list">鲜花管理</a>
			<a class="navbar-brand" href="admin_user_list">用户管理</a>
			<a class="navbar-brand" href="admin_order_list">订单管理</a>
			<a class="navbar-brand" href="adminlogout">退出登录</a>
		</c:if>
	</nav>
</div>