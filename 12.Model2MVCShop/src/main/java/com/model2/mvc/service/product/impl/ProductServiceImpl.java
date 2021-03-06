package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.ProductImg;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.productImg.ProductImgDao;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDao;
	
	@Autowired
	@Qualifier("productImgDaoImpl")
	private ProductImgDao productImgDao;
	
	public ProductServiceImpl() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}

	@Override
	public void addProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		productDao.addProdcut(product);
	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		ProductImg pdImg =productImgDao.getProductImg(prodNo);
		Product product = productDao.getProduct(prodNo);
		product.setProductImg(pdImg);
		return product;
	}

	@Override
	public Map<String, Object> getProductList(Map map) throws Exception {
		// TODO Auto-generated method stub
		List<Product> list = productDao.getProductList(map);
		int totalCount = productDao.getTotalCount(map);
		
		map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", totalCount);
		
		return map;
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		productDao.updateProduct(product);
	}
	
	@Override
	public void updateStatus(Product product) throws Exception {
		// TODO Auto-generated method stub
		productDao.updateStatus(product);
	}

}
