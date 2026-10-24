package uk.ac.westminster.lecture4.dto;

import uk.ac.westminster.lecture4.service.interfaces.Discountable;

public class DigitalProduct extends Product implements Discountable {
        private String downloadUrl;
        private Double fileSizeMB;
        private double discountedPrice;

    public DigitalProduct(Long id, String name, Double price, String downloadUrl, Double fileSizeMB) {
        super(id, name, price);
        this.downloadUrl = downloadUrl;
        this.fileSizeMB = fileSizeMB;
    }

    public DigitalProduct() {
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public Double getFileSizeMB() {
        return fileSizeMB;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public void setFileSizeMB(Double fileSizeMB) {
        this.fileSizeMB = fileSizeMB;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    @Override
    public String getSummary() {
        return "Digital: Name: " + getName() +
                " | Price: " + getPrice() + " | Url: " +
        downloadUrl + " | FileSize: " + fileSizeMB;
    }




    @Override
    public void applyDiscount(double rate) {
        // no shipping: full rate applies
        discountedPrice = getPrice() * (1 - rate);
    }

//    @Override
//    public DigitalProduct getRelatedProduct() {
//        return new DigitalProduct();
//    }

    //    public void applyDiscount() {
//        price = 0.0; // Compilation error!
//    }


//    @Override


//    public void displayDetails() {
//        System.out.println("Downloadable product");
//    }


}



