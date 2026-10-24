package uk.ac.westminster.lecture4.service;

import org.springframework.stereotype.Service;
import uk.ac.westminster.lecture4.dto.DigitalProduct;
import uk.ac.westminster.lecture4.dto.PhysicalProduct;
import uk.ac.westminster.lecture4.dto.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();

    //nextId++ — use the current value, then increment.
    //++nextId — increment first, then use the new value.
    private Long nextId = 1L;

    public ProductService() {
        System.out.println("ProductService constructor executed");
        products.add(new DigitalProduct(nextId++, "E-book",
                9.99, "/dl/1", 4.2));

        products.add(new PhysicalProduct(nextId++,
                "Monitor", 249.99, 5.4, "60x40"));

//        products.add(new Product(nextId++, "book", 100.00));
    }

    public List<Product> getProducts() {
        products.get(0);
        System.out.println("getProducts() called");
        return products;
    }

    public Product addProduct(Product product) {
        product.setId(nextId);
        nextId += 1L;
        products.add(product);
        return product;
    }
    public List<Product> findProductsByName(String name) {
        return products.stream()
                .filter(product -> product.getName().toLowerCase()
                        .contains(name.toLowerCase()))
                .toList();
    }

    public List<String> getAllSummaries(){

        List<String> summaries = new ArrayList<>();
        for (Product p : products) {
            summaries.add(p.getSummary());
        }
        return summaries;

//        return products.stream().map(p ->
//                p.getSummary()).toList();

    }

    public void testMethods(){
        Product p1 = new DigitalProduct(nextId++, "E-book",
                9.99, "/dl/1", 4.2);

        DigitalProduct dp = (DigitalProduct) p1;

        System.out.println(dp.getDownloadUrl());
    }

    public void testDiscount(){
        DigitalProduct digital = new DigitalProduct(
                1L, "Java E-book", 100.00,
                "https://example.com/java", 25.5
        );

        PhysicalProduct physical = new PhysicalProduct(
                2L, "Laptop", 100.00,
                2.1, "35 x 25 x 2 cm"
        );

        digital.applyDiscount(0.30);
        physical.applyDiscount(0.50);

        System.out.println("Digital prouduct: "+digital.getDiscountedPrice());
        System.out.println("Physical prouduct: "+physical.getDiscountedPrice());
    }

//    public void testSummary(){
//        PhysicalProduct physical = new PhysicalProduct(
//                2L, "Laptop", 100.00,
//                2.1, "35 x 25 x 2 cm"
//        );
//        physical.DisplaySummary();
//    }


}
