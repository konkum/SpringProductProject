package com.example.demo;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/showProduct")
public class ProductController {
	@ModelAttribute
	public void addProductToModel(Model model) {
		String path = "src/main/resources/static/ProductTemplate.txt";
		ArrayList<Product> products = ProductIO.getProducts(path);
		for (int i = 0; i < products.size(); i++) {
			System.out.print(products.get(i).getCode());
		}
		model.addAttribute("products", products);
	}

	@GetMapping
	public String showProductForm(Model model) {
		return "showProduct";
	}

	@PostMapping
	public String sendToAddProduct(Product product) {
		return "redirect:/addProduct/add";
	}
}
