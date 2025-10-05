package com.blossom.test.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="products")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence_prod",
            sequenceName = "primary_sequence_prod",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence_prod"
    )
    private Integer id;

    @Column(length = 100)
    private String name;

    @Column(length = 500)
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    
    @Column(name="creation_date",length = 500)
    private Date date;
    
    @Column(length = 100)
    private Double price;

    @OneToMany(mappedBy = "product")
    private Set<ProductOrder> productProductOrders = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
    
    public void setPricee(final Double price) {
        this.price = price;
    }
    
    public Double getPrice() {
        return this.price;
    }

    public Set<ProductOrder> getProductProductOrders() {
        return productProductOrders;
    }

    public void setProductProductOrders(final Set<ProductOrder> productProductOrders) {
        this.productProductOrders = productProductOrders;
    }
    
    @PrePersist
    protected void onCreate() {
    	this.date = new Date();
    }
    
    @PreUpdate
    private void onUpdate() {
    	this.date = new Date();
    }

}
