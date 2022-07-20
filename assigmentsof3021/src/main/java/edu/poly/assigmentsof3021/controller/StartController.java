package edu.poly.assigmentsof3021.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hydrabakery")
public class StartController {
	@RequestMapping("")
	public String index() {
		return "index";
	}
}
