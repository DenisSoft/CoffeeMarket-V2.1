package com.coffeemarket.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Dzianis on 22.05.2017.
 */
@MappedSuperclass
@Data
abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;
}
