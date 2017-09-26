package com.coffeemarket.repository;

import com.coffeemarket.entity.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by Dzianis on 22.08.2017.
 */
public interface CoffeeOrderRepository extends CrudRepository<CoffeeOrder, Long> {

    CoffeeOrder findByName(String name);
}
