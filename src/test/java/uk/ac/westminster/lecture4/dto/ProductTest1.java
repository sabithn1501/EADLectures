package uk.ac.westminster.lecture4.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//import static org.junit.jupiter.api.Assertions.*;


class ProductTest1 {

    @Test
    void shouldUpdatePriceWhenValid() {
        //Arrange
        Product monitor = new PhysicalProduct(1L, "Monitor",
                249.99, 5.4, "60x40");

        //act
        monitor.setPrice(349.99);

        //assertion
        assertEquals(349.99, monitor.getPrice());

    }

    @Test
    void shouldUpdatePriceWhenValidForPhysicalProduct(){
        Product laptop = new PhysicalProduct(1l,"Laptop",300.00,1.5, "120x150");
        laptop.setPrice(100.00);
        assertEquals(100.00,laptop.getPrice());

    }

    @Test
    void shouldRejectNegativePrice() {
        //Arrange
        Product monitor = new PhysicalProduct(1L, "Monitor",
                249.99, 5.4, "60x40");

        // act and assertions
        assertThrows(IllegalArgumentException.class,
                () -> monitor.setPrice(-249.99));
    }

    @Test
    void shouldUpdatePriceWithDelta() {
        //arrange
        Product monitor = new PhysicalProduct( 1L, "Monitor",
                249.99, 5.4, "60x40");

        //act
        monitor.setPrice(349.99);

        //assert
        assertNotNull(monitor.getPrice());
        assertEquals(349.98, monitor.getPrice(),0.01);
    }

}