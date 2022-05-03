package com.model2.mvc.service.productImg.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.service.domain.ProductImg;
import com.model2.mvc.service.productImg.ProductImgDao;

@Repository("productImgDaoImpl")
public class ProductImgDaoImpl implements ProductImgDao{
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public ProductImgDaoImpl() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());		
	}

	@Override
	public void addProductImg(ProductImg productImg) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("ProductImgMapper.addProductImg",productImg);
	}

	@Override
	public ProductImg getProductImg(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("ProductImgMapper.getProductImg",prodNo);
	}

	@Override
	public void updateProductImg(ProductImg productImg) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("ProductImgMapper.updateBoardImg",productImg);
	}

}
