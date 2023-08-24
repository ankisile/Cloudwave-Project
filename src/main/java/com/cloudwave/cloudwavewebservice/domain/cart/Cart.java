package com.cloudwave.cloudwavewebservice.domain.cart;

import com.cloudwave.cloudwavewebservice.domain.product.Product;
import com.cloudwave.cloudwavewebservice.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @ManyToOne
    private Product product;
}
