package uk.ac.westminster.lecture4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.ac.westminster.lecture4.util.ProductValidator;

@SpringBootApplication
public class Lecture4Application {

    public static void main(String[] args) {
        SpringApplication.run(Lecture4Application.class, args);
//
//        try {
//            ProductValidator.checkProduct(25L);
//        } catch (RuntimeException ex){
//            System.out.println(ex.getMessage());
//        }


        //try contains code that might throw an exception.
//        try {
//            int result = 10 / 0;
//            System.out.println(result);
//
//        //catch handles an exception of a matching type.
//        } catch (ArithmeticException e) {
//            System.out.println("Cannot divide by zero!");
//        }
//        catch (Exception e) {
//            System.out.println("General error occurred.");
//
//        //finally normally runs whether the exception occurs or not, making it useful for cleanup.
//        } finally {
//            System.out.println("Execution complete.");
//        }


    }

}
