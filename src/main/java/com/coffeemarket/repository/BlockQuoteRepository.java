package com.coffeemarket.repository;

import com.coffeemarket.entity.BlockQuote;
import com.coffeemarket.entity.CoffeeOrderItem;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Dzianis on 29.08.2017.
 */
public interface BlockQuoteRepository extends CrudRepository<BlockQuote, Long> {
}
