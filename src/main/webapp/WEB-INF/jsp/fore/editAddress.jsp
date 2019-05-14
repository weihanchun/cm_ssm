<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<div class="workingArea">
	<div class="panel panel-warning editDiv">
		<div class="panel-heading">修改地址</div>
		<div class="panel-body">
			<form method="post" id="editForm" action="foreAddressupdate">
				<table class="editTable">
					<tr>
						<td>省</td>
						<td><input id="province" name="province" value="${a.province}"
								   type="text" class="form-control"></td>
					</tr>
					<tr>
						<td>市</td>
						<td><input id="city" name="city" value="${a.city}"
								   type="text" class="form-control"></td>
					</tr>
					<tr>
						<td>县</td>
						<td><input id="county" name="county" value="${a.county}"
								   type="text" class="form-control"></td>
					</tr>
					<tr>
						<td>乡/镇</td>
						<td><input id="town" name="town" value="${a.town}"
								   type="text" class="form-control"></td>
					</tr>
					<tr>
						<td>街道/村</td>
						<td><input id="street" name="street" value="${a.street}"
								   type="text" class="form-control"></td>
					</tr>
					<tr class="submitTR">
						<td colspan="2" align="center">
							<input type="hidden" name="id" value="${a.id}">
							<button type="submit" class="btn btn-success">提 交</button></td>
					</tr>
				</table>
			</form>
			<a href="foreDefaultAddress?aid=${a.id}">
               <button class="btn btn-primary btn-xs">设置为默认地址</button>
            </a>
		</div>
	</div>
</div>