package uk.ac.westminster.lecture4.service;

import org.springframework.stereotype.Service;
import uk.ac.westminster.lecture4.dto.DigitalProduct;
import uk.ac.westminster.lecture4.dto.PhysicalProduct;
import uk.ac.westminster.lecture4.dto.Product;
import uk.ac.westminster.lecture4.exception.ProductNotFoundException;
import uk.ac.westminster.lecture4.service.interfaces.Discountable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();

    //nextId++ — use the current value, then increment.
    //++nextId — increment first, then use the new value.
    private Long nextId = 1L;

    public ProductService() {
        System.out.println("ProductService constructor executed");
        // Note: no new Product(...) anywhere — it is abstract now.
        products.add(new DigitalProduct(
                nextId++, "Clean Code (E-book)", 29.99,
                "/downloads/clean-code.pdf", 4.2));
        products.add(new PhysicalProduct(
                nextId++, "27-inch Monitor", 249.99,
                5.4, "61cm x 41cm x 18cm"));

//        products.add(new Product(nextId++, "book", 100.00));
    }

    public List<Product> getProducts() {
        products.get(0);
        System.out.println("getProducts() called");
        return products;
    }

    public Optional<Product> getProductById(Long id) {
        System.out.println("product Service getProductById invoked");
        return products.stream()
                    .filter(p -> p.getId().equals(id))
                    .findFirst();
    }

    // Notice that the return type has changed from Optional<Product> to Product.
    /*The service now has a clear contract:
        - If the product exists, return it.
        - If the product doesn't exist, throw ProductNotFoundException.
    * */
    public Product getProductByUniqueId(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new ProductNotFoundException(id));
    }

    public String deleteProduct(Long id) {
        boolean flag = products.removeIf(product -> product.getId().equals(id));

        if (flag) {
            return "Successfully deleted";

        } else {
            throw new ProductNotFoundException(id);
        }
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
            if (p instanceof Discountable d) {
                d.applyDiscount(0.10);
                System.out.println("Digital prouduct: "+d.getDiscountedPrice());
            }
        }
        return summaries;

//        return products.stream().map(p ->
//                p.getSummary()).toList();

    }

    public void testMethods(){
        Product p1 = new DigitalProduct(nextId++, "E-book",
                9.99, "/dl/1", 4.2);
        p1.getName();

//        Product a = new Product(1L, "Mug", 8.50);

       System.out.println ( "download url: "+((DigitalProduct)
               p1).getDownloadUrl());

        DigitalProduct dp = (DigitalProduct) p1;

//        System.out.println(((DigitalProduct)
//                a).getDownloadUrl());
//
//        System.out.println(dp.getDownloadUrl());
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
