<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>��ǰ ��� ��ȸ</title>	
	<!-- ���� : http://getbootstrap.com/css/   ���� -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	
	<!-- Bootstrap Dropdown Hover CSS -->
	  <link href="/css/animate.min.css" rel="stylesheet">
	  <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
	   <!-- Bootstrap Dropdown Hover JS -->
	  <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
	  
	  
	  <!-- jQuery UI toolTip ��� CSS-->
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	 <!-- jQuery UI toolTip ��� JS-->
	 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 	<style>
		@font-face {
		    font-family: 'GmarketSansMedium';
		    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
		    font-weight: normal;
		    font-style: normal;
		}
		
		body {
			font-family: 'GmarketSansMedium';
			padding-top : 100px;
		}
		.addBtn{
	    	display:flex;
	    	background-color:#001D6E;
	    	width:50px;
	    	height:50px;
	    	border-radius:100%;
	    	justify-content:center;
	    	align-items:center;	    	
	    }
	</style>
<script type="text/javascript">
	// �˻� / page �ΰ��� ��� ��� Form ������ ���� JavaScrpt �̿�
	const num = /^[0-9]+/g;
	
	function fncGetUserList(currentPage) {
		const con = document.getElementById("searchCondition").value;
		const keyword = document.getElementById("searchKeyword").value;
		//if(keyword != ""){
		//	if(!num.test(keyword)){
		//		alert("���ڸ�");
		//		return;
		//	}
		//}		
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();
	}
	
	
	function fncPageProductList(currentPage){
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();
	}
	
	function fncSearchPrice(currentPage){
		const price = $('.sPriceKeyword').val();	
		console.log(price)
		if(price != ""){
			if(!num.test(price)){
				alert("���ڸ�");
				return;
			}
		}
		
		document.detailForm.action = "/product/listProduct?menu=${param.menu }&searchValue=5";
		document.getElementById("currentPage").value = currentPage;		
	   	document.detailForm.submit();
	}
	
	$(function(){
		$( ".btn-primary" ).on("click" , function() {				
			fncGetUserList('1');			
		});
		
		const List = [];		
		$.getJSON("/product/json/listProduct/key",
				(data,status)=>{
					if(status ==='success'){
						for(let i=0; i<data.length; i++){
							List.push(data[i].prodName);	
						}
					}
				}
		)
		
		$( ".addBtn" ).on("click" , function() {
	 		//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$(self.location).attr("href","/product/addProductView.jsp");
		});
		
		$("#searchKeyword").autocomplete({  //���� ���ø�Ʈ ����
			source: List,	// source�� data.js���� ������ List �迭
			focus : function(event, ui) { // ����Ű�� �ڵ��ϼ��ܾ� ���� �����ϰ� �������	
				return false;
			},
			minLength: 1,// �ּ� ���ڼ�
			delay: 100,	
		}); 
		  
        
		
		
		$(function(){
			$('.list-group').children('.list-group-item').on('click',function(){
				$('.list-group').children('.list-group-item').attr('class','list-group-item');
				$(this).attr('class','list-group-item active');
				
				console.log(123)
				if($(this).data('value') === 5 ){
					if($(".sPriceKeyword").val() === "" || $(".bPriceKeyword").val() ===""){
						return;
					} else {
						fncSearchPrice('1');
					}
				} else {				
					a($(this).data('value'),$(this).text())
				}
			})			
		});
		
		function a(param,name){
			self.location = "/product/listProduct?&searchValue="+param;
		}
		
		$($('.list-group').children('.list-group-item')[${search.searchValue}]).attr('class','list-group-item active');
	})
	
</script>
</head>
<body>
<!-- ToolBar Start /////////////////////////////////////-->
<jsp:include page="/layout/toolbar.jsp" />
<!-- ToolBar End /////////////////////////////////////-->

<div style="width:98%; margin-left:10px;" id="container">

<form name="detailForm" action="/product/listProduct?menu=${param.menu }&searchValue=0" method="post">
<input type="hidden" id="currentPage" name="currentPage" value=""/>
<div class="row">
	<div class="col-md-1"></div>
	<div class="col-md-2">
		<div class="list-group">
			 <a href="#" class="list-group-item" data-value="0">��ǰ��ȣ��</a>
			 <a href="#" class="list-group-item" data-value="1">�̸� ����</a>
			 <a href="#" class="list-group-item" data-value="2">���ݳ�����</a>
			 <a href="#" class="list-group-item" data-value="3">����Ϻ�����</a>
			 <a href="#" class="list-group-item" data-value="4">���ȸ�����</a>
			 <div class="list-group-item" data-value="5">
		  	<details>
				<summary>���� ��</summary>
				<c:if test="${search.searchValue==5}">
					<input class="sPriceKeyword" name="sPriceKeyword" type="text" value="${search.searchKeyword.split(',')[0]}">
					<h3>~</h3>
					<input class="bPriceKeyword" name="bPriceKeyword" type="text" value="${search.searchKeyword.split(',')[1]}" />
				</c:if>
				<c:if test="${search.searchValue !=5}">
					<input class="sPriceKeyword" name="sPriceKeyword" type="text">
					<h3>~</h3>
					<input class="bPriceKeyword" name="bPriceKeyword" type="text" />
				</c:if>
				<div class="priceSearch">�˻�</div>
			</details>
			</div>
		</div>
		
	</div>
	<div class="col-md-6 display">
		<div style="text-align:center;">
			<input type="hidden" name="searchCondition" id="searchCondition" value="2" ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>
			<div class="col-md-10">
				<input name="searchKeyword" type="text" class="form-control" id="searchKeyword" placeholder="Search">
			</div>
			<button type="submit" class="btn btn-primary col-md-2">�˻�</button>			
		</div>
		<div class="disW">		
			<jsp:include page="productCard.jsp" />
		</div>
	</div>
	<div class="col-md-2" style="position: fixed; right: 5%; bottom: 5%;">
		<div class="addBtn">
			<h1 style="color:#fff; font-size:50px;">+</h1>
		</div>
	</div>
</div>


</form>
</div>

</body>
</html>