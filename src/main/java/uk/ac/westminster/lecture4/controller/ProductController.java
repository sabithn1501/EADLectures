package uk.ac.westminster.lecture4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.westminster.lecture4.dto.Product;
import uk.ac.westminster.lecture4.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        System.out.println(product.getClass().getSimpleName());
        return productService.addProduct(product);
    }

    @GetMapping("/search")
    public List<Product> getProductsByName(
            @RequestParam String name) {
        return productService.findProductsByName(name);

    }

    // ProductController.java
    @GetMapping("/summaries")
    public List<String> getAllProductSummaries() {
//        productService.testSummary();
        productService.testDiscount();
        return productService.getAllSummaries();
    }
}
