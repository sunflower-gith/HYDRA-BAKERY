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

import edu.poly.assigmentsof3021.domain.Account;
import edu.poly.assigmentsof3021.model.AccountDto;
import edu.poly.assigmentsof3021.service.AccountService;

@Controller
@RequestMapping("aregister")
public class AdminRegisterController {
	@Autowired
	AccountService accountService;
	@GetMapping("add")
	public String add(Model model, AccountDto dto) {
		model.addAttribute("account", new Account());
		dto.setIsEdit(false);
		model.addAttribute("account", dto);
		return "admin/accounts/addOrEdit";
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("account") AccountDto dto,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/accounts/addOrEdit");
		}
		Account entity = new Account();
		BeanUtils.copyProperties(dto, entity);
		accountService.save(entity);
		model.addAttribute("message", "Admin account registration successful!");
		return new ModelAndView("forward:/admin/accounts", model);
	}
}
