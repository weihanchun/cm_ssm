<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	
<script>
$(function(){
	$("ul.pagination li.disabled a").click(function(){
		return false;
	});
});
</script>
<nav>
  <ul class="pagination">
    <li <c:if test="${!page.hasPreviouse}">class="disabled"</c:if>>
      <a  href="?start=0${page.param}" aria-label="Previous" >
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>

    <li <c:if test="${!page.hasPreviouse}">class="disabled"</c:if>>
      <a  href="?start=${page.start-page.count}${page.param}" aria-label="Previous" >
        <span aria-hidden="true">&lsaquo;</span>
      </a>
    </li>    

<!-- 中间页 -->
    <c:forEach begin="0" end="${page.totalPage-1}" varStatus="status">
    <c:if test="${status.count*page.count-page.start<=20 && status.count*page.count-page.start>=-10}">
    	<!-- current：当前这次迭代的（集合中的）项
    	     index：当前这次迭代从 0 开始的迭代计数
             count：当前这次迭代从 1 开始的迭代计数 -->
		    <li <c:if test="${status.index*page.count==page.start}">class="disabled"</c:if>>
		    	<a href="?start=${status.index*page.count}${page.param}"
		    	<c:if test="${status.index*page.count==page.start}">class="current"</c:if>
		    	>${status.count}</a>
		    </li>
	</c:if>
    </c:forEach>

    <li <c:if test="${!page.hasNext}">class="disabled"</c:if>>
      <a href="?start=${page.start+page.count}${page.param}" aria-label="Next">
        <span aria-hidden="true">&rsaquo;</span>
      </a>
    </li>
    <li <c:if test="${!page.hasNext}">class="disabled"</c:if>>
      <a href="?start=${page.last}${page.param}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
