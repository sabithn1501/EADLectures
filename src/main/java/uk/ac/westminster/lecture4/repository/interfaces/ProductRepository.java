package uk.ac.westminster.lecture4.repository.interfaces;

import uk.ac.westminster.lecture4.dto.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAllProducts();
    Optional<Product> findProductById(Long id);
    Product saveProduct(Product product);
    boolean deleteProduct(Long id);
    List<Product> findByName(String name);
    List<String> getAllSummaries();
}
