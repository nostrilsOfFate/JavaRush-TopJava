package com.space.repository;

import com.space.model.Ship;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipRepository extends CrudRepository<Ship, Long> {

    List<Ship> getAll();

    Ship getById(Long id);
}
