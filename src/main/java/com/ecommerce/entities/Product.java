package com.ecommerce.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private String price;

    @Column(name = "product_details")
    private String productDetails;

    @ManyToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<CartDetails> carts;

}
