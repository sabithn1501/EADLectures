package uk.ac.westminster.lecture4.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.ac.westminster.lecture4.dto.DigitalProduct;
import uk.ac.westminster.lecture4.dto.Product;
import uk.ac.westminster.lecture4.exception.ProductNotFoundException;
import uk.ac.westminster.lecture4.repository.interfaces.ProductRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//Hooks Mockito into JUnit 5.
//Integrates Mockito with JUnit 5 and manages mock initialization.
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    // no new ProductService(...) here:
    // Mockito calls the constructor

    //Creates a dynamic fake of a collaborator, Creates a mock repository instead of using the actual repository implementation.
    @Mock
    private ProductRepository repository;

    //Creates the real class being tested and injects its mock dependencies.
    @InjectMocks
    private ProductService service;


    @Test
    void shouldReturnProductWhenFoundHappyPath() {
        //Defines what the mock should return when a method is called.
        when(repository.findProductById(1L)).thenReturn(Optional.of(new DigitalProduct(1L, "E-book", 9.99, "/dl/1", 4.2)));

        Product product = service.getProductByUniqueId(1L);


        assertEquals("E-book", product.getName());
        assertEquals(9.99, product.getPrice(), 0.01);
    }

    @Test
    void shouldThrowWhenProductNotFound() {
        when(repository.findProductById(999L)).thenReturn(Optional.empty());


        assertThrows(ProductNotFoundException.class, () -> service.getProductByUniqueId(999L));
    }


    @Test
    void shouldSaveNewProduct() {
        //Arrange
        Product digitalProduct = new DigitalProduct(null, "E-book", 9.99, "/dl/1", 4.2);
        Product expectedDigitalProduct = new DigitalProduct(1L, "E-book", 9.99, "/dl/1", 4.2);
        when(repository.saveProduct(digitalProduct)).thenReturn(expectedDigitalProduct);

        //act
        Product actualDigitalProduct = service.addProduct(digitalProduct);

        assertEquals(expectedDigitalProduct.getId(),actualDigitalProduct.getId());
        //Checks whether a method was called on the mock.
        verify(repository).saveProduct(digitalProduct);
    }

    @Test
    void shouldReturnProductWhenFound() {
        when(repository.findProductById(1L))
                .thenReturn(Optional.of(new DigitalProduct(1L, "E-book", 9.99, "/dl/1", 4.2)));

        Product product = service.getProductByUniqueId(1L);

        assertEquals("E-book", product.getName());
        assertEquals(9.99, product.getPrice(), 0.01);
        verify(repository).findProductById(1L);
        verify(repository, never()).findByName(any());
    }


}