package com.model2.mvc.service.productImg.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.service.domain.ProductImg;
import com.model2.mvc.service.productImg.ProductImgDao;
import com.model2.mvc.service.productImg.ProductImgService;

@Service("productImgServiceImpl")
public class ProductImgServiceImpl implements ProductImgService {
	
	@Autowired
	@Qualifier("productImgDaoImpl")
	private ProductImgDao productImgDao;
	
	public ProductImgServiceImpl() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}

	@Override
	public void addProductImg(ProductImg productImg) throws Exception {
		// TODO Auto-generated method stub
		productImgDao.addProductImg(productImg);
	}

	@Override
	public ProductImg getProductImg(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return productImgDao.getProductImg(prodNo);
	}

	@Override
	public void updateProductImg(ProductImg productImg) throws Exception {
		// TODO Auto-generated method stub
		productImgDao.updateProductImg(productImg);
	}


}
