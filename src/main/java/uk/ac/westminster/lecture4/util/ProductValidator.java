package uk.ac.westminster.lecture4.util;

public class ProductValidator {
    public static void checkProduct(Long id) {
        //Java has thrown an exception because an operation failed.
        // But we can also throw an exception ourselves when a business rule is not satisfied.
        if (id > 20) {
            throw new RuntimeException("Product is not found!");
        }

        System.out.println("Product exists in the inventory");
    }
}
