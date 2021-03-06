<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<title>상품등록</title>

	<!-- 참조 : http://getbootstrap.com/css/   참조 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
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
    </style>
<script type="text/javascript" src="../javascript/calendar.js">
</script>
<script type="text/javascript">
	const fncAddProduct = () =>{
	
	
	$("form").attr("method","POST").attr("action","/product/addProduct").attr("enctype","multipart/form-data").submit();
}

	$(function() {
		$("button.btn.btn-primary").on("click" , function() {
			fncAddProduct();
		});
	 
		$("a[href='#' ]").on("click" , function() {
			$("form")[0].reset();
		});	
		
		
		
	});

	function readURL(input) {
	if (input.files && input.files[0]) {
		const reader = new FileReader();
		reader.onload = function(e) {
			document.getElementById('preview').src = e.target.result;
		};
		reader.readAsDataURL(input.files[0]);
	} else {
		document.getElementById('preview').src = "";
	}	
	
	
}
	

</script>

</head>

<body bgcolor="#ffffff" text="#000000">
	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->
<div class="container">
	<form class="form-horizontal">
		<div class="form-group">
			<label for="prodName" class="col-sm-offset-1 col-sm-3 control-label">상품명</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="prodName" name="prodName" placeholder="상품명" >
			</div>
		</div>
		
		<div class="form-group">
			<label for="prodDetail" class="col-sm-offset-1 col-sm-3 control-label">상품상세정보</label>
			<div class="col-sm-4">
				<textarea type="text" class="form-control" id="prodContent" name="prodContent" placeholder="상품상세설명"></textarea>
			</div>
		</div>
		
		<div class="form-group">
			<label for="manuDate" class="col-sm-offset-1 col-sm-3 control-label">카테고리</label>
			<div class="col-sm-4">
				<select id="prodCategory" name="prodCategory">
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<label for="price" class="col-sm-offset-1 col-sm-3 control-label">가격</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="price" placeholder="원">
			</div>
		</div>		
		
		<div class="form-group">
			<label for="stock" class="col-sm-offset-1 col-sm-3 control-label">상품이미지</label>
			<div class="col-sm-4">				
				<input type="file" name="file" multiple="multiple" required="required" class="file" onchange="readURL(this);" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="stock" class="col-sm-offset-1 col-sm-3 control-label">미리보기</label>
			<div class="col-sm-4">				
				<img id="preview" style="width:200px; height:200px;">
			</div>
		</div>		
		
		<div class="form-group">
			<div class="col-sm-offset-4  col-sm-4 text-center">
				<button type="button" class="btn btn-primary"  >등록</button>
				<a class="btn btn-primary btn" href="#" role="button">취소</a>
			</div>
		</div>
	</form>
</div>
</body>
</html>
<script>
	categoryOption();

	function categoryOption(){
		const category = ["옷","생필품","음식","장난감","기타"];
		console.log(category)
		console.log(category[0])
		let i =0;
		console.log(category)
		
		while(i < category.length){
			document.getElementById("prodCategory").options[i] = new Option(category[i], category[i]);
			i++;
		}
		
	}
</script>