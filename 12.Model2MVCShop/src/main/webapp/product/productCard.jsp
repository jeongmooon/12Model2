<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<style>
	.productComponet{
		justify-content: center;
	}
    .product {
        display: flex;
        padding: 10px;
        justify-content: center;
    }
    .thumbnail {
        max-width: 200px;
        width: 200px;
        border-radius: 10px;
        background-size: cover;
        background-position: center;        
        
    }
    .flex-grow-1{
    	padding-left:50px;
    }
    .product .price {
        font-size: 16px;
        font-weight: 600;
    }
    .product .title {
        font-size: 17px;
        font-weight: 600;
    }
    .product .date {
        color: grey;
        font-size: 13px;
    }
    span{
            color:white;
    }
</style>
<script>
	$(function(){
		
		$( "#send:contains('����ϱ�')" ).on("click" , function() {
			const prodNo = $(this).data("value");
			self.location = "/updateTranCode?prodNo="+prodNo+"&tranCode=2&menu=${param.menu}&con=''";
		});
		
		if('${user.role}' !== 'admin'){
			$(".title").on("click",function (){			
				const prodNo = $(this).data("value");
				self.location = "/product/getProduct?prodNo="+prodNo;
			})
		} else {
			$(".title").on("click",function (){			
				const prodNo = $(this).data("value");
				self.location = "/product/updateProduct?prodNo="+prodNo;
			})
		}		
	})
	
	let page = 2;
	$(window).on("scroll", function() {
		const rPrice = $('.sPriceKeyword').val();
		const hPrice = $('.bPriceKeyword').val();
        const scrollHeight = $(document).height();
        const scrollPosition = $(window).height() + $(window).scrollTop();
        if (scrollHeight <= scrollPosition) {			
            //console.log(${resultPage.endUnitPage})
            if(page <=${resultPage.maxPage}){
            $.getJSON("/product/json/listProduct",
                        {
                            currentPage : page,
                            searchValue : ${search.searchValue},
            				searchKeyword : rPrice+","+hPrice			
                        },
                        (data,status)=>{
                        	if(status ==='success'){
                        		for(let i=0; i<data.list.length; i++){
                        			
                        			let card = $(".productComponet").clone()[0];
                        			//let card = $(".product").clone();
                        			const prod = data.list[i];
                        			$(card).find(".title").attr("data-value",prod.prodNo).text(prod.prodName)
                        			$(card).find(".date").text(prod.regDate)
                        			$(card).find(".price").text(prod.price)
                        			$(card).find(".image").attr("src","../images/uploadFiles/"+prod.fileName);
                        			$(card).find(".float-end").text(prod.stock)
                        			
                        			if(prod.stock===0){
                        				$(card).find(".status").text("������")
                        			} else {
                        				$(card).find(".status").text("�Ǹ���")
                        			}
                        			
                        			$("#test").append(card);
                        			
                        			$( "#send:contains('����ϱ�')" ).on("click" , function() {
                        				const prodNo = $(this).data("value");
                        				self.location = "/updateTranCode?prodNo="+prodNo+"&tranCode=2&menu=${param.menu}&con=''";
                        			});
                        			
                        			if('${user.role}' !== 'admin'){
                        				$(".title").on("click",function (){			
                        					const prodNo = $(this).data("value");
                        					self.location = "/product/getProduct?prodNo="+prodNo;
                        				})
                        			} else {
                        				$(".title").on("click",function (){			
                        					const prodNo = $(this).data("value");
                        					self.location = "/product/updateProduct?prodNo="+prodNo;
                        				})
                        			}
                        		}
                        		
                        	}
                        })
            //$("#test").append('<div id="content">123122332</div>');
            //console.log(page)
            //page++
            }
            //console.log($(".container").clone())
            //$("#test").append($(".container").clone()[0])
            page++
        }        
    });
	
</script>

<div id="test">
	<c:forEach var="product" items="${list}">
		<c:set var="i" value="${ i+1 }" />
    <div class="productComponet">
        <div class="product">
          <div class="thumbnail" style="background-image: url('http://folo.co.kr/img/gm_noimage.png')">
          <img class="image" style="width:200px; height:200px;" src="../images/uploadFiles/"/>
          </div>
          <div class="flex-grow-1 p-4">
			<h5 class="title" style="font-size:larger; text-align:center;" data-value="${product.prodNo}" >${product.prodName}</h5> 
            <p class="date">����� : ${product.createAt}</p>
            <p class="price">���� : ${product.price}</p>
            <p class="float-end">ī�װ� : ${product.prodCategory }</p>
			<h4 class="status" title="stauts" style="text-align:center;">
				<c:if test="${product.status !=0}">
					�Ǹ���
				</c:if>
				<c:if test="${product.status == 0}">
					�Ǹ� ���� ��
				</c:if>
			</h4>
          </div>
        </div>	
    </div>
    </c:forEach>    
</div>
<span id="b">scroll < 500px</span>