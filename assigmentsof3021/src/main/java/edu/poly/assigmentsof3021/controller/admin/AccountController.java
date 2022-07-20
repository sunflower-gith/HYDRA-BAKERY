package edu.poly.assigmentsof3021.controller.admin;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.assigmentsof3021.domain.Account;
import edu.poly.assigmentsof3021.model.AccountDto;
import edu.poly.assigmentsof3021.service.AccountService;
import edu.poly.assigmentsof3021.service.SessionService;

@Controller
@RequestMapping("admin/accounts")
public class AccountController {
	@Autowired
	AccountService accountService;
	@Autowired
	SessionService sessionService;

	@PostMapping("saveOrUpdate/{username}")
	public ModelAndView saveOrUpdate(ModelMap model, @PathVariable("username") String username,
			@Validated @ModelAttribute("account") AccountDto dto,BindingResult result) {
		Optional<Account> optional = accountService.findById(username);
		if (result.hasErrors()) {
			return new ModelAndView("admin/accounts/addOrEdit");
		}
		if(optional.isPresent()) {
			Account entity = optional.get();
			BeanUtils.copyProperties(dto, entity);
			accountService.save(entity);
			model.addAttribute("message", "Account has been saved!");
			return new ModelAndView("admin/accounts/changePass",model);
		}
		return new ModelAndView("forward:/admin/accounts", model);
	}
	
	@RequestMapping("")
	public String list(ModelMap model, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 7);
		Page<Account> list = accountService.findAll(pageable);
		model.addAttribute("accounts", list);
		return "admin/accounts/search";
	}
	@RequestMapping("edit/{username}")
	public ModelAndView update(ModelMap model, @PathVariable("username") String username) {
		Optional<Account> optional = accountService.findById(username);
		AccountDto dto = new AccountDto();
		if(optional.isPresent()) {
			Account entity = optional.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);
			model.addAttribute("account",dto);
//			model.addAttribute("message","Change password successfully!");
			return new ModelAndView("admin/accounts/changePass",model);
		}else {
			model.addAttribute("message","Account does not exist!");
		}
		return new ModelAndView("forward:/admin/accounts/",model);
	}
}
