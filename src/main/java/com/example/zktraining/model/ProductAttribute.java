package com.example.zktraining.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "zk_product_attributes")
public class ProductAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(
            name = "product_id",
            nullable = true,
            referencedColumnName = "id"
    )
    private Product product;

    @ManyToOne
    @JoinColumn(
            name = "attribute_id",
            nullable = true,
            referencedColumnName = "id"
    )
    private Attribute attribute;
}
