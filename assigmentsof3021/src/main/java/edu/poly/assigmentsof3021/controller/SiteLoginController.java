package edu.poly.assigmentsof3021.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.assigmentsof3021.domain.Customer;
import edu.poly.assigmentsof3021.model.SiteLoginDto;
import edu.poly.assigmentsof3021.service.CustomerService;
import edu.poly.assigmentsof3021.service.SessionService;

@Controller
public class SiteLoginController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private HttpSession session;
	@Autowired 
	private SessionService sessionService;
	
	
	@GetMapping("slogin")
	public String login(ModelMap model) {
		model.addAttribute("account", new SiteLoginDto());
		return "/site/accounts/login";
	}
	
	@PostMapping("slogin")
	public ModelAndView login(ModelMap model, @Valid @ModelAttribute("account") SiteLoginDto dto, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("err");
			return new ModelAndView("/site/accounts/login", model);
		}
		Customer account = customerService.login(dto.getEmail(), dto.getPassword());
		if(account == null) {
			model.addAttribute("message", "Wrong email or password!");
			return new ModelAndView("/site/accounts/login", model);
		}
		sessionService.setAttribute("customerId", account.getCustomerId());
		Object ruri = session.getAttribute("redirect-uri");
		if(ruri != null) {
			session.removeAttribute("redirect-uri");
			return new ModelAndView("redirect:" + ruri,model);
		}
		return new ModelAndView("redirect:/site/products", model);
	}
}
