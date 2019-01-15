package org.shop.data;

import java.util.HashMap;
import java.util.Map;

public class Product {
    private String productName;
    private int id;
    private double price;
    private Map<String, String> characteristic = new HashMap<>();
    private String imgUrl;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl() {
        this.imgUrl = "/prodImg/" + this.getProductName().replaceAll("\\s","_") + ".jpg";
    }
}
