package edu.poly.assigmentsof3021.controller.site;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.assigmentsof3021.domain.Customer;
import edu.poly.assigmentsof3021.domain.Order;
import edu.poly.assigmentsof3021.domain.OrderDetail;
import edu.poly.assigmentsof3021.domain.Product;
import edu.poly.assigmentsof3021.model.OrderDetailDto;
import edu.poly.assigmentsof3021.model.OrderDto;
import edu.poly.assigmentsof3021.service.CartItemService;
import edu.poly.assigmentsof3021.service.CustomerService;
import edu.poly.assigmentsof3021.service.OrderDetailService;
import edu.poly.assigmentsof3021.service.OrderService;
import edu.poly.assigmentsof3021.service.ProductService;
import edu.poly.assigmentsof3021.service.SessionService;
import edu.poly.assigmentsof3021.service.StorageService;

@Controller
@RequestMapping("site/orders")
public class SiteOrderController {
	@Autowired
	OrderService orderService;
	@Autowired
	CustomerService customerService;
	@Autowired
	ProductService productService;
	@Autowired
	CartItemService cartItemService;
	@Autowired
	OrderDetailService orderDetailService;
	@Autowired
	StorageService storageService;
	@Autowired
	SessionService sessionService;

	@RequestMapping("listOrders/{customerId}")
	public String list(ModelMap model, @PathVariable("customerId") Long customerId, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 7);
		Page<Order> list = orderService.findByCustomerId(customerId, pageable);
		model.addAttribute("orders", list);
		return "site/orders/list";
	}
	@RequestMapping("buyNow/{customerId}/{productId}")
	public String addOrder(Model model, @PathVariable("customerId") Long customerId, @PathVariable("productId") Long productId) {
		Optional< Customer> optional = customerService.findById(customerId);
		OrderDto dto = new OrderDto();
		Order order = new Order();
		Customer customer = optional.get();
		customer.setCustomerId(optional.get().getCustomerId());
		order.setCustomer(customer);
		order.setAmount((double )1);
		order.setOrderDate(new Date());
		order.setStatus((short)0);
		Set<OrderDetail> set = new HashSet<OrderDetail>();
			OrderDetail orderDetail = new OrderDetail();
			OrderDetailDto dto2 = new OrderDetailDto();
			Optional< Product> optional2 = productService.findById(productId);
			Product product = optional2.get();
			product.setProductId(optional2.get().getProductId());
			orderDetail.setProduct(product);
			orderDetail.setQuantity((int)1);
			orderDetail.setUnitPrice(optional2.get().getUnitPrice());
			set.add(orderDetail);
			order.setOrderDetails(set);
			orderService.save(order);
			sessionService.setLong("orderId", order.getOrderId());
			model.addAttribute("message", "Order Success !");
		return "forward:/site/products";
	}
	@GetMapping("addOrder/{customerId}")
	public String addOrder(Model model,@PathVariable("customerId") Long customerId) {
		Optional< Customer> optional = customerService.findById(customerId);
		OrderDto dto = new OrderDto();
		Order order = new Order();
		Customer customer = optional.get();
		customer.setCustomerId(optional.get().getCustomerId());
		order.setCustomer(customer);
		order.setAmount(cartItemService.getAmount());
		order.setOrderDate(new Date());
		order.setStatus((short)0);
		Set<OrderDetail> set = new HashSet<OrderDetail>();
		cartItemService.getCartItems().forEach(item->{
			OrderDetail orderDetail = new OrderDetail();
			Product product = new Product();
			product.setProductId(item.getProductId());
			orderDetail.setProduct(product);
			orderDetail.setQuantity(item.getQuantity());
			orderDetail.setUnitPrice(item.getUnitPrice());
			set.add(orderDetail);
		});
		order.setOrderDetails(set);
		orderService.save(order);
		sessionService.setLong("orderId", order.getOrderId());
		model.addAttribute("order", dto);
		sessionService.setDouble("total", cartItemService.getAmount());
		model.addAttribute("message", "Order Success !");
		return "forward:/site/products";
	}
	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serverFile(@PathVariable("filename") String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attchment; filename =\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	@GetMapping("view/{orderId}")
	public ModelAndView view(ModelMap model, @PathVariable("orderId") Long orderId,
			@RequestParam("p") Optional<Integer> p) throws IOException {
		Pageable pageable = PageRequest.of(p.orElse(0), 4);
		Page<OrderDetail> list = orderDetailService.findByOrderId(orderId, pageable);
		model.addAttribute("orderdetails", list);
		return new ModelAndView("site/orders/view", model);
	}
	@GetMapping("delete/{orderId}")
	public ModelAndView delete(ModelMap model, @PathVariable("orderId") Long orderId) throws IOException {
		orderService.deleteById(orderId);
		model.addAttribute("message", "Order has been cancelled!");
		return new ModelAndView("forward:/site/products", model);
	}
}
