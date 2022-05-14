package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/addProduct")
public class AddProductController {
	@GetMapping("/add")
	public String showAddProdutForm() {
		return "addProduct";
	}

	@PostMapping("/product")
	public String sendToProductTemplate(Product product) {
		String path = "src/main/resources/static/ProductTemplate.txt";
		ProductIO.insert(product, path);
		return "redirect:/showProduct";
	}

	@PostMapping("/return")
	public String returnToProductForm() {
		return "redirect:/showProduct";
	}
}
