package com.blossom.test.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.blossom.test.constants.EnumOrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence_ord",
            sequenceName = "primary_sequence_ord",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence_ord"
    )
    private Integer id;

    @Column(nullable = false)
    private Date orderDate;

    @Column(nullable = false)
    private String orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order")
    private List<ProductOrder> orderProductOrders = new ArrayList<>();

    @OneToMany(mappedBy = "order")
    private List<Payment> orderPayments = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(final Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(final String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public List<ProductOrder> getOrderProductOrders() {
        return orderProductOrders;
    }

    public void setOrderProductOrders(final List<ProductOrder> orderProductOrders) {
        this.orderProductOrders = orderProductOrders;
    }

    public List<Payment> getOrderPayments() {
        return orderPayments;
    }

    public void setOrderPayments(final List<Payment> orderPayments) {
        this.orderPayments = orderPayments;
    }
    
    @PrePersist
    protected void onCreate() {
    	this.orderDate = new Date();
    	this.orderStatus = EnumOrderStatus.PENDING.getCode();
    }

}
