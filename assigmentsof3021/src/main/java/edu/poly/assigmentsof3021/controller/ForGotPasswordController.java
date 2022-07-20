package edu.poly.assigmentsof3021.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.assigmentsof3021.domain.Customer;
import edu.poly.assigmentsof3021.service.CustomerService;
import edu.poly.assigmentsof3021.service.MailerService;

@Controller
@RequestMapping("sforgot")
public class ForGotPasswordController {
	@Autowired
	MailerService mailer;
	@Autowired
	CustomerService customerService;
	
	@ResponseBody
	@RequestMapping("find")
	public ModelAndView demo(ModelMap model, @RequestParam(name = "email", required = false) String email){
//		try {
//			mailer.send("jianyang10252001@gmail.com", "Subject", "Body");
//			return "OK";
//		} catch (MessagingException e) {
//			return e.getMessage();
//		}
		Optional<Customer> optional = customerService.findByEmail(email);
		mailer.queue(optional.get().getEmail(), "PASSWORD RETRIEVAL","Your password is " + optional.get().getPassword());
		model.addAttribute("message", "Password has been sent to your email!");
		return new ModelAndView("site/accounts/forgotPassword", model);
	}
	@RequestMapping("")
		public String sforgot(){
			return "site/accounts/forgotPassword";
		}
} 
