package org.quantum.managerapp.controller;

import org.quantum.managerapp.dto.CreateProductDto;
import org.quantum.managerapp.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/catalogue/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductService productService;

    @GetMapping("list")
    public String getProducts(Model model) {
	model.addAttribute("products", productService.findAll());
	return "catalogue/products/list";
    }

    @GetMapping("create")
    public String getCreateProductPage() {
	return "catalogue/products/create";
    }

    @PostMapping("create")
    public String createProduct(CreateProductDto productDto) {
	productService.create(productDto.title(), productDto.details());
	return "redirect:/catalogue/products/list";
    }
}
