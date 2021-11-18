package com.example.zktraining.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "zk_products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Integer price;

    @OneToMany(mappedBy = "product")
    private List<Orderdetail> orderdetail;

    @OneToMany(mappedBy = "product")
    private List<ProductAttribute> productAttributes;

    @OneToMany(mappedBy = "product")
    private List<Images> images;

    @OneToOne(mappedBy = "product")
    private Sale sale;

    @ManyToOne
    @JoinColumn(
            name = "category_id",
            nullable = true,
            referencedColumnName = "id"
    )
    private Category category;


}
