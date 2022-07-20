package edu.poly.assigmentsof3021.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.assigmentsof3021.domain.Customer;
import edu.poly.assigmentsof3021.model.CustomerDto;
import edu.poly.assigmentsof3021.service.CustomerService;

@Controller
@RequestMapping("sregister")
public class SiteRegisterController {
	@Autowired
	CustomerService customerService;

	@GetMapping("add")
	public String add(Model model, CustomerDto dto) {
		model.addAttribute("account", new Customer());
		dto.setIsEdit(false);
		model.addAttribute("account", dto);
		return "site/accounts/addOrEdit";
	}

	@RequestMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("account") Customer dto,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("site/accounts/addOrEdit");
		}
		Customer entity = new Customer();
		BeanUtils.copyProperties(dto, entity);
		customerService.save(entity);
		model.addAttribute("message", "Customer account registration successful!");
		return new ModelAndView("forward:/site/products", model);
	}
}
