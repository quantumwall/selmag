package org.quantum.managerapp.controller;

import java.util.Locale;

import org.quantum.managerapp.dto.UpdateProductDto;
import org.quantum.managerapp.exception.ProductNotFoundException;
import org.quantum.managerapp.model.Product;
import org.quantum.managerapp.service.ProductService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/catalogue/products/{productId:\\d+}")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final MessageSource messageSource;

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

    @PostMapping("update")
    public String updateProduct(@ModelAttribute Product product, UpdateProductDto updateDto) {
	productService.update(product.getId(), updateDto.title(), updateDto.details());
	return "redirect:/catalogue/products/list";
    }

    @PostMapping("delete")
    public String deleteProduct(@ModelAttribute Product product) {
	productService.delete(product.getId());
	return "redirect:/catalogue/products/list";
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public String handleProductNotFoundException(ProductNotFoundException exception,
						 Model model,
						 HttpServletResponse response,
						 Locale locale) {
	model.addAttribute("error", messageSource.getMessage(exception.getMessage(), new Object[0],
							     exception.getMessage(), locale));
	response.setStatus(HttpStatus.NOT_FOUND.value());
	return "errors/404";
    }
}
