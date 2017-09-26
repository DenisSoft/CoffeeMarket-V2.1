package com.coffeemarket.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Dzianis on 22.08.2017.
 */

@Entity
@Table(name = "coffeeorderitem")
@Data
@ToString(exclude = "coffeeOrder")
public class CoffeeOrderItem extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "type_id")
    private CoffeeType coffeeType;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private CoffeeOrder coffeeOrder;

    @Column(name = "quantity")
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CoffeeOrderItem that = (CoffeeOrderItem) o;

        if (quantity != that.quantity) return false;
        if (coffeeType != null ? !coffeeType.equals(that.coffeeType) : that.coffeeType != null) return false;
        return coffeeOrder != null ? coffeeOrder.equals(that.coffeeOrder) : that.coffeeOrder == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (coffeeType != null ? coffeeType.hashCode() : 0);
        result = 31 * result + quantity;
        return result;
    }
}
