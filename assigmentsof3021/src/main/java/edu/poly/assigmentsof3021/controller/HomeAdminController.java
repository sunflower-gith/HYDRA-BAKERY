package edu.poly.assigmentsof3021.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.poly.assigmentsof3021.domain.Category;
import edu.poly.assigmentsof3021.domain.Product;
import edu.poly.assigmentsof3021.model.CategoryDto;
import edu.poly.assigmentsof3021.service.CategoryService;
import edu.poly.assigmentsof3021.service.ProductService;
import edu.poly.assigmentsof3021.service.StorageService;

@Controller
@RequestMapping("ahome")
public class HomeAdminController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	StorageService storageService;

	@ModelAttribute("categories")
	public List<CategoryDto> getCategories() {
		return categoryService.findAll().stream().map(item -> {
			CategoryDto dto = new CategoryDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}

	@RequestMapping("")
	public String list(ModelMap model, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 6);
		Pageable pageable2 = PageRequest.of(p.orElse(0), 10);
		Page<Product> list = productService.findAll(pageable);
		Page<Category> list2 = categoryService.findAll(pageable2);
		model.addAttribute("products", list);
		model.addAttribute("categories", list2);
		return "admin/accounts/home";
	}

	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serverFile(@PathVariable("filename") String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attchment; filename =\"" + file.getFilename() + "\"")
				.body(file);
	}
	@RequestMapping("search/{categoryId}")
	public String search(ModelMap model, @PathVariable("categoryId") Long categoryId,
			@RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 6);
		Pageable pageable2;
		Page<Product> list = null;
		Page<Category> list2 = null;
				if(categoryId != null) {
					pageable2 = PageRequest.of(p.orElse(0), 1);
					list =productService.findByCategoryId(categoryId, pageable);
					list2 = categoryService.findAllById(categoryId, pageable);
				} else {
					list = productService.findAll(pageable);
					pageable2 = PageRequest.of(p.orElse(0), 7);
					list2 = categoryService.findAll(pageable);
				}
		model.addAttribute("products", list);
		model.addAttribute("categories", list2);
		return "admin/accounts/top";
	}
}
