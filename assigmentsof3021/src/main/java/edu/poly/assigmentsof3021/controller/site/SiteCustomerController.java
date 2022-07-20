package edu.poly.assigmentsof3021.controller.site;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.assigmentsof3021.domain.Customer;
import edu.poly.assigmentsof3021.model.CustomerDto;
import edu.poly.assigmentsof3021.service.CustomerService;

@Controller
@RequestMapping("site/update")
public class SiteCustomerController {
	@Autowired
	CustomerService customerService;
	
	@RequestMapping("update/{customerId}")
	public ModelAndView update(ModelMap model, @PathVariable("customerId") Long customerId) {
		Optional<Customer> optional = customerService.findById(customerId);
		CustomerDto dto = new CustomerDto();
		if(optional.isPresent()) {
			Customer entity = optional.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);
			model.addAttribute("customer",dto);
//			model.addAttribute("message","Update customer information successfully!");
			return new ModelAndView("site/accounts/profile",model);
		}else {
			model.addAttribute("message","Customer information update failed!");
		}
		return new ModelAndView("site/accounts/profile",model);
	}
	@PostMapping("saveOrUpdate/{customerId}")
	public ModelAndView saveOrUpdate(ModelMap model,@PathVariable("customerId") Long customerId,
			@Validated @ModelAttribute("customer") CustomerDto dto,BindingResult result) {
		Optional<Customer> optional = customerService.findById(customerId);
		if(result.hasErrors()) {
			return new ModelAndView("site/accounts/profile");
		}
		if(optional.isPresent()) {
			Customer entity = optional.get();
			BeanUtils.copyProperties(dto, entity);
			customerService.save(entity);
			model.addAttribute("message","Update customer information successfully!");
			
			return new ModelAndView("site/accounts/profile",model);
		}
		return new ModelAndView("forward:/site/products",model);
		
	}
}
