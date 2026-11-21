package uk.ac.westminster.lecture4.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import uk.ac.westminster.lecture4.dto.DigitalProduct;
import uk.ac.westminster.lecture4.dto.PhysicalProduct;
import uk.ac.westminster.lecture4.dto.Product;
import uk.ac.westminster.lecture4.repository.interfaces.ProductRepository;

import java.util.*;

//@Primary
@Repository
@Primary
public class DatabaseProductRepository implements ProductRepository {

    // Keyed by id, so findById and delete are one line each
// instead of a loop.
    private final Map<Long, Product> store = new HashMap<>();

    private Long nextId = 1L;
    // The seed data lives here now, not in the service.
// Passing null as the id lets save() assign it.
    public DatabaseProductRepository() {
        System.out.println("invoked DatabaseProductRepository...");
        saveProduct(new DigitalProduct(null, "E-book", 9.99, "/dl/1", 4.2));
        saveProduct(new PhysicalProduct(null, "Monitor", 249.99, 5.4, "60x40"));
    }

    @Override
    public List<Product> findAllProducts() {

        // A copy, so callers cannot modify our storage by accident.
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        // ofNullable turns "maybe null" into "definitely an Optional".
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Product saveProduct(Product product) {
        // Assign an id ONLY if there is not one already:
        // no id means insert, an existing id means update.
        if (product.getId() == null) {
            product.setId(nextId++);
        }
        // Assign first, then store — otherwise the key is null.
        store.put(product.getId(), product);
        return product;
    }

    @Override
    public boolean deleteProduct(Long id) {
        boolean flag = store.containsKey(id);
        store.remove(id);
        return flag;
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
