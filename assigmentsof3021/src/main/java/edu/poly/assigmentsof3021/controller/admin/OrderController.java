package edu.poly.assigmentsof3021.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.assigmentsof3021.domain.Order;
import edu.poly.assigmentsof3021.domain.OrderDetail;
import edu.poly.assigmentsof3021.domain.Product;
import edu.poly.assigmentsof3021.model.CustomerDto;
import edu.poly.assigmentsof3021.model.ProductDto;
import edu.poly.assigmentsof3021.service.CustomerService;
import edu.poly.assigmentsof3021.service.OrderDetailService;
import edu.poly.assigmentsof3021.service.OrderService;
import edu.poly.assigmentsof3021.service.ProductService;
import edu.poly.assigmentsof3021.service.StorageService;

@Controller
@RequestMapping("admin/orders")
public class OrderController {
	@Autowired
	CustomerService customerService;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderDetailService orderDetailService;
	@Autowired
	StorageService storageService;
	@Autowired
	ProductService productService;
	
	@ModelAttribute("customers")
	public List<CustomerDto> getCustomerDtos() {
		return customerService.findAll().stream().map(item -> {
			CustomerDto dto = new CustomerDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}
	
	@ModelAttribute("products")
	public List<ProductDto> getProductsDtos() {
		return productService.findAll().stream().map(item -> {
			ProductDto dto = new ProductDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}
	
	@RequestMapping("")
	public String list(ModelMap model, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 7);
		Page<Order> list = orderService.findAll(pageable);
		model.addAttribute("orders", list);
		return "admin/orders/list";
	}
	
	@GetMapping("delete/{orderId}")
	public ModelAndView delete(ModelMap model, @PathVariable("orderId") Long orderId) throws IOException {
		orderService.deleteById(orderId);
		model.addAttribute("message", "Order has been cancelled!");
		return new ModelAndView("forward:/admin/orders", model);
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
		return new ModelAndView("admin/orders/view", model);
	}
}
