package org.quantum.managerapp.controller;

import org.quantum.managerapp.dto.UpdateProductDto;
import org.quantum.managerapp.model.Product;
import org.quantum.managerapp.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/catalogue/products/{productId:\\d+}")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @ModelAttribute("product")
    public Product product(@PathVariable Long productId) {
	return productService.getProduct(productId);
    }

    @GetMapping
    public String getProduct() {
	return "catalogue/products/product";
    }
    
    @GetMapping("edit")
    public String getEditProductPage() {
	return "catalogue/products/edit";
    }
    
    @PostMapping
    public String updateProduct(@ModelAttribute Product product, UpdateProductDto updateDto) {
	productService.update(product.getId(), updateDto.title(), updateDto.details());
	return "redirect:/catalogue/products/list";
    }
}
