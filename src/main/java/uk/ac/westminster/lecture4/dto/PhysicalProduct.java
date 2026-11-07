package uk.ac.westminster.lecture4.dto;

import uk.ac.westminster.lecture4.service.interfaces.Discountable;

public class PhysicalProduct extends Product implements Discountable {
    private Double weightKg;
    private String dimensions;

    private double discountedPrice;

    public PhysicalProduct() {
    }

    public PhysicalProduct(Long id, String name, Double price, Double weightKg, String dimensions) {
        super(id, name, price);
        this.weightKg = weightKg;
        this.dimensions = dimensions;
    }

    public Double getWeightKg() {
        return weightKg;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setWeightKg(Double weightKg) {
        this.weightKg = weightKg;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }



    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    @Override
    public String getSummary() {
        return "Physical: Name: " + getName() + " | Price: " + getPrice() +
                " | Weight : " + weightKg + " | Dimensions: " + dimensions;

    }

//    @Override
//    public PhysicalProduct getRelatedProduct() {
//        return new PhysicalProduct();
//    }


    @Override
    public void displayDetails() {
        System.out.println("Shippable product");
    }

    public void DisplaySummary(){
        System.out.println(getSummary());
    }

    @Override
    public void applyDiscount(double rate) {
            double capped = Math.min(rate, 0.20);
            this.discountedPrice = getPrice()* (1 - capped);
    }

    @Override
    public double getDiscountedPrice() {
        return discountedPrice;
    }

}
