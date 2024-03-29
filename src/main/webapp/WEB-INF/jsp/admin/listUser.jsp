<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<title>用户管理</title>
<div class="workingArea">
   <h1 class="label label-info">用户管理</h1>
   <br>
   <br>
   <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>用户名称</th>
                <th>手机号</th>
                <th>密保问题</th>
                <th>密保答案</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${us}" var="u">
                <tr>
                    <td>${u.id}</td>
                    <td>${u.name}</td>
                    <td>${u.phone}</td>
                    <td>${u.question}</td>
                    <td>${u.result}</td>
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
</div>