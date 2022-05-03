<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<!-- ���� : http://getbootstrap.com/css/   ���� -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>

<title>Insert title here</title>
<script type="text/javascript" src="../javascript/calendar.js"></script>
<script type="text/javascript">
	$(()=>{
		$( ".btn:contains('����')" ).on("click" , function() {
			$("form").attr("method","POST").attr("action","/purchase/addPurchase").submit();
		});
		
		$( "td.ct_btn01:contains('���')" ).on("click" , function() {
			history.go(-1);
		});
	})
</script>
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
</head>

<body>
<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->
<div class="container">
	<form class="form-horizontal">
		<input type="hidden" name="prodNo" value="${product.prodNo}" />		
		<input type="hidden" name="buyerId" value="${user.userId}" />
		<div class="form-group">
			<div class="col-sm-2"></div>
			<div class="col-sm-3">				
				<img id="preview" style="width:200px; height:200px;" src="../images/uploadFiles/${product.fileName }">
			</div>
			<div class="col-sm-5">
				<div>
					<label>��ǰ��ȣ : ${product.prodNo}</label>
				</div>
				<div>
					<label>��ǰ�� : ${product.prodName}</label>	
				</div>
				<div>
					<label>��ǰ������ : ${product.prodDetail}</label>	
				</div>
				<div>
					<label>�������� : ${product.manuDate}</label>	
				</div>
				<div>
					<label>������� : ${product.regDate}</label>	
				</div>
				<div>
					<label>������� : ${product.regDate}</label>	
				</div>
				<div>
					<label>���� : ${product.price}</label>	
				</div>
			</div>
			<div class="col-sm-2"></div>
		</div>
		
		
		<div class="form-group">
			<label for="stock" class="col-sm-offset-1 col-sm-3 control-label">������ ���̵�</label>
			<div class="col-sm-4">
				<label>${user.userId}</label>
			</div>
		</div>
		
		<div class="form-group">
			<label for="stock" class="col-sm-offset-1 col-sm-3 control-label">���Ź��</label>
			<div class="col-sm-4">
				<select 	name="paymentOption"		class="ct_input_g" 
							style="width: 100px; height: 19px">
					<option value="1" selected="selected">���ݱ���</option>
					<option value="2">�ſ뱸��</option>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<label for="stock" class="col-sm-offset-1 col-sm-3 control-label">����</label>
			<div class="col-sm-4">
				<select id="useCheck" name="couponNo" style="width: 100px; height: 19px" maxLength="20">
					<option value="0">�Ⱦ�</option>
					<c:forEach var="coupon" items="${user.coupon}">
						<c:set var="i" value="${ i+1 }" />
						<c:if test="${coupon.salePercent==1}">
							<option value="${coupon.couponNo}">10%</option>
						</c:if>
						<c:if test="${coupon.salePercent==2}">
							<option value="${coupon.couponNo}">20%</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<label for="stock" class="col-sm-offset-1 col-sm-3 control-label">���</label>
			<div class="col-sm-4">
				<select id="stock" 	name="stock"		class="ct_input_g" 
							style="width: 100px; height: 19px">
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<label for="stock" class="col-sm-offset-1 col-sm-3 control-label">�������̸�</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="receiverName" name="receiverName" value="${user.userName }" >
			</div>
		</div>
		
		<div class="form-group">
			<label for="receiverPhone" class="col-sm-offset-1 col-sm-3 control-label">�����ڿ���ó</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="receiverPhone" name="receiverPhone" value="${user.phone }" >
			</div>
		</div>
		
		<div class="form-group">
			<label for="divyAddr" class="col-sm-offset-1 col-sm-3 control-label">�������ּ�</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="divyAddr" name="divyAddr" value="${user.addr }" >
			</div>
		</div>
		
		<div class="form-group">
			<label for="divyRequest" class="col-sm-offset-1 col-sm-3 control-label">���ſ�û����</label>
			<div class="col-sm-4">
				<textarea class="form-control" id="divyRequest" name="divyRequest" ></textarea>
			</div>
		</div>
		
		<div class="form-group">
			<label for="divyDate" class="col-sm-offset-1 col-sm-3 control-label">����������</label>
			<div class="col-sm-4">
				<input type="date" class="form-control" name="divyDate">
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-4  col-sm-4 text-center">
				<button type="button" class="btn btn-primary"  >����</button>
				<a class="btn btn-primary btn" href="#" role="button">���</a>
			</div>
		</div>
	</form>
</div>


</body>
</html>

<script type="text/javascript">
	
	function resetData(){
		document.addPurchase.reset();
	}
	
	stock()
	function stock(){
		for(let i=1; i<=${product.stock}; i++){
	    	document.getElementById('stock').options[i-1] = new Option(i, i);
	    }
	}
</script>