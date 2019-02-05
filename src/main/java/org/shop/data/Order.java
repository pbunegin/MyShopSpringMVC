package org.shop.data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany
    @JoinTable(name = "basket",
            joinColumns = @JoinColumn(name = "order_number"),
            inverseJoinColumns = @JoinColumn(name = "product_code")
    )
    private List<Product> products = new ArrayList<>();

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
