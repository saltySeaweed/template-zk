package com.example.zktraining.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "zk_categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "icon")
    private String icon;

    @OneToMany(mappedBy = "category")
    private List<Product> product;

    @OneToMany(mappedBy = "category")
    private List<Category> listCategories;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "category_id",
            nullable = true,
            referencedColumnName = "id"
    )
    private Category category;

}
