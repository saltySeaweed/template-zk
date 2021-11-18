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
@Table(name = "zk_statusorders")
public class StatusOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "note")
    private String note;

    @OneToMany(mappedBy = "statusOrder")
    private List<OrderHistory> orderHistory;
}
