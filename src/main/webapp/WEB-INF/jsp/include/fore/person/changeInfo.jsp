<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@include file="../include/fore/header.jsp"%>
<%@include file="../include/fore/top.jsp"%> --%>
<script>
    $(function(){
    	<c:if test="${!empty msg}">
        $("span.errorMessage").html("${msg}");
        $("div.infoErrorMessageDiv").css("visibility","visible");
        </c:if>

        $("input").focus(function(){
        	$("div.pwdErrorMessageDiv").css("visibility","hidden");
         });
           
        $(".changeInfo").submit(function(){
            
        	if(0==$("#name").val().length){
                $("span.errorMessage").html("用户名不能为空");
                $("div.infoErrorMessageDiv").css("visibility","visible");
                return false;
            }
        	
            if(0==$("#phone").val().length||$("#phone").val().length !=11){
                $("span.errorMessage").html("请输入正确的手机号码格式");
                $("div.infoErrorMessageDiv").css("visibility","visible");
                return false;
            }
            return true;
        });
    })
</script>
<div class="changeinfoDiv" >
	<div class="changeinfoTopPart">
		<a href="#nowhere" class="changeinfoTopPartSelectedLink selected">个人信息</a>
		<a href="#nowhere" class="changeinfoTopchangepwdLink">修改密码</a>
	</div>
	<div class="changeinfoPart">
		<form method="post" action="forechangeinfo" class="changeInfo">
			<div class="registerDiv">
				<div class="infoErrorMessageDiv">
					<div class="alert alert-danger" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close"></button>
						<span class="errorMessage"></span>
					</div>
				</div>
				<table class="registerTable" align="center">
				    <tr>
				        <td class="registerTip  registerTableLeftTD">个人信息</td>
				    </tr>
					<tr>
						<td class="registerTableLeftTD">用户名</td>
						<td class="registerTableRightTD"><input id="name" name="name"
							type="text" value="${u.name}"></td>
					</tr>
					<tr>
						<td class="registerTableLeftTD">手机号码</td>
						<td class="registerTableRightTD"><input id="phone"
							name="phone" type="text" value="${u.phone}"></td>
					</tr>
					<tr>
						<td colspan="2" class="registerButtonTD"><button>保存更改</button></td>
					</tr>
				</table>
			</div>
		</form>
</div>
</div>