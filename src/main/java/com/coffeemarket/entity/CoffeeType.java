package com.coffeemarket.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;


/**
 * Created by Dzianis on 23.08.2017.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "coffeetype")
@Data
public class CoffeeType extends BaseEntity{

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "description_ru")
    private String descriptionRu;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "description_en")
    private String descriptionEn;

    @Column(name = "price")
    private double price;

    @Column(name = "image")
    private String image;

    @Column(name = "disabled")
    private char disabled;
}
