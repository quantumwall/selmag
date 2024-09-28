package org.quantum.managerapp.controller;

import org.quantum.managerapp.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
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
}
