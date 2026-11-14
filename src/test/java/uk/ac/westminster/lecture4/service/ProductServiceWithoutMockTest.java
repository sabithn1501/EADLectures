package uk.ac.westminster.lecture4.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.westminster.lecture4.dto.DigitalProduct;
import uk.ac.westminster.lecture4.dto.Product;
import uk.ac.westminster.lecture4.exception.ProductNotFoundException;
import uk.ac.westminster.lecture4.repository.InMemoryProductRepository;
import uk.ac.westminster.lecture4.repository.interfaces.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceWithoutMockTest {

    private ProductRepository repository;
    private ProductService service;

    // runs before EVERY @Test: a fresh,
    // empty repository each time
    @BeforeEach
    void setUp() {
        repository = new InMemoryProductRepository();
        service = new ProductService(repository);

    }

    @Test
    void shouldReturnProductWhenFound() {
        //arrange
        repository.saveProduct(
                new DigitalProduct(null, "E-book",
                        9.99, "/dl/1", 4.2));

        //act
        Product product = service.getProductByUniqueId(3L);

        assertEquals("E-book", product.getName());
    }

    @Test
    void shouldThrowWhenProductNotFound() {
        assertThrows(ProductNotFoundException.class,
                () -> service.getProductByUniqueId(3L));
    }
}

