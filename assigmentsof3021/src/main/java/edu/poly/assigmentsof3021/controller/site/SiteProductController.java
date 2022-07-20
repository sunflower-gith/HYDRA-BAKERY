package edu.poly.assigmentsof3021.controller.site;

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

import edu.poly.assigmentsof3021.domain.Product;
import edu.poly.assigmentsof3021.model.CategoryDto;
import edu.poly.assigmentsof3021.model.ProductDto;
import edu.poly.assigmentsof3021.service.CategoryService;
import edu.poly.assigmentsof3021.service.ProductService;
import edu.poly.assigmentsof3021.service.StorageService;

@Controller
@RequestMapping("site/products")
public class SiteProductController {
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

	@RequestMapping("edit/{productId}")
	public ModelAndView edit(ModelMap model, @PathVariable("productId") Long productId) {
		Optional<Product> opt = productService.findById(productId);
		ProductDto dto = new ProductDto();
		if (opt.isPresent()) {
			Product entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setCategoryId(entity.getCategory().getCategoryId());
			dto.setIsEdit(true);
			model.addAttribute("product", dto);
			return new ModelAndView("site/products/addOrEdit", model);
		}
		model.addAttribute("message", "Product does not exist!");
		return new ModelAndView("forward:/site/products", model);
	}

	@RequestMapping("")
	public String list(ModelMap model, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 6);
		Page<Product> list = productService.findAll(pageable);
		model.addAttribute("products", list);
		return "site/products/search";
	}

	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serverFile(@PathVariable("filename") String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attchment; filename =\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 6);
		Page<Product> list = null;
		if (StringUtils.hasText(name)) {
			list = productService.findByNameContaining(name, pageable);
		} else {
			list = productService.findAll(pageable);
		}
		model.addAttribute("products", list);
		return "site/products/search";
	}
}
