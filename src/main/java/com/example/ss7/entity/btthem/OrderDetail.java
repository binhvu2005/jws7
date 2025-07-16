package com.example.ss7.entity.btthem;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data

public class OrderDetail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailId;

    private Integer quantity;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;
}
