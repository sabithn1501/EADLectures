package uk.ac.westminster.lecture4.repository;

import org.springframework.stereotype.Repository;
import uk.ac.westminster.lecture4.dto.Product;
import uk.ac.westminster.lecture4.repository.interfaces.ProductRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class DatabaseProductRepository implements ProductRepository {
    @Override
    public List<Product> findAllProducts() {
        return null;
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return Optional.empty();
    }

    @Override
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long id) {
        return false;
    }

    @Override
    public List<Product> findByName(String name) {
        return null;
    }

    @Override
    public List<String> getAllSummaries() {
        return null;
    }
}
