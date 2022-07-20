package edu.poly.assigmentsof3021.service;

import java.util.Collection;

import edu.poly.assigmentsof3021.domain.CartItem;

public interface CartItemService {

	void add(CartItem item);

	void remove(Long productId);

	Collection<CartItem> getCartItems();

	void clear();

	void update(Long productId, int quantity);

	double getAmount();

	int getCount();

}
