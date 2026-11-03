package uk.ac.westminster.lecture4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.westminster.lecture4.dto.Product;
import uk.ac.westminster.lecture4.exception.ProductNotFoundException;
import uk.ac.westminster.lecture4.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    //orElse(null) - Misleading response orElse(null)
    //this results in a 200 OK response with no response body.

    //orElseThrow() - Wrong error meaning
    //supplied exception throws NoSuchElementException when the product is absent.
    // If it is not handled, Spring typically returns 500 Internal Server Error.
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
//                .orElse(null);
                .orElseThrow();
    }

    /*
    Notice that the return type has changed from Optional<Product> to Product.
    The service now has a clear contract:
        - If the product exists, return it.
        - If the product doesn't exist, throw ProductNotFoundException.

        http Response
        When successful, Spring normally returns the product as JSON with 200 OK.
        But how do we explicitly control the status and body?
     */
    @GetMapping("/exception/{id}")
    public Product getProductByUniqueId(@PathVariable Long id) {
        return productService.getProductByUniqueId(id);
    }

    @GetMapping("/response_entity/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        try {
            Product product = productService.getProductByUniqueId(id);
            return ResponseEntity
                    .status(HttpStatus.OK) // 200
                    .body(product);
        } catch (ProductNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND) // 404
                    .body(e.getMessage());
        }
    }



    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product) {
        System.out.println(product.getClass().getSimpleName());
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteProductById(
            @PathVariable Long id) {
        return productService.deleteProduct(id);
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
//        productService.testDiscount();
        return productService.getAllSummaries();
    }
}
