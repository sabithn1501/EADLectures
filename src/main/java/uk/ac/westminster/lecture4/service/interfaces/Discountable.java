package uk.ac.westminster.lecture4.service.interfaces;

public interface Discountable {

    /*
    // A pure contract. No bodies, no fields, no constructor.
    // Any class that implements this promises to provide both
    // methods — but decides for itself how they behave.
     */
    void applyDiscount(double rate);

    double getDiscountedPrice();
}
