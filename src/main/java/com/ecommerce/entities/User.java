package com.ecommerce.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "user_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String location;

    private String password;

    @Column(unique=true)
    private String email;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CartDetails cartDetails;

}
