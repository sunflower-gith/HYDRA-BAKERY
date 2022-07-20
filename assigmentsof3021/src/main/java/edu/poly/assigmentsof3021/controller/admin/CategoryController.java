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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.assigmentsof3021.domain.Category;
import edu.poly.assigmentsof3021.model.CategoryDto;
import edu.poly.assigmentsof3021.service.CategoryService;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@GetMapping("add")
	public String add(Model model, CategoryDto dto) {
		model.addAttribute("category", new Category());
		dto.setIsEdit(false);
		model.addAttribute("category", dto);
		return "admin/categories/addOrEdit";
	}

	@GetMapping("edit/{categoryId}")
	public ModelAndView edit(ModelMap model, @PathVariable("categoryId") Long categoryId) {
		Optional<Category> opt = categoryService.findById(categoryId);
		CategoryDto dto = new CategoryDto();
		if (opt.isPresent()) {
			Category entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);
			model.addAttribute("category", dto);
			return new ModelAndView("admin/categories/addOrEdit", model);
		}
		model.addAttribute("message", "Cagetory does not exist!");
		return new ModelAndView("forward:/admin/categories", model);
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("category") CategoryDto dto,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/categories/addOrEdit");
		}
		Category entity = new Category();
		BeanUtils.copyProperties(dto, entity);
		categoryService.save(entity);
		model.addAttribute("message", "The category has been saved!");
		return new ModelAndView("forward:/admin/categories", model);
	}

	@RequestMapping("")
	public String list(ModelMap model, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 7);
		Page<Category> list = categoryService.findAll(pageable);
		model.addAttribute("categories", list);
		return "admin/categories/search";
	}

	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 7);
		Page<Category> list = null;
		if (StringUtils.hasText(name)) {
			list = categoryService.findByNameContaining(name, pageable);
		} else {
			list = categoryService.findAll(pageable);
		}
		model.addAttribute("categories", list);
		return "admin/categories/search";
	}

	@GetMapping("delete/{categoryId}")
	public ModelAndView delete(ModelMap model, @PathVariable("categoryId") Long categoryId) {
		categoryService.deleteById(categoryId);
		model.addAttribute("message", "Category has been deleted!");
		return new ModelAndView("forward:/admin/categories/search", model);
	}
}
