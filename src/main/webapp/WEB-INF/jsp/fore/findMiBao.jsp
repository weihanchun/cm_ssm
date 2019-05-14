<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../include/fore/header.jsp"%>
<%@include file="../include/fore/top.jsp"%>

<script>
    $(function(){
    	<c:if test="${!empty message}">
        $("span.errorMessage").html("${message}");
        $("div.findMBErrorMessageDiv").css("visibility","visible");
        </c:if>
        
        $("input").focus(function(){
        	$("div.findMBErrorMessageDiv").css("visibility","hidden");
         });
        
        $(".MBForm").submit(function(){
            if(0==$("#phone").val().length||$("#phone").val().length!=11){
                $("span.errorMessage").html("请输入正确的号码格式");
                $("div.findMBErrorMessageDiv").css("visibility","visible");
                return false;
            }
            
            return true;
        });
    })
</script>



<form method="post" action="foreFindByMiBao" class="MBForm">


	<div class="registerDiv">
		<div class="findMBErrorMessageDiv">
			<div class="alert alert-danger" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close"></button>
				<span class="errorMessage"></span>
			</div>
		</div>


		<table class="registerTable" align="center">
		     <tr>
				<td class="registerTip registerTableLeftTD">找回密码</td>
				<td class="registerTableRightTD">填写密保答案</td>
			</tr>
			<tr>
				<td class="registerTableLeftTD">密保问题</td>
				<td class="registerTableRightTD">
				<input id="question" name="question" disabled="disabled" type="text" value="${user.question}"></td>
			</tr>
			<tr>
				<td class="registerTableLeftTD">密保答案</td>
				<td class="registerTableRightTD">
				<input id="result" name="result" type="text"  placeholder="请输入密保答案"></td>
			</tr>
			<tr>
				<td colspan="2" class="registerButtonTD">
				<input name="uid" type="hidden" value="${user.id}">
                       <button>提 交</button>
                </td>
			</tr>
		</table>
	</div>
</form>

