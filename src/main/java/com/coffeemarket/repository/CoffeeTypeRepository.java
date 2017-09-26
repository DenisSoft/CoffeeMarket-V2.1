package com.coffeemarket.repository;

import com.coffeemarket.entity.CoffeeOrder;
import com.coffeemarket.entity.CoffeeType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzianis on 23.08.2017.
 */
public interface CoffeeTypeRepository extends PagingAndSortingRepository<CoffeeType, Long> {

    List<CoffeeType> findByDisabledAndNameEnContainsOrNameRuContains(char disabled, String nameEn, String nameRu);
    List<CoffeeType> findByDisabledOrderByPriceAsc(char disabled);
    List<CoffeeType> findByDisabledOrderByPriceDesc(char disabled);
    List<CoffeeType> findTop2ByDisabledOrderByIdDesc(char disabled);
    List<CoffeeType> findAllByDisabled(char disabled);

}
