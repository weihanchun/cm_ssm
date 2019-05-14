<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="top ">
	<a href="forehome">
	 <span style="color: #C40000; margin: 0px"	class=" glyphicon glyphicon-home redColor"></span> 首页
	</a>
	<span>欢迎来到此木花店</span>

	<c:if test="${!empty sessionScope.user}">
		<a href="foreperson">${user.name}</a>
	    <a href="forepassword">修改密码</a>
		<a href="forelogout">退出</a>
	<span class="pull-right">
	 <a href="foreaddress">地址管理</a>
	 <a href="forecollection">我的收藏</a>
	 <a href="foreorder">我的订单</a>
	 <a href="forecart">
	 <span style="color: #C40000; margin: 0px" class=" glyphicon glyphicon-shopping-cart redColor"></span> 购物车      <strong class="addAjax">${cartTotalItemNumber}</strong>件
	 </a>
	</span>
	</c:if>

	<c:if test="${empty sessionScope.user}">
		<a href="loginPage">你好，请登录</a>
		<a href="registerPage">用户注册</a>
		<a href="adminLoginPage">花店管理</a>
	</c:if>

	


</nav>



