package com.model2.mvc.service.cart;

import java.util.List;

import com.model2.mvc.service.domain.Cart;

public interface CartService {
	public void addCart(Cart cart) throws Exception;
	public List<Cart> getCart(String userId) throws Exception;
	public void updateCart(Cart cart) throws Exception;
}
