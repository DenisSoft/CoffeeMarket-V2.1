package com.coffeemarket.repository;

import com.coffeemarket.entity.CoffeeOrderItem;
import com.coffeemarket.entity.Configuration;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Dzianis on 23.08.2017.
 */
public interface ConfigurationRepository extends CrudRepository<Configuration, String> {

}
