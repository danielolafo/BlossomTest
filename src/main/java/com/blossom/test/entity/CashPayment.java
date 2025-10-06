package com.blossom.test.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
//@Table(name="cash_payment")
@DiscriminatorValue("CASH")
public class CashPayment extends Payment{

//    @Id
//    @Column(nullable = false, updatable = false)
//    @SequenceGenerator(
//            name = "primary_sequence_pay_card",
//            sequenceName = "primary_sequence_pay_card",
//            allocationSize = 1,
//            initialValue = 10000
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "primary_sequence_pay_card"
//    )
//    private Integer id;

//    @Column(nullable = false)
//    private Boolean status;

//    @Column(nullable = false)
    private Date paymentDate;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "payment_id", nullable = false)
//    private Payment payment;

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(final Integer id) {
//        this.id = id;
//    }

//    public Boolean getStatus() {
//        return status;
//    }
//
//    public void setStatus(final Boolean status) {
//        this.status = status;
//    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(final Date paymentDate) {
        this.paymentDate = paymentDate;
    }

//    public Payment getPayment() {
//        return payment;
//    }
//
//    public void setPayment(final Payment payment) {
//        this.payment = payment;
//    }

}
