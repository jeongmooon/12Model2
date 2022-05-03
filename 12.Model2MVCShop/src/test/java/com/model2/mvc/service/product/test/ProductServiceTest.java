package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml"})
public class ProductServiceTest {
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	//@Test
	public void testAddProduct() throws Exception{
		Product prod = new Product();
		prod.setProdName("TEst3Æ®ÁßÀÌ¼À3");
		prod.setProdContent("½Î´Ù½Î¾ó¸¥»ç¼À");
		prod.setPrice(500000);
		prod.setProdCategory("¼Ò¸ðÇ°");
		prod.setStatus(0);
		prod.setStatus(0);
		
		User user = new User();
		user.setAddr("¹Ú´Þ1µ¿");
		user.setUserId("user11");
		prod.setUser(user);
		
		productService.addProduct(prod);
		prod=productService.getProduct(10063);
		
		System.out.println(prod);
	}
	
	//@Test
	public void testGetProduct() throws Exception{
		Product prod=productService.getProduct(10041);
		System.out.println(prod);
	}
	
	//@Test
	public void testUpdateProduct() throws Exception{
		Product prod = new Product();
		prod.setProdNo(10041);
		prod.setProdName("Àüº¹Á×ÆÈ¾Æ¿ä");
		prod.setProdContent("¾ó¸¥»ç¼À");
		prod.setPrice(30000);
		prod.setStatus(0);
		prod.setStatus(0);
		
		productService.updateProduct(prod);
		
		prod = productService.getProduct(10041);
	}
	
	//@Test
	public void testGetProductList() throws Exception{
		Search search = new Search();
		search.setCurrentPage(1);
		search.setSearchValue("0");
		//search.setSearchCondition("0");
		search.setPageSize(3);
		
		//Map<String,Object> map = productService.getProductList(search);
		
		//List<Object> list = (List)map.get("list");
		
//		for (Object object : list) {
//			System.out.println("====================");
//			System.out.println(object);
//		}
		
		//Integer totalCount = (Integer)map.get("totalCount");
	 	//System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
		
	}
	
}
