package uk.ac.westminster.lecture4.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(DigitalProduct.class),
        @JsonSubTypes.Type(PhysicalProduct.class)
})
public abstract class Product {
    private Long id;
    private String name;

    private Double price;

    public Product() {
    }

    public Product(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        if (price < 0) {
            throw new IllegalArgumentException(
                    "Price cannot be negative"
            );
        }
        this.price = price;
    }

//    public String getSummary(){
//        return "name: " + name +" | price: "+ price;
//    }

//    public Product getRelatedProduct() {
//        return new Product();
//    }

    public abstract String getSummary();

    public void displayDetails() {
        System.out.println("General product");
    }
}
