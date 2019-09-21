package com.space.repository;

import com.space.model.Ship;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipRepository extends CrudRepository<Ship, Long> {

    @Query("select s from Ship s where s.id = ?1")
    Ship findShipById(Long id);

    List<Ship> findAll();

}
