package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/editProduct")
public class EditProductController {
	@ModelAttribute
	public void addEditProduct(@RequestParam(name = "editCode", required = false) String editCode,
			@RequestParam(name = "editDescription", required = false) String editDescription,
			@RequestParam(name = "editPrice", required = false) String editPrice, Model model) {
		if (editCode == null) {
			return;
		}
		String code = editCode;
		String descriptionString = editDescription;
		double price = Double.parseDouble(editPrice);
		Product product = new Product(code, descriptionString, price);
		model.addAttribute("editProduct", product);
	}

	@GetMapping("/edit")
	public String editProductForm(Model model) {
		return "editProduct";
	}

	@PostMapping("/editProductPost")
	public String editProduct(Product product) {
		String path = "src/main/resources/static/ProductTemplate.txt";
		ProductIO.update(product, path);
		return "redirect:/showProduct";
	}

	@PostMapping("/returnProductPost")
	public String returnToProductForm() {
		return "redirect:/showProduct";
	}
}
