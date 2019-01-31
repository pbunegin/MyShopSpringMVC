package org.shop.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {
    @Id
    private long id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "category_code")
    private String categoryName;
    private double price;
    private String characteristic = "";
    @Column(name = "img_url")
    private String imgUrl;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
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
        this.characteristic += characteristic;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl() {
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
