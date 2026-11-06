package uk.ac.westminster.lecture4.repository;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import uk.ac.westminster.lecture4.dto.DigitalProduct;
import uk.ac.westminster.lecture4.dto.PhysicalProduct;
import uk.ac.westminster.lecture4.dto.Product;
import uk.ac.westminster.lecture4.repository.interfaces.ProductRepository;
import uk.ac.westminster.lecture4.service.interfaces.Discountable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class InMemoryProductRepository implements ProductRepository {
    private final List<Product> products
            = new ArrayList<>();
    private Long nextId = 1L;

    public InMemoryProductRepository() {
        // Note: no new Product(...) anywhere — it is abstract now.
        products.add(new DigitalProduct(
                nextId++, "Clean Code (E-book)", 29.99,
                "/downloads/clean-code.pdf", 4.2));
        products.add(new PhysicalProduct(
                nextId++, "27-inch Monitor", 249.99,
                5.4, "61cm x 41cm x 18cm"));

//        products.add(new Product(nextId++, "book", 100.00));
    }

    @Override
    public List<Product> findAllProducts() {
        return products;
    }

    @Override
    public Optional<Product> findProductById(Long id) {
                return products.stream()
                    .filter(p -> p.getId().equals(id))
                    .findFirst();
    }

    @Override
    public Product saveProduct(Product product) {
        if (product.getId() == null)
            product.setId(nextId++);
        products.add(product);
        return product;
    }

    @Override
    public boolean deleteProduct(Long id) {
        boolean flag = products.removeIf(product -> product.getId().equals(id));
        return flag;
    }

    @Override
    public List<Product> findByName(String name) {
        return products.stream()
                .filter(product -> product.getName().toLowerCase()
                        .contains(name.toLowerCase()))
                .toList();
    }

    @Override
    public List<String> getAllSummaries() {
        List<String> summaries = new ArrayList<>();
        for (Product p : products) {
            summaries.add(p.getSummary());
            if (p instanceof Discountable d) {
                d.applyDiscount(0.10);
                System.out.println("Digital prouduct: "+d.getDiscountedPrice());
            }
        }
        return summaries;
    }
}
