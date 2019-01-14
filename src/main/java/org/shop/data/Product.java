package org.shop.data;

import java.util.HashMap;
import java.util.Map;

public class Product implements Entity {
    private String productName;
    private String categoryName;
    private Long id;
    private double price;
    private Map<String, String> characteristic = new HashMap<>();
    private String imgUrl;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Map<String, String> getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String name, String value) {
        this.characteristic.put(name, value);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl() {
        this.imgUrl = "src\\main\\webapp\\prodImg\\" + this.getProductName() + ".jpg";
    }
}
