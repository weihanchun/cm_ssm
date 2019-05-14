<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../include/fore/header.jsp"%>
<%@include file="../include/fore/top.jsp"%>

 <script>
    $(function(){
    	<c:if test="${!empty message}">
        $("span.errorMessage").html("${message}");
        $("div.findPwdErrorMessageDiv").css("visibility","visible");
        </c:if>
        
        $("input").focus(function(){
        	$("div.findPwdErrorMessageDiv").css("visibility","hidden");
         });
        
        $(".PwdForm").submit(function(){
            if(0==$("#npassword").val().length){
                $("span.errorMessage").html("请输入新密码");
                $("div.findPwdErrorMessageDiv").css("visibility","visible");
                return false;
            }
            if($("#npassword").val().length < 6||$("#npassword").val().length > 12){
                $("span.errorMessage").html("密码长度应在6-12位之间");
                $("div.findPwdErrorMessageDiv").css("visibility","visible");
                return false;
            }
            if(0==$("#repeatpassword").val().length){
                $("span.errorMessage").html("请输入确认密码");
                $("div.findPwdErrorMessageDiv").css("visibility","visible");
                return false;
            }
            if($("#npassword").val() !=$("#repeatpassword").val()){
                $("span.errorMessage").html("重复密码不一致");
                $("div.findPwdErrorMessageDiv").css("visibility","visible");
                return false;
            }
            return true;
        });
    })
</script>
<form method="post" action="foreFindByPwd" class="PwdForm">

	<div class="registerDiv">
		<div class="findPwdErrorMessageDiv">
			<div class="alert alert-danger" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close"></button>
				<span class="errorMessage"></span>
			</div>
		</div>


		<table class="registerTable" align="center">
			<tr>
				<td class="registerTip registerTableLeftTD">修改密码</td>
				<td class="registerTableRightTD">登录时验证，保护账号信息</td>
			</tr>
			<tr>
				<td class="registerTableLeftTD">新密码</td>
				<td class="registerTableRightTD">
				<input id="npassword" name="npassword"  type="password" placeholder="设置你的新密码"></td>
			</tr>
			<tr>
				<td class="registerTableLeftTD">密码确认</td>
				<td class="registerTableRightTD">
				<input id="repeatpassword" type="password"  placeholder="请再次输入新密码"></td>
			</tr>

			<tr>
				<td colspan="2" class="registerButtonTD">
				  <input name="uid" type="hidden"  value="${user.id}">
				  <button>提 交</button>
				</td>
			</tr>
		</table>
	</div>
</form>
