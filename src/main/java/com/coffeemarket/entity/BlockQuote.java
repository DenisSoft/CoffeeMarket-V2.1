package com.coffeemarket.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Dzianis on 29.08.2017.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "blockquote")
@Data
public class BlockQuote extends BaseEntity{

    @Column(name = "text_ru")
    private String textRu;

    @Column(name = "text_en")
    private String textEn;

    @Column(name = "author_ru")
    private String authorRu;

    @Column(name = "author_en")
    private String authorEn;
}
