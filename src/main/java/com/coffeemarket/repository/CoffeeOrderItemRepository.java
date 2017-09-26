package com.coffeemarket.repository;

import com.coffeemarket.entity.CoffeeOrderItem;
import com.coffeemarket.entity.CoffeeType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Dzianis on 23.08.2017.
 */
public interface CoffeeOrderItemRepository extends PagingAndSortingRepository<CoffeeOrderItem, Long> {

    @Query("select t from CoffeeOrderItem o join o.coffeeType t where t.disabled = ?1 " +
            "group by t order by sum(quantity) desc")
    List<CoffeeType>  findAllHit(char disabled);
}
