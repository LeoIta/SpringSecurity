package com.leoita.repository;

import com.leoita.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends CrudRepository<Card, Long> {

    List<Card> findByCustomerId(long customerId);

}
