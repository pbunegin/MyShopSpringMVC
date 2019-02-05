package org.shop.data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Basket {
    @ManyToOne
    @JoinColumn(name = "order_number")
    private Long orderNumber;
    @ManyToOne
    @JoinColumn(name = "product_code")
    private Long productCode;

    private int quantity;

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
