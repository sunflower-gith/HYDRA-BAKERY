package edu.poly.assigmentsof3021.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import edu.poly.assigmentsof3021.domain.CartItem;
import edu.poly.assigmentsof3021.service.CartItemService;
@Service
@SessionScope
public class CartItemServiceImpl implements CartItemService{
private Map<Long, CartItem> map = new HashMap<Long, CartItem>();
	
	@Override
	public void add(CartItem item) {
		CartItem existedItem = map.get(item.getProductId());
		if(existedItem!=null) {
			existedItem.setQuantity(item.getQuantity()+existedItem.getQuantity());
		}else {
			map.put(item.getProductId(), item);
		}	
	}
	
	@Override
	public void remove(Long productId) {
		map.remove(productId);
	}
	
	@Override
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	@Override
	public void clear() {
		map.clear();
	}
	
	@Override
	public void update(Long productId, int quantity) {
		CartItem item = map.get(productId);
		item.setQuantity(quantity);
		if(item.getQuantity()<=0) {
			map.remove(productId);
		}
	}
	@Override
	public double getAmount() {
		return map.values().stream().mapToDouble(item->item.getQuantity()*item.getUnitPrice()).sum();
	}

	@Override
	public int getCount() {
		if(map.isEmpty()) {
			return 0;
		}
		return map.values().size();
	}
}
