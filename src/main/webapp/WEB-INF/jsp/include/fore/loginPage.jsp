<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
    $(function(){

        <c:if test="${!empty message}">
        $("span.errorMessage").html("${message}");
        $("div.loginErrorMessageDiv").show();
        </c:if>

        $("form.loginForm").submit(function(){
            if(0==$("#name").val().length||0==$("#password").val().length){
                $("span.errorMessage").html("请输入账号密码");
                $("div.loginErrorMessageDiv").show();
                return false;
            }
            return true;
        });

        $("form.loginForm input").keyup(function(){
            $("div.loginErrorMessageDiv").hide();
        });



        var left = window.innerWidth/2+162;
        $("div.loginSmallDiv").css("left",left);
    })
</script>


<div id="loginDiv" style="position: relative">
    <!-- 首页链接 -->
	<div class="simpleLogo">
		<a href="${contextPath}"><img src="img/site/logo1.png"></a>
	</div>


	<img id="loginBackgroundImg" class="loginBackgroundImg" src="img/site/loginBackground.png">

	<form class="loginForm" action="forelogin" method="post">
		<div id="loginSmallDiv" class="loginSmallDiv">
			<div class="loginErrorMessageDiv">
				<div class="alert alert-danger" >
					<button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
					<span class="errorMessage"></span>
				</div>
			</div>

			<div class="login_acount_text">用户登录</div>
			<div class="loginInput " >
				<span class="loginInputIcon ">
					<span class=" glyphicon glyphicon-user"></span>
				</span>
				<input id="name" name="name" placeholder="用户名" type="text">
			</div>

			<div class="loginInput " >
				<span class="loginInputIcon ">
					<span class=" glyphicon glyphicon-lock"></span>
				</span>
				<input id="password" name="password" type="password" placeholder="密码" type="text">
			</div>


			<div>
				<a href="foreFind">忘记登录密码</a>
				<a href="registerPage" class="pull-right">免费注册</a>
			</div>
			<div style="margin-top:20px">
				<button class="btn btn-block redButton" type="submit">登录</button>
			</div>
		</div>
	</form>


</div>	