package org.shop.data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_number")
    private Long orderNumber;

    @Column(name = "point_code")
    private Long pointCode = 1L;

    @Column(name = "user_code")
    private Long userCode;

    private String status = "OK";

    private LocalDate date;

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

    public Long getUserCode() {
        return userCode;
    }

    public void setUserCode(Long userCode) {
        this.userCode = userCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
