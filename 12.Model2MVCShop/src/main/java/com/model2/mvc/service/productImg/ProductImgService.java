package com.model2.mvc.service.productImg;

import com.model2.mvc.service.domain.ProductImg;

public interface ProductImgService {
	public void addProductImg(ProductImg productImg) throws Exception;
	public ProductImg getProductImg(int prodNo) throws Exception;
	public void updateProductImg(ProductImg productImg) throws Exception;
}
