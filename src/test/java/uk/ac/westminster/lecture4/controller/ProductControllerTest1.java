package uk.ac.westminster.lecture4.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import uk.ac.westminster.lecture4.dto.DigitalProduct;
import uk.ac.westminster.lecture4.exception.ProductNotFoundException;
import uk.ac.westminster.lecture4.service.ProductService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Spring Boot 4: spring-boot-starter-webmvc-test
// MockMvc allows us to test Spring MVC controllers without starting a real HTTP server.
// This annotation tells Spring Boot to load only the web layer required for the controller test.
@WebMvcTest(ProductController.class)
class ProductControllerTest1 {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService service;


    @Test
    void shouldReturn404WhenProductMissing() throws
            Exception {

        //arrange and act
        when(service.getProductById(99L))
                .thenThrow(new
                        ProductNotFoundException(99L));

        //Assertion
        mockMvc.perform(get("/products/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnProductAsJson() throws Exception {
        when(service.getProductByUniqueId(1L))
                .thenReturn(new DigitalProduct(1L, "E-book", 9.99, "/dl/1", 4.2));
        mockMvc.perform(get("/products/exception/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                        .value("E-book"));
    }

    @Test
    void shouldDeleteProductAndReturnOk() throws Exception {

        when(service.deleteProduct(1L))
                .thenReturn("Successfully deleted");

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully deleted"));

        verify(service).deleteProduct(1L);
    }

    @Test
    void shouldDeleteProductAndReturnNoContent() throws Exception {

        when(service.deleteProduct(1L))
                .thenReturn("Successfully deleted");

        mockMvc.perform(delete("/products/test/1"))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));

        verify(service).deleteProduct(1L);
    }


}

