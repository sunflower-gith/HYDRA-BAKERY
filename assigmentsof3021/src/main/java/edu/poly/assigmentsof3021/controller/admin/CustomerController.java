package edu.poly.assigmentsof3021.controller.admin;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.assigmentsof3021.domain.Customer;
import edu.poly.assigmentsof3021.service.CustomerService;

@Controller
@RequestMapping("admin/customers")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@RequestMapping("")
	public String list(ModelMap model, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 6);
		Page<Customer> list = customerService.findAll(pageable);
		model.addAttribute("customers", list);
		return "admin/customers/search";
	}

	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 6);
		Page<Customer> list = null;
		if (StringUtils.hasText(name)) {
			list = customerService.findByNameContaining(name, pageable);
		} else {
			list = customerService.findAll(pageable);
		}
		model.addAttribute("customers", list);
		return "admin/customers/search";
	}

	@GetMapping("delete/{customerId}")
	public ModelAndView delete(ModelMap model, @PathVariable("customerId") Long customerId) {
		customerService.deleteById(customerId);
		model.addAttribute("message", "Customer has been deleted!");
		return new ModelAndView("forward:/admin/customers/search", model);
	}
}
