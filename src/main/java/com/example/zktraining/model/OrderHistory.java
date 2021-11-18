package com.example.zktraining.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "zk_orderhistories")
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate = new Date();

    @ManyToOne
    @JoinColumn(
            name = "status_id",
            nullable = true,
            referencedColumnName = "id"
    )
    private StatusOrder statusOrder;

    @ManyToOne
    @JoinColumn(
            name = "order_id",
            nullable = true,
            referencedColumnName = "id"
    )
    private Order order;
}
