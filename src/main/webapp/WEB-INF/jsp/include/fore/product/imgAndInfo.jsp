<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script>
 
$(function(){
    var stock = ${p.stock};
    $(".productNumberSetting").keyup(function(){
        var num= $(".productNumberSetting").val();
        num = parseInt(num);
        if(isNaN(num))
            num= 1;
        if(num<=0)
            num = 1;
        if(num>stock)
            num = stock;
        $(".productNumberSetting").val(num);
    });
     
    $(".increaseNumber").click(function(){
        var num= $(".productNumberSetting").val();
        num++;
        if(num>stock)
            num = stock;
        $(".productNumberSetting").val(num);
    });
    $(".decreaseNumber").click(function(){
        var num= $(".productNumberSetting").val();
        --num;
        if(num<=0)
            num=1;
        $(".productNumberSetting").val(num);
    });
     
    //$(".addCollectionButton").removeAttr("disabled");
    $(".addCollectionLink").click(function(){//收藏监听 （使用ajax的方式访问forechecklogin）
        var page = "forecheckLogin";
        $.get(
                page,
                function(result){
                    if("success"==result){
                        var pid = ${p.id};
                        var addCollectionpage = "foreaddCollection";
                        $.get(
                                addCollectionpage,
                                {"pid":pid},
                                //alert(addCollectionpage),
                                function(result){
                                    if("success"==result){
                                        $(".addCollectionButton").html("已收藏");
                                        $(".addCollectionButton").attr("disabled","disabled");
                                        $(".addCollectionButton").css("background-color","lightgray")
                                        $(".addCollectionButton").css("border-color","lightgray")
                                        $(".addCollectionButton").css("color","black")
                                    }
                                    else{
                                    	 
                                    }
                                }
                        );                          
                    }
                    else{
                        $("#loginModal").modal('show');                     
                    }
                }
        );      
        return false;
    });
    
    $(".addCartButton").removeAttr("disabled");
    $(".addCartLink").click(function(){//加入购物车监听 （使用ajax的方式访问forechecklogin）
        var page = "forecheckLogin";
        $.get(
                page,
                function(result){
                    if("success"==result){
                        var pid = ${p.id};
                        var num= $(".productNumberSetting").val();
                        var add=$(".addAjax").html();
                        var addCartpage = "foreaddCart";
                        $.get(
                                addCartpage,
                                {"pid":pid,"num":num},
                                function(result){
                                    if("success"==result){
                                        $(".addCartButton").html("已加入购物车");
                                        $(".addCartButton").attr("disabled","disabled");
                                        $(".addCartButton").css("background-color","lightgray")
                                        $(".addCartButton").css("border-color","lightgray")
                                        $(".addCartButton").css("color","black")
                                        add++;
                                        $(".addAjax").html(add);//点击加入购物车之后，ajax异步刷新购物车商品数量增加
                                    }
                                }
                        );                          
                    }
                    else{
                        $("#loginModal").modal('show');                     
                    }
                }
        );      
        return false;
    });
    $(".buy").click(function(){//监听购买按钮
        var page = "forecheckLogin";
        $.get(
                page,
                function(result){
                    if("success"==result){
                        var number = $(".productNumberSetting").val();
                        location.href= $(".buy").attr("href")+"&number="+number;
                    }
                    else{
                        $("#loginModal").modal('show');                     
                    }
                }
        );      
        return false;
    });
     
    $("button.loginSubmitButton").click(function(){
        var name = $("#name").val();
        var password = $("#password").val();
         
        if(0==name.length||0==password.length){
            $("span.errorMessage").html("请输入账号密码");
            $("div.loginErrorMessageDiv").show();           
            return false;
        }
         
        var page = "foreloginAjax";
        $.post(
                page,
                {"name":name,"password":password},
                function(result){
                    if("success"==result){
                        location.reload();
                    }
                    else{
                        $("span.errorMessage").html("账号密码错误");
                        $("div.loginErrorMessageDiv").show();                       
                    }
                }
        );          
         
        return true;
    });
     
    $("img.smallImage").mouseenter(function(){
        var bigImageURL = $(this).attr("bigImageURL");
        $("img.bigImg").attr("src",bigImageURL);
    });
     
    $("img.bigImg").load(//图片加载用load函数
        function(){
            $("img.smallImage").each(function(){
                var bigImageURL = $(this).attr("bigImageURL");
                img = new Image();
                img.src = bigImageURL;
                 
                img.onload = function(){
                    $("div.img4load").append($(img));
                };
            });     
        }
    );
});
 
</script>

<div class="imgAndInfo">

	<div class="imgInimgAndInfo">
		<img src="img/productSingle/${p.firstProductImage.id}.jpg"
			class="bigImg">
		<div  align="center" class="smallImageDiv">
			<c:forEach items="${p.productSingleImages}" var="pi">
				<img src="img/productSingle_small/${pi.id}.jpg"
					bigImageURL="img/productSingle/${pi.id}.jpg" class="smallImage">
			</c:forEach>
		</div>
		<div class="img4load hidden"></div>
		<div align="center">
		<a href="#nowhere" class="addCollectionLink">
		<button class="addCollectionButton">
		     收藏
		</button></a>
	   </div>
	</div>


	<div class="infoInimgAndInfo">

		<div class="productTitle">${p.name}</div>
		<div class="productSubTitle">${p.subTitle}</div>



		<div class="productPrice">
			<div class="productParamterPart">
				<div class="productParamterList">
					<c:forEach items="${pvs}" var="pv">
						<table>
							<tr>
								<td style="width:50px;height:30px">${pv.property.name}:</td>
								<td style="height:0px">${pv.value}</td>
							</tr>
							<tr>
								
							</tr>
						</table>
					</c:forEach>
				</div>
				<div style="clear: both"></div>
			</div>


			<div class="productPriceDiv">

				<div class="originalDiv">
					<span class="originalPriceDesc">价格</span> <span
						class="originalPriceYuan">¥</span> <span class="originalPrice">
						<fmt:formatNumber type="number" value="${p.originalPrice}"
							minFractionDigits="2" />
					</span>
				</div>

				<div class="promotionDiv">
					<span class="promotionPriceDesc">促销价 </span> <span
						class="promotionPriceYuan">¥</span> <span class="promotionPrice">
						<fmt:formatNumber type="number" value="${p.promotePrice}"
							minFractionDigits="2" />
					</span>
				</div>
			</div>
		</div>

		<div class="productSaleAndReviewNumber">
			<div>
				销量 <span class="redColor boldWord"> ${p.saleCount}</span>
			</div>
			<div>
				累计评价 <span class="redColor boldWord">${p.reviewCount}</span>
			</div>
		</div>
		<div class="productNumber">
			<span>数量</span> <span> <span class="productNumberSettingSpan">
					<input class="productNumberSetting" type="text" value="1">
			</span> <span class="arrow"> <a href="#nowhere"
					class="increaseNumber"> <span class="updown"> 
					<img src="img/site/increase.png">
					</span>
				</a> <span class="updownMiddle"> </span> <a href="#nowhere"
					class="decreaseNumber"> <span class="updown"> <img
							src="img/site/decrease.png">
					</span>
				</a>

			</span> 件
			</span> <span>库存${p.stock}件</span>
		</div>
		<div class="serviceCommitment">
			<span class="serviceCommitmentDesc">服务承诺</span> <span
				class="serviceCommitmentLink"> <a href="#nowhere">正品保证</a> <a
				href="#nowhere">极速退款</a> <a href="#nowhere">赠运费险</a> <a
				href="#nowhere">七天无理由退换</a>
			</span>
		</div>

		<div class="buyDiv">
			<a class="buy" href="forebuyone?pid=${p.id}">
			  <button class="buyButton">立即购买</button></a>
	       <a href="#nowhere" class="addCartLink"><button class="addCartButton">
					<span class="glyphicon glyphicon-shopping-cart"></span>加入购物车
				</button></a>
		</div>
	</div>

	<div style="clear: both"></div>

</div>