package com.model2.mvc.service.domain;

import java.sql.Date;

public class ProductImg {
	private int pdImgNo;
	private int prodNo;
	private Date createAt;
	private Date updateAt;
	private String imgURL;
	public int getPdImgNo() {
		return pdImgNo;
	}
	public int getProdNo() {
		return prodNo;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setPdImgNo(int pdImgNo) {
		this.pdImgNo = pdImgNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	
	@Override
	public String toString() {
		return "ProductImg [pdImgNo=" + pdImgNo + ", pordNo=" + prodNo + ", createAt=" + createAt + ", updateAt="
				+ updateAt + ", imgURL=" + imgURL + "]";
	}		
}
