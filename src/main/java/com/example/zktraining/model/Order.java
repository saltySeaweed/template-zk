package com.example.zktraining.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "zk_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate = new Date();

    @Column(name = "status")
    private Integer status;

    @Column(name = "total")
    private Integer total;

    @OneToMany(mappedBy = "order")
    private List<OrderHistory> orderHistory;

    @OneToMany(mappedBy = "order")
    private List<Orderdetail> orderdetail;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = true,
            referencedColumnName = "id"
    )
    private User user;
}
