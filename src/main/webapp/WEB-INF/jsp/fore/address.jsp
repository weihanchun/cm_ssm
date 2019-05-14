<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../include/fore/header.jsp"%>
<%@include file="../include/fore/top.jsp"%>
<script>
    $(function() {
        $("#addForm").submit(function() {
            if (!checkEmpty("province","省"))
                return false;
            if (!checkEmpty("city", "市"))
                return false;
            if (!checkEmpty("county", "县"))
                return false;
            if (!checkEmpty("town", "乡/镇"))
                return false;
            if (!checkEmpty("street", "街道/村"))
                return false;
            return true;
        });
    });
    
    function syncCreateOrderButton(){
    	var selectAny = false;
    	$(".cartProductItemIfSelected").each(function(){
    		if("selectit"==$(this).attr("selectit")){
    			selectAny = true;
    		}
    	});
    	
    	if(selectAny){
    		$("button.createOrderButton").css("background-color","#C40000");
    		$("button.createOrderButton").removeAttr("disabled");
    	}
    	else{
    		$("button.createOrderButton").css("background-color","#AAAAAA");
    		$("button.createOrderButton").attr("disabled","disabled");		
    	}
    		
    }
</script>
<div class="workingArea">
	<div class="listDataTableDiv">
		<table	class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
			<tr class="success">
				<th></th>
				<th>省</th>
				<th>市</th>
				<th>县</th>
				<th>乡/镇</th>
				<th>街道/村</th>
				<th>编辑</th>
				<th>删除</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${as}" var="a" varStatus="st">
				<tr>
					<td>
					<c:if test="${a.isdefault=='Y'}">
					<span style="color:red">默认地址</span>
					</c:if>
					</td>
					<td>${a.province}</td>
					<td>${a.city}</td>
					<td>${a.county}</td>
					<td>${a.town}</td>
					<td>${a.street}</td>
					<td><a href="foreAddressedit?id=${a.id}">
					   <span class="glyphicon glyphicon-edit"></span>
					   </a>
					</td>
					<td><a href="foreAddressdelete?id=${a.id}">
						   <span class="glyphicon glyphicon-trash"></span>
						</a>
				    </td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="pageDiv" align="center">
		<%@include file="../include/fore/forePage.jsp"%>
	</div>
<div align="center">
	<div class="panel panel-warning addDiv" style="width:300px" align="center">
		<div class="panel-heading" align="center" style="width:300px">新增收货地址</div>
		<div class="panel-body" align="center">
			<form method="post" id="addForm" action="foreAddressadd">
				<table class="addTable">
					<tr>
						<td>省</td>
						<td><input id="province" name="province" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td>市</td>
						<td><input id="city" name="city" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td>县</td>
						<td><input id="county" name="county" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td>乡/镇</td>
						<td><input id="town" name="town" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td>街道/村</td>
						<td><input id="street" name="street" type="text" class="form-control"></td>
					</tr>
					<tr class="submitTR">
						<td colspan="2" align="center">
							<input type="hidden" name="uid" value="${u.id}">
							<button type="submit" class="btn btn-success">提 交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
</div>