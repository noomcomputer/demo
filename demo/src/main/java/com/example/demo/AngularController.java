package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
class AngularController {
	@RequestMapping(value = "/**/{[path:[^\\.]*}")
	public String redirectUi() {
		System.out.println("It's me MVC!");
		return "forward:index.html";
	}
}