package com.model2.mvc.service.cart.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.service.cart.CartService;
import com.model2.mvc.service.domain.Cart;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml"})
public class CartServiceTest {
	@Autowired
	@Qualifier("cartServiceImpl")
	private CartService cartService;
	
	//@Test
	public void testAddCart() throws Exception {
		Cart cart = new Cart();
		cart.setUserId("user11");
		cart.setProdNo(10062);
		cart.setStatus(0);
		
		cartService.addCart(cart);		
	}
	
	//@Test
	public void testUpdateCart() throws Exception {
		Cart cart = new Cart();
		cart.setUserId("user11");
		cart.setProdNo(10060);
		cart.setStatus(1);
		
		cartService.updateCart(cart);
	}	

	//@Test
	public void testGetCart() throws Exception{
		List<Cart> list = cartService.getCart("user11");
		
		for (Cart cart : list) {
			System.out.println("==============");
			System.out.println(cart);
		}
	}
	
}
