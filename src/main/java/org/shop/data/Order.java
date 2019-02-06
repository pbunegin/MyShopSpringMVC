package org.shop.data;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_number")
    private Long orderNumber;

    @Column(name = "point_code")
    private Long pointCode = 1L;

    @ManyToOne
    @JoinColumn(name = "user_code")
    private User user;

    private String status = "OK";

    @JsonIgnore
    @ElementCollection
    @Column(name = "quantity")
    @CollectionTable(name = "basket",
            joinColumns = @JoinColumn(name = "order_number"))
    @MapKeyJoinColumn(name = "product_code")
    Map<Product, Long> products = new HashMap<>();


    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getPointCode() {
        return pointCode;
    }

    public void setPointCode(Long pointCode) {
        this.pointCode = pointCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<Product, Long> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Long> products) {
        this.products = products;
    }

    @JsonGetter
    public Map<Long, Long> getProductsWithId() {
        Map<Long, Long> result = new HashMap<>();
        for (Map.Entry<Product, Long> map: this.products.entrySet()) {
            result.put(map.getKey().getId(), map.getValue());
        }
        return result;
    }

}
