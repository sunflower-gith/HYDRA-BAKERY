package edu.poly.assigmentsof3021.controller.site;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.poly.assigmentsof3021.domain.CartItem;
import edu.poly.assigmentsof3021.domain.Product;
import edu.poly.assigmentsof3021.service.CartItemService;
import edu.poly.assigmentsof3021.service.ProductService;
import edu.poly.assigmentsof3021.service.StorageService;

@Controller
@RequestMapping("site/carts")
public class CartItemController {
	@Autowired
	ProductService productService;
	
	@Autowired
	CartItemService cartItemService;
	 
	@Autowired
	StorageService storageService;
	
	@RequestMapping("")
	public String list(Model model) {
		Collection<CartItem> cartItems = cartItemService.getCartItems();
		model.addAttribute("cartItems",cartItems);
		model.addAttribute("total",cartItemService.getAmount());
		model.addAttribute("NoOfItems",cartItemService.getCount());
		return "site/products/cart";
	}
	
	@GetMapping("add/{productId}")
	public String add(@PathVariable("productId") Long productId) {
		Optional<Product> product = productService.findById(productId);
		if(product!=null) {
			CartItem item = new CartItem();
			item.setProductId(product.get().getProductId());
			item.setName(product.get().getName());
			item.setUnitPrice(product.get().getUnitPrice());
			item.setQuantity(1);
			item.setImage(product.get().getImage());
			cartItemService.add(item);
		}
		return "redirect:/site/carts";
	}
	
	@GetMapping("remove/{productId}")
	public String remove(@PathVariable("productId") Long productId) {
		cartItemService.remove(productId);
		return "redirect:/site/carts";
	}
	@PostMapping("update")
	public String update(@RequestParam Long productId,@RequestParam int quantity) {
		cartItemService.update(productId, quantity);
		return "redirect:/site/carts";
	}
	@GetMapping("clear")
	public String clear() {
		cartItemService.clear();
		return "redirect:/site/carts";
	}
	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serverFile(@PathVariable("filename") String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attchment; filename =\"" + file.getFilename() + "\"")
				.body(file);
	}
}
