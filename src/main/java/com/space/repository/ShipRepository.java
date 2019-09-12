package com.space.repository;

import com.space.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {

////
//public Ship create(Ship ship);
//
//    public Boolean delete(Long id);
//
//    public List<Ship> getAll();
//
//    public Integer getCount();
//
//    public Ship get(Long id);
//
//    public Ship update(Ship ship);



    public Ship create(Ship ship);

  //  @Query("delete from cosmoport.ship where ship.id =:?")
    public Boolean delete(@Param("id") Long id);

    public List<Ship> getAll();

    public Integer getCount();



    Ship getShipById(Long id);

    public Ship update(Ship ship);
}
