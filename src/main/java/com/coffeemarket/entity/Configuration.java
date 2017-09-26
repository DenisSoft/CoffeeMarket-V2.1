package com.coffeemarket.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Dzianis on 23.08.2017.
 */
@Entity
@Table(name = "configuration")
@Data
public class Configuration {

    @Id
    private String id;

    @Column(name = "value")
    private int value;
}
