package com.blossom.test.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="categories")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
	
	@Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence_cat",
            sequenceName = "primary_sequence_cat",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence_cat"
    )
	private Integer id;
	
	@Column()
	private String name;
	
	@Column()
	private Date creationDate;
	
	

}
