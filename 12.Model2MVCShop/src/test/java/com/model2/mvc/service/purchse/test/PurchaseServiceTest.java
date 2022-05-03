package com.model2.mvc.service.purchse.test;

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
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml"})
public class PurchaseServiceTest {
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	//@Test
	public void addPurchaseTest() throws Exception {
		Purchase pur = new Purchase();
		Product pro = new Product();
		User user = new User();
		pro.setProdNo(10060);
		pro.setStatus(1);
		
		user.setUserId("user22");
		
		pur.setProduct(pro);
		pur.setBuyer(user);
		
		pur.setStatus(0);
		
		purchaseService.addPurchase(pur);
	}
	
	//@Test
	public void getPurchase() throws Exception{
		Purchase pruchase = purchaseService.getPurcahse(10061);
		
		System.out.println(pruchase);
	}
	
	@Test
	public void getPurchaseList() throws Exception{
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		Map<String, Object> map = purchaseService.getPurchaseList(search, "user22");
		List list = (List)map.get("list");
		
		for (Object object : list) {
			System.out.println(list);
		}
	}
}
