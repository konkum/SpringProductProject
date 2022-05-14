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
@RequestMapping("/deleteProduct")
public class DeleteProductController {
	@ModelAttribute
	public void addEditProduct(@RequestParam(name = "deleteCode", required = false) String deleteCode,
			@RequestParam(name = "deleteDescription", required = false) String deleteDescription,
			@RequestParam(name = "deletePrice", required = false) String deletePrice, Model model) {
		if (deleteCode == null) {
			return;
		}
		String code = deleteCode;
		String descriptionString = deleteDescription;
		double price = Double.parseDouble(deletePrice);
		Product product = new Product(code, descriptionString, price);
		model.addAttribute("deleteProduct", product);
	}

	@GetMapping("/delete")
	public String deleteProductForm(Model model) {
		return "deleteProduct";
	}

	@PostMapping("/deleteThisProduct")
	public String deleteProductPost(Product product) {
		String path = "src/main/resources/static/ProductTemplate.txt";
		ProductIO.delete(product, path);
		return "redirect:/showProduct";
	}

	@PostMapping("/returnFromDeleteForm")
	public String returnToProductForm() {
		return "redirect:/showProduct";
	}
}
