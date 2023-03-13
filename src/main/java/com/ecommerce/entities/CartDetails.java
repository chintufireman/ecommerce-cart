package com.ecommerce.entities;

import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart_details")
@Getter
@Setter
public class CartDetails {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartId;

    @Column(name = "total_items")
    private int totalItems;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_cart",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> product;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
