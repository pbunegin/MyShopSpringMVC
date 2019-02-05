package org.shop.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_code")
    private Long id;
    @Column(name = "product_name")
    private String productName;
    @ManyToOne
    @JoinColumn(name = "category_code")
    @JsonProperty(value = "categoryName")
    private Category category;
    private double price;
    private String characteristic = "";
    @Column(name = "img_url")
    private String imgUrl;
    @Column(name = "maker_code")
    private Long maker = 1L;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getMaker() {
        return maker;
    }

    public void setMaker(Long maker) {
        this.maker = maker;
    }

    @PostConstruct
    public void refreshImgUrl() {
        this.imgUrl = "/prodImg/" + this.getId() + ".jpg?" + Math.random();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Double.compare(product.price, price) == 0 &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(characteristic, product.characteristic) &&
                Objects.equals(imgUrl, product.imgUrl);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productName, id, price, characteristic, imgUrl);
    }
}
