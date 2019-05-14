<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
    $(function(){
/* 当账号提交到服务端，服务端判断当前账号已经存在的情况下，显示返回的错误提示 '用户名已经被使用,不能使用' */
    	<c:if test="${!empty message}">
        $("span.errorMessage").html("${message}");
        $("div.registerErrorMessageDiv").css("visibility","visible");
        </c:if>
        
        $("input").focus(function(){
        	$("div.registerErrorMessageDiv").css("visibility","hidden");
         });
        
        $(".registerForm").submit(function(){
            if(0==$("#name").val().length){
                $("span.errorMessage").html("请输入用户名");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            if(0==$("#password").val().length){
                $("span.errorMessage").html("请输入密码");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            if(0==$("#password").val().length){
                $("span.errorMessage").html("请输入密码");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            if(0==$("#question").val().length){
                $("span.errorMessage").html("请设置密保问题，用于找回密码");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            if(0==$("#result").val().length){
                $("span.errorMessage").html("请输入密保答案");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            if(0==$("#phone").val().length||$("#phone").val().length !=11){
                $("span.errorMessage").html("请输入正确的手机号码格式");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            
            if($("#password").val().length<6||$("#password").val().length > 12){
                $("span.errorMessage").html("密码长度应在6-12位之间");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            
            if(0==$("#repeatpassword").val().length){
                $("span.errorMessage").html("请输入重复密码");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            if($("#password").val() !=$("#repeatpassword").val()){
                $("span.errorMessage").html("重复密码不一致");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }

            return true;
        });
    })
</script>



<form method="post" action="foreregister" class="registerForm">


	<div class="registerDiv">
		<div class="registerErrorMessageDiv">
			<div class="alert alert-danger" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close"></button>
				<span class="errorMessage"></span>
			</div>
		</div>


		<table class="registerTable" align="center">
			<tr>
				<td class="registerTip registerTableLeftTD">设置会员名</td>
				<td class="registerTableRightTD">用于登录</td>
			</tr>
			<tr>
				<td class="registerTableLeftTD">登录名</td>
				<td class="registerTableRightTD">
				<input id="name" name="name" placeholder="设置登录名"></td>
			</tr>
			<tr>
				<td class="registerTip registerTableLeftTD">设置登录密码</td>
				<td class="registerTableRightTD">登录时验证，保护账号信息</td>
			</tr>
			<tr>
				<td class="registerTableLeftTD">登录密码</td>
				<td class="registerTableRightTD">
				<input id="password" name="password"  type="password" placeholder="设置登录密码"></td>
			</tr>
			<tr>
				<td class="registerTableLeftTD">密码确认</td>
				<td class="registerTableRightTD">
				<input id="repeatpassword" type="password"  placeholder="请再次输入你的密码"></td>
			</tr>
			<tr><td class="registerTip registerTableLeftTD">设置手机号码</td>
			</tr>
			<tr>
				<td class="registerTableLeftTD">手机号码</td>
				<td class="registerTableRightTD">
				<input id="phone" name="phone" placeholder="请输入手机号码" ></td>
			</tr>
            <tr>
				<td class="registerTip registerTableLeftTD">设置密保问题</td>
				<td class="registerTableRightTD">用于找回密码</td>
			</tr>
			<tr>
				<td class="registerTableLeftTD">密保问题</td>
				<td class="registerTableRightTD">
				<input id="question" name="question"  type="text" placeholder="一旦设置无法修改"></td>
			</tr>
			<tr>
				<td class="registerTableLeftTD">密保答案</td>
				<td class="registerTableRightTD">
				<input id="result" name="result" type="text"  placeholder="设置密保答案"></td>
			</tr>
			<tr>
				<td colspan="2" class="registerButtonTD">
                       <button>提 交</button>
                </td>
			</tr>
		</table>
	</div>
</form>