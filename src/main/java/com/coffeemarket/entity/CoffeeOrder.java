package com.coffeemarket.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


/**
 * Created by Dzianis on 22.08.2017.
 */
@Entity
@Table(name = "coffeeorder")
@Data
public class CoffeeOrder extends BaseEntity{

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "cost")
    private double cost;

    @OneToMany(mappedBy = "coffeeOrder", cascade = CascadeType.ALL)
    private Set<CoffeeOrderItem> coffeeOrderItemList = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CoffeeOrder that = (CoffeeOrder) o;

        if (Double.compare(that.cost, cost) != 0) return false;
        if (orderDate != null ? !orderDate.equals(that.orderDate) : that.orderDate != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (deliveryAddress != null ? !deliveryAddress.equals(that.deliveryAddress) : that.deliveryAddress != null)
            return false;
        return coffeeOrderItemList != null ? coffeeOrderItemList.equals(that.coffeeOrderItemList) : that.coffeeOrderItemList == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (deliveryAddress != null ? deliveryAddress.hashCode() : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public int quantityInCart(){
        return coffeeOrderItemList.stream().mapToInt(CoffeeOrderItem::getQuantity).sum();
    }

    public void addToCart(CoffeeOrderItem coffeeOrderItem){
        Optional<CoffeeOrderItem> matchingObjects= coffeeOrderItemList
                .stream()
                .filter(o -> o.getCoffeeType().getId().equals(coffeeOrderItem.getCoffeeType().getId()))
                .findFirst();
        CoffeeOrderItem available = matchingObjects.orElse(null);
        if (available != null){
            available.setQuantity(available.getQuantity()+coffeeOrderItem.getQuantity());
        }else{
            coffeeOrderItemList.add(coffeeOrderItem);
        }
    }

    public void removeFromCart(Long id){
        Optional<CoffeeOrderItem> matchingObjects = coffeeOrderItemList
                .stream()
                .filter(o -> o.getCoffeeType().getId().equals(id))
                .findFirst();
        CoffeeOrderItem available = matchingObjects.orElse(null);
        if (available != null){
            coffeeOrderItemList.remove(available);
        }
    }

}
