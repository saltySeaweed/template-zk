package com.example.zktraining.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "zk_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_name")
    private String roleName;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = true,
            referencedColumnName = "id"
    )
    private User user;

    @ManyToOne
    @JoinColumn(
            name = "auhtoritie_id",
            nullable = true,
            referencedColumnName = "id"
    )
    private Authoritie authoritie;
}
